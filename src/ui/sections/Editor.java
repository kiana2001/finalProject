package ui.sections;

import core.Pattern;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.GenericStyledArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.model.Paragraph;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;
import org.reactfx.Subscription;
import org.reactfx.collection.ListModification;
import ui.Utility;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Matcher;


import static core.Interpreter.getType;

public class Editor extends CodeArea {
    private final ExecutorService executor;
    private static ContextMenu suggest=new ContextMenu();
    private Task<List<String>> computeHighlightingAsync() {
        String text = this.getText();

        Task<List<String>> task = new Task<>() {
            @Override
            protected List<String> call() {
                return autoComplete(text);
            }
        };
        executor.execute(task);
        return task;
    }
    public List<String> autoComplete(String text){
        try {
            suggest.hide();
        }
        catch (Exception ignored){

        }
        if(text.charAt(text.length() - 1)!=' ') {
            var split = text.split(" |\n");
            List<String> vars = new ArrayList<>();
            for (String word : split) {
                if (word.matches(Pattern.VARIABLE_NAME))
                    vars.add(word);
            }
            if (split.length > 0) {
                String last = split[split.length - 1];
                last = last.replace("\n", "");
                var keyWord = Utility.getMatches(Arrays.asList(Pattern.KEYWORDS), "^" + last + "\\w+");
                var varMatch = Utility.getMatches(vars, "^" + last + "\\w+");
                for (var x : varMatch) {
                    if (!keyWord.contains(x))
                        keyWord.add(x);
                }
                keyWord.add(last);
                return keyWord;
            } else {
                Set<String> set = new HashSet<>(vars);
                vars.clear();
                vars.addAll(set);
                vars.add("");
                return vars;
            }
        }
        else{
            List<String> nullList=new ArrayList<>();
            nullList.add(" ");
            return nullList;
        }
    }
    public void contextMenu(List<String> texts){
        Collections.reverse(texts);
        suggest.getItems().retainAll();
        var length =texts.get(0).length();
        texts.remove(0);
        if(texts.size()>0){

            double x=this.caretBoundsProperty().getValue().get().getMaxX();
            double y=this.caretBoundsProperty().getValue().get().getMaxY();

            for(var t :texts){
                var item=new MenuItem(t);
                suggest.getItems().add(item);
                item.setOnAction(e->{
                    this.deleteText(this.getCaretPosition()-length,this.getCaretPosition());
                    this.appendText(item.getText());

                });
            }
            suggest.show(this,x,y);

        }
    }
    public Editor() {
        executor = Executors.newSingleThreadExecutor();
        setParagraphGraphicFactory(LineNumberFactory.get(this));
        setContextMenu(new DefaultContextMenu());
        Subscription cleanupWhenDone = this.multiPlainChanges()
                .successionEnds(Duration.ofMillis(500))
                .supplyTask(this::computeHighlightingAsync)
                .awaitLatest(this.multiPlainChanges())
                .filterMap(t -> {
                    if(t.isSuccess()) {
                        return Optional.of(t.get());
                    } else {
                        t.getFailure().printStackTrace();
                        return Optional.empty();
                    }
                })
                .subscribe(this::contextMenu);

        getVisibleParagraphs().addModificationObserver(new VisibleParagraphStyler<>(this, this::computeHighlighting));

       this.caretPositionProperty().addListener(e->{
           try {
               suggest.hide();
           }
           catch (Exception ignored){

           }
       });

        final java.util.regex.Pattern whiteSpace = java.util.regex.Pattern.compile("^\\s+");
        addEventHandler(KeyEvent.KEY_PRESSED, KE ->
        {
            if (KE.getCode() == KeyCode.ENTER) {
                int caretPosition = getCaretPosition();
                int currentParagraph = getCurrentParagraph();
                Matcher m0 = whiteSpace.matcher(getParagraph(currentParagraph - 1).getSegments().get(0));
                if (m0.find()) Platform.runLater(() -> insertText(caretPosition, m0.group()));
            }
        });
        setText("\n");
    }

    public Editor(String text) {
        this();
        setText(text);
    }

    private StyleSpans<Collection<String>> computeHighlighting(String text) {


        StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();

        if (getType(text) == null)
            spansBuilder.add(Collections.singleton("error"), text.length());
        else {
            int lastKwEnd = 0;
            Matcher matcher = java.util.regex.Pattern.compile(core.Pattern.HIGHLIGHT).matcher(text);

            while (matcher.find()) {
                String styleClass =
                        matcher.group("KEYWORD") != null ? "keyword" :
                                matcher.group("PAREN") != null ? "paren" :
                                        matcher.group("BRACE") != null ? "brace" :
                                                matcher.group("BRACKET") != null ? "bracket" :
                                                        matcher.group("SEMICOLON") != null ? "semicolon" :
                                                                matcher.group("STRING") != null ? "string" :
                                                                        matcher.group("COMMENT") != null ? "comment" : null;

                spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
                spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
                lastKwEnd = matcher.end();
            }
            spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);
        }

        return spansBuilder.create();
    }

    public void setText(String text) {
        this.replaceText(0, 0, text);
    }

    private static class VisibleParagraphStyler<PS, SEG, S> implements Consumer<ListModification<? extends Paragraph<PS, SEG, S>>> {
        private final GenericStyledArea<PS, SEG, S> area;
        private final Function<String, StyleSpans<S>> computeStyles;
        private int prevParagraph, prevTextLength;

        public VisibleParagraphStyler(GenericStyledArea<PS, SEG, S> area, Function<String, StyleSpans<S>> computeStyles) {
            this.computeStyles = computeStyles;
            this.area = area;
        }

        @Override
        public void accept(ListModification<? extends Paragraph<PS, SEG, S>> lm) {
            if (lm.getAddedSize() > 0) {
                int paragraph = Math.min(area.firstVisibleParToAllParIndex() + lm.getFrom(), area.getParagraphs().size() - 1);
                String text = area.getText(paragraph, 0, paragraph, area.getParagraphLength(paragraph));

                if (paragraph != prevParagraph || text.length() != prevTextLength) {
                    int startPos = area.getAbsolutePosition(paragraph, 0);
                    Platform.runLater(() -> area.setStyleSpans(startPos, computeStyles.apply(text)));
                    prevTextLength = text.length();
                    prevParagraph = paragraph;
                }
            }
        }
    }

    private static class DefaultContextMenu extends ContextMenu {

        public DefaultContextMenu() {
            MenuItem fold = new MenuItem("Fold selected text");
            fold.setOnAction(AE -> {
                hide();
                fold();
            });

            MenuItem unfold = new MenuItem("Unfold from cursor");
            unfold.setOnAction(AE -> {
                hide();
                unfold();
            });

            MenuItem print = new MenuItem("Print");
            print.setOnAction(AE -> {
                hide();
                print();
            });

            getItems().addAll(fold, unfold, print);
        }

        /**
         * Folds multiple lines of selected text, only showing the first line and hiding the rest.
         */
        private void fold() {
            ((CodeArea) getOwnerNode()).foldSelectedParagraphs();
        }

        /**
         * Unfold the CURRENT line/paragraph if it has a fold.
         */
        private void unfold() {
            CodeArea area = (CodeArea) getOwnerNode();
            area.unfoldParagraphs(area.getCurrentParagraph());
        }

        private void print() {
            System.out.println(((CodeArea) getOwnerNode()).getText());
        }
    }
}

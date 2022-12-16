package com.akawane0813.textEditor;

import com.akawane0813.editorElements.CustomCharacter;
import com.akawane0813.editorElements.CustomFont;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

/**
 * This class uses the Flyweight pattern to reduce the memory usage of storing text with similar font styles.
 * The `CustomCharacter` and `CustomFont` classes are used as flyweight objects to store character and font
 * information, respectively.
 */

public class FlyWeightTextEditor implements TextEditor {
    private RunArray runArray;
    private List<CustomCharacter> text;

    public RunArray getRunArray() {
        return runArray;
    }

    public List<CustomCharacter> getText() {
        return text;
    }

    public FlyWeightTextEditor() {
        this.runArray = new RunArray();
        this.text = new ArrayList<>();
    }

    /**
     * Write text with similar font, CustomCharacter valueOf uses flyweight object
     */
    @Override
    public void write(StyledText styledText) {

        runArray.appendRun(styledText.text().length(), styledText.font());

        for(char c: styledText.text().toCharArray()) {
            text.add(CustomCharacter.valueOf(c));
        }
    }

    @Override
    public void write(List<StyledText> textList) {
        for(StyledText styledText: textList) {
            write(styledText);
        }
    }

     class RunArray {
        private ArrayList<FontInfo> runArrayList = new ArrayList<>();

        public void addRun(int startIndex, int run, Font font) {
            runArrayList.add(new FontInfo(startIndex, run, CustomFont.valueOf(font)));
        }

        public void appendRun(int run, Font font) {
            FontInfo prev = runArrayList.size() == 0 ? null : runArrayList.get(runArrayList.size() - 1);
            int startIndex = prev == null ? 0 : prev.startIndex + prev.run;
            runArrayList.add(new FontInfo(startIndex, run, CustomFont.valueOf(font)));
        }

         /**
          * Returns the font at the specified index in the run array.
          * @param index the index of the font in the run array
          * @return the font at the specified index, or `null` if no font is found at the index
          */
        public Font getFont(int index) {
            return runArrayList.stream()
                    .filter(fontInfo -> index >= fontInfo.startIndex() &&
                            index <= fontInfo.startIndex() + fontInfo.run())
                    .map(fontInfo -> fontInfo.font())
                    .map(customFont -> customFont.getFont())
                    .findFirst()
                    .orElse(null);
        }
    }

    /**
     * A Record to maintain font information
     */
    public record FontInfo(int startIndex, int run, CustomFont font) {}
}

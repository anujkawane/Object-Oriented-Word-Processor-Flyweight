package com.akawane0813.textEditor;

import com.akawane0813.editorElements.CustomCharacter;
import com.akawane0813.editorElements.CustomFont;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the TextEditor interface without using the Flyweight pattern.
 * The `StyledChar` inner class is used to store information about each character
 * in the text, including its font style.
 */
public class RegularTextEditor implements TextEditor {

    private List<StyledChar> text;

    public List<StyledChar> getText() {
        return text;
    }

    public RegularTextEditor() {
        this.text = new ArrayList<>();
    }

    @Override
    public void write(StyledText styledText) {
        for(char c: styledText.text().toCharArray()) {
            text.add(new StyledChar(new CustomCharacter(c), new CustomFont(styledText.font())));
        }
    }

    @Override
    public void write(List<StyledText> textList) {
        for(StyledText styledText: textList) {
            write(styledText);
        }
    }
}

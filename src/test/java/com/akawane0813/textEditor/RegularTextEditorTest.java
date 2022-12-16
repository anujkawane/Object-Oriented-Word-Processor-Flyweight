package com.akawane0813.textEditor;

import org.junit.jupiter.api.Test;

import java.awt.Font;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegularTextEditorTest {
    @Test
    public void testWrite() {
        // Create a RegularTextEditor instance
        RegularTextEditor editor = new RegularTextEditor();

        // Create a StyledText instance with some text and a font
        StyledText styledText = new StyledText("Hello, World!", new Font("Arial", Font.PLAIN, 12));

        // Write the styled text to the editor
        editor.write(styledText);

        // Check that the text was written correctly
        assertEquals("Hello, World!", editor.getText().stream()
                .map(s -> String.valueOf(s.value().getValue()))
                .collect(Collectors.joining()));

        // Check that the font was written correctly
        assertEquals(new Font("Arial", Font.PLAIN, 12), editor.getText().get(0).font().getFont());
    }

    @Test
    public void testWriteList() {
        // Create a RegularTextEditor instance
        RegularTextEditor editor = new RegularTextEditor();

        // Create a list of StyledText instances with different fonts
        List<StyledText> textList = Arrays.asList(
                new StyledText("Hello, World!", new Font("Arial", Font.PLAIN, 12)),
                new StyledText("Goodbye, World!", new Font("Arial", Font.BOLD, 12))
        );

        // Write the list of styled text to the editor
        editor.write(textList);

        // Check that the text was written correctly
        assertEquals("Hello, World!Goodbye, World!", editor.getText().stream()
                .map(s -> String.valueOf(s.value().getValue()))
                .collect(Collectors.joining()));

        // Check that the fonts were written correctly
        assertEquals(new Font("Arial", Font.PLAIN, 12), editor.getText().get(0).font().getFont());
        assertEquals(new Font("Arial", Font.BOLD, 12), editor.getText().get(13).font().getFont());

        // Check that the font is returned correctly for each index in the text
        for (int i = 0; i < editor.getText().size(); i++) {
            if (i < 13) {
                assertEquals(new Font("Arial", Font.PLAIN, 12), editor.getText().get(i).font().getFont());
            } else {
                assertEquals(new Font("Arial", Font.BOLD, 12), editor.getText().get(i).font().getFont());
            }
        }
    }
}


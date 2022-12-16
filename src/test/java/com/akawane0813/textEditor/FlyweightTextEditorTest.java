package com.akawane0813.textEditor;

import com.akawane0813.editorElements.CustomCharacter;
import com.akawane0813.util.SizeofUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FlyweightTextEditorTest {

    @Test
    public void testCharacterPointsToSameObject() {
        // Create a FlyWeightTextEditor instance
        FlyWeightTextEditor editor = new FlyWeightTextEditor();

        // Create a StyledText instance with some text and a font
        StyledText styledText = new StyledText("Hello, World!", new Font("Arial", Font.PLAIN, 12));

        // Write the styled text to the editor
        editor.write(styledText);

        //here l is repeated at index 2,3 and 10
        CustomCharacter expectedChar = CustomCharacter.valueOf('l');

        // Check that the text was written correctly
        assertSame(expectedChar, editor.getText().get(2));
        assertSame(expectedChar, editor.getText().get(3));
        assertSame(expectedChar, editor.getText().get(10));

        //check instances created by new is not flyweight
        assertNotSame(expectedChar, new CustomCharacter('l'));

    }
    @Test
    public void testWrite() {
        // Create a FlyWeightTextEditor instance
        FlyWeightTextEditor editor = new FlyWeightTextEditor();

        // Create a StyledText instance with some text and a font
        StyledText styledText = new StyledText("Hello, World!", new Font("Arial", Font.PLAIN, 12));

        // Write the styled text to the editor
        editor.write(styledText);

        String actualText = getTextFromEditor(editor);

        // Check that the text was written correctly
        assertEquals("Hello, World!", actualText);

        // Check that the font was written correctly
        assertEquals(new Font("Arial", Font.PLAIN, 12), editor.getRunArray().getFont(0));

    }

    @Test
    public void testWriteList() {
        // Create a FlyWeightTextEditor instance
        FlyWeightTextEditor editor = new FlyWeightTextEditor();

        // Create a list of StyledText instances with different fonts
        List<StyledText> textList = Arrays.asList(
                new StyledText("Hello, World!", new Font("Arial", Font.PLAIN, 12)),
                new StyledText("Goodbye, World!", new Font("Arial", Font.BOLD, 12))
        );

        // Write the list of styled text to the editor
        editor.write(textList);

        String actualText = getTextFromEditor(editor);

        // Check that the text was written correctly
        assertEquals("Hello, World!Goodbye, World!", actualText);

        // Check that the fonts were written correctly
        assertEquals(new Font("Arial", Font.PLAIN, 12), editor.getRunArray().getFont(0));

    }

    @Test
    public void testEditorSizeCalculation() {
        List<StyledText> actualList = new ArrayList<>();
        List<StyledText> expectedList = new ArrayList<>();

        String text1 = """
                CS 635 Advanced Object-Oriented Design & Programming
                Fall Semester, 2018
                Doc 17 Mediator, Flyweight, Facade, Demeter, Active Object
                Nov 19, 2019""";
        String text2 = """
                Copyright ©, All rights reserved. 2019 SDSU & Roger Whitney,
                5500 Campanile Drive, San Diego, CA 92182-7700 USA.
                OpenContent (http://www.opencontent.org/opl.shtml) license
                defines the copyright on this document.""";

        actualList.add(new StyledText(text1, new Font("TIMES NEW ROMAN", Font.ITALIC, 10)));
        actualList.add(new StyledText(text2, new Font("ARIAL", Font.BOLD, 12)));

        FlyWeightTextEditor editorWithFlyweight = new FlyWeightTextEditor();
        editorWithFlyweight.write(actualList);

        double actualSize = new SizeofUtil() {
            @Override
            protected int create() {
                FlyWeightTextEditor flyWeightTextEditor = new FlyWeightTextEditor();
                flyWeightTextEditor.write(actualList);
                return 1;
            }
        }.averageBytes();

        String expectedText = "";
        int count = 0;
        for (int i = 0; i < 356; i++) {
            expectedText = expectedText.concat(Character.toString((char) count+50));
            if (count < 54) {
                count += 1;
            }
        }

        System.out.println(expectedText.length());

        expectedList.add(new StyledText(expectedText.substring(0, 145), new Font("TIMES NEW ROMAN", Font.ITALIC, 10)));
        expectedList.add(new StyledText(expectedText.substring(145, expectedText.length()), new Font("ARIAL", Font.BOLD, 12)));

        double expectedSize = new SizeofUtil() {
            @Override
            protected int create() {
                FlyWeightTextEditor flyWeightTextEditor = new FlyWeightTextEditor();
                flyWeightTextEditor.write(expectedList);
                return 1;
            }
        }.averageBytes();

        Assertions.assertEquals(expectedSize, actualSize);
    }

    private String getTextFromEditor(FlyWeightTextEditor editor ) {
        StringBuffer sb = new StringBuffer();
        for(CustomCharacter customCharacter: editor.getText()) {
            sb.append(customCharacter.getValue());
        }
        return  sb.toString();
    }
}

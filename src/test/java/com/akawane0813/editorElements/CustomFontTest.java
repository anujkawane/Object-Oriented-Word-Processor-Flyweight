package com.akawane0813.editorElements;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

public class CustomFontTest {
    @Test
    public void testValueOf() {
        // Create a font to use for testing
        Font font1 = new Font("Arial", Font.PLAIN, 12);

        // Check that valueOf returns the same instance for the same font
        CustomFont f1 = CustomFont.valueOf(font1);
        CustomFont f2 = CustomFont.valueOf(font1);
        assertSame(f1, f2);

        // Check that valueOf returns different instances for different fonts
        Font font2 = new Font("Arial", Font.BOLD, 12);
        CustomFont f3 = CustomFont.valueOf(font2);
        assertNotSame(f1, f3);
    }
}

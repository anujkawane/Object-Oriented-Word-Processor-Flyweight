package com.akawane0813.editorElements;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomCharacterTest {
    @Test
    public void testValueOf() {
        // Check that valueOf returns the same instance for the same character
        CustomCharacter c1 = CustomCharacter.valueOf('A');
        CustomCharacter c2 = CustomCharacter.valueOf('A');
        assertSame(c1, c2);

        // Check that new() always returns different instances
        CustomCharacter c3 = new CustomCharacter('A');
        assertNotSame(c1, c3);
    }

}
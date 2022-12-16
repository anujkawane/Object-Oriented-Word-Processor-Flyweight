package com.akawane0813.textEditor;

import com.akawane0813.editorElements.CustomCharacter;
import com.akawane0813.editorElements.CustomFont;

/**
 * A record representing a styled character.
 *
 * @param value the character value
 * @param font the font for the character
 */
public record StyledChar(CustomCharacter value, CustomFont font) {

}
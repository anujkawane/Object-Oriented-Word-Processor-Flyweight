package com.akawane0813.editorElements;

import java.util.HashMap;
import java.util.Map;

/**
 * Custom Character used to encapsulate character unicode and implement flyweight pattern.
 */
public class CustomCharacter {
    private char value;
    public char getValue() {
        return value;
    }

    // Factory method for the CustomCharacter
    private static class CharacterCache {
        private CharacterCache(){}
        static Map<Character, CustomCharacter> cache = new HashMap<>();

        public static Map<Character, CustomCharacter> getCache() {
            return cache;
        }
    }

    public CustomCharacter(char value) {
        this.value = value;
    }

    /**
     * Returns the CustomCharacter instance associated If the character is already cached,
     * a new instance is created and cached before being returned, otherwise.
     *
     * @param c the character to lookup
     * @return the CustomCharacter instance associated with the specified character
     */
    public static CustomCharacter valueOf(char c) {
        Map<Character, CustomCharacter> cache = CustomCharacter.CharacterCache.getCache();
         if(!cache.containsKey(c)) {
             cache.put(c, new CustomCharacter(c));
         }
        return cache.get(c);
    }
}
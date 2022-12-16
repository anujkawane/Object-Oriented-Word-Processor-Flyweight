package com.akawane0813.editorElements;

import java.awt.*;
import java.util.*;

public class CustomFont {

    private Font font;

    public Font getFont() {
        return font;
    }

    /**
     * Factory method for the CustomFont. The font cache that stores the shared font objects.
     */
    private static class FontCache {
        static Map<Font, CustomFont> cache = new HashMap<>();

        public static Map<Font, CustomFont> getCache() {
            return cache;
        }
    }

    public CustomFont(Font font) {
        this.font = font;
    }

    /**
     * Gets a `CustomFont` object for the specified font If font already exists, Otherwise,
     * a new `CustomFont` object is created and added to the cache.
     *
     * @param font The font to get a `CustomFont` object for.
     * @return A `CustomFont` object for the specified font.
     */
    public static CustomFont valueOf(Font font) {
        Map<Font, CustomFont> cache = CustomFont.FontCache.getCache();
        if(!cache.containsKey(font)) {
            cache.put(font, new CustomFont(font));
        }
        return cache.get(font);
    }
}
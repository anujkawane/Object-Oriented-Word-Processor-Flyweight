package com.akawane0813.textEditor;

import java.util.List;

public interface TextEditor {
    void write(StyledText styledText);

    void write(List<StyledText> text);
}
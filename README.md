# Flyweight Pattern Implementation

This is a Java implementation that demonstrates the Flyweight pattern using characters in a word processor as an example. The Flyweight pattern is used to conserve memory by sharing common data among multiple objects. In this case, we focus on optimizing the representation of characters and fonts in a document.

## Implementation Details

### Character Flyweight

Represents a character in the document and stores only the Unicode code point of the character. It acts as a flyweight object to conserve memory. To access a flyweight character object, use the `Factory` that provides a single point of access throughout the program.

### Font Flyweight

Manages the creation and sharing of font objects. It utilizes a flyweight approach to avoid redundant font objects. A font is defined by a triple consisting of the font name, point size, and style (bold, italic, underline, etc.).

### RunArray

The `RunArray` class is responsible for storing runs of characters in the document along with their associated font. It keeps track of runs using a sequence-based approach. Each run is defined by its start index, length, and corresponding font. The `RunArray` provides a convenient method to retrieve the font for a specific index in the document.

## Space Optimization

The primary objective of this implementation is to save memory space. To evaluate the effectiveness of the Flyweight pattern, we measure the space occupied by different objects. For example, on a 64-bit machine, an empty Java object takes up 16 bytes, while a font object occupies 72 bytes. To measure the size of Java objects, you can use the tool available at [http://java.dzone.com/articles/java-getting-size-object](http://java.dzone.com/articles/java-getting-size-object).


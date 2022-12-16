package com.akawane0813;

import com.akawane0813.textEditor.FlyWeightTextEditor;
import com.akawane0813.textEditor.RegularTextEditor;
import com.akawane0813.util.SizeofUtil;
import com.akawane0813.textEditor.StyledText;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the main application that runs the flyweight and regular
 * text editor examples and compares their memory usage.
 *
 * @author Anuj Kawane
 */
public class Application {
    public static void main(String[] args) {

        List<StyledText> list = new ArrayList<>();
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

        list.add(new StyledText(text1, new Font("TIMES NEW ROMAN",Font.ITALIC,10)));
        list.add(new StyledText(text2, new Font("ARIAL",Font.BOLD,12)));

        System.out.printf("The total size of Flyweight TextEditor TotalSize is %.1f bytes%n", new SizeofUtil() {
            @Override
            protected int create() {
                FlyWeightTextEditor flyWeightTextEditor = new FlyWeightTextEditor();
                flyWeightTextEditor.write(list);
                return 1;
            }
        }.averageBytes());

        System.out.printf("The total size of Normal TextEditor is %.1f bytes%n", new SizeofUtil() {
            @Override
            protected int create() {
                RegularTextEditor regularTextEditor = new RegularTextEditor();
                regularTextEditor.write(list);
                return 1;
            }
        }.averageBytes());
    }
}

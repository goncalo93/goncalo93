package org.academiadecodigo.hangmangame;

import java.io.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class AsciiReader {

    public static String readAscii(int startLine, int endLine) {

        String line;
        BufferedReader br = null;
        int currentLineNo = 1;
        String txtFile = "resources/hangman.txt";
        StringBuilder ascii = new StringBuilder();

        try {

            br = new BufferedReader(new FileReader(txtFile));

            while (currentLineNo <= endLine) {

                line = br.readLine();
                if (currentLineNo >= startLine) {
                    ascii.append(line).append("\n");
                }
                currentLineNo++;
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return ascii.toString();
    }
}

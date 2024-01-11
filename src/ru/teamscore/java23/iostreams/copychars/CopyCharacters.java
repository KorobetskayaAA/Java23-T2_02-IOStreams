package ru.teamscore.java23.iostreams.copychars;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class CopyCharacters {
    static String homeDir = System.getProperty("user.home");
    static final String inFileName = "foo.txt";
    static final String outFileName = "foo-copy.txt";

    public static void main(String[] args) throws IOException {
        try (
                FileReader inputStream = new FileReader(Path.of(homeDir, inFileName).toFile());
                FileWriter outputStream = new FileWriter(Path.of(homeDir, outFileName).toFile())) {
            int c;
            while ((c = inputStream.read()) != -1) {
                System.out.write(c);
                System.err.println(c);
                outputStream.write(c);
            }
        }
    }
}
package ru.teamscore.java23.iostreams.copybytes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

public class CopyBytes {
    static String homeDir = System.getProperty("user.home");
    static final String inFileName = "foo.txt";
    static final String outFileName = "foo-copy.txt";

    public static void main(String[] args) throws IOException {
        FileInputStream in = null;
        FileOutputStream out = null;

        try {
            in = new FileInputStream(Path.of(homeDir, inFileName).toFile());
            out = new FileOutputStream(Path.of(homeDir, outFileName).toFile());
            int c;

            while ((c = in.read()) != -1) {
                System.out.write(c);
                System.err.println(c);
                out.write(c);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}
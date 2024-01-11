package ru.teamscore.java23.iostreams.copylines;

import java.io.*;
import java.nio.file.Path;

public class CopyLines {
    static String homeDir = System.getProperty("user.home");
    static final String inFileName = "foo.txt";
    static final String outFileName = "foo-copy.txt";

    public static void main(String[] args) throws IOException {
        BufferedReader inputStream = new BufferedReader(
                new FileReader(Path.of(homeDir, inFileName).toFile()));
        PrintWriter outputStream = new PrintWriter(
                new FileWriter(Path.of(homeDir, outFileName).toFile()));
        try (inputStream; outputStream) {
            String l;
            while ((l = inputStream.readLine()) != null) {
                System.out.println(l);
                outputStream.println(l);
            }
        } catch (FileNotFoundException x) {
            System.err.println("Не удалось открыть файл");
        } catch (IOException x) {
            System.err.println("Ошибка чтения-записи");
        }
    }
}

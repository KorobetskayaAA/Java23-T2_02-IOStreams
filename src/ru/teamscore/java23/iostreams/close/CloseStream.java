package ru.teamscore.java23.iostreams.close;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Scanner;

public class CloseStream {
    static final String text = "Hello, IO Streams!\n" +
            "Привет потокам ввода-вывода!";
    static final String outputFileName = "out.txt";
    static final Path filePath = Path.of("", outputFileName);

    public static void main(String[] args) {
        try {
            writeFile();
        } catch (IOException x) {
            System.err.println("Произошла ошибка при закрытии файла");
        }
        readFile();
    }

    private static void readFile() {
        try (InputStream srcStream = new FileInputStream(filePath.toString());
             Scanner sc = new Scanner(srcStream);
        ) {
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
        } catch (FileNotFoundException x) {
            System.err.printf("Не удалось открыть для чтения файл '%s': %s",
                    filePath.toString(),
                    x.getLocalizedMessage());
        } catch (IOException x) {
            System.err.println("Ошибка при записи в файл " + x.getLocalizedMessage());
        }
    }

    private static void writeFile() throws IOException {
        OutputStream destStream = null;
        try {
            destStream = new FileOutputStream(filePath.toString());
            byte[] buffer = text.getBytes(StandardCharsets.UTF_8);
            destStream.write(buffer);
        } catch (FileNotFoundException x) {
            System.err.printf("Не удалось открыть для записи файл '%s': %s",
                    filePath.toString(),
                    x.getLocalizedMessage());
        } catch (IOException x) {
            System.err.println("Ошибка при записи в файл " + x.getLocalizedMessage());
        } finally {
            if (destStream != null) {
                destStream.close(); // throws IOException
            }
        }
    }
}

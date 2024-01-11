package ru.teamscore.java23.iostreams.datastream;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        var products = Arrays.asList(new Product[] {
                new Product("Молоко", 1, 99.99),
                new Product("Носки", 8, 125.0),
                new Product("Скрепки", 99, 1.05)
        });
        Path path = Path.of("productsV.bin");
        try (OutputStream stream = new BufferedOutputStream(
                Files.newOutputStream(path, StandardOpenOption.CREATE))
        ) {
            ProductReaderWriter.WriteByField(products, stream);
        }
        System.out.println("Чтение по полям:");
        try (InputStream stream = new BufferedInputStream(
                Files.newInputStream(path))
        ) {
            for (Product p : ProductReaderWriter.ReadByField(stream)) {
                System.out.println(p);
            }
        }

        try (OutputStream stream = new BufferedOutputStream(
                new FileOutputStream("productsO.bin"))
        ) {
            ProductReaderWriter.WriteByObject(products, stream);
        }
        System.out.println("Чтение по объектам:");
        try (InputStream stream = new BufferedInputStream(
                new FileInputStream("productsO.bin"))
        ) {
            for (Product p : ProductReaderWriter.ReadByObject(stream)) {
                System.out.println(p);
            }
        } catch (ClassNotFoundException e) {
            System.err.println("В файле сохранен неверный класс");
        }
    }
}

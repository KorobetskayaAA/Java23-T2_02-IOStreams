package ru.teamscore.java23.iostreams.datastream;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductReaderWriter {
    public static void WriteByField(Iterable<Product> products,
                                    OutputStream stream) {
        try (DataOutputStream out = new DataOutputStream(stream)) {
            for (var product : products) {
                out.writeDouble(product.getPrice());
                out.writeInt(product.getQuantity());
                out.writeUTF(product.getTitle());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Iterable<Product> ReadByField(InputStream stream) {
        try (DataInputStream in = new DataInputStream(stream)) {
            List<Product> products = new ArrayList<Product>();
            try {
                while (true) {
                    var product = new Product();
                    product.setPrice(in.readDouble());
                    product.setQuantity(in.readInt());
                    product.setTitle(in.readUTF());
                    products.add(product);
                }
            } catch (EOFException x) {
                return products;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void WriteByObject(Iterable<Product> products,
                                     OutputStream stream) {
        try (ObjectOutputStream out = new ObjectOutputStream(stream)) {
            for (var product : products) {
                out.writeObject(product);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Iterable<Product> ReadByObject(InputStream stream)
            throws ClassNotFoundException {
        try (var in = new ObjectInputStream(stream)) {
            List<Product> products = new ArrayList<Product>();
            try {
                while (true) {
                var product = in.readObject();
                products.add((Product)product);
                }
            } catch (EOFException x) {
                return products;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

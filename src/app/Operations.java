package app;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
@SuppressWarnings("all")
public class Operations {

    public static <T> ArrayList<T> readAllData(String fileName) {
        ArrayList<T> list = new ArrayList<T>(0);
        ObjectInputStream inputStream = null;
        try {
            inputStream = new ObjectInputStream(new FileInputStream(fileName));
            boolean EOF = false;
            while (!EOF) {
                try {
                    T myObj = (T) inputStream.readObject();
                    list.add(myObj);
                } catch (ClassNotFoundException e) {
                    // System.out.println("Class not found");
                } catch (EOFException end) {
                    EOF = true;
                }
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                System.out.println("IO Exception while closing file");
            }
        }
        return list;
    }

    public static <T> void writeData(T s, String fileName) {
        ObjectOutputStream outputStream = null;
        try {
            ArrayList<T> list = readAllData(fileName);
            list.add(s);
            outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
            for (T student : list)
                outputStream.writeObject(student);
        } catch (IOException e) {
            System.out.println("IO Exception while opening file");
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                System.out.println("IO Exception while closing file");
            }
        }
    }
}

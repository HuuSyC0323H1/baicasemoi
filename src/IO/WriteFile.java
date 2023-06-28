package IO;

import Login_logOut.User;
import MyProduct.Product;

import java.io.*;
import java.util.ArrayList;

public class WriteFile implements Serializable {
    public static void writeDataToFile(String path, ArrayList<Product> listProduct) {
        try
                (FileOutputStream fos = new FileOutputStream(path);
                 ObjectOutputStream oos = new ObjectOutputStream(fos)){
            oos.writeObject(listProduct);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeUserToFile(String path, ArrayList<User> list) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)){
            oos.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeEmpToFile(String path, ArrayList<User> list) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)){
            oos.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

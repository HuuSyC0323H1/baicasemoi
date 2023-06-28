package IO;
import Login_logOut.User;
import MyProduct.Product;

import javax.imageio.IIOException;
import java.io.*;
import java.util.ArrayList;

public class ReadFile implements Serializable {
    public static ArrayList<Product> readDataFromFile(String path){
        ArrayList<Product> listProduct = new ArrayList<>();
        try ( FileInputStream fis = new FileInputStream(path);
              ObjectInputStream ois = new ObjectInputStream(fis)){
            listProduct = (ArrayList<Product>) ois.readObject();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return listProduct ;
    }
    public static ArrayList<User> readUserFormFile(String path, ArrayList<User> userList){
        ArrayList<User> list = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream("textuser.txt");
            if (fis.available() != 0 ){
                ObjectInputStream ois = new ObjectInputStream(fis);
                list = (ArrayList<User>) ois.readObject();
            }else {
                return list;
            }
        }catch (IIOException | ClassNotFoundException | FileNotFoundException exception){
            exception.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
//    public static ArrayList<User> readEmpFormFile(String path, ArrayList<User> empList){
//        ArrayList<User> list = new ArrayList<>();
//        try {
//            FileInputStream fis = new FileInputStream("textEmplyoee.txt");
//            if (fis.available() != 0 ){
//                ObjectInputStream ois = new ObjectInputStream(fis);
//                list = (ArrayList<User>) ois.readObject();
//            }else {
//                return list;
//            }
//        }catch (IIOException | ClassNotFoundException | FileNotFoundException exception){
//            exception.printStackTrace();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return list;
//    }
}

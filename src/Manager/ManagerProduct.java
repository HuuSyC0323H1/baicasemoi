package Manager;

import IO.ReadFile;
import IO.WriteFile;
import MyProduct.Phone;
import MyProduct.Product;
import MyProduct.Sim;
import java.util.*;


public class ManagerProduct {
    Scanner scanner = new Scanner(System.in);
    ArrayList<Product> ListProduct = ReadFile.readDataFromFile("text.txt");

    public void Show() {
        System.out.println("1. Hiển thị tất cả sản phẩm");
        System.out.println("2. Hiển thị các sản phẩm điện thoại ");
        System.out.println("3. Hiển thị các sản phẩm sim ");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1 -> {
                System.out.printf("%s%15s%18s%16s%15s%15s%15s%15s", "ID", "Name", "Price", "Quantity", "Describe", "Color","Number","Provider").println();
                int dem = 0;
                for (Product emp : ListProduct) {
                    if (dem == 5) {
                        scanner.nextLine();
                        dem = 0;
                        System.out.println("Nhấn Enter để tiếp tục...");
                    }
                    System.out.print(emp);
                    System.out.println();
                    dem++;
                }
            }
            case 2 -> {
                System.out.printf("%-3s%-15.2s%-18s%-16s%-15s%-15s", "ID", "Name", "Price", "Quantity", "Describe", "Color").println();
                for (Product emp : ListProduct) {
                    if (emp instanceof Phone phone) {
                        System.out.println(phone);
                    } else {
                        System.out.println("Cty chưa có sản phẩm này ");
                    }
                    System.out.println();
                }
            }
            case 3 -> {
                System.out.printf("%-3s%-15s%-18s%-16s%-15s", "ID", "Name", "Phone", "Birthday", "Hệ số").println();
                for (Product emp : ListProduct) {
                    if (emp instanceof Sim sim) {
                        System.out.println(sim);
                    } else {
                        System.out.println("Cty chưa có sản phẩm này ");
                    }
                    System.out.println();
                }
            }
            default -> System.out.println("Lựa chọn không hợp lệ.");
        }
    }

    public void Add() {
        System.out.println("Nhập loại sản phẩm: ");
        String type = scanner.nextLine();
        System.out.println("Tên sản phẩm:");
        String name = scanner.nextLine();
        System.out.println("Nhập giá sản phẩm: ");
        float price = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                price = Float.parseFloat(scanner.nextLine());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Giá sản phẩm không hợ lệ. Vui lòng nhập lại: ");
            }
        }
        System.out.println("Nhập số lượng sản phẩm: ");
        int quantity = 0;
        validInput = false;
        while (!validInput) {
            try {
                quantity = Integer.parseInt(scanner.nextLine());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Số lượng sản phẩm không hợp lệ. Vui lòng nhập lại: ");
            }
        }
        switch (type) {
            case "dienthoai" -> {
                System.out.println("Mô tả sản phẩm: ");
                String description = scanner.nextLine();
                System.out.println("Màu của sản phẩm: ");
                String color = scanner.nextLine();
                Phone phone = new Phone(name, price, quantity, description, color);
                ListProduct.add(phone);
                WriteFile.writeDataToFile("text.txt",ListProduct);
                System.out.println("Sản phẩm đã được thêm vào danh sách.");
            }
            case "sim" -> {
                System.out.print("Nhập số thẻ Sim: ");
                int number = 0;
                validInput = false;
                while (!validInput) {
                    try {
                        number = Integer.parseInt(scanner.nextLine());
                        validInput = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Số thẻ Sim không hợp lệ. Vui lòng nhập lại: ");
                    }
                }
                System.out.println("Nhập nhà cung cấp dịch vụ của Sim: ");
                String provider = scanner.nextLine();
                Sim sim = new Sim(name, price, quantity, number, provider);
                ListProduct.add(sim);
                WriteFile.writeDataToFile("text.txt",ListProduct);
                System.out.println("Sản phẩm đã được thêm vào danh sách.");
            }
            default -> System.out.println("Loại sản phẩm không hợp lệ.");
        }
    }
    public void remove(){
        try {
            System.out.println("Nhap ten san pham muon xoa: ");
            String name = scanner.nextLine();
            boolean removed = false;
            for (Product product : ListProduct) {
                if (product.getName().equals(name)) {
                    ListProduct.remove(product);
                    removed = true;
                    break;
                }
            }
            if (removed) {
                System.out.println("San pham " + name + " da bi xoa.");
                WriteFile.writeDataToFile("text.txt", ListProduct);
            } else {
                System.out.println("Khong tim thay san pham " + name + " trong danh sach.");
            }
        } catch (Exception e) {
            System.out.println("Loi xay ra khi xoa san pham: " + e.getMessage());
        }
    }
    public void sortbypricetang(){
        Collections.sort(ListProduct);
    }
    public void sortbypricegiam(){
        ListProduct.sort(Comparator.comparing(Product::getPrice).reversed());
    }
    public void sortbynametang(){
        ListProduct.sort(Comparator.comparing(Product::getName));
    }
    public void sortbynamegiam(){
        ListProduct.sort(Comparator.comparing(Product::getName).reversed());
    }
    public void find() {
        System.out.println("Nhập ten: ");
        String name = scanner.next();
        for (Product nhanvien : ListProduct) {
            if (nhanvien.getName().equals(name)) {
                System.out.println("Thông tin nhân viên tìm được: ");
                System.out.println(nhanvien);
            }
        }
    }
    public void update() {
        System.out.println("Nhập tên sản phẩm cần sửa: ");
        String id = scanner.nextLine();
        boolean found = false;
        for (Product product : ListProduct) {
            if (product.getId().equals(id)) {
                found = true;
                System.out.println("Nhập thông tin mới cho sản phẩm:");
                System.out.println("Tên sản phẩm: ");
                String name = null;
                while (name == null || name.trim().isEmpty()) {
                    System.out.println("Tên sản phẩm không được để trống. Vui lòng nhập lại: ");
                    name = scanner.nextLine();
                }
                System.out.println("Nhập giá của sản phẩm: ");
                float price = 0;
                boolean validInput = false;
                while (!validInput) {
                    try {
                        price = Float.parseFloat(scanner.nextLine());
                        if (price < 0) {
                            System.out.println("Giá sản phẩm không được âm. Vui lòngNhập lại: ");
                        } else {
                            validInput = true;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Giá sản phẩm không hợp lệ. Vui lòng nhập lại: ");
                    }
                }
                System.out.println("Nhập số lượng sản phẩm: ");
                int quantity = 0;
                validInput = false;
                while (!validInput) {
                    try {
                        quantity = Integer.parseInt(scanner.nextLine());
                        if (quantity < 0) {
                            System.out.println("Số lượng sản phẩm không được âm. Vui lòng nhập lại: ");
                        } else {
                            validInput = true;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Số lượng sản phẩm không hợp lệ. Vui lòng nhập lại: ");
                    }
                }
                if (product instanceof Phone) {
                    Phone phone = (Phone) product;
                    System.out.println("Mô tả sản phẩm: ");
                    String description = scanner.nextLine();
                    System.out.println("Màu của sản phẩm: ");
                    String color = scanner.nextLine();
                    phone.setName(name);
                    phone.setPrice(price);
                    phone.setQuantity(quantity);
                    phone.setDescription(description);
                    phone.setColor(color);
                    WriteFile.writeDataToFile("text.txt", ListProduct);
                } else if (product instanceof Sim) {
                    Sim sim = (Sim) product;
                    System.out.println("Nhập số thẻ Sim: ");
                    int number = 0;
                    validInput = false;
                    while (!validInput) {
                        try {
                            number = Integer.parseInt(scanner.nextLine());
                            if (number < 0) {
                                System.out.println("Số thẻ Sim không được âm. Vui lòng nhập lại: ");
                            } else {
                                validInput = true;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Số thẻ Sim không hợp lệ. Vui lòng nhập lại: ");
                        }
                    }
                    System.out.println("Nhập nhà cung cấp dịch vụ của Sim: ");
                    String provider = scanner.nextLine();
                    sim.setName(name);
                    sim.setPrice(price);
                    sim.setQuantity(quantity);
                    sim.setNumber(number);
                    sim.setProvider(provider);
                    WriteFile.writeDataToFile("text.txt", ListProduct);
                }
                System.out.println("Thông tin sản phẩm đã được cập nhật.");
                break;
            }
        }
        if (!found) {
            System.out.println("Không tìm được sản phẩm với mã sản phẩm trên.");
        }
    }
}

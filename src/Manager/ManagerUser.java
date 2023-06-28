package Manager;

import IO.ReadFile;
import IO.WriteFile;
import Login_logOut.User;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class ManagerUser implements Serializable {
    Scanner scanner = new Scanner(System.in);
    private ArrayList<User> userList;
    private User currentUser;

    public ManagerUser() {
        userList = ReadFile.readUserFormFile("textuser.txt", userList);
        if (userList == null) {
            userList = new ArrayList<>();
        }
    }

    public User addUser() {
        System.out.println("Ten dang nhap: ");
        String username = scanner.nextLine();
        System.out.println("Mat Khau: ");
        String password = scanner.nextLine();
        String role = scanner.nextLine();
        User user = new User(username, password,role);
        userList.add(user);
        WriteFile.writeUserToFile("textuser.txt", userList);
        System.out.println("User added successfully");
        return user;
    }

    public void removeUser() {
        System.out.println("Ten dang nhap: ");
        String username = scanner.nextLine();
        User user = findUser(username);
        if (user != null) {
            userList.remove(user);
            ReadFile.readUserFormFile("textuser.txt", userList);
        } else {
            System.out.println("User not found");
        }
    }

    public void UpdateUser() {
        System.out.println("Nhap ten nhan vien: ");
        String username = scanner.nextLine();
        User user = findUser(username);
        if (user != null) {
            User newUser = new User();
            userList.set(userList.indexOf(user), newUser);
            WriteFile.writeUserToFile("textuser.txt", userList);
            System.out.println("User updated successfully");
        } else {
            System.out.println("User not found");
        }
    }

    public User findUser(String username) {
        for (User user : userList) {
            if (user.getUsername() != null && user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public void displayUser() {
        if (userList.isEmpty()) {
            System.out.println("There are no users to display");
        } else {
            System.out.println("List of users with complete information");
            for (User user : userList) {
                if (user.getUsername() != null && user.getPassword() != null) {
                    System.out.println("username: " + user.getUsername() + ", " + "password: " + user.getPassword());
                }
            }
        }
    }
    public void displayEmp(){
        if (userList.isEmpty()) {
            System.out.println("There are no users to display");
        } else {
            System.out.println("List of emplyoee with complete information");
            for (User user : userList) {
                if (user.getName() != null && user.getEmail() != null && user.getPhone() != null) {
                    System.out.println(user);
                }
            }
        }
    }

    public boolean login(String username, String password) {
        User user = findUser(username);
        if (user != null && user.checkPassword(password)) {
            currentUser = user;
            System.out.println("login successfully");
            return true;
        } else {
            System.out.println("Thông tin tài khoản không chính xác");
            return false;
        }
    }

    public boolean logout() {
        currentUser = null;
        System.out.println("Logout successfully");
        System.exit(0);
        return false;
    }

    public boolean hasPermission() {
        if (currentUser == null) {
            System.out.println("You are not Logged in");
            return false;
        }
        String userRole = (String) currentUser.getRole();
        if (userRole.equals("CEO")) {
            return true;
        } else {
            System.out.println("You do not have permission to perform this action!");
            return false;
        }
    }

    public void addEmp() {
        System.out.println("Ten: ");
        String name = scanner.nextLine();
        System.out.println("Email: ");
        String email = scanner.nextLine();
        System.out.println("Phone : ");
        String phone = scanner.nextLine();
        System.out.println("Vi tri:");
        String role = scanner.nextLine();
        System.out.println("Luong co ban: ");
        double basicSalary = scanner.nextLong();
        User user =new User(name,email,phone,role,basicSalary);
        userList.add(user);
        WriteFile.writeUserToFile("textuser.txt", userList);
        System.out.println("Emp added successfully");
    }

    public void removeEmp() {
        System.out.println("Ten : ");
        String name = scanner.nextLine();
        User user = findUser(name);
        if (user != null) {
            userList.remove(user);
            ReadFile.readUserFormFile("textuser.txt", userList);
        } else {
            System.out.println("User not found");
        }
    }

    public User findEmp(String role) {
        for (User user : userList) {
            if (user.getRole().equals(role)) {
                System.out.println(user);
            }
        }
        return null;
    }

    public void UpdateEmp() {
        System.out.println("Nhap ten nhan vien: ");
        String username = scanner.nextLine();
        User user = findUser(username);
        if (user != null) {
            User newUser = new User();
            userList.set(userList.indexOf(user), newUser);
            WriteFile.writeUserToFile("textEmplyoee.txt", userList);
            System.out.println("User updated successfully");
        } else {
            System.out.println("User not found");
        }
    }
    public double calculateSalaryByName(String name) {
        String pattern = "##,###,###,###.##";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        double salary;
        for (User user : userList) {
            if (user.getName().equals(name)) {
                salary = user.calculateSalary();
                System.out.println("Salary for " + user.getName() + " is " + decimalFormat.format(salary));
                return salary;
            }
        }
        System.out.println("Usernot found");
        return 0;
    }
    public void calculateSalaryByPosition(String role) {
        String pattern = "##,###,###,###.##";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        double totalSalary = 0;
        int count = 0;
        for (User user : userList) {
            if (user.getRole().equals(role)) {
                double salary = user.calculateSalary();
                System.out.println("Salary for " + user.getName() + " is " + decimalFormat.format(salary));
                totalSalary += salary;
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No users found with position " + role);
        } else {
            System.out.println("Total salary for " + count + " users with position " + role + " is " + decimalFormat.format(totalSalary));
        }
    }

    public void calculateTotalSalary() {
        String pattern = "##,###,###,###.##";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        double totalSalary = 0;
        for (User user : userList) {
            double salary = user.calculateSalary();
            totalSalary += salary;
        }
        System.out.println("Total salary for all users is " + decimalFormat.format(totalSalary));
    }
    public void addUser(User user) {
        userList.add(user);
        WriteFile.writeUserToFile("textuser.txt", userList);
    }
    public ArrayList<User> getUserList() {
        ArrayList<User> completeUserList = new ArrayList<>();
        if (userList.isEmpty()) {
            System.out.println("There are no users to display");
        } else {
            for (User user : userList) {
                if (user.getName() != null && user.getEmail() != null && user.getPhone() != null) {
                    completeUserList.add(user);
                }
            }
        }
        return completeUserList;
    }
}


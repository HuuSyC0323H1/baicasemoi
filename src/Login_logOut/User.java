package Login_logOut;

import java.io.Serial;
import java.io.Serializable;

public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 965558757579529874L;
    private String username;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String role;
    private double basicSalary;
    public User(String username, String password,String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }


    public User() {
    }

    public User(String name, String email, String phone, String role, double basicSalary) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.basicSalary = basicSalary;
    }

    public User(String name, String email, String phone, double basicSalary) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.basicSalary = basicSalary;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public Object getRole() {
        return role;
    }
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
    public double calculateSalary() {
        double bonus = switch (role) {
            case "Receptionist" -> 500000;
            case "Accountant" -> 1000000;
            case "CEO" -> 5000000;
            default -> 0;
        };
        return basicSalary + bonus;
    }
    @Override
    public String toString() {
        return "Name: " + name + ", Email: " + email +", Phone: " + phone + ", Role: " + role + ", Basic Salary: " + basicSalary;
    }

}

package Customer;

import Login_logOut.User;

import java.io.Serializable;

public class CEO extends User implements Serializable {

    public CEO(String name, String email, String phone, String role, double basicSalary) {
        super(name, email, phone, role, basicSalary);
    }

    public CEO() {
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public void setUsername(String username) {
        super.setUsername(username);
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public String getPhone() {
        return super.getPhone();
    }

    @Override
    public void setPhone(String phone) {
        super.setPhone(phone);
    }

    @Override
    public void setRole(String role) {
        super.setRole(role);
    }

    @Override
    public Object getRole() {
        return super.getRole();
    }



}

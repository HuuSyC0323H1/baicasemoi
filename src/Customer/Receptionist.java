package Customer;

import Login_logOut.User;

import java.io.Serializable;

public class Receptionist extends User implements Serializable {
    public Receptionist(String name, String email, String phone, String role, double basicSalary) {
        super(name, email, phone, role, basicSalary);
    }

    public Receptionist() {
    }
}

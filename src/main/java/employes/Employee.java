package employes;

import enumeration.Role;
import users.User;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "employees")
public class Employee extends User {

    @Column(nullable = false)
    private String position;

    @Column(nullable = false)
    private String department;

    @Column(nullable = false)
    private Double salary;

    public Employee() {
    }

    public Employee(String name, String email, String position, String department, Double salary) {
        super(name, email);
        this.position = position;
        this.department = department;
        this.salary = salary;
    }

    public Employee(String name, String email, String password, String phone, Date birthdate, String cin, Role role, String position, String department, Double salary) {
        super(name, email, password, phone, birthdate, cin, role);
        this.position = position;
        this.department = department;
        this.salary = salary;
    }


    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}

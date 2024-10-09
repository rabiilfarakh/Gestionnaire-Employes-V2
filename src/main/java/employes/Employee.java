package employes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "employes")
public class Employee {

    @Column(nullable = false)
    private String position;

    @Column(nullable = false)
    private String department;

    @Column(nullable = false)
    private Double salary;

    public Employee(String position, String department, Double salary) {
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

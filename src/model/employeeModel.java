
package model;

public class employeeModel {
    
    private int id;
    private String employeeName;
    private String empType;
    private String phone;
    private String Address;
    private double Salary;
    private double remainedSalary;

    public double getRemainedSalary() {
        return remainedSalary;
    }

    public void setRemainedSalary(double remainedSalary) {
        this.remainedSalary = remainedSalary;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmpType() {
        return empType;
    }

    public void setEmpType(String empType) {
        this.empType = empType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public double getSalary() {
        return Salary;
    }

    public void setSalary(double Salary) {
        this.Salary = Salary;
    }
    
}

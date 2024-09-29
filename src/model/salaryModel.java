
package model;


public class salaryModel {
    
    private int id;
    private String employeeName;
    private String jopTitle;
    private String Note;
    private String date;
    private double Taked;
    private double currentSalary;

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

    public String getJopTitle() {
        return jopTitle;
    }

    public void setJopTitle(String jopTitle) {
        this.jopTitle = jopTitle;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTaked() {
        return Taked;
    }

    public void setTaked(double Taked) {
        this.Taked = Taked;
    }

    public double getCurrentSalary() {
        return currentSalary;
    }

    public void setCurrentSalary(double currentSalary) {
        this.currentSalary = currentSalary;
    }
    
}

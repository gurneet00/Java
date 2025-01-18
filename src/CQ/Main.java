package CQ;

import java.util.Objects;
import java.util.Scanner;
public class Main {
    // Static variable to track the total number of employees
    private static int totalEmployees = 0;
    // Instance variables
    private String name;
    private int employeeID;
    private String employeeType;
    // Constructor to initialize employee details
    public Main(String name, int employeeID, String employeeType) {
        this.name = name;
        this.employeeID = employeeID;
        this.employeeType = employeeType;
        totalEmployees++;  // Increment total employees count when an employee is created
    }

    // Method to calculate salary for permanent employees
    public double calculateSalary(double baseSalary, double bonus) {
        return baseSalary + bonus;
    }

    // Overloaded method to calculate salary for contract employees
    public double calculateSalary(int hoursWorked, double hourlyRate) {
        return hoursWorked * hourlyRate;
    }
    // Method to display employee information
    public void displayEmployeeInfo(double salary) {
        System.out.println(this.employeeID);
        System.out.println(this.name);
        System.out.println(this.employeeType);
        System.out.println(salary);
    }

    // Static method to display total employees
    public static void displayTotalEmployees() {
        System.out.println("Total Employees: " + totalEmployees);
    }
    public  String getEmployeeType() {return this.employeeType;}
    public static void main(String[] args) {
// Create a Scanner object to read input from the console Scanner
        Scanner scanner = new Scanner(System.in); // Read employee name
        String name =scanner.nextLine(); // Read employee ID
        int employeeID = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        String employeeType =scanner.nextLine();
        System.out.println(employeeType);
        // Creating employees
        Main emp = new Main(name , employeeID, employeeType);
// Displaying total employees
        Main.displayTotalEmployees();
        if(Objects.equals(emp.getEmployeeType(), "Permanent")){
// Calculating and displaying salary for permanent employee
            double salary = emp.calculateSalary(50000, 5000);//baseSalary + bonus
            emp.displayEmployeeInfo(salary);
        }
        if(Objects.equals(emp.getEmployeeType(), "Contract")){
            double salary = emp.calculateSalary(160, 50);  // hoursWorked * hourlyRate
// Calculating and displaying salary for contract employee
            emp.displayEmployeeInfo(salary);
        }
    }
}
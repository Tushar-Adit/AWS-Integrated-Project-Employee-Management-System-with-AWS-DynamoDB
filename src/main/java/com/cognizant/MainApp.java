import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        EmployeeManager manager = new EmployeeManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Employee Management (AWS DynamoDB) ===");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employee");
            System.out.println("3. Delete Employee");
            System.out.println("4. List All Employees");
            System.out.println("5. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("ID: "); int id = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Name: "); String name = sc.nextLine();
                    System.out.print("Age: "); int age = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Position: "); String position = sc.nextLine();
                    System.out.print("Salary: "); double salary = sc.nextDouble();
                    manager.addEmployee(new Employee(id, name, age, position, salary));
                    break;
                case 2:
                    System.out.print("Enter ID to View: ");
                    manager.viewEmployee(sc.nextInt());
                    break;
                case 3:
                    System.out.print("Enter ID to Delete: ");
                    manager.deleteEmployee(sc.nextInt());
                    break;
                case 4:
                    manager.listEmployees();
                    break;
                case 5:
                    System.out.println("👋 Exiting.");
                    return;
                default:
                    System.out.println("❗ Invalid choice.");
            }
        }
    }
}

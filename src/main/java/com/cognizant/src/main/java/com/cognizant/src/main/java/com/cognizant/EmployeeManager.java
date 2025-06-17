public class Employee {
    private int id;
    private String name;
    private int age;
    private String position;
    private double salary;

    public Employee(int id, String name, int age, String position, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.position = position;
        this.salary = salary;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getPosition() { return position; }
    public double getSalary() { return salary; }
}

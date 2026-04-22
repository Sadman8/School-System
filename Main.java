import java.util.ArrayList;
import java.util.Scanner;

abstract class Person {
    private String name;
    private String id;

    public Person(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() { return name; }
    public String getId() { return id; }

    public abstract String getRole();

    @Override
    public String toString() {
        return getRole() + " | ID: " + id + " | Name: " + name;
    }
}

class Student extends Person {
    private String enrolledCourse;
    private boolean isPresent = false;

    public Student(String name, String id, String enrolledCourse) {
        super(name, id);
        this.enrolledCourse = enrolledCourse;
    }

    @Override
    public String getRole() { return "Student"; }
    public boolean isPresent() { return isPresent; }
    public void setPresent(boolean present) { this.isPresent = present; }

    @Override
    public String toString() {
        return super.toString() + " | Course: " + enrolledCourse + " | Status: " + (isPresent ? "PRESENT" : "ABSENT");
    }
}

class Teacher extends Person {
    private String subject;

    public Teacher(String name, String id, String subject) {
        super(name, id);
        this.subject = subject;
    }

    @Override
    public String getRole() { return "Teacher"; }

    @Override
    public String toString() {
        return super.toString() + " | Subject: " + subject;
    }
}

class School {
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Teacher> teachers = new ArrayList<>();

    public void addStudent(Student s) { students.add(s); }
    public void addTeacher(Teacher t) { teachers.add(t); }

    public void markAttendance(String id, boolean present) {
        for (Student s : students) {
            if (s.getId().equals(id)) {
                s.setPresent(present);
                System.out.println("Attendance updated for " + s.getName());
                return;
            }
        }
        System.out.println("ID not found.");
    }

    public void showStudents() {
        System.out.println("\n--- Student List ---");
        if (students.isEmpty()) System.out.println("No students found.");
        for (Student s : students) System.out.println(s);
    }

    public void showTeachers() {
        System.out.println("\n--- Teacher List ---");
        for (Teacher t : teachers) System.out.println(t);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        School school = new School();

        // Pre-loaded Data
        school.addTeacher(new Teacher("Arif Faysal", "T001", "Algorithms"));
        school.addStudent(new Student("Shadman", "242-35-001", "SE-221"));

        while (true) {
            System.out.println("\n--- SCHOOL MANAGEMENT SYSTEM ---");
            System.out.println("1. View All Students");
            System.out.println("2. View All Teachers");
            System.out.println("3. Add New Student");
            System.out.println("4. Mark Attendance (P/X)");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    school.showStudents();
                    break;
                case 2:
                    school.showTeachers();
                    break;
                case 3:
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter ID (242-35-XXX): ");
                    String id = sc.nextLine();
                    System.out.print("Enter Course: ");
                    String course = sc.nextLine();
                    school.addStudent(new Student(name, id, course));
                    System.out.println("Student added!");
                    break;
                case 4:
                    System.out.print("Enter Student ID: ");
                    String attId = sc.nextLine();
                    System.out.print("Enter Status (P for Present / X for Absent): ");
                    String input = sc.nextLine().trim().toLowerCase();
                    
                    if (input.equals("p")) {
                        school.markAttendance(attId, true);
                    } else if (input.equals("x")) {
                        school.markAttendance(attId, false);
                    } else {
                        System.out.println("Invalid input! Use 'P' or 'X'.");
                    }
                    break;
                case 5:
                    System.out.println("Exiting program...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
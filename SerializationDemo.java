import java.io.*;

public class SerializationDemo {

    static class Student implements Serializable {

        private static final long serialVersionUID = 1L;

        int id;
        String name;
        transient String password;

        Student(int id, String name, String password) {
            this.id = id;
            this.name = name;
            this.password = password;
        }

        @Override
        public String toString() {
            return "ID: " + id
                    + ", Name: " + name
                    + ", Password: " + password;
        }
    }

    public static void main(String[] args) {

        Student[] students = {
            new Student(1, "Purv", "abc123"),
            new Student(2, "Rahul", "xyz456"),
            new Student(3, "Amit", "pass789")
        };

        // Save objects to a file
        try (ObjectOutputStream out =
                     new ObjectOutputStream(
                         new FileOutputStream("students.dat"))) {

            out.writeObject(students);

            System.out.println("Students saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
            return;
        }

        // Load objects from the file
        try (ObjectInputStream in =
                     new ObjectInputStream(
                         new FileInputStream("students.dat"))) {

            Student[] loadedStudents =
                    (Student[]) in.readObject();

            System.out.println("\nLoaded Student Records:");

            for (Student student : loadedStudents) {
                System.out.println(student);
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading students: " + e.getMessage());
        }
    }
}
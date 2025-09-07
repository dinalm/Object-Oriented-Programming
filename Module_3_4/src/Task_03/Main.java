package Task_03;

import java.io.*;

// Student class implementing Serializable interface
class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private int age;

    // Default constructor
    public Student() {}

    // Parameterized constructor
    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "', age=" + age + "}";
    }
}

// Course class implementing Serializable interface
class Course implements Serializable {
    private static final long serialVersionUID = 1L;

    private String courseCode;
    private String courseName;
    private String instructor;

    // Default constructor
    public Course() {}

    // Parameterized constructor
    public Course(String courseCode, String courseName, String instructor) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.instructor = instructor;
    }

    // Getters
    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getInstructor() {
        return instructor;
    }

    // Setters
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return "Course{courseCode='" + courseCode + "', courseName='" + courseName + "', instructor='" + instructor + "'}";
    }
}

// Enrollment class implementing Serializable interface
class Enrollment implements Serializable {
    private static final long serialVersionUID = 1L;

    private Student student;
    private Course course;
    private String enrollmentDate;

    // Default constructor
    public Enrollment() {}

    // Parameterized constructor
    public Enrollment(Student student, Course course, String enrollmentDate) {
        this.student = student;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
    }

    // Getters
    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public String getEnrollmentDate() {
        return enrollmentDate;
    }

    // Setters
    public void setStudent(Student student) {
        this.student = student;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setEnrollmentDate(String enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    @Override
    public String toString() {
        return "Enrollment{\n" +
                "  student=" + student + ",\n" +
                "  course=" + course + ",\n" +
                "  enrollmentDate='" + enrollmentDate + "'\n" +
                "}";
    }
}

// Main class
public class Main {
    private static final String FILENAME = "enrollments.ser";

    public static void main(String[] args) {
        System.out.println("Course Enrollment Serialization\n");

        // Create instances of Student, Course, and Enrollment
        Student student = new Student(101, "Alice Johnson", 20);
        Course course = new Course("CS101", "Introduction to Computer Science", "Dr. Smith");
        Enrollment enrollment = new Enrollment(student, course, "2024-09-01");

        System.out.println("Original Enrollment Object:");
        System.out.println(enrollment);
        System.out.println();

        // Serialize the enrollment object
        serializeEnrollment(enrollment);

        // Deserialize the enrollment object
        Enrollment deserializedEnrollment = deserializeEnrollment();

        if (deserializedEnrollment != null) {
            System.out.println("Deserialized Enrollment Object:");
            System.out.println(deserializedEnrollment);
            System.out.println();

            // Verify that the data is correctly preserved
            System.out.println("Verification");
            System.out.println("Student ID: " + deserializedEnrollment.getStudent().getId());
            System.out.println("Student Name: " + deserializedEnrollment.getStudent().getName());
            System.out.println("Student Age: " + deserializedEnrollment.getStudent().getAge());
            System.out.println("Course Code: " + deserializedEnrollment.getCourse().getCourseCode());
            System.out.println("Course Name: " + deserializedEnrollment.getCourse().getCourseName());
            System.out.println("Instructor: " + deserializedEnrollment.getCourse().getInstructor());
            System.out.println("Enrollment Date: " + deserializedEnrollment.getEnrollmentDate());
        }
    }

    // Serialize the enrollment object
    private static void serializeEnrollment(Enrollment enrollment) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            oos.writeObject(enrollment);
            System.out.println("Enrollment object serialized successfully to " + FILENAME);
            System.out.println();
        } catch (IOException e) {
            System.err.println("Error during serialization: " + e.getMessage());
        }
    }

    // Deserialize the enrollment object
    private static Enrollment deserializeEnrollment() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            Enrollment enrollment = (Enrollment) ois.readObject();
            System.out.println("Enrollment object deserialized successfully from " + FILENAME);
            System.out.println();
            return enrollment;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error during deserialization: " + e.getMessage());
            return null;
        }
    }
}

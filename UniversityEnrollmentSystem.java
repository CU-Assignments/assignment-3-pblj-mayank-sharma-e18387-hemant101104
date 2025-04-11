import java.util.ArrayList;
import java.util.List;

class CourseFullException extends Exception {
    public CourseFullException(String message) {
        super(message);
    }
}

class PrerequisiteNotMetException extends Exception {
    public PrerequisiteNotMetException(String message) {
        super(message);
    }
}

class Course {
    private String name;
    private String prerequisite;
    private int capacity;
    private List<String> enrolledStudents;

    public Course(String name, String prerequisite, int capacity) {
        this.name = name;
        this.prerequisite = prerequisite;
        this.capacity = capacity;
        this.enrolledStudents = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getPrerequisite() {
        return prerequisite;
    }

    public boolean isFull() {
        return enrolledStudents.size() >= capacity;
    }

    public void enrollStudent(String studentName) throws CourseFullException {
        if (isFull()) {
            throw new CourseFullException("Error: CourseFullException - " + name + " is full.");
        }
        enrolledStudents.add(studentName);
    }
}

class Student {
    private String name;
    private List<String> completedCourses;

    public Student(String name) {
        this.name = name;
        this.completedCourses = new ArrayList<>();
    }

    public void completeCourse(String courseName) {
        completedCourses.add(courseName);
    }

    public void enrollInCourse(Course course) throws PrerequisiteNotMetException, CourseFullException {
        if (course.getPrerequisite() != null && !completedCourses.contains(course.getPrerequisite())) {
            throw new PrerequisiteNotMetException("Error: PrerequisiteNotMetException - Complete " + course.getPrerequisite() + " before enrolling in " + course.getName() + ".");
        }
        course.enrollStudent(name);
        System.out.println("Enrolled in " + course.getName() + " successfully!");
    }
}

public class UniversityEnrollmentSystem {
    public static void main(String[] args) {
        // Create courses
        Course coreJava = new Course("Core Java", null, 2);
        Course advancedJava = new Course("Advanced Java", "Core Java", 2);

        // Create students
        Student alice = new Student("Alice");
        Student bob = new Student("Bob");

        try {
            // Attempt to enroll in courses
            alice.enrollInCourse(advancedJava);
        } catch (PrerequisiteNotMetException | CourseFullException e) {
            System.out.println(e.getMessage());
        }

        // Complete prerequisite course
        alice.completeCourse("Core Java");

        try {
            // Attempt to enroll in courses
            alice.enrollInCourse(advancedJava);
        } catch (PrerequisiteNotMetException | CourseFullException e) {
            System.out.println(e.getMessage());
        }
    }
}

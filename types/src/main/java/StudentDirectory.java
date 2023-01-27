// StudentDirectory.java

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

public class StudentDirectory {
    static class Student {
        // Properties
        private String name;
        private String cohortName;
        public Student(String name, String cohortName) {
            this.name = name;
            this.cohortName = cohortName;
        }
        public String getName() {
            return this.name;
        }
        public String getCohortName() {
            return this.cohortName;
        }
    }
    public static void main(String[] args) {
        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(new Student("Alex", "January 2020"));
        studentList.add(new Student("Maria", "January 2020"));
        studentList.add(new Student("Lisa", "January 2020"));

        Enumeration<Student> enumeration = Collections.enumeration(studentList);

        System.out.println("List of students:");
        while (enumeration.hasMoreElements()) {
            Student currentStudent = enumeration.nextElement();
            String line = String.format(" * %s - %s", currentStudent.getName(), currentStudent.getCohortName());
            System.out.println(line);
        }
    }
}

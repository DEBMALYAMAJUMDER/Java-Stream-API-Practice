import entity.MinMax;
import entity.Student;
import entity.StudentReport;
import entity.StudentStats;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StudentStreamMain {
    public static void main(String[] args) {
        List<Student> students = List.of(
                new Student(1, "Ankit", "CSE", 21, 8.7),
                new Student(2, "Rahul", "ECE", 22, 7.5),
                new Student(3, "Priya", "CSE", 20, 9.2),
                new Student(4, "Neha", "IT", 23, 8.0),
                new Student(5, "Aman", "ECE", 21, 8.8),
                new Student(6, "Sneha", "IT", 22, 9.5),
                new Student(7, "Rohit", "CSE", 23, 6.9),
                new Student(8, "Pooja", "ECE", 20, 9.1)
        );
        /**
         * true → students having CGPA >= 8.5
         * false → remaining students
         */
        System.out.println(students.stream()
                .collect(Collectors.partitioningBy(student -> student.cgpa() >= 8.5)));
        /**
         * true → number of students with CGPA >= 8.5
         * false → number of remaining students
         */
        System.out.println(students.stream()
                .collect(Collectors.partitioningBy(student -> student.cgpa() >= 8.5, Collectors.counting())));
        /**
         * Count
         * Sum
         * Average
         * Min
         * Max
         */
        System.out.println(students.stream()
                .collect(Collectors.summarizingDouble(Student::cgpa)));
        /**
         * true → statistics of students with CGPA >= 8.5
         * false → statistics of the remaining students.
         */
        System.out.println(students.stream()
                .collect(Collectors.partitioningBy(student -> student.cgpa() >= 8.5, Collectors.summarizingDouble(Student::cgpa))));
        /**
         * Using the same students list, return a single String containing the student names:
         * Sorted alphabetically
         * Separated by " | "
         */
        System.out.println(students.stream()
                .map(Student::name)
                .sorted()
                .collect(Collectors.joining(" | ")));
        /**
         * Find Max Min in one traversal
         */
        System.out.println(students.stream()
                .collect(Collectors.teeing(
                        Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(Student::cgpa)), Optional::get),
                        Collectors.collectingAndThen(Collectors.minBy(Comparator.comparing(Student::cgpa)), Optional::get),
                        MinMax::new)));
        /**
         * I want both the average CGPA and the total number of students in one stream traversal
         */
        System.out.println(students.stream()
                .collect(Collectors.teeing(
                        Collectors.counting(),
                        Collectors.averagingDouble(Student::cgpa),
                        StudentStats::new)));
        /**
         * Nested Teeing
         */
        StudentStats stats = new StudentStats(8, 8.46);

        System.out.println(stats.totalStudents());
        System.out.println(stats.averageCgpa());
        System.out.println(new StudentReport(new Student(1,"DEB","MATH",29,8.73), stats.averageCgpa(), stats.totalStudents()));
//        System.out.println(
//                students.stream()
//                        .collect(Collectors.teeing(
//                                Collectors.collectingAndThen(
//                                        Collectors.maxBy(Comparator.comparingDouble(Student::cgpa)),
//                                        Optional::get
//                                ),
//                                Collectors.teeing(
//                                        Collectors.counting(),
//                                        Collectors.averagingDouble(Student::cgpa),
//                                        StudentStats::new
//                                ),
//                                (topper, stats) ->
//                                        new StudentReport(
//                                                topper,
//                                                stats.averageCgpa(),
//                                                stats.totalStudents()
//                                        )
//                        )));
    }
}

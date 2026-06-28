import entity.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class EmployeeStreamMain {
    public static void main(String[] args) {
        List<Employee> employees = List.of(new Employee(1, "Alice", "IT", 90000), new Employee(2, "Bob", "HR", 60000), new Employee(3, "Charlie", "IT", 120000), new Employee(4, "David", "Finance", 80000), new Employee(5, "Eva", "IT", 95000), new Employee(6, "Frank", "HR", 70000));
        /**
         * Filter employees whose salary is greater than 80,000.
         * Sort them by salary in descending order.
         * Convert the result into a List<String> containing only employee names.
         */
        System.out.println(employees.stream().filter(employee -> employee.salary() > 80000).sorted(Comparator.comparing(Employee::salary).reversed()).map(Employee::name).toList());
        /**
         * This one is very common in interviews.
         *
         * Using the same entity.Employee list, return a Map<String, List<String>> where:
         *
         * Key = department
         * Value = list of employee names in that department
         */
        System.out.println(employees.stream().collect(Collectors.groupingBy(Employee::department, Collectors.mapping(Employee::name, Collectors.toList()))));
        /**
         * Key = department
         * Value = highest-paid employee in that department.
         * Map<String, Optional<entity.Employee>>
         */
        System.out.println(employees.stream().collect(Collectors.groupingBy(Employee::department, Collectors.maxBy(Comparator.comparing(Employee::salary)))));
        /**
         * Key = department
         * Value = highest-paid employee in that department.
         * Map<String, entity.Employee>
         */
        System.out.println(employees.stream().collect(Collectors.groupingBy(Employee::department,
                Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(Employee::salary)), Optional::get))));
        /**
         * Key = department
         * Value = average salary of that department.
         * Map<String, Double>
         */
        System.out.println(employees.stream().collect(Collectors.groupingBy(Employee::department, Collectors.averagingDouble(Employee::salary))));
        /**
         * Using the same employee list, return the second highest-paid employee.
         */
        System.out.println(employees.stream().sorted(Comparator.comparing(Employee::salary).reversed()).skip(1).findFirst());
        /**
         * Return the names of employees grouped by department, sorted by salary in descending order within each department.
         */
        System.out.println(employees.stream()
                .sorted(Comparator.comparing(Employee::salary).reversed())
                .collect(Collectors.groupingBy(Employee::department, Collectors.mapping(Employee::name, Collectors.toList()))));
        /**
         * Create a Map<String, Employee> where:
         * Key = department
         * Value = highest-paid employee in that department.
         * Do not use groupingBy().
         */
        System.out.println(employees.stream()
                .collect(Collectors.toMap(Employee::department,
                        Function.identity(),
                        (existingVal, newVal) -> existingVal.salary() > newVal.salary() ? existingVal : newVal)));
        /**
         * Return the top 2 highest-paid employees from each department.
         * List<Employee> employees
         * Map<String, List<Employee>>
         */
        System.out.println(employees.stream()
                .collect(Collectors.groupingBy(Employee::department,
                        Collectors.collectingAndThen(Collectors.toList(),
                                list -> list.stream()
                                        .sorted(Comparator.comparing(Employee::salary)
                                                .reversed()).limit(2).toList()))));
    }
}
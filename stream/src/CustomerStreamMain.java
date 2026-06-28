import entity.Customer;
import entity.Order;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerStreamMain {
    public static void main(String[] args) {
        List<Customer> customers = List.of(
                new Customer(
                        "Alice",
                        List.of(
                                new Order(1, "Alice", "Laptop", "Electronics", 80000),
                                new Order(3, "Alice", "Book", "Books", 500)
                        )
                ),
                new Customer(
                        "Bob",
                        List.of(
                                new Order(2, "Bob", "Mouse", "Electronics", 1200),
                                new Order(6, "Bob", "Keyboard", "Electronics", 2500)
                        )
                ),
                new Customer(
                        "Charlie",
                        List.of(
                                new Order(4, "Charlie", "Phone", "Electronics", 50000)
                        )
                ),
                new Customer(
                        "David",
                        List.of(
                                new Order(5, "David", "Novel", "Books", 700)
                        )
                )
        );
        /**
         * Return a single list of product names purchased by all customers.
         */
        System.out.println(customers.stream()
                .flatMap(customer -> customer.orders().stream().map(Order::product)).toList());
        /**
         * Key = customer name
         * Value = total amount spent by that customer.
         */
        System.out.println(customers.stream()
                .collect(Collectors.toMap(Customer::name,
                        customer -> customer.orders().stream().mapToDouble(Order::amount).sum(),
                        Double::sum)));
        /**
         * Key = product category
         * Value = number of orders in that category.
         */
        System.out.println(customers.stream()
                .flatMap(customer -> customer.orders().stream())
                .collect(Collectors.groupingBy(Order::category, Collectors.counting())));
    }
}

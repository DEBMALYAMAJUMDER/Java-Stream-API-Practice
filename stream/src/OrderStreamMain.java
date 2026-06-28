import entity.Order;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OrderStreamMain {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order(1, "Alice", "Laptop", "Electronics", 80000),
                new Order(2, "Bob", "Mouse", "Electronics", 1200),
                new Order(3, "Alice", "Book", "Books", 500),
                new Order(4, "Charlie", "Phone", "Electronics", 50000),
                new Order(5, "David", "Novel", "Books", 700),
                new Order(6, "Bob", "Keyboard", "Electronics", 2500)
        );
        /**
         * Return a List<String> containing the customer names who placed orders worth more than 2000, sorted by amount descending.
         */
        System.out.println(orders.stream()
                .filter(order -> order.amount() > 2000)
                .sorted(Comparator.comparing(Order::amount).reversed())
                .map(Order::customer)
                .toList());
        /**
         * Key = customer
         * Value = total amount spent by that customer
         */
        System.out.println(orders.stream()
                .collect(Collectors.groupingBy(Order::customer,
                        Collectors.summingDouble(Order::amount))));
        /**
         * Key = customer
         * Value = list of product names purchased by that customer.
         */
        System.out.println(orders.stream()
                .collect(Collectors.groupingBy(Order::customer, Collectors.mapping(Order::product, Collectors.toList()))));
        /**
         * containing all unique customer names sorted alphabetically.
         */
        System.out.println(orders.stream()
                .map(Order::customer)
                .distinct()
                .sorted()
                .toList());
    }
}

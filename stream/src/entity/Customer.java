package entity;

import java.util.List;

public record Customer(String name,
                       List<Order> orders) {
}

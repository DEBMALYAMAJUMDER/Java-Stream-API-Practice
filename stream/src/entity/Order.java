package entity;

public record Order(int id,
                    String customer,
                    String product,
                    String category,
                    double amount) {
}

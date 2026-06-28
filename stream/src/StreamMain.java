import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamMain {
    public static void main(String[] args) {
        List<String> words = List.of(
                "apple",
                "banana",
                "apple",
                "orange",
                "banana",
                "apple"
        );
        /**
         * containing the frequency of each word.
         * Map<String, Long>
         */
        System.out.println(words.stream()
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting())));
    }
}

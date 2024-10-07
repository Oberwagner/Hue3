package net.htlgrieskirchen.pos3.streams;
import java.util.function.Predicate;

public class PredicateExample {
    public static void main(String[] args) {

        final Predicate<Integer> isEven = number -> number % 2 == 0;
        final Predicate<Integer> isPositive = number -> number != null && number > 0;
        final Predicate<Integer> isZero = number -> number == 0;
        final Predicate<Object> isNull = object -> object == null;


        final Predicate<String> isShortWord = word -> word.length() < 4;

        System.out.println("Test: Is 4 even? " + isEven.test(4)); // true
        System.out.println("Test: Is -1 positive? " + isPositive.test(-1)); // false
        System.out.println("Test: Is 0 zero? " + isZero.test(0)); // true
        System.out.println("Test: Is null object null? " + isNull.test(null)); // true
        System.out.println("Test: Is non-null object null? " + isNull.test(new Object())); // false
        System.out.println("Test: Is 'hi' a short word? " + isShortWord.test("hi")); // true
        System.out.println("Test: Is 'hello' a short word? " + isShortWord.test("hello")); // false


        Predicate<Integer> isPositiveAndEven = isPositive.and(isEven);
        System.out.println("\nTest: Is 4 positive and even? " + isPositiveAndEven.test(4)); // true
        System.out.println("Test: Is 3 positive and even? " + isPositiveAndEven.test(3)); // false

        Predicate<Integer> isPositiveAndOdd = isPositive.and(isEven.negate());
        System.out.println("\nTest: Is 5 positive and odd? " + isPositiveAndOdd.test(5)); // true
        System.out.println("Test: Is 6 positive and odd? " + isPositiveAndOdd.test(6)); // false
    }
}

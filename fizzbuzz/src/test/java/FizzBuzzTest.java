import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FizzBuzzTest {
    @Test
    void testGenerateListsNumbersUpToOne() {
        FizzBuzz subject = new FizzBuzz();
        String result = subject.generate(1);
        assertEquals("1", result);
    }

    @Test
    void testGenerateListsNumbersUpToTwo() {
        FizzBuzz subject = new FizzBuzz();
        String result = subject.generate(2);
        assertEquals("1, 2", result);
    }

    @Test
    void testGenerateListsNumbersUpToThree() {
        FizzBuzz subject = new FizzBuzz();
        String result = subject.generate(3);
        assertEquals("1, 2, Fizz", result);
    }

    @Test
    void testGenerateListsNumbersUpToFive() {
        FizzBuzz subject = new FizzBuzz();
        String result = subject.generate(5);
        assertEquals("1, 2, Fizz, 4, Buzz", result);
    }

    @Test
    void testGenerateListsNumbersUpToSix() {
        FizzBuzz subject = new FizzBuzz();
        String result = subject.generate(6);
        assertEquals("1, 2, Fizz, 4, Buzz, Fizz", result);
    }

    @Test
    void testGenerateListsNumbersUpToFifteen() {
        FizzBuzz subject = new FizzBuzz();
        String result = subject.generate(15);
        assertEquals("1, 2, Fizz, 4, Buzz, Fizz, 7, 8, Fizz, Buzz, 11, Fizz, 13, 14, FizzBuzz", result);
    }

    @Test
    void testGenerateListsNumbersUpToThirty() {
        FizzBuzz subject = new FizzBuzz();
        String result = subject.generate(30);
        assertEquals("1, 2, Fizz, 4, Buzz, Fizz, 7, 8, Fizz, Buzz, 11, Fizz, 13, 14, FizzBuzz, 16, 17, Fizz, 19, Buzz, Fizz, 22, 23, Fizz, Buzz, 26, Fizz, 28, 29, FizzBuzz", result);
    }
}
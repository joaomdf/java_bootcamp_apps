public class App {
    public static class Fizzbuzz {
        public String play(Integer number) {
            if (this.isDivisibleBy(15, number)) {
                return "FizzBuzz";
            } else if (this.isDivisibleBy(5, number)) {
                return "Buzz";
            } else if (this.isDivisibleBy(3, number)) {
                return "Fizz";
            } else {
                return number.toString();
            }
        }
        public boolean isDivisibleBy(Integer divisor, Integer number) {
            return number % divisor == 0;
        }
    }
    public static void main(String[] args) {
        Fizzbuzz fizzbuzz = new Fizzbuzz();

        for (int i = 1 ; i < 100 ; i ++) {
            System.out.println(fizzbuzz.play(i));
        }
    }
}
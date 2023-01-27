public class FizzBuzz {
    public String generate(Integer number) {
        StringBuilder result = new StringBuilder("1");
        String added;
        for (int i = 2; i <= number; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                added = "FizzBuzz";
            }
            else if (i % 3 == 0) {
                added = "Fizz";
            }
            else if (i % 5 == 0) {
                added = "Buzz";
            }
            else {
                added = String.valueOf(i);
            }
            result.append(", ").append(added);
        }
        return result.toString();
    }
}

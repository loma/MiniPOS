public class FizzBuzz {

    String get(int i) {
        if (i == 0) return "0";
        if (i % 15 == 0) return "FizzBuzz";
        if (i % 5 == 0) return "Buzz";
        if (i % 3 == 0) return "Fizz";
        return i+"";
    }
    
}

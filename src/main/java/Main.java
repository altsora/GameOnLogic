import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//TODO:
public class Main {
    private static final boolean DEBUG_MODE = false;
//    private static final boolean DEBUG_MODE = true;

    private static final int NUMBER_SIZE = 4;
    private static final String format = "|%s -> %d:%d|";
    private static final String EXIT = "q";

    private static final List<String> STORAGE = new ArrayList<>();
    private static final List<String> ORIGINAL_NUMBER = numberGeneration();

    public static void main(String[] args) {
        if (DEBUG_MODE) System.out.println("DEBUG MODE\nOriginal number: " + getOriginalNumberString());
        System.out.println("Start!\nPress 'q' to exit.");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("\nInput:  ");
            String inputNumber = scanner.nextLine();
            if (inputNumber.equals(EXIT)) {
                System.out.println("Do not worry!");
                break;
            }
            boolean invalidInput = checkInputNumber(inputNumber);
            if (invalidInput) continue;
            int secondNumber = compute(inputNumber);
            showCurrentResult();
            if (secondNumber == 4) {
                System.out.println("Congratulations!");
                break;
            }
        }
        System.out.printf("Correct answer: %s\nNumber of attempts: %d\n\nGame over!\n", getOriginalNumberString(), STORAGE.size());
    }

    private static boolean checkInputNumber(String inputNumber) {
        if (inputNumber.isBlank()) {
            System.out.println("EMPTY INPUT!");
            return true;
        }
        if (inputNumber.length() != 4) {
            System.out.println("WRONG INPUT! The length of the number is not equal to 4.");
            return true;
        }
        if (!inputNumber.matches("\\d+")) {
            System.out.println("WRONG INPUT! '" + inputNumber + "' should only contain numbers .");
            return true;
        }
        List<String> numberList = inputNumber.chars().mapToObj(i -> (char) i).map(String::valueOf).collect(Collectors.toList());
        for (String number : numberList) {
            int frequency = Collections.frequency(numberList, number);
            if (frequency > 1) {
                System.out.println("WRONG INPUT! Number '" + number + "' entered " + frequency + " times! ");
                return true;
            }
        }
        return false;
    }

    private static int compute(String inputNumber) {
        int firstNumber = 0, secondNumber = 0;
        for (int i = 0; i < inputNumber.length(); i++) {
            String number = String.valueOf(inputNumber.charAt(i));
            if (ORIGINAL_NUMBER.contains(number)) firstNumber++;
            if (ORIGINAL_NUMBER.get(i).equals(number)) secondNumber++;
        }
        STORAGE.add(String.format(format, inputNumber, firstNumber, secondNumber));
        return secondNumber;
    }

    private static void showCurrentResult() {
        System.out.println("+" + "-".repeat(11) + "+");
        STORAGE.forEach(System.out::println);
        System.out.println("+" + "-".repeat(11) + "+");
    }

    private static List<String> numberGeneration() {
        List<Integer> numbers = IntStream.range(0, 10).boxed().collect(Collectors.toList());
        Collections.shuffle(numbers);
        return numbers.stream().limit(NUMBER_SIZE).map(String::valueOf).collect(Collectors.toList());
    }

    private static String getOriginalNumberString() {
        return String.join("", Main.ORIGINAL_NUMBER);
    }
}

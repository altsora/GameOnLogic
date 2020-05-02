import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<String> initialNumber = numberGeneration();
    private static int count;

    public static void main(String[] args) {
        System.out.println("Start!\n");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Input:  ");
            String inputNumber = scanner.nextLine();
            if (inputNumber.length() != initialNumber.size()) {
                System.out.println("INVALID ENTRY! The length of the number is not equal to 4.");
                continue;
            }

            count++;
            int firstNumber = 0, secondNumber = 0;
            for (int i = 0; i < inputNumber.length(); i++) {
                String digit = String.valueOf(inputNumber.charAt(i));
                if (initialNumber.contains(digit)) {
                    firstNumber++;
                }

                if (initialNumber.get(i).equals(digit)) {
                    secondNumber++;
                }
            }
            System.out.printf("Output: %d:%d\n\n", firstNumber, secondNumber);
            if (secondNumber == 4) {
                break;
            }
        }
        System.out.printf("Congratulations!\n" +
                "Correct answer: %d\n" +
                "Count: %d\n\n" +
                "Game over!\n", getInitialNumber(), count);
    }

    private static ArrayList<String> numberGeneration() {
        ArrayList<String> tmp = new ArrayList<>(4);
        for (int i = 0; i < 10; i++) {
            tmp.add(String.valueOf(i));
        }
        Collections.shuffle(tmp);
        ArrayList<String> number = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            number.add(tmp.get(i));
        }
        return number;
    }

    private static int getInitialNumber() {
        StringBuilder sb = new StringBuilder();
        for (String s : initialNumber) {
            sb.append(s);
        }
        return Integer.parseInt(sb.toString());
    }
}

import java.util.ArrayList;
import java.util.List;

public class MysteriousNumberAnalyzer {
    private static boolean isSpecial(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    private static List<Integer> findSpecialUpTo(int limit) {
        List<Integer> specialNumbers = new ArrayList<>();
        boolean[] notSpecial = new boolean[limit + 1];
        for (int i = 2; i <= Math.sqrt(limit); i++) {
            if (!notSpecial[i]) {
                for (int j = i * i; j <= limit; j += i) {
                    notSpecial[j] = true;
                }
            }
        }
        for (int i = 2; i <= limit; i++) {
            if (!notSpecial[i]) specialNumbers.add(i);
        }
        return specialNumbers;
    }

    private static int findNextSpecial(int n) {
        while (!isSpecial(++n));
        return n;
    }

    private static boolean areTwinSpecials(int a, int b) {
        return isSpecial(a) && isSpecial(b) && Math.abs(a - b) == 2;
    }

    public static void main(String[] args) {
        // Test 1: Check if a number is special
        int testNumber = 17;
        System.out.println("Test 1: Is " + testNumber + " special? " + isSpecial(testNumber));

        // Test 2: Find special numbers up to a limit
        int limit = 30;
        List<Integer> specialList = findSpecialUpTo(limit);
        System.out.println("Test 2: Special numbers up to " + limit + ": " + specialList);

        // Test 3: Find the next special number after a given number
        int start = 18;
        int nextSpecial = findNextSpecial(start);
        System.out.println("Test 3: Next special number after " + start + " is " + nextSpecial);

        // Test 4: Check for twin specials
        int a = 11, b = 13;
        System.out.println("Test 4: Are " + a + " and " + b + " twin specials? " + areTwinSpecials(a, b));

        // Test 5: Performance test for finding special numbers
        long startTime = System.currentTimeMillis();
        findSpecialUpTo(1000000);
        long endTime = System.currentTimeMillis();
        System.out.println("Test 5: Time taken to find special numbers up to 1,000,000: " + (endTime - startTime) + " ms");
    }
}
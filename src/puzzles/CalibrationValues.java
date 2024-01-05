package puzzles;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalibrationValues implements Puzzle {

    private List<String> calibrationValues;
    private final Map<String, Integer> numberMap = new HashMap<>();

    @Override
    public int partOne() {
        int sum = 0;
        for (String calibrationValue : calibrationValues) {
            String[] digits = calibrationValue.split("\\D");
            String number = "";
            for (String s : digits) {
                number += s;
            }
            if (number.length() == 1) {
                sum += Integer.parseInt(number + number);
            }
            if (number.length() > 1) {
                sum += Integer.parseInt(number.charAt(0) + number.substring(number.length() - 1));
            }
        }
        return sum;
    }

    @Override
    public int partTwo() {
        mapAddNumbers(true);
        int sum = 0;
        for (String calibrationValue : calibrationValues) {
            int firstIndex = Integer.MAX_VALUE;
            int lastIndex = -1;
            int firstDigit = 0;
            int lastDigit = 0;
            for (String key : numberMap.keySet()) {
                int newIndex = calibrationValue.indexOf(key);
                if (newIndex < firstIndex && newIndex > -1) {
                    firstIndex = newIndex;
                    firstDigit = numberMap.get(key);
                }
                int newLastIndex = calibrationValue.lastIndexOf(key);
                if (lastIndex < newLastIndex) {
                    lastIndex = newLastIndex;
                    lastDigit = numberMap.get(key);
                }
            }
            sum += firstDigit * 10 + lastDigit;
        }
        return sum;
    }

    private void mapAddNumbers(boolean numbers) {
        if (numbers) {
            numberMap.put("one", 1);
            numberMap.put("two", 2);
            numberMap.put("three", 3);
            numberMap.put("four", 4);
            numberMap.put("five", 5);
            numberMap.put("six", 6);
            numberMap.put("seven", 7);
            numberMap.put("eight", 8);
            numberMap.put("nine", 9);
        } else {
            numberMap.clear();
            for (int i = 1; i < 10; i++) {
                numberMap.put(String.valueOf(i), i);
            }
        }
    }

    @Override
    public void setInput(List<String> input) {
        calibrationValues = input;
    }

}

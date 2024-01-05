package puzzles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Scratchcards implements Puzzle {

    private List<String> scratchcards;

    @Override
    public int partOne() {
        int sum = 0;
        for (String scratchcard : scratchcards) {
            int cardValue = 0;
            scratchcard = scratchcard.split(":")[1].strip();
            List<String> winningNumbers = Arrays.stream(scratchcard.split("\\|\s*")[0].strip().split("\\D+")).toList();
            List<String> numbers = Arrays.stream(scratchcard.split("\\|\s*")[1].strip().split("\\D+")).toList();
            for (String number : numbers) {
                if (winningNumbers.contains(number)) {
                    if (cardValue == 0) {
                        cardValue = 1;
                    } else {
                        cardValue *= 2;
                    }
                }
            }
            sum += cardValue;
        }
        return sum;
    }

    @Override
    public int partTwo() {
        List<Integer> list = new ArrayList<>();
        for (int j = 0; j < scratchcards.size(); j++) {
            list.add(1);
        }
        int sum = 0;
        for (int i = 0; i < scratchcards.size(); i++) {
            String scratchcard = scratchcards.get(i);
            int cardMatches = 0;
            scratchcard = scratchcard.split(":")[1].strip();
            List<String> winningNumbers = Arrays.stream(scratchcard.split("\\|\s*")[0].strip().split("\\D+")).toList();
            List<String> numbers = Arrays.stream(scratchcard.split("\\|\s*")[1].strip().split("\\D+")).toList();
            for (String number : numbers) {
                if (winningNumbers.contains(number)) {
                    cardMatches++;
                    list.set(i + cardMatches, list.get(i + cardMatches) + list.get(i));
                }
            }
        }
        for (int n : list) {
            sum += n;
        }
        return sum;
    }

    @Override
    public void setInput(List<String> input) {
        scratchcards = input;
    }

}

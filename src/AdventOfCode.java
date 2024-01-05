import puzzles.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AdventOfCode {

    private final MyFrame frame;
    private int day;
    private boolean partOne = true;
    private Puzzle puzzle;
    private String fileName;

    public AdventOfCode(MyFrame jFrame) {
        frame = jFrame;
    }

    public void setDay(int day) {
        this.day = day;
        setPuzzle();
        frame.addPuzzlePanel();
    }

    public void setPuzzle() {
        switch (day) {
            case 1 -> {
                fileName = "calibrationDocument.txt";
                puzzle = new CalibrationValues();
            }
            case 2 -> {
                puzzle = new CubeGame();
                fileName = "games.txt";
            }
            case 4 -> {
                puzzle = new Scratchcards();
                fileName = "scratchcards.txt";
            }
        }
        List<String> input = Arrays.stream(getPuzzleInput().split("\n")).toList();
        puzzle.setInput(input);
    }

    public String getPuzzleText() {
        String text = "";
        try (BufferedReader br = new BufferedReader(new FileReader("day" + day + ".txt"))) {
            String s;
            if (partOne) {
                while ((s = br.readLine()) != null) {
                    if (s.equalsIgnoreCase("--- Part Two ---")) {
                        break;
                    }
                    text += s + "\n";
                }
            } else {
                while ((s = br.readLine()) != null) {
                    text += s + "\n";
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Something wrong with the file");
        }
        text = text.strip();
        return text;
    }

    public String getPuzzleInput() {
        String input = "";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String s;
            while ((s = br.readLine()) != null) {
                input += s + "\n";
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Something wrong with the file");
        }
        input = input.strip();
        return input;
    }

    public int checkAnswer(int userAnswer) {
        if (partOne) {
            return Integer.compare(userAnswer, puzzle.partOne());
        } else {
            return Integer.compare(userAnswer, puzzle.partTwo());
        }
    }

    public void setPartOne(boolean partOne) {
        this.partOne = partOne;
    }

    public boolean isPartOne() {
        return partOne;
    }
}

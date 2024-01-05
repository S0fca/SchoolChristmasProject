import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyFrame {

    private final AdventOfCode adventOfCode = new AdventOfCode(this);
    private final JFrame frame;

    public MyFrame() {
        frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        addDaysPanel();
    }

    private void addDaysPanel() {
        JPanel chooseDayPanel = new JPanel();
        chooseDayPanel.setLayout(new BorderLayout());

        JTextArea daysText = new JTextArea("Day 1: Trebuchet?!\nDay 2: Cube Conundrum\nDay 4: Scratchcards");
        daysText.setEditable(false);
        daysText.setBorder(BorderFactory.createLineBorder(Color.white, 10));

        JTextArea userInputText = new JTextArea(" Pick day:  ");
        userInputText.setEditable(false);

        JTextField input = new JTextField();
        input.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                if (!Character.isDigit(ch) && ch != KeyEvent.VK_BACK_SPACE || input.getText().length() >= 1) {
                    e.consume();
                }
                if (ch == KeyEvent.VK_ENTER && (input.getText().length() > 0) && Integer.parseInt(input.getText()) < 5 && Integer.parseInt(input.getText()) > 0 && !"3".equals(input.getText())) {
                    adventOfCode.setDay(Integer.parseInt(input.getText()));
                    frame.remove(chooseDayPanel);
                }
            }
        });

        chooseDayPanel.add(daysText, BorderLayout.PAGE_START);
        chooseDayPanel.add(userInputText, BorderLayout.LINE_START);
        chooseDayPanel.add(input, BorderLayout.CENTER);
        chooseDayPanel.setBorder(BorderFactory.createLineBorder(Color.white));

        frame.add(chooseDayPanel);
        frame.pack();
    }

    public void addPuzzlePanel() {
        JPanel puzzlePanel = new JPanel();
        puzzlePanel.setBackground(Color.white);
        puzzlePanel.setLayout(new BorderLayout());

        JTextArea puzzleText = new JTextArea();
        puzzleText.setEditable(false);
        puzzleText.setText(adventOfCode.getPuzzleText());
        puzzleText.setBorder(BorderFactory.createLineBorder(Color.white, 10));

        JLabel userInputLabel = new JLabel("Answer:");
        userInputLabel.setBorder(BorderFactory.createLineBorder(Color.white, 5));

        JButton getPuzzleInputButton = new JButton("get puzzle input");
        getPuzzleInputButton.setBackground(Color.white);
        getPuzzleInputButton.addActionListener(e -> {
            JFrame puzzleInput = new JFrame("Puzzle Input");
            JPanel jPanel = new JPanel();
            JTextArea textArea = new JTextArea(adventOfCode.getPuzzleInput());
            textArea.setEditable(false);
            jPanel.add(textArea);

            JScrollPane scrollBar = new JScrollPane(jPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            puzzleInput.add(scrollBar);
            puzzleInput.setVisible(true);
            puzzleInput.pack();
        });

        JTextField userInput = new JTextField();
        userInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                if (!Character.isDigit(ch) && ch != KeyEvent.VK_BACK_SPACE && userInput.getText().length() < 10) {
                    e.consume();
                }
                if (ch == KeyEvent.VK_ENTER && (userInput.getText().length() > 0)) {
                    frame.remove(puzzlePanel);
                    checkAnswer(adventOfCode.checkAnswer(Integer.parseInt(userInput.getText())));
                }
            }
        });

        puzzlePanel.add(puzzleText, BorderLayout.PAGE_START);
        puzzlePanel.add(userInputLabel, BorderLayout.LINE_START);
        puzzlePanel.add(getPuzzleInputButton, BorderLayout.LINE_END);
        puzzlePanel.add(userInput, BorderLayout.CENTER);

        frame.add(puzzlePanel);
        frame.pack();
    }

    private void checkAnswer(int answer) {
        JFrame checkAnswerFrame = new JFrame();
        JTextArea checkAnswerText = new JTextArea();
        checkAnswerText.setEditable(false);
        switch (answer) {
            case 0 -> {
                if (adventOfCode.isPartOne()) {
                    checkAnswerText.setText("That's the right answer!");
                } else {
                    checkAnswerText.setText("That's the right answer!\nDay completed!");
                }
                adventOfCode.setPartOne(false);
            }
            case 1 -> checkAnswerText.setText("The answer is lower :(");
            case -1 -> checkAnswerText.setText("The answer is higher :(");
        }
        checkAnswerText.setBorder(BorderFactory.createLineBorder(Color.white, 5));
        addPuzzlePanel();
        checkAnswerFrame.add(checkAnswerText);
        checkAnswerFrame.pack();
        checkAnswerFrame.setVisible(true);
    }
}

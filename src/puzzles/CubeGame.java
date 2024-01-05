package puzzles;

import java.util.ArrayList;
import java.util.List;

public class CubeGame implements Puzzle {

    private List<String> games;
    private final List<String> colors = new ArrayList<>();

    public CubeGame() {
        colors.add("red");
        colors.add("green");
        colors.add("blue");
    }

    @Override
    public int partOne() {
        int redCubes = 12;
        int greenCubes = 13;
        int blueCubes = 14;
        int sum = 0;
        for (String game : games) {
            boolean gamePossible = true;
            int gameIndex = Integer.parseInt(game.substring(5, game.indexOf(":")));
            String[] subsets = game.substring(game.indexOf(":") + 2).split("; ");
            for (String subset : subsets) {
                String[] cubes = subset.split(", ");
                for (String cube : cubes) {
                    for (String color : colors) {
                        if (cube.contains(color)) {
                            int numberOfCubes = Integer.parseInt(cube.substring(0, cube.indexOf(color)).strip());
                            if (color.equals("red") && redCubes < numberOfCubes || color.equals("green") && greenCubes < numberOfCubes || color.equals("blue") && blueCubes < numberOfCubes) {
                                gamePossible = false;
                                break;
                            }
                        }
                    }
                }
            }
            if (gamePossible) {
                sum += gameIndex;
            }
        }
        return sum;
    }

    @Override
    public int partTwo() {
        int sum = 0;
        for (String game : games) {
            int redCubes = 0;
            int greenCubes = 0;
            int blueCubes = 0;
            String[] subsets = game.substring(game.indexOf(":") + 2).split("; ");
            for (String subset : subsets) {
                String[] cubes = subset.split(", ");
                for (String cube : cubes) {
                    for (String color : colors) {
                        if (cube.contains(color)) {
                            int numberOfCubes = Integer.parseInt(cube.substring(0, cube.indexOf(color)).strip());
                            if (color.equals("red") && numberOfCubes > redCubes) {
                                redCubes = numberOfCubes;
                            } else if (color.equals("green") && numberOfCubes > greenCubes) {
                                greenCubes = numberOfCubes;
                            } else if (color.equals("blue") && numberOfCubes > blueCubes) {
                                blueCubes = numberOfCubes;
                            }
                        }
                    }
                }
            }
            sum += redCubes * greenCubes * blueCubes;
        }
        return sum;
    }

    @Override
    public void setInput(List<String> input) {
        games = input;
    }

}

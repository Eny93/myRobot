package main;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class RobotService {

    static Scanner scanner = new Scanner(System.in);

    static List<String> directions = Arrays.asList("EAST", "WEST", "NORTH", "SOUTH");

    static List<String> movements = Arrays.asList("PLACE", "MOVE", "LEFT", "RIGHT", "REPORT", "EXIT");

    static boolean gameOver = false;

    public RobotService() {

    }

    public void runService() {

        System.out.println("Please, place the robot on the field or write EXIT if you want to quit");
        String command = scanner.nextLine();
        List<String> step = parse(command);

        Position p = new Position(0, 0, Direction.NORTH);

        while (!gameOver) {
            if ("EXIT".equalsIgnoreCase(step.get(0))) {
                gameOver = true;
            }

            if (isValidPlace(step)) {

                p = execute(step, p);

                while (!gameOver && scanner.hasNextLine()) {
                    step = parse(scanner.nextLine());
                    if (movements.contains(step.get(0))) {
                        p = execute(step, p);
                    }
                }
                scanner.close();
                System.out.println("Game is over");
            }
        }

    }

    public List<String> parse(String command) {

        List<String> step = Arrays.asList(command.trim().split("[ ,]+"));
        step.set(0, step.get(0).toUpperCase());
        return step;
    }

    public Position execute(List<String> step, Position p) {

        switch (step.get(0)) {
            case "PLACE":
                return place(step, p);
            case "MOVE":
                return move(p);
            case "LEFT":
                return turn(p, -1);
            case "RIGHT":
                return turn(p, 1);
            case "REPORT":
                System.out.println(p.X + "," + p.Y + "," + p.F);
                return p;
            case "EXIT": //stopping the game by the user
                gameOver = true;
                return null;
            default:
                return p;
        }
    }

    public Position turn(Position p, int side) {

        int dir = p.F.getValue() + side;

        if (dir == -1) {
            dir = 3;
        }
        ;
        if (dir == 4) {
            dir = 0;
        }
        ;
        return new Position(p.X, p.Y, Direction.values()[dir]);
    }

    boolean isValidPlace(List<String> step) {

        if (!step.get(1).matches("-?\\d+") || !step.get(2).matches("-?\\d+")) {
            return false;
        }

        int x = Integer.parseInt(step.get(1));
        int y = Integer.parseInt(step.get(2));

        if (step.size() != 4 || !directions.contains(step.get(3).toUpperCase()) || x < 0 || x > 5 || y < 0 || y > 5) {
            return false;
        }
        return true;
    }

    Position place(List<String> step, Position p) {

        if (!isValidPlace(step)) {
            return p;
        }
        return new Position(Integer.parseInt(step.get(1)), Integer.parseInt(step.get(2)), Direction.valueOf(step.get(3).toUpperCase()));
    }

    Position move(Position p) {

        if (p.F.equals(Direction.NORTH) && p.Y + 1 < 5) {
            return new Position(p.X, p.Y + 1, p.F);
        }
        if (p.F.equals(Direction.SOUTH) && p.Y + 1 >= 0) {
            return new Position(p.X, p.Y - 1, p.F);
        }
        if (p.F.equals(Direction.EAST) && p.X + 1 < 5) {
            return new Position(p.X + 1, p.Y, p.F);
        }
        if (p.F.equals(Direction.WEST) && p.X - 1 >= 0) {
            return new Position(p.X - 1, p.Y, p.F);

        } else
            return p;
    }

}

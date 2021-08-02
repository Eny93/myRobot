package main;

public enum Direction {
    NORTH(0),
    EAST(1),
    SOUTH(2),
    WEST(3);
    private int value;

    private Direction(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

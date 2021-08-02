package main;

public class Position {

    int X;
    int Y;
    Direction F;

    public Position(int X, int Y, Direction F) {
        this.X=X;
        this.Y=Y;
        this.F=F;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public Direction getF() {
        return F;
    }

    @Override
    public String toString() {
        return X + "," + Y + "," + F;
    }
}

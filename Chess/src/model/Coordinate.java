package model;

public class Coordinate
{
    private int x; // Column
    private int y; // Row

    public boolean equals( Coordinate c2 )
    {
        return (this.x == c2.getX() && this.y == c2.getY());
    }

    public int getX() {
        return x;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public void setX(int x) {
        this.x = x;
    }


    public int getY() {
        return y;
    }


    public void setY(int y) {
        this.y = y;
    }


    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

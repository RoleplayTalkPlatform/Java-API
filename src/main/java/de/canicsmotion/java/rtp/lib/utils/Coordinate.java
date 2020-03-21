package de.canicsmotion.java.rtp.lib.utils;

/**
 * @Coordinate is an Object that basically represents the in-game Position of a Player
 * x is the players x-position in-game
 * y is the players height of the players position in-game
 * z is the players z-position in-game
 */
public class Coordinate {
    private double x = 0;
    private double y = 0;
    private double z = 0;

    /**
     * @param x is the players x-position in-game
     * @param y is the players height of the players position in-game
     * @param z is the players z-position in-game
     */
    public Coordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Coordinate() {
    }

    /**
     * @return the players x-position in-game
     */
    public double getX() {
        return x;
    }

    /**
     * @param x is the players x-position in-game
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @return the players height of the position in-game
     */
    public double getY() {
        return y;
    }

    /**
     * @param y is the players height of the position in-game
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * @return the players z-position in-game
     */
    public double getZ() {
        return z;
    }

    /**
     * @param z is the players z-position in-game
     */
    public void setZ(double z) {
        this.z = z;
    }

    /**
     * @return the coordinate as String
     */
    @Override
    public String toString() {
        return String.format("X:%.1f Y:%.1f Z%.1f", x, y, z);
    }
}

/**
 * -----------------------------------------------------
 * ES234211 - Programming Fundamental
 * Genap - 2023/2024
 * Group Capstone Project: Snake and Ladder Game
 * -----------------------------------------------------
 * Class    : D
 * Group    : 13
 * Members  :
 * 1. 5026221044 - Sintiarani Febyan Putri
 * 2. 5026231035 - Aldani Prasetyo
 * 3. 5026231160 - Muhammad Gandhi Taqi Utomo
 * ------------------------------------------------------
 */

public class Snake {
    private int tailPosition;
    private int headPosition;
    private int penaltyPoints;

    public Snake(int t, int h, int penaltyPoints) {
        this.tailPosition = t;
        this.headPosition = h;
        this.penaltyPoints = penaltyPoints;
    }

    public void setTailPosition(int t) {
        this.tailPosition = t;
    }

    public void setHeadPosition(int h) {
        this.headPosition = h;
    }

    public void setPenaltyPoints(int penaltyPoints) {
        this.penaltyPoints = penaltyPoints;
    }

    public int getTailPosition() {
        return this.tailPosition;
    }

    public int getHeadPosition() {
        return this.headPosition;
    }

    public int getPenaltyPoints() {
        return this.penaltyPoints;
    }
}

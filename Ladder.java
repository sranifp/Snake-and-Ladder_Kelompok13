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

public class Ladder {
    private int topPosition;
    private int bottomPosition;

    public Ladder(int t, int b) {
        this.topPosition = t;
        this.bottomPosition = b;
    }

    public void setTopPosition(int t) {
        this.topPosition = t;
    }

    public void setBottomPosition(int b) {
        this.bottomPosition = b;
    }

    public int getTopPosition() {
        return this.topPosition;
    }

    public int getBottomPosition() {
        return this.bottomPosition;
    }
}

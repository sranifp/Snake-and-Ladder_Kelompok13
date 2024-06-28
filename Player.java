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

public class Player {
    private String name;
    private int position;
    private int previousPosition;

    Player(String name) {
        this.name = name;
        this.position = 0;
        this.previousPosition = 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(int position) {
        this.previousPosition = this.position;
        this.position = position;
    }

    public String getName() {
        return this.name;
    }

    public int getPosition() {
        return this.position;
    }

    public int getPreviousPosition() {
        return this.previousPosition;
    }

    public int rollDice() {
        return (int) (Math.random() * 6 + 1);
    }

    public void moveAround(int y, int boardSize) {
        if (this.position + y > boardSize) {
            this.previousPosition = this.position;
            this.position = boardSize - (this.position + y) % boardSize;
        } else {
            this.previousPosition = this.position;
            this.position = this.position + y;
        }
    }
}

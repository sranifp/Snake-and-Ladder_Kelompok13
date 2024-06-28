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
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SnL {
    private ArrayList<Player> players;
    private ArrayList<Snake> snakes;
    private ArrayList<Ladder> ladders;
    private int boardSize;
    private int gameStatus;
    private int nowPlaying;
    private int[] snakeHitCount;
    private ArrayList<SurpriseCard> surpriseCards;
    private StringBuilder gameLog;

    public SnL(int s) {
        this.boardSize = s;
        this.players = new ArrayList<Player>();
        this.snakes = new ArrayList<Snake>();
        this.ladders = new ArrayList<Ladder>();
        this.gameStatus = 0;
        this.snakeHitCount = new int[5]; // assuming a 5-player game
        this.surpriseCards = new ArrayList<>();
        this.gameLog = new StringBuilder(); // initialize the gameLog
        addSurpriseCards();
    }

    private void addSurpriseCards() {
        surpriseCards.add(new SurpriseCard("Move forward 10 steps!", 10));
        surpriseCards.add(new SurpriseCard("Move backward 35 steps!", -35));
    }

    public void setBoardSize(int s) {
        this.boardSize = s;
    }

    public void setGameStatus(int s) {
        this.gameStatus = s;
    }

    public int getGameStatus() {
        return this.gameStatus;
    }

    public void play() {
        Player playerInTurn;
        Scanner read = new Scanner(System.in);
        System.out.println("Enter number of players: ");
        while (true) {
            try {
                int numPlayers = read.nextInt();
                read.nextLine(); // consume newline

                if (numPlayers > 5) {
                    throw new IllegalArgumentException("Maximum number of players is 5.");
                }

                for (int i = 1; i <= numPlayers; i++) {
                    System.out.println("Please enter Player " + i + ": ");
                    String playerName = read.nextLine();
                    Player player = new Player(playerName);
                    addPlayer(player);
                }

                break; // exit loop if input is valid
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                read.nextLine(); // consume newline
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        initiateGame();

        do {
            playerInTurn = getWhoseTurn();
            System.out.println("Now Playing " + playerInTurn.getName());
            boolean rollAgain;
            do {
                rollAgain = false;
                System.out.println(playerInTurn.getName() + " please press enter to roll the dice");
                String enter = read.nextLine();

                int die1 = 0;
                int die2 = 0;
                int y = 0;
                if (enter.isEmpty()) {
                    die1 = playerInTurn.rollDice();
                    die2 = playerInTurn.rollDice();
                    y = die1 + die2;
                }
                System.out.println("Dice Number : " + die1 + " And " + die2);
                int previousPosition = playerInTurn.getPosition();
                movePlayerAround(playerInTurn, y);
                int newPosition = playerInTurn.getPosition();
                System.out.println("Previous Position: " + previousPosition);
                System.out.println("New Position: " + newPosition);
                System.out.println("==============================================");
                if (die1 == die2) {
                    rollAgain = true;
                    System.out.println(playerInTurn.getName() + " rolled a double! Roll again.");
                }

            } while (rollAgain);

        } while (getGameStatus() != 2);

        System.out.println("The winner is: " + playerInTurn.getName());
    }

    public void addPlayer(Player s) {
        this.players.add(s);
    }

    public ArrayList<Player> getPlayers(Player s) {
        return this.players;
    }

    public void addSnake(Snake s) {
        this.snakes.add(s);
    }

    public void addSnakes(int[][] s, int[] penalties) {
        for (int r = 0; r < s.length; r++) {
            Snake snake = new Snake(s[r][0], s[r][1], penalties[r]);
            this.snakes.add(snake);
        }
    }

    public void addLadder(Ladder l) {
        this.ladders.add(l);
    }

    public void addLadders(int[][] l) {
        for (int r = 0; r < l.length; r++) {
            Ladder ladder = new Ladder(l[r][1], l[r][0]);
            this.ladders.add(ladder);
        }
    }

    public int getBoardSize() {
        return this.boardSize;
    }

    public ArrayList<Snake> getSnakes() {
        return this.snakes;
    }

    public ArrayList<Ladder> getLadders() {
        return this.ladders;
    }

    public void initiateGame() {
        int[][] l = {
                {2, 23},
                {8, 34},
                {20, 77},
                {32, 68},
                {41, 79},
                {74, 88},
                {82, 100},
                {85, 95}
        };
        addLadders(l);

        int[][] s = {
                {5, 47},
                {9, 29},
                {15, 38},
                {25, 97},
                {33, 53},
                {37, 62},
                {54, 86},
                {70, 92}
        };
        int[] penalties = {5, 5, 5, 10, 10, 10, 15, 15};
        addSnakes(s, penalties);
    }

    public void movePlayerAround(Player p, int x) {
        int previousPosition = p.getPosition();
        p.moveAround(x, this.boardSize);

        // Debugging: Print current position before checking for surprise cards
        System.out.println("Current Position: " + p.getPosition());

        if (p.getPosition() % 20 == 0 || p.getPosition() % 35 == 0) {
            SurpriseCard card = null;
            if (p.getPosition() % 20 == 0) {
                card = surpriseCards.get(0); // Move forward 10 steps
                System.out.println(p.getName() + " drew a surprise card: " + card.getDescription());
            } else if (p.getPosition() % 35 == 0) {
                card = surpriseCards.get(1); // Move backward 35 steps
                System.out.println(p.getName() + " drew a surprise card: " + card.getDescription());
            }

            if (card != null) {
                gameLog.append(p.getName() + " drew a surprise card: " + card.getDescription() + "\n");
                p.moveAround(card.getMoveSteps(), this.boardSize);
                System.out.println(p.getName() + " moved due to surprise card: " + card.getMoveSteps() + " steps");
            }
        }

        for (Ladder l : this.ladders) {
            if (p.getPosition() == l.getBottomPosition()) {
                gameLog.append(p.getName() + " got a Ladder from " + l.getBottomPosition() + " to " + l.getTopPosition() + "\n");
                p.setPosition(l.getTopPosition());
            }
        }
        for (Snake s : this.snakes) {
            if (p.getPosition() == s.getHeadPosition()) {
                gameLog.append(p.getName() + " got a Snake from " + s.getHeadPosition() + " to " + s.getTailPosition() + "\n");
                p.setPosition(s.getTailPosition());
            }
        }
        if (p.getPosition() == this.boardSize) {
            this.gameStatus = 2;
        }
        System.out.println(p.getName() + " moved from " + previousPosition + " to " + p.getPosition());
    }

    public Player getWhoseTurn() {
        if (this.gameStatus == 0) {
            this.gameStatus = 1;
            this.nowPlaying = 0;
            return this.players.get(0);
        } else {
            this.nowPlaying = (this.nowPlaying + 1) % this.players.size();
            return this.players.get(this.nowPlaying);
        }
    }

    private void skipNextTurn(int playerIndex) {
        this.nowPlaying = (playerIndex + 1) % this.players.size();
        System.out.println(this.players.get(playerIndex).getName() + " skips the next turn.");
    }

    private void updateBoard() {
        // Add implementation for updating the board if needed
    }
}



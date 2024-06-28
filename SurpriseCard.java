public class SurpriseCard {
    private String description;
    private int moveSteps;

    public SurpriseCard(String description, int moveSteps) {
        this.description = description;
        this.moveSteps = moveSteps;
    }

    public String getDescription() {
        return description;
    }

    public int getMoveSteps() {
        return moveSteps;
    }
}

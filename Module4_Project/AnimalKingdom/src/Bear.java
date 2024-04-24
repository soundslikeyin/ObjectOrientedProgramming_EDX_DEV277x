import java.awt.*;

public class Bear extends Critter{
    boolean isPolar;
    int stepCount;

    public Bear (boolean polar) {
        isPolar = polar;
        stepCount = 0;
    }

    public Color getColor() {
        if (isPolar) {
            return Color.WHITE;
        }
        else return Color.BLACK;
    }

    public String toString() {
        if (stepCount % 2 > 0) {
            return "\\";
        }
        else return "/";

    }

    public Action getMove(CritterInfo info) {

        if (stepCount == 2) {
            stepCount = 0;
        }
        ++stepCount;

        if (info.frontThreat()){
            return Action.INFECT;
        }
        else if (info.getFront() == Neighbor.EMPTY) {
            return Action.HOP;
        }
        else {
            return Action.LEFT;
        }
    }


}

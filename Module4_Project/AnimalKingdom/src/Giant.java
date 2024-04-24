import java.awt.*;

public class Giant extends Critter{
    int stepCount;

    public Giant(){
        stepCount = 1;
    }

    public Color getColor() {
        return Color.GRAY;
    }

    public String toString() {
        if (stepCount < 7) {
            return "fee";
        } else if (stepCount < 13) {
            return "fie";
        } else if (stepCount < 19) {
            return "foe";
        } else {
            return "fum";
        }
    }

    public Action getMove(CritterInfo info) {
        if (stepCount == 24) {
            stepCount = 1;
        } else {
            ++stepCount;
        }

        if (info.frontThreat()){
            return Action.INFECT;
        }
        else if (info.getFront() == Neighbor.EMPTY) {
            return Action.HOP;
        }
        else {
            return Action.RIGHT;
        }
    }
}

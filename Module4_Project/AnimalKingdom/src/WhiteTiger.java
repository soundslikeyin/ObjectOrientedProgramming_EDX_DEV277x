import java.awt.*;

public class WhiteTiger extends Critter{
    boolean hasInfectedOther;

    public WhiteTiger(){
        hasInfectedOther = false;
    }

    public Color getColor() {
        return Color.WHITE;
    }

    public String toString() {
        if (hasInfectedOther) {
            return "TGR";
        } else return "tgr";
    }

    public Action getMove(CritterInfo info) {
        if (info.frontThreat()){
            hasInfectedOther = true;
            return Action.INFECT;
        }
        else if ((info.getFront() == Neighbor.WALL) || (info.getRight() == Neighbor.WALL) ){
            return Action.LEFT;
        }
        else if (info.getFront() == Neighbor.SAME){
            return Action.RIGHT;
        } else {
            return Action.HOP;
        }
    }




}

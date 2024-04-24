import java.awt.*;
import java.util.Random;

public class Tiger extends Critter{
    Random rand = new Random();
    int stepCount;
    int randomNum;
    int newNum;
    Color currentColor;

    public Tiger () {
        stepCount = 1;
    }

    public Color getColor() {
        if (stepCount == 1) {

            while (newNum == randomNum) {
                newNum = 1 + rand.nextInt(3);
            }
            randomNum = newNum;

            switch (randomNum) {
                case 1:
                    currentColor = Color.RED;
                    break;
                case 2:
                    currentColor = Color.GREEN;
                    break;
                case 3:
                    currentColor = Color.BLUE;
                    break;
            }
        }
        return currentColor;
    }

    public String toString() {
        return "TGR";
    }

    public Action getMove(CritterInfo info) {

        if (stepCount == 3) {
            stepCount = 0;
        }

        ++stepCount;

        if (info.frontThreat()){
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

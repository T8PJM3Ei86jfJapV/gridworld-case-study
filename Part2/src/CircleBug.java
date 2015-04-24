import info.gridworld.actor.Bug;

public class CircleBug extends Bug {
    private int steps;
    private int sideLength;

    /**
     * Constructor
     */
    public CircleBug(int radius) {
        steps = 0;
        sideLength = radius;
    }

    public void act() {
        if (steps < sideLength && canMove()) {
            move();
            steps++;
        } else {
            turn();
            steps = 0;
        }
    }
}
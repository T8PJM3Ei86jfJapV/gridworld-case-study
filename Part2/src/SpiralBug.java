import info.gridworld.actor.Bug;

public class SpiralBug extends Bug {
    private int steps;
    private int sideLength;

    /**
     * Constructor
     */
    public SpiralBug(int length) {
        steps = 0;
        sideLength = length;
    }

    public void act() {
        if (steps < sideLength) {
            move();
            steps++;
        } else {
            turn();
            turn();
            steps = 0;
            sideLength++;
        }
    }
}
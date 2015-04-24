import info.gridworld.actor.Bug;

public class ZBug extends Bug {
    private int steps;
    private int sideLength;

    public ZBug(int length) {
        steps = 0;
        sideLength = length;
    }

    public void act() {
        if (steps == 0) {
            turn();
            turn();
        } else if (steps == sideLength){
            turn();
            turn();
            turn();
        } else if (steps == 2*sideLength) {
            turn();
            turn();
            turn();
            turn();
            turn();
        }
        if (steps < 3*sideLength && canMove()) {
            move();
            steps++;
        }
    }
}

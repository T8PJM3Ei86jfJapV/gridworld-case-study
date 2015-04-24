import java.util.Arrays;

import info.gridworld.actor.Bug;

public class DancingBug extends Bug {
    private int steps;
    private int[] times;
    private int length;

    /**
     * Constructor
     */
    public DancingBug(int[] array) {
        steps = 0;

        /**
         * An array should not be stored directly
         */
        if (array == null) {
            this.times = new int[0];
        } else {
            this.times = Arrays.copyOf(array, array.length);
        }
        length = times.length;
    }

    public void act() {
        if (steps == length) {
            steps = 0;
        }
        if (steps < length && canMove()) {
            for (int i=0; i < times[steps]; i++) {
                    turn();
            }
            move();
            steps++;
        }
    }
}

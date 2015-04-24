/*
 *
 * Group 13-gridWorld-74
 * School of Software, Sun Yat-sen University 
 *
 */

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;

import java.awt.Color;

public class Jumper extends Actor
{
    /**
     * Define four moving status of Jumpers
     */
    private static final int ERROR = -1;
    private static final int TURN = 0;
    private static final int STEP = 1;
    private static final int JUMP = 2;

    /**
     * Constructs an orange jumper by default.
     */
    public Jumper() {
        setColor(Color.ORANGE);
    }

    /**
     * Constructs a jumper of a given color.
     */
    public Jumper(Color jumperColor) {
        setColor(jumperColor);
    }

    /**
     * If status equals to STEP or JUMP, calls move(),
     * calls turn() when status equals to TURN,
     * otherwise, do nothing.
     */
    public void act() {
        int status = canMove();
        if (status == TURN) {
            turn();
        } else if (status == STEP || status == JUMP) {
            move(status);
        }
    }

    /**
     * Turns the jumper 45 degrees to the right without changing its location.
     */
    public void turn() {
        setDirection(getDirection() + Location.HALF_RIGHT);
    }

    /**
     * If possible, allocate a jumper into a new location,
     * calls removeSelfFromGrid() to remove a jumper if it can not be allocated.
     */
    public void move(int status) {
        Grid<Actor> grid = getGrid();
        if (grid == null) {
            return;            
        }
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        Location nextNext = next.getAdjacentLocation(getDirection());

        if (status == STEP) {
            moveTo(next);
        } else if (status == JUMP) {
            moveTo(nextNext);
        } else {
            removeSelfFromGrid();
        }
    }

    /**
     * Determine Jumpers' moving status.
     */
    public int canMove() {
        Grid<Actor> grid = getGrid();
        if (grid == null) {
            return ERROR;
        }
        Location cur = getLocation();
        Location next = cur.getAdjacentLocation(getDirection());
        Location nextNext = next.getAdjacentLocation(getDirection());

        /**
         * If a Jumper can jump, return JUMP.
         */
        if (grid.isValid(nextNext)) {
            Actor neighbor = grid.get(nextNext);
            if ((neighbor == null) || (neighbor instanceof Flower)) {
                return JUMP;
            }
        }

        /**
         * If a Jumper can step, return STEP.
         */
        if (grid.isValid(next)) {
            Actor neighbor = grid.get(next);
            if ((neighbor == null) || (neighbor instanceof Flower)) {
                return STEP;
            }
        }

        /**
         * Oterwise, return TURN.
         */
        return TURN;
    }
}

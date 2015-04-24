// my version
package info.gridworld.maze;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
    public Location next;
    public Location last;
    public boolean isEnd = false;
    public Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
    public Integer stepCount = 0;
    boolean hasShown = false;//final message has been shown

    private int[] directionHitCount = new int[4];
    private static final boolean DIRECTION_HIT = true;
    private static final boolean DIRECTION_BACK = false;

    /**
     * Constructs a maze bug that traces a square of a given side length
     * 
     */
    public MazeBug() {
        setColor(Color.GREEN);
        last = new Location(0, 0);

        //this.putSelfInGrid(this.getGrid(), last);

        ArrayList<Location> cross = new ArrayList<Location>();
        cross.add(last);
        crossLocation.push(cross);
    }

    /**
     * Moves to the next location of the square.
     */
    public void act() {
        boolean willMove = canMove();

        if (isEnd == true) {
            //to show step count when reach the goal        
            if (hasShown == false) {
                String msg = stepCount.toString() + " steps";
                JOptionPane.showMessageDialog(null, msg);
                hasShown = true;
            }
        } else if (willMove) {
            last = getLocation();
            ArrayList<Location> cross = new ArrayList<Location>();
            cross.add(getLocation());
            crossLocation.push(cross);

            move();
            stepCount++;
        } else {
            directionCounter(getDirection(), DIRECTION_HIT);

            next = last;
            crossLocation.pop();
            last = crossLocation.peek().get(0);
            crossLocation.peek().add(getLocation());

            move();
            stepCount++;
        }

    }

    /**
     * Find all positions that can be move to.
     * 
     * @param loc
     *            the location to detect.
     * @return List of positions.
     */
    public ArrayList<Location> getValid(Location loc) {

        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return null;
        }

        ArrayList<Location> valid = new ArrayList<Location>();

        int[] directions = {Location.NORTH, Location.EAST, Location.SOUTH, Location.WEST};
        int back = getLocation().getDirectionToward(last);

        for (int direction: directions) {
            if (direction != back) {
                Location det = loc.getAdjacentLocation(direction);

                if (gr.isValid(det) && !crossLocation.peek().contains(det)) {
                    if (gr.get(det) instanceof Rock && gr.get(det).getColor().equals(Color.RED)) {
                        isEnd = true;

                    } else if (gr.get(det) instanceof Flower || gr.get(det) == null) {
                        valid.add(det);
                        directionCounter(direction, DIRECTION_HIT);
                    }
                }
            }
        }

        return valid;
    }

    private void directionCounter(int direction, boolean factor) {
        if (factor == DIRECTION_HIT) {
            directionHitCount[direction/90]++;
        } else if (directionHitCount[direction/90] > 0) {
            // && factor == DIRECTION_BACK
            directionHitCount[direction/90] --;
        }
    }

    private Location selectLocation(ArrayList<Location> locs) {
        int sum = 0;
        for (Location loc: locs) {
            int direction = getLocation().getDirectionToward(loc);
            sum += directionHitCount[direction/90];
        }

        Random rand = new Random();
        int flag =  rand.nextInt(sum);

        int total = 0;
        for (Location loc: locs) {
            int direction = getLocation().getDirectionToward(loc);
            total += directionHitCount[direction/90];
            if (flag < total) {
                return loc;
            }
        }

        return (Location) null;
    }

    /**
     * Tests whether this bug can move forward into a location that is empty or
     * contains a flower.
     * 
     * @return true if this bug can move.
     */
    public boolean canMove() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return false;
        }

        ArrayList<Location> locs = getValid(getLocation());
        if (locs.size() == 0) {
            return false;
        }

        next = selectLocation(locs);

        return true;
    }

    /**
     * Moves the bug forward, putting a flower into the location it previously
     * occupied.
     */
    public void move() {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return;
        Location loc = getLocation();
        if (gr.isValid(next)) {
            setDirection(getLocation().getDirectionToward(next));
            moveTo(next);
        } else
            removeSelfFromGrid();
        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(gr, loc);
    }
}

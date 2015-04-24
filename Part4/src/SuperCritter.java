import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;
import java.util.ArrayList;

// A SuperCritter eat all of the neighbors within two steps of its current location(except SuperCritter objects).
// A SuperCritter turn randomly one direction from LEFT, RIGHT, AHEAD and HALF_CIRCLE to go if possible.
// A SuperCritter has its lifetime, and it is 10 steps by default.
// A SuperCritter turns to HALF_RIGHT when it can't move.
public class SuperCritter extends Critter
{
    private int lifetime;
    private int steps;

    // Initialize step of a SuperCritter to zero, and color to RED.
    // Lifetime of it is 10 by default.
    public SuperCritter()
    {
        steps = 0;
        lifetime = 10;
        setColor(Color.RED);
    }

    // Initialize a SuperCritter by given lifetime.
    public SuperCritter(int life)
    {
        steps = 0;
        lifetime = life;
        setColor(Color.RED);
    }

    // Gets all actors within two steps of current location.
    // Return an ArrayList.
    public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> actors = new ArrayList<Actor>();

        Grid<Actor> grid = getGrid();
        Location curLoc = getLocation();
        int curCol = curLoc.getCol();
        int curRow = curLoc.getRow();

        Location loc1 = new Location(curRow - 2, curCol - 2);
        Location loc2 = new Location(curRow + 2, curCol + 2);

        int minRow = loc1.getRow();
        int maxRow = loc2.getRow();
        int minCol = loc1.getCol();
        int maxCol = loc2.getCol();

        // scan a block of a location.
        for (int i = minRow; i <= maxRow; i++) {
            for (int j = minCol; j <= maxCol; j++) {
                Location loc = new Location(i, j);
                if (grid.isValid(loc)) {
                    Actor a = grid.get(loc);
                    if ((a != null) && !(a instanceof SuperCritter)) {
                        actors.add(a);
                    }
                }
            }
        }
        return actors;
    }

    // Eats all of the neighbors within two steps of its current location,
    // except SuperCritter objects.
    public void processActors(ArrayList<Actor> actors)
    {
        for (Actor a : actors)
        {
            if (!(a instanceof SuperCritter)) {
                a.removeSelfFromGrid();
            }
        }
    }

    // Gets all movable locations in four direction.
    public ArrayList<Location> getMoveLocations()
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        int[] dirs = { Location.LEFT, Location.RIGHT, Location.AHEAD, Location.HALF_CIRCLE};
        for (Location loc : getLocationsInDirections(dirs)) {
            if (getGrid().get(loc) == null) {
                locs.add(loc);
            }
        }
        return locs;
    }

    public void makeMove(Location loc)
    {
        if (loc.equals(getLocation()))
        {
            setDirection(getDirection() + Location.HALF_RIGHT);
        } else if (loc != null && steps < lifetime) {
            int newDir = getLocation().getDirectionToward(loc);
            setDirection(newDir);
            moveTo(loc);
            steps++;
        } else {
            removeSelfFromGrid();            
        }
    }

    public ArrayList<Location> getLocationsInDirections(int[] directions)
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid gr = getGrid();
        Location loc = getLocation();
        for (int d : directions)
        {
            Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
            if (gr.isValid(neighborLoc)) {
                locs.add(neighborLoc);
            }
        }
        return locs;
    }
}
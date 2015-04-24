import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;

import java.awt.Color;
import java.util.ArrayList;

// A QuickCrab processes actors the same way a CrabCritter does.
// A QuickCrab moves to one of the two locations, randomly selected,
// that are two spaces to its right or left,
// if that location and the intervening location are both empty.
// Otherwise, a QuickCrab moves like a CrabCritter.
public class QuickCrab extends CrabCritter
{
    public ArrayList<Location> getMoveLocations()
    {
        ArrayList<Location> quickPass = getQuickPass();
        int n = quickPass.size();

        if (n == 2) {
            return quickPass;
        } else {
            return super.getMoveLocations();
        }
    }

    public ArrayList<Location> getQuickPass() {
        Grid<Actor> grid = getGrid();
        ArrayList<Location> locs = new ArrayList<Location>();
        ArrayList<Location> pass = new ArrayList<Location>();

        Location cur = getLocation();

        // Gets four location beside, and add them to an ArrayList.
        Location left = cur.getAdjacentLocation(Location.LEFT);
        Location leftAgain = left.getAdjacentLocation(Location.LEFT);
        Location right = cur.getAdjacentLocation(Location.RIGHT);
        Location rightAgain = right.getAdjacentLocation(Location.RIGHT);
        locs.add(left);
        locs.add(leftAgain);
        locs.add(right);
        locs.add(rightAgain);

        // If all of these four location are all empty,
        // add leftAgain and rightAgain to the array.
        // Otherwise, return a null ArrayList.
        for (Location loc : locs) {
            if (!grid.isValid(loc) || (grid.get(loc) != null && !(grid.get(loc) instanceof Flower))) {
                return pass;
            }
        }

        pass.add(leftAgain);
        pass.add(rightAgain);
        return pass;
    }
}

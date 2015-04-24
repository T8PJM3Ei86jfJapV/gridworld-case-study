import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;

import java.awt.Color;
import java.util.ArrayList;

// A KingCrab gets the actors to be processed in the same way a CrabCritter does.
// A KingCrab causes each actor that it processes to move one location further away from the KingCrab.
// If the actor cannot move away, the KingCrab removes it from the grid. 
// When the KingCrab has completed processing the actors, it moves like a CrabCritter.

public class KingCrab extends CrabCritter
{
    public void processActors(ArrayList<Actor> actors)
    {
        Grid<Actor> grid = getGrid();

        for (Actor a : actors)
        {
            Location loc = getLocation();
            int dir = loc.getDirectionToward(a.getLocation());

            Location nextLoc = a.getLocation().getAdjacentLocation(dir);

            if (!grid.isValid(nextLoc)) {
                a.removeSelfFromGrid();
            } else if (grid.get(nextLoc) != null) {
                a.removeSelfFromGrid();
            } else {
                a.moveTo(nextLoc);
            }
        }
    }
}
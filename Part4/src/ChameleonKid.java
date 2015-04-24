import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

public class ChameleonKid extends ChameleonCritter
{
    // lose 5% of color value in each step
    private static final double DARKENING_FACTOR = 0.05;

    // Randomly selects a neighbor and changes this critter's color to be the
    // same as that neighbor's. If there are no neighbors, no action is taken.
    public void processActors(ArrayList<Actor> actors)
    {
        for (Actor actor : actors) {
            Location curLoc = getLocation();
            Location actorLoc = actor.getLocation();
            int relative = curLoc.getDirectionToward(actorLoc);

            // A ChameleonKid changes its color to the color of one of the actors immediately in front or behind
            if (relative == 0 || relative == 180) {
                setColor(actor.getColor());
                return;
            }
        }

        Color c = getColor();
        int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
        int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
        int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));
        setColor(new Color(red, green, blue));
    }
}

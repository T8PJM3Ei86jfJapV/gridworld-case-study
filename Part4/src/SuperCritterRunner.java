import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains Chameleon critters. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public final class SuperCritterRunner
{
    private SuperCritterRunner() {
        //An empty constructor
    }

    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.add(new Location(7, 8), new Rock(Color.PINK));
        world.add(new Location(4, 4), new SuperCritter());
        world.add(new Location(5, 8), new SuperCritter());
        world.show();
    }
}

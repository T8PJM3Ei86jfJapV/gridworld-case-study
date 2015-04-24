import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * If a class is only a utility class,
 * we should make the class final and define a private constructor
 */
public final class DancingBugRunner
{    
    private DancingBugRunner() {
        /**
         * An Empty private constructor
         */
    }

    public static void main(String[] args)
    {
        /**
         * Inintialize an ActorWorld to filling with Actors.  
         */
        ActorWorld world = new ActorWorld();

        /**
         * Inintialize a DancingBug object by given array.  
         */
        int array[] = {1, 2, 3, 4, 5};
        DancingBug alice = new DancingBug(array);
        alice.setColor(Color.ORANGE);
        world.add(new Location(6, 5), alice);
        world.show();
    }
}

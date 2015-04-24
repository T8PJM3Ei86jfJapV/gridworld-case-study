import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * If a class is only a utility class,
 * we should make the class final and define a private constructor
 */
public final class ZBugRunner
{
    private ZBugRunner() {
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
        ZBug alice = new ZBug(4);
        alice.setColor(Color.ORANGE);
        world.add(new Location(2, 3), alice);
        world.show();
    }
}

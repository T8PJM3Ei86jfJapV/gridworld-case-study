import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * If a class is only a utility class,
 * we should make the class final and define a private constructor
 */
public final class SpiralBugRunner
{
    private SpiralBugRunner() {
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
        SpiralBug alice = new SpiralBug(2);
        alice.setColor(Color.ORANGE);
        world.add(new Location(5, 3), alice);
        world.show();
    }
}

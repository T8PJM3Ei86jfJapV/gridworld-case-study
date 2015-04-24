import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains BlusterCritter critters.
 * This class is not tested on the AP CS A and AB exams.
 */
public final class BlusterCritterRunner
{
    private BlusterCritterRunner() {
        //An empty constructor
    }

    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();

        // add a block of four BlusterCritters into world
        for (int i=2; i<4; i++){
            for (int j=2; j<4; j++) {
                world.add(new Location(i, j), new BlusterCritter(1));
            }
        }
        world.show();
    }
}
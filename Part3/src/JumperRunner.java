/*
 * JumperRunner.java
 * Version 1.0
 * School of Software, Sun Yat-sen University
 * 
 * @author 
 * @author 
 * @author 
 * @author 
 */

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;

import java.awt.Color;

/**
 * This class runs a world that contains jumper(s).
 */
public final class JumperRunner
{
    private JumperRunner() {

    }

    public static void main(String[] args)
    {
		/**
		 * Create a new ActorWorld.
		 */
        ActorWorld world = new ActorWorld();

		/**
		 * Add two Jumpers into the grid.
		 */
        Jumper alice = new Jumper(Color.RED);
	    Jumper bob = new Jumper();
        world.add(new Location(7, 5), alice);
	    world.add(new Location(9, 7), bob);

		/**
		 * Add two Rocks into the grid.
		 */
	    world.add(new Location(4, 5), new Rock());
	    world.add(new Location(3, 5), new Rock());

        world.show();
    }
}

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;

// This class runs a world with additional grid choices.

public final class Runners
{
    private Runners() {
        // An empty constructor.
    }
    public static void main(String[] args)
    {
        // New a world.
        ActorWorld world = new ActorWorld();

        // Add custom classes of Grid to world.
        world.addGridClass("SparseBoundedGrid");
        world.addGridClass("SparseBoundedGrid2");
        world.addGridClass("SparseBoundedGrid3");
        world.addGridClass("UnboundedGrid2");
        world.add(new Location(2, 2), new Critter());
        world.show();
    }
}
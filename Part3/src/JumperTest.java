import static org.junit.Assert.*;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import org.junit.Test;

public class JumperTest {

    /**
     * a.
     * What will a jumper do if the location in front of it is empty,
     * but the location two cells in front contains a flower or a rock？
     */
    @Test
    public void case1() {
        Grid<Actor> grid = new BoundedGrid<Actor>(20, 20);

        Jumper jumper = new Jumper();
        jumper.putSelfInGrid(grid, new Location(10, 10));

        Flower flower = new Flower();
        flower.putSelfInGrid(grid, new Location(8, 10));
        jumper.act();
        assertEquals(jumper.getLocation(), new Location(8, 10));

        Rock rock = new Rock();
        rock.putSelfInGrid(grid, new Location(6, 10));
        jumper.act();
        assertEquals(jumper.getLocation(), new Location(7, 10));
    }


    /**
     * b.
     * What will a jumper do if the location two cells in front of the jumper is out of the grid?
     */
    @Test
    public void case2() {
        Grid<Actor> grid = new BoundedGrid<Actor>(20, 20);

        Jumper jumper = new Jumper();
        jumper.putSelfInGrid(grid, new Location(1, 10));
        jumper.act();
        assertEquals(jumper.getLocation(), new Location(0, 10));
    }


    /**
     * c.
     * What will a jumper do if it is facing an edge of the grid?
     */
    @Test
    public void case3() {
        Grid<Actor> grid = new BoundedGrid<Actor>(20, 20);

        Jumper jumper = new Jumper();
        jumper.putSelfInGrid(grid, new Location(0, 10));

        jumper.act();
        assertEquals(jumper.getDirection(), Location.NORTHEAST);

        jumper.act();
        assertEquals(jumper.getDirection(), Location.EAST);

        jumper.act();
        assertEquals(jumper.getLocation(), new Location(0, 12));

    }

    /**
     * d.
     * What will a jumper do if another actor (not a flower or a rock)
     * is in the cell that is two cells in front of the jumper?
     */
    @Test
    public void case4() {
        Grid<Actor> grid = new BoundedGrid<Actor>(20, 20);

        Jumper jumper = new Jumper();
        jumper.putSelfInGrid(grid, new Location(10, 10));

        Bug bug = new Bug();
        bug.putSelfInGrid(grid, new Location(8, 10));

        jumper.act();
        assertEquals(jumper.getLocation(), new Location(9, 10));

    }

    /**
     * e.
     * What will a jumper do if it encounters another jumper in its path?
     */
    @Test
    public void case5() {
        Grid<Actor> grid = new BoundedGrid<Actor>(20, 20);

        Jumper jumper1 = new Jumper();
        jumper1.putSelfInGrid(grid, new Location(10, 10));

        Jumper jumper2 = new Jumper();
        jumper2.setDirection(Location.EAST);
        jumper2.putSelfInGrid(grid, new Location(8, 8));

        jumper1.act();
        assertEquals(jumper1.getLocation(), new Location(8, 10));

        jumper2.act();
        assertEquals(jumper2.getLocation(), new Location(8, 9));
    }
}

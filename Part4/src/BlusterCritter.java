import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;

import java.awt.Color;
import java.util.ArrayList;


/*
 * Create a class BlusterCritter that extends Critter.
 * A BlusterCritter looks at all of the neighbors within two steps of its current location.
 * It counts the number of critters in those locations.
 * If there are fewer than c critters, the BlusterCritter's color gets brighter (color values increase).
 * If there are c or more critters, the BlusterCritter's color darkens (color values decrease).
 * Here, c is a value that indicates the courage of the critter. It should be set in the constructor.
*/
public class BlusterCritter extends Critter
{
    //static variables for color changing
    private static final boolean DARKER = true;
    private static final boolean BRIGHTER = false;

    private int courage;

    //construct an BlusterCritter by a given courage
    BlusterCritter (int c) {
    	super();
        courage = c;
    }

    public void processActors(ArrayList<Actor> actors) {
    	Location curLoc = getLocation();
        int curCol = curLoc.getCol();
        int curRow = curLoc.getRow();

        //get two corner of the block to scan 
        Location loc1 = new Location(curRow - 2, curCol - 2);
        Location loc2 = new Location(curRow + 2, curCol + 2);

        int count = scanSquare(loc1, loc2);

        if (count < courage) {
        	colorChanging(BRIGHTER);
        } else {
        	colorChanging(DARKER);
        }
    }

    private int scanSquare(Location loc1, Location loc2) {
        Grid grid = getGrid();

    	int minRow = loc1.getRow();
    	int maxRow = loc2.getRow();
    	int minCol = loc1.getCol();
    	int maxCol = loc2.getCol();

    	int num = 0;

    	for (int i = minRow; i <= maxRow; i++) {
    		for (int j = minCol; j <= maxCol; j++) {
    			Location loc = new Location(i, j);
                if (grid.isValid(loc) && grid.get(loc) instanceof Critter) {
                    num++;
                }
    		}
    	}
        // except the BlusterCritter it self
        return num--;
    }

    private void colorChanging(boolean factor) {
        if (factor == DARKER) {
            setColor(getColor().darker());
        } else {
            setColor(getColor().brighter());
        }
    }
}
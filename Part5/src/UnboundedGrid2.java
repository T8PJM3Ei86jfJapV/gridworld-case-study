import java.util.ArrayList;

import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

public class UnboundedGrid2<E> extends AbstractGrid<E>
{

    // Use an array of Objects to store the grid elements
    // @param rowNum number of an increasing rows
    // @param colNum number of an increasing columns
    private Object[][] occupantArray;
    private int rowNum;
    private int colNum;

    // Constructs an empty unbounded grid.
    // The constructor allocates a 16 x 16 array.
    public UnboundedGrid2() {
        rowNum = 16;
        colNum = 16;
        occupantArray = new Object[rowNum][colNum];
    }

    public int getNumRows() {
        return -1;
    }

    public int getNumCols() {
        return -1;
    }

    public boolean isValid(Location loc) {
        // Return true when a location has non-negative row and column values.
        return (0 <= loc.getRow() && 0 <= loc.getCol());
    }

    private void doubleSize() {

        // Double both array bounds.
        rowNum = 2 * rowNum;
        colNum = 2 * colNum;

        Object[][] oldOccupantArray = (Object[][]) occupantArray.clone();

        // Construct a new square array with those bounds,
        // and place the existing occupants into the new array.
        occupantArray = new Object[rowNum][colNum];
        for (int r = 0; r < rowNum/2; r++) {
            for (int c = 0; c < colNum/2; c++) {
                occupantArray[r][c] = oldOccupantArray[r][c];
            }
        }
    }

    public ArrayList<Location> getOccupiedLocations() {
        ArrayList<Location> locs = new ArrayList<Location>();

        // Look at all grid locations.
        for (int r = 0; r < rowNum; r++)
        {
            for (int c = 0; c < colNum; c++)
            {
                // If there's an object at this location, put it in the array.
                Location loc = new Location(r, c);
                if (get(loc) != null) {
                    locs.add(loc);
                }
            }
        }

        return locs;
    }

    public E get(Location loc) {
        if (loc == null) {
            throw new NullPointerException("loc == null");
        }

        if (0 <= loc.getRow() && loc.getRow() < rowNum && 0 <= loc.getCol() && loc.getCol() < colNum) {
            return (E) occupantArray[loc.getRow()][loc.getCol()];
        }

        return null;
    }

    public E put(Location loc, E obj) {
        if (loc == null) {
            throw new NullPointerException("loc == null");            
        }
        if (obj == null) {
            throw new NullPointerException("obj == null");            
        }

        // When the index that is outside the current array bounds, double size of the array.
        while (rowNum <= loc.getRow() || colNum <= loc.getCol()) {
            doubleSize();
        }

        // Add the object to the grid.
        E oldOccupant = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }

    public E remove(Location loc) {
        if (loc == null) {
            throw new NullPointerException("loc == null");
        }

        // Remove the object from the grid.
        E r = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return r;
    }
}
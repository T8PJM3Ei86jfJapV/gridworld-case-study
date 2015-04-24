import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

public class SparseBoundedGrid2<E> extends AbstractGrid<E> {

    // Using a HashMap to implement the SparseBoundedGrid.
    // @param rowNum number of rows in SparseBoundedGrid
    // @param colNum number of columns in SparseBoundedGrid
    private Map<Location, E> map;
    private int rowNum;
    private int colNum;

    // Constructs an empty sparse bounded grid.
    public SparseBoundedGrid2(int rows, int cols) {
        if (rows <= 0) {
            throw new IllegalArgumentException("rows <= 0");
        }
        if (cols <= 0) {
            throw new IllegalArgumentException("cols <= 0");
        }

        rowNum = rows;
        colNum = cols;
        map = new HashMap<Location, E>();
    }

    public int getNumRows() {
        return colNum;
    }

    public int getNumCols() {
        return rowNum;
    }

    public boolean isValid(Location loc) {

        // Return true when the location in the square
        // within range of rowNum and colNum
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    public ArrayList<Location> getOccupiedLocations() {
        ArrayList<Location> locs = new ArrayList<Location>();

        for (Location loc: map.keySet()) {
            locs.add(loc);
        }

        return locs;
    }

    public E get(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }

        return map.get(loc);
    }

    public E put(Location loc, E obj) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }
        if (obj == null) {
            throw new NullPointerException("obj == null");
        }

        // Put an E object into the given location
        // and return the old one.
        return map.put(loc, obj);
    }

    public E remove(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }

        // Remove an E object and return the old one.
        return map.remove(loc);
    }
}
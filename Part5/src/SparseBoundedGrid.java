import java.util.ArrayList;
import java.util.LinkedList;

import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

public class SparseBoundedGrid<E> extends AbstractGrid<E> {

    // Uses a LinkedList<OccupantInCol> with a helper class.
    // @param rowNum number of rows in SparseBoundedGrid
    // @param colNum number of columns in SparseBoundedGrid
    private ArrayList<LinkedList<OccupantInCol>> occupantArray;
    private int rowNum;
    private int colNum;

    public SparseBoundedGrid(int rows, int cols) {
        if (rows <= 0) {
            throw new IllegalArgumentException("rows <= 0");
        }
        if (cols <= 0) {
            throw new IllegalArgumentException("cols <= 0");
        }

        rowNum = rows;
        colNum = cols;

        occupantArray = new ArrayList<LinkedList<OccupantInCol>>();

        // Add all rows into the accupantArray
        for (int i = 0; i < rowNum; i++) {
            occupantArray.add(new LinkedList<OccupantInCol>());
        }
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

        // Look at all grid locations.
        for (int row = 0; row < rowNum; row++) {
            LinkedList<OccupantInCol> occupants = occupantArray.get(row);

            // If there are objects at this location, put them in the locs array.
            for (OccupantInCol occupant : occupants) {
                Location loc = new Location(row, occupant.getCol());
                locs.add(loc);
            }
        }

        return locs;
    }

    public E get(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }

        LinkedList<OccupantInCol> occupants = occupantArray.get(loc.getRow());

        if (occupants.size() != 0) {
            for (OccupantInCol occupant : occupants) {

                // Gets occupant at the given location.
                if (occupant.getCol() == loc.getCol()) {
                    return (E) occupant.getOccupant();
                }
            }
        }

        // If there is no occupant, return null.
        return null;
    }

    public E put(Location loc, E obj) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }
        if (obj == null) {
            throw new NullPointerException("obj == null");
        }

        E oldOccupant = get(loc);

        LinkedList<OccupantInCol> occupants = occupantArray.get(loc.getRow());
        if (oldOccupant == null) {
            occupants.add(new OccupantInCol(obj, loc.getCol()));
        } else {
            for (OccupantInCol occupant : occupants) {
                if (occupant.getCol() == loc.getCol()) {

                    // Set occupant to the given object.
                    occupant.setOccupant(obj);
                }
            }
        }

        // Return old occupant at the location. 
        return oldOccupant;
    }

    public E remove(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }

        LinkedList<OccupantInCol> occupants = occupantArray.get(loc.getRow());
        if (occupants.size() != 0) {
            for (OccupantInCol occupant : occupants) {
                if (occupant.getCol() == loc.getCol()) {
                    E removeObject = (E) occupant.getOccupant();

                    // Remove the object from the grid.
                    occupants.remove(occupant);

                    // Return the element that was removed from the list.
                    return removeObject;
                }
            }
        }

        // Return null value if there is no occupant at the given location.
        return null;
    }
}
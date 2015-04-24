import info.gridworld.grid.UnboundedGrid;
import info.gridworld.grid.Location;

// Use the UnboundedGrid class to impelment a SparseBoundedGrid
public class SparseBoundedGrid3<E> extends UnboundedGrid<E>
{
    // @param rowNum number of rows in SparseBoundedGrid
    // @param colNum number of columns in SparseBoundedGrid
    private int rowNum;
    private int colNum;

    // Constructs an empty unbounded grid.
    public SparseBoundedGrid3(int rows, int cols) {
        if (rows <= 0) {
            throw new IllegalArgumentException("rows <= 0");
        }
        if (cols <= 0) {
            throw new IllegalArgumentException("cols <= 0");            
        }

        rowNum = rows;
        colNum = cols;
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
}

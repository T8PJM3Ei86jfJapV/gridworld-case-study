// A helper class for SparseBoundedGrid class.

public class OccupantInCol
{
    private Object occupant;
    private int col;

    // Construct an OccupantInCol with a given object and a column value.
    public OccupantInCol(Object obj, int column) {

        // Ensure the column not less than zero,
        // and an object in the location is not null. 
        if (column < 0) {
            throw new IllegalArgumentException("column < 0");
        }

        if (obj == null) {
            throw new NullPointerException("obj == null");
        }

        occupant = obj;
        col = column;
    }

    public int getCol() {
        return col;
    }

    public Object getOccupant() {
        return occupant;
    }

    public void setOccupant(Object obj) {

        // Ensure an object in the location is not null. 
        if (obj == null) {
            throw new NullPointerException("obj == null");
        }

        occupant = obj;
    }
}
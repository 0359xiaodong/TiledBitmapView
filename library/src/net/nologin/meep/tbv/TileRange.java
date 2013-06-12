package net.nologin.meep.tbv;

// immutable!
public class TileRange {

    public final int left, top, right, bottom;

    public TileRange(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    public boolean contains(Tile t) {
        return contains(t,0);
    }
    
    public boolean contains(Tile t, int buffer) {

        return t != null && contains(t.xId, t.yId, buffer);

    }

    public boolean contains(int x, int y) {
        return contains(x,y,0);
    }

    public boolean contains(int x, int y, int buffer) {

        buffer = Math.max(0,buffer);

        return
                // check for empty first
                left < right && top < bottom
                        // then containment (inclusive of all boundaries, unlike android.graphics.Rect)
                        && x >= left - buffer
                        && x <= right + buffer
                        && y >= top - buffer
                        && y <= bottom + buffer;
    }


    public int numTilesHorizontal() {
        if (left > right) {
            return 0;
        }
        return Math.abs(right - left + 1);
    }

    public int numTilesVertical() {
        if (top > bottom) {
            return 0;
        }
        return Math.abs(bottom - top + 1);
    }

    public int numTiles() {
        return numTilesHorizontal() * numTilesVertical();
    }

    public String toString() {
        return String.format("TR[x=%d to %d,y=%d to %d,n=%d*%d=%d]", left, right, top, bottom, numTilesHorizontal(), numTilesVertical(), numTiles());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TileRange tileRange = (TileRange) o;

        return left == tileRange.left
                && top == tileRange.top
                && right == tileRange.right
                && bottom == tileRange.bottom;

    }

    @Override
    public int hashCode() {
        int result = left;
        result = 31 * result + top;
        result = 31 * result + right;
        result = 31 * result + bottom;
        return result;
    }

}

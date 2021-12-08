
public class Queen extends Piece {

	public Queen(Color color, String ID, int startX, int startY) {
		super(color, ID, startX, startY);
	}
    
	@Override
	public String toString() {
		if (this.getColor() == Color.WHITE) {
			return "♕";
		}
		return "♛";
	}

    /**
     * check all cases to see if is a possible move.
     * - check if not obstructed
     * - check cannot take own color
     * - can move in all 8 directions 
     */
	@Override
	public boolean possibleMove(int x, int y) {
		if (Board.isPathClear(getX(), getY(), x, y) != true) {
			return false;
		}
        if (this.sameColor(Board.getPiece(x, y)) == true) {
			return false;
		}
		if (Math.abs(getX() - x) == Math.abs(getY() - y)) { 
			return true;
		}
		if (Math.abs(getX() - x) != 0 && Math.abs(getY() - y) == 0
				|| Math.abs(getX() - x) == 0 && Math.abs(getY() - y) != 0) {
			return true;
		}
		return false;
	}

	@Override
    /**
     * implements the logic of deciding if a queen can move to
     * a given location
     */
	public boolean canMove() {
		int y = this.getY();
		int x = this.getX();

		// digonal moves 
		while ((--x) >= 0 && (--y) >= 0) {
			if (this.testMove(x, y)) {
				return true;
			}
		}
		x = this.getX();
		y = this.getY();
		while ((++x) <= 7 && (--y) >= 0) {
			if (this.testMove(x, y)) {
				return true;
			}
		}
		x = this.getX();
		y = this.getY();
		while ((--x) >= 0 && (++y) <= 7) {
			if (this.testMove(x, y)) {
				return true;
			}
		}
		x = this.getX();
		y = this.getY();
		while ((++x) <= 7 && (++y) <= 7) {
			if (this.testMove(x, y)) {
				return true;
			}
		}

        //vertical and horizontal
		x = this.getX();
		y = this.getY();
		while ((--x) >= 0 && y >= 0) {
			if (this.testMove(x, y)) {
				return true;
			}
		}
		x = this.getX();
		y = this.getY();
		while ((++x) <= 7 && y >= 0) {
			if (this.testMove(x, y)) {
				return true;
			}
		}
		x = this.getX();
		y = this.getY();
		while (x >= 0 && (++y) <= 7) {
			if (this.testMove(x, y)) {
				return true;
			}
		}
		x = this.getX();
		y = this.getY();
		while (x <= 7 && (--y) >= 0) {
			if (this.testMove(x, y)) {
				return true;
			}
		}
		return false;
	}

}
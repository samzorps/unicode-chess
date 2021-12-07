
public class Bishop extends Piece {

	public Bishop(Color color, String ID, int startX, int startY) {
		super(color, ID, startX, startY);
	}

	@Override
	public String toString() {
		if (this.getColor() == Color.WHITE) {
			return "♗";
		}
		return "♝";
	}

	@Override
	public boolean possibleMove(int x, int y) {
		// cannot conflict with same color piece
		if (this.sameColor(Board.getPiece(x, y)) == true) {
			return false;
		}
		// cannot move side to side 
		if (Math.abs(getX() - x) != Math.abs(getY() - y)) {
			return false;
		}

		if (Board.isPathClear(getX(), getY(), x, y)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean canMove() {
		int originX = this.getX();
		int originY = this.getY();
		int x = originX;
		int y = originY;
        /*
        Check all cases of illegal moves:
         going out of bounds on either side, top or bottom
         or conflicts
         */
		while ((--x) >= 0 && (--y) >= 0) {
			if (this.testMove(x, y)) {
				return true;
			}
		}
		x = originX;
		y = originY;
		while ((++x) <= 7 && (--y) >= 0) {
			if (this.testMove(x, y)) {
				return true;
			}
		}
		x = originX;
		y = originY;
		while ((--x) >= 0 && (++y) <= 7) {
			if (this.testMove(x, y)) {
				return true;
			}
		}
        /**
         * once we have checked all possible cases we may return true
         */
		x = originX;
		y = originY;
		while ((++x) <= 7 && (++y) <= 7) {
			if (this.testMove(x, y)) {
				return true;
			}
		}
		return false;
	}

}

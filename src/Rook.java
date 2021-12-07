
public class Rook extends Piece {

	public Rook(Color color, String ID, int startX, int startY) {
		super(color, ID, startX, startY);
	}

	boolean isFirstMove = true;

	@Override
	public boolean canMove() {
		int y = this.getY();
		int x = this.getX();

		//logic for left/right
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


		// logic for up/down
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

	/**
	 * Checks to see if it is possible to move this rook to
	 * square x, y
	 */
	@Override
	public boolean possibleMove(int x, int y) {
		if (this.sameColor(Board.getPiece(x, y)) == true) {
			return false;
		}
		if (Math.abs(getX() - x) != 0 && Math.abs(getY() - y) != 0) {
			return false;
		}

		if (Board.isPathClear(getX(), getY(), x, y)) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		if (this.getColor() == Color.WHITE) {
			return "♖";
		}
		return "♜";
	}


}
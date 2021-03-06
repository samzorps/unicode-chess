
public class Knight extends Piece {

	public Knight(Color color, String ID, int startX, int startY) {
		super(color, ID, startX, startY);
	}

	@Override
    /**
     * tells if you can move this knight to a given x, y location
     * checks to make sure you are not taking your own piece, 
     * and your path is not blocked 
     */
	public boolean possibleMove(int x, int y) {
		if (this.sameColor(Board.getPiece(x, y)) == true) {
			return false;
		}

		if (Math.abs(this.getY() - y) == 2 && Math.abs(this.getX() - x) == 1
				|| Math.abs(this.getY() - y) == 1 && Math.abs(this.getX() - x) == 2) {
			return true;
		}

		return false;
	}

	@Override
	public String toString() {
		if (this.getColor() == Color.WHITE) {
			return "♘";
		}
		return "♞";
	}

	@Override
	public boolean canMove() {

		int x = this.getX();
		int y = this.getY();

		if (this.testMove(x + 2, y - 1)) {
			return true;
		}
		if (this.testMove(x + 1, y - 2)) {
			return true;
		}

		if (this.testMove(x - 2, y - 1)) {
			return true;
		}
		if (this.testMove(x - 1, y - 2)) {
			return true;
		}


		if (this.testMove(x + 2, y + 1)) {
			return true;
		}
		if (this.testMove(x + 1, y + 2)) {
			return true;
		}

		if (this.testMove(x - 2, y + 1)) {
			return true;
		}
		if (this.testMove(x - 1, y + 2)) {
			return true;
		}

		return false;
	}

}

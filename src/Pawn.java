
public class Pawn extends Piece {

	boolean isFirstMove = true;

	public Pawn(Color color, String ID, int startX, int startY) {
		super(color, ID, startX, startY);
	}

	/**
	 * returns ascii representation of a pawn
	 */
	@Override
	public String toString() {
		if (this.getColor() == Color.WHITE) {
			return "♙";
		}
		else {
			return "♟";
		}
	}

	/***
	 * Determines if the pawn can move to (x, y) in a given move
	 */
	@Override
	public boolean possibleMove(int x, int y) {


		// If it is white, then look above!
		if (this.getColor() == Color.WHITE) {

			// If its the first move the pawn is making, you can move it two forward
			if (this.isFirstMove == true && this.getY() - y == 2 && this.getX() - x == 0
					&& Board.isPathClear(getX(), getY(), x, y) && Board.getPiece(x, y) == null) {
				return true;
			}
			// If its not the first move, can only move it one piece forward
			if (this.getY() - y == 1 && this.getX() - x == 0 && Board.getPiece(x, y) == null) {
				return true;
			}

			// If there is another piece to the diagonal entry of it, then can move it there
			if (this.getY() - y == 1 && Math.abs(this.getX() - x) == 1 && Board.getPiece(x, y) != null
					&& this.sameColor(Board.getPiece(x, y)) == false) {
				return true;
			}
		}

		// If it is black, look below!
		if (this.getColor() == Color.BLACK) {
			
			// If its the first move the pawn is making, you can move it two forward
			if (this.isFirstMove == true && this.getY() - y == -2 && this.getX() - x == 0
					&& Board.isPathClear(getX(), getY(), x, y) && Board.getPiece(x, y) == null) {
				return true;
			}
			// If its not the first move, can only move it one piece forward
			if (this.getY() - y == -1 && this.getX() - x == 0 && Board.getPiece(x, y) == null) {
				return true;
			}

			// If there is another piece to the diagonal entry of it, then can move it there
			if (this.getY() - y == -1 && Math.abs(this.getX() - x) == 1 && Board.getPiece(x, y) != null
					&& this.sameColor(Board.getPiece(x, y)) == false) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Determines if piece can move anywhere, used for determining if checkmate
	 */
	@Override
	public boolean canMove() {
		int x = this.getX();
		int y = this.getY();

		// if color is white, check corresponding possible spots
		if (this.getColor() == Color.WHITE) {

			if (this.testMove(x, y - 1)) {
				return true;
			}

			if (this.testMove(x, y - 2)) {
				return true;
			}

			if (this.testMove(x - 1, y - 1)) {
				return true;
			}

			if (this.testMove(x + 1, y - 1)) {
				return true;
			}

		}

		// if color is black, check corresponding possible spots
		if (this.getColor() == Color.BLACK) {

			if (this.testMove(x, y + 1)) {
				return true;
			}

			if (this.testMove(x, y + 2)) {
				return true;
			}

			if (this.testMove(x - 1, y - 1)) {
				return true;
			}

			if (this.testMove(x + 1, y + 1)) {
				return true;
			}
		}

		// if cant move in any of the corresponding spots, return false
		return false;
	}
}
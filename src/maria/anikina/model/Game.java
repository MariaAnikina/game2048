package maria.anikina.model;

import maria.anikina.service.Direction;

public interface Game {
	void init();

	boolean canMove();

	boolean move(Direction direction);

	void addItem();

	Board getGameBoard();

	boolean hasWin();
}

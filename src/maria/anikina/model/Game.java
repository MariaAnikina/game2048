package maria.anikina.model;

import maria.anikina.exception.NotEnoughSpace;
import maria.anikina.service.Direction;

public interface Game {
	void init();

	boolean canMove();

	boolean move(Direction direction);

	void addItem() throws NotEnoughSpace;

	Board getGameBoard();

	boolean hasWin();
}

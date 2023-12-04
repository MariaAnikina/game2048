package maria.anikina.service;

import maria.anikina.model.Direction;
import maria.anikina.service.Board;

public interface Game {
	void init();
	boolean canMove();
	void move(Direction direction);
	void addItem();
	Board getGameBoard();
	boolean hasWin();
}

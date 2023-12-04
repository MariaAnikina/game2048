package maria.anikina.service;

import maria.anikina.model.Direction;
import maria.anikina.model.Key;

import java.util.Collection;
import java.util.List;
import java.util.Random;

public class Game2048 implements Game {
	GameHelper helper = new GameHelper();
	Board board;
	Random random = new Random();

	public Game2048(Board board) {
		this.board = board;
	}

	@Override
	public void init() {
		System.out.println("Игра началась!");
	}

	@Override
	public boolean canMove() {
		Collection<Integer> list = board.getBoard().values();
		return list.contains(null);
	}

	@Override
	public void move(Direction direction) {

	}

	@Override
	public void addItem() {
		int row = -1;
		int column = -1;
		Integer element = 0;
		while (element != null) {
			row = random.nextInt(board.getHeight());
			column = random.nextInt(board.getWidth());
			element = board.getValue(new Key(row, column));
		}
		board.getBoard().put(new Key(row, column), 2);
	}

	@Override
	public Board getGameBoard() {
		return board;
	}

	@Override
	public boolean hasWin() {
		return board.getBoard().containsValue(2048);
	}
}

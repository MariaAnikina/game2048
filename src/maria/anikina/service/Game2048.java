package maria.anikina.service;

import maria.anikina.model.Board;
import maria.anikina.model.Game;

import java.util.*;
import java.util.stream.Collectors;

public class Game2048 implements Game {
	public final double FIELD_OCCUPANCY_RATIO_TO_START_THE_GAME = 0.2;
	GameHelper helper = new GameHelper();
	Board board;
	Random random = new Random();

	public Game2048(Board board) {
		this.board = board;
	}

	@Override
	public void init() {
		int listLength = board.getHeight() * board.getWidth();
		List<Integer> listNull = new ArrayList<>();
		for (int i = 0; i < listLength; i++) {
			listNull.add(null);
		}
		board.fillBoard(listNull);
		int fieldFullness = (int) (listLength * FIELD_OCCUPANCY_RATIO_TO_START_THE_GAME);
		for (int i = 0; i < fieldFullness; i++) {
			addItem();
		}
		System.out.println("Игра началась!");
	}

	@Override
	public boolean canMove() {
		Collection<Integer> list = board.getBoard().values();
		return list.contains(null);
	}

	@Override
	public void move(Direction direction) {
		if (canMove()) {
			switch (direction) {
				case LEFT:
					moveLeft();
					break;
				case RIGHT:
					moveRight();
					break;
				case UP:
					moveUp();
					break;
				case DOWN:
					moveDown();
					break;
			}
		}
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
		board.getBoard().put(board.getKey(row, column), 2);
	}

	@Override
	public Board getGameBoard() {
		return board;
	}

	@Override
	public boolean hasWin() {
		return board.getBoard().containsValue(2048);
	}

	private void moveLeft() {
		for (int i = 0; i < board.getWidth(); i++) {
			List<Integer> list = helper.moveAndMergeEqual(
					board.getRow(i).stream()
							.map(s -> board.getValue(s))
							.collect(Collectors.toList())
			);
			for (int j = 0; j < list.size(); j++) {
				board.getBoard().put(board.getKey(i, j), list.get(j));
			}
		}
	}

	private void moveRight() {
		for (int i = 0; i < board.getWidth(); i++) {
			List<Integer> list = board.getRow(i).stream()
					.map(s -> board.getValue(s))
					.collect(Collectors.toList());
			Collections.reverse(list);
			List<Integer> listReverse = helper.moveAndMergeEqual(list);
			Collections.reverse(listReverse);
			for (int j = 0; j < listReverse.size(); j++) {
				board.getBoard().put(board.getKey(i, j), listReverse.get(j));
			}
		}
	}

	private void moveUp() {
		for (int j = 0; j < board.getWidth(); j++) {
			List<Integer> list = helper.moveAndMergeEqual(
					board.getColumn(j).stream()
							.map(s -> board.getValue(s))
							.collect(Collectors.toList())
			);
			for (int i = 0; i < list.size(); i++) {
				board.getBoard().put(board.getKey(i, j), list.get(i));
			}
		}
	}

	private void moveDown() {
		for (int j = 0; j < board.getWidth(); j++) {
			List<Integer> list = board.getColumn(j).stream()
					.map(s -> board.getValue(s))
					.collect(Collectors.toList());
			Collections.reverse(list);
			List<Integer> listReverse = helper.moveAndMergeEqual(list);
			Collections.reverse(listReverse);
			for (int i = 0; i < listReverse.size(); i++) {
				board.getBoard().put(board.getKey(i, j), listReverse.get(i));
			}
		}
	}
}

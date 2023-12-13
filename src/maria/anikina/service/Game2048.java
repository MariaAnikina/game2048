package maria.anikina.service;

import maria.anikina.exception.NotEnoughSpace;
import maria.anikina.model.Board;
import maria.anikina.model.Game;

import java.util.*;
import java.util.stream.Collectors;

public class Game2048 implements Game {
	public final double FIELD_OCCUPANCY_RATIO_TO_START_THE_GAME = 0.125;
	public static final int GAME_SIZE = 4;
	private Board<Key, Integer> board = new SquareBoard<>(GAME_SIZE);
	private GameHelper helper = new GameHelper();
	private Random random = new Random();

	public Game2048(Board<Key, Integer> board) {
		this.board = board;
	}

	public Game2048() {
	}

	@Override
	public void init() {
		try {
			int listLength = GAME_SIZE * GAME_SIZE;
			List<Integer> listNull = new ArrayList<>();
			for (int i = 0; i < listLength; i++) {
				listNull.add(null);
			}
			board.fillBoard(listNull);
			int fieldFullness = (int) (listLength * FIELD_OCCUPANCY_RATIO_TO_START_THE_GAME);
			for (int i = 0; i < fieldFullness; i++) {
				addItem();
			}
		} catch (NotEnoughSpace e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean canMove() {
		Collection<Integer> list = board.getBoard().values();
		if (list.contains(null)) {
			return true;
		}
		for (int i = 0; i < board.getHeight(); i++) {
			for (int j = 0; j < board.getWidth() - 1; j++) {
				if (i == 0) {
					if (board.getValue(board.getKey(i, j)).equals(board.getValue(board.getKey(i, j + 1)))) {
						return true;
					}
					if (board.getValue(board.getKey(i, j)).equals(board.getValue(board.getKey(i + 1, j)))) {
						return true;
					}
				}
				if (i > 0 && i < board.getHeight() - 1) {
					if (board.getValue(board.getKey(i, j)).equals(board.getValue(board.getKey(i - 1, j)))) {
						return true;
					}
					if (board.getValue(board.getKey(i, j)).equals(board.getValue(board.getKey(i, j + 1)))) {
						return true;
					}
					if (board.getValue(board.getKey(i, j)).equals(board.getValue(board.getKey(i + 1, j)))) {
						return true;
					}
				}
				if (i == board.getHeight() - 1) {
					if (board.getValue(board.getKey(i, j)).equals(board.getValue(board.getKey(i, j + 1)))) {
						return true;
					}
					if (board.getValue(board.getKey(i, j)).equals(board.getValue(board.getKey(i - 1, j)))) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean move(Direction direction) {
		boolean isMoveMade = false;
		try {
			if (canMove()) {
				switch (direction) {
					case LEFT:
						moveLeft();
						addItem();
						break;
					case RIGHT:
						moveRight();
						addItem();
						break;
					case UP:
						moveUp();
						addItem();
						break;
					case DOWN:
						moveDown();
						addItem();
						break;
				}
				isMoveMade = true;
			}
		} catch (NotEnoughSpace e) {
			e.printStackTrace();
		}
		return isMoveMade;
	}

	@Override
	public void addItem() throws NotEnoughSpace {
		if (!board.getBoard().containsValue(null)) {
			throw new NotEnoughSpace("Нет свободного места");
		}
		List<Key> nullValueItemKeys = board.getBoard().keySet().stream()
				.filter(key -> board.getBoard().get(key) == null).collect(Collectors.toList());
		if (nullValueItemKeys.size() == 0) {
			return;
		}
		int randomKey = random.nextInt(nullValueItemKeys.size());
		int chance = random.nextInt(10);
		if (chance > 7) {
			board.getBoard().put(nullValueItemKeys.get(randomKey), 4);
		} else {
			board.getBoard().put(nullValueItemKeys.get(randomKey), 2);
		}
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

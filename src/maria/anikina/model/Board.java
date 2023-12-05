package maria.anikina.model;

import maria.anikina.service.Key;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Board {
	private int width;
	private int height;
	private Map<Key, Integer> board = new HashMap<>();

	public Board(int weigh, int height) {
		this.width = weigh;
		this.height = height;
	}

	public Board() {
	}

	public abstract void fillBoard(List<Integer> list);

	public abstract List<Key> availableSpace();

	public abstract void addItem(Key key, Integer value);

	public abstract Key getKey(int i, int j);

	public abstract Integer getValue(Key key);

	public abstract List<Key> getColumn(int j);

	public abstract List<Key> getRow(int i);

	public abstract boolean hasValue(Integer value);

	public abstract List<Integer> getValues(List<Key> keys);

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Map<Key, Integer> getBoard() {
		return board;
	}

	public void setBoard(Map<Key, Integer> board) {
		this.board = board;
	}
}

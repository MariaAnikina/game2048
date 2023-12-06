package maria.anikina.model;

import maria.anikina.service.Key;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Board<T, V> {
	private int width;
	private int height;
	private Map<T, V> board = new HashMap<>();

	public Board(int weigh, int height) {
		this.width = weigh;
		this.height = height;
	}

	public Board() {
	}

	public abstract void fillBoard(List<V> list);

	public abstract List<T> availableSpace();

	public abstract void addItem(T key, V value);

	public abstract T getKey(int i, int j);

	public abstract V getValue(T key);

	public abstract List<T> getColumn(int j);

	public abstract List<T> getRow(int i);

	public abstract boolean hasValue(V value);

	public abstract List<V> getValues(List<T> keys);

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

	public Map<T, V> getBoard() {
		return board;
	}

	public void setBoard(Map<T, V> board) {
		this.board = board;
	}
}

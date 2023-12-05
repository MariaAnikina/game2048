package maria.anikina.service;

import maria.anikina.model.Board;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SquareBoard extends Board {

	private int size;

	public SquareBoard(int size) {
		super(size, size);
		this.size = size;
	}

	@Override
	public void fillBoard(List<Integer> list) {
		getBoard().clear();
		int index = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				getBoard().put(new Key(i, j), list.get(index));
				index++;
			}
		}
	}

	@Override
	public List<Key> availableSpace() {
		return getBoard().keySet().stream()
				.filter(s -> getBoard().get(s) == null)
				.collect(Collectors.toList());
	}

	@Override
	public void addItem(Key key, Integer value) {
		getBoard().put(key, value);
	}

	@Override
	public Key getKey(int i, int j) {
		return getBoard().keySet().stream()
				.filter(s -> (s.getI() == i && s.getJ() == j))
				.findFirst()
				.orElse(null);

	}

	@Override
	public Integer getValue(Key key) {
		return getBoard().get(key);
	}

	@Override
	public List<Key> getColumn(int j) {
		return getBoard().keySet().stream()
				.filter(s -> s.getJ() == j)
				.sorted(Comparator.comparing(Key::getI))
				.collect(Collectors.toList());
	}

	@Override
	public List<Key> getRow(int i) {
		return getBoard().keySet().stream()
				.filter(s -> s.getI() == i)
				.sorted(Comparator.comparing(Key::getJ))
				.collect(Collectors.toList());
	}

	@Override
	public boolean hasValue(Integer value) {
		return getBoard().containsValue(value);
	}

	@Override
	public List<Integer> getValues(List<Key> keys) {
		return getBoard().keySet().stream()
				.filter(keys::contains)
				.map(s -> getBoard().get(s))
				.collect(Collectors.toList());
	}
}

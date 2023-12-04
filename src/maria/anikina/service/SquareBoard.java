package maria.anikina.service;

import maria.anikina.model.Key;
import maria.anikina.service.Board;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SquareBoard extends Board {

	private int size;

	public SquareBoard(int size) {
		this.size = size;
		super.setHeight(size);
		super.setWidth(size);
	}

	@Override
	public void fillBoard(List<Integer> list) {
		super.getBoard().clear();
		int c = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				super.getBoard().put(new Key(i, j), list.get(c));
				c++;
			}
		}
	}

	@Override
	public List<Key> availableSpace() {
		return super.getBoard().keySet().stream()
				.filter(s -> super.getBoard().get(s) == null)
				.collect(Collectors.toList());
	}

	@Override
	public void addItem(Key key, Integer value) {
		super.getBoard().put(key, value);
	}

	@Override
	public Key getKey(int i, int j) {
		Optional<Key> keyOptional = super.getBoard().keySet().stream()
				.filter(s -> (s.getI() == i && s.getJ() == j))
				.findFirst();
		return keyOptional.orElse(null);
	}

	@Override
	public Integer getValue(Key key) {
		return super.getBoard().get(key);
	}

	@Override
	public List<Key> getColumn(int j) {
		return  super.getBoard().keySet().stream()
				.filter(s -> s.getJ() == j)
				.collect(Collectors.toList());
	}

	@Override
	public List<Key> getRow(int i) {
		return super.getBoard().keySet().stream()
				.filter(s -> s.getI() == i)
				.collect(Collectors.toList());
	}

	@Override
	public boolean hasValue(Integer value) {
		return super.getBoard().containsValue(value);
	}

	@Override
	public List<Integer> getValues(List<Key> keys) {
		return super.getBoard().keySet().stream()
				.filter(keys::contains)
				.map(s -> super.getBoard().get(s))
				.collect(Collectors.toList());
	}
}

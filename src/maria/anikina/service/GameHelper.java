package maria.anikina.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GameHelper {
	public List<Integer> moveAndMergeEqual(List<Integer> list) {
		if (list.isEmpty()) {
			return list;
		}
		if (list.contains(null)) {
			int index = list.indexOf(null);
			List<Integer> sublist = new ArrayList<>(list.subList(index, list.size()));
			List<Integer> sublistNotNull = sublist.stream()
					.filter(Objects::nonNull)
					.collect(Collectors.toList());
			for (int i = 0; i < sublistNotNull.size(); i++) {
				list.set(index + i, sublistNotNull.get(i));
			}
			for (int i = index + sublistNotNull.size(); i < list.size(); i++) {
				list.set(i, null);
			}
		}
		int listSize = list.size();
		for (int i = 1; i < listSize; i++) {
			if (list.get(i) != null && list.get(i - 1) != null && list.get(i).equals(list.get(i - 1))) {
				list.set(i - 1, list.get(i - 1) * 2);
				list.remove(i);
				list.add(null);

			}
		}
		return list;
	}
}

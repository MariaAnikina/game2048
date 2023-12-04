package maria.anikina.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GameHelper {
	public List<Integer> moveAndMergeEqual(List<Integer> list) {
		System.out.println(list);
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
				list.set(index + sublistNotNull.size() + i, null);
			}
			for (int i = index + sublistNotNull.size(); i < list.size(); i++) {
				list.set(i, null);
			}
		}
		int listSize = list.size();
		for (int i = 1; i < listSize; i++) {
			if (list.get(i) != null && list.get(i - 1) != null && list.get(i).equals(list.get(i - 1))) {
				list.set(i, list.get(i) * 2);
				System.out.println(list);
				list.remove(i - 1);
				list.add(null);

			}
		}
		return list;
	}
}

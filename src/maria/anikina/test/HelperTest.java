package maria.anikina.test;

import maria.anikina.service.GameHelper;
import org.junit.Assert;

import java.util.ArrayList;

import static java.util.Arrays.asList;

public class HelperTest {
	private final static GameHelper helper = new GameHelper();

	public static void main(String[] args) {
		Assert.assertEquals(helper.moveAndMergeEqual(new ArrayList<>(asList(1, 2, null, 3))), asList(1, 2, 3, null));
		Assert.assertEquals(helper.moveAndMergeEqual(new ArrayList<>(asList(2, 2, null, 3))), asList(4, 3, null, null));
		Assert.assertEquals(helper.moveAndMergeEqual(new ArrayList<>(asList(2, 2, 4, 4))), asList(4, 8, null, null));
		Assert.assertEquals(helper.moveAndMergeEqual(new ArrayList<>(asList(2, 2, 2, 3))), asList(4, 2, 3, null));
		Assert.assertEquals(helper.moveAndMergeEqual(new ArrayList<>(asList(2, null, null, 2))), asList(4, null, null, null));
		Assert.assertEquals(helper.moveAndMergeEqual(new ArrayList<>(asList(null, null, null, null))), asList(null, null, null, null));
		Assert.assertEquals(helper.moveAndMergeEqual(new ArrayList<>(asList(null, null, null, 2))), asList(2, null, null, null));
		Assert.assertEquals(helper.moveAndMergeEqual(new ArrayList<>(asList(null, null, 2, 2))), asList(4, null, null, null));
	}
}

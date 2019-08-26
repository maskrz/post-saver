package saver.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import saver.service.OperationRuntimeException;
import saver.utils.TestUtils;

public class JsonPostConverterTest {

	private JsonPostConverter converter;

	@Before
	public void setUp() {
		this.converter = new JsonPostConverter();
	}

	@Test(expected = OperationRuntimeException.class)
	public void test_nullData() {
		converter.convertData(null);
	}

	@Test(expected = OperationRuntimeException.class)
	public void test_emptyData() {
		converter.convertData("");
	}

	@Test(expected = OperationRuntimeException.class)
	public void test_invalidData() {
		converter.convertData("some unparseable data");
	}

	@Test
	public void test_singleElement() {
		// given
		String resource = TestUtils.getResourceContent("singlePost.txt", this.getClass());
		String expected = TestUtils
				.normalizeEOL(TestUtils.getResourceContent("singlePostExpectedResult.txt", this.getClass()));

		// when
		Map<String, String> parsedResource = converter.convertData(resource);

		// then
		assertEquals(1, parsedResource.size());
		assertTrue(parsedResource.containsKey("3"));
		assertEquals(expected, parsedResource.get("3"));
	}

	@Test
	public void test_multipleElements() {
		// given
		String resource = TestUtils.getResourceContent("multiplePosts.txt", this.getClass());
		String expected1 = TestUtils
				.normalizeEOL(TestUtils.getResourceContent("multiplePostsExpectedResult1.txt", this.getClass()));
		String expected2 = TestUtils
				.normalizeEOL(TestUtils.getResourceContent("multiplePostsExpectedResult2.txt", this.getClass()));

		// when
		Map<String, String> parsedResource = converter.convertData(resource);

		// then
		assertEquals(2, parsedResource.size());
		assertTrue(parsedResource.containsKey("3"));
		assertTrue(parsedResource.containsKey("4"));
		assertEquals(expected1, parsedResource.get("3"));
		assertEquals(expected2, parsedResource.get("4"));
	}
}

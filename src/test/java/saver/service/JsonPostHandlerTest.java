package saver.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import saver.converter.JsonPostConverter;
import saver.downloader.JsonPostDownloader;
import saver.saver.JsonPostSaver;

public class JsonPostHandlerTest {

	private JsonPostHandler jsonPostHandler;
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Captor
	ArgumentCaptor<Map<String, String>> resultCaptor;

	@Before
	public void setUp() {
		this.jsonPostHandler = new JsonPostHandlerMock();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test_simpleFlowTest() {
		// given
		JsonPostDownloader downloader = mock(JsonPostDownloader.class);
		when(downloader.downloadData(Mockito.anyString()))
				.thenReturn("not important");
		JsonPostConverter converter = mock(JsonPostConverter.class);
		Map<String, String> expectedMap = new HashMap<String, String>();
		expectedMap.put("1", "not important");
		when(converter.convertData(Mockito.anyString())).thenReturn(expectedMap);
		JsonPostSaver saver = mock(JsonPostSaver.class);
		doNothing().when(saver).saveData(resultCaptor.capture(), Mockito.anyString());
		jsonPostHandler.dataDownloader = downloader;
		jsonPostHandler.dataConverter = converter;
		jsonPostHandler.dataSaver = saver;
		
		
		// when
		jsonPostHandler.hanldeAction();
		verify(saver).saveData(resultCaptor.capture(), Mockito.anyString());

		// then
		Map<String, String> resultMap = resultCaptor.getValue();
		assertEquals(1, resultMap.size());
		assertTrue(resultMap.containsKey("1"));
		assertEquals("not important", resultMap.get("1"));
	}

	@Test(expected=OperationRuntimeException.class)
	public void test_exceptionFlow() {
		// given
		JsonPostDownloader downloader = mock(JsonPostDownloader.class);
		when(downloader.downloadData(Mockito.anyString()))
				.thenThrow(new OperationRuntimeException("exception"));
		JsonPostConverter converter = mock(JsonPostConverter.class);
		when(converter.convertData(Mockito.anyString())).thenReturn(new HashMap<String, String>());
		jsonPostHandler.dataDownloader = downloader;
		jsonPostHandler.dataConverter = converter;
		
		
		// when
		jsonPostHandler.hanldeAction();
		
		//then
		verify(downloader, never()).downloadData(Mockito.anyString());
		expectedEx.expect(OperationRuntimeException.class);

	}
	
	private class JsonPostHandlerMock extends JsonPostHandler {
		@Override
		protected void setupConverter() {
		}
	}
}

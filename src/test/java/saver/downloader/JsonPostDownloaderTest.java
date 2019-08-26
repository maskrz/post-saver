package saver.downloader;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import mockit.Mock;
import mockit.MockUp;
import mockit.integration.junit4.JMockit;
import saver.service.OperationRuntimeException;

@RunWith(JMockit.class)
public class JsonPostDownloaderTest {

	private JsonPostDownloader downloader;
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Before
	public void setUp() {
		this.downloader = new JsonPostDownloader();
	}
	
	@Test
	public void test_nullData() throws IOException {
        //given
		String jsonResponse = "json response";
		new MockUp<IOUtils>() {
	        @Mock
	        public String toString(final URL url, final String encoding) throws IOException {
	            return jsonResponse;
	        }
	    };
	    
	    //when
		String result = downloader.downloadData("https://jsonplaceholder.typicode.com/posts");
		
		//then
		assertEquals(jsonResponse, result);
	}
	
	@Test
	public void test_invalidUrl() throws IOException {
		String sUrl = "invalid url (no protocol)";
		expectedEx.expect(OperationRuntimeException.class);
	    expectedEx.expectCause(new CauseMatcher(MalformedURLException.class, "no protocol: " + sUrl));
		downloader.downloadData(sUrl);
	}
	
	private static class CauseMatcher extends TypeSafeMatcher<Throwable> {
	    private final Class<? extends Throwable> type;
	    private final String expectedMessage;
	  
	    public CauseMatcher(Class<? extends Throwable> type, String expectedMessage) {
	        this.type = type;
	        this.expectedMessage = expectedMessage;
	    }
	  
	    @Override
	    protected boolean matchesSafely(Throwable item) {
	        return item.getClass().isAssignableFrom(type)
	                && item.getMessage().contains(expectedMessage);
	    }
	  
	    @Override
	    public void describeTo(Description description) {
	        description.appendText("expects type ")
	                .appendValue(type)
	                .appendText(" and a message ")
	                .appendValue(expectedMessage);
	    }
	}

}

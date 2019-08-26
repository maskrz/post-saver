package saver.utils;

import java.io.IOException;

import org.apache.commons.io.IOUtils;

public class TestUtils {


	public static String getResourceContent(String fileName, Class<?> clazz) {
		try {
			return IOUtils.toString(clazz.getResourceAsStream(fileName), SaverStatics.ENCODING_UTF8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String normalizeEOL(String fileString) {
		return fileString.replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n");
	}
}

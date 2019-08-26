package saver.downloader;

import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.IOUtils;

import saver.service.OperationRuntimeException;
import saver.utils.SaverStatics;

public class JsonPostDownloader implements DataDownloader {

	@Override
	public String downloadData(String sURL) {
		try {
			URL url = new URL(sURL);
			return IOUtils.toString(url, SaverStatics.ENCODING_UTF8);
		} catch (IOException ex) {
			throw new OperationRuntimeException("Exception during data downloading: ", ex);
		}
	}

}

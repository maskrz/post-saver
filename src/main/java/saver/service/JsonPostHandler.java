package saver.service;

import java.util.Map;

import saver.converter.JsonPostConverter;
import saver.downloader.JsonPostDownloader;
import saver.saver.JsonPostSaver;

public class JsonPostHandler extends DataHandlerTemplate {

	private final String url = "https://jsonplaceholder.typicode.com/posts";

	private static final String path = "./savedPosts/";

	private String plainPostsData;
	
	private Map<String, String> posts; 

	@Override
	protected void setupConverter() {
		dataDownloader = new JsonPostDownloader();
		dataConverter = new JsonPostConverter();
		dataSaver = new JsonPostSaver();
	}

	@Override
	protected void downloadData() {
		plainPostsData = dataDownloader.downloadData(url);
	}

	@Override
	protected void convertData() {
		posts = dataConverter.convertData(plainPostsData);
	}

	@Override
	protected void saveData() {
		dataSaver.saveData(posts, path);
	}
}

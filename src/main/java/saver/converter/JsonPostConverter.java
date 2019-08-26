package saver.converter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import saver.bean.Post;
import saver.service.OperationRuntimeException;

public class JsonPostConverter implements DataConverter {

	private Gson gson = new GsonBuilder().setPrettyPrinting().create();

	@Override
	public Map<String, String> convertData(String plainData) {
		if (plainData == null || plainData.isEmpty()) {
			throw new OperationRuntimeException("Empty text to parse.");
		}
		try {
			return convertPostsToStringList(convertPlainDataToPosts(plainData));
		} catch (JsonSyntaxException ex) {
			throw new OperationRuntimeException("Unable to parse data.");
		}
	}

	private List<Post> convertPlainDataToPosts(String plainData) {
		return Arrays.asList(gson.fromJson(plainData, Post[].class));
	}

	private Map<String, String> convertPostsToStringList(List<Post> posts) {
		return posts.stream().collect(Collectors.toMap(e -> String.valueOf(e.getId()), e -> gson.toJson(e)));
	}

}

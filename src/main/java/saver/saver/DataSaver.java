package saver.saver;

import java.util.Map;

public interface DataSaver {

	public void saveData(Map<String, String> data, String saveFolderPath);
}

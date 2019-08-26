package saver.saver;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import saver.service.OperationRuntimeException;
import saver.utils.SaverStatics;

public class JsonPostSaver implements DataSaver {

	@Override
	public void saveData(Map<String, String> data, String saveFolderPath) {
		for (String key : data.keySet()) {
			saveFile(saveFolderPath + key + SaverStatics.JSON_FILE_SUFFIX, data.get(key));
		}
	}

	private void saveFile(String fileCompletePath, String content) {
		File file = new File(fileCompletePath);
		try {
			FileUtils.writeStringToFile(file, content, SaverStatics.ENCODING_UTF8);
		} catch (IOException ex) {
			throw new OperationRuntimeException("Exception during data saving: ", ex);
		}
	}

}

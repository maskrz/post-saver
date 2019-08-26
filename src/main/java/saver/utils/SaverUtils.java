package saver.utils;

import saver.service.DataHandlerTemplate;
import saver.service.InvalidActionTypeException;
import saver.service.JsonPostHandler;

public class SaverUtils {

	public static DataHandlerTemplate getDataHandler(ACTION_TYPE actionType) {
		switch (actionType) {
		case JSON_POSTS_DOWNLOAD_SAVE:
			return new JsonPostHandler();
		default:
			throw new InvalidActionTypeException("Cannot obtain handler for action type: " + actionType);
		}
	}
}

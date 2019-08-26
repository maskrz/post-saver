package saver.service;

import saver.utils.ACTION_TYPE;
import saver.utils.SaverUtils;

public class PostSaverFacadeImpl implements PostSaverFacade {

	public void downloandAndSaveJsonPosts() {
		downloandAndSaveJsonPosts(ACTION_TYPE.JSON_POSTS_DOWNLOAD_SAVE);
	}

	public void downloandAndSaveJsonPosts(ACTION_TYPE actionType) {
		SaverUtils.getDataHandler(actionType).hanldeAction();
	}

}

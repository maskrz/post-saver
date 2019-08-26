package saver;

import saver.service.PostSaverFacade;
import saver.service.PostSaverFacadeImpl;

public class Main {

	public static void main(String[] args) {
		PostSaverFacade postSaverFacade = new PostSaverFacadeImpl();
		postSaverFacade.downloandAndSaveJsonPosts();
	}

}

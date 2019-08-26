package saver.service;

import saver.converter.DataConverter;
import saver.downloader.DataDownloader;
import saver.saver.DataSaver;

public abstract class DataHandlerTemplate {

	protected DataDownloader dataDownloader;
	
	protected DataConverter dataConverter;
	
	protected DataSaver dataSaver;
	
	/*
	 * implemented as template because some parts could potentially come with default/common implementation
	 * more download types necessary to implement common parts
	 */
	public void hanldeAction() {
		try {
			setupConverter();
			downloadData();
			convertData();
			saveData();
		} catch (OperationRuntimeException ex) {
			// Depending on project/framework/business requirements I would handle exception here:
			// - log with log4j and/or
			// - save to the database and/or
			// - rethrow it
			throw ex;
		}
	}
	
	protected abstract void setupConverter();
	
	protected abstract void downloadData();
	
	protected abstract void convertData();
	
	protected abstract void saveData();
}

package finalProject;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {
	
	private static Logger logger;
	
	public static Logger getLogger(String fileName) throws SecurityException, IOException {
		if(logger == null) {
			fileName = "C:\\users\\40740\\Desktop\\Work\\Files\\LoginUsersLogging.txt";
			File f = new File(fileName);
			if(! f.exists()) {
				f.createNewFile();
			}
			FileHandler fh = new FileHandler(fileName, true);
			logger = Logger.getLogger("UsersLoginApp");
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
		}
		return logger;
	}
	
	private Log (String fileName) {
		
	}
	
}

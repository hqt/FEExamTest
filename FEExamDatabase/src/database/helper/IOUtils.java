package database.helper;

import java.io.File;
import java.io.IOException;

import com.google.common.io.Files;

public class IOUtils {
	public static byte[] getBinaryFromFile(String pathname) {
		File file = new File(pathname);
		try {
			return Files.toByteArray(file);
		} catch (IOException e) {
			return null;
		}
	}

}

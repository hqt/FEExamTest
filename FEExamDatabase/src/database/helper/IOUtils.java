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
			return new byte[0];
		}
	}
	
	/*public static void ConvertBinaryToImage(Byte[] bytearray) {
		bytearray = Base64.decode(base64String);
		 
		BufferedImage imag=ImageIO.read(new ByteArrayInputStream(bytearray));
		ImageIO.write(imag, "jpg", new File(dirName,"snap.jpg"));
	}*/
	
}

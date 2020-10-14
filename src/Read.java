import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Read {
	//Le um file dentro da pasta res
public static byte[] getBytes(String path) {
	String s=System.getProperty("user.dir")+"/res/"+path;
	File file = new File(s);
	
		byte[] array=null;
		try {
			array = Files.readAllBytes(file.toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return array;
}
}

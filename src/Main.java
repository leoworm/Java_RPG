import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) {
		boolean isGameRunning = true;
		

		System.out.println(parseMap("temp.txt").get(1).get(1));
		

	}
	public static List<List<String>> parseMap(String fileName){
		List<List<String>> map = new ArrayList<List<String>>();
		
		//Reading from a File...
		  
		try {
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			boolean eof = false;
			
			while(eof == false) {
				String line = br.readLine();
				if(line == null) {
					eof = true;
				}else {
					map.add(Arrays.asList(line.split(",")));
				}
			}
			
			br.close();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		return map;
	}
}

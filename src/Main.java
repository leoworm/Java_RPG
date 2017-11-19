import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) {
		boolean isGameRunning = true;
		

		System.out.println(parseMap("map1.map").get(0).get(3));
		

	}
	public static List<List<String>> parseMap(String fileName){
		List<List<String>> map = new ArrayList<List<String>>();
		
		//Reading from map File...
		  
		try {
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			boolean eof = false;
			br.readLine();
			while(eof == false) {
				String line = br.readLine();
				if(line == null) {
					eof = true;
				}else {
					String ready = line.replaceAll("\\s+","");
					map.add(Arrays.asList(ready.split(",")));
				}
			}
			
			br.close();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		return map;
	}
}

import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.GridLayout;

//TODO: add a scale argument to the toAscii method

public class Main {

	public static void main(String[] args) {
		boolean isGameRunning = true;
		drawMap(parseMap("map1.map"));
		//System.exit(0);

		

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
	
	public static String getRowContent(List<List<String>> map, Integer row) {
		String output = "";
		for(int i = 0; i < map.get(row).size(); i++) {
			output = output + map.get(row).get(i);
		}
		return output;
	}
	
	public static void drawMap(List<List<String>> map) {

		JPanel panel = new JPanel(new GridLayout(map.size(), map.get(0).size(), 0, 0));

		for(int i = 0; i < map.size(); i++) {
			for(int j = 0; j < map.get(i).size(); j++) {
				char current = toAscii(map, i).charAt(j);
				panel.add(new JLabel(Character.toString(current)));
			}
		}
		
		JFrame frame = new JFrame();
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static String toAscii(List<List<String>> map, Integer row) {
		String result = "";
		for(int i = 0; i < map.get(row).size(); i++) {
			String current = map.get(row).get(i);
			switch(current) {
			case "wall":
				result = result + "#";
				break;
			case "floor":
				result = result + "·";
				break;
				
			}
		}
		return result;
	}
}

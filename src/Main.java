import java.util.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

//TODO: add a scale argument to the toAscii method
//TODO: switch to a map class

public class Main {
	static HashMap<String, Integer> initialPlayerPos = new HashMap<String, Integer>();
	public static void main(String[] args) {
		boolean isGameRunning = true;
		drawMap(parseMap("map1.map"));
		//System.exit(0);
		System.out.println(initialPlayerPos.get("x"));
		System.out.println(initialPlayerPos.get("y"));
		

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
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		for(int i = 0; i < map.size(); i++) {
			for(int j = 0; j < map.get(i).size(); j++) {
				char current = toAscii(map, i).charAt(j);
				String currentString = Character.toString(current);
				if("¤".equals(currentString)) {
					JLabel temp = new JLabel("<html><FONT COLOR=WHITE>" + Character.toString(current) + "</FONT></html>");
					temp.setFont(new Font(temp.getFont().getName(), Font.PLAIN, (int)width / 130));
					panel.add(temp);
				}else if(" ".equals(currentString)) {
					JLabel temp = new JLabel("<html><FONT COLOR=GRAY>" + Character.toString(current) + "</FONT></html>");
					temp.setFont(new Font(temp.getFont().getName(), Font.PLAIN, (int)width / 50));
					panel.add(temp);
				}else if("@".equals(currentString)) {
					initialPlayerPos.put("x", j);
					initialPlayerPos.put("y", i);
					JLabel temp = new JLabel("<html><FONT COLOR=GREEN>" + Character.toString(current) + "</FONT></html>");
					temp.setFont(new Font(temp.getFont().getName(), Font.PLAIN, (int)width / 200));
					panel.add(temp);
				}
				
				
				
			}
		}
		panel.setPreferredSize(new Dimension(500, 500));
		panel.setBorder(BorderFactory.createEmptyBorder(map.size()*10, map.get(0).size()*23, map.size()*10, map.get(0).size()*23));
		JFrame frame = new JFrame();
		panel.setBackground(Color.BLACK);
		frame.setBackground(Color.BLACK);
		frame.add(panel);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	//TODO make it return a Array to simplify controlling single characters in the GridLayout
	public static String toAscii(List<List<String>> map, Integer row) {
		String result = "";
		for(int i = 0; i < map.get(row).size(); i++) {
			String current = map.get(row).get(i);
			switch(current) {
			case "wall":
				result = result + "¤";
				break;
			case "floor":
				result = result + " ";//"·";
				break;
			case "player":
				result = result + "@";
				break;
			}
		}
		return result;
	}
}

package lanqiao._2017._1_迷宫;

import java.util.HashMap;
import java.util.HashSet;

public class Main {
	public static void main(String[] args) {
		String s = "UDDLUULRUL\r\n" + "UURLLLRRRU\r\n" + "RRUURLDLRD\r\n" + "RUDDDDUUUU\r\n" + "URUDLLRRUU\r\n"
				+ "DURLRLDLRL\r\n" + "ULLURLLRDU\r\n" + "RDLULLRDDD\r\n" + "UUDDUDUDLL\r\n" + "ULRDLUURRR";
		String[] lines = s.split("\r\n");
		String[][] maze = new String[10][10];
		for (int i = 0; i < 10; i++) {
			maze[i] = lines[i].split("");
		}
		
		HashMap<String, int[]> m = new HashMap<String, int[]>(); // 也可用switch
		HashSet<String> path = new HashSet<String>(); // 也可用visited数组
		m.put("U", new int[] { -1, 0 });
		m.put("D", new int[] { 1, 0 });
		m.put("L", new int[] { 0, -1 });
		m.put("R", new int[] { 0, 1 });
		
		int out = 0;
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < 10; j++) {
				int ii = i, jj = j;
				
				path.clear();
				while (true) {
					if (path.contains("" + ii + jj)) {
						break;
					}
					path.add("" + ii + jj);
					
					String dir = maze[ii][jj];
					int[] move = m.get(dir);
					ii += move[0];
					jj += move[1];
					
					if (!(0 <= ii && ii < 10 && 0 <= jj && jj < 10)) {
						out++;
						break;
					}
				}
			}
		}
		
		System.out.println(out);
	}
}

package main2021;

import java.util.Scanner;

public class Day11 {
	public static void flash(int x, int y) {
		if(charge[x][y]>9&&!flashed[x][y]) {
			flashed[x][y]=true;
			for(int dx = -1; dx <= 1; dx++) { for(int dy = -1; dy <= 1; dy++) {
				try {
					charge[x+dx][y+dy]++;
					flash(x+dx, y+dy);
				} catch(ArrayIndexOutOfBoundsException e) { continue; }
			} }
		}
	}

	static int flashes = 0;
	static boolean[][] flashed = new boolean[10][10];
	static int[][] charge = new int[10][10];
	public static void main(String[] args) {
		Scanner in = new Scanner(input);
		for(int j = 0; in.hasNextLine(); j++) {
			String temp = in.nextLine();
			for(int i = 0; i < temp.length(); i++) {
				flashed[j][i]=false;
				charge[j][i]=Integer.parseInt(temp.charAt(i)+"");
			}
		}
		in.close();
		
		for(int day = 0; day < 999; day++) {
			for(int x = 0; x < charge.length; x++) { for(int y = 0; y < charge[x].length; y++) { charge[x][y]++; } }
			for(int x = 0; x < charge.length; x++) { for(int y = 0; y < charge[x].length; y++) { flash(x, y); } }
			boolean allFlash = true;
			for(int x = 0; x < flashed.length; x++) { for(int y = 0; y < flashed[x].length; y++) { if(flashed[x][y]) { flashed[x][y]=false; flashes++; charge[x][y]=0; } else { allFlash=false; } } }
			if(allFlash) { System.out.println(day); break; }
		}
		System.out.println(flashes);
	}
	static final String input = "6788383436\r\n" + 
			"5526827441\r\n" + 
			"4582435866\r\n" + 
			"5152547273\r\n" + 
			"3746433621\r\n" + 
			"2465145365\r\n" + 
			"6324887128\r\n" + 
			"8537558745\r\n" + 
			"4718427562\r\n" + 
			"2283324746";
	static final String test = "5483143223\r\n" + 
			"2745854711\r\n" + 
			"5264556173\r\n" + 
			"6141336146\r\n" + 
			"6357385478\r\n" + 
			"4167524645\r\n" + 
			"2176841721\r\n" + 
			"6882881134\r\n" + 
			"4846848554\r\n" + 
			"5283751526";
}

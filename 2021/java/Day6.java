package main2021;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Day6 {
	static Map<Integer, Long> memoi = new HashMap<>();
	static long memoize(int init) {
		if(memoi.get(init)!=null) { return memoi.get(init); }
		int x = 6;
		List<Integer> spawns = new ArrayList<>();
		for(int i = init; i < 256; i++) { if(x==0) { spawns.add(i+3); x=6; } else { x--; } }
		long res = 1;
		for(int i : spawns) { res+=memoize(i); }
		memoi.put(init, res);
		return memoi.get(init);
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(input);
		List<Integer> bois = new ArrayList<>();
		for(String s : in.nextLine().split(",")) { bois.add(Integer.parseInt(s)); }
		in.close();
		long sumOfBois = 0;
		for(int b : bois) { sumOfBois+=memoize(b-6); }
		System.out.println(sumOfBois);
		for(Entry<Integer, Long> e : memoi.entrySet()) {
			System.out.println(e.getKey()+" "+e.getValue());
		}
	}
	static final String input = "4,2,4,1,5,1,2,2,4,1,1,2,2,2,4,4,1,2,1,1,4,1,2,1,2,2,2,2,5,2,2,3,1,4,4,4,1,2,3,4,4,5,4,3,5,1,2,5,1,1,5,5,1,4,4,5,1,3,1,4,5,5,5,4,1,2,3,4,2,1,2,1,2,2,1,5,5,1,1,1,1,5,2,2,2,4,2,4,2,4,2,1,2,1,2,4,2,4,1,3,5,5,2,4,4,2,2,2,2,3,3,2,1,1,1,1,4,3,2,5,4,3,5,3,1,5,5,2,4,1,1,2,1,3,5,1,5,3,1,3,1,4,5,1,1,3,2,1,1,1,5,2,1,2,4,2,3,3,2,3,5,1,5,1,2,1,5,2,4,1,2,4,4,1,5,1,1,5,2,2,5,5,3,1,2,2,1,1,4,1,5,4,5,5,2,2,1,1,2,5,4,3,2,2,5,4,2,5,4,4,2,3,1,1,1,5,5,4,5,3,2,5,3,4,5,1,4,1,1,3,4,4,1,1,5,1,4,1,2,1,4,1,1,3,1,5,2,5,1,5,2,5,2,5,4,1,1,4,4,2,3,1,5,2,5,1,5,2,1,1,1,2,1,1,1,4,4,5,4,4,1,4,2,2,2,5,3,2,4,4,5,5,1,1,1,1,3,1,2,1";
}

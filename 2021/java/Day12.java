package main2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Day12 {
	static class MultiMap<A, B> {
		Map<A, List<B>> map;
		public MultiMap() { map = new HashMap<>(); }
		public void add(A a, B b) {
			List<B> list = map.containsKey(a)?map.get(a):new ArrayList<>();
			list.add(b);
			map.put(a, list);
		}
		public List<B> get(A a) {
			return map.get(a);
		}
		public Set<A> keySet() { return map.keySet(); }
		@Override public String toString() {
			StringBuilder res = new StringBuilder();
			res.append("[");
			for(A a : keySet()) {
				res.append("[");
				res.append(a);
				res.append(":");
				for(B b : get(a)) {
					res.append(" ");
					res.append(b);
					res.append(",");
				}
				res.deleteCharAt(res.length()-1);
				res.append("]");
			}
			res.append("]");
			return res.toString();
		}
	}
	static void link(String from, String to) {
		map.add(from, to);
		map.add(to, from);
	}
	
	static MultiMap<String, String> map = new MultiMap<>();
	static List<List<String>> routes = new ArrayList<>();
	public static void main(String[] args) {
		Scanner in = new Scanner(input);
		while(in.hasNextLine()) {
			String temp = in.nextLine();
			link(temp.substring(0, temp.indexOf('-')),temp.substring(temp.indexOf('-')+1));
		}
		in.close();
		for(String n : map.keySet()) { if(n.equals("start")) { route(new ArrayList<String>(Arrays.asList(n))); } }
		System.out.println(routes.size());
		System.out.println(map);
	}
	static void route(List<String> path) {
		if(path.get(path.size()-1).equals("end")) { routes.add(path); return; }
		for(String n : map.get(path.get(path.size()-1))) {
			if(!(n.equals(n.toLowerCase())&&path.contains(n)&&(doubleSmall(path)||n.equals("start")))) {
				List<String> newPath = new ArrayList<>(path);
				newPath.add(n);
				route(newPath);
			}
		}
	}
	static boolean doubleSmall(List<String> in) {
		for(int i = 0; i < in.size(); i++) {
			for(int j = 0; j < in.size(); j++) {
				if(i==j) { continue; }
				if(in.get(i).equals(in.get(j))&&in.get(i).equals(in.get(i).toLowerCase())) { return true; }
			}
		}
		return false;
	}
	static final String smallTest = "start-A\r\n" + 
			"start-b\r\n" + 
			"A-c\r\n" + 
			"A-b\r\n" + 
			"b-d\r\n" + 
			"A-end\r\n" + 
			"b-end";
	static final String input = "start-YY\r\n" + 
			"av-rz\r\n" + 
			"rz-VH\r\n" + 
			"fh-av\r\n" + 
			"end-fh\r\n" + 
			"sk-gp\r\n" + 
			"ae-av\r\n" + 
			"YY-gp\r\n" + 
			"end-VH\r\n" + 
			"CF-qz\r\n" + 
			"qz-end\r\n" + 
			"qz-VG\r\n" + 
			"start-gp\r\n" + 
			"VG-sk\r\n" + 
			"rz-YY\r\n" + 
			"VH-sk\r\n" + 
			"rz-gp\r\n" + 
			"VH-av\r\n" + 
			"VH-fh\r\n" + 
			"sk-rz\r\n" + 
			"YY-sk\r\n" + 
			"av-gp\r\n" + 
			"rz-qz\r\n" + 
			"VG-start\r\n" + 
			"sk-fh\r\n" + 
			"VG-av";
}

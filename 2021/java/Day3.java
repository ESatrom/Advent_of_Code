package main2021;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day3 {
	public static void main(String[] args) {
		Scanner in = new Scanner("");
		try { 
			URL url = new URL("https://adventofcode.com/2021/day/4/input");
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(((HttpURLConnection) (url).openConnection()).getInputStream(), Charset.forName("UTF-8")));
			String temp;
			do {
				temp = reader.readLine();
				System.out.println(temp);
			} while(temp!=null);
			Scanner i = new Scanner(url.openStream());
			while(i.hasNext()) { System.out.println(i.next()); }
		} catch (Exception e) { e.printStackTrace(); }
		List<String> oIn = new ArrayList<>();
		List<String> cIn = new ArrayList<>();
		while(in.hasNextLine()) {
			String temp = in.nextLine();
			oIn.add(temp);
			cIn.add(temp);
		}
		in.close();
		
		for(int i = 0; i < 12; i++) {
			int oneCount = 0;
			for(int j = 0; j < oIn.size(); j++) { if(oIn.get(j).charAt(i)=='1') { oneCount++; } }
			char mostCommon = oneCount>Math.floor((oIn.size()/2.0)-.1)?'1':'0';
			for(int j = 0; j < oIn.size(); j++) {
				if(oIn.size()==1) { break; }
				if(oIn.get(j).charAt(i)!=mostCommon) { oIn.remove(j); j--; continue; }
			}
		}
		for(int i = 0; i < 12; i++) {
			int oneCount = 0;
			for(int j = 0; j < cIn.size(); j++) { if(cIn.get(j).charAt(i)=='1') { oneCount++; } }
			char leastCommon = oneCount>Math.floor((cIn.size()/2.0)-.1)?'0':'1';
			for(int j = 0; j < cIn.size(); j++) {
				if(cIn.size()==1) { break; }
				if(cIn.get(j).charAt(i)!=leastCommon) { cIn.remove(j); j--; continue; }
			}
		}
		System.out.println(Integer.parseInt(oIn.get(0),2)*Integer.parseInt(cIn.get(0),2));
	}
}

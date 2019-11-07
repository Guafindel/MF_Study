package day02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Quest {
	
	static int number[] = {43, 24, 10, 18, 1, 36};

	public static void main(String[] args) {
		
		//int num[] = {43, 24, 10, 18, 1, 36};
		//Collections.shuffle(Arrays.asList(num));
		
		lottoNum();
		//start();
	}
	
	public static void start() {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("몇 번 게임 하시겠어요?");
		int cnt = scan.nextInt();
		
		for(int i=1; i<=cnt; i++) {
			//System.out.println(lottoNum());
		}
	}
	
	public static void lottoNum() {
		List<Integer> set = new ArrayList<Integer>();
		
		for(int i = 1; i<=45; i++) {
			set.add(i);
			//System.out.println(set);
		}
		Collections.shuffle(set);
		//System.out.println(set);
		int num[] = new int[6];
		for(int i=0; i<6; i++) {
			num[i] = set.get(i);
		}
		Arrays.sort(num);
		
		System.out.println(Arrays.toString(num));
		
	}
}

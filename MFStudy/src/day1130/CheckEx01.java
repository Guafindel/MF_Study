package day1130;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class CheckEx01 {

	public static void main(String[] args) {
		
		Set<Integer> set01 = new HashSet<Integer>();
		Set<Integer> set02 = new HashSet<Integer>();
		List<Integer> list01 = new ArrayList<Integer>();
		List<Integer> list02 = new ArrayList<Integer>();
		
		Set<Integer> set03 = new LinkedHashSet<Integer>();
		Set<Integer> set04 = new LinkedHashSet<Integer>();
		List<Set<Integer>> listSet = new ArrayList<Set<Integer>>();
		
		set01.add(1);
		set01.add(2);
		set01.add(3);
		set01.add(4);
		set01.add(5);
		set01.add(6);
		
		list01.addAll(set01);
		Collections.shuffle(list01);
		
		set02.add(6);
		set02.add(5);
		set02.add(4);
		set02.add(3);
		set02.add(2);
		set02.add(1);
		
		list02.addAll(set02);
		Collections.shuffle(list02);
	
		set03.addAll(list01);
		set04.addAll(list02);
	
		boolean checkOverlap = false;
		
		listSet.add(set03);
		
		System.out.println(set03);
		System.out.println(set04);
		if(listSet.contains(set04)) {
			checkOverlap = true;
		}
		
		System.out.println(checkOverlap);
		
		
	}
	
}

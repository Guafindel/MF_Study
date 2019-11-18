package day10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Logic5 {

	List<Integer> firstPlace = new ArrayList<>(); // 로또 1등 리스트
	List<Integer> lottoPlace = null;// = new ArrayList<>(); // 로또 당첨 리스트(2등~꼴등)

	// lottoPlace에서 만든 리스트를 담아둘 리스트 생성
	List<ArrayList<Integer>> listPlace = new ArrayList<ArrayList<Integer>>();

	Random ra = new Random(); // 1~45(또는 그 큰 범위 {-45~60}의 랜덤 숫자 뽑아내기 위한 Random 인스턴스 생성

	// 로또 범위를 지정할 상수 지정 (1 ~ 45)...(-45 ~ 60)...(MIN ~ MAX)
	static int MAX = 45;
	static int MIN = 1;

	public static void main(String[] args) {
		
		Logic5 lg = new Logic5(); 

		/*
		 * 23,12,9,2,43,32 1:1,2:3,3:10,4:50,5:100 500 "TRUE" 
		 * count = 6, 1등 | count = 5, 2등 | count = 4, 3등 | count = 3, 4등 
		 * count = 2, 5등 | count = 1, 미당첨 | count = 0, 미당첨
		 */	
		
		/* 1등 번호 총 6개 지정 */
		String [] stringFirstNum = null;
		stringFirstNum = args[0].split(",");
		int[] firstNum = new int[stringFirstNum.length];
		if(stringFirstNum.length > 3) {
			for(int i = 0; i < stringFirstNum.length; i++) {
				firstNum[i] = Integer.parseInt(stringFirstNum[i]);
				lg.firstPlace.add(firstNum[i]);
			}
		}
		
		/* 각 등수의 리스트 생성 개수 지정 */
		String [] stringCountNum = null;
		stringCountNum = args[1].split(",|:");
		for(int i = 0; i < stringCountNum.length; i++) {
			System.out.println(stringCountNum[i]);
		}
		

		String rp = "ramdomPermission";

//		for (int i = 0; i < firstCount; i++) {
//			lg.getLotto(0); // 1등
//		}
//		for (int i = 0; i < secondCount; i++) {
//			lg.getLotto(1); // 2등
//		}
//		for (int i = 0; i < thirdCount; i++) {
//			lg.getLotto(2); // 3등
//		}
//		for (int i = 0; i < fourCount; i++) {
//			lg.getLotto(3); // 4등
//		}
//		for (int i = 0; i < fifthCount; i++) {
//			lg.getLotto(4); // 5등
//		}
//		for (int i = 0; i < failCount; i++) {
//			lg.getLotto(6); // 꽝
//		}
//
		/* args값으로 TURE가 들어왔을 때 */
		if (rp.equals("TRUE")) {
			Collections.shuffle(lg.listPlace);
			for (int i = 0; i < lg.listPlace.size(); i++) {
				System.out.print(lg.getRank(lg.listPlace.get(i)) + "\t");
				System.out.println(lg.listPlace.get(i));
			}
		} else {
			for (int i = 0; i < lg.listPlace.size(); i++) {
				System.out.println(lg.listPlace.get(i));
				System.out.print(lg.getRank(lg.listPlace.get(i)) + "\t");
			}
		}
	}

	/* 로또 번호의 범위값을 지정하는 메소드 */
	public int getRnum(int max, int min) {

		int rNum = 0;
		// nextInt(max - min + 1) + min; == min ~ max의 난수 생성
		rNum = ra.nextInt(max - min + 1) + min;
		return rNum;
	}

	/* ranOrPull이 true이면 랜덤번호리스트 반환, false면 1등번호 리스트 반환 */
	public List<Integer> pullAndRanNum(int wrongNum, boolean ranOrPull) {

		Set<Integer> uniqueNum = new HashSet<>();
		List<Integer> num = null;

		int rNum = 0;
		while (uniqueNum.size() < wrongNum) {
			rNum = getRnum(MAX, MIN);
			if (ranOrPull == true) {
				if (!(firstPlace.contains(rNum))) {
					uniqueNum.add(rNum);
					num = new ArrayList<Integer>(uniqueNum);
				}
			} else {
				if (firstPlace.contains(rNum)) {
					uniqueNum.add(rNum);
					num = new ArrayList<Integer>(uniqueNum);
				}
			}
			/* ranOrPull값에 따라서, 다른 조건의 wrongNum개의 정수를 가진 리스트가 만들어진 후 while문 탈출 */
		}
		return num;
	}

	/* 1등 번호와 랜덤 번호를 교환해주는 메소드 */
	public List<Integer> replaceNum(List<Integer> lottoPlace, int wrongNum) {

		List<Integer> ranNum = pullAndRanNum(wrongNum, true); // true, 랜덤번호 생성
		List<Integer> pullNum = pullAndRanNum(wrongNum, false); // false, 1등번호 생성

		for (int i = 0; i < lottoPlace.size(); i++) {
			for (int j = 0; j < pullNum.size(); j++) {
				if (lottoPlace.get(i) == pullNum.get(j)) {
					lottoPlace.set(i, ranNum.get(j));
				}
			}
		}
		return lottoPlace;
	}

	public int[] shuffle(int[] array) {
		int arr = array.length;
		for (int i = 0; i < arr; i++) {
			int a = (int) (Math.random() * arr);
			int b = (int) (Math.random() * arr);
			int tmp = array[a];
			array[a] = array[b];
			array[b] = tmp;
		}
		return array;
	}

	/* 로또 리스트를 만드는 메소드 */
	public void getLotto(int wrongNum) {

		lottoPlace = new ArrayList<Integer>();
		lottoPlace.addAll(firstPlace);
		replaceNum(lottoPlace, wrongNum);
		Collections.shuffle(lottoPlace);
		listPlace.add((ArrayList<Integer>) lottoPlace);
	}

	/* 로또 등수 표시 메소드 */
	public String getRank(List<Integer> arr) {

		int count = 0;
		for (int i = 0; i < firstPlace.size(); i++) {
			for (int j = 0; j < arr.size(); j++) {
				if (firstPlace.get(i) == arr.get(j)) {
					count++;
				}
			}
		}
		switch (count) {
		case 6:
			return "1등";
		case 5:
			return "2등";
		case 4:
			return "3등";
		case 3:
			return "4등";
		case 2:
			return "5등";
		case 1:
			return "미당첨";
		case 0:
			return "미당첨";
		}
		return "범위 지정 오류.";

	}

}

package day11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Logic6 {

	List<Integer> firstPlace = new ArrayList<>();
	List<Integer> countPlace = new ArrayList<>();
	List<Integer> lottoPlace = null;
	
	List<ArrayList<Integer>> listPlace = new ArrayList<ArrayList<Integer>>();
	Random ra = new Random();

	static int MAX = 45;
	static int MIN = 1;

	public static void main(String[] args) {
		
		Logic6 lg = new Logic6(); 

		lg.getInputNum(args[0], "FIRST_NUM");
		
		lg.getInputNum(args[1], "COUNT_NUM");
		
		int totalCount = Integer.parseInt(args[2]);
		
		int failCount = lg.getFailCount(lg.countPlace, totalCount);
		
		String rp = "ramdomPermission";
		
		if(args.length > 3) {
			rp = args[3];
		}
		
		/**
		 * execueCount(틀린 개수, 생성개수)
		 */
		lg.executeCount(0, lg.countPlace.get(0)); 
		lg.executeCount(1, lg.countPlace.get(1)); 
		lg.executeCount(2, lg.countPlace.get(2)); 
		lg.executeCount(3, lg.countPlace.get(3)); 
		lg.executeCount(4, lg.countPlace.get(4));
		lg.executeFailCount(5, failCount);
		
		/* args값으로 TURE가 들어왔을 때 */
		if (rp.equals("TRUE")) {
			Collections.shuffle(lg.listPlace);
			for (int i = 0; i < lg.listPlace.size(); i++) {
				System.out.print(lg.getRank(lg.listPlace.get(i)) + "\t");
				System.out.println(lg.listPlace.get(i));
				
			}
		} else {
			for (int i = 0; i < lg.listPlace.size(); i++) {
				System.out.print(lg.getRank(lg.listPlace.get(i)) + "\t");
				System.out.println(lg.listPlace.get(i));	
			}
		}
	}
	
	/**
	 * 받은 문자열 스플릿하는 메소드
	 */
	public String[] getStringNum(String inputNum) {
		
		String[] stringNum = null;
		stringNum = inputNum.split(",|:");
		return stringNum;
	}
	
	/**
	 * 스플릿한 문자 배열 인트배열로 옮겨 담는 메소드
	 */
	public void getInputNum(String inputVal, String categoryCode) {
		
		String[] stringNum = getStringNum(inputVal);
		
		if(categoryCode.equals("FIRST_NUM")) {
			int[] convertNum = new int[stringNum.length];
			if(stringNum.length > 3) {
				for(int i = 0; i < convertNum.length; i++) {
					convertNum[i] = Integer.parseInt(stringNum[i]);
					firstPlace.add(convertNum[i]);
				}
			} else {
				System.out.println("로또의 최소 길이는 숫자 4개 이상입니다.");
				System.exit(0);
			}
			
		} else if(categoryCode.equals("COUNT_NUM")) {
			int[] convertNum = new int[stringNum.length/2];
			if(stringNum.length > 4) {
				for(int i = 0; i < convertNum.length; i++) {
					convertNum[i] = Integer.parseInt(stringNum[(2*i)+1]);
					countPlace.add(convertNum[i]);
				}
			} else {
				System.out.println("최소 2등까지는 개수 지정이 필요합니다.");
				System.exit(0);
			}
		}	
	}
	
	/**
	 * 미당첨 갯수 지정하는 메소드
	 */
	public int getFailCount(List<Integer> countNum, int totalCount) {
		
		int failCount = 0;
		int sum = 0;
		for(int i = 0; i < countNum.size(); i++) {
			sum += countNum.get(i);
		}
		failCount = totalCount - sum;
		return failCount;
	}
	
	/**
	 * 로또 번호의 범위값을 지정하는 메소드
	 */
	public int getRnum(int max, int min) {

		int rNum = 0;
		// nextInt(max - min + 1) + min; == min ~ max의 난수 생성
		rNum = ra.nextInt(max - min + 1) + min;
		return rNum;
	}

	/**
	 * ranOrPull이 true이면 랜덤번호리스트 반환, false면 1등번호 리스트 반환
	 */
	public List<Integer> pullAndRanNum(int wrongNum, boolean ranOrPull) {

		Set<Integer> uniqueNum = new HashSet<>();
		List<Integer> arrayNum = new ArrayList<>();
		
		int rNum = 0;
		while (uniqueNum.size() < wrongNum) {
			rNum = getRnum(MAX, MIN);
			if (ranOrPull == true) {
				if (!(firstPlace.contains(rNum))) {
					uniqueNum.add(rNum);
					arrayNum = new ArrayList<Integer>(uniqueNum);
				}
			} else {
				if (firstPlace.contains(rNum)) {
					uniqueNum.add(rNum);
					arrayNum = new ArrayList<Integer>(uniqueNum);
				}
			}
		}
		return arrayNum;
	}

	/**
	 * 1등 번호와 랜덤 번호를 교환해주는 메소드
	 */
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

	/**
	 * 로또 리스트를 만드는 메소드
	 */
	public void getLotto(int wrongNum) {

		lottoPlace = new ArrayList<Integer>();
		lottoPlace.addAll(firstPlace);
		replaceNum(lottoPlace, wrongNum);
		Collections.shuffle(lottoPlace);
		listPlace.add((ArrayList<Integer>) lottoPlace);
	}
	
	/**
	 * 입력받은 숫자만큼 로또 리스트 생성하는 메소드
	 */
	public void executeCount(int wrongNum, int countNum) {
		for (int i = 0; i < countNum; i++) {
			getLotto(wrongNum);
		}
	}
	public void executeFailCount(int wrongNum, int countNum) {
		for (int i = 0; i< countNum; i++) {
			getLotto(getRnum(wrongNum+1, wrongNum));
		}
	}
	
	/**
	 * 로또 등수 표시 메소드
	 */
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
			return "1등 ";
		case 5:
			return "2등 ";
		case 4:
			return "3등 ";
		case 3:
			return "4등 ";
		case 2:
			return "5등 ";
		case 1:
			return "미당첨";
		case 0:
			return "미당첨";
		}
		return "범위 지정 오류.";
	}
		
}

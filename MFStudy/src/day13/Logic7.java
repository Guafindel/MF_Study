package day13;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Logic7 {
	
	private int lottoSize = 6;
	
	private final static int firstCount = 1;
	private int secondCount = 0;
	private int thirdCount = 0;
	private int fourthCount = 0;
	private int fifthCount = 0;
	
	private int totalCount = 0;
	
	private int min = 1;
	private int max = 45;
	
	private boolean setMix = false;

	List<Integer> firstPlace = new ArrayList<>();
	List<Integer> countPlace = new ArrayList<>();
	List<Integer> lottoPlace = null;
	
	List<ArrayList<Integer>> listPlace = new ArrayList<ArrayList<Integer>>();
	Random ra = new Random();

	static final int MAX = 45;
	static final int MIN = 1;

	public static void main(String[] args) {
		// 23,12,9,2,13,1 1:1,2:3,3:10,4:50,5:100 1000 TRUE
		Logic7 logic = new Logic7(); 

		logic.getInputNum(args[0], "FIRST_NUM");
		
		logic.getInputNum(args[1], "COUNT_NUM");
		
		int totalCount = Integer.parseInt(args[2]);
		
		int failCount = logic.getFailCount(totalCount);
		logic.countPlace.add(failCount);
		
		String rp = "ramdomPermission";
		
		if(args.length > 3) {
			rp = args[3];
		}
			
		/**
		 * execueCount(틀린 개수, 생성개수)
		 */
		for(int i = 0; i < logic.firstPlace.size(); i++) {
			logic.executeCount(i, logic.countPlace.get(i));
		}
		
		logic.outPutList(rp);
	}
	
	/**
	 * 받은 문자열 스플릿하는 메소드
	 */
	public String[] getStringNum(String inputVal) {
		
		String[] stringNum = null;
		stringNum = inputVal.split(",|:");
		return stringNum;
	}
	
	/**
	 * 스플릿한 문자 배열 인트배열로 옮겨 담는 메소드
	 * @param inputVal
	 * @param categoryCode
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
			if(stringNum.length > 5) {
				for(int i = 0; i < convertNum.length; i++) {
					convertNum[i] = Integer.parseInt(stringNum[(2*i)+1]);
					countPlace.add(convertNum[i]);
				}
			} else {
				System.out.println("최소 3등까지는 개수 지정이 필요합니다.");
				System.exit(0);
			}
		}	
	}
	
	/**
	 * 미당첨 갯수 지정하는 메소드
	 */
	public int getFailCount(int totalCount) {
		
		int failCount = 0;
		int sum = 0;
		for(int i = 0; i < countPlace.size(); i++) {
			sum += countPlace.get(i);
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
		
		if (count == firstPlace.size()) {
			return "1등 ";
		} 
		if (count == (firstPlace.size() - 1)) {
			return "2등 ";
		}
		if (count == (firstPlace.size() - 2) && firstPlace.size() > 3) {
			return "3등 ";
		} 
		if (count == (firstPlace.size() - 3) && firstPlace.size() > 4) {
			return "4등 ";
		}
		if (count == (firstPlace.size() - 4) && firstPlace.size() > 5) {
			return "5등 ";
		}
		if (count == (firstPlace.size() - 5) && firstPlace.size() > 6) {
			return "6등";
		}
		if (count == (firstPlace.size() - 6) && firstPlace.size() > 7) {
			return "7등";
		}
		return "미당첨";
	}
	
	public void getRanked() {
		
		int[] arr = new int[500];
		int[] rankArr = new int[6];
		for(int i = 0; i < rankArr.length; i++) {
			for(int j = 0; j < arr.length; j++) {
				if(arr[i] < arr[j]) {
					rankArr[i]++;
				}
			}
		}
	}
	
	/**
	 * 입력받은 args가 TRUE인지에 따라서 출력방식을 다르게 정하는 메소드
	 */
	public void outPutList(String rp) {
		
		if (rp.equals("TRUE")) {
			Collections.shuffle(listPlace);
			for (int i = 0; i < listPlace.size(); i++) {
				System.out.print(getRank(listPlace.get(i)) + "\t");
				System.out.println(listPlace.get(i));
			}
		} else {
			for (int i = 0; i < listPlace.size(); i++) {
				System.out.print(getRank(listPlace.get(i)) + "\t");
				System.out.println(listPlace.get(i));	
			}
		}
	}
			
}

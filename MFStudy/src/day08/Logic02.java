package day08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.stream.Collector;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

public class Logic02 {
	
	static List<Integer> firstPlace = new ArrayList<>(); // 1등 번호 리스트
	static List<Integer> secondPlace = new ArrayList<>(); // 2등 번호가 리스트
	static List<Integer> thirdPlace = new ArrayList<>(); // 3등 번호가 리스트
	static List<Integer> fourPlace = new ArrayList<>(); // 4등 번호가 리스트
	static List<Integer> fifthPlace = new ArrayList<>(); // 5등 번호 리스트
	static List<Integer> lastPlace = new ArrayList<>(); // 미당첨 번호 리스트
	
	static Random ra = new Random(); // 1~45(또는 그 큰 범위 {-45~60}의 랜덤 숫자 뽑아내기 위한 Random 인스턴스 생성

	public static void main(String[] args) {

		Logic02 lg = new Logic02(); // Logic에 생성해둔 메소드 사용하기 위한 인스턴스 생성
		
 		// 로또 조건 값 {23 12 9 2 43 32} 당첨 갯수 1 3 10 50 100 500, 무작위 셔플 여부 "TRUE"
		
		// 1등 번호 총 6개 지정
		int winNum1 = Integer.parseInt(args[0]);
		int winNum2 = Integer.parseInt(args[1]);
		int winNum3 = Integer.parseInt(args[2]);
		int winNum4 = Integer.parseInt(args[3]);
		int winNum5 = Integer.parseInt(args[4]);
		int winNum6 = Integer.parseInt(args[5]);

		int firstCount = Integer.parseInt(args[6]);
		int secondCount = Integer.parseInt(args[7]);
		int thirdCount = Integer.parseInt(args[8]);
		int fourCount = Integer.parseInt(args[9]);
		int fifthCount = Integer.parseInt(args[10]);
		int failCount = -((firstCount + secondCount + thirdCount + fourCount + fifthCount) - 500);
		
		String rq = "anyWord";
		
		if(args.length == 12) {
			rq = args[11];
		}
		
		firstPlace.add(winNum1);
		firstPlace.add(winNum2);
		firstPlace.add(winNum3);
		firstPlace.add(winNum4);
		firstPlace.add(winNum5);
		firstPlace.add(winNum6);
		
		
		lg.getRnum();
			
	}

	// 2등 당첨 로또 리스트를 만드는 메소드
	void getSecond(int n) {

		// 2등 번호를 1등 번호와 동일하게 만들도록 복사 대입
		secondPlace.addAll(firstPlace);

		// 2등 로또 리스트인 secondPlace에 대입하는 메소드 
		// 2등 로또가 들어갈 리스트 지정(secondPlace)
		// 당첨 번호에서 몇 개 틀려야 2등이 되는지 지정(n개를 틀릴 경우 2등)
		replaceNum(secondPlace, getRandom(n), getPullNum(n));

		// 2등 로또 리스트를 생성 한 후 무작위 자리 배정
		Collections.shuffle(secondPlace);
		
		// 완성된 리스트 출력
		System.out.println(secondPlace);
		
		// 완성된 리스트 내용 삭제
		secondPlace.removeAll(secondPlace);

	}

	// 3등 당첨 로또 리스트를 만드는 메소드
	void getThird(int n) {

		thirdPlace.addAll(firstPlace);
		replaceNum(thirdPlace, getRandom(n), getPullNum(n));
		Collections.shuffle(thirdPlace);
		System.out.println(thirdPlace);
		thirdPlace.removeAll(thirdPlace);

	}

	// 4등 당첨 로또 리스트를 만드는 메소드
	void getFourth(int n) {

		fourPlace.addAll(firstPlace);
		replaceNum(fourPlace, getRandom(n), getPullNum(n));
		Collections.shuffle(fourPlace);
		System.out.println(fourPlace);
		fourPlace.removeAll(fourPlace);
	}

	// 5등 당첨 로또 리스트를 만드는 메소드
	void getFifth(int n) {

		fifthPlace.addAll(firstPlace);
		replaceNum(fifthPlace, getRandom(n), getPullNum(n));
		Collections.shuffle(fifthPlace);
		System.out.println(fifthPlace);
		fifthPlace.removeAll(fifthPlace);

	}

	// 미당첨 로또 리스트를 만드는 메소드
	void getLast(int n) {

		lastPlace.addAll(firstPlace);
		replaceNum(lastPlace, getRandom(n), getPullNum(n));
		Collections.shuffle(lastPlace);
		System.out.println(lastPlace);
		lastPlace.removeAll(lastPlace);

	}

	// 1등 당첨 번호 6개를 제외한 1~45 범위 안의 임의의 수를 매개 변수만큼 생성해 리스트 만드는 메소드 
	List<Integer> getRandom(int n) {
		
		// 생성한 수를 담아낼 리스트
		List<Integer> ranNum = new ArrayList<>();

		// 1~45까지의 수를 대입시킬 지역 변수
		int rNum = 0;

		// 몇 개의 수를 뽑아낼지 정하기 위한 while 조건(1~6개까지 다르게 메소드를 적용하기 위함)
		while(ranNum.size() < n) {
			// 1부터 45까지의 랜덤 수를 rNum에 대입
			rNum = ra.nextInt(45)+1;
			// 1부터 45까지의 수 중에서 1등의 값을 제외한 수를 뽑기 위한 조건
			if(!(firstPlace.contains(rNum))) {
				// 1등의 6가지 숫자를 제외한 숫자가 뽑혔다면 리스트에 삽입
				ranNum.add(rNum);
				// 리스트 안의 숫자들끼리 중복검사를 위한 HashSet 대입
				HashSet<Integer> uniqueRanNum = new HashSet<Integer>(ranNum);
				// 중복 제거된 HashSet을 리스트에 대입
				ranNum = new ArrayList<Integer>(uniqueRanNum);
				
				// n개의 정수를 가진 리스트가 만들어진 후 while문 탈출
				
			} 
		}
		return ranNum;
	}
	
	// 당첨 번호 6개의 숫자 중에서 임의의 수를 매개 변수의 수 만큼 뽑아내는 메소드 
	List<Integer> getPullNum(int n) {
		
		// 생성한 수를 담아낼 리스트
		List<Integer> pullNum = new ArrayList<Integer>();
			
		// 1~45까지의 랜덤 수를 담아낼 변수
		int rNum = 0;

		// 몇 개의 수를 뽑아낼지 정하기 위한 while 조건(1~6개까지 다르게 메소드를 적용하기 위함)
		while(pullNum.size() < n) {
			// 1부터 45까지의 랜덤 수를 rNum에 대입
			rNum = ra.nextInt(45)+1;
			// 1부터 45까지의 수 중에서 1등의 값과 똑같은 수가 뽑기 위한 조건
			if(firstPlace.contains(rNum)) {
				// 1등의 6가지 숫자 중 똑같은 값이 뽑혔다면 리스트에 삽입
				pullNum.add(rNum);
				// 리스트 안의 중복검사를 하기 위해 HashSet 객체 생성후 대입
				HashSet<Integer> uniquePullNum = new HashSet<Integer>(pullNum);
				// HashSet에서 중복이 있다면 제거, 아니라면 상태 유지 후 다시 HashSet을 리스트에 대입
				pullNum = new ArrayList<Integer>(uniquePullNum);
				
				// n개의 정수를 가진 리스트가 만들어진 후 while문 탈출
				
			} 
		}	
		return pullNum;
	}
	
	// pullNum으로 뽑아낸 수를 ranNum으로 생성한 수로 대체하고 특정 등수(1등,2등,3등...) 리스트에 집어넣는 메소드
	List<Integer> replaceNum(List<Integer> arr, List<Integer> ranNum, List<Integer> pullNum) {
		
		// 특정 리스트의 사이즈 만큼 반복
		for (int i : arr) {
			// pullNum 리스트가 가진 모든 숫자와 비교하기 위한 반복 사이즈 지정
			for (int j : pullNum) {
				// 특정 리스트의 i값과 pullNum 리스트의 모든 숫자 비교, 동일한지 판단
				if (arr.get(i) == pullNum.get(j)) {
					// 동일 하다면 특정 리스트(i)의 값을 ranNum(j)로 대체 변경, 동일하지 않다면 다음 i값으로 이동
					arr.set(i, ranNum.get(j));
				}
			}
		}
		return arr;
	}
	
	int getRnum() {
		
		int rNum = 0;
		
		//rNum = ra.nextInt(60)-105;
		
		
		System.out.println(rNum);
		
		return 0;
	}
	

	// map 에 arraylist 담아서 셔플 돌려보기(TRUE)조건 
	

}

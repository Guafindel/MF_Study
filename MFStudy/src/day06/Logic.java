package day06;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.stream.Collector;

public class Logic {
	
	static List<Integer> firstPlace = new ArrayList<>(); // 1등 번호 리스트
	static List<Integer> secondPlace = new ArrayList<>(); // 2등 번호가 리스트
	static List<Integer> thirdPlace = new ArrayList<>(); // 3등 번호가 리스트
	static List<Integer> fourPlace = new ArrayList<>(); // 4등 번호가 리스트
	static List<Integer> fifthPlace = new ArrayList<>(); // 5등 번호 리스트
	static List<Integer> lastPlace = new ArrayList<>(); // 미당첨 번호 리스트
	
	static Random ra = new Random(); // 1~45의 랜덤 숫자 뽑아내기 위한 Random 인스턴스 생성

	public static void main(String[] args) {

		Logic lg = new Logic(); // Logic에 생성해둔 메소드 사용하기 위한 인스턴스 생성
		
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
		
		System.out.println(firstPlace);
		
		lg.getLast();
		
		

//		if (rq.equals("TRUE")) {
//			
//			System.out.println("아직 문제가 있어요");
//			
//		} else {
//			for (int i = 0; i < firstCount; i++) {
//				System.out.println(firstPlace);
//			}
//			for (int i = 0; i < secondCount; i++) {
//				lg.getSecond();
//
//			}
//			for (int i = 0; i < thirdCount; i++) {
//				lg.getThird();
//			}
//			for (int i = 0; i < fourCount; i++) {
//				lg.getFourth();
//
//			}
//			for (int i = 0; i < fifthCount; i++) {
//				lg.getFifth();
//
//			}
//			for (int i = 0; i < failCount; i++) {
//				lg.getLast();
//
//			}
//		}

	}

	// 2등 당첨 로또 리스트를 만드는 메소드
	void getSecond() {

		// 2등 번호를 1등 번호와 동일하게 만들도록 복사 대입
		secondPlace.addAll(firstPlace);

		// 1등 번호 6개를 제외한 1~45까지의 번호 중에서 1개 생성한 것을 담아둘 리스트
		List<Integer> ranNum = new ArrayList<>(getRandom(1)); 
						
		// 1등 번호 6개 중에서 무작위로 번호 1개를 뽑아내 담아둘 리스트
		List<Integer> pullNum = new ArrayList<>(getSameNum(1));

		// getSameNum으로 뽑아낸 숫자를 getRandom으로 뽑아낸 숫자로 대체
		for (int i = 0; i < secondPlace.size(); i++) {
			for (int j = 0; j < pullNum.size(); j++) {
				if (secondPlace.get(i) == pullNum.get(j)) {
					secondPlace.set(i, ranNum.get(j));
				}
			}
		}

		Collections.shuffle(secondPlace);
		System.out.println(secondPlace);
		secondPlace.removeAll(secondPlace);

	}

	// 3등 당첨 로또 리스트를 만드는 메소드
	void getThird() {

		thirdPlace.addAll(firstPlace);

		// 1등 번호 6개를 제외한 1~45까지의 번호 중에서 2개 생성한 것을 담아둘 리스트
		List<Integer> ranNum = new ArrayList<>(getRandom(2)); 
				
		// 1등 번호 6개 중에서 무작위로 번호 2개를 뽑아내 담아둘 리스트
		List<Integer> pullNum = new ArrayList<>(getSameNum(2));

		// getSameNum으로 뽑아낸 숫자를 getRandom으로 뽑아낸 숫자로 대체
		for (int i = 0; i < thirdPlace.size(); i++) {
			for (int j = 0; j < pullNum.size(); j++) {
				if (thirdPlace.get(i) == pullNum.get(j)) {
					thirdPlace.set(i, ranNum.get(j));
				}
			}
		}

		Collections.shuffle(thirdPlace);
		System.out.println(thirdPlace);
		thirdPlace.removeAll(thirdPlace);

	}

	// 4등 당첨 로또 리스트를 만드는 메소드
	void getFourth() {

		fourPlace.addAll(firstPlace);

		// 1등 번호 6개를 제외한 1~45까지의 번호 중에서 3개 생성한 것을 담아둘 리스트
		List<Integer> ranNum = new ArrayList<>(getRandom(3)); 
						
		// 1등 번호 6개 중에서 무작위로 번호 3개를 뽑아내 담아둘 리스트
		List<Integer> pullNum = new ArrayList<>(getSameNum(3));

		// getSameNum으로 뽑아낸 숫자를 getRandom으로 뽑아낸 숫자로 대체
		for (int i = 0; i < fourPlace.size(); i++) {
			for (int j = 0; j < pullNum.size(); j++) {
				if (fourPlace.get(i) == pullNum.get(j)) {
					fourPlace.set(i, ranNum.get(j));
				}
			}
		}
		Collections.shuffle(fourPlace);
		System.out.println(fourPlace);
		fourPlace.removeAll(fourPlace);
	}

	// 5등 당첨 로또 리스트를 만드는 메소드
	void getFifth() {

		fifthPlace.addAll(firstPlace);

		// 1등 번호 6개를 제외한 1~45까지의 번호 중에서 4개 생성한 것을 담아둘 리스트
		List<Integer> ranNum = new ArrayList<>(getRandom(4)); 
						
		// 1등 번호 6개 중에서 무작위로 번호 4개를 뽑아내 담아둘 리스트
		List<Integer> pullNum = new ArrayList<>(getSameNum(4));

		// getSameNum으로 뽑아낸 숫자를 getRandom으로 뽑아낸 숫자로 대체
		for (int i = 0; i < fifthPlace.size(); i++) {
			for (int j = 0; j < pullNum.size(); j++) {
				if (fifthPlace.get(i) == pullNum.get(j)) {
					fifthPlace.set(i, ranNum.get(j));
				}
			}
		}
		Collections.shuffle(fifthPlace);
		System.out.println(fifthPlace);
		fifthPlace.removeAll(fifthPlace);

	}

	// 미당첨 로또 리스트를 만드는 메소드
	void getLast() {

		lastPlace.addAll(firstPlace);
		
		//getSameNum(6);
		getPullNum(6);

		// getSameNum으로 뽑아낸 숫자를 getRandom으로 뽑아낸 숫자로 대체하고 특정 당첨 등수 리스트에 대입
		//replaceNum(lastPlace, getRandom(6), getSameNum(6));
		
		Collections.shuffle(lastPlace);
		//System.out.println(lastPlace);
		lastPlace.removeAll(lastPlace);

	}

	// 1등 당첨 번호 6개를 제외한 1~45 범위 안의 임의의 수를 매개 변수만큼 생성해 리스트 만드는 메소드 
	List<Integer> getRandom(int n) {

		// 1~45까지의 수를 대입시킬 지역 변수
		int rNum = 0;

		// 생성한 수를 담아낼 리스트
		List<Integer> ranNum = new ArrayList<>();

		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 1; j++) {
				rNum = ra.nextInt(45) + 1;
				if (firstPlace.contains(rNum)) {
					j--;
				}
			}
			ranNum.add(rNum);
			
			
		}
		
		for (int i = 0; i < ranNum.size(); i++) {
			for (int j = 0; j < i; j++) {
				while (ranNum.get(i) == ranNum.get(j)) {
					for (int k = 0; k < n; k++) {
						for (int o = 0; o < 1; o++) {
							rNum = ra.nextInt(45) + 1;
							if (lastPlace.contains(rNum)) {
								o--;
							}

						}
						ranNum.set(i, rNum);
					}

				}

			}
		}
		
		return ranNum;
	}

	// 당첨 번호 6개의 숫자 중에서 임의의 수를 매개 변수의 수 만큼 뽑아내는 메소드 
	List<Integer> getSameNum(int n) {

		// 1~45까지의 수를 대입시킬 지역 변수
		int rNum = 0;
 
		// 생성한 수를 담아낼 리스트
		List<Integer> pullNum = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 1; j++) {
				rNum = ra.nextInt(45) + 1;
				if (!(firstPlace.contains(rNum))) {
					j--;
				}
				
			}
			pullNum.add(rNum);
		}
		System.out.println(pullNum);

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i; j++) {
				while (pullNum.get(i) == pullNum.get(j)) {
					for (int k = 0; k < n; k++) {
						for (int o = 0; o < 1; o++) {
							rNum = ra.nextInt(45) + 1;
							if (!(firstPlace.contains(rNum))) {
								o--;
							}
							
						}
						pullNum.set(i, rNum);
					}

				}

			}
		}
		System.out.println(pullNum);

		return pullNum;
	}
	List<Integer> getPullNum(int n) {
		
		List<Integer> pullNum = new ArrayList<Integer>();
			
		int rNum = 0;

		while(pullNum.size() < n) {
			rNum = ra.nextInt(45)+1;
			if(firstPlace.contains(rNum)) {
				pullNum.add(rNum);
				HashSet<Integer> uniquePullNum = new HashSet<Integer>(pullNum);
				pullNum = new ArrayList<Integer>(uniquePullNum);
				
			} 
		}
		
		return pullNum;
	}
	
	// pullNum으로 뽑아낸 수를 ranNum으로 생성한 수로 대체하고 특정 등수(1등,2등,3등...) 리스트에 집어넣는 메소드
	List<Integer> replaceNum(List<Integer> arr, List<Integer> ranNum, List<Integer> pullNum) {
		
		for (int i = 0; i < arr.size(); i++) {
			for (int j = 0; j < pullNum.size(); j++) {
				if (arr.get(i) == pullNum.get(j)) {
					arr.set(i, ranNum.get(j));
				}
			}
		}
		return arr;
	}
	
	
	
	
	// map 에 arraylist 담아서 셔플 돌려보기(TRUE)조건 
	

}

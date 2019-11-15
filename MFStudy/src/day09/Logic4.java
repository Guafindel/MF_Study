package day09;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Logic4 {

	List<Integer> firstPlace = new ArrayList<>(); // 로또 1등 리스트
	List<Integer> lottoPlace;// = new ArrayList<>(); // 로또 당첨 리스트(2등~꼴등)

	List<ArrayList<Integer>> listPlace = new ArrayList<ArrayList<Integer>>();

	Random ra = new Random(); // 1~45(또는 그 큰 범위 {-45~60}의 랜덤 숫자 뽑아내기 위한 Random 인스턴스 생성

	final static int MAX = 45;
	final static int MIN = 1;

	public static void main(String[] args) {

		Logic4 lg = new Logic4(); // Logic에 생성해둔 메소드 사용하기 위한 인스턴스 생성

		// 로또 조건 값 {23 12 9 2 43 32} 당첨 갯수 1 3 10 50 100 500, 무작위 셔플 여부 "TRUE"
		// count = 6, 1등 | count = 5, 2등 | count = 4, 3등 | count = 3, 4등 | count = 2, 5등
		// count = 1, 미당첨 | count = 0, 미당첨

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

		if (args.length == 12) {
			rq = args[11];
		}

		lg.firstPlace.add(winNum1);
		lg.firstPlace.add(winNum2);
		lg.firstPlace.add(winNum3);
		lg.firstPlace.add(winNum4);
		lg.firstPlace.add(winNum5);
		lg.firstPlace.add(winNum6);

		for (int i = 0; i < firstCount; i++) {
			lg.getLotto(0); // 1등

		}
		for (int i = 0; i < secondCount; i++) {
			lg.getLotto(1); // 2등

		}
		for (int i = 0; i < thirdCount; i++) {
			lg.getLotto(2); // 3등

		}
		for (int i = 0; i < fourCount; i++) {
			lg.getLotto(3); // 4등

		}
		for (int i = 0; i < fifthCount; i++) {
			lg.getLotto(4); // 5등

		}
		for (int i = 0; i < failCount; i++) {
			lg.getLotto(6); // 꽝

		}

		if (rq.equals("TRUE")) {
			Collections.shuffle(lg.listPlace);
			for (int i = 0; i < lg.listPlace.size(); i++) {
				System.out.print(lg.getRank(lg.listPlace.get(i))+"\t");
				System.out.println(lg.listPlace.get(i));
				
			}
		} else {
			for (int i = 0; i < lg.listPlace.size(); i++) {
				System.out.println(lg.listPlace.get(i));
				System.out.print(lg.getRank(lg.listPlace.get(i))+"\t");
			}
		}
	}

	// 로또 리스트를 만드는 메소드
	void getLotto(int wrongNum) {

		lottoPlace = new ArrayList<Integer>();

		// 번호를 1등 번호와 동일하게 만들도록 복사 대입
		lottoPlace.addAll(firstPlace);

		// 로또 리스트인 lottoPlace에 대입하는 메소드
		// 당첨 번호에서 몇 개 틀려야 몇 등이 되는지 지정(n개를 틀릴 경우 몇 등)
		replaceNum(lottoPlace, getRandom(wrongNum), getPullNum(wrongNum));

		// 로또 리스트를 생성 한 후 무작위 자리 배정
		Collections.shuffle(lottoPlace);

		// 생성한 리스트를 다른 리스트에 담아둔다.
		listPlace.add((ArrayList<Integer>) lottoPlace);

	}

	// 1등 당첨 번호 6개를 제외한 1~45 범위 안의 임의의 수를 매개 변수만큼 생성해 리스트 만드는 메소드
	List<Integer> getRandom(int n) {

		// 생성한 수를 담아낼 리스트
		List<Integer> ranNum = new ArrayList<>();

		// 1~45까지의 수를 대입시킬 지역 변수
		int rNum = 0;

		// 몇 개의 수를 뽑아낼지 정하기 위한 while 조건(1~6개까지 다르게 메소드를 적용하기 위함)
		while (ranNum.size() < n) {
			// 1부터 45까지의 랜덤 수를 rNum에 대입
			rNum = getRnum(MAX, MIN);
			// 1부터 45까지의 수 중에서 1등의 값을 제외한 수를 뽑기 위한 조건
			if (!(firstPlace.contains(rNum))) {
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
		while (pullNum.size() < n) {
			// 1부터 45까지의 랜덤 수를 rNum에 대입
			rNum = getRnum(MAX, MIN);
			// 1부터 45까지의 수 중에서 1등의 값과 똑같은 수가 뽑기 위한 조건
			if (firstPlace.contains(rNum)) {
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
		for (int i = 0; i < arr.size(); i++) {
			// pullNum 리스트가 가진 모든 숫자와 비교하기 위한 반복 사이즈 지정
			for (int j = 0; j < pullNum.size(); j++) {
				// 특정 리스트의 i값과 pullNum 리스트의 모든 숫자 비교, 동일한지 판단
				if (arr.get(i) == pullNum.get(j)) {
					// 동일 하다면 특정 리스트(i)의 값을 ranNum(j)로 대체 변경, 동일하지 않다면 다음 i값으로 이동
					arr.set(i, ranNum.get(j));
				}
			}
		}
		return arr;
	}

	// 로또 번호의 범위값을 지정하는 메소드
	int getRnum(int max, int min) {

		int rNum = 0;

		// nextInt(max - min + 1) + min; == min ~ max의 난수 생성
		rNum = ra.nextInt(max - min + 1) + min;

		return rNum;
	}

	// 로또 등수 표시 메소드
	String getRank(List<Integer> arr) {

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
		return "당첨 범위를 다시 지정해주세요.";

	}

}

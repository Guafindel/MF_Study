package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Lotto00002 {
	
	final static int MIN = 1;
	final static int MAX = 45;
	
	public static void main(String[] args) {
		Lotto00002 lotto = new Lotto00002();
		
		// 유효성검사 메서드 호출
		lotto.valCheck(args);		
	}
	
	/**
	 * <pre>
	 * 유효성 검사 메서드
	 * 
	 * 	유효성 검사 하기..
	 * 		1. 조건4의 TRUE 입력에 대한 검사			OK
	 * 		2. 1등번호가 6개의 숫자를 받고 있는지 검사	OK
	 * 		3. 1등번호 중복검사					OK
	 * 		4. args.length가 3개이상 입력되었는지(최소조건)	OK
	 * 		5. 조건3의 총개수가 각 등수의개수보다 작은지 검사 (커야함)	OK
	 * 		6. 1등 번호가 MIN 과 MAX 사이에 있는지 검사			OK
	 * 		7. 조건 1,2,3에 숫자가 입력되었는지
	 * </pre>
	 * @param args	: 	arguments 에 입력한 조건배열
	 */
	public void valCheck(String[] args) {
		// 23,12,9,2,13,1 1:1,2:3,3:10,4:50,5:100 1000 true
		// 1. TRUE 여부 (무작위출력)  -> 조건에 "TRUE"가 있으면 반환값 : true , 없으면 false
		boolean shuffleCheck = Arrays.asList(args).contains("TRUE");
		if (shuffleCheck == false) {	// args에 "TRUE"가 입력되지않으면 false
			if (args.length > 3) {		// "TRUE"를 입력하지 않고 순차적으로 출력할 것인지에 대한 조건 검사
				System.out.println("조건 4 'TRUE' 를 다시 입력해주세요");
				System.exit(0);
			}
		}
		
		// 2. 1등번호 개수 검사 
		String [] firstNum = args[0].split(",");
		if ( firstNum.length < 6 || firstNum.length > 6) {
			System.out.println("1등번호는 6개 입니다. 다시 입력해주세요.");
			System.exit(0);
		}
		
		// 3. 1등번호 중복검사 
		// 6. 1등 번호가 MIN 과 MAX 사이에 있는지 검사
		for (int i = 0; i < firstNum.length; i++) {
			int item = Integer.parseInt(firstNum[i]);
			if (item < MIN || item > MAX) {
				System.out.println("1등번호의 범위는 1~45 입니다. 다시 입력해주세요.");
				System.exit(0);
			}
			
			for (int j = 0; j < i; j++) {
				if ( firstNum[i].equals(firstNum[j]) ) {
					System.out.println("1등번호가 중복됩니다. 다시 입력해주세요.");
					System.exit(0);
				}
			}
		}
		
		// 4. args.length가 최소 3개이상 입력되었는지검사
		if (args.length < 3) {
			System.out.println("조건이 부족합니다. 다시 입력해주세요.");
			System.exit(0);
		}
		
		// 조건을 분리할 메서드 호출
		conditionMake(args);
	}
	
	/**
	 * args에 입력된 조건들을 분리할 메서드
	 * 	
	 *  	1. 1등번호를 추출해야함 -> args[0]에 1등번호를 입력
	 *  		-> String 배열로 선언되어있는 번호를 int 배열로 변환해주기.
	 *  	2. args[1]에 있는 조건을 분리한다..  -> 각등수마다 생성할 로또개수
	 *  		1) args[1]을 ','로 자른다.
	 *  		2) ','로 자른것을 ':'로 다시 자른다.
	 *  		3) ':'로 자른 값의 [0]은 등수 [1]은 개수가된다.
	 *  		4) 미등수의 key값은 마지막키의 다음번호가 된다.  --> map의 크기 + 1  : 마지막 key값으로 들어가기위해
	 *  		5) 미등수의 value값은 전체 생성수 - 각등수출력개수의 합이된다.
	 *  	3. 전체 생성될 개수 변수에 담아준다.  -> totalCount
	 *  
	 * @param args	:  arguments 에 입력한 조건배열
	 */
	public void conditionMake(String[] args) {
		// 1.입력된 로또 1등번호를 List로 변환해준다.
		String [] firstIndex = args[0].split(",");   // args[0]에 입력된 1등번호를
		int [] firstNum = new int[firstIndex.length];	// int배열에 옮기기 위한 작업
		// firstLottoNum -> 1등번호
		for (int i = 0; i < firstIndex.length; i++) {
			firstNum[i] = Integer.parseInt(firstIndex[i]);
		}
		List<Integer> firstLottoNum = new ArrayList<Integer>();	// int 배열에 있는 1등번호를 List에 담아주는 작업
		for (int i : firstNum) {
			firstLottoNum.add(i);
		}
		
		Set<Integer> aaa = new HashSet<Integer>();
		for(String item : firstIndex) {
			aaa.add(Integer.parseInt(item));
		}
		if(aaa.size() != 6) {
		}
		
		// 2. 각 등수마다 생성할 로또개수 조건을 분리해준다.
		String [] splitArr = args[1].split(","); 	// 1:1,2:3,3:10,4:50,5:100 를 ','로 스플릿
		List<String[]> rankList = new ArrayList<String[]>();	// 각 등수,개수가 하나의배열묶음으로 담긴 List
		for (int i = 0; i < splitArr.length; i++) {
			rankList.add(splitArr[i].split(":"));	// List에 ':'로 스플릿한 결과들을 배열로 담아준다.
		}
		
		// 3. 전체 로또 생성 개수
		int totalCount = Integer.parseInt(args[2]);

		// 4. 각 등수와 출력개수를 key,value에 담아준다
		Map<Integer, Integer> secondCondition = new HashMap<Integer, Integer>();  // 등수,갯수를 key,value로 구분하기위해
		// ':'로 자른 값의 [0]은 등수 [1]은 개수가된다.
		// 등수에 해당하는 개수를 Map의 key,value로 넣어준다. -> 등수 : key  개수 : value
		int rankCountSum = 0;	// 1~5등 출력개수의 합을 담을 변수  -> 미등수 출력개수를 구하기 위한 변수
		for (int i = 0; i < rankList.size(); i++) {
			// 반복돌며 List에 있는값을 배열에 담아준다
			String[] rankCountVlaue = rankList.get(i);
			// 담긴 배열의 0번지에는 로또등수값이 들어있고 1번지에는 등수에해당하는 개수값이 들어있다.
			int rankNumber = Integer.parseInt(rankCountVlaue[0]);     // 등수
			int rankCount = Integer.parseInt(rankCountVlaue[1]);	  // 개수
			//key에는 등수,  value에는 개수 를 넣어준다.
			// 1:1,2:3,3:10,4:50,5:100  -> ':' 를 기준으로 0번지에는 key값, 1번지에는 value값
			secondCondition.put(rankNumber, rankCount);
			rankCountSum += rankCount;	// 1~5등 출력개수를 변수에 합해준다.
		}
		
		// 미등수 로또의 출력 개수
		int restCount = totalCount - rankCountSum;	
		// 미등수의 key값은 map의 전체 크기 + 1 ---> 문제조건에서 주어진 각 등수들을 먼저 담고 마지막에 미등수를 담아주기위해
		// 미등수의 value 값은 ---> 전체출력개수 - 1~5등의 출력개수합
		secondCondition.put(secondCondition.size()+1, restCount);
		
		// 5. 조건3의 총개수가 각 등수의개수보다 작은지 검사 (커야함)
		if ( totalCount < rankCountSum) {
			System.out.println("총 출력개수가 부족합니다. 다시 입력해주세요");
			System.exit(0);
		}
		
		// 로또 생성 메서드 호출
		getLotto(firstLottoNum, secondCondition, totalCount);
	}

	
	/**
	 * 	로또생성메서드
	 * 
	 * 	배열의 자리값으로 교체해주는 방법
	 * 		배열의 값만큼 set 해야 할 규칙 찾기
	 * 			2등일 경우 : 0번지만 1개 교체
	 * 			3등일경우 : 0~1번지 2개 교체
	 * 			4등일경우 : 0~2번지 3개 교체
	 * 			5등일경우 : 0~3번지 4개 교체
	 * 			미둥수경우 : 0~4번지 5개교체 or 0~6번지 6개 교체
	 * 
	 * 	로또 생성 로직..
	 * 		1. 1등번호를 새로운 List에 복사 -> newLotto
	 * 		2. 복사된 1등번호를 셔플을 돌려 자리를 랜덤으로 바꿔준다.  -> 중복검사
	 * 		3. 그 List의 i번지를 새로운 랜덤번호로 바꿔준다.  -> i번지 : 2등은 0번지 1개교체
	 * 													 3등은 0,1번지 2개교체 
	 * 													 4등은 0,1,2번지 3개교체
	 * 													 5등은 0,1,2,3번지 4개교체
	 * 													미등수는 .. 0,1,2,3,4번지 5개 or 0,1,2,3,4,5번지 6개 교체
	 * 		4. 생성된 로또를 전체로또를 담을 List에 담아준다  -> LottoList
	 * 
	 * 	ranNum 의 중복체크
	 * 		1. 1등번호에 있는지 여부 검사.
	 * 		2. 새로생성되는 행에 있는지 중복검사.
	 * 
	 * @param firstLottoNum		: 1등번호
	 * @param secondCondition	: 등수와 개수를 담은 Map (key:등수, value:개수)
	 * @param totalCount		: 총 출력 개수
	 */
	public void getLotto(List<Integer> firstLottoNum, Map<Integer, Integer> secondCondition, int totalCount) {
		Map<Integer, List<List<Integer>>> map = new HashMap<Integer, List<List<Integer>>>();	// 출력을 위한 map -> key : 등수 , value : 생성된 로또 List
		List<List<Integer>> LottoList = new ArrayList<List<Integer>>();		// 등수별로 생성 될 로또들 담을 List
		List<Integer> newLotto = new ArrayList<Integer>();					// 새로생성되는 로또를 담을 List
		
		Iterator<Integer> iter = secondCondition.keySet().iterator();
		
		// 1등~5등, 미등수 로또 생성
		while (iter.hasNext()) {
			int rate = iter.next();					// 등수 : 1~5등 (key값)
			int rateCount = secondCondition.get(rate);	// 개수 : 각 등수의 해당하는 개수 (value값)
			int count = 0;		// 조건 범위를 설정하기위한 변수
			LottoList = new ArrayList<List<Integer>>();		// map 의 value에 들어갈 로또List
			while (count < rateCount) {		// 개수만큼 반복
				newLotto = new ArrayList<Integer>(firstLottoNum);	// 1등번호를 새로운List로 복사
				Collections.shuffle(newLotto);						// 복사된 1등번호의 자리를 셔플
				
				// 1~5등 로또 생성
				for (int i = 0; i < rate-1; i++) {					// 0부터 등수-1 만큼 반복 : 1등은 반복x 2등은 1자리 3등은 2자리 교체해야하기때문...
					int ranNum = getRandomNum();					// 설정한 범위 (1 ~ 45) 만큼 랜덤 번호 하나 생성
					if ( !firstLottoNum.contains(ranNum) && !newLotto.contains(ranNum) ) {	// 랜덤번호 중복체크
						newLotto.set(i, ranNum);					// 반복돌며 i번지에 중복체크한 랜덤번호로 교체
					} else {
						i--;										// 중복이있을시 i-- 하여 다시 랜덤번호 생성하도록
					}
				}
				
				// 미등수 로또 생성
				if (rate == secondCondition.size()) {				// 키값이 map의크기와 같을때 --> 키값이 마지막 키값일때 실행
					int index = (int)(Math.random() * 2 + 5);		// 5 or 6 랜덤 번호를 받는다 --> index값으로 쓰기 위해 --> 1등과 5개가다르거나 6개가 다르도록
					for (int i = 0; i < index; i++) {				// 생성된 index값 만큼 반복을 돌릴것
						int ranNum = getRandomNum();				// 1~45 랜덤한 수 생성
						if ( !firstLottoNum.contains(ranNum) && !newLotto.contains(ranNum) ) {  // 1~45 랜덤값 중복체크
							newLotto.set(i, ranNum);				// 즁복체크후 i번지까지 랜덤값으로 교체해주기
						} else {
							i--;
						}
					}
				}
				LottoList.add(newLotto);							// 생성된 로또를 List에 담아준다
				count++;											// 조건 범위 변수를 증가시킨다.
			}
			map.put(rate, LottoList);		// key : 등수 , value : 생성된로또List
		}
		
		// 출력메서드 호출
		output(firstLottoNum, map);
	}
	
	/**
	 * 랜덤 번호를 생성해주는 메서드 
	 * 
	 * @return	: 1~45의 랜덤번호
	 */
	public int getRandomNum() {
		int ranNum = (int)(Math.random() * MAX + MIN);

		return ranNum;
	}
	
	/**
	 * 전체로또를 출력할 출력메서드
	 * 
	 * @param firstLottoNum		: 1등 번호
	 * @param map				: 등수와 해당등수의 로또들을 담고 있는 map
	 */
	public void output(List<Integer> firstLottoNum, Map<Integer, List<List<Integer>>> map) {
		
		/*
		 * 	TRUE 입력 시 적절하게 섞어서 출력...
		 * 		
		 */
		
		Iterator<Integer> iter = map.keySet().iterator();
		
		while (iter.hasNext()) {
			int rate = iter.next();   // map의 key값 : 등수
			List<List<Integer>> lottoList = map.get(rate);	// map의 value값 : 로또List
			
			for (int i = 0; i < lottoList.size(); i++) {
				if (rate == map.size()) {					// map.size() = 미등수의 key값이기때문에 출력문에 등수를 표시하지않는다.
					System.out.println(lottoList.get(i));
				} else {
					System.out.println(lottoList.get(i) + "  -> " + rate + "등");		// 로또번호와 함께 등수 표시
				}
			}
		}
	}
}

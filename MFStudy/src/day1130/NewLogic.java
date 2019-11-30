package day1130;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class NewLogic {

	private int lottoSize = 6;

	private final static int firstCount = 1;

	private int secondCount = 0;
	private int thirdCount = 0;
	private int fourthCount = 0;
	private int fifthCount = 0;
	private int sixCount = 0;
	private int sevenCount = 0;
	private int eightCount = 0;
	private int nineCount = 0;

	private int totalCount = 0;

	private int Min = 1;
	private int Max = 45;

	private boolean setMix = false;

	private Set<Integer> firstSet = new LinkedHashSet<Integer>();
	private Map<Integer, Integer> countMap = new LinkedHashMap<Integer, Integer>();

	/**
	 * 등수가 있는 당첨 로또 리스트
	 */
	private List<Set<Integer>> firstList = new ArrayList<Set<Integer>>();
	private List<Set<Integer>> secondtList = new ArrayList<Set<Integer>>();
	private List<Set<Integer>> thirdList = new ArrayList<Set<Integer>>();
	private List<Set<Integer>> fourthList = new ArrayList<Set<Integer>>();
	private List<Set<Integer>> fifthList = new ArrayList<Set<Integer>>();
	private List<Set<Integer>> sixList = new ArrayList<Set<Integer>>();
	private List<Set<Integer>> sevenList = new ArrayList<Set<Integer>>();
	private List<Set<Integer>> eightList = new ArrayList<Set<Integer>>();
	private List<Set<Integer>> nineList = new ArrayList<Set<Integer>>();

	/**
	 * 등수를 가진 당첨 로또 리스트를 한번에 담아둔 리스트
	 */
	private List<Set<Integer>> allRankList = new ArrayList<Set<Integer>>();

	/**
	 * 등수가 없는 미당첨 로또 리스트
	 */
	private List<Set<Integer>> noRankList = new ArrayList<Set<Integer>>();

	public static void main(String[] args) {

		NewLogic logic = new NewLogic();

		try {

			logic.getValidation(args);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// 행간 검사를 할 때 셋 자체를 셋에 담아서 하는 방식은 불가능하고 셋의 요소끼리 검사를 해야한다.

	/**
	 * 사용자가 입력한 Param에 대한 유효성 검사
	 * 
	 * @param params 사용자가 입력한 값
	 * @return 성공적으로 유효성검사를 마쳤을 때의 문자 반환(success)
	 * @throws Exception
	 */
	private String getValidation(String[] params) throws Exception {

		String result = "success";

		// 프로그램이 실행되는데에는 필수 3개의 요소가 필요하다. 필수 요소가 들어왔는지에 대한 체크
		if (params.length < 3) {
			throw new Exception(
					"필수 파라미터를 입력해야 합니다. param1(필수요소) : 1등 기준 숫자(, 으로 구분), param2(필수요소) : 등수에 해당하는 로또의 개수 지정(n:n,n2:n2...), param3(필수요소) : 전체 생성 개수");
		}

		String[] firstNum = params[0].split(",");

		// 1등의 최대 개수 체크
		if (firstNum.length > 10) {
			throw new Exception(String.format("1등 로또 숫자의 최대 개수는 %d개를 초과할 수 없습니다.", 10));
		}

		// 1등의 개수가 지정된 값과 일치하는지 체크
		if (firstNum.length != lottoSize) {
			throw new Exception(String.format("1등 로또 숫자는 %d개의 개수로 지정되어야 합니다.", lottoSize));
		}

		// 해쉬셋에 첫 번째 param요소들을 집어넣어 중복을 제거한다.
		for (String param1 : firstNum) {
			firstSet.add(Integer.parseInt(param1));
		}
		// 만약 같은 번호가 지정되어있다면 사이즈의 변화가 일어나 체크 가능.
		if (firstSet.size() != lottoSize) {
			throw new Exception(String.format("1등 로또 숫자 %d개의 요소들은 중복이 없어야 합니다.", lottoSize));
		}

		// 1등 번호들의 범위를 지정된 max와 min의 사이값으로 제한한다.
		for (int firstNumElement : firstSet) {
			if (firstNumElement < Min || firstNumElement > Max) {
				throw new Exception(String.format("1등 로또의 숫자들은  %d이상 %d이하의 숫자로 구성해야 합니다.", Min, Max));
			}
		}

		// 특수문자에서 ,과 :을 제외한 나머지를 모두 제한하고 문자또한 제한하도록 split과 parseInt로 작성
		String[] rankCountNum = params[1].split(",|:");

		if (rankCountNum.length / 2 > firstNum.length - 1) {
			throw new Exception(String
					.format("param요소 두번째인 (등수:개수)를 지정할 때 이 묶음의 총 개수는 1등 로또 번호의 총 개수 %d보다 하나 적게 지정해야 합니다.", lottoSize));
		}

		// 숫자가 아닌 문자나 특수문자 따위가 들어간다면 parseInt에서 에러가 나므로 모두 숫자로 입력하도록 제한할 수 있다.
		for (int i = 0; i < rankCountNum.length / 2; i++) {
			int rank = Integer.parseInt(rankCountNum[i * 2]);
			int rankCount = Integer.parseInt(rankCountNum[(i * 2) + 1]);

			switch (rank) {
			case 2:
				countMap.put(rank, rankCount);
				secondCount = rankCount;
				break;
			case 3:
				countMap.put(rank, rankCount);
				thirdCount = rankCount;
				break;
			case 4:
				countMap.put(rank, rankCount);
				fourthCount = rankCount;
				break;
			case 5:
				countMap.put(rank, rankCount);
				fifthCount = rankCount;
				break;
			case 6:
				countMap.put(rank, rankCount);
				sixCount = rankCount;
				break;
			case 7:
				countMap.put(rank, rankCount);
				sevenCount = rankCount;
				break;
			case 8:
				countMap.put(rank, rankCount);
				eightCount = rankCount;
				break;
			case 9:
				countMap.put(rank, rankCount);
				nineCount = rankCount;
				break;
			}
		}

		// 초기값을 1로 잡은 이유는 1등 로또는 1개로 기본 제한할 것이기 때문에
		int totalRankCount = 1;

		Iterator<Integer> keys = countMap.keySet().iterator();
		while (keys.hasNext()) {
			int key = keys.next();
			totalRankCount += countMap.get(key);
		}

		totalCount = Integer.parseInt(params[2]);

		int noRankCount = totalCount - totalRankCount;

		// 총 개수가 500개인데 각 등수 개수 지정의 합이 이를 초과하는 것을 제한한다.
		if (totalRankCount > totalCount) {
			throw new Exception(String.format("각 등수의 합산 개수 %d는 총 개수  %d를 초과해서는 안됩니다.", totalRankCount, totalCount));
		}

		// 무작위 배치 옵션을 선택했을 시 setMix를 true값으로 변경한다. 다른 제한은 두지 않는다.
		if (params.length > 3) {
			setMix = "TRUE".equals(params[3]);
		}

		/*
		 * 등수 합산 총 개수가 비등수의 개수보다 낮게 제한해야 하는 것에 대한 논의가 필요함. if (totalRankCount >
		 * noRankCount) { throw new Exception(String.
		 * format("등수로 지정한 개수의 총 합산은 비등수 개수보다 적어야 합니다.(등수 : %d, 비등수 : %d)",
		 * totalRankCount, noRankCount)); }
		 */

		// 모든 유효성 검사 통과했을 때 1등 로또 리스트 추가
		firstList.add(firstSet);

		return result;
	}

	/**
	 * @param min 범위의 최소값
	 * @param max 범위의 최대값
	 * @return min과 max 사이의 랜덤 수
	 */
	private int getRnum(int min, int max) {

		Random random = new Random();

		int rNum = 0;
		// nextInt(max - min + 1) + min; == min ~ max의 난수 생성
		rNum = random.nextInt(max - min + 1) + min;

		return rNum;
	}

	/**
	 * @param rank 2등~10등까지 원하는 등수 입력
	 * @return 입력한 등수에 해당하는 로또 set 반환
	 */
	private Set<Integer> makeLotto(int rank) {

		// 만드는 로또에서 교체할 숫자 개수(9등이라면 8개)
		int exchangeNumCount = rank - 1;

		// 만드는 로또에서 유지할 숫자 개수(9등이라면 2개)
		int remainNumCount = lottoSize - exchangeNumCount;

		Set<Integer> rankSet = new LinkedHashSet<Integer>();

		List<Integer> copyList = new ArrayList<Integer>();
		List<Integer> dummyList = new ArrayList<Integer>();

		copyList.addAll(this.firstSet);

		while (dummyList.size() == lottoSize) {
			int rNum = getRnum(0, this.lottoSize - 1);

			// 1등 리스트가 가진 동일한 숫자를 남길 숫자만큼 뽑아내 삽입
			if (dummyList.size() < remainNumCount) {
				int getNum = copyList.get(rNum);
				if (!(dummyList.contains(getNum))) {
					dummyList.add(getNum);
				}
			} else {
				int getNum = getRnum(this.Min, this.Max);
				if (!(dummyList.contains(getNum)) && !(dummyList.contains(this.firstSet))) {
					dummyList.add(getNum);
				}
			}
		}

		// 섞어주기
		Collections.shuffle(dummyList);
		if (dummyList.size() == this.lottoSize) {
			for (int i = 0; i < dummyList.size(); i++) {
				rankSet.add(dummyList.get(i));
			}
		}

		return rankSet;
	}

	private void getRankList(int numCount, int rank, List<Set<Integer>> numList) {
		
		if(numCount > 0) {
			while(numList.size() == numCount) {
				Set<Integer> numSet = makeLotto(rank);
				
				if(!(numList.contains(numSet))) {
					if(lineOverlapCheck(numSet) == false) {
						numList.add(numSet);
						this.allRankList.add(numSet);
					}
				}
			}
		}
	}

	private void makeRankLottoList() {

		getRankList(this.secondCount, 2, this.secondtList);

		getRankList(this.thirdCount, 3, this.thirdList);

		getRankList(this.fourthCount, 4, this.fourthList);

		getRankList(this.fifthCount, 5, this.fifthList);

		getRankList(this.sixCount, 6, this.sixList);

		getRankList(this.sevenCount, 7, this.sevenList);

		getRankList(this.eightCount, 8, this.eightList);

		getRankList(this.nineCount, 9, this.nineList);

	}

	/**
	 * 미등수 로또set을 만들어 리스트에 삽입
	 */
	private void makeNoRankLottoList() {

		int noRankCount = this.totalCount - (1 + this.secondCount + this.thirdCount + this.fourthCount + this.fifthCount
				+ this.sixCount + this.sevenCount + this.eightCount + this.nineCount);

		while (this.noRankList.size() == noRankCount) {
			int rNum = getRnum(this.lottoSize - 1, this.lottoSize);
			Set<Integer> noRankSet = makeLotto(rNum);

			if (!(this.noRankList.contains(noRankSet))) {
				this.noRankList.add(noRankSet);
			}
		}
	}

	/**
	 * @param rankSet 행 중복 검사 대상(특정 로또 set)
	 * @return 중복대상이 있을시 true
	 */
	private boolean lineOverlapCheck(Set<Integer> rankSet) {

		boolean check = this.allRankList.contains(rankSet);

		return check;
	}

	private int getCountNum(int rank) {

		int result = 0;

		return result;

	}

	private String printLotto() {

		String result = "";

		return result;
	}
}

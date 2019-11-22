package day13;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <pre>
 * 이 클래스는 아래와 같은 로직 구현하는것
 * 
 * i.	LottoMaker 23,12,9,2,43,32 1:1,2:3,3:10,4:50,5:100 500   1~5등, 미등수가 순서대로 나옴
 * ii.	LottoMaker 23,12,9,2,43,32 1:1,2:3,3:10,4:50,5:100 500 TRUE  1~5등, 미등수가 적절하게 섞여서 나옴
 * </pre>
 *
 * @author JeongY.Eom
 * @date 2019. 7. 22.
 * @time 오전 10:12:54
 **/
public class LottoMaker {
	private Set<Integer> firstSet = new LinkedHashSet<>();

	/**
	 * 한행에 들어가는 숫자 갯수
	 */
	private int itemCount = 6;

	private int secondCount = 0;
	private int thirdCount = 0;
	private int fourthCount = 0;
	private int fifthCount = 0;

	private int totalCount = 0;

	private int minNumber = 1;
	private int maxNumber = 45;

	private boolean printMix = false;

	/**
	 * 등수/행으로 구성됨
	 */
	private Map<Integer, List<Set<Integer>>> lottoMap = new LinkedHashMap<>();
	/**
	 * 미등록 구성리스트
	 */
	private List<Set<Integer>> noLottoList = new ArrayList<>();

	public static void main(String[] args) {
		LottoMaker lottoMaker = new LottoMaker();

		try {
			// 1. 입력 Param 체크
			lottoMaker.paramValidation(args);

			// 2. 등수 생성
			lottoMaker.makeLottoGrade();

			// 3. 미등수 생성
			lottoMaker.makeNoLottoGrade();

			// 4. 출력
			lottoMaker.printGrade();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 호출시 입력한 Param(논리적인 오류만 체크)
	 * 
	 * @param param
	 * @throws Exception
	 */
	private void paramValidation(String[] param) throws Exception {
		// 1. 입력한 Param 갯수 체크
		if (param.length < 2) {
			throw new Exception("파라메터가 없습니다.\nP1(필수) : 1등숫자(,로구분) / P2(필수) : 각등수별 갯수(1:1,2:3...) / P3(필수) : 생성할 전체 수 / P4(옵션) : Mix여부(TRUE)");
		}
		// 2. 항목별 순서체크
		// 2.1.1 1등 체크 갯수체크
		String[] first = param[0].split(",");
		if (first.length != itemCount) {
			throw new Exception(String.format("1등은 %d개의 숫자로 구성되야 합니다.", itemCount));
		}

		// 2.1.2 1등 중복체크
		for (String item : first) {
			this.firstSet.add(Integer.parseInt(item));
		}
		if (this.firstSet.size() != itemCount) {
			throw new Exception(String.format("1등 숫자 %d개는 중복이 없어야 합니다.", itemCount));
		}

		// 2.1.3 1등 범위체크
		for (int item : this.firstSet) {
			if (minNumber > item || maxNumber < item) {
				throw new Exception(String.format("1등 각 숫자는 %d 이상, %d 이하만 가능합니다.", minNumber, maxNumber));
			}
		}

		// 2.2.1 각항목별 갯수 체크
		// 1:1,2:3,3:10,4:50,5:100
		String[] mapping = param[1].split(",");
		for (String item : mapping) {
			int divIdx = item.indexOf(":");

			String grade = item.substring(0, divIdx);
			int gradeCount = Integer.parseInt(item.substring(divIdx + 1));

			switch (grade) {
			case "2":
				this.secondCount = gradeCount;
				break;
			case "3":
				this.thirdCount = gradeCount;
				break;
			case "4":
				this.fourthCount = gradeCount;
				break;
			case "5":
				this.fifthCount = gradeCount;
				break;
			}
		}
		int totalGradeCount = 1 + secondCount + thirdCount + fourthCount + fifthCount;
		this.totalCount = Integer.parseInt(param[2]);
		int noGradeCount = this.totalCount - totalGradeCount;
		// 2.2.2 등수와 비등수 숫자 비교
		if (totalGradeCount > noGradeCount) {
			throw new Exception(String.format("등수에 포함되는 갯수는 비등수 보다 작아야 합니다.(등수 : %d, 비등수 : %d)", totalGradeCount, noGradeCount));
		}

		// 3. mix여부
		if (param.length > 3) {
			this.printMix = "TRUE".equals(param[3]);
		}

		// 이상없으면. 1등 추가
		List<Set<Integer>> firstList = new ArrayList<>();
		firstList.add(firstSet);
		this.lottoMap.put(1, firstList);

	}

	/**
	 * 랜덤한 숫자 생성(양끝 포함)
	 * 
	 * @param fromInt
	 *            시작숫자
	 * @param toInt
	 *            종료숫자
	 * @return
	 */
	private int makeRandom(int fromInt, int toInt) {
		int retValue = (int) (Math.random() * toInt) + fromInt;
		return retValue;
	}

	/**
	 * 등수 생성
	 */
	private void makeLottoGrade() {
		// 2등 생성
		if (secondCount > 0) {
			while (getGradeCount(2) < secondCount) {
				Set<Integer> gradeSet = makeGrade(2);

				if (existsGrade(gradeSet) == false) {
					addGradeSet(2, gradeSet);
				}
			}
		}
		// 3등 생성
		if (thirdCount > 0) {
			while (getGradeCount(3) < thirdCount) {
				Set<Integer> gradeSet = makeGrade(3);

				if (existsGrade(gradeSet) == false) {
					addGradeSet(3, gradeSet);
				}
			}
		}
		// 4등 생성
		if (fourthCount > 0) {
			while (getGradeCount(4) < fourthCount) {
				Set<Integer> gradeSet = makeGrade(4);

				if (existsGrade(gradeSet) == false) {
					addGradeSet(4, gradeSet);
				}
			}
		}
		// 5등 생성
		if (fifthCount > 0) {
			while (getGradeCount(5) < fifthCount) {
				Set<Integer> gradeSet = makeGrade(5);

				if (existsGrade(gradeSet) == false) {
					addGradeSet(5, gradeSet);
				}
			}
		}
	}

	/**
	 * 등수에 안든거 구하기
	 */
	private void makeNoLottoGrade() {
		int makeCount = totalCount - (1 + secondCount + thirdCount + fourthCount + fifthCount);
		do {
			Set<Integer> gradeSet = makeGrade(itemCount);
			if (this.noLottoList.contains(gradeSet) == false) {
				this.noLottoList.add(gradeSet);
			}
		} while (this.noLottoList.size() < makeCount);
	}

	/**
	 * 등수 생성
	 * 
	 * @param grade
	 *            2 : 1등과 1개틀림, 3 : 1등과 2개틀림, 4 : 1등과 3개틀림, 5 : 1등과 4개틀림
	 * @return
	 */
	private Set<Integer> makeGrade(int grade) {
		int replaceCount = grade - 1; // 1등에서 바꿀 숫자 
		int keepCount = itemCount - replaceCount; // 1등에서 유지할 숫자

		List<Integer> gradeList = new ArrayList<>();
		do {
			// 1등과 동일한 거 추가
			int item = 0;
			if (gradeList.size() < keepCount) {
				item = (int) firstSet.toArray()[makeRandom(0, itemCount - 1)];
				if(gradeList.contains(item) == false) {
					gradeList.add(item);
				}
			} else { // 새로 생성해서 추가
				item = makeRandom(minNumber, maxNumber);
				if(this.firstSet.contains(item) == false && gradeList.contains(item) == false) {
					gradeList.add(item);
				}
			}
		} while (gradeList.size() < itemCount);
		
		// 순서섞기
		Collections.shuffle(gradeList);
		Set<Integer> gradeSet = new LinkedHashSet<>();
		for(Integer item : gradeList) {
			gradeSet.add(item);
		}
		
		return gradeSet;
	}

	/**
	 * 기존에 존재 하는지 체크
	 * 
	 * @param gradeSet
	 * @return
	 */
	private boolean existsGrade(Set<Integer> gradeSet) {
		boolean retValue = false;
		for (int item : this.lottoMap.keySet()) {
			List<Set<Integer>> row = this.lottoMap.get(item);
			retValue = row.contains(gradeSet);

			if (retValue) {
				break;
			}
		}
		return retValue;
	}

	/**
	 * 등수에 해당하는 행수 구하기
	 * 
	 * @param grade
	 * @return
	 */
	private int getGradeCount(int grade) {
		int retValue = 0;
		if (this.lottoMap.containsKey(grade)) {
			retValue = this.lottoMap.get(grade).size();
		}

		return retValue;
	}

	/**
	 * 등수 맵에 추가
	 * 
	 * @param grade
	 * @param gradeSet
	 */
	private void addGradeSet(int grade, Set<Integer> gradeSet) {
		List<Set<Integer>> rowList = this.lottoMap.containsKey(grade) == false ? new ArrayList<>() : this.lottoMap.get(grade);
		rowList.add(gradeSet);

		this.lottoMap.put(grade, rowList);
	}

	/**
	 * 출력
	 */
	private void printGrade() {
		int index = 1;
		if (this.printMix == false) { // 생성된 순서대로 출력
			for (int key : this.lottoMap.keySet()) {
				List<Set<Integer>> list = this.lottoMap.get(key);

				for (Set<Integer> item : list) {
					System.out.println(String.format("%s. %s => %d등", index++, item, key));
				}
			}
			for (Set<Integer> item : this.noLottoList) {
				System.out.println(String.format("%s. %s => 등수없음", index++, item));
			}
		} else { // 적당히 섞어서 출력
			List<Set<Integer>> lottoList = new ArrayList<>();
			// 등수에 포함되는건 마지막에 등수를 표기하여 가공.
			for (int key : this.lottoMap.keySet()) {
				List<Set<Integer>> list = this.lottoMap.get(key);

				for (Set<Integer> item : list) {
					int addValue = key * 100;
					item.add(addValue);
					lottoList.add(item);
				}
			}
			Collections.shuffle(lottoList);

			// 몇개마다 등수가 나올지 가공
			int loopUnit = this.noLottoList.size() / lottoList.size();
			int subListIdx = 0;
			for (Set<Integer> item : lottoList) {
				for (Set<Integer> noItem : this.noLottoList.subList(subListIdx, subListIdx + loopUnit)) {
					System.out.println(String.format("%s. %s => 등수없음", index++, noItem));
				}

				// 등수는 마지막에 등수를 표기했으니 가공
				Set<Integer> printItem = new LinkedHashSet<>();
				int grade = 0;

				Integer[] gradeItem = item.toArray(new Integer[] {});
				for (int i = 0, n = gradeItem.length; i < n; i++) {
					if (i < n - 1) {
						
						printItem.add(gradeItem[i]);
					} else {
						grade = gradeItem[i] / 100;
					}
				}

				System.out.println(String.format("%s. %s => %s등", index++, printItem, grade));

				subListIdx += loopUnit;
			}
			for (Set<Integer> item : this.noLottoList.subList(subListIdx, this.noLottoList.size())) {
				System.out.println(String.format("%s. %s => 등수없음", index++, item));
			}
		}
	}
}
// :)--
package day12;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class NewLottoList4 {
	final int MINNUM = 1;
	final int MAXNUM = 45;

	// 랜덤 출력 유무
	boolean random = false;

	// 로또 번호의 갯수, 등수
	int lottoCount = 6;
	String lottoName = "";

	// 각 등수 별로 출력할 인원 수를 담을 변수 선언 (1~5등, 미등수, 전체개수)
	int first = 0;
	int second = 0;
	int third = 0;
	int fourth = 0;
	int fifth = 0;
	int noRank = 0;
	int total = 0;
	
	// 당첨자 수, 랜덤 출력 시 나눌 기준
	int rank = 0;
	int div = 0;
	
	// 1등 번호 list
	List<Integer> lottoNum = new ArrayList<Integer>();
	// 등수별, 당첨, 미당첨 list
	List<List<Integer>> firstList, secondList, thirdList, fourthList, fifthList, noLottoList,
			allRankList = new ArrayList();

	public static void main(String[] args) {
		NewLottoList4 lotto = new NewLottoList4();

		// param 유효성 검사
		lotto.argsValiddation(args);

	}

	public void argsValiddation(String[] args) {
		// 입력 받은 argument를 변수에 담는다.
		String lottoNumArgs = args[0];
		String rankAll = args[1];
		String totalPerson = args[2];
		String[] rankAllSplit = null;

		// 1등 번호 저장을 위해 split하여 String배열에 담고 list에 넣음
		String[] lottoSplit = lottoNumArgs.split(",");

		// 1등 번호가 지정된 갯수가 맞는지 확인
		if (lottoSplit.length != lottoCount) {
			System.out.println("당첨 번호는 " + lottoCount + "개여야 합니다.");
			System.exit(0);
		}

		// 숫자와 기호 ,:만 포함되었을 시
		if (lottoNumArgs.matches("^[0-9,:]*$")) {
			for (int i = 0; i < lottoSplit.length; i++) {

				// int로 변환하여 list에 담기
				lottoNum.add(Integer.parseInt(lottoSplit[i]));

				// 1등 번호가 지정된 최소값, 최대값 범위 내에 있는지 확인
				if (!(lottoNum.get(i) >= MINNUM && lottoNum.get(i) <= MAXNUM)) {
					System.out.println(MINNUM + "과 " + MAXNUM + "사이의 수를 입력해주세요.");
					System.exit(0);
				}
			}
		} else {
			System.out.println("입력하신 문자가 올바르지 않습니다.");
			System.exit(0);
		}

		// 각 등수 별 인원을 먼저 split 후, 인원 별 명수만 따로 int 변수에 담기
		rankAllSplit = rankAll.split(",");

		first = Integer.parseInt(rankAllSplit[0].substring(2));
		second = Integer.parseInt(rankAllSplit[1].substring(2));
		third = Integer.parseInt(rankAllSplit[2].substring(2));
		fourth = Integer.parseInt(rankAllSplit[3].substring(2));
		fifth = Integer.parseInt(rankAllSplit[4].substring(2));
		total = Integer.parseInt(totalPerson);
		noRank = Integer.parseInt(totalPerson) - first - second - third - fourth - fifth;

		// 당첨자 수, 랜덤 출력 시 나눌 기준
		rank = total - noRank;
		div = total / rank;
		
		// total보다 당첨자 수가 많으면 종료
		if (total < rank) {
			System.out.println("전체 개수는 당첨 개수보다 높아야 합니다.");
			System.exit(0);
		}
		
		// TRUE를 입력하지 않았을 시 boolean변수 안에 false값을 넣고 TRUE 입력 시 String변수로 받아 boolean값으로 변경
		if (args.length == 3) {
			random = false;
		} else if (args.length == 4) {
			String ran = args[3];
			for (int i = 0; i < ran.length(); i++) {
				if (ran.equals("TRUE")) {
					random = true;
				} else {
					System.out.println("입력하신 문자가 올바르지 않습니다.");
					System.exit(0);
				}
			}
		} else {
			System.out.println("입력하신 문자가 올바르지 않습니다.");
			System.exit(0);
		}

		// 로또 번호 생성
		inputLottoList();

	}

	public void inputLottoList() {
		// 1등 번호와 중복되어야하는 개수
		int condition = 0;

		// 각 등수별로 담아줄 list 선언
		firstList = new ArrayList();
		secondList = new ArrayList();
		thirdList = new ArrayList();
		fourthList = new ArrayList();
		fifthList = new ArrayList();
		noLottoList = new ArrayList();

		// list를 전부를 담을 list를 생성.
		allRankList = new ArrayList();

		// 각 등수 별로 1등 번호와 같아야하는 개수를 정해 함수 실행하고 list로 담기
		for (int i = 0; i < first; i++) {
			condition = lottoCount;
			firstList.add(lottoNum);
		}
		// 2등 당첨자 수만큼 list에 담기
		for (int i = 0; i < second; i++) {
			condition = lottoCount - 1;
			secondList.add(RankLotto(condition, lottoNum, MAXNUM, MINNUM));
		}
		for (int i = 0; i < third; i++) {
			condition = lottoCount - 2;
			thirdList.add(RankLotto(condition, lottoNum, MAXNUM, MINNUM));
		}
		for (int i = 0; i < fourth; i++) {
			condition = lottoCount - 3;
			fourthList.add(RankLotto(condition, lottoNum, MAXNUM, MINNUM));
		}
		for (int i = 0; i < fifth; i++) {
			condition = lottoCount - 4;
			fifthList.add(RankLotto(condition, lottoNum, MAXNUM, MINNUM));
		}
		for (int i = 0; i < noRank; i++) {
			// condition = (int) (Math.random() * 2) + lottoCount - 6;
			condition = (int) (Math.random() * 2);
			noLottoList.add(RankLotto(condition, lottoNum, MAXNUM, MINNUM));
		}

		// 등수 별 list 한 곳에 담기
		allRankList.addAll(firstList);
		allRankList.addAll(secondList);
		allRankList.addAll(thirdList);
		allRankList.addAll(fourthList);
		allRankList.addAll(fifthList);

		// 출력
		PrintLotto();

	}

	/**
	 * 2~5등, 미등수를 생성하는 함수
	 * 
	 * @param condition : 1등 번호와 중복되는 번호의 수
	 * @param lottoNum  : 1등 번호
	 * @param MAXNUM    : 랜덤 최대값
	 * @param MINNUM    : 랜덤 최소값
	 * @return : 로또 번호를 생성하여 리턴
	 */
	public List<Integer> RankLotto(int condition, List<Integer> lottoNum, int MAXNUM, int MINNUM) {
		int lottoNumber = 0;
		Random ran = new Random();

		// 1등 번호를 담는 list
		List<Integer> rankLotto = new ArrayList<Integer>(lottoNum);

		// 랜덤으로 구한 index를 각 등수의 자리수가 되도록 제거
		while (true) {
			int index = ran.nextInt(rankLotto.size());
			rankLotto.remove(index);

			// 전달 받은 각 등수의 사이즈가 되면 break;
			if (rankLotto.size() == condition) {
				break;
			}
		}
		while (true) {
			lottoNumber = (int) (ran.nextInt(MAXNUM) + MINNUM);

			// 1등 번호와 중복되지 않고, 현재 list내에 중복이 없을 시 난수를 담기
			if (!lottoNum.contains(lottoNumber) && !rankLotto.contains(lottoNumber)) {
				rankLotto.add(lottoNumber);
			}
			// 정해진 자리 수가 채워졌을 때 break; (현재는 6개)
			if (rankLotto.size() == lottoCount) {
				break;
			}
		}
		return rankLotto;

	}

	/**
	 * 
	 * 출력하는 메소드
	 */
	public void PrintLotto() {
		// 출력 시 번호
		int num = 1;
		// 당첨, 미당첨 리스트 사이즈
		int rankSize = allRankList.size();
		int noLottoSize = noLottoList.size();
		
		// 랜덤 출력 미완성이라 랜덤 출력할 리스트
		List<List<Integer>> prepareList = new ArrayList();
		
		System.out.print(
				"1:" + first + ",2:" + second + ",3:" + third + ",4:" + fourth + ",5:" + fifth + " " + total + "\n");

		System.out.println("=========== 결과 ===========");

		// 당첨리스트 + 미당첨리스트의 사이즈
		int totalSize = allRankList.size() + noLottoList.size();

		//System.out.println("나눌 값" + div + " 토탈 사이즈 : " + totalSize + " 당첨 사이즈 : " + rankSize + " 미당첨 사이즈 : " + noLottoSize);
		
		
		// TURE일 시 적절하게 출력
		if (random == true) {
			prepareList.addAll(allRankList);
			prepareList.addAll(noLottoList);
			Collections.shuffle(prepareList);
			
			for(int i = 0; i < prepareList.size(); i++) {
				LottoName(prepareList.get(i));
				System.out.println((i + 1) + ". " + prepareList.get(i) + lottoName);
			}

			
			
			/* 랜덤 출력 아직 미완성입니다 죄송합니다..
			 * for (int i = 0; i < totalSize; i++) {
			 * 
			 * // 미당첨 > 당첨 if(noLottoList.size() > allRankList.size()) { if(totalSize % div
			 * == 0) { for(int j = 0; j < div; j++) { System.out.println("미당첨 : " +
			 * noLottoList.get(i)); //noLottoList.remove(i); }
			 * 
			 * } if(totalSize % div != 0) { System.out.println("맞냐구" + allRankList.get(i));
			 * //allRankList.remove(i); }
			 * 
			 * // 당첨 > 미당첨 }else { if(totalSize % div == 0) { System.out.println("맞냐구" +
			 * allRankList.get(i)); //allRankList.remove(i); } if(totalSize % div != 0) {
			 * System.out.println("미당첨 : " + noLottoList.get(i)); //noLottoList.remove(i); }
			 * 
			 * }
			 * 
			 * }
			 */

		// 순서대로 출력
		} else {
			for (int j = 0; j < allRankList.size(); j++) {
				LottoName(allRankList.get(j));
				System.out.println(num + ". " + allRankList.get(j) + lottoName);
				num++;
			}
			for (int c = 0; c < noLottoList.size(); c++) {
				System.out.println(num + ". " + noLottoList.get(c));
				num++;
			}

		}

	}

	/**
	 * 각 등수 별 lottoName을 정하기 위한 메소드
	 * 
	 * @param list 출력 메소드에서 전달 받은 list 값
	 */
	public void LottoName(List<Integer> list) {
		int count = 0;
		int j = 0;

		// 당첨번호와 비교하여 count
		while (j < list.size()) {
			count = 0;
			for (Integer r : list) {
				// 1~5등까지 다 넣은 리스트
				for (Integer c : lottoNum) {
					// 로또 당첨번호
					if (r == c) {
						count++;
						break;
					}
				}
			}

			// count의 값대로 출력
			switch (count) {
			case 0:
				lottoName = " ";
				break;
			case 1:
				lottoName = " ";
				break;
			case 2:
				lottoName = " -> 5등";
				break;
			case 3:
				lottoName = " -> 4등";
				break;
			case 4:
				lottoName = " -> 3등";
				break;
			case 5:
				lottoName = " -> 2등";
				break;
			case 6:
				lottoName = " -> 1등";
				break;
			default:
				System.out.println("정상적으로 계산되지 않았습니다");
				return;

			}
			j++;
		}
	}

}
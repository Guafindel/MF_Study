package test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
/******************************************************
 * 각 등수의 맞은 수를 관리하는 클래스-각등수의 맞은수 지정
 ****************************************************/
class LottoSett{
	private int[] countRank = {6,5,4,3,2,1};//등수{0번지부터 1등,2등...}
	private int[] countNonRank= {0};//미등수

	public int[] getCountRank() {
		return countRank;
	}

	public int[] getCountNonRank() {
		return countNonRank;
	}

	public void setCountNonRank(int[] countNonRank) {
		this.countNonRank = countNonRank;
	}

	public void setCountRank(int[] countRank) {
		this.countRank = countRank;
	}
}
public class Lotto7 {
	final static int STARTNUM=1;
	final static int ENDNUNM=45;
	public static void main(String[] args) {
		/***************************************************************************
		 *  선언부 
		 *  ************************************************************************/
		Lotto7 lotto7 = new Lotto7();
		LottoSett set =new LottoSett();
		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> firstNum = new ArrayList<Integer>();// 1등

		ArrayList<Integer> randomTemp;// 로또 생성

		/**
		 * 총 등수별 만들어야할 갯수
		 */
		ArrayList<Integer> numberForRank = new ArrayList<Integer>();// 총 등수별 만들어야할 갯수

		/* 이제 등수별 만들어야할 갯수 */
		ArrayList<Integer> makeNumberForRank = new ArrayList<Integer>();// 이제 등수별 만들어야할 갯수

		// adfasfasdfaffafsdfsf
		ArrayList<Integer> exisitNumberRank = new ArrayList<Integer>();// 등수별 만들어진 갯수
		// adfasfasfasfasffasdf
		ArrayList<Integer> exisitNumberRankFinal = new ArrayList<Integer>();// 최종 등수별 만들어진 갯수

		int totalGame = 0; // 전체 게임수
		int failGame = 0; // 미등수 게임수
		int winGame = 0; // 등수 게임수
		int madeGame = 0;// 만들어야할 게임수

		int createRank = 0; // 생성할 등수
		int cnt = 0;// 일치수 카운트

		boolean isSort = false; // (순차/랜덤) 출력 선책
		String rankName = "";// 랭크 출력
	
		Validation validation =new Validation();

		/****************************************************************************
		 * 타당성 검사
		 * ****************************************************************************/
		if (args.length == 0) {
			System.out.println("초기값을 입력해주세요.");
			System.out.println("프로그램을 종료합니다.");
			System.exit(0);
		}else if(validation.checkValidation(args, STARTNUM, ENDNUNM)==false) {
			System.out.println("종료합니다.");
			System.exit(0);
		}
		/***************************************************************************
		 *  인자값 분류부 
		 **************************************************************************/
		firstNum = lotto7.createFirstNum(args);// 1등번호 추가
		 if (set.getCountRank().length>firstNum.size()) {
				System.out.println("등수 수가 로또번호 수 보다 많습니다.");
				System.exit(0);;
		}else if (validation.checkSetRankCount(set.getCountRank().length,firstNum.size())==false) {//등수 총 수가 로또 한게임 총수보다 큰지 판단 -크다-false 작다-true
			System.out.println("종료합니다.");
			System.exit(0);
		}
		/* 두번째 인자값 추출-각 등수별 생성할 숫자 수 */
		numberForRank = lotto7.createNumberForRank(args, numberForRank);
		exisitNumberRank = lotto7.createExisitingNumberForRank(list, firstNum);
		if (validation.checkRankCount(numberForRank.size(),firstNum.size())==false) {//등수 총 수가 로또 한게임 총수보다 큰지 판단 -크다-false 작다-true
			System.out.println("종료합니다.");
			System.exit(0);
		}else if (validation.checkRankCountToSett(numberForRank.size(),set.getCountRank().length)==false) {
			System.out.println("종료합니다.");
			System.exit(0);
		}
		// 등수 갯수 구하기
		for (int i = 0; i < numberForRank.size(); i++) {
			winGame += numberForRank.get(i);
		}

		/* 세번째 인자값 추출-전체 생성할 갯수 */
		totalGame = lotto7.createTotalGame(args);
		if (winGame>totalGame) {
			System.out.println("전체게임보다 만들어야할 당첨게임수가 더 많습니다.");
			System.out.println("종료합니다.");
			System.exit(0);
		}
		failGame = totalGame - winGame;// 미등수 갯수 구하기
		numberForRank.add(failGame);// 미등수 갯수 추가

		/* 네번째 인자 값 추출- 랜덤/순차 여부 */
		isSort = lotto7.createIsSort(args, isSort);
		/* 이제 만들어야 랭크별 갯수 구하기 */
		for (int i = 0; i < numberForRank.size(); i++) {
			makeNumberForRank.add(numberForRank.get(i) - exisitNumberRank.get(i));
		}
		/*******************************************************************************
		 *  기능 수행부
		 ****************************************************************************** */
		/*
		 * 모든 등수로또 번호 생성 -생성해야 할 로또 등수에서 0이 아닌 값이 아닌 양수가 있으면 그 양수 만큼 생성해야함
		 * -createRank=생성할 등수 (0->1등,1->2등,2->3등,3->4등,4->5등,5->미등수,6->미등수)
		 * -madeGame=생성할 게임수
		 */
		int i = 0;
		while (i < makeNumberForRank.size()) {
			if (makeNumberForRank.get(i) == 0) { // 더이상 생성할 번호가 없음
				i++;
				continue;
			} else if (makeNumberForRank.get(i) < 0) { // 0미만이면 잘못된 갯수가 생성된것
				System.out.println("각등수의 갯수를 오버하는 로또번호가 발생했습니다.");
			} else {// 해당 등수에 만들 게임이 있음
				createRank = i;
				madeGame = makeNumberForRank.get(i);// 해당 등수의 생성할 게임수 저장
			}
			int i2 = 0;
			while (i2 < madeGame) {
				randomTemp = new ArrayList<Integer>();// 객체를 맨위에 생성하면 1번만 생성가능하여 깊은 복사의 문제를 해결 못함 그래서 필요시 매번 생성
														// (궁금증? 이름이 같은 객체가 계속 생성되는데 알아서 이름은 같지만주소가 다른 객체가 생성되는 것인가?)
				randomTemp.addAll(lotto7.createRandomNum(createRank,makeNumberForRank, firstNum, list));// 생성한 로또 번호 복사
				list.add(randomTemp);// 생성번호 추가
				i2++;
			}
			i++;
		}
		/* random순으로 변환 */
		if (isSort == true) {
			Collections.shuffle(list);
			//lotto7.listShuffle(list);
		}
		/* 모든 결과 출력 */
		for (int j = 0; j < list.size(); j++) {
			cnt = 0;
			for (int j2 = 0; j2 < list.get(j).size(); j2++) {
				for (int k = 0; k < firstNum.size(); k++) {
					if (list.get(j).get(j2) == firstNum.get(k)) {
						cnt++;
					}
				}
			}
			/* 맞은 갯수에대한 등수 출력 */
			for (int j2 = 0; j2 < set.getCountNonRank().length; j2++) {
				if (set.getCountNonRank()[j2]==cnt) {
					rankName="미등수";
				}
			}
			/*if (setRankCount.get(0)==cnt) {
				rankName="1등";
			}*/
			for (int j2 = 0; j2 < set.getCountRank().length; j2++) {
				if (set.getCountRank()[j2]==cnt) {
					rankName=j2+1+"등";
				}
			}
			System.out.println(list.get(j) + rankName);
		}
		exisitNumberRankFinal = lotto7.createExisitingNumberForRank(list, firstNum);
		System.out.println("생성한 각 등수 갯수");
		for (int j = 0; j < exisitNumberRankFinal.size(); j++) {
			System.out.print(exisitNumberRankFinal.get(j) + " ");
		}
		System.out.println();
	}


	/*******************************************************************************
	 * 메소드 부 
	 * *********************************************************************************/
	/**
	 * 각 등수에 해당하는 갯수만큼 해당 등수 로또 번호 생성
	 * 
	 * @param madeGame -생성할 게임수
	 * @param makeNumberForRank -만들어야할 당첨번호 랭크를 담음 리슽트
	 * @param firstNum -1등 번호
	 * @param list     -전체 리스트
	 * @param setRankCount -각 당첨번호의 정답수
	 * @param setNonRankCount-각 미당첨 번호의 정답수
	 * @return result -1게임 -생성 로또 리스트
	 */
	private Collection<? extends Integer> createRandomNum(int createRank, ArrayList<Integer> makeNumberForRank,
			ArrayList<Integer> firstNum, ArrayList<ArrayList<Integer>> list) {
		// TODO Auto-generated method stub
		int i = 0;
		int index = 0;// 추가할 인덱스
		int value = 0;// 추가할 값
		int randomOk = createRandomOk(createRank,makeNumberForRank);// 정답수
		boolean firstOk = false, secondOk = false;
		ArrayList<Integer> result = new ArrayList<Integer>();
		ArrayList<Integer> existValue = new ArrayList<Integer>();

		// 1등값 복사
		for (i = 0; i < firstNum.size(); i++) {
			result.add(firstNum.get(i));
			existValue.add(firstNum.get(i));
		}
		// 1등값 섞기
		Collections.shuffle(result);
		// 해당등수에 맞게 제거하여 정답수 맞춤
		for (i = randomOk; i < firstNum.size(); i++) {
			result.remove(result.size() - 1);
		}

		for (i = result.size(); i < firstNum.size(); i++) {// 갯수채움
			index = indexRandom(result.size());// 인데스구함
			firstOk = false;
			secondOk = false;
			/* check1 해당 리스트에서 이미있는 값인지 확인 */
			while (!(firstOk && secondOk)) {
				value = valueRandom();
				for (int j = 0; j < existValue.size(); j++) {
					if (existValue.get(j) == value) {
						value =valueRandom();
						j = -1;
						firstOk = false;
					}
				}
				firstOk = true;
				secondOk = true;
			}
			result.add(index, value);
			existValue.add(value);
		}
		return result;
	}

	/**
	 * 추가할랜덤값
	 * 
	 * @return (int)(Math.random()*totalNum+startNum)-startNum부터 시작하는 totalNum까지의 수
	 *         중하나 반환
	 */
	private int valueRandom() {
		// TODO Auto-generated method stub
		int startNum = 1;
		int totalNum = 45;
		return (int) (Math.random() * totalNum + startNum);
	}

	/**
	 * //추가할 랜덤 인덱스
	 * 
	 * @param size-현재 리스트의 크기->이유) 현재 리스트에 추가할 인덱스를 구하는 것이므로 리스트크기-1의 범위까지 수를 생성해야
	 *                함으로
	 * @return (int)(Math.random()*totalNum+startNum)-startNum부터 시작하는 totalNum까지의 수
	 *         중하나 반환
	 */
	private int indexRandom(int size) {
		// TODO Auto-generated method stub
		int startNum = 0;
		int totalNum = size;
		return (int) (Math.random() * totalNum + startNum);
	}

	/**
	 * 해당 등수의 정답수
	 * 
	 * @param createRank 만들어야되는 인덱스번호(0-1등)
	 * @param makeNumberForRank 
	 * @param setNonRankCount-각 미당첨 번호의 정답수
	 * @return int - 해당 등수의 총 정답수를 반환
	 */
	private int createRandomOk(int createRank, ArrayList<Integer> makeNumberForRank) {
		// TODO Auto-generated method stub
		LottoSett set =new LottoSett();
		int failCount = 0; //미등수 맞은 갯수중 어느 것을 생성할지 고를 변수
		int total=set.getCountRank().length+set.getCountNonRank().length; //등수+미등수
		int result=0; 
		boolean ok=false; //등수 생성이 끝났는지 확인
		for (int i = 0; i < set.getCountRank().length; i++) {
			if (i==createRank) {
				result= set.getCountRank()[createRank];
				ok=true;
			}
		}
		if(ok==false) {
			for (int i = set.getCountRank().length; i < total; i++) {
				if (i==createRank) {
					failCount = (int) (Math.random() * set.getCountNonRank().length + 0);
					result= failCount;
				}
			}
		}
		//이전 소스-등수 수가 변할시 변경이 힘듬
		/*switch (createRank) {
		case 0://1등
			return set.getCountRank()[createRank];
			//return setRankCount.get(createRank);
		case 1://2등
			return set.getCountRank()[createRank];
			//return setRankCount.get(createRank);
		case 2://3등
			return set.getCountRank()[createRank];
			//return setRankCount.get(createRank);
		case 3://4등
			return set.getCountRank()[createRank];
			//return setRankCount.get(createRank);
		case 4://5등
			return set.getCountRank()[createRank];
			//return setRankCount.get(createRank);

		default:
			failCount = (int) (Math.random() * set.getCountNonRank().length + 0);
			return failCount;
		}*/
		return result;
	}

	/**
	 * 각 등수별 존재하는 리스트 갯수 구하기
	 * 
	 * @param list                   -전체 리스트
	 * @param firstNum               -1등번호
	 * @return exisitNumberRank
	 */
	private ArrayList<Integer> createExisitingNumberForRank(ArrayList<ArrayList<Integer>> list,ArrayList<Integer> firstNum) {
		// TODO Auto-generated method stub
		ArrayList<Integer> exisitNumberRank=new ArrayList<Integer>();
		LottoSett set =new LottoSett();
		int[] existrank = new int[set.getCountRank().length+1]; // 각 등수를 담을 배열
		int cnt = 0;
		for (int i = 0; i < list.size(); i++) {
			cnt = 0;
			for (int j = 0; j < list.get(i).size(); j++) {
				for (int j2 = 0; j2 < firstNum.size(); j2++) {
					if (list.get(i).get(j) == firstNum.get(j2)) {
						cnt++;
					}

				}
			}
			for (int j = 0; j < set.getCountRank().length; j++) {
				if (set.getCountRank()[j]==cnt) {
					existrank[j]++;
				}
			}
			for (int j = 0; j < set.getCountNonRank().length; j++) {
				if (j==cnt) {
					existrank[set.getCountRank().length]++;
				}
			}
			//이전 소스-등수 수가 변할시 변경이 힘듬
			/*switch (cnt) {
			case 0:
				existrank[5]++;
				break;
			case 1:
				existrank[5]++;
				break;
			case 2:
				existrank[4]++;
				break;
			case 3:
				existrank[3]++;
				break;
			case 4:
				existrank[2]++;
				break;
			case 5:
				existrank[1]++;
				break;
			case 6:
				existrank[0]++;
				break;
			default:
				break;
			}*/
		}
		for (int i = 0; i < existrank.length; i++) {
			exisitNumberRank.add(existrank[i]);
		}
		return exisitNumberRank;
	}

	/**
	 * 3번째 인자값 추출-출력방식 선책(등수/랜덤)
	 * 
	 * @param args   -인자값
	 * @param isSort -출력방식
	 * 
	 * @return isSort -출력방식
	 */
	private boolean createIsSort(String[] args, boolean isSort) {
		if (args.length>3) {
			if (args[3].equals("TRUE")) {
				isSort = true;
			}else {
				isSort=false;
			}
		}
		return isSort;
	}

	/**
	 * 2번째 인자값 추출-전체 게임수
	 * 
	 * @param args      -인자값
	 * @return totalGame -전체 게임수
	 */
	private int createTotalGame(String[] args) {
		int totalGame;
		totalGame = Integer.parseInt(args[2]);
		return totalGame;
	}

	/**
	 * 1번째 인자값 추출-각 등수별 갯수 지정
	 * 
	 * @param args          인자값
	 * @param numberForRank 만들어야 할 각 등수별 갯수
	 * @return numberForRank 만들어야 할 각 등수별 갯수
	 */
	private ArrayList<Integer> createNumberForRank(String[] args, ArrayList<Integer> numberForRank) {
		// TODO Auto-generated method stub
		String[] twiceArr;
		twiceArr = args[1].split(",");
		
		for (int i = 0; i < twiceArr.length; i++) {
			numberForRank.add(Integer.parseInt(twiceArr[i].split(":")[1]));
		}
		

		return numberForRank;
	}

	/**
	 * 0번째 인자값 분리하여 firstNum 추출
	 * 
	 * @param args     :인자값
	 * @return firstNum : 1등번호를 담은 ArrayList
	 */
	private ArrayList<Integer> createFirstNum(String[] args) {
		ArrayList<Integer> firstNum =new ArrayList<Integer>();
		String[] argsFirst;
		argsFirst = args[0].split(",");
		
		for (int i = 0; i < argsFirst.length; i++) {
			firstNum.add(Integer.parseInt(argsFirst[i]));
		}
		
		return firstNum;
	}
	
	private boolean isNumber(String checkVal) {
		try {
			Integer.parseInt(checkVal);
		} catch (Exception e) {
			return false;
		}
		return true;
		
		
	}
}

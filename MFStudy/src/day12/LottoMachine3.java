package day12;

 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LottoMachine3 {

	private final int MAXNUM = 45;	// 로또번호 뽑을때 범위(최대값)
	private final int MINNUM = 1;	// 로또번호 뽑을때 범위(최소값)
	
	
	private String 	checkSort 		= "FALSE";			// args[3]에 있는 TRUE 문자열을 이용하여 정렬방식을 정함 없으면 초기값 FALSE로 세팅

	List<Integer> orgLottoNumber 	= new ArrayList<Integer>();			// 로또 1등 당첨번호

	
	// 1~5등 미당첨자 수
	private int userCntTotal 		= 0;		// 총 로또개수
    private int userCntRank1 		= 0;		// 1등 당첨수
    private int userCntRank2 		= 0;		// 2등 당첨수
    private int userCntRank3 		= 0;		// 3등 당첨수
    private int userCntRank4 		= 0;		// 4등 당첨수
    private int userCntRank5 		= 0;		// 5등 당첨수
    
    // 미 당첨자 수 = 총 로또개수-(1등~5등 합친개수)
    private int userCntRankFail		= 0;

    // 1~5등 미당첨자의 로또번호들을 담음
    List<List<Integer>> lottoListAll = new ArrayList<List<Integer>>();
    
    // 로또1등 당첨번호와 같은 숫자개수 EX) 1등=6, 2등=5, 3등=4 .... 
    private int checkLotto 			= 0;
    
    // 뽑은 로또 번호가 몇등인지 알려줌 true = 3이면 4등
    private int lottoRankResult 	= 0;

    
    
	public static void main(String[] args) {
		
		LottoMachine3 ltm = new LottoMachine3();
				
		try {
			// args의 validation검사
			ltm.argsLengthCheck(args);

			// 로또1~5등 미당첨자 리스트 생성
			ltm.addLottoRankList();
			
			// 로또 총 리스트 출력
			ltm.printLottoList();
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("에러발생");
			System.exit(0);
		}
		
	
	}
	//EOF main
	
	
	
	
	/**
	 * 문자열 TRUE면 셔플을 사용하여 LIST 랜덤 정렬
	 * TRUE가 아닐 시 기본정렬(1~5등 미당첨자 순차정렬)
	 * 
	 * @param checkSort	: 문자열 TRUE를 비교함
	 */
	public void lottoListSort(String checkSort) {
		
		this.checkSort = checkSort;
		
		System.out.println();
		// TRUE값이 왔을 때 등수 랜덤정렬
		if(this.checkSort.equals("TRUE")) {
			Collections.shuffle(lottoListAll);
			System.out.println("----- LIST 랜덤정렬 -----");
		}
		else {
			System.out.println("----- LIST 기본정렬 -----");			
		}
		
	}
	// EOF lottoListSort

	
	/**
	 * 해당 등수의 수만큼 반복하여 해당 등수의 로또번호를 뽑아 lottoListAll에 담습니다.
	 * checkLotto : 1등번호와 비교하여 일치하는 개수 (6=1등, 5=2등, 4=3등 ...)
	 * 
	 */
	public void addLottoRankList() {
		
		// 1등 로또번호 뽑기
        for(int i = 0; i < userCntRank1; i++) {
        	checkLotto = 6;
        	lottoListAll.add(lottorandomNumList(orgLottoNumber, checkLotto));
        }
        // 2등 로또번호 뽑기
        for(int i = 0; i < userCntRank2; i++) {
        	checkLotto = 5;
        	lottoListAll.add(lottorandomNumList(orgLottoNumber, checkLotto));
        }
        // 3등 로또번호 뽑기
        for(int i = 0; i < userCntRank3; i++) {
        	checkLotto = 4;
        	lottoListAll.add(lottorandomNumList(orgLottoNumber, checkLotto));
        }
        // 4등 로또번호 뽑기
        for(int i = 0; i < userCntRank4; i++) {
        	checkLotto = 3;
        	lottoListAll.add(lottorandomNumList(orgLottoNumber, checkLotto));
        }
        // 5등 로또번호 뽑기
        for(int i = 0; i < userCntRank5; i++) {
        	checkLotto = 2;
        	lottoListAll.add(lottorandomNumList(orgLottoNumber, checkLotto));
        }
        // 미당첨자 로또번호 뽑기 1등 번호와 비교하여 맞은개수 0 or 1
        for(int i = 0; i < userCntRankFail; i++) {
            checkLotto = (int)(Math.random() * 2);
            lottoListAll.add(lottorandomNumList(orgLottoNumber, checkLotto));
        }
        
	}
	// EOF addLottoRankList
	
	
	/**
	 * 뽑은 로또번호 전체를 등수 포함하여 출력합니다.
	 */
	public void printLottoList() {
		
		// 뽑기전에 정렬방식을 선택
		lottoListSort(checkSort);
		
		// 총 로또번호 리스트
		for( int i = 0; i < lottoListAll.size(); i++) {
			// 1등과 비교하여 일치하는 개수로 등수 표현 
			lottoRankResult = lottoTrueCnt(orgLottoNumber, lottoListAll.get(i)); 

			switch(lottoRankResult) {
				case 6:
					System.out.println(lottoListAll.get(i) + " / 1등 당첨");
					break;
				case 5:
					System.out.println(lottoListAll.get(i) + " / 2등 당첨"); 
					break; 
				case 4:
					System.out.println(lottoListAll.get(i) + " / 3등 당첨"); 
					break;
				case 3:
					System.out.println(lottoListAll.get(i) + " / 4등 당첨"); 
					break;
				case 2:
					System.out.println(lottoListAll.get(i) + " / 5등 당첨");
					break; 
				default:
					System.out.println(lottoListAll.get(i) + " / 미당첨");
					break; 
			}  
		}
		
	}
	// EOF printLottoList
	
	
	/**
	 * 로또번호에 사용할 지정범위 안에 숫자를 뽑는다.
	 * 최대/최소 값 포함
	 * @param maxNum	: 최대값
	 * @param minNum	: 최소값
	 * @return			: 최대값~최소값 범위의 랜덤숫자를 출력
	 */
	public int getRanNum(int maxNum, int minNum) {
		
		int randomNum	= 0;

		randomNum = (int)((Math.random() * (maxNum)) + minNum);

		return randomNum;
	}
	// EOF getRanNum
	
	
    /**
     * 해당 등수의 로또 번호를 뽑음 
     * 
     * @param orgLottoNumber	: 1등 당첨번호
     * @param checkLotto		: 1등번호랑 비교하여 맞은 개수 / 1등-6개, 2등-5개, 3등-4개, 4등-3개 ... 
     * @return					: 해당 등수의 6개의 로또번호
     */
    public ArrayList<Integer> lottorandomNumList(List<Integer> orgLottoNumber, int checkLotto) {

    	// 1등 당첨번호
    	List<Integer> lottoNum = new ArrayList<Integer>(orgLottoNumber);
    	// 로또 번호  6개를 담을 리스트
    	ArrayList<Integer> lottoNumList = new ArrayList<Integer>();
    	
    	
    	int sameNumCnt 	= checkLotto;	// 1등번호랑 비교하여 맞은 개수
    	int addOrgNum 	= 0;			// 1등 당첨번호를 랜덤으로받아 해당값을 리스트에 추가
    	int numCnt		= 0;			// 1등 당첨번호랑 같으면 증가
		
		int ranOrgIndex = 0;			// index번호 랜덤으로 뽑을 변수
		

		// 받아온 일치개수랑, 랜덤으로 돌려서 당첨번호와 일치한 개수가 같을때까지 반복
		if(sameNumCnt > 0) {
			// 무한루프
			while (true) {
				 
				ranOrgIndex = (int)(Math.random() * 6);		// index 0~5 랜덤
				addOrgNum 	= lottoNum.get(ranOrgIndex);	// 1등 당첨번호의 랜덤으로 뽑은 index값을 받는다.
	
				// 1등 당첨번호에 포함 && 넣을 리스트에 해당 값이 없어야 추가(중복제거)		
				if (lottoNum.contains(addOrgNum) && !lottoNumList.contains(addOrgNum)) {					
					lottoNumList.add(addOrgNum);
					numCnt++;
					// System.out.println("A110. lottoNumList : " + lottoNumList + " \t / orgIndex[" + ranOrgIndex + "]");
				}
				
				// lottoNumList size가 같아야되는 개수랑 같으면 탈출
				if (numCnt == sameNumCnt) {
		        	break;
		        }
			}
		}
		
		
		int addLastNum = 0;      // 마지막 로또번호 생성
		
		// lottoNumList size가 6이 될때까지 남은 로또번호 생성
		while (lottoNumList.size() < 6) {
			addLastNum = getRanNum(MAXNUM, MINNUM);	// (최대값, 최소값) 범위안의 랜덤번호 뽑기
			
			// 1등 당첨번호에 없음  && 추가할 리스트에 같은 값이 없음 
			if(!orgLottoNumber.contains(addLastNum) && !lottoNumList.contains(addLastNum) ) {
				lottoNumList.add(addLastNum);	// 새로뽑은 값 리스트에 추가
			}
			// System.out.println("A120. lottoNumList : " + lottoNumList + " / addLastNum : " + addLastNum);

		}

		return lottoNumList;
    }
    // EOF lottoranNumList
    
    
    /**
     * 1등 로또번호와 비교하여 동일한 만큼 true 추가
     * @param orgLottoNumber	: args에서 받은 1등 로또번호
     * @param lottoListAll		: 총 로또번호의 리스트
     * @return					: 리스트 전체를 돌려 1등과 비교하여 일치하는 개수를 출력 (1등 = 6개, 2등 = 5개...)
     */
    public int lottoTrueCnt(List<Integer> orgLottoNumber, List<Integer> lottoListAll) {
    	
       int result = 0;	// 1등 비교와 일치개수 count
       
       for(int i = 0; i < lottoListAll.size(); i++) {
            if(orgLottoNumber.contains(lottoListAll.get(i))) {
                result += 1;
            }
       }
       return result;
    }
    // EOF lottoTrueCnt
    
    
    /**
     * args의 배열 길이를 검사하고 길이가 4면 정렬을 판단하는 문자열 값을 받고 이상이 없으면 argsLottoNumCheck메소드 실행
     * @param args	: Arguments 값을 받음
     */
    public void argsLengthCheck(String[] args) {
    	
    	// args의 길이 validation 확인
		if(args.length < 2) {
			System.out.println("ERR100 : 입력받은 args의 길이가 작습니다.");
			System.exit(0);
		}
		else if(args.length > 4) {
			System.out.println("ERR110 : 입력받은 args의 길이가 깁니다.");
			System.exit(0);
		}
    	
		if(args.length == 4) {
			this.checkSort = args[3];	// TRUE값을 받았기때문에 해당 값을 가져옴 없을경우 기본값 false
		}
		
		argsLottoNumCheck(args);
    }
    // EOF argsLengthCheck
    
    
    /**
     * 1등 로또 번호를 받아 검사 이상이 없으면 argsUserCntCheck메소드 실행
     * @param args	: Arguments 값을 받음
     */
    public void argsLottoNumCheck(String[] args)  {
    
    	
    	List<String> argsLottoNum 	= new ArrayList<String>();		// args[0]에서 받은 로또 번호목록

		
		argsLottoNum 	=	Arrays.asList(args[0].split(","));		// , 콤마제거하여 arraylist에 추가

		
		// 1등번호들을 String -> Integer로 type변경하여  orgLottoNumber에 추가. (추가중에 문자가 포함되있으면 오류메세지 출력 후 프로그램 종료)	
		for (String lottoNum : argsLottoNum) {
			try {
				orgLottoNumber.add(Integer.parseInt(lottoNum));				
			} 
			catch (NumberFormatException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
		
		// orgLottoNumber의 값이 MINNUM, MAXNUM이랑 비교하여 validation 확인
		for(int lottoNum : orgLottoNumber) {
			if(lottoNum < MINNUM) {
				System.out.println("1등 당첨번호 중 " + MINNUM + "보다 커야 됩니다.");
				System.exit(0);
			}
			else if(lottoNum > MAXNUM) {
				System.out.println("1등 당첨번호 중 " + MAXNUM + "보다 작아야 됩니다.");
				System.exit(0);
			}
		}
		System.out.println("L100. 1등 당첨번호(orgLottoNumber) : " + orgLottoNumber);
		
		argsUserCntCheck(args);
    }
    //EOF argsLottoNumCheck
    
    
    /**
     * 해당 등수의 인원을 받아 MAP에 저장함 이상
     * @param args	: Arguments 값을 받음
     */
    public void argsUserCntCheck(String[] args)  {

    	List<String> argsUserCnt 	= new ArrayList<String>();		// args[1]에서 받은 1~5등 미당첨자의 수
    	
		int	colonIndex		= 0;			// 문자열을 자르는 기준인덱스	
		int	argsUserTotal 	= 0;			// args[2]에 있는 총 로또번호 개수를 받음
		int	userCnt			= 0;			// 해당 등수 인원
		int	userRank		= 0;			// 해당 등수
		int userRankTotal	= 0;			// 1~5등 인원
		
		Map<Integer, Integer> lottoUserCnt = new HashMap<Integer, Integer>();
		
		
		argsUserCnt		= 	Arrays.asList(args[1].split(","));		// , 콤마제거하여 arraylist에 추가

		// 당첨자 수만 뽑기위해 :가 포함한 앞에숫자 버림
		for(int i = 0; i <argsUserCnt.size(); i++) {
			
			// :가 해당하는 인덱스 자리를 구합니다.
			colonIndex 		= argsUserCnt.get(i).indexOf(":");
			
			try {
				// 해당 등수를 넣는다 1~5
				userRank	= Integer.parseInt(argsUserCnt.get(i).substring(0, colonIndex));
				userCnt		= Integer.parseInt(argsUserCnt.get(i).substring(colonIndex+1));
				lottoUserCnt.put(userRank, userCnt);
			}
			catch (NumberFormatException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
		
		

		// String -> Integer로 type변경시 문자가 포함되있으면 오류메세지 출력 후 프로그램 종료
		try {
			argsUserTotal = Integer.parseInt(args[2]);
		} 
		catch (NumberFormatException e) {
			e.printStackTrace();
			System.exit(0);
		}

		// 해당 등수의 인원수를  넣습니다.
		userCntRank1 = (lottoUserCnt.get(1) == null) ? 0 : lottoUserCnt.get(1);
		userCntRank2 = (lottoUserCnt.get(2) == null) ? 0 : lottoUserCnt.get(2);
		userCntRank3 = (lottoUserCnt.get(3) == null) ? 0 : lottoUserCnt.get(3);
		userCntRank4 = (lottoUserCnt.get(4) == null) ? 0 : lottoUserCnt.get(4);
		userCntRank5 = (lottoUserCnt.get(5) == null) ? 0 : lottoUserCnt.get(5);
		
		userCntTotal = argsUserTotal;	// 총 로또 수
		
		// 미등수 수
		userCntRankFail 	= 	userCntTotal - (userCntRank1 + userCntRank2 + userCntRank3 + userCntRank4 + userCntRank5);
		
		// 1~5등 수
		userRankTotal 		=  	userCntRank1 + userCntRank2 + userCntRank3 + userCntRank4 + userCntRank5;
		
		if(userCntTotal < userRankTotal) {
			System.out.println("1~5등 인원이 전체인원보다 많습니다.");
			System.exit(0);
		}

	}
    // EOF argsUserCntCheck
    
    
    
}
package day10;

 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


 

public class LottoMachine3 {

	public static void main(String[] args) {

		LottoMachine3 ltm = new LottoMachine3();
		
		List<String> argsLottoNum 	= new ArrayList<String>();
		List<String> argsUserCnt 	= new ArrayList<String>();
		
		List<Integer> lottoNum 		= new ArrayList<Integer>();
		List<Integer> userCnt 		= new ArrayList<Integer>();
		
		int	   argsUserTotal 		= 0;
		String argsCheckSort 		= "";
		
		int startIndex = 0;		// 문자열 시작 인덱스 번호
		int endIndex = 0;		// 문자열 마지막 인덱스 번호
		
		// TRUE 미 입력시 args.length =3
		if(args.length > 0 && args.length == 3) {
			argsLottoNum 	=	Arrays.asList(args[0].split(","));	// , 콤마제거하여 받음
			argsUserCnt		= 	Arrays.asList(args[1].split(","));	// , 콤마제거하여 받음
			
			// 당첨자 수만 뽑기위해 :가 포함한 앞에숫자 버림
			for(int i = 0; i <argsUserCnt.size(); i++) {
				//  :가 해당하는 인덱스자리의 +1  / ex) 331:12 일때  시작 index값 = 4
				startIndex 	= argsUserCnt.get(i).indexOf(":") + 1;
				endIndex 	= argsUserCnt.get(i).length();
				
				// :포함한 앞에숫자를 버리고 당첨자 수만 int형 arraylist에값을 넣음
				userCnt.add(Integer.parseInt(argsUserCnt.get(i).substring(startIndex, endIndex) ));
			}
			
			argsUserTotal	=   Integer.parseInt(args[2]);	// 총 뽑을 로또번호 개수
			argsCheckSort	= 	"FALSE";					// TRUE값을 받지 않았기 때문에 문자열을 FALSE로 세팅
		}
		else if(args.length > 0 && args.length == 4) {
			
			// TRUE입력 시 args.length = 4
			argsLottoNum 	=	Arrays.asList(args[0].split(","));
			argsUserCnt		= 	Arrays.asList(args[1].split(","));
			
			for(int i = 0; i <argsUserCnt.size(); i++) {
				//  :가 해당하는 인덱스자리의 +1  / ex) 331:12 일때  시작 index값 = 4
				startIndex 	= argsUserCnt.get(i).indexOf(":") + 1;
				endIndex 	= argsUserCnt.get(i).length();

				userCnt.add(Integer.parseInt(argsUserCnt.get(i).substring(startIndex, endIndex) ));
			}
			
			argsUserTotal	=   Integer.parseInt(args[2]);
			argsCheckSort	= 	args[3];	// TRUE값을 받았기때문에 해당 값을 가져옴
		}
		else if(args.length < 1 || args.length > 4) {
			System.out.println("args 입력값 오류");
		}

				
		for (int i = 0; i < argsLottoNum.size(); i++) {
			// String -> Integer 변경
			lottoNum.add(Integer.parseInt(argsLottoNum.get(i)));

			/*
			try {
				lottoNum[i] = Integer.parseInt(lottoNumStr[i].replaceAll("^[0-9]*$",""));
				System.out.println(lottoNum[i] + "##");
			} 
			catch (NumberFormatException nfe) {
				System.out.println("숫자가 아닙니다.");
				return;
			}
			*/
		}

		// 1등 로또번호 넣을 list
		List<Integer> orgLotto = new ArrayList<Integer>();

		// 1~5등 미당첨자 map에 담기
		Map<String, Integer> rankLotto = new HashMap<String, Integer>();
		
		// 로또 1등 당첨번호를 받는다.
		for(int i = 0; i < lottoNum.size(); i++) {
			orgLotto.add(lottoNum.get(i));
		}		

		// 1~5등 
        int lotto1 = 0;
        int lotto2 = 0;
        int lotto3 = 0;
        int lotto4 = 0;
        int lotto5 = 0;
        int lotto6 = 0;
        
        int lottoTotal = 0;
        
        // 뽑은 로또 번호가 몇등인지 알려줌 true = 3이면 4등
        int ltResult = 0;
        
        lotto1	= userCnt.get(0);
        rankLotto.put("1등", lotto1);

        lotto2	= userCnt.get(1);
        rankLotto.put("2등", lotto2);

        lotto3	= userCnt.get(2);
        rankLotto.put("3등", lotto3);
        
        lotto4	= userCnt.get(3);
        rankLotto.put("4등", lotto4);
        
        lotto5	= userCnt.get(4);
        rankLotto.put("5등", lotto5);
        
        lottoTotal	= argsUserTotal;
        
        // 미 당첨자 수 = 총 로또개수 - (1등~5등 합친개수)
        lotto6 = lottoTotal - (lotto1 + lotto2 + lotto3 + lotto4 + lotto5);

        rankLotto.put("미당첨", lotto6);
        
        System.out.println("총 로또 개수 : " + lottoTotal);
        System.out.println("미당첨 : " + lotto6);
        

        // buyLottoList 로또번호를 담을 list
        List<List<Integer>> lottoListAll = new ArrayList<List<Integer>>();
        

        System.out.print("1등 로또 당첨 번호 : ");
        
        // 1등 당첨번호 출력
    	for(int i = 0; i < orgLotto.size(); i++ ) {
    		System.out.print(orgLotto.get(i) + " ");
    	}

        System.out.println("\n-------------------------------");
        
        // 1~5등 미당첨자 수
        int lottoUserCnt = 0;
        // 로또1등 당첨번호와 같은 숫자개수 EX) 1등=6, 2등=5, 3등=4 .... 
        int checkLotto = 0;
        
        // 1등~5등 미당첨자들의 로또번호 list
        List<List<Integer>> lottoList1 = new ArrayList<List<Integer>>();
        List<List<Integer>> lottoList2 = new ArrayList<List<Integer>>();
        List<List<Integer>> lottoList3 = new ArrayList<List<Integer>>();
        List<List<Integer>> lottoList4 = new ArrayList<List<Integer>>();
        List<List<Integer>> lottoList5 = new ArrayList<List<Integer>>();
        List<List<Integer>> lottoList6 = new ArrayList<List<Integer>>();
        
        // key있는곳 까지 반복
		for(String key : rankLotto.keySet()) {
			
			if(key.equals("1등") && rankLotto.get("1등") > 0) { 
				lottoUserCnt = rankLotto.get("1등");
				// 일치 개수
				checkLotto = 6;
				// key값을 이용해 해당 당첨자 수만큼 받는다.
				for(int i = 0; i < lottoUserCnt; i++) {
					lottoList1.add(ltm.ranNum(orgLotto, checkLotto));
				}
			}
			else if(key.equals("2등") && rankLotto.get("2등") > 0) {
				lottoUserCnt = rankLotto.get("2등");
				// 일치 개수
				checkLotto = 5;
				// key값을 이용해 해당 당첨자 수만큼 받는다.
				for(int i = 0; i < lottoUserCnt; i++) {
					lottoList2.add(ltm.ranNum(orgLotto, checkLotto));
				}
			}			
			else if(key.equals("3등") && rankLotto.get("3등") > 0) {
				lottoUserCnt = rankLotto.get("3등");
				// 일치 개수
				checkLotto = 4;
				// key값을 이용해 해당 당첨자 수만큼 받는다.
				for(int i = 0; i < lottoUserCnt; i++) {
					lottoList3.add(ltm.ranNum(orgLotto, checkLotto));
				}
			}
			else if(key.equals("4등") && rankLotto.get("4등") > 0) {
				lottoUserCnt = rankLotto.get("4등");
				// 일치 개수
				checkLotto = 3;
				// key값을 이용해 해당 당첨자 수만큼 받는다.
				for(int i = 0; i < lottoUserCnt; i++) {
					lottoList4.add(ltm.ranNum(orgLotto, checkLotto));
				}
			}
			else if(key.equals("5등") && rankLotto.get("5등") > 0) {
				lottoUserCnt = rankLotto.get("5등");
				// 일치 개수
				checkLotto = 2;
				// key값을 이용해 해당 당첨자 수만큼 받는다.
				for(int i = 0; i < lottoUserCnt; i++) {
					lottoList5.add(ltm.ranNum(orgLotto, checkLotto));
				}
			}
			else if(key.equals("미당첨") && rankLotto.get("미당첨") > 0) {
				lottoUserCnt = rankLotto.get("미당첨");
				
				// key값을 이용해 해당 당첨자 수만큼 받는다.
				for(int i = 0; i < lottoUserCnt; i++) {
					// 일치 개수
					checkLotto = (int)(Math.random() * 1);
					System.out.println(checkLotto + "#");
					lottoList6.add(ltm.ranNum(orgLotto, checkLotto));
				}
			}

		}
		
		// 1~5등 미당첨자 순으로 list를 lottoListAll에 넣음
		lottoListAll.addAll(lottoList1);
		lottoListAll.addAll(lottoList2);
		lottoListAll.addAll(lottoList3);
		lottoListAll.addAll(lottoList4);
		lottoListAll.addAll(lottoList5);
		lottoListAll.addAll(lottoList6);

        String sortRandom = argsCheckSort;	// 정렬을 판단하는 문자열 가져옴

		// TRUE값이 왔을 때 등수 랜덤정렬
		if(sortRandom.equals("TRUE")) {
			Collections.shuffle(lottoListAll);
			System.out.println("-- LIST 랜덤정렬 --");
		}
		else {
			System.out.println("-- LIST 기본정렬 --");			
		}

		// 총로또번호 리스트 반복
		for( int i = 0; i < lottoListAll.size(); i++) {
			
			ltResult = ltm.check(orgLotto, lottoListAll.get(i)); // 1등과 비교하여 일치하는 개수로 등수 표현 

			switch(ltResult) {
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
	//EOF main

	

    /**
     * 로또 번호를 받는 함수
     * 
     * @param orgLotto		: 1등 당첨번호
     * @param checkLotto	: 1등번호랑 비교하여 맞은 개수 / 1등-6개, 2등-5개, 3등-4개, 4등-3개 ... 
     * @return				: 해당 등수의 6개의 로또번호
     */
    public ArrayList<Integer> ranNum(List<Integer> orgLotto, int checkLotto) {

    	// 1등 당첨번호
    	List<Integer> LottoNum = new ArrayList<Integer>(orgLotto);
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
				addOrgNum 	= LottoNum.get(ranOrgIndex);	// 1등 당첨번호의 랜덤으로 뽑은 index값을 받는다.
	
				// 1등 당첨번호에 포함 && 넣을 리스트에 해당 값이 없어야 추가(중복제거)		
				if (LottoNum.contains(addOrgNum) && !lottoNumList.contains(addOrgNum)) {					
					lottoNumList.add(addOrgNum);
					numCnt++;
					System.out.println("A110. lottoNumList : " + lottoNumList + " \t / orgIndex[" + ranOrgIndex + "]");
				}
				
				// lottoNumList size가 같아야되는 개수랑 같으면 탈출
				if (numCnt == sameNumCnt) {
		        	break;
		        }
			}
		}
		
		int addLastNum = 0;      // 마지막 로또번호 생성
		
		// 고정행 5개를 제외한 마지막 1개의 로또번호 생성
		while (lottoNumList.size() < 6) {
			addLastNum = (int)(Math.random() * 45) + 1;
			
			if(!orgLotto.contains(addLastNum) && !lottoNumList.contains(addLastNum) ) {
				lottoNumList.add(addLastNum);	// 새로뽑은 값 리스트에 추가
			}
			System.out.println("A120. lottoNumList : " + lottoNumList + " / addLastNum : " + addLastNum);

		}
		

		// System.out.println(lottoList + "=====");
		System.out.println("===================================");

		return lottoNumList;
    }
    
    
    
	
    // 1등 로또번호와 비교하여 동일한 만큼 true 추가
    public int check(List<Integer> orgLotto, List<Integer> buyLottoList) {
    	
       int result = 0;
       for(int i = 0; i < buyLottoList.size(); i++) {
            if(orgLotto.contains(buyLottoList.get(i))) {
                result += 1;
            }
       }

       return result;

 
    }
}
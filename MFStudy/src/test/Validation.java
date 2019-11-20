package test;

import java.util.ArrayList;

public class Validation {

	/**
	 * 각 args 타당성 검사
	 * @param args
	 * @param startnum
	 * @param endnum
	 * @return boolean
	 */
	public boolean checkValidation(String[] args, int startnum, int endnum) {
		// TODO Auto-generated method stub
		String[] temp=null;
		boolean check=true;
		for (int i = 0; i < args.length; i++) {
			switch (i) {
			case 0:
				temp=args[0].split(",");
				check= checkZeroArgsValid(temp,startnum,endnum,i);
				if (check==false) {
					return check;
				}
			case 1:
				temp=args[1].split(",");
				check= checkFirstArgsValid(temp,i);
				if (check==false) {
					return check;
				}
			case 2:
				check=checkSecondArgsValid(args[2],i);
				if (check==false) {
					return check;
				}
				break;
			case 3:
				check=checkThirdArgsValid(args,i);
				break;
			default:
				break;
			}
		}
		return check;
	}



	/**
	 * 0번째 인자 타당성 검사
	 * @param temp
	 * @param endnum 
	 * @param startnum 
	 * @param i2 
	 * @return boolean
	 */
	private boolean checkZeroArgsValid(String[] temp, int startnum, int endnum, int argsIndex) {
		// TODO Auto-generated method stub
		int i=0;
		while (i<temp.length) {
			/*형식이 맞는지 체크*/
			if(checkTypeValid(temp[i], argsIndex)==false) {
				return false;
			}
			/*startNum ~ encNum까지의 입력인지 체크*/
			if (!(Integer.parseInt(temp[i].trim())>=startnum && Integer.parseInt(temp[i].trim())<=endnum)) {
				System.out.println(startnum+"~"+endnum+"까지의 수만 가능합니다.");
				return false;
			}
			/*중복값 검사*/
			for (int j = 0; j < i; j++) {
				if (temp[i].equals(temp[j])) {
					System.out.println("중복값이 있습니다.");
					return false;
				}
			}
			i++;
		}
		return true;
	}
	/**
	 * 1번째 인자 타당성 검사
	 * @param temp
	 * @param i2 
	 * @return boolean
	 */
	private boolean checkFirstArgsValid(String[] temp, int argsIndex) {
		// TODO Auto-generated method stub
		ArrayList<String> tempSplit =new ArrayList<String>();
		/*최종 split -각 등수별 갯수*/
		String str="";
		/*형식검사-구분자가 맞는지*/
		for (int i = 0; i < temp.length; i++) {
			try {
				tempSplit.add(temp[i].split(":")[1]);
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("1번째 인자-1:1,2:3,3:10,4:50,5:100 형식으로 입력해주세요.-구분자가 잘못됨");
				return false;
			}
		}
		/*형식검사-값이 숫자가  맞는지*/
		for (int i = 0; i < tempSplit.size(); i++) {
			str=tempSplit.get(i);
			if (checkTypeValid(str, argsIndex)==false) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 2번째 인자 타당성 검사
	 * @param str
	 * @param i2 
	 * @return boolean
	 */
	private boolean checkSecondArgsValid(String str, int argsIndex) {
		// TODO Auto-generated method stub
		if(checkTypeValid(str,argsIndex)==false) {
			return false;	
		}

		
		return true;
	}
	
	/**
	 * 3번째 인자 타당성 검사
	 * @param args
	 * @return boolean
	 */
	private boolean checkThirdArgsValid(String[] args, int argsIndex) {
		// TODO Auto-generated method stub
		if (checkTypeValidChar(args[3])==false) {
			return false;
		}
		return true;
	}


	/**
	 * 타입 타당성 검사-영문자인지
	 * @param str
	 * @return boolean
	 */
	private boolean checkTypeValidChar(String str) {
		// TODO Auto-generated method stub
		char ch;
		for (int i = 0; i < str.length(); i++) {
			ch=str.toUpperCase().charAt(i);
			if (!(ch>='A' && ch<='Z')) {
				System.out.println("영문자가 아닙니다.");
				return false;
			}
		}
		return true;
	}



	/**
	 * 타입 타당성 검사-숫자인지
	 * @param str
	 * @return boolean
	 */
	private boolean checkTypeValid(String str,int argsIndex) {
		char ch;
		for (int i = 0; i < str.length(); i++) {
			ch=str.charAt(i);
			if (!(ch>='0' && ch<='9')) {
				if (argsIndex==0) {
					System.out.println("0번째 인자-23,15,14,24,15 형식으로 입력해주세요.-숫자 구분 또는 숫자값이 이상함");
				}else if (argsIndex==1) {
					System.out.println("1번째 인자-1:1,2:3,3:10,4:50,5:100 형식으로 입력해주세요.-숫자 값이 잘못됨");
				}else if (argsIndex==2) {
					System.out.println("2번째 인자-숫자만 입력가능합니다.");
				}else if (argsIndex==3) {
					
				}
				return false;
			}
		}
		return true;
		// TODO Auto-generated method stub
	}
	/**
	 * 로또 등수 총수(1등,2등...)보다 한게임의 로또수보다 많은지 판단 
	 * @param numberForRank
	 * @param firstNumSize
	 * @return boolean
	 */
	public boolean checkRankCount(int numberForRank, int firstNumSize) {
		if (numberForRank>firstNumSize) {
			System.out.println("생성할려는 등수 수보다 로또 번호 수가 적습니다.");
			return false;
		}
		return true;
	}

	/**
	 * 설정된 등수 갯수보다 로또수보다 많은지 판단
	 * @param length LottoSett에 설정된 등수 수 보다 로또수가 적은지 판단
	 * @param size 한 게임 로또 수
	 * @return boolean
	 */
	public boolean checkSetRankCount(int length, int size) {
		// TODO Auto-generated method stub
		if (length>size) {
			System.out.println("설정된 전체 등수 수가 게임수의 개수보다 큽니다.");
			return false;
		}
		return true;
	}


	/**
	 * 설정된 등수수와 입력받은 등수 수가 같은지 판단 
	 * @param numberForRankSize 
	 * @param setSize
	 * @return boolean
	 */
	public boolean checkRankCountToSett(int numberForRankSize, int setSize) {
		// TODO Auto-generated method stub
		if (numberForRankSize!=setSize) {
			System.out.println("설정된 등수 수와 입력받은 등수수가 일치 하지 않습니다.");
			return false;
		}
		return true;
	}
}

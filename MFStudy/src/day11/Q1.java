package day11;

public class Q1 {

	// 아래는 테스트로 출력해 보기 위한 코드입니다.
	public static void main(String args[]) {
		a1 a1 = new a1();
		System.out.println(a1.getStrToInt("1234"));
	
	}

}
class a1 {
	public int getStrToInt(String str) {
		boolean Sign = true;
		int result = 0;

		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (ch == '-') {
				Sign = false;
			} else if (ch != '+') {
				result = result * 10 + (ch - '0');
			}		
		}
		return (Sign ? 1 : -1) * result;
	}
}

package day01;

public class Lesson7_Casting {
	public static void main(String args[]) {
		float a = 1.1f;
		double b = 1.1;
		
		double c = 1;
		//System.out.println(c); // 실수형 타입은 double은 정수형 숫자를 담아낼 수 있고 값을 온전히 표현해낼 수 있다.
							   // 이유는 double가 더 큰 타입이기 때문이다 int는 4바이트이고 double은 8바이트이다.
							   // 또한 int가 4바이트고 float도 4바이트지만 float도 int를 온전히 표현 할 수 있다.
		
		// int d = 1.1; int 같은 정수형 타입은 실수를 담아낼 수 없기때문에 에러가 난다. 왜냐하면 1.1을 표현할 수 없는 int
		// 는 1.1 -> 1 로 표현되는 '손실'이 일어나기 때문이다, 그렇기 때문에 이를 강제적으로 표현하기 위해서는 명시적 형변환
		// 을 해야한다.
		int d = (int)1.1; // 손실을 감수하고 명시적 형변환을 적용한 코드
		
		// 1 to String
		String f = Integer.toString(1);
		//System.out.println(f.getClass());
		
		display(2, '+', 3, 5);
		
	}
	
	private static void display(int num1, char c, int num2, int result) {
		//System.out.println(num1 + c + num2 + '=' + result); // 114가 나온다
															  // 숫자 + 부호의 식이 만들어질 경우 부호를 유니
															  // 코드 값으로 계산하여 에상하지 못하는 값이
															  // 나올 수가 있다.
		System.out.println(Integer.toString(num1) + c + Integer.toString(num2) + '=' + result);
															  //그런 경우를 방지하기 위해 특정 숫자 변수 값을
															  //Integer.toString을 이용해 문자로 변환시켜
															  //계산을 시키면 원하는 값을 받을 수 있다.
	}
	
	
}

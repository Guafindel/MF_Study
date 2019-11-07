package day01;

public class Lesson9_Operation {

	public static void main(String[] args) {
		
		/* 연산의 방향과 우선 순위 
		 * 단항 연산자 : + - (타입) ++(증가 연산자) --(감소 연산자) ~(비트 반전 연산자) !(부정 연산자)
		 * 이항 연산자 : 산술 - +, -, *, /, %, / << >> >>> (비트 연산자)
		 * 삼항 연산자 : ? : - ?값이 true면  :의 좌측의 값을 false면 우측의 값을 반환
		 * 대입 연산자 : = 오른쪽의 값을 좌측에 대입
		 * 단항 연산자가 가장 우선 순위가 높으며 다음으로  이항 연산자 중에서도 산술 연산자(사칙연산의 우선순위 적용) 
		 * 그 다음으로 이항 연산자의 비교 연산자 우선순위를 가지며 그 다음으로 이항 연산자의 논리 연산자가 우선
		 * 그 다음으로 삼항 연산자가 우선 순위를 가지며 대입 연산자가 가장 낮은 우선순위를 가지고 있다.
		 * 
		 * */
		
		int a = 6%2;
		System.out.println("% = " + a);
		int b = 6/2;
		System.out.println("/ = " + b);
		int c = 7%2;
		System.out.println(c);
		
		int i = 3;
		int j = 6;
		System.out.println(i + j + "JDK");
		System.out.println("JDK" + i + j);
		
		if(!(i>10)) {
			i = 30;
			System.out.println(i);
		} else {
			i = 50;
			System.out.println(i);
		}
		
		int f = 20;
		f += 10;
		System.out.println(f);
		
		int jam = 25;
		char on = (jam>30)? 't' : 'c';
		char jax = (jam>30? 't' : 'c');
		System.out.println(on);
		System.out.println(jax);
		
		// 예약어란 프로그래밍 언어에서 지정한 특정 단어를 뜻한다. int를 위시로한 다양한 변수들과 class, return, public,
		// private 등등의 단어들을 뜻한다 특정한 의미를 이미 지정하고 약속했기 때문에 이와같은 예약어들은 변수명으로 쓸 수가 없다.
		
		String foo = "Hello world";
		System.out.println(foo.contains("world"));
		System.out.println(foo.contains("JongWon"));
		// true if this string contains s, false otherwise / contains 메소드는 앞 선 특정 값의 문장에 뒤에
		// 적은 문자열이 포함되있을 경우 true 아니라면 false를 반환하는 boolean타입이다.
	}

}

package day01;

public class Lesson5 {
	public static void main(String args[]) {
		int a;
		a = 1;
		
		int b = (int) 1.1; // int는 정수형 변수 타입이기 때문에, 실수 영역인 소수값을 표현할 수 없고
						   // 따라서 1.1을 표현할 수 없기에 형변환을 해야만 한다. 그러나 이렇게 하면 소수점은 표현이 안된다.
		System.out.println(b);
		
		float c = 1.123f;
		System.out.println(c);
		
		String d = "Hello World";
		System.out.println(d);
	}
}

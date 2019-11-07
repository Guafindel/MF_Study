package day01;

public class Lesson1 {
	public static void main(String args[]) {
		System.out.println("Hellow World");

		int intValue = 123456789;
		//System.out.println("intValue = " + intValue);
		byte byteValue = (byte) intValue;
		//System.out.println("byteValue = " + byteValue);

		int intA = 10;
		float floatA = 0.5f;
		//System.out.println("intA + floatA = " + intA + floatA);
		//System.out.println(intA + floatA);

		int a = 10;
		int b = 20;
		//System.out.println("a + b = " + a + b);
		//System.out.println(a + b);
		
		System.out.println(6); // 숫자
		System.out.println("six"); // String
		
		System.out.println("6"); // String 6 -> 숫자가 아니라 문자로 인식
		
		System.out.println(6+6); // 12
		System.out.println("6"+"6"); // 66
		
		System.out.println(6*6); // 36
		
		//System.out.println("6"*"6"); // 자바에서 인식 불능 문자끼리 곱하기가 불가능하기 때문
		
		System.out.println("11111".length()); // 5 length메소드는 문자열의 길이를 숫자로 반환한다.
		//System.out.println(11111.length()); // length메소드는 숫자를 인식하지 못한다.
		
	}

	public class dataType {

		// 네 가지 정수형 변수
		byte a = 10;
		short b = 20;
		int c = 30;
		long d = 40;

		// 소수점 표현이 가능한 실수형 변수
		float e = 1.4f;
		double f = 1.2311;

		// 문자형 변수
		char g = 'a';

		// 참과 거짓을 반환하는 변수
		boolean h = true;

		// 변수란? 하나의 값을 저장할 수 있는 메모리 공간

	}

}

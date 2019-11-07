package day01;

public class AuthApp {
	public static void main(String[] args) {

		//System.out.println(args[0]);

		String id = "JongWon";
		String inputId = args[0];

		String pw = "9999";
		String inputPw = args[1];

		System.out.println("Hello.");

		//System.out.println(inputId == id);
		// if(inputId == id) {
//		if(inputId.equals(id)) {
//			if(inputPw.equals(pw)) {
//				System.out.println("Master");
//			} else {
//				System.out.println("Wrong password!");
//			}
//		} else {
//			System.out.println("But, Who are you?");
//		}

		if(inputId.equals(id) && inputPw.equals(pw)) {
			System.out.println("Master");
		} else {
			System.out.println("But, Who are you?");
		}

	}
}

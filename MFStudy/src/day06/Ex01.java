package day06;

public class Ex01 {

	public static void main(String[] args) {
		
		Mother a = new Son();
		
		Son b = new Son();
		
		Mother c= new Mother();
		
//		a.run();
//		b.run();
//		c.run();
		
		Mother kid = (Son) a;
		Mother kid2 = (Son) a;
		
		Mother kim = (Mother) a;
	
		Son kid3 = (Son) a;
		
		kim.run();
//		kid2.run();
		kid.run();
		
	}
}

package day06;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Logic {

	static ArrayList<Integer> list1 = new ArrayList<>();
	static ArrayList<Integer> list2 = new ArrayList<>();
	static ArrayList<Integer> list3 = new ArrayList<>();
	static ArrayList<Integer> list4 = new ArrayList<>();
	static ArrayList<Integer> list5 = new ArrayList<>();
	static ArrayList<Integer> list6 = new ArrayList<>();
	static ArrayList<Integer> listNum = new ArrayList<>();
	static ArrayList<Integer> check = new ArrayList<>();
	static ArrayList<Integer> lastShuffle = new ArrayList<>();

	static Random ra = new Random();

	public static void main(String[] args) {

// 		로또 조건 값 {23 12 9 2 43 32} 당첨 갯수 1 3 10 50 100 500, 무작위 셔플 여부 "TRUE"

		int lotto1 = Integer.parseInt(args[0]);
		int lotto2 = Integer.parseInt(args[1]);
		int lotto3 = Integer.parseInt(args[2]);
		int lotto4 = Integer.parseInt(args[3]);
		int lotto5 = Integer.parseInt(args[4]);
		int lotto6 = Integer.parseInt(args[5]);

		int firstCount = Integer.parseInt(args[6]);
		int secondCount = Integer.parseInt(args[7]);
		int thirdCount = Integer.parseInt(args[8]);
		int fourCount = Integer.parseInt(args[9]);
		int fifthCount = Integer.parseInt(args[10]);
		int failCount = -((firstCount + secondCount + thirdCount + fourCount + fifthCount) - 500);
		String rq = null;
		
		
		if(!(args[11].isEmpty())) {
			rq = args[11];
		}
		
		list1.add(lotto1);
		list1.add(lotto2);
		list1.add(lotto3);
		list1.add(lotto4);
		list1.add(lotto5);
		list1.add(lotto6);

		listNum.add(firstCount);
		listNum.add(secondCount);
		listNum.add(thirdCount);
		listNum.add(fourCount);
		listNum.add(fifthCount);
		listNum.add(failCount);
		
		lastShuffle.addAll(list1);
		for(int i = 0; i < secondCount; i++) {
			lastShuffle.addAll(list2);
		}

		if (rq.equals("TRUE")) {
			
			
			
		} else {
			System.out.println("-----------1등-----------");
			for (int i = 0; i < firstCount; i++) {
				System.out.println(list1);

			}

			System.out.println("-----------2등-----------");
			for (int i = 0; i < secondCount; i++) {
				getSecond();

			}

			System.out.println("-----------3등-----------");
			for (int i = 0; i < thirdCount; i++) {
				getThird();
			}

			System.out.println("-----------4등-----------");
			for (int i = 0; i < fourCount; i++) {
				getFourth();

			}

			System.out.println("-----------5등-----------");
			for (int i = 0; i < fifthCount; i++) {
				getFifth();

			}

			System.out.println("-----------6등-----------");
			for (int i = 0; i < failCount; i++) {
				getFail();

			}
		}

	}

	static void getSecond() {

		int rnum1 = 0;
		int rnum2 = 0;

		list2.addAll(list1);

		// 1등 번호를 제외한 1~45까지의 수 하나
		for (int i = 0; i < 1; i++) {
			rnum1 = ra.nextInt(45) + 1;
			if (list1.contains(rnum1)) {
				i--;
			}
		}

		// 1등 번호와 일치하는 수 하나
		for (int i = 0; i < 1; i++) {
			rnum2 = ra.nextInt(45) + 1;
			if (!(list1.contains(rnum2))) {
				i--;
			}
		}

		// 1등 번호 중 하나의 숫자를 뽑아내서 새로 뽑은 숫자로 대체하기
		for (int i = 0; i < list2.size(); i++) {
			if (list2.get(i) == rnum2) {
				list2.set(i, rnum1);
			}
		}

		Collections.shuffle(list2);
		System.out.println(list2 + "      2등");
		list2.removeAll(list2);

	}

	static void getThird() {

		list3.addAll(list1);

		ArrayList<Integer> check1 = new ArrayList<>(getRandom(2));
		ArrayList<Integer> check2 = new ArrayList<>(getSameNum(2));

		// getSameNum으로 뽑아낸 숫자를 getRandom으로 뽑아낸 숫자로 대체
		for (int i = 0; i < list3.size(); i++) {
			for (int j = 0; j < check2.size(); j++) {
				if (list3.get(i) == check2.get(j)) {
					list3.set(i, check1.get(j));
				}
			}
		}

		Collections.shuffle(list3);
		System.out.println(list3 + "      3등");
		list3.removeAll(list3);

	}

	static void getFourth() {

		list4.addAll(list1);

		ArrayList<Integer> check1 = new ArrayList<>(getRandom(3));
		ArrayList<Integer> check2 = new ArrayList<>(getSameNum(3));

		// getSameNum으로 뽑아낸 숫자를 getRandom으로 뽑아낸 숫자로 대체
		for (int i = 0; i < list4.size(); i++) {
			for (int j = 0; j < check2.size(); j++) {
				if (list4.get(i) == check2.get(j)) {
					list4.set(i, check1.get(j));
				}
			}
		}
		Collections.shuffle(list4);
		System.out.println(list4 + "      4등");
		list4.removeAll(list4);
	}

	static void getFifth() {

		list5.addAll(list1);

		ArrayList<Integer> check1 = new ArrayList<>(getRandom(4));
		ArrayList<Integer> check2 = new ArrayList<>(getSameNum(4));

		// getSameNum으로 뽑아낸 숫자를 getRandom으로 뽑아낸 숫자로 대체
		for (int i = 0; i < list5.size(); i++) {
			for (int j = 0; j < check2.size(); j++) {
				if (list5.get(i) == check2.get(j)) {
					list5.set(i, check1.get(j));
				}
			}
		}
		Collections.shuffle(list5);
		System.out.println(list5 + "      5등");
		list5.removeAll(list5);

	}

	static void getFail() {

		list6.addAll(list1);

		ArrayList<Integer> check1 = new ArrayList<>(getRandom(6));
		ArrayList<Integer> check2 = new ArrayList<>(getSameNum(6));

		// getSameNum으로 뽑아낸 숫자를 getRandom으로 뽑아낸 숫자로 대체
		for (int i = 0; i < list6.size(); i++) {
			for (int j = 0; j < check2.size(); j++) {
				if (list6.get(i) == check2.get(j)) {
					list6.set(i, check1.get(j));
				}
			}
		}
		Collections.shuffle(list6);
		System.out.println(list6 + "      꽝");
		list6.removeAll(list6);

	}

	static ArrayList<Integer> getRandom(int n) {

		int rnum1 = 0;

		ArrayList<Integer> check1 = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			// 1등 번호를 제외한 1~45까지의 수 하나
			for (int j = 0; j < 1; j++) {
				rnum1 = ra.nextInt(45) + 1;
				if (list1.contains(rnum1)) {
					j--;
				}
			}
			check1.add(rnum1);
		}

		for (int i = 0; i < check1.size(); i++) {
			for (int j = 0; j < i; j++) {
				while (check1.get(i) == check1.get(j)) {
					for (int k = 0; k < n; k++) {
						// 1등 번호와 일치하는 수 하나
						for (int o = 0; o < 1; o++) {
							rnum1 = ra.nextInt(45) + 1;
							if (list1.contains(rnum1)) {
								o--;
							}

						}
						check1.set(i, rnum1);
					}

				}

			}
		}
		return check1;
	}

	static ArrayList<Integer> getSameNum(int n) {

		int rnum2 = 0;

		ArrayList<Integer> check2 = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			// 1등 번호와 일치하는 수 하나
			for (int j = 0; j < 1; j++) {
				rnum2 = ra.nextInt(45) + 1;
				if (!(list1.contains(rnum2))) {
					j--;
				}
			}
			check2.add(rnum2);
		}

		for (int i = 0; i < check2.size(); i++) {
			for (int j = 0; j < i; j++) {
				while (check2.get(i) == check2.get(j)) {
					for (int k = 0; k < n; k++) {
						// 1등 번호와 일치하는 수 하나
						for (int o = 0; o < 1; o++) {
							rnum2 = ra.nextInt(45) + 1;
							if (!(list1.contains(rnum2))) {
								o--;
							}

						}
						check2.set(i, rnum2);
					}

				}

			}
		}

		return check2;
	}

}

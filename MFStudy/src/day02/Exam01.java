package day02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Exam01 {

	public static void main(String[] args) {

		Exam01 l = new Exam01();
		l.start();

	}

	void start() {
		Scanner sc = new Scanner(System.in);

		System.out.print("게임수를 입력하세요: ");
		int gameCnt = sc.nextInt();

		for (int i = 1; i <= gameCnt; i++) {
			System.out.println(i + "번째 로또번호: " + lottoNumbers());
		}

	}

	String lottoNumbers() {

		List<Integer> set = new ArrayList<Integer>();
		//Random r = new Random();

		for (int i = 1; i <= 45; i++) {
			set.add(i);
		}

		// set안의 수를 무작위로 섞는다
		Collections.shuffle(set);

		int[] lottoNums = new int[6];
		for (int i = 0; i < 6; i++) {
			lottoNums[i] = set.get(i);
		}

		Arrays.sort(lottoNums);

		return Arrays.toString(lottoNums);
	}

}
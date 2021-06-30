package coderbyte;

import java.util.Scanner;

class FirstReverse {

	public static String firstReverse(String str) {
		int length = str.length();
		String word = "";
		for (int i = 0; i < length; ++i) {
			word = word + str.charAt(length - i - 1);
		}
		return word;
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.print(firstReverse(s.nextLine()));
		s.close();
	}

}
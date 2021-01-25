package bankingapp.utils;

import java.util.Scanner;

public class BankConsole {

	private static Scanner sc = new Scanner(System.in);
	
	public static void display(String prompt) {
		System.out.println(prompt);
	}
	public static void display(PROMPTS prompt) {
		System.out.println(prompt.toString());
	}
	public static String read() {
		return sc.nextLine();
	}
}

package com.zookao.test;

import java.util.Scanner;

public class FamilyAccount{
	public static void main(String[] args){
		boolean isFlag = true;
		//用于记录用户的收入和支出的详情
		String details = "收支\t账户金额\t收支金额\t说    明\n";
		//初始金额
		int balance = 10000;
		while(isFlag){
			System.out.println(">>>>>>>家庭收支记账软件<<<<<<<");
			System.out.println("        1 收支明细");
			System.out.println("        2 登记收入");
			System.out.println("        3 登记支出");
			System.out.println("        4 退    出");
			System.out.print("        请选择(1-4)：");
			//获取用户的选择：1-4
			char selection = Utility.readMenuSelection();
			switch(selection){
				case '1':
					System.out.println(">>>>>>>当前收支明细记录<<<<<<<");
					System.out.println(details);
					break;
				case '2':
					System.out.print("本次收入金额：");
					int addMoney = Utility.readNumber();
					System.out.print("本次收入说明：");
					String addInfo = Utility.readString();
					//处理balance
					balance += addMoney;
					//处理details
					details += ("收入\t" + balance + "\t\t" + addMoney + "\t\t" + addInfo + "\n");
					System.out.println(">>>>>>>登记完成<<<<<<<\n");
					break;
				case '3':
					System.out.print("本次支出金额：");
					int minusMoney = Utility.readNumber();
					System.out.print("本次支出说明：");
					String minusInfo = Utility.readString();
					if(balance >= minusMoney){
						balance -= minusMoney;
						details += ("支出\t" + balance + "\t\t" + minusMoney + "\t\t" + minusInfo + "\n");
					}else{
						System.out.println("支出超出账户额度，支付失败");
					}
					System.out.println(">>>>>>>登记完成<<<<<<<\n");
					break;
				case '4':
					System.out.print("确认是否退出(Y/N)：");
					char isExit = Utility.readConfirmSelection();
					if(isExit == 'Y'){
						isFlag = false;
					}
			}
		}
	}
}

class Utility {
	private static Scanner scanner = new Scanner(System.in);

	public static char readMenuSelection() {
		char c;
		for (; ; ) {
			String str = readKeyBoard(1);
			c = str.charAt(0);
			if (c != '1' && c != '2' && c != '3' && c != '4') {
				System.out.print("选择错误，请重新输入：");
			} else break;
		}
		return c;
	}

	public static int readNumber() {
		int n;
		for (; ; ) {
			String str = readKeyBoard(4);
			try {
				n = Integer.parseInt(str);
				break;
			} catch (NumberFormatException e) {
				System.out.print("数字输入错误，请重新输入：");
			}
		}
		return n;
	}

	public static String readString() {
		String str = readKeyBoard(8);
		return str;
	}

	public static char readConfirmSelection() {
		char c;
		for (; ; ) {
			String str = readKeyBoard(1).toUpperCase();
			c = str.charAt(0);
			if (c == 'Y' || c == 'N') {
				break;
			} else {
				System.out.print("选择错误，请重新输入：");
			}
		}
		return c;
	}

	private static String readKeyBoard(int limit) {
		String line = "";
		while (scanner.hasNext()) {
			line = scanner.nextLine();
			if (line.length() < 1 || line.length() > limit) {
				System.out.print("输入长度（不大于" + limit + "）错误，请重新输入：");
				continue;
			}
			break;
		}
		return line;
	}
}
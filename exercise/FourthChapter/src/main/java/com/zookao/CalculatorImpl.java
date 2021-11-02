package com.zookao;

public class CalculatorImpl implements Calculator {
	public int add(int a, int b){
		if(a < 0 || b < 0){
			return -1;
		}
		long sum = (long) a + b;
		System.out.println("sum = " + sum);
		if(sum > Integer.MAX_VALUE){
			return -2;
		}
		return (int) sum;
	}
}

package it.unibs.pajc;

import java.util.HashMap;

public class Calc {
	public static void main(String[] args) {
		double a=2;
		double b=0;
		String op="^";
		double result=0;
		
		BinaryOp operator = null;
		
		HashMap<String, BinaryOp> operatori=new HashMap<String, BinaryOp>();
		
		operatori.put("+", new Somma());
		operatori.put("-", new Sottrazione());
		operatori.put("*", new Moltiplicazione());
		operatori.put("/", new Divisione());
		operatori.put("^", new Potenza());
		
		operator = operatori.get(op);
		
		if(operator!=null)
			result=operator.eval(a, b);
		
		System.out.printf("%.1f %s %.1f = %.1f",a,op,b,result);
	}
}

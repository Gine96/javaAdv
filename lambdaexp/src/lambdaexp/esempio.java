package lambdaexp;

import java.util.function.Consumer;

public class esempio {
	public static void main(String[] args) {
		ripeti(10,()->System.out.println("hello"));
		//qua da errore
		//ripeti(10,()->System.out.println(i));
		ripeti(10, (e)->System.out.println(e+" lmao"));
	}
	
	public static void hello(int n) {
		for(int i=0;i<n;i++) {
			System.out.println("hello");
		}
	}
	
	public static void star(int n) {
		for(int i=0;i<n;i++) {
			System.out.println("*");
		}
	}
	/*
	 * Questi due hanno in comune la stessa logica
	 * --> voglio evitare di scrivere 2 volte la stessa cosa
	 * */
	
	public static void ripeti(int n, Runnable action) {
		for(int i=0;i<n;i++)
			action.run();
	}
	
	/*
	 * qua la differenza è che la varibile da stampare è interna
	 * */
	public static void interi(int n) {
		for(int i=0;i<n;i++)
			System.out.println(i);
	}
	
	public static void pari(int n) {
		for(int i=0;i<n;i++)
			if(i%2==0)
				System.out.println("pari: " + i);
	}
	
	/*
	 * oltre a runnable ci sono altre interfacce che permettono di sfruttare le lambda exp
	 * */
	public static void ripeti(int n, Consumer action) {
		for(int i=0;i<n;i++)
			action.accept(i);
	}
	
}

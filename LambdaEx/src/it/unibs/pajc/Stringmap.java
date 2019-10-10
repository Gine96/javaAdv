package it.unibs.pajc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/*
 * crea un metodo che modifica una lista di stringhe con una funzione passata come parametro
 * */

public class Stringmap {
	public static void main(String[] args) {
		ArrayList<String> stringList = new ArrayList<String>(Arrays.asList(new String[] {"proviamo", "test", "unibs"}));
		ArrayList<Integer> intList = new ArrayList<Integer>(Arrays.asList(new Integer[] {1,2,3}));
		
		//ArrayList<String> modificato = mapList(lista, s->s.toUpperCase());
		//posso usare un metodo reference se la lambda exp implementa solo una funzione
		//ArrayList<String> modificato = mapList(lista, String::toUpperCase);
		
		printAll(stringList);
		printAll(map(stringList, String::toUpperCase));
		printAll(map(intList, x->x*x));
		//come faccio a fare sta cosa senza errore? --> override
		printAll(map(new Integer[] {1,2,3,4},x->x*x));
		
	}
	
	public static void printAll(List list) {
		System.out.println("----------------");
		for(Object o: list)
			System.out.println(o);
		System.out.println("----------------");
	}
	
	
	//generalizza questi metodi per qualunque oggetto --> uso i generics
//	public static ArrayList<String> mapList(ArrayList<String> list,Function<String, String> map){ 
//		ArrayList<String> temp=new ArrayList<String>();
//		for(String s: list)
//			temp.add(map.apply(s));
//		return temp;
//	}
//	
//	public static ArrayList<Integer> mapIntList(ArrayList<Integer> list,Function<Integer, Integer> map){ 
//		ArrayList<Integer> temp=new ArrayList<Integer>();
//		for(int s: list)
//			temp.add(map.apply(s));
//		return temp;
//	}
	
	public static <T> ArrayList<T> map(List<T> list, Function<T, T> map){
		ArrayList<T> temp = new ArrayList<T>();
		for(T t: list)
			temp.add(map.apply(t));
		return temp;
	}
	
	public static <T> ArrayList<T> map(T[] list, Function<T, T> map){
		return map(Arrays.asList(list), map);
	}
	
}

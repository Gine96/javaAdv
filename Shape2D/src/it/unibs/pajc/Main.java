package it.unibs.pajc;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
	public static void main(String[] args) {
		/*
		 * elenco oggetti raccolti
		 * li fai passare tutti con la descrizione di ognuno
		 * */
		ArrayList<Shape2D> list = new ArrayList<>();
		
		list.add(new Rectangle(3, 10));
		list.add(new Rectangle(5, 4));
		list.add(new Square(4));
		list.add(new Ellipse(4, 6));
		list.add(new Circle(7));
		
		for(Shape2D s : list) {
			System.out.println(s.toString());
		}
		System.out.println("gary sbaush");
		
		HashMap<String, Integer> mappaOggetti = new HashMap<String, Integer>();
		
		for(Shape2D s : list) {
			String key=s.getClass().getSimpleName();
			if(mappaOggetti.containsKey(key)) {
				int counter = mappaOggetti.get(key);
				mappaOggetti.put(key, counter+1);
			}else 
				mappaOggetti.put(key, 1);
		}
		
		for(HashMap.Entry<String, Integer> kv : mappaOggetti.entrySet()) {
			System.out.printf("%s: %d\n", kv.getKey(), kv.getValue());
		}
		
	}
}

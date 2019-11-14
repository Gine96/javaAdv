package it.unibs.pajc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest {
	public static void main(String[] args) {
		
		List<String> source = Arrays.asList("a1","a2","b1","c1","c2");
		
//		ArrayList<String> result = new ArrayList<String>();
//		
//		for(String s : source)
//			if(s.startsWith("c"))
//				result.add(s);
//		
//		ArrayList<String> result1 = new ArrayList<String>();
//		for(String s : source)
//			result1.add(s.toUpperCase());
//		
//		System.out.println(result);
//		System.out.println(result1);
		
		/*
		 * posso usare degli stream invocati direttamente sulla lista invece di usare cicli ogni volta
		 * gli stream sono unità composte da una serie di step
		 * in uno stream posso aggiungere togliere o cambiare ordine degli elementi
		 * */
		
//		source
//			.stream()
//			.filter(s -> s.startsWith("c"))//questa operazione non altera source ma trasforma stream
//			.map(s -> s.toUpperCase())//questa altera stream
//			.sorted()
//			.forEach(System.out::println);
		/*
		 * tra foreach e map c'è differenza
		 * foreach è un consumer -> non restituisce niente e consuma lo stream
		 * map invece restituisce un altro stream
		 */
		
		//posso generare stream tipizzati
		
		double result = IntStream
			.range(1, 10)
			.filter(x -> x%2!=0)
			.mapToDouble(x -> Math.sqrt(x))
			.sum();
		//sum torna un intero/quello che gli arriva prima
		System.out.println(result);
		
		Stream<String> myStream = source
			.stream()
			//qua non viene restituito l'intero array ma un elemento a volta
			.filter(s ->{
				System.out.println("filtro: " + s);
				return s.startsWith("c");
			});
		//questo non torna nulla
		myStream.forEach(System.out::println);
		//questo ritorna
		
		//questo genera un eccezione perchè stream è già stato consumato
//		myStream
//			.anyMatch(s -> {
//				System.out.println("match: " + s);
//				return s.startsWith("c2");
//			});
		
		Supplier<IntStream> generatore = () -> {
			return IntStream
				.iterate(2, x->x*2)
				.map(x -> x+1);
		};
		//facendo cosi creo un generatore dello stesso stream e posso usare get() per costruire ogni volta uno stream
		//si può usare supplier per creare generatori standard di oggetti
		generatore.get().anyMatch(x -> {
				System.out.println("Match: " + x);
				return x>=16;
			});
		//come faccio a rielaborare questo stream?
		//posso usare una lambda exp / interfaccia supplier
		
		/*
		 * esercizio:
		 * genera sequenza di fibonacci con uno stream
		 * */
		
		
	}
}

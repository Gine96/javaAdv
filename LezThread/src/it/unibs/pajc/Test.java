package it.unibs.pajc;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Test{
	
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		//solitamente crei gli oggetti direttamente nell'executorservice
//		Mytask task1 = new Mytask("task1", 31);
//		Mytask task2 = new Mytask("task2", 25);
//		Mytask task3 = new Mytask("task3", 13);
		
		/*
		 * cosi crei tipo una fabbrica con linee di montaggio
		 * puoi mettergli più compiti da eseguire
		 * cachedthreadpool usa più thread
		 * singlethread usa solo 1 quindi fa i task in ordine
		 * devi stopparlo altrimenti non termina da solo
		 */
		//ExecutorService executor = Executors.newSingleThreadExecutor();
		ExecutorService executor = Executors.newCachedThreadPool();
		
//		executor.submit(new Mytask("task1", 31));
//		executor.submit(new Mytask("task2", 25));
//		executor.submit(new Mytask("task3", 13));
		/*
		 * questo non stacca subito aspetta la fine
		 * tipo spegnere il pc
		 */
		executor.shutdown();
		
		System.out.println("fine!" + Thread.currentThread().getName());
		/*
		 * in questo modo crei un executor con un certo numero di thread
		 * se ne metti di più li fa dopo che ha finito
		 */
		ExecutorService ex2 = Executors.newFixedThreadPool(2);
		
//		Future<Integer> f1 = ex2.submit(new Mytask("task1", 31));
//		Future<Integer> f2 = ex2.submit(new Mytask("task2", 25));
//		Future<Integer> f3 = ex2.submit(new Mytask("task3", 13));
		ex2.shutdown();
		
		/*
		 * submit torna Future<>
		 */
		
//		System.out.println(f1.get());
//		System.out.println(f2.get());
//		System.out.println(f3.get());
		
		ExecutorService ex3 = Executors.newFixedThreadPool(2);
		
		List<Callable<String>> callables = Arrays.asList(
				()->"alfa",
				()->"beta",
				()->"gamma"
				);
		
		ex3.invokeAll(callables)
		.stream()
		.map(e->{
			try {
				return e.get();
			} catch (Exception e1) {
				
			}
			return null;
		})
		.forEach(System.out::println);
		ex3.shutdown();
		
		
		ExecutorService ex4 = Executors.newFixedThreadPool(3);
		Counter counter = new Counter();
		IntStream.range(0, 10000).forEach(i->ex4.submit(counter::inc));
		ex4.shutdown();
		ex4.awaitTermination(60, TimeUnit.SECONDS);
		System.out.println(counter.count);
		
		
		
	}
}

class Counter{
	int count=0;
	
	synchronized void inc() {
		count++;
	}
}


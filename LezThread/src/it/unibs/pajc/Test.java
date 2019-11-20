package it.unibs.pajc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test{
	
	
	public static void main(String[] args) {
	
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
		
		ex2.submit(new Mytask("task1", 31));
		ex2.submit(new Mytask("task2", 25));
		ex2.submit(new Mytask("task3", 13));
		ex2.shutdown();
		
	}
}

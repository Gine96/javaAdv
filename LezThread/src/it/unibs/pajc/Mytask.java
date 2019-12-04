package it.unibs.pajc;

import java.util.concurrent.Callable;

public class Mytask implements Callable<Integer>{
	
	private static int nTask=0;
	int id=0;
	int delay;
	String name;
	
	public Mytask(String name, int delay) {
		this.name=name;
		this.delay=delay;
		
		this.id=nTask++;
	}

	public Integer call() throws Exception{
		int step = 5000/delay;
		
		for(int i=0;i<step;i++) {
			System.out.format("%d, line: %4d -- %s\n", this.id, i, Thread.currentThread().getName());
			
			try {
				Thread.currentThread().sleep(delay);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return step;
		
	}
	
}

package it.unibs.pajc;

import java.util.Random;

public class Termostato {
	
	public enum StatoTermostato{
		LETTURA("lettura"){
			public StatoTermostato nextStep(Termostato term) {
				//genera un numero a caso per modificare la temperatura
				Random rand = new Random();
				term.currTemp+=term.accesa?rand.nextDouble()*1.5:rand.nextDouble()*1.2-1;
				System.out.println(term.currTemp);
				return CONTROLLO;
			}
		},
		CONTROLLO("controllo"){
			public StatoTermostato nextStep(Termostato term) {
				StatoTermostato statoSucc = WAIT;
				if(term.currTemp<term.tMin)
					statoSucc=ACCENDO;
				else if(term.currTemp>term.tMax)
					statoSucc=SPENGO;
				return statoSucc;
			}
		},
		ACCENDO("accendo"){
			public StatoTermostato nextStep(Termostato term) {
				term.accesa=true;
				return WAIT;
			}
		},
		SPENGO("spengo"){
			public StatoTermostato nextStep(Termostato term) {
				term.accesa=false;
				return WAIT;
			}
		},
		WAIT("wait"){
			public StatoTermostato nextStep(Termostato term) {
				//metti in pausa
				try {
					Thread.currentThread().sleep(500);
				} catch (InterruptedException e) {
					//e.printStackTrace();
				}
				return LETTURA;
			}
		};
		
		private String desc;
		StatoTermostato(String desc){
			this.desc=desc;
		}
		
		public String getDesc() {
			return this.desc;
		}
		
		public abstract StatoTermostato nextStep(Termostato term);
	}
	
	private double currTemp;
	private final double tMax, tMin;
	private StatoTermostato stato;
	private boolean accesa=false;
	
	public Termostato(double tMax, double tMin) {
		this.currTemp=21;
		this.tMax=tMax;
		this.tMin=tMin;
		this.stato=StatoTermostato.LETTURA;
	}
	
	public StatoTermostato getStato() {
		return this.stato;
	}
	
	public StatoTermostato nextStep() {
		stato = stato.nextStep(this);
		return stato;
	}
	
	
	
}

package it.unibs.pajc;

import it.unibs.pajc.Termostato.StatoTermostato;

public class Main {
	public static void main(String[] args) {
		Termostato termostato = new Termostato(27,18);
		while(true)
			System.out.println(termostato.nextStep());
	}
}

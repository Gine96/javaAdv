package it.unibs.pajc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
	
	public static String read(File file) {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(file));
			StringBuffer sb = new StringBuffer();
			String s;
			int numeroRiga=1;
			while((s=in.readLine())!=null) 
				sb.append(String.format("%3d| %s\n", numeroRiga++, s));
			return sb.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "";
	}
	
	public static void main(String[] args) {
		File file = new File("src/it/unibs/pajc/Main.java");
		String text = read(file);
		System.out.println(text);
		
		/*
		 * Prova scrittura
		File lmao = new File("lmao.ayy");
		try {
			lmao.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedWriter out=null;
		try {
			out = new BufferedWriter(new FileWriter(lmao));
			for(int i=0;i<1000;i++) {
				out.write("gary\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		*/
	}
}

package it.unibs.pajc;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileUtil {
	public static String read(File file, StringTransformer transf) {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(file));
			StringBuffer sb = new StringBuffer();
			String s;
			int numeroRiga=1;
			while((s=in.readLine())!=null) {
				s=transf.transform(s);
				sb.append(String.format("%3d| %s\n", numeroRiga++, s));
			}
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

	public static String read(File file) {
		return read(file, null);
	}

	public static void dump(String filename) {
		try {
			//cosi apri file binari
			DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(filename)));

			byte b;
			
			StringBuilder sb = new StringBuilder();
			try {
				for(int i=0;;i++) {
					b=in.readByte();
					if(i%16==0) { 
						if(sb.length()>0) {
							System.out.printf("  %s", sb.toString());
							sb = new StringBuilder();
						}
						System.out.printf("\n%04X  ", i);
					}
					//printf ti formatta le cose!!
					System.out.printf("%02X ", b);
					sb.append((b>=32&&b<=127) ? (char)b : '.');
					
					
					/*
					if(b>=32&&b<=127) 
						System.out.printf("%c ", b);
					else 
						System.out.printf("%c ", '.');
					*/	
					
				}
			} catch (EOFException e) {
				e.printStackTrace();
			}

			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}

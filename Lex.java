/*
Lex.java
Juan Andreas
jandreas
PA1
*/

import java.io.*;
import java.util.Scanner;
import java.util.Arrays;

class Lex{
	
	public static void main(String[] args) throws IOException{
		
		List L = new List();
		String line = null;
		String[] A = null;
		int lineCount = 0;
		int a = 0;
		int j = 0;
		int x;
		
		// check input
		if(args.length < 2){
			System.out.println("Usage: Lex input_file output_file");
			System.exit(1);
		}
		
		//open in and out files
		Scanner in = new Scanner(new File(args[0]));
		PrintWriter out = new PrintWriter(new FileWriter(args[1]));
		
		// read args[0] file and count for lines
		Scanner inLines = new Scanner(new File(args[0]));
		while(inLines.hasNextLine()){
			inLines.nextLine();
			lineCount++;
		}
		inLines.close();
		
		// puts input file words into String array
		A = new String[lineCount];
		
		while(in.hasNextLine()){
			A[a] = in.nextLine();
			a++;
		}
		
		// sorting algorithm---------------------------------------------------
		
		L.append(j); // inserts 0 initially
		for(j = 1; j < A.length; j++){
			L.moveFront();
			while(L.index() >= 0){
				x = L.get();
				// if A[j] comes before current
				if(A[j].compareTo(A[x]) < 0){
					L.insertBefore(j);
					L.moveBack();
				}
				if(A[j].compareTo(A[x]) == 0){
					L.insertAfter(j);
					L.moveBack();
				}
				L.moveNext();
			}
			// if A[j] comes after current
			if(A[j].compareTo(A[L.back()]) > 0 && L.index() == -1){
				L.append(j);
			}
			//System.out.println("List is: "+L);
		}
		//System.out.println(L.length());
		// --------------------------------------------------------------------
		
		// write into output file
		L.moveFront();
		for(int n = 0; n < A.length; n++){
			out.println(A[L.get()]);
			L.moveNext();
		}
		
		// close in and out files
		in.close();
		out.close();
	}
}
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Converter {
	File input;
	File output;
	Scanner sc;
	PrintWriter pw;
	private static final String EXON = "exon";
	private static final String INTRON = "intron";
	private static final String u5 = "Five_prime-UTR";
	private static final String u3 = "Three_prime-UTR";
	private static final String divider = "\t";
	
	
	public Converter(File input, File output){
		this.input = input;
		this.output = output;
	}
	
	public void execute(){
		try {
			 sc = new Scanner(input);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			 pw  = new PrintWriter(output);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Skip header
	   String header = sc.nextLine();
	   int columns = getColumnNum(header);
	   processLine(header, columns);
		while(sc.hasNextLine()){
			processLine(sc.nextLine(), columns);
		}
		sc.close();
		pw.close();
	}

	

	private int getColumnNum(String header) {
		String[] columns = header.split(divider);
		return columns.length;
	}

	private void processLine(String line, int num) {
		String[] data = line.split(divider);
		String id = data[0];
		String gls = data[1];
		String phase = data[2];
		if(num ==19){
			//Print exons
            for(int i = 5; i<18; i=i+2){
            printWithColon(id);
            printWithColon(gls);
            printWithColon(phase);
            printWithColon(EXON);
            printWithColon(Integer.toString((i-3)/2));
            pw.println(data[i]);
            }
            
            //Print 5 UTR
            printWithColon(id);
            printWithColon(gls);
            printWithColon(phase);
            printWithColon(u5);
            printWithColon("1");
            pw.println(data[4]);
            
            //Print introns
            for(int i = 6; i<18; i=i+2){
                printWithColon(id);
                printWithColon(gls);
                printWithColon(phase);
                printWithColon(INTRON);
                printWithColon(Integer.toString((i-4)/2));
                pw.println(data[i]);
                }
            
          //Print 3 UTR
          //  printWithColon(id);
           // printWithColon(gls);
           // printWithColon(phase);
           // printWithColon(u3);
           // printWithColon("1");
           // pw.println(data[19]);
            
		}else{
			//Print exons
			for(int i = 5; i<8; i=i+2){
	            printWithColon(id);
	            printWithColon(gls);
	            printWithColon(phase);
	            printWithColon(EXON);
	            printWithColon(Integer.toString((i-1)/2));
	            pw.println(data[i]);
	            }
			
			 //Print introns
            for(int i = 4; i<9; i=i+2){
                printWithColon(id);
                printWithColon(gls);
                printWithColon(phase);
                printWithColon(INTRON);
                printWithColon(Integer.toString((i-2)/2));
                pw.println(data[i]);
                }
			
		}
		
	}
	private void printWithColon(String s){
		pw.print(s);
		pw.print(",");
		
	}

}

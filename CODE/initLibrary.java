import java.io.*;
import java.io.File;
import java.util.*;
import java.net.*;
import java.nio.charset.Charset;

//import k_gram.*;
//import minHash.*;
//import jaccardDistance.*;
//import lsh.*;
public class initLibrary {
    String outputNames[];
    
    public static void kGramAll(String library, int gramTotal, int k) throws IOException {
		File libraryFile = new File(library);
		Scanner reader = new Scanner(libraryFile);
		String currBook = "";
		
		while(reader.hasNextLine()) {
			currBook = reader.nextLine();
			k_gram.write(currBook, "kGram_" + currBook, gramTotal, k);
		}
	
	}
	
	
	public static void jDistAll(String library, int gramTotal, int k) throws IOException{
		
		File saveFile = new File("jDist_" + gramTotal + "_" + k);
		if(saveFile.exists() && saveFile.isDirectory()) {
			//do nothing
		}
		else {
			saveFile.mkdirs();
		}
		
		File libraryFile = new File(library);
		File stats = new File("jDist_" + gramTotal + "_" + k, "Statistics.txt");
		
		Scanner reader = new Scanner(libraryFile);
		Scanner checker = new Scanner(libraryFile);
		
		String book1 = "";
		String book2 = "";
		
		float currJd = 0;
		
		float minJd = 1;
		String minBook1 = "";
		String minBook2 = "";
		
		float maxJd = 0;
		String maxBook1 = "";
		String maxBook2 = "";
		
		
		float currminJd = 1;
		String currminBook1 = "";
		String currminBook2 = "";
		
		float currmaxJd = 0;
		String currmaxBook1 = "";
		String currmaxBook2 = "";
		
		PrintWriter out = new PrintWriter(new FileOutputStream(stats, true));
		
		while(reader.hasNextLine()) {
			book1 = reader.nextLine();
			checker = new Scanner(libraryFile);
			out = new PrintWriter(new FileOutputStream(stats, true));
			while(checker.hasNextLine()) {
				book2 = checker.nextLine();
				if(book1.equals(book2)) {
					
				}
				else {
					currJd = jaccardDistance.write("kGram_" + book1, "kGram_" + book2, "jDist_" + book1, gramTotal, k);
					if(maxJd < currJd) {
						maxBook1 = book1.toString();
						maxBook2 = book2.toString();
						maxJd = currJd;
					}
					else if(minJd > currJd) {
						minBook1 = book1.toString();
						minBook2 = book2.toString();
						minJd = currJd;
					}

					if(currmaxJd < currJd) {
						currmaxBook1 = book1.toString();
						currmaxBook2 = book2.toString();
						currmaxJd = currJd;
					}
					else if(currminJd > currJd) {
						currminBook1 = book1.toString();
						currminBook2 = book2.toString();
						currminJd = currJd;
					}	
				}
			}
			
			out.println("The Min Jaccard Distance for the Book " + currminBook1 + " is with " + currminBook2);
			out.println("The Jaccard Distance is: " + currminJd);
			out.println("");
			out.println("The Max Jaccard Distance for the Book " + currmaxBook1 + " is with " + currmaxBook2);
			out.println("The Jaccard Distance is: " + currmaxJd);
			out.println("-------------------------------------------------");
			out.println("");
			out.println("");
			out.close();
			
			currminJd = 1;
			currminBook1 = "";
			currminBook2 = "";
			

			currmaxJd = 0;
			currmaxBook1 = "";
			currmaxBook2 = "";
		}
		
		out.println("The Min Jaccard Distance for the Library: " + minBook1 + " " + minBook2);
		out.println("The Jaccard Distance is: " + minJd);
		out.println("-------------------------------------------------");
		out.println("");
		out.println("");
		out.println("The Max Jaccard Distance for the Library: " + maxBook1 + " " + maxBook2);
		out.println("The Jaccard Distance is: " + maxJd);
		out.println("-------------------------------------------------");
		out.println("");
		out.println("");
		out.close();
	}
	
	public static void minHashAll(String library, int gramTotal, int k) throws IOException{
		File saveFile = new File("minHash_" + gramTotal + "_" + k);
		if(saveFile.exists() && saveFile.isDirectory()) {
			//do nothing
		}
		else {
			saveFile.mkdirs();
		}
		
		File libraryFile = new File(library);
		File stats = new File("minHash_" + gramTotal + "_" + k, "Statistics.txt");
		
		Scanner reader = new Scanner(libraryFile);
		Scanner checker = new Scanner(libraryFile);
		
		String book1 = "";
		String book2 = "";
		
		double currMh = 0.00;
		
		double minMh = 1.00;
		String minBook1 = "";
		String minBook2 = "";
		
		double maxMh = 0.00;
		String maxBook1 = "";
		String maxBook2 = "";
		
		
		double currminMh = 1.00;
		String currminBook1 = "";
		String currminBook2 = "";
		
		double currmaxMh = 0.00;
		String currmaxBook1 = "";
		String currmaxBook2 = "";
		
		PrintWriter out = new PrintWriter(new FileOutputStream(stats, true));
		
		while(reader.hasNextLine()) {
			book1 = reader.nextLine();
			checker = new Scanner(libraryFile);
			out = new PrintWriter(new FileOutputStream(stats, true));
			while(checker.hasNextLine()) {
				book2 = checker.nextLine();
				if(book1.equals(book2)) {
					
				}
				else {
					currMh = minHash.write(new File("kGram_" + gramTotal + "_" + k,"kGram_" + book1), new File("kGram_" + gramTotal + "_" + k,"kGram_" + book2), new File("minHash_" + gramTotal + "_" + k,"minHash_" + book1), gramTotal, k);
					if(maxMh < currMh) {
						maxBook1 = book1.toString();
						maxBook2 = book2.toString();
						maxMh = currMh;
					}
					else if(minMh > currMh) {
						minBook1 = book1.toString();
						minBook2 = book2.toString();
						minMh = currMh;
					}

					if(currmaxMh < currMh) {
						currmaxBook1 = book1.toString();
						currmaxBook2 = book2.toString();
						currmaxMh = currMh;
					}
					else if(currminMh > currMh) {
						currminBook1 = book1.toString();
						currminBook2 = book2.toString();
						currminMh = currMh;
					}	
				}
			}
			
			out.println("The Min Min Hash for the Book " + currminBook1 + " is with " + currminBook2);
			out.println("The Min Hash is: " + currminMh);
			out.println("");
			out.println("The Max Min Hash for the Book " + currmaxBook1 + " is with " + currmaxBook2);
			out.println("The Min Hash is: " + currmaxMh);
			out.println("-------------------------------------------------");
			out.println("");
			out.println("");
			out.close();
			
			currminMh = 1.00;
			currminBook1 = "";
			currminBook2 = "";
			

			currmaxMh = 0.00;
			currmaxBook1 = "";
			currmaxBook2 = "";
		}
		
		out.println("The Min Min Hash for the Library: " + minBook1 + " " + minBook2);
		out.println("The Min Hash is: " + minMh);
		out.println("-------------------------------------------------");
		out.println("");
		out.println("");
		out.println("The Max Min Hash for the Library: " + maxBook1 + " " + maxBook2);
		out.println("The Min Hash is: " + maxMh);
		out.println("-------------------------------------------------");
		out.println("");
		out.println("");
		out.close();
	}
	
	
}

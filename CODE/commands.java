import java.io.*;
import java.io.File;
import java.util.*;
import java.net.*;
import java.nio.charset.Charset;

public class commands {

	public static void kGramLibrary(int gramTotal, int k)   {
		try {
			initLibrary.kGramAll("library.txt", gramTotal, k);
		}catch(IOException e){
			System.out.println("Failed at Run!");
		}
	}
	
	public static void jDistLibrary(int gramTotal, int k) throws IOException {
		initLibrary.jDistAll("library.txt", gramTotal, k);
	}
	
	public static void minHashLibrary(int gramTotal, int k) throws IOException {
		System.out.println("in commands");
		initLibrary.minHashAll("library.txt", gramTotal, k);
	}

	public static void initLibrary(int gramTotal, int k) throws IOException {
		kGramLibrary(gramTotal, k);
		try{ 
			jDistLibrary(gramTotal, k);
			minHashLibrary(gramTotal, k);
			}finally {
				return;
			}
	}
	
	public static void kGramBook(String newBook, String kgrams, int gramTotal, int k) {
		k_gram.write(newBook, kgrams, gramTotal, k);
	}
	
	public static void jDistBook(String library, int gramTotal, int k) throws IOException {
		File saveFile = new File("jDist_" + gramTotal + "_" + k);
		if(saveFile.exists() && saveFile.isDirectory()) {
			saveFile.delete();
			saveFile.mkdirs();
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
	
	public static void minHashBook(String library, int gramTotal, int k) throws IOException {
		File saveFile = new File("minHash_" + gramTotal + "_" + k);
		if(saveFile.exists() && saveFile.isDirectory()) {
			saveFile.delete();
			saveFile.mkdirs();
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
	
	public static void addBook(String newBook, int gramTotal, int k) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter("library.txt",true));
		out.println(newBook);
		out.close();
		kGramBook(newBook, "kGram_" + newBook, gramTotal, k);	
		jDistBook("library.txt", gramTotal, k);
		minHashBook("library.txt", gramTotal, k);
		
	}
	
	//to be finsihed
	public static void retrieveBookjDist(int gramTotal, int k, String book) throws IOException{
		File stats = new File("jDist_" + gramTotal + "_" + k, "jDist_" + book);
		Scanner reader = new Scanner(stats);
		while(reader.hasNextLine()) {
			System.out.println(reader.nextLine());
		}
	}
	//to be finished
	public static void retrieveBookminHash(int gramTotal, int k, String book) throws IOException {
		File stats = new File("minHash_" + gramTotal + "_" + k, "minHash_" + book);
		Scanner reader = new Scanner(stats);
		while(reader.hasNextLine()) {
			System.out.println(reader.nextLine());
		}
	}
	//to be finished
	public static void retrieveAlljDist(int gramTotal, int k) throws IOException {
		File stats = new File("jDist_" + gramTotal + "_" + k, "Statistics.txt");
		Scanner reader = new Scanner(stats);
		while(reader.hasNextLine()) {
			System.out.println(reader.nextLine());
		}
	}
	//to be finished
	public static void retrieveAllminHash(int gramTotal, int k) throws IOException {
		File stats = new File("minHash_" + gramTotal + "_" + k, "Statistics.txt");
		Scanner reader = new Scanner(stats);
		while(reader.hasNextLine()) {
			System.out.println(reader.nextLine());
		}
	}
	//to be finished
	public static void retrieveBook(int gramTotal, int k, String book) throws IOException {
		retrieveBookjDist(gramTotal, k, book);
		retrieveBookminHash(gramTotal, k, book);
	}
	
	public static void retrieveAll(int gramTotal, int k) throws IOException {
		retrieveAlljDist(gramTotal, k);
		retrieveAllminHash(gramTotal, k);
	}
	
	//to be finished: extra feature
	/*public static void printCurrentDirectories() {
		File file = new File();
		String[] directories = file.list(new FilenameFilter() {
		  @Override
		  public boolean accept(File current, String name) {
		    return new File(current, name).isDirectory();
		  }
		});
		String[] dirNames = Arrays.toString(directories));
		for(int i = 0; i < dirNames.size(); i++) {
			char[] dirName = dirNames[i].toCharArray();
			for(int j = 0; j < dirName.size(); j++) {
				char currChar = dirName[j];
				if(curr)
			}
		}
	}*/
	
	
}
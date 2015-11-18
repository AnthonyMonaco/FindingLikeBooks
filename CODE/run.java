
import java.io.*;
import java.io.File;
import java.util.*;
import java.net.*;
import java.nio.charset.Charset;


public class run {
	
	public static void main(String[] args) throws IOException {
		Scanner userInputScanner = new Scanner(System.in);
		//commands.initLibrary(150,2);
		/*try{ 
			commands.minHashLibrary(150,2);
			}finally {
				return;
			}*/
			
		System.out.println("Please choose from the Following menu Items:");
		System.out.println("Initialize Library, add a book, or retrieve information from the library?");
		System.out.println("Type init, add, or retrieve respectively.");
		String command = userInputScanner.nextLine();
		
		if(command.equals("init")) {
			System.out.println("please pick the number of grams");
			int gramTotal = userInputScanner.nextInt();
			System.out.println("please pick k for k-gram: either 2 or 3.");
			int k = userInputScanner.nextInt();
			commands.initLibrary(gramTotal,k);
		}
		
		else if(command.equals("add")) {
			System.out.println("Please type the name of the file to be added:");
			String book = userInputScanner.nextLine();
			System.out.println("please type the number of grams and type of k-gram of an existing library: ");
			System.out.println("put a space inbetween the two numbers:");
			int gramTotal = userInputScanner.nextInt();
			int k = userInputScanner.nextInt();
			commands.addBook(book,gramTotal,k);
		}
		
		else if(command.equals("retrieve")) {
			System.out.println("Please type the name of the file to be retrieved: Type all for the entire library");
			String book = userInputScanner.nextLine();
			System.out.println("please type the number of grams and type of k-gram of an existing library:");
			System.out.println("put a space in between the two numbers:");
			int gramTotal = userInputScanner.nextInt();
			int k = userInputScanner.nextInt();
			if(book.equals("all")) {
				commands.retrieveAll(gramTotal, k);
			}
			else {
				commands.retrieveBook(gramTotal,k,book);
			}
		}
	}
	
}
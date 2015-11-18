import java.io.*;
import java.io.File;
import java.util.*;
import java.net.*;
import java.nio.charset.Charset;

public class k_gram {
	
	
	public static boolean check_file3word(String next1, String next2, String next3, File save){
		Scanner reader = null;
		try {
			reader = new Scanner(save);
			} catch (FileNotFoundException e) {
					e.printStackTrace();  
			  }
		String next01;
		if (reader.hasNext()) {
			next01 = reader.next();
		}
		else {
			reader.close();
			return false;
		}
		//Check to see if you have written to this file yet.	
		String next02;
		if (reader.hasNext()) {
			next02 = reader.next();
		}
		else {
			reader.close();
			return false;
		}
		String next03;
		if (reader.hasNext()) {
			next03 = reader.next();
		}
		else {
			reader.close();
			return false;
		}
		
		while(true) {
		  if(next1.equals(next01)) {
			if(next2.equals(next02)) {
				if(next3.equals(next03)){
					reader.close();
					return true;
				}
			}
		  }
		  next01 = next02;
		  next02 = next03;
		  if (reader.hasNext()) {
			if((next03 = reader.next()).equals(",")) {
				if(reader.hasNext()) {
					next03 = reader.next();
				}
				else {
					reader.close();
					return false;
				}
			}
		  }
		  else {
			reader.close();
			return false;
		  }
		}
    }
	
	public static boolean check_file2word(String next1, String next2, File save){
		Scanner reader = null;
		try {
			reader = new Scanner(save);
			} catch (FileNotFoundException e) {
					e.printStackTrace();  
			  }
			
		String next01;
		if (reader.hasNext()) {
			next01 = reader.next();
		}
		else {
			reader.close();
			return false;
		}
		
		String next02;
		if (reader.hasNext()) {
			next02 = reader.next();
		}
		else {
			reader.close();
			return false;
		}
		
		while(true) {
		  if(next1.equals(next01)) {
				if(next2.equals(next02)){
					reader.close();
					return true;
				}

		  }
		  next01 = next02;
		  if (reader.hasNext()) {
			if((next02 = reader.next()).equals(",")) {
				if(reader.hasNext()) {
					next02 = reader.next();
				}
				else {
					reader.close();
					return false;
				}
			}
		  }
		  else {
			reader.close();
			return false;
		  }
		}
    }
	
	
	public static void read_3word(File f1, File save, int gramTotal, int k) throws IOException {
		Scanner reader = null;
		Scanner checker = null;
		String matcher1 = null;
		String matcher2 = null;
		String matcher3 = null;
		
		try {
			reader = new Scanner(f1);
			} catch (FileNotFoundException e) {
					e.printStackTrace();  
			  }
			
		for(int i = 0; i < 500 ; i++) {
			matcher1 = reader.next();
		}
		
		matcher1 = reader.next().toLowerCase();
		matcher2 = reader.next().toLowerCase();
		matcher3 = reader.next().toLowerCase();
		
		
		
		String next1 = null;
		String next2 = null;
		String next3 = null;

		PrintWriter out = new PrintWriter(new FileOutputStream(save, true));
		int counter = 0;
		Scanner checkSv = new Scanner(save);
		
		if(checkSv.hasNext()) {
			System.out.println("Already in kGram form");
		}
		else {
			while (counter < gramTotal) {
				try {
					checker = new Scanner(f1);
					} catch (FileNotFoundException e) {
						e.printStackTrace();  
					}
				for(int i = 0; i < 500 ; i++) {
					next1 = checker.next();
				}
				next1 = checker.next().toLowerCase();
				next2 = checker.next().toLowerCase();
				next3 = checker.next().toLowerCase();
				while(true) {
					if (next1.equals(matcher1)) {
						if(next2.equals(matcher2)) {
							if(next3.equals(matcher3)) {
								if(check_file3word(next1,next2,next3,save)) {
									//do nothing
								}
								else {
									out = new PrintWriter(new FileOutputStream(save, true));
									out.println(matcher1 + " " + matcher2 + " " + matcher3);
									out.close();
									//update

								}
							}
						}
					} 
					else {
						//did not match
					}	
					//update
					next1 = next2;
					next2 = next3;
			
					if(checker.hasNext()) {
						next3 = checker.next().toLowerCase();
					}
					else{
						break;
					}
				}
			
				matcher1 = matcher2;
			
				matcher2 = matcher3;
			
				if(reader.hasNext()) {
					matcher3 = reader.next().toLowerCase();
			
				}
				else {
					checker.close();
					reader.close();
					return;
				}
				counter++;	
			}
			checker.close();
			reader.close();
		}


	}
	public static void read_2word(File f1, File save, int gramTotal, int k) throws IOException {
		Scanner reader = null;
		Scanner checker = null;
		String matcher1 = null;
		String matcher2 = null;
		
		try {
			reader = new Scanner(f1);
			} catch (FileNotFoundException e) {
					e.printStackTrace();  
			  }
			
		for(int i = 0; i < 500 ; i++) {
			matcher1 = reader.next();
		}
		
		matcher1 = reader.next().toLowerCase();
		matcher2 = reader.next().toLowerCase();
		
		
		
		String next1 = null;
		String next2 = null;

		PrintWriter out = new PrintWriter(new FileOutputStream(save, true));
		Scanner checkSv = new Scanner(save);
		if(checkSv.hasNext()) {
			System.out.println("Already in kGram form");
		}
		else {
			int counter = 0;
			while (counter < gramTotal && reader.hasNext()) {
				try {
					checker = new Scanner(f1);
					} catch (FileNotFoundException e) {
							e.printStackTrace();  
				  		}
						for(int i = 0; i < 500 ; i++) {
							next1 = checker.next();
						}
						next1 = checker.next().toLowerCase();
						next2 = checker.next().toLowerCase();
				while(true) {
					if (next1.equals(matcher1)) {
						if(next2.equals(matcher2)) {
							if(check_file2word(next1,next2,save)) {
								//do nothing
							}
							else {
								out = new PrintWriter(new FileOutputStream(save, true));
								out.println(matcher1 + " " + matcher2);
								out.close();
								//update

							}
						}
					} 
					else {
						//did not match
					}
					//update
					next1 = next2;
				
					if(checker.hasNext()) {
						next2 = checker.next().toLowerCase();
					}
					else{
						break;
					}
				}
				matcher1 = matcher2;
				if(reader.hasNext()) {
					matcher2 = reader.next().toLowerCase();
			
				}
				else {
					checker.close();
					reader.close();
					return;
				}
				counter++;	
			}
			checker.close();
			reader.close();
		}

	}
	
	
	
	
	public static void write(String f1, String save, int gramTotal, int k) {
		File currBook = new File(f1);
		File saveFile = new File("kGram_" + gramTotal + "_" + k);
		if(saveFile.exists() && saveFile.isDirectory()) {
			//do nothing
		}
		else {
			saveFile.mkdirs();
		}
		saveFile = new File("kGram_" + gramTotal + "_" + k, save);
		if(k == 3) {
			try {	
				read_3word(new File(f1), saveFile, gramTotal, k);	
			}
			finally {	
				return;
			}
		}
		else {
			try {	
				read_2word(new File(f1), saveFile, gramTotal, k);	
			}
			finally {	
				return;
			}
		}
	}
}
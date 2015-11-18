import java.io.*;
import java.io.File;
import java.util.*;
import java.net.*;
import java.nio.charset.Charset;


public class jaccardDistance {
	
	public static int count(File f1) {
		int count = 0;
		Scanner reader = null;
		try {
			reader = new Scanner(f1);
			} catch (FileNotFoundException e) {
					e.printStackTrace();  
			  }
		while(reader.hasNext()) {
			
			reader.nextLine();
			count++;
		}
		reader.close();
		return count;
	} 
	
	public static float read(File f1, File f2, File save) throws IOException {
		
		float union = 0;
		float set1 = 0;
		float set2 = 0;
		float intersection = 0;
		float jd = 0;
		Scanner reader = null;
		Scanner checker = null;
		String matcher1 = null;
		PrintWriter out = new PrintWriter(new FileOutputStream(save, true));
		
		try {
			reader = new Scanner(f1);
			} catch (FileNotFoundException e) {
					e.printStackTrace();  
			  }
		
		String next1 = null;
		try {
			checker = new Scanner(f2);
		} 
		catch (FileNotFoundException e) {
					e.printStackTrace();  
		}
		
			while(checker.hasNext()) {
				set2++;
				checker.nextLine();
			}

			while (reader.hasNext()) {
				matcher1 = reader.nextLine();
				try {
					checker = new Scanner(f2);
					} catch (FileNotFoundException e) {
							e.printStackTrace();  
				  	}
				next1 = checker.nextLine();
				set1++;
				while(checker.hasNext()) {
				
					next1 = checker.nextLine();
					if (next1.equals(matcher1)) {
						intersection++;
						set1--;
					} 
					else {
					
					}
				//update
				
				}
			}
			union = set1 + set2;	
			
		
			out.println("The Jaccard Distance for the Following Files: " + f1.getName() + " and " + f2.getName());
			out.println("The union is: " + union);
			out.println("The Intersection is: " + intersection);
			jd = 1 - (intersection/union);
			out.println("The Jaccard Distance is: " + jd);
			out.println("-------------------------------------------------");
			out.println("");
			out.println("");
			out.close();
			checker.close();
			reader.close();
			return jd;
	}
	
	public static float write (String file1, String file2, String save, int gramTotal, int k) throws IOException {	
		float jd = 0;
		
		File saveFile = new File("jDist_" + gramTotal + "_" + k);
		saveFile = new File("jDist_" + gramTotal + "_" + k, save);
		
		
		try {
			jd = read(new File("kGram_" + gramTotal + "_" + k, file1), 
					  new File("kGram_" + gramTotal + "_" + k, file2), saveFile);	
		}finally {
			return jd;
		}
		
	}
	
	
}
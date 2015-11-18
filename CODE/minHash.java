import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.io.*;
import java.io.File;
import java.util.*;
import java.net.*;
import java.nio.charset.Charset;
import java.math.*;

public class minHash<T> {

	private int hash[];
	private int numHash;
	public static Set<String> set1;
	public static Set<String> set2;
    
	/**
	 * 
	 */
	public minHash(int numHash){
		this.numHash = numHash;
        hash = new int[numHash];

        Random r = new Random(11);
        for (int i = 0; i < numHash; i++){
            int a = (int)r.nextInt();
            int b = (int)r.nextInt();
            int c = (int)r.nextInt();
            int x = hash(a*b*c, a, b, c);
            hash[i] = x;
        } 
    }
	

    public double similarity(Set<T> set1, Set<T> set2, int t){

        int numSets = 2;
        Map<T, boolean[]> bitMap = buildBitMap(set1, set2);
        
        int[][] minHashValues = initializeHashBuckets(numSets, numHash);

        computeminHashForSet(set1, 0, minHashValues, bitMap);
        computeminHashForSet(set2, 1, minHashValues, bitMap);

        return computeSimilarityFromSignatures(minHashValues, numHash, t);
    }
    
	/**
	 * 
	 */
	private static int[][] initializeHashBuckets(int numSets, int numHashFunctions) {
		int[][] minHashValues = new int[numSets][numHashFunctions];

        for (int i = 0; i < numSets; i++) {
        	for (int j = 0; j < numHashFunctions; j++) {
        		minHashValues[i][j] = Integer.MAX_VALUE;
            }
        }
        return minHashValues;
    }
	 
	/**
	 * 
	 * @param minHashValues
	 * @param numHashFunctions
	 * @return
	 */
	private static double computeSimilarityFromSignatures(int[][] minHashValues, int numHashFunctions, int t) {
		int identicalminHashes = 0;
        for (int i = 0; i < numHashFunctions; i++){
            if (minHashValues[0][i] == minHashValues[1][i]) {
				identicalminHashes++;
				if (identicalminHashes == t) {
					return 1.0;
				}
            }
        }
		double j = 1.0000000/(double)numHashFunctions;
		double m = j*identicalminHashes;
		double result = 1.0000000 - m;
		return result;
    }

	
	/**
	 * 
	 * @param x
	 * @param a
	 * @param b
	 * @param c
	 * @return
	 */
	private static int hash(int x, int a, int b, int c) {
		//40127
        int hashValue = (int)((a * (x >> 4) + b * x + c) & 40127);
        return Math.abs(hashValue);
    }
	
	
    private void computeminHashForSet(Set<T> set, int setIndex, int[][] minHashValues, Map<T, boolean[]> bitArray){

    	int index=0;
    	
    	for(T element : bitArray.keySet()) { // for every element in the bit array
    		if(index >= numHash) {
				break;
			}
			for (int i = 0; i < numHash; i++){ // for every hash 
    			if(set.contains(element)) { // if the set contains the element
    				int hindex = hash[index]; // get the hash
    				if (hindex < minHashValues[setIndex][index]) { 
    					// if current hash is smaller than the existing hash in the slot then replace with the smaller hash value
    					minHashValues[setIndex][i] = hindex;
    				}
    			}
    		}
    		index++;
    	}
    }
    
	/**
	 * 
	 * @param set1
	 * @param set2
	 * @return
	 */
	public Map<T,boolean[]> buildBitMap(Set<T> set1, Set<T> set2){
		
		Map<T,boolean[]> bitArray = new HashMap<T,boolean[]>();
		
		for(T t : set1){
			bitArray.put(t, new boolean[]{true,false});
		}
		
		for(T t : set2){
			if(bitArray.containsKey(t)){
				// item is not present in set1
				bitArray.put(t, new boolean[]{true,true});
			}else if(!bitArray.containsKey(t)){
				// item is not present in set1
				bitArray.put(t, new boolean[]{false,true});
			}
		}
		
		
		return bitArray;
	}
	
	
	public static void init(File file1, File file2) {
		Scanner reader = null;
		Scanner checker = null;
		String nextLine = null;
		try {
			reader = new Scanner(file1);
			} catch (FileNotFoundException e) {
					e.printStackTrace();  
			  }
		while (reader.hasNext()) {
			nextLine = reader.nextLine();
			set1.add(nextLine);
		}
		try {
			checker = new Scanner(file2);
			} catch (FileNotFoundException e) {
					e.printStackTrace();  
			  }
		while (checker.hasNext()) {
			set2.add(checker.nextLine());
		}
	}
	
	public static double write(File file1, File file2, File save, int gramTotal, int k) throws IOException {
		System.out.println("minHash");
		set1 = new HashSet<String>();
		set2 = new HashSet<String>();
		
		init(file1,file2);
		double least = 0.00;
		double ans = 0.00;
		try {
		PrintWriter out = new PrintWriter(new FileOutputStream(save, true));
		out.println("------Normalized-L0 bewtween files " + file1 + " and " + file2 + "-------------");
		minHash<String> minHash = null;
		minHash = new minHash<String>(set1.size()+set2.size());
		ans = minHash.similarity(set1, set2, 5);
		out.println("Normalized-L0 when t = " + 5 + ": " + ans);
		if(ans < least) {
			least = ans;
		}
		out.close();
	    }
		finally {
			return least;
		}
	}
}
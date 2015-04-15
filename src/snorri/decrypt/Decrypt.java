package snorri.decrypt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Decrypt {
	
	public static void main(String[] args) throws IOException {
		
		String text = cleanUp(readFile("cipherText.txt"));
		String corpus = cleanUp(readFile("corpus.txt"));
		
		FrequencyMatrix bestFit = checkKeyLengths(text, corpus, 20);
		DecryptionMatrix decrypt = new DecryptionMatrix(bestFit);
		
		System.out.println("\nFrequency Matrix: ");
		bestFit.print(26);
		System.out.println("\nDecryption Matrix: ");
		decrypt.print();
		
		System.out.println();
		
		System.out.println("Ciphertext/Plaintext Comparison: ");
		System.out.println(text.substring(0, 40).toUpperCase());
		System.out.println(decrypt.decrypt(text.substring(0, 40)));
		
	}
	
	static String readFile(String fileName) throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader(fileName));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        return sb.toString();
	    } finally {
	        br.close();
	    }
	}
	
	public static String cleanUp(String msg) {
		msg = msg.toLowerCase().replaceAll("[^a-z]", "");
		return msg;
	}
	
	/*
	 * 
	 * TODO: make this a more sophisticated check for the "right" one?
	 * Take the lowest significant one rather than the minimum?
	 * alpha = 0.05 seems good
	 * 
	 * Currently, this is sketchy af, but very efficient
	 * Messes around with reference types and shit
	 * 
	 */
	
	public static FrequencyMatrix checkKeyLengths(String text, String corpus, int max) {
		
		double minChiSquared = Double.MAX_VALUE;
		FrequencyMatrix optimumMatrix = new FrequencyMatrix(0, 0);
		int keySize = 1;
		
		FrequencyDistribution corpusFreq;
		for (int i = 2; i < max + 1; i++) {
			FrequencyMatrix matrix = new FrequencyMatrix(i, 26);
			corpusFreq = new FrequencyDistribution();
			corpusFreq.addText(corpus);
			matrix.addRow(corpusFreq);
			matrix.loadVigenere(text);
			
			if (matrix.getChiSquared() < minChiSquared) {
				optimumMatrix = matrix;
				minChiSquared = matrix.getChiSquared();
				keySize = i;
			}
		}
		
		System.out.println("Key Size: " + keySize + " (chi-squared = " + optimumMatrix.getChiSquared() + ")");
		
		return optimumMatrix;
		
	}

}

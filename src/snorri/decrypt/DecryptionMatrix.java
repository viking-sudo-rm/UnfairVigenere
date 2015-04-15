package snorri.decrypt;

public class DecryptionMatrix {
	
	/*
	 * 
	 * TODO: implement a genetic algorithm to optimize english bigram frequencies, etc.
	 * 
	 */

	private char[] plaintext;
	private char[][] matrix;
	
	public DecryptionMatrix(FrequencyMatrix matrix) {
		
		plaintext = new char[matrix.getAlphabetSize()];
		this.matrix = new char[matrix.getAlphabetSize()][matrix.getKeySize()];
		
		FrequencyDistribution plainDist = matrix.getRow(0);
		Long[] alphabet = plainDist.getAlphabet(matrix.getAlphabetSize());
		for (int i = 0; i < alphabet.length; i++)
			plaintext[i] = plainDist.getKey(alphabet[i]);
		
		for (int j = 0; j < matrix.getKeySize(); j++) {
			plainDist = matrix.getRow(j + 1);
			alphabet = plainDist.getAlphabet(matrix.getAlphabetSize());
			for (int i = 0; i < alphabet.length; i++) {
				this.matrix[i][j] = plainDist.getKey(alphabet[i]);
			}
		}
				
	}
	
	public char makePlain(char c, int j) {
		for (int i = 0; i < plaintext.length; i++) {
			if (matrix[i][j] == c)
				return plaintext[i];
		}
		return '_';
	}
	
	public String decrypt(String ciphertext) {
		String output = "";
		
		int key;
		for (int index = 0; index < ciphertext.length(); index++) {
			key = index % matrix[0].length;
			output += makePlain(ciphertext.charAt(index), key);
			
		}
		
		return output;
	}
	
	public void print() {
		System.out.print("  ");
		for (int i = 0; i < plaintext.length; i++) {
			System.out.print(plaintext[i] + " ");
		}
		System.out.println();
		for (int j = 0; j < matrix[0].length; j++) {
			System.out.print(j + " ");
			for (int i = 0; i < plaintext.length; i++) {
				//System.out.print(matrix[i]);
				System.out.print(Character.toUpperCase(matrix[i][j]) + " ");
			}
			System.out.println();
		}
	}
	
}

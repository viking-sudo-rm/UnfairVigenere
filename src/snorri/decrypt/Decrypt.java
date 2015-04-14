package snorri.decrypt;

public class Decrypt {

	private static final String TEXT = "asldkfjasdl;kcahsdl;kfhadfl;kajsdcal;skdjfasdf;alsjkfa";
	
	public static void main(String[] args) {
		getFrequencyMatrix(3);
	}
	
	public static void getFrequencyMatrix(int keySize) {
		for (int i = 0; i < keySize; i++) {
			FrequencyDistribution dist = new FrequencyDistribution();
			for (int j = 0; j < TEXT.length(); j++) {
				if (j % keySize == i)
				dist.add(TEXT.charAt(j));
			}
		
			int[] alphabet = dist.getAlphabet(10);
			System.out.println(alphabet[0]);
			System.out.println(alphabet.length);
		
		}
	}

}

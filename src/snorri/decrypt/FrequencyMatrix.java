package snorri.decrypt;

import java.util.ArrayList;

public class FrequencyMatrix {
	
	private ArrayList<FrequencyDistribution> rows;
	
	private int keySize, alphabetSize;
	
	public FrequencyMatrix(int keySize, int alphabetSize) {
		rows = new ArrayList<FrequencyDistribution>();
		this.keySize = keySize;
		this.alphabetSize = alphabetSize;
	}
	
	public void addRow(FrequencyDistribution dist) {
		rows.add(dist);
	}
	
	public void loadVigenere(String text) {
		for (int i = 0; i < keySize; i++) {
			FrequencyDistribution dist = new FrequencyDistribution();
			for (int j = 0; j < text.length(); j++) {
				if (j % keySize == i)
				dist.add(text.charAt(j));
			}
			addRow(dist);
		}
	}
	
	public double getExpected(int i, int j) {
		
		double sum = 0;
		for (FrequencyDistribution dist : rows)
			sum += 1d * dist.getAlphabet(alphabetSize)[i];
		
		return sum * rows.get(j).getSum() / getSum();
		
	}
	
	public long getSum() {
		long sum = 0;
		for (FrequencyDistribution dist : rows)
			sum += dist.getSum();
		return sum;
	}
	
	public double getChiSquared() {
		long observed;
		double chiSquared = 0, expected, difference;
		for (int c = 0; c < alphabetSize; c++) {
			for (int rule = 0; rule < keySize; rule++) {
				expected = getExpected(c, rule);
				if (expected == 0)
					continue;
				observed = rows.get(rule).getAlphabet(alphabetSize)[c];
				difference = observed - expected;
				chiSquared += difference * difference / expected;		
			}
		}
		return chiSquared;
	}
	
	public void print(int size) {
		for (FrequencyDistribution dist : rows) {
			dist.print(size);
			System.out.println();
		}
	}
	
	public FrequencyDistribution getRow(int i) {
		return rows.get(i);
	}
	
	public int getKeySize() {
		return keySize;
	}
	
	public int getAlphabetSize() {
		return alphabetSize;
	}

}

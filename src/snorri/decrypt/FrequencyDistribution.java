package snorri.decrypt;

import java.util.HashMap;
import java.util.Iterator;

public class FrequencyDistribution extends HashMap<Character, Integer> {

	private static final long serialVersionUID = 1L;

	public FrequencyDistribution() { }
	
	public void add(Character c) {
		put(c,  get(c) + 1);
	}
	
	public int get(Character c) {
		return (super.get(c) == null) ? 0 : super.get(c);
	}
	
	public int[] getAlphabet(int size) {
		Iterator<Integer> iterator = values().iterator();
		int[] result = new int[size];
		for (int i = 0; i < size; i++)
			result[i] = iterator.hasNext() ? iterator.next() : 0;
		return result;
	}
	
}

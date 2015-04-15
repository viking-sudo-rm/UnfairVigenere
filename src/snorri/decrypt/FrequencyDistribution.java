package snorri.decrypt;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class FrequencyDistribution extends HashMap<Character, Long> {
	
	private static final long serialVersionUID = 1L;

	public FrequencyDistribution() { }
	
	public void add(Character c) {
		put(c,  get(c) + 1);
	}
	
	public long get(Character c) {
		return (super.get(c) == null) ? 0 : super.get(c);
	}
	
	public void addText(String text) {
		for (int i = 0; i < text.length(); i++)
			add(text.charAt(i));
	}
	
	public Long[] getAlphabet(int size) {
		Iterator<Long> iterator = values().iterator();
		Long[] result = new Long[size];
		for (int i = 0; i < size; i++)
			result[i] = iterator.hasNext() ? iterator.next() : 0L;
		Arrays.sort(result, Collections.reverseOrder());
		return result;
	}
	
	public long getSum() {
		int sum = 0;
		for (long value : values())
			sum += value;
		return sum;
	}
	
	public Character getKey(long value) {
	    for (Character o : keySet()) {
	      if (get(o) == value) {
	        return o;
	      }
	    }
	    return '_';
	  }
	
	public void print(int size) {
		for (int i = 0; i < getAlphabet(size).length; i++)
			System.out.print(" " + getAlphabet(size)[i]);
	}
	
}

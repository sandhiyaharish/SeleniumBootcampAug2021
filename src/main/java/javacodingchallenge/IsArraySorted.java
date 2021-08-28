package javacodingchallenge;

import java.lang.reflect.Array;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.util.ArrayUtil;

public class IsArraySorted {
	

	public static void main(String[] args) {
		int[] arr = {5,3,7,8};
		IsArraySorted objArraySort = new IsArraySorted();
		System.out.println(objArraySort.ArraySort(arr));
		
		System.out.println("Using inbuilt method");
		System.out.println(ArrayUtils.isSorted(arr));
		
	}
	
	boolean ArraySort(int[] arraySort) {
		
		int counter = 0;
		for(int i=0;i<Array.getLength(arraySort);i++) {
			if(i+1!=Array.getLength(arraySort)) {
				if(arraySort[i]>arraySort[i+1]) counter=counter+1;
			}
		}
		System.out.println(counter);
		if(counter==0) return true;
		return false;
	}


}

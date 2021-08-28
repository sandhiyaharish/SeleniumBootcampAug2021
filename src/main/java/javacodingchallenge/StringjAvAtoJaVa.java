package javacodingchallenge;

public class StringjAvAtoJaVa {
	
	public static void main(String args[]) {
		
		String input = "jAvA";
		char inputArray[] = input.toCharArray();
		for (char c : inputArray) {
			if(Character.isUpperCase(c)) {
				System.out.print(Character.toLowerCase(c));
			}else if(Character.isLowerCase(c)){
				System.out.print(Character.toUpperCase(c));
			}
		}
		
		System.out.println("");
		for(int i =0;i<input.length();i++) {
			char ch = input.charAt(i);
			if(Character.isUpperCase(ch)) {
				System.out.print(Character.toLowerCase(ch));
			}else if(Character.isLowerCase(ch)) {
				System.out.print(Character.toUpperCase(ch));
			}
		}
		
		System.out.println("");
		
		StringBuffer sbuffer = new StringBuffer(input);
		for(int j=0;j<input.length();j++) {
			if(Character.isUpperCase(input.charAt(j))) {
				sbuffer.setCharAt(j, Character.toLowerCase(input.charAt(j)));
			}else if(Character.isLowerCase(input.charAt(j))) {
				sbuffer.setCharAt(j, Character.toUpperCase(input.charAt(j)));
			}
		}
		
		System.out.println(sbuffer);
	}

}

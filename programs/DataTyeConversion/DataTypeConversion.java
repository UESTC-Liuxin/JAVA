import java.util.Scanner;

public class DataTypeConversion{
	public static void main(String[] args){
		
		short a=(short)-65538;
		System.out.println(a);
		byte b= (byte)150;//150的补码0000 0000 1001 0110  舍去高位1001 0110得到其原码1110 1010 
		System.out.println(b);
		short c=124;
		System.out.println(c);
		int e=4;
		short d=(short)e;
		System.out.println(d);
		
		java.util.Scanner s=new java.util.Scanner(System.in);
		long inputNum=s.nextLong();//接受数字
		
	}	
}
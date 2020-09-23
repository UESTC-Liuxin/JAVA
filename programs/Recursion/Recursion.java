public class Recursion{
	public static void main(String[] args){
		System.out.println(recur(5));
	}
	public static int recur(int n){
		if(n==1)
			return 1;
		return n*recur(n-1); //返回当前值
	}
}
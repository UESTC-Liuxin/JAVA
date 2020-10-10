public class StaticCode{
	static int i=100;
	static{
		System.out.println(i+"execute to static code");//可以访问实例变量
	}	
	
	{
		System.out.println("execute to incetance code");//
	}
	
	public StaticCode(){
		System.out.println(i+"execute to Construct code");//
	}
	public static void main(String[] args){
		StaticCode s1=new StaticCode();
		StaticCode s2=new StaticCode();
		
	}
}

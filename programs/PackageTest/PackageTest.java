public class PackageTest{
	public static void main(String[] args){
		Person person = new Person();
		System.out.println(person.age);
	 	System.out.println(person.flyAbility);
		
	}
}



class Person{
	public static boolean flyAbility;
	public int age;
	
	public void setAge(int age){
		if(age<100&&age>0){
			System.out.println("Error!");
			return;
		}
		this.age=age;
	}
	
	public int getAge(){
		return age;  //最好是这样写
	}
	public static boolean getFlyAbility(){
		
	}
}

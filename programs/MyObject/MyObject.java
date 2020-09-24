class T{
	A a;	
	public static void main(String[] args){
		D d=new D();
		C c=new C();
		B b=new B();
		A a=new A();
		T t=new T();  //T已经实例化了
		
		c.d =d;
		b.c =c;
		a.b =b;
		t.a =a;
		
		//利用t访问i，哪怕是在本类，也只能通过实例变量来访问
		System.out.println(t.a.b.c.d.i);
	}
}

class A{
	B b;
}

class B{
	C c;
}

class C{
	D d;
}

class D{
	int i;
}
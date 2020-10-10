public class Inherit{
	public static void main(String[] args){
		CreditAccount c = new CreditAccount(100);
		c.printInfo();
	}
	
}

/**
*/

class Account{
	private String accID;
	private String name;
	
	public Account(String accID,String name){
		setAccID(accID);
		setName(name);
	}
	public Account(){
		
	}
	
	public void setAccID(String accID){
		this.accID=accID;
	}
	
	public String getAccID(){
		return this.accID;
	}
	
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return this.name;
	}
}
/**

*/
class CreditAccount extends Account{//继承于Account
	private int creditScorce;
	
	public CreditAccount(int creditScorce){
		setCreditScore(creditScorce);
	}
	public CreditAccount(){
		
	}
	
	public void setCreditScore(int creditScorce){
		this.creditScorce=creditScorce;
	}
	public int getCreditScorce(){
		return this.creditScorce;
	}
	
	public void printInfo(){
		System.out.println("accID:"+getAccID()+";\ncreditScorce:"+getCreditScorce());
	}
	 
}


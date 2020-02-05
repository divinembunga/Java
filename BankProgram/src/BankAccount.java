import java.util.ArrayList;

public class BankAccount {
	public long accountNumber;
	public double balance;
	ArrayList<Transaction>accountTransactions;
	
	public BankAccount(long accountNumber){
		this.accountNumber=accountNumber;
		this.balance=balance;
		this.accountTransactions=new ArrayList<Transaction>();
	}
	
	public ArrayList<Transaction> getAcountTransactions() {
		return accountTransactions;
	}

	public void setAcountTransactions(ArrayList<Transaction> acountTransactions) {
		this.accountTransactions = acountTransactions;
	}


	
	
	public double returnBalance(){
		return balance;
	}
	
	public void creditBalance(double balance){
		this.balance += balance;
	}
	
	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	private double getBalance() {
		return balance;
	}

	private void setBalance(double balance) {
		this.balance = balance;
	}

	public void debitBalance(double balance){
		this.balance -= balance;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

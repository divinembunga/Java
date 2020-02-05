import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Branch {
	
	String address;
	String manager;
	
	public static final int SORT_CODE=991222;
	public ArrayList<Customer>customerList= new ArrayList<Customer>();
	
	public Branch(){
		customerList=new ArrayList<Customer>();
	}
	
	public Customer getCustomer(long accountNumber){
		Customer customer =new Customer();
		boolean customerFound=false;
		int counter=0;
		while(!customerFound && counter< customerList.size()){
			 customer= customerList.get(counter);
			ArrayList<BankAccount> customerAccounts=customer.getCustomerAccounts();
			boolean accountFound = false;
			int accountCounter = 0;
			while(!accountFound && accountCounter < customerAccounts.size()){
				if(customerAccounts.get(accountCounter).getAccountNumber()  ==accountNumber){
					accountFound=true;
					customerFound=true;
				}
			}
		}
		if(!customerFound){
			customer=null;
		}
		return customer;
	}
	
	public Customer createCustomer(String name, String address,String email, String dateOfBirth){
		DateFormat formatter = new SimpleDateFormat("dd/mm/yyy");
		Date birthDate = null;
		try{
			birthDate = formatter.parse(dateOfBirth);
		}catch(ParseException e){
			e.printStackTrace();
		}
		Customer customer = new Customer(name,address,email,birthDate);
		customerList.add(customer);
		return customer;
	}
	
	public long createAccount(String accountType,Customer customer){
		long accountNumber=0;
		if(accountType.equals(BankConstants.CURRENT_ACCOUNT)|| accountType.equals(BankConstants.SAVINGS_ACCOUNT)){
		  HeadOffice headOffice =new HeadOffice();
	      accountNumber=headOffice.createAccountNumber();
		  if(accountType.equals(BankConstants.CURRENT_ACCOUNT)){
			CurrentAccount currentAccount = new CurrentAccount(accountNumber);
			customer.getCustomerAccounts().add(currentAccount);
		  }else if(accountType.equals(BankConstants.SAVINGS_ACCOUNT)){
			 SavingsAccount savingsAccount = new SavingsAccount(accountNumber);
			 customer.getCustomerAccounts().add(savingsAccount);
		}
	  }
        return accountNumber;
		
   }
	
	public BankAccount getAccount(long accountNumber, Customer customer){
		BankAccount bankAccount =null;
		if(customer !=null && accountNumber>0 ){
			boolean accountFound=false;
			int counter=0;
			while(!accountFound && counter<customer.getCustomerAccounts().size()){
				//since it inherits we can use it
				 bankAccount=customer.getCustomerAccounts().get(counter);
				if(bankAccount.getAccountNumber()==accountNumber){
					accountFound=true;
				}
				counter++;
			}
			if(accountFound){
				bankAccount=null;
			}	
		}
		return bankAccount;
	}
	
	
	public void creditAccount(BankAccount bankAccount, double amount){
		if(bankAccount !=null && amount>0){
			bankAccount.creditBalance(amount);
			
		}
	}
	
	public void debitAccount(BankAccount bankAccount, double amount){
		if(bankAccount !=null && amount>0){
			bankAccount.debitBalance(amount);
			
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

import java.util.ArrayList;
import java.util.Date;

//copy and paste bank customer here
//remove accNo, sortCode,balance,transactions,first constructor after the () one 
public class Customer {
	private String address;
	private String name;
	private String email;
	private Date date;
	private ArrayList<BankAccount>customerAccounts;
	
	public Customer(){
		
	}
	
	
	public Customer(String address, String name, String email, Date date) {
		this.address = address;
		this.name = name;
		this.email = email;
		this.date = date;
		this.customerAccounts =new ArrayList<BankAccount>();
	}



	public ArrayList<BankAccount> getCustomerAccounts() {
		return customerAccounts;
	}


	public void setCustomerAccounts(ArrayList<BankAccount> customerAccounts) {
		this.customerAccounts = customerAccounts;
	}


	@Override
	public String toString() {
		return "Customer [address=" + address + ", name=" + name + ", email=" + email + ", date=" + date + "]";
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

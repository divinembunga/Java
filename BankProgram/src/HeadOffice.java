import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class HeadOffice {
	String bankName;
	String address;
	
	private long accountNumber= 10000000;
	private int sortCode=991100;
	
	
	public HeadOffice(){
		writeAccountNumberToFile( accountNumber);
		writeSortCodeToFile( sortCode);
		
	}
	
	private void writeAccountNumberToFile(long accountNumber){
		try{
			FileWriter writer=new FileWriter("account.txt");
			BufferedWriter buffer= new BufferedWriter(writer);
			buffer.write(String.valueOf(accountNumber));
			buffer.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	private void writeSortCodeToFile(int sortCode){
		try{
			FileWriter writer=new FileWriter("sortcode.txt");
			BufferedWriter buffer= new BufferedWriter(writer);
			buffer.write(String.valueOf(sortCode));
			buffer.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public long createAccountNumber(){
		long accountNumber=-1;
		try{
			FileReader fileReader= new FileReader("account.txt");
			BufferedReader bufferedReader= new BufferedReader(fileReader);
			boolean endOfFile=false;
			while(!endOfFile){
				String accountNumberString=bufferedReader.readLine();
				if 
				
			}
			
		}catch{
			
		}
		if(accountNumber>0){
			accountNumber++;
			writeAccountNumberToFile(accountNumber);
		}
		return accountNumber;
		//Put file Reader in here but for accNo etc..String accNumerString=buffredReader.readLine 
	}
	
	public int createSortCode(){
		return sortCode++;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

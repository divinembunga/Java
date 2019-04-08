
public class Harness {
	private String make;
	private int modelNumber;
	public int timesUsed;
	public String instructorName;
	public boolean isLoaned;
	public String borrowerName;
	
	public static final int MAX_USE_OF_HARNESS=20;

	public Harness(String make, int modelNumber, int timesUsed, String instructorName, boolean isLoaned,
			String borrowerName) {
		
		this.make = make;
		this.modelNumber = modelNumber;
		if(timesUsed <0 ){
			throw new IllegalArgumentException("The number of times the harness was used cannot be below zero");
		}
		this.timesUsed=timesUsed;
		this.instructorName=instructorName;
		this.isLoaned=isLoaned;
		if(isLoaned){
			this.borrowerName=borrowerName;	
		}else if(!isLoaned){
			this.borrowerName=null;
		}
	}
		


	public Harness(String make, int modelNumber, String instructorName) {
		
		this.make = make;
		this.modelNumber = modelNumber;
		this.instructorName = instructorName;
		this.timesUsed=0;
		this.isLoaned=false;
		this.borrowerName=null;
	}
	
	
	public void checkHarness(String instructorName){
		if(instructorName != null){
			if(this.isLoaned==false){
				this.instructorName=instructorName;
				this.timesUsed=0;
			}
		}else{
			throw new NullPointerException();
		}
		
	}
	
	
	public boolean isHarnessOnLoan(){
		return this.isLoaned;
	}
	
	
	public boolean canHarnessBeLoaned(){
		boolean canBeLoaned=false;
		if(!isHarnessOnLoan()){
			if(this.timesUsed <= MAX_USE_OF_HARNESS ){
				canBeLoaned=true;
			}
		}
		return canBeLoaned;
	}
	
	
	public void loanHarness(String clubMemberName){
		if(clubMemberName != null){
			if(canHarnessBeLoaned()){
				this.borrowerName=clubMemberName;
				this.isLoaned=true;
				this.timesUsed++;
			}
			
		}else{
			throw new NullPointerException();
		}
	}
	
	
	public void returnHarness(){
		if(isHarnessOnLoan()){
			this.isLoaned=false;
			this.borrowerName=null;
		}
	}
	

	@Override
	public String toString() {
        String harnessInformation="";
        harnessInformation+="Make: "+this.make+"\nModel Number: "+this.modelNumber;
		if(this.isHarnessOnLoan()){
			harnessInformation+= "\nThis harness is on loan to :"+this.borrowerName;	
		}else if(!this.isHarnessOnLoan()){
			harnessInformation+= "\nThis harness is not loan";
		}
		harnessInformation += "\nThis harness has been used: "+this.timesUsed+" times.";
		harnessInformation +=  "\nThis harness was last safety checked by the instructor: "+this.instructorName;
		return harnessInformation;
	}


	public String getMake() {
		return make;
	}


	public void setMake(String make) {
		this.make = make;
	}


	public int getModelNumber() {
		return modelNumber;
	}


	public void setModelNumber(int modelNumber) {
		this.modelNumber = modelNumber;
	}


	public int getTimesUsed() {
		return timesUsed;
	}


	public void setTimesUsed(int timesUsed) {
		this.timesUsed = timesUsed;
	}


	public String getInstructorName() {
		return instructorName;
	}


	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}


	public boolean isLoaned() {
		return isLoaned;
	}


	public void setLoaned(boolean isLoaned) {
		this.isLoaned = isLoaned;
	}


	public String getBorrowerName() {
		return borrowerName;
	}


	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

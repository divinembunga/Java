import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HarnessRecords  {
	    
   protected ArrayList<Harness> harnessList;
   
   HarnessRecords(){
	   harnessList=new ArrayList<Harness>();
	   
   }
   
    public HarnessRecords(FileReader fileReader)throws IOException{
    	try{
	        if(harnessList == null){
	        	harnessList=new ArrayList<Harness>();
	        }
	        BufferedReader bufferedReader= new BufferedReader(fileReader);
	        Harness harness= null;
	        boolean endOfFile=false;
	        while(!endOfFile){
			   String harnessData=bufferedReader.readLine();
			   if(harnessData !=null){
				   String[] harnessDetails=harnessData.split(",");
				   String make=harnessDetails[0];
				   int modelNumber=Integer.parseInt(harnessDetails[1]);
				   String instructorName=harnessDetails[2];
				   if(harnessDetails.length > 3){
					   int timesUsed=Integer.parseInt(harnessDetails[3]);
					   //being able to convert boolean into a string 
					   boolean isLoan=harnessDetails[4].equals("false")?false:true;
					   String borrowerName=harnessDetails[5];
					   harness=new Harness(make,modelNumber,timesUsed,instructorName,
							   isLoan,borrowerName);
					   this.harnessList.add(harness);
					  
				   }else{
					   harness = new Harness(make,modelNumber,instructorName);
					   this.harnessList.add(harness);
					   
					   
				   }
				  
			     
			   }else{
				   endOfFile=true;
			   }
	        }
	        bufferedReader.close();
			fileReader.close();
			  
		   }catch(FileNotFoundException e){
			     e.printStackTrace();
		    } catch(IOException e){
			     e.printStackTrace();
		     }

	     

    }		   
	   
   
    public boolean isEmpty(){
    	boolean isEmpty=false;
    	if(this.harnessList==null){
    		isEmpty=true;
    	}
    	return isEmpty;
    }
    
    
    public Harness addHarness(Harness harness){
       this.harnessList.add(harness);
       return harness;
    }
    
    
    public Harness findHarness(String make,int modelNumber){
    	boolean harnessFound=false;
    	int counter=0;
    	Harness harnesses=null;
    	while(!harnessFound &&counter<harnessList.size()){
    		harnesses=harnessList.get(counter);
    		if((harnessList.get(counter).getMake()==make) && (harnessList.get(counter).getModelNumber()==modelNumber)){
    			harnessFound=true;
    		}
    	}
    	if(!harnessFound){
    		harnesses=null;
    	}
    	return harnesses;
    }
    
	
    public Harness checkHarness(String instructorName,String make,int modelNumber){
    	Harness harness=findHarness(make,modelNumber);
    	if(harness!=null){
    		if(!harness.isHarnessOnLoan()){
    			harness.checkHarness(instructorName);
    		}else{
    			harness=null;
    		}
    	}else{
    		harness=null;
    	}
    	return harness;
    }

    
    public Harness loanHarness(String clubMember){
    	Harness harness=null;
    	boolean harnessFound=false;
    	int counter=0;
    	while(!harnessFound && counter<harnessList.size()){
    		harness=harnessList.get(counter);
    		if(harness.canHarnessBeLoaned()){
    			harness.loanHarness(clubMember);
    		}else{
    			harness=null;
    		}
    	}
    	if(!harnessFound){
    		harness=null;
    	}
    	return harness;
    	
    }
    
    public Harness returnHarness(String make, int modelNumber){
    	Harness harness=findHarness(make,modelNumber);
    	if(harness != null){
    		harness.returnHarness();
    	}else{
    		harness=null;
    	}
    	return harness;	
    }
    
    
    public Harness removeHarness(String make, int modelNumber){
    	boolean harnessFound=false;
    	int counter=0;
    	Harness harnesses=null;
    	while(!harnessFound &&counter<harnessList.size()){
    		harnesses=harnessList.get(counter);
    		if((harnessList.get(counter).getMake()==make) && 
    				(harnessList.get(counter).getModelNumber()==modelNumber)){
    			harnessList.remove(counter);
    			harnessFound=true;
    		}
    	}
    	if(!harnessFound){
    		harnesses=null;
    	}
    	return harnesses;
    	
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
	}

}

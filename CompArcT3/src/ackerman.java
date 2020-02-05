//@author: Divine Mbunga
public class ackerman {
	/*Global Variables*/
	public static int procedureCalls = 0;
	public static int windowDepth = 0;
	public static int maxDepth = 0;
	public static int overFlow = 0;
	public static int underFlow = 0;
	public static int registerSet = 8;
	public static int filledRegisterWindow = 2;
	 //need at least two valid register windows
	
	static int ackermann(int x, int y){
		procedureCalls += 1;
		windowDepth +=1;
		overFlowHandler(); //when function called
		int result = 0;
		
		if(x==0){
			windowDepth -= 1;
			underFlowHandler();//before function return
			return y+1;
		}else if(y==0){
			result = ackermann(x-1,1);
			windowDepth -= 1;
			underFlowHandler();
			return result;
		}else{
			result = ackermann(x-1,ackermann(x,y-1));
			windowDepth -= 1;
			underFlowHandler();
			return result;
			
		}
	}
	
	static void overFlowHandler(){
		if(windowDepth > maxDepth){
			maxDepth = windowDepth;
		}
		if(filledRegisterWindow < registerSet){
			filledRegisterWindow += 1; //as function is being called
		}else{
			overFlow += 1;
		}
		
	}
	
	static void underFlowHandler(){
		if(filledRegisterWindow > 2){
			filledRegisterWindow -= 1; //as function in returning
		}else{
			underFlow += 1;
		}
	}
    
	public static void main (String[] args){
		//changed the global variable registerSets to 6, 8 and 16 manually
		System.out.print("Ackermann(3,6) with "+registerSet+" register sets"+"\n");
		double startTime = System.nanoTime();
		int result = ackermann(3,6);
		double endTime = System.nanoTime();
		double duration = (endTime - startTime)/1000000;
		System.out.print("Procedure Calls: "+procedureCalls+"\n");
		System.out.print("Max Depth: "+maxDepth+"\n");
		System.out.print("OverFlows: "+overFlow+"\n");
		System.out.print("UnderFlow: "+underFlow+"\n");
		System.out.print("Time to execute = "+duration+"ms");
		
	}
	

}

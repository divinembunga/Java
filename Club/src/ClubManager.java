import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class ClubManager {
	public static final int NAME_INDEX=0;
	public static final int ADDRESSS_INDEX=1;
	public static final int YEAR_OF_REG_INDEX=2;
	public static final int EMAIL_INDEX=3;
	public static int groupID=101;
	
	public void printMembers(ArrayList<ClubMember>clubMembers){
		String clubMemberNames="Names of members are: ";
		for(int i=0;i<clubMembers.size();i++){
		    clubMemberNames+=clubMembers.get(i).getName()+",";
		}
		System.out.println(""+clubMemberNames.substring(0,clubMemberNames.lastIndexOf(".")));
	}
	
	public ClubMember getMember(ArrayList<ClubMember>clubMembers,String name){
	    boolean memberFound= false;
	    int counter=0;
	    ClubMember member=null;
	    while(!memberFound && counter<clubMembers.size()){
	    	member= clubMembers.get(counter);
	    	if(name.equals(member.getName())){
	    		memberFound=true;
	    	}else{
	    		counter++;
	    		member=null;
	    	}
	    
	    }
	    return member;
	}
	
	
	public void updateMember(ClubMember member, String address,String email){
		if(member!=null){
			if(address !=null){
				member.setAddress(address);
			}
			if(email!=null){
				member.setEmail(email);
			}
		}
	}
	
	
	public Group CreateGroup(int groupId, String groupDescription){
		Group clubGroup = new Group(groupId,groupDescription,new ArrayList<ClubMember>());
		return clubGroup;
	}
	
	public void addMemberToGroup(Group group, ClubMember member){
		ArrayList<ClubMember>groupMembers=group.getGroupMembers();
		boolean memberFound=false;
		int counter=0;
		while(!memberFound && counter<groupMembers.size()){
			if(groupMembers.get(counter).getName().equals(member.getName())){
				memberFound=true;
			}else{
				counter++;
			}
		}
		if(!memberFound){
			groupMembers.add(member);
		}
	}
	
	public void printGroupMembers(Group group){
		
		ArrayList<ClubMember>clubMember=group.getGroupMembers();
		if(clubMember!=null && clubMember.size()>0){
			System.out.println("Members of group "+group.getGroupDescription()+" are:");
			for(ClubMember member: clubMember){
				System.out.println(member.getName());
			}
		}
	}
	
	
	public void printGroupNamesAndNumbersOfMembers(ArrayList<Group>groupList){
		if(groupList != null &&groupList.size()>0){
			for(Group group: groupList){
				System.out.println("Group Description :"+group.getGroupDescription()+"\n"+"Number of Members:"+group.getGroupMembers().size());
			}
		}
		
		
	}
	
			
		
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
    ArrayList<ClubMember>clubMembers=new  ArrayList<ClubMember>();
    ArrayList<Group>groupList=new ArrayList<Group>();
    try{
		FileReader fileReader=new FileReader("Members");
		BufferedReader bufferedReader=new BufferedReader(fileReader);
		boolean endOfFile=false;
		while(!endOfFile){
			String clubData=bufferedReader.readLine();
			if(clubData!=null){
				String[] clubInformation=clubData.split(",");
				String name=clubInformation[NAME_INDEX];
				String address=clubInformation[ADDRESSS_INDEX];
				int yearOfRegistration=Integer.parseInt(clubInformation[YEAR_OF_REG_INDEX]);
				String email=clubInformation[EMAIL_INDEX];
				ClubMember clubMember=new ClubMember(name,address,yearOfRegistration,email);
				//save it in a collection
				clubMembers.add(clubMember);
			}else{
				endOfFile=true;
			}
		}
		bufferedReader.close();
		fileReader.close();
	}catch(FileNotFoundException e){
		e.printStackTrace();
	}
	catch(IOException e){
		e.printStackTrace();
	}
    
    boolean exit=false;
    ClubManager manager= new ClubManager();
    Scanner userInput= new Scanner(System.in);
    while(!exit){
    	System.out.println("Please select 1 to creat a group, 2 to view club members,3 to view details of a member");
    	//Create a group
    	String groupDescription="test";
    	Group group=manager.CreateGroup(groupID++,groupDescription);
    	groupList.add(group);
    	
    	//user has selected view club member by name
    	manager.printMembers(clubMembers);
    	
    	//user has chosen to change user address and or email
    	String membersName="Jack";
    	String address="Joly Theatre TCD";
    	String email= "Jack@tcd.ie";
    	ClubMember member=manager.getMember(clubMembers,membersName);
    	manager.updateMember(member,address,email);
    }
	}

}

/**
 * @author Divine Mbunga
 */

import java.net.DatagramSocket;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import tcdIO.*;

/**
 *
 * Subscriber class
 * 
 * An instance accepts user input 
 *
 */
public class Subscriber extends Node {
	static final int DEFAULT_SRC_PORT = 50002;
	static final int MAX_SUBSCRIPTION=4;
	static int port= DEFAULT_SRC_PORT;
	
	Terminal terminal;
	ArrayList<Integer>connectedPublishers= new ArrayList<Integer>();

	
	/**
	 * 
	 * Constructor
	 * Attempts to create socket at given port and create an InetSocketAddress for the destinations
	 * 
	 */
	Subscriber(Terminal terminal,int port) {
		try {
			this.terminal= terminal;
			socket= new DatagramSocket(port);
			listener.go();
		}
		catch(java.lang.Exception e) {e.printStackTrace();}
	}
	
	/**
	 * 
	 * Assume that incoming packets contain a String and print the string.
	 * 
	 */
	public synchronized void onReceipt(DatagramPacket packet) {
		try{
			StringContent content = new StringContent(packet);
			byte[]headerData = getHeaderData(packet);
			int publisherActive = (int)headerData[1];
			if(publisherActive > connectedPublishers.size()){
				connectedPublishers.add(0);
				terminal.print("New ");
			}
			StringContent response;
			byte[]header = new byte[PacketContent.HEADERLENGTH];
			if(checkSeqNumber(publisherActive-1,(int)headerData[0])){
				terminal.println("Publisher "+publisherActive+":"+content.toString());
				response =(new StringContent("OK", header));
			}else{
				response = (new StringContent("NOK", header));
			}
			int returnSeqNumber = (int)connectedPublishers.get(publisherActive-1);
			/*If the sequence number received is not correct, it will not change and we
			 * will ask for the same one again. */
			header[0]=(byte)returnSeqNumber;
			header[2]=(byte)publisherActive;
			
			DatagramPacket message = response.toDatagramPacket();
		    message.setSocketAddress(packet.getSocketAddress());
		    socket.send(message);

		}catch(Exception e){e.printStackTrace();}
		
	}
	
	/**
	 * 
	 * Return true if the seqno is the same as seqno of the connected client
	 * 
	 */
	private boolean checkSeqNumber(int i, int j){
		int subSeqNumber = connectedPublishers.get(i);
		if(subSeqNumber == j){
			iterate(connectedPublishers,i);
			return true;
		}else{
			return false;
		}
	
   }

    private void iterate(ArrayList<Integer>clientList,int index){
    	int seqNumber = clientList.get(index);
    	if(seqNumber==0){
    		seqNumber = 1;
    	}else{
    		seqNumber=0;
    	}
    	clientList.set(index, seqNumber);
    	return;
    	
   }
	/**
	 * Sender Method
	 * 
	 */
	public synchronized void start() throws Exception {
		terminal.println("Waiting for contact...");
		this.wait();
		
	}


   /**
    * 
    * Checks if the port is available to be used
    * 
    */
   
    public static boolean available(int port){
	   DatagramSocket ds=null;
	   ServerSocket sskt=null;
	   try{
		   sskt = new ServerSocket(port);
		   sskt.setReuseAddress(true);
		   ds= new DatagramSocket(port);
		   ds.setReuseAddress(true);
		   return true;
	   }catch(Exception e){
	   }finally{
		   if(ds !=null){
			   ds.close();
		   }else if(sskt !=null){
			   try{
				   sskt.close();
			   }catch (IOException e){
				   
			   }
		   }
	   }
	   return false;
    }
    
    /**
     * 
     *Extracts the payload, i.e message from the header
     *
     */
   
     private byte[] getHeaderData(DatagramPacket packet){
       byte[]payload;
  	   byte[]buffer;
  	   
  	   buffer=packet.getData();
  	   payload= new byte[PacketContent.HEADERLENGTH];
  	   System.arraycopy(buffer, 0, payload, 0, PacketContent.HEADERLENGTH);
  	   
  	   return payload;
	  
    }

     
	/**
	 * Test method
	 * 
	 * Sends a packet to a given address
	 */
	public static void main(String[] args) {
		try {	
			Terminal terminal = new Terminal("Subscriber");
			(new Subscriber(terminal,DEFAULT_SRC_PORT)).start();
			terminal.println("Program completed");
		} catch(java.lang.Exception e) {e.printStackTrace();}
	}
}



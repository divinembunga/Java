/**
 * @author Divine Mbunga
 */
import java.net.DatagramSocket;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.Queue;

	import tcdIO.*;

	/**
	 *
	 * Publisher class
	 * 
	 * An instance accepts user input 
	 *
	 */
	public class Publisher extends Node {
		static final int DEFAULT_SRC_PORT = 50000;
		static final int DEFAULT_DST_PORT = 50001;
		static final String DEFAULT_DST_NODE = "localhost";	
		private static final int MAX_ATTEMPTS = 5;
		private static final int MAX_NUMBER_PORT = 65000;
		private static final int MIN_NUMBER_PORT = 1000;
		
		static final int DEFAULT_BROKER_PORT = 50001;
		static final int DEFAULT_SERVER_PORT = 50002;
		
		static int port= DEFAULT_SRC_PORT;
		
		Terminal terminal;
		InetSocketAddress dstAddress;
		int sequenceNumber =0;
		private boolean ackReceived ;

		
		/**
		 * Constructor
		 * 	 
		 * Attempts to create socket at given port and create an InetSocketAddress for the destinations
		 */
		Publisher(Terminal terminal, String dstHost, int dstPort, int srcPort) {
			try {
				this.terminal= terminal;
				dstAddress= new InetSocketAddress(dstHost, dstPort);
				socket= new DatagramSocket(srcPort);
				listener.go();
			}
			catch(java.lang.Exception e) {e.printStackTrace();}
		}
		
		/**
		 * Assume that incoming packets contain a String and print the string.
		 */
		public synchronized void onReceipt(DatagramPacket packet) {
			byte[]headerData = getHeaderData(packet);
			StringContent content= new StringContent(packet);
			int seqNumberReceived = headerData[0];
			if(seqNumberReceived == sequenceNumber){
				ackReceived = true;
				this.notify();
				terminal.println(content.toString());
			}
			
		}

		
		/**
		 * Sender Method
		 * 
		 */
		public synchronized void start() throws Exception {
			
			DatagramPacket packet = null;
			
			byte[]payload = null;
			byte[]header = null;
			byte[]buffer= null;
			
			header=new byte[PacketContent.HEADERLENGTH];
			payload=(terminal.readString("Message: ")).getBytes();
			
			header[0]=(byte)sequenceNumber;
			iterateSequenceNumber();
			buffer=new byte[header.length+payload.length];
			
			System.arraycopy(header, 0, buffer, 0, header.length);
			System.arraycopy(payload, 0, buffer, header.length, payload.length);
			
			terminal.println("Sending Packet ...");
			packet = new DatagramPacket(buffer,buffer.length,dstAddress);
			ackReceived =false;
			int countSent = 0;
			
			while(!ackReceived){
				if(countSent <MAX_ATTEMPTS){
					socket.send(packet);
					terminal.println("Packet Sent");
					this.wait(5000);
					countSent++;
				}else{
					terminal.println("Connection Error!: Maxium attempts breeched");
					break;
				}
				
			}
					
			
			
		}


	   public void iterateSequenceNumber(){
		   if(this.sequenceNumber ==0){
			   this.sequenceNumber=1;
		   }else{
			   this.sequenceNumber=0;
		   }
		   return;
	   }
	   
	   /**
	    * 
	    *Checks whether the port is available or not
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
	    * Extracts the payload, i.e the message from the header of the packet
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
	   
	 
		public static void main(String[] args) {
			try {	
				while(!available(port)){
					port++;
					
				}
				Terminal terminal= new Terminal("Publisher");		
				(new Publisher(terminal, DEFAULT_DST_NODE,DEFAULT_DST_PORT,port)).start();
				terminal.println("Program completed");
			} catch(java.lang.Exception e) {e.printStackTrace();}
		}
	}



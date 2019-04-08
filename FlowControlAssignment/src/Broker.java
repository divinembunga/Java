/**
 * @author Divine Mbunga
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collection;


import tcdIO.Terminal;

public class Broker extends Node {
	static final int DEFAULT_PORT = 50001;
	static final String DEFAULT_DST_NODE = "localhost";
	static final int DEFAULT_DST_PORT = 50002;
	
	Terminal terminal;
	
	InetSocketAddress subscriberAddress = new InetSocketAddress("localhost",DEFAULT_DST_PORT);
	InetSocketAddress publisherAddress = new InetSocketAddress("localhost",50000);
	ArrayList<InetSocketAddress>activePublishers= new ArrayList<InetSocketAddress>();
	
	/**
	 *
	 * Broker class
	 * 
	 * A warehouse that forwards messages from the publisher to subscriber
	 *
	 */
	Broker(Terminal terminal, int port) {
		try {
			this.terminal= terminal;
			socket= new DatagramSocket(port);
			listener.go();
		}
		catch(java.lang.Exception e) {e.printStackTrace();}
	}
	

	/**
	 * Assume that incoming packets contain a String and print the string.
	 */
	public synchronized void onReceipt(DatagramPacket packet) {
		try {
			DatagramPacket forward;
			byte[] headerData = getHeaderData(packet);
			terminal.println("Message Received");
			InetSocketAddress srcAddress =(InetSocketAddress)packet.getSocketAddress();
			if(!srcAddress.equals(subscriberAddress)){
				int adrIndex=searchConnection(srcAddress);
				headerData[1] = (byte)adrIndex;
			}
			StringContent content = new StringContent(packet);
			String packetMessage = content.toString();
			terminal.println(packetMessage);
			content.setHeader(headerData);
			forward = content.toDatagramPacket();
			if(headerData[2] == 0){
				forward.setSocketAddress(subscriberAddress);
			}else if (headerData[2] != 0){
				int index =headerData[2];
				forward.setSocketAddress(activePublishers.get(index-1));
				
			}
			socket.send(forward); //else loop to next subscriber
			terminal.println("Forwarding Message");
		
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	
	public synchronized void start() throws Exception {
		terminal.println("Waiting for contacts...");
		this.wait();
	}
	
	/**
	 * 
	 * Finds the connection to the publisher
	 * 
	 */
	private int searchConnection(InetSocketAddress srcAddress){
		int index = activePublishers.indexOf(srcAddress);
		if(index != -1){
			return index+1;
		}else{
			activePublishers.add(srcAddress);
			return activePublishers.size();
		}
	}
	
	/**
	 * 
	 *Extracts the payload i.e message from the header of the packet
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
	 
	/*
	 * Creates the Broker Terminal
	 */
	public static void main(String[] args) {
		try {					
			Terminal terminal= new Terminal("Broker");
			(new Broker(terminal, DEFAULT_PORT)).start();
		} catch(java.lang.Exception e) {e.printStackTrace();}
	}
}


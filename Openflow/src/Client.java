/**
 * Client Class
 * 
 * @author Divine Mbunga
 */

import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.net.SocketException;

import java.io.IOException;

import java.util.HashMap;

import tcdIO.*;

public class Client extends Node{
	static final String DEFAULT_DST_NODE = "localhost";	
	Terminal terminal;
	InetSocketAddress startAdr,dstAdr;
	int srcPort;
	int []dstPort;
	boolean finished=false;
	String dstHost;

	Client (Terminal terminal, String dstHost, int dstPort1, int dstPort2, int dstPort3, int srcPort, int startPort)
	{
		try {
			this.terminal=terminal;
			this.dstHost=dstHost;
			this.dstPort = new int [3];
			dstPort [0]= dstPort1;
			dstPort [1]= dstPort2;
			dstPort [2]= dstPort3;
			this.srcPort=srcPort;
			this.socket = new DatagramSocket(this.srcPort);
			this.startAdr=new InetSocketAddress (this.dstHost,startPort);
			listener.go();
		} catch (SocketException e) {e.printStackTrace();}
	}
	
	/**
	 * Incoming packets
	 */
	@Override
	public void onReceipt(DatagramPacket packet) throws Exception {
		terminal.println("Packet Received");
	}

	public void start () throws Exception
	{
		DatagramPacket packet= null;
		String message;
		int targetUser;
		while(!finished)
		{
			message=terminal.readString("String to send: "); 
			if (message.isEmpty()||message.equalsIgnoreCase("Finished"))
			{
				this.finished=true;
				terminal.println("Complete");
				socket.close();
			}
			else{
				terminal.println(message);
				/*
				 * Each user has 3 end-points to communicate with, assuming all of them are available
				 */
				targetUser=Integer.parseInt(terminal.readString("User to send to: "));
				if (targetUser<1||targetUser>3)
				{
					targetUser=2;
				}
				terminal.println("Sending packet to User: "+targetUser+"...");
				packet=new StringContent(message).toDatagramPacket(srcPort, dstPort[targetUser-1], delimiter);
				packet.setSocketAddress(startAdr);
				socket.send(packet);
			}			
		}
	}

	/*
	 * start each client separately, other ways were too complicated
	 * 
	 */
	public static void main(String[] args) {
		try {
			Terminal terminal0= new Terminal("Client 0");		
			Client client0 = new Client(terminal0, DEFAULT_DST_NODE, 40001,40002,40003,40000,40004);
			client0.start();
			Terminal terminal1=new Terminal("Client 1");
			Client client1 = new Client(terminal1, DEFAULT_DST_NODE, 40000,40002,40003,40001,40005);
			client1.start();
			Terminal terminal2=new Terminal("Client 2");
			Client client2 = new Client(terminal2, DEFAULT_DST_NODE, 40000,40001,40003,40002,40006);
			client2.start();
			Terminal terminal3=new Terminal("Client 3");
			Client client3 = new Client(terminal3, DEFAULT_DST_NODE, 40000,40001,40002,40003,40007);
			client3.start();				
		} catch(java.lang.Exception e) {e.printStackTrace();}
	}
}

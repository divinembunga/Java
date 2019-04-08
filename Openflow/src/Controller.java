/**
 * Controller Class
 * 
 * @author Divine Mbunga
 */
import java.util.ArrayList;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

import tcdIO.*;
public class Controller extends Node{
	static final String DEFAULT_DST_NODE = "localhost";
	String destH;
	Terminal terminal;
	Key[] keys;
	
	Controller(Terminal terminal, String destH){
		try{
			this.terminal = terminal;
			this.destH = destH;
			keys = new Key[12];
			socket = new DatagramSocket(CONTROLLER_P);
			connect();
			listener.go();
		}catch(SocketException e ){
			e.printStackTrace();
		}
	}
	
	/**
	 * Incoming packets
	 */
	@Override
	public void onReceipt(DatagramPacket packet)throws Exception{
		StringContent content = new StringContent(packet);
		int srcPort = content.getsrcPort();
		int dstPort = content.getdstPort();
		terminal.println("Received request from: "+srcPort+"-"+dstPort);
		int[]path = checkPath(srcPort,dstPort);
		sendPacket(srcPort,dstPort,path);
	}
	
	private int[] checkPath(int srcPort, int dstPort){
		int p[] =null;
		for(int i=0; i<this.keys.length; i++){
			if(keys[i].contains(srcPort-40000, dstPort-40000)){
				p = keys[i].getPath();
			}
		}
		return p;
	}
	
	/**
	 * 
	 *Sending method
	 *
	 */
	private void sendPacket(int srcPort, int dstPort, int[] path)throws Exception{
		String response;
		for(int i=1; i<path.length-1; i++ ){
			response = ""+path[i+1];
			DatagramPacket reply = new 
					StringContent(response).toDatagramPacket(srcPort,dstPort,delimiter);
			InetSocketAddress node = new
					InetSocketAddress(destH,path[i]-40000);
			reply.setSocketAddress(node);
			terminal.println("Sending response to: "+reply.getPort());
			socket.send(reply);
		}
	}
	
	/**
	 * Connects with the input stream
	 */
	public void connect()
	{
		In in = new In("EWD.txt");
		EdgeWeightedDigraph EWD = new EdgeWeightedDigraph(in);
		int [][] point2point = {{1,2,3},{0,2,3},{0,1,3},{0,1,2}};
		int index=0;
		for (int i=0; i<point2point.length; i++)
		{
			for (int j=0; j<point2point[i].length;j++)
			{
				int [] list = construct(i,point2point[i][j],EWD);
				keys[index]=new Key (i,point2point[i][j],list);
				index++;
			}
		}
	}

	public int [] construct(int src, int dst, EdgeWeightedDigraph EWD)
	{
		ArrayList <Integer> tmp = new ArrayList <Integer>();
		DijkstraSP dsp = new DijkstraSP(EWD, src);
		if (dsp.hasPathTo(dst))
		{
			for (DirectedEdge e : dsp.pathTo(dst)) {
				tmp.add(e.to());
			}
		}
		int [] row = reverse(tmp,src);
		return row;
	}

	private int [] reverse(ArrayList <Integer> tmp,int srcP)
	{
		int [] list= new int [tmp.size()+1];
		int index=1;
		list[0]=srcP;
		for (int i=tmp.size()-1; i>=0; i--)
		{
			list[index]=tmp.get(i).intValue();
			index++;		
		}
		return list;
	}

	public synchronized void start()
	{
		try {
			terminal.println("Waiting for contact...");
			this.wait();
		} catch(java.lang.Exception e) {e.printStackTrace();}
	}

	public static void main(String[] args) {
		try {					
			Terminal terminal= new Terminal("Controller");		
			(new Controller(terminal, DEFAULT_DST_NODE)).start();
		} catch(java.lang.Exception e) {e.printStackTrace();}
	}


}

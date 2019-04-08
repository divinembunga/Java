/*
 * 
 * Router class
 * 
 * @author: Divine Mbunga
 */
import java.util.HashMap;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.InetSocketAddress;

import tcdIO.*;
public class Router extends Node {
	static final String DEFAULT_DST_NODE ="localhost";
	Terminal terminal;
	String destH;
	int nodeNumber;
	int localPort;
	int[] destPorts;
	
	RouterNodes[] routerNodes;
	RouterNodes rNode1;
	RouterNodes rNode2;
	RouterNodes rNode3;
	RouterNodes rNode4;
	
	InetSocketAddress controllerAdr;
	int packetNumber;
	int pathNumber;
	HashMap<String,Integer>path;
	HashMap<String,DatagramPacket>packt;
	
	Router(Terminal terminal, int nodeOne, int nodeTwo, int nodeThree,
			String destH, int srcPort, int[] destPorts){
		try{
			nodeNumber = 3;
			routerNodes    = new RouterNodes[nodeNumber];
			routerNodes[0] = new RouterNodes(nodeOne,terminal);
			routerNodes[1] = new RouterNodes(nodeTwo,terminal);
			routerNodes[2] = new RouterNodes(nodeThree,terminal);
			
			localPort = srcPort;
			this.terminal = terminal;
			this.destPorts = destPorts;
			this.destH = destH;
			
			socket= new DatagramSocket(localPort);
			controllerAdr = new InetSocketAddress(this.destH,CONTROLLER_P);
			path = new HashMap<String,Integer>();
			packt = new HashMap<String, DatagramPacket>();
			connect();
			listener.go();
		}catch(SocketException e){
			e.printStackTrace();
		}
	}
	
	Router(Terminal terminal, int nodeOne, int nodeTwo, int nodeThree,
			int nodeFour, String destH, int srcPort, int[] destPorts){
		try{
	
			nodeNumber = 4;
			routerNodes    = new RouterNodes[nodeNumber];
			routerNodes[0] = new RouterNodes(nodeOne,terminal);
			routerNodes[1] = new RouterNodes(nodeTwo,terminal);
			routerNodes[2] = new RouterNodes(nodeThree,terminal);
			routerNodes[3] = new RouterNodes(nodeFour,terminal);
			
			localPort = srcPort;
			this.terminal = terminal;
			this.destPorts = destPorts;
			this.destH = destH;
			
			socket= new DatagramSocket(localPort);
			controllerAdr = new InetSocketAddress(this.destH,CONTROLLER_P);
			path = new HashMap<String,Integer>();
			packt = new HashMap<String, DatagramPacket>();
			connect();
			listener.go();
		}catch(SocketException e){
			e.printStackTrace();
		}
			
	}
	
	private void connect(){
		RouterNodes r;
		for(int i=0;i<routerNodes.length;i++){
			r=routerNodes[i];
			r.connect(destH,destPorts[i]);
		}
	
	}
	
	private void sendNode(int node, DatagramPacket packet)throws Exception{
		terminal.println(""+node);
		for(int i=0; i<routerNodes.length;i++){
			terminal.println(""+routerNodes[i].getNode());
			if(routerNodes[i].getNode() == node){
				terminal.println("Sending....");
			}else{
				terminal.println("Cannot locate "+node);
			}
		}
	}
	
	@Override
	public void onReceipt(DatagramPacket packet)throws Exception{
		try{
			StringContent content = new StringContent(packet);
			int srcPort = content.getsrcPort();
			int dstPort = content.getdstPort();
			/*Store the path and send packet directly*/
			if(packet.getPort() == CONTROLLER_P){
				terminal.println("Recording node....");
				int nextNode = Integer.parseInt(content.toString());
				path.put(""+srcPort+""+dstPort, nextNode);
				if(packt.containsKey(""+srcPort+""+dstPort)){
					DatagramPacket packetS = packt.get(""+srcPort+""+dstPort);
					terminal.println("Sending packet to "+nextNode);
					sendNode(nextNode,packetS);
				}
			}else{
				/*
				 * if went through the same path again, check the hashMap for next node
				 */
				if (!path.isEmpty()&& path.containsKey(""+srcPort+""+dstPort))
				{
					terminal.println("Sending to "+path.get(""+srcPort+""+dstPort));	
					sendNode(path.get(""+srcPort+""+dstPort),packet);
				}
				//Communicate with  controller
				/*
				 * Store the previous packets so it doesn't get mixed up with the controller's packet 
				 */
				else 
				{
					terminal.println("Ask controller from:  "+srcPort+" to "+dstPort);
					packt.put(""+srcPort+""+dstPort, packet);
					//create a new packet for controller
					DatagramPacket callCtrl = new StringContent("").toDatagramPacket(srcPort,dstPort,delimiter);
					callCtrl.setSocketAddress(controllerAdr);	
					socket.send(callCtrl);
				}
			}
		}catch(Exception e) {e.printStackTrace();
		}
	}
	
	public synchronized void start(){
		try {
			terminal.println("Waiting for contact...");
			this.wait();
		} catch(java.lang.Exception e) {e.printStackTrace();}
	}

	public static void main(String[] args) {
		try {					
			Terminal terminal1= new Terminal("Router1");Terminal terminal2= new Terminal("Router2");	
			Terminal terminal3= new Terminal("Router3");Terminal terminal4= new Terminal("Router4");
			Terminal terminal5= new Terminal("Router5");Terminal terminal6= new Terminal("Router6");
			Terminal terminal7= new Terminal("Router7");Terminal terminal8= new Terminal("Router8");
			Terminal terminal9= new Terminal("Router9");Terminal terminal10= new Terminal("Router10");
			Terminal terminal11= new Terminal("Router11");Terminal terminal12= new Terminal("Router12");
			Terminal terminal13= new Terminal("Router13");Terminal terminal14= new Terminal("Router14");
			Terminal terminal15= new Terminal("Router15");Terminal terminal16= new Terminal("Router16");
			
			Router [] routerList = new Router[16];
			int []node1 ={40000,40008,40015};int []node2 ={40001,40009,40010};
			int []node3 ={40002,40011,40012};int []node4 ={40003,40013,40014};
			int []node5 ={40004,40009,40016};int []node6 ={40005,40008,40017};
			int []node7 ={40005,40011,40017};int []node8 ={40006,40010,40018};
			int []node9 ={40006,40013,40018};int []node10 ={40007,40012,40019};
			int []node11 ={40007,40015,40019};int []node12 ={40004,40014,40016};
			int []node13 ={40008,40015,40017,40019};int []node14 ={40009,40010,40016,40018};
			int []node15 ={40011,40012,40017,40019};int []node16 ={40013,40014,40016,40018};
			
			routerList[0]=new Router (terminal1,0,8,15,DEFAULT_DST_NODE,40004,node1);
			routerList[1]=new Router (terminal2,1,9,10,DEFAULT_DST_NODE,40005,node2);
			routerList[2]=new Router (terminal3,2,11,12,DEFAULT_DST_NODE,40006,node3);
			routerList[3]=new Router (terminal4,3,13,14,DEFAULT_DST_NODE,40007,node4);
			routerList[4]=new Router (terminal5,4,9,16,DEFAULT_DST_NODE,40008,node5);
			routerList[5]=new Router (terminal6,5,8,17,DEFAULT_DST_NODE,40009,node6);
			routerList[6]=new Router (terminal7,5,11,17,DEFAULT_DST_NODE,40010,node7);
			routerList[7]=new Router (terminal8,6,10,18,DEFAULT_DST_NODE,40011,node8);
			routerList[8]=new Router (terminal9,6,13,18,DEFAULT_DST_NODE,40012,node9);
			routerList[9]=new Router (terminal10,7,12,19,DEFAULT_DST_NODE,40013,node10);
			routerList[10]=new Router (terminal11,7,15,19,DEFAULT_DST_NODE,40014,node11);
			routerList[11]=new Router (terminal12,4,14,16,DEFAULT_DST_NODE,40015,node12);
			routerList[12]=new Router (terminal13,8,15,17,19,DEFAULT_DST_NODE,40016,node13);
			routerList[13]=new Router (terminal14,9,10,16,18,DEFAULT_DST_NODE,40017,node14);
			routerList[14]=new Router (terminal15,11,12,17,19,DEFAULT_DST_NODE,40018,node15);
			routerList[15]=new Router (terminal16,13,14,16,18,DEFAULT_DST_NODE,40019,node16);
			
			for (int i=0; i<routerList.length;i++)
			{
				routerList[i].start();
			}

		} catch(java.lang.Exception e) {e.printStackTrace();}
	}

}


			
			
			
	
			


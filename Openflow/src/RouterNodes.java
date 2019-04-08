/**
 * RouterNodes Class
 * 
 * @author Divine Mbunga
 */
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import tcdIO.Terminal;

public class RouterNodes {

	Terminal terminal;
	int node;
	InetSocketAddress dstAdr;

	public RouterNodes(int node, Terminal terminal)
	{	
		this.node = node;
		this.terminal=terminal;	
	}
	
	//get which node to send to 
	public int getNode(){
		return this.node;
	}

	//connect port to a host
	public void connect (String destH, int dstPort)
	{
		terminal.println("Node "+this.node+"is connected");
		this.dstAdr=new InetSocketAddress(destH,dstPort);
	}

	public void sendPacket (DatagramPacket packet, DatagramSocket socket) throws Exception{
		if (this.dstAdr != null){
			terminal.println("Sending from: "+ socket.getLocalPort()+"--"+this.dstAdr.getPort());
			packet.setSocketAddress(this.dstAdr);
			socket.send(packet);
		}
	}
}

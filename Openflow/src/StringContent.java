/**
 * StringContent Class
 * 
 * @author Divine Mbunga
 */
import java.net.DatagramPacket;
import java.nio.ByteBuffer;

public class StringContent implements PacketContent {
	String data;
	int srcPort,dstPort;
	
	public StringContent(DatagramPacket packet) {
		byte[] header;
		byte[] payload;
		byte[] buffer;
		
		buffer= packet.getData();
		header=new byte[HEADERLENGTH];
		payload= new byte[packet.getLength()-HEADERLENGTH];
		
		System.arraycopy(buffer, 0, header, 0, HEADERLENGTH);
		System.arraycopy(buffer, HEADERLENGTH, payload, 0, packet.getLength()-HEADERLENGTH);
		
		data = new String(payload);
		String temp = new String(header);
		String[] splitTemp = temp.split("*");
		srcPort=Integer.parseInt(splitTemp[0]);	
		dstPort=Integer.parseInt(splitTemp[1].trim());	
	}
	
	public StringContent(String string) {
		this.data = string;
	}
	
	public int getsrcPort()
	{
		return this.srcPort;
	}
	
	public int getdstPort()
	{
		return this.dstPort;
	}
	
	public String toString() {
		return data;
	}

	public DatagramPacket toDatagramPacket(int srcPort, int dstPort, String delimiter) {
		DatagramPacket packet= null;
		byte[] buffer= null;
		byte[] payload= null;
		byte[] header= null;
		byte[] tmpHeader;
		try {
			payload= data.getBytes();
			header= new byte[HEADERLENGTH];	
			tmpHeader=(""+srcPort+delimiter+dstPort).getBytes();	
			System.arraycopy(tmpHeader, 0, header, 0, tmpHeader.length);
			buffer= new byte[header.length+payload.length];
			System.arraycopy(header, 0, buffer, 0, header.length);
			System.arraycopy(payload, 0, buffer, HEADERLENGTH, payload.length);
			packet= new DatagramPacket(buffer, buffer.length);
		}
		catch(Exception e) {e.printStackTrace();}
		return packet;
	}	
}
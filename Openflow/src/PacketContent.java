/**
 * Packet Content Interface
 * 
 *@author Divine Mbunga
 */
import java.net.DatagramPacket;

public interface PacketContent {
	
	public static byte HEADERLENGTH = 20;
	
	public String toString();
	public DatagramPacket toDatagramPacket(int srcPort, int dstPort, String delimiter);
}

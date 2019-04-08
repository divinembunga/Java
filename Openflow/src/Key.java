/**
 * Key Class
 * 
 * @author Divine Mbunga
 *
 */
public class Key {
	int a,b;
	int [] path;

	public Key(int a, int b, int[] list)
	{
		this.a=a;
		this.b=b;
		this.path=list;
	}
	
	public boolean contains(int a, int b)
	{
		if (this.a==a && this.b==b)return true;
		else return false;
	}
	
	public int getx()
	{
		return a;
	}

	public int gety()
	{
		return b;
	}
	
	public int[] getPath()
	{
		return path;
	}
	
	public String toString()
	{
		return new String(a+", "+b);
	}
}


public class ClubMember {
	private String name;
	private String address;
	private int yearOfRegistration;
	private String email;

	public ClubMember(String name, String address, int yearOfRegistration, String email) {
		super();
		this.name = name;
		this.address = address;
		this.yearOfRegistration = yearOfRegistration;
		this.email = email;
	}

	@Override
	public String toString() {
		return "ClubMember [name=" + name + ", address=" + address + ", yearOfRegistration=" + yearOfRegistration
				+ ", email=" + email + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getYearOfRegistration() {
		return yearOfRegistration;
	}

	public void setYearOfRegistration(int yearOfRegistration) {
		this.yearOfRegistration = yearOfRegistration;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

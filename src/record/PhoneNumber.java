package record;

public class PhoneNumber {

	String homeNumber;
	String workNumber;
	String mobileNumber;
	
	public PhoneNumber(String homeNumber, String workNumber, 
			String mobileNumber) {
		this.homeNumber = homeNumber;
		this.workNumber = workNumber;
		this.mobileNumber = mobileNumber;
	}
	
	/*public void print() {
		System.out.println(nation+" "+province+" "+city+" "+addr);
	}*/
	public String print() {
		String temp = homeNumber+" "+workNumber+" "+mobileNumber;
		return temp;
	}
}


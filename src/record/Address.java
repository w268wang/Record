package record;


public class Address {
	String nation;
	String province;
	String city;
	String addr;
	
	public Address(String nation, String province, 
			String city, String addr) {
		this.nation = nation;
		this.province = province;
		this.city = city;
		this.addr = addr;
	}
	
	/*public void print() {
		System.out.println(nation+" "+province+" "+city+" "+addr);
	}*/
	public String print() {
		String temp = nation+" "+province+" "+city+" "+addr;
		return temp;
	}
}

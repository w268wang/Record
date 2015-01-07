package datastruct;


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

	public String getNation() {
	    return nation;
	}

	public String getProvince() {
	    return province;
	}

	public String getCity() {
	    return city;
	}

	public String getAddr() {
	    return addr;
	}
}

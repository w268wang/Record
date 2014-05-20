package record;
/*
 * ID	lastname	firstname	gender	age	
 * e_mail	address		dateofbirth		result
 */




public class Person {

	int ID;
	String lastname;
	String firstname;
	String gender; //"male"||"female"
	int age;
	String eMail;
	PhoneNumber phoneNumber;
	Address address;
	MyDate dateofbirth;
	String result;
	
	public Person(int ID, String lastname, String firstname,
			String gender, int age, String eMail, PhoneNumber phoneNumber, Address address,
			MyDate dateofbirth, String result) {
		this.ID = ID;
		this.lastname = lastname;
		this.firstname = firstname;
		this.gender = gender;
		this.age = age;
		this.eMail = eMail;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.dateofbirth = dateofbirth;
		this.result = result;
	}
	/*public void print() {
		System.out.println(ID+" "+lastname+" "+firstname+" "+gender
				+" "+age+" "+eMail+" ");
		address.print();
		dateofbirth.print();
		System.out.println(result);
	}*/
	public String print() {
		String temp = address.print();
		return ID+" - "+lastname+" - "+firstname+" - "+gender
				+" - "+age+" - "+eMail+" - "+phoneNumber.print()+" - "+address.print()+
				" - "+dateofbirth.print()+" - "+result;
	}
	public void setID(int ID){
		this.ID = ID;
	}
}

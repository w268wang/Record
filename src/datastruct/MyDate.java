package datastruct;


public class MyDate {

	int year;
	int month;
	int day;
	
	public MyDate(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}
	/*public void print() {
		System.out.println(year+"-"+month+"-"+day);
	}*/
	public String print() {
		String temp = year+" - "+month+" - "+day;
		return temp;
	}

	public int getYear() {
	    return year;
	}

	public int getMonth() {
	    return month;
	}

	public int getDay() {
	    return day;
	}
}

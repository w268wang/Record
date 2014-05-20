package record;


import java.util.*;
import java.io.*;

public class Data {
	
	private static final String TYPE = "wwj";
	private static final String SEPERATOR = "*********************************";
	private final String HOMEPATH = "D:/";
	
	private static int thisNameInd = 0;
	private static int lastNameInd = 0;
	
	static ArrayList<Person> data= new ArrayList<Person>();
	
	public Data(int nameInd) {
		//copy things from disk
		if(nameInd<0) {
			nameInd = 0;
			for(int i=0; i<Integer.MAX_VALUE; i++){
				File f = new File(HOMEPATH+"david/out"+i+"."+TYPE);
				if(!f.exists()){
					nameInd = i;
					break;
				}
			}
			thisNameInd = nameInd;
			System.out.println("The new file is out"+thisNameInd+"."+TYPE);
			return;
		}
		thisNameInd = nameInd;
		try{
			BufferedReader br = new BufferedReader(new FileReader
		    		(HOMEPATH+"david/out"+nameInd+"."+TYPE));
			br.readLine();
			String line=br.readLine();
			while(line!=null) {
				line = br.readLine();
				if(line.compareTo(SEPERATOR)==0)
					break;
				System.out.println(line);
			}
			while ((line = br.readLine()) != null) {
				/*
				// \\s+ means any number of whitespaces between tokens
			    String [] tokens = line.split("\\s+");
			    //int indexAcc = 0;
			    int ID = Integer.valueOf(tokens[0]).intValue();
				String lastname = tokens[2];
				String firstname = tokens[4];
				String gender = tokens[6];
				int age = Integer.valueOf(tokens[8]).intValue();
				String eMail = tokens[10];
				Address address = new 
						Address(tokens[12], tokens[13], tokens[14], tokens[15]);
				int year = Integer.valueOf(tokens[17]).intValue();
				int month = Integer.valueOf(tokens[19]).intValue();
				int day = Integer.valueOf(tokens[21]).intValue();
				MyDate dateofbirth = new MyDate(year, month, day);
				String result = tokens[23];
				System.out.println(result);
				Person temp = new Person(ID, lastname, firstname,
						gender, age, eMail, address, dateofbirth, result);
				Data.addElement(temp);*/
				int ID = Integer.valueOf(line).intValue();
				String lastname = br.readLine();
				String firstname = br.readLine();
				String gender = br.readLine();
				int age = Integer.valueOf(br.readLine()).intValue();
				String eMail = br.readLine();
				line = br.readLine();
				String [] tokens = line.split("\\s+");
				PhoneNumber phoneNumber = new 
						PhoneNumber(tokens[0], tokens[1], tokens[2]);
				line = br.readLine();
				tokens = line.split("\\s+");
				Address address = new 
						Address(tokens[0], tokens[1], tokens[2], tokens[3]);
				line = br.readLine();
				tokens = line.split("\\s+");
				int year = Integer.valueOf(tokens[0]).intValue();
				int month = Integer.valueOf(tokens[2]).intValue();
				int day = Integer.valueOf(tokens[4]).intValue();
				MyDate dateofbirth = new MyDate(year, month, day);
				String result = br.readLine();
				Person temp = new Person(ID, lastname, firstname,
						gender, age, eMail, phoneNumber, address, dateofbirth, result);
				Data.addElement(temp);
			}
			br.close();
		}catch (Exception e){//Catch exception if any
		      System.err.println("COPYError: " + e.getMessage());
		}
		int acc = 0;

		try{
			acc = 0;
			for(int i=0; i<Integer.MAX_VALUE; i++){
				File f = new File(HOMEPATH+"david/out"+i+"."+TYPE);
				if(!f.exists()){
					acc = i-1;
					break;
				}
			}
		} catch(Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		lastNameInd = acc-1;
		
	}
	
	static int length = data.size();
	
	public void save(int nameInd, String name) {
		//copy things to disk
		File dir = new File(HOMEPATH+"david");
		File ind = new File(HOMEPATH+"david/Index."+TYPE);
		if(!dir.exists()||!dir.isDirectory()){
			new File(HOMEPATH+"david").mkdirs();
		}
		if(!ind.exists()){
			try{
			    // Create file 
			    FileWriter fstream = new FileWriter(HOMEPATH+"david/Index."+TYPE);
			    BufferedWriter out = new BufferedWriter(fstream);
			    out.write("*********************************");
			    out.newLine();
			    out.write("*An Index for outputs of records*");
			    out.newLine();
			    out.write("*********************************");
			    out.newLine();
			    out.write("lastNameInd "+lastNameInd);
			    //Close the output stream
			    out.close();
			}catch (Exception e){//Catch exception if any
			      System.err.println("Error: " + e.getMessage());
			}
		}
		
		if(nameInd<0){
			nameInd = 0;
			for(int i=0; i<Integer.MAX_VALUE; i++){
				File f = new File(HOMEPATH+"david/out"+i+"."+TYPE);
				if(!f.exists()){
					nameInd = i;
					break;
				}
			}
		}
		
		try{
		    //FileWriter fstream = new FileWriter(System.currentTimeMillis() + "out.txt");
			// Create file
		    FileWriter f = new FileWriter(HOMEPATH+"david/out"+nameInd+"."+TYPE);
		    BufferedWriter out = new BufferedWriter(f);
		    out.write("*********************************");
		    out.newLine();
		    out.write("DataBase "+nameInd);
		    out.newLine();
		    out.write("Name "+name);
		    out.newLine();
		    out.write("DataSize "+data.size());
		    out.newLine();
		    out.write("*********************************");
		    out.newLine();
		    /*for(int i=0; i<length; i++) {
		    	out.write(data.get(i).print());
		    	out.newLine();
			}*/
		    
		    for(int i=0; i<length; i++) {
		    	out.write(""+data.get(i).ID);
		    	out.newLine();
		    	out.write(data.get(i).lastname);
		    	out.newLine();
		    	out.write(data.get(i).firstname);
		    	out.newLine();
		    	out.write(data.get(i).gender);
		    	out.newLine();
		    	out.write(""+data.get(i).age);
		    	out.newLine();
		    	out.write(data.get(i).eMail);
		    	out.newLine();
		    	out.write(data.get(i).phoneNumber.print());
		    	out.newLine();
		    	out.write(data.get(i).address.print());
		    	out.newLine();
		    	out.write(data.get(i).dateofbirth.print());
		    	out.newLine();
		    	out.write(data.get(i).result);
		    	out.newLine();
			}
		    
		    //Close the output stream
		    out.close();
		    BufferedReader br = new BufferedReader(new FileReader
		    	(HOMEPATH+"david/out"+nameInd+"."+TYPE));
			String line;
			while ((line = br.readLine()) != null) {
			   System.out.println(line);
			}
			 
			br.close();
		}catch (Exception e){//Catch exception if any
		      System.err.println("Error: " + e.getMessage());
		}
		try{
			File file = new File(HOMEPATH+"david/Index."+TYPE);
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line = null;
			while((line = br.readLine())!= null ){
			        // \\s+ means any number of whitespaces between tokens
			    String [] tokens = line.split("\\s+");
			    if(tokens[0]=="lastNameInd") 
			    	lastNameInd = Integer.valueOf(tokens[1]).intValue();
			    	
			}
			br.close();
		} catch (Exception ex){}
		if(lastNameInd<nameInd) {
			try{
			    FileWriter fstream = new FileWriter(HOMEPATH+"david/Index."+TYPE);
			    BufferedWriter out = new BufferedWriter(fstream);
			    out.write("*********************************");
			    out.newLine();
			    out.write("*An Index for outputs of records*");
			    out.newLine();
			    out.write("*********************************");
			    out.newLine();
			    out.write("lastNameInd "+nameInd);
			    //Close the output stream
			    out.close();
			}catch (Exception e){//Catch exception if any
			      System.err.println("Error: " + e.getMessage());
			}
		}
		thisNameInd = nameInd;
		/*
		 * try {

        String content = "This is the content to write into file";

        File file = new File("/users/mkyong/filename.txt");

        // if file doesnt exists, then create it
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(content);
        bw.close();

        System.out.println("Done");

    } catch (IOException e) {
        e.printStackTrace();
    }
		 */
	}
	
	public int getCurrentNameInd() {
		return thisNameInd;
	}
	public int getLastNameInd() {
		
		/*int nameInd = 0;

		try{
			nameInd = 0;
			for(int i=0; i<Integer.MAX_VALUE; i++){
				File f = new File("D:/david/out"+i+"."+TYPE);
				if(!f.exists()){
					nameInd = i;
					break;
				}
			}
		} catch(Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		return nameInd-1;
		
		lastNameInd = nameInd-1;
		try{
		    // Create file 
		    FileWriter fstream = new FileWriter("D:/david/Index."+TYPE);
		    BufferedWriter out = new BufferedWriter(fstream);
		    out.write("*********************************");
		    out.newLine();
		    out.write("*An Index for outputs of records*");
		    out.newLine();
		    out.write("*********************************");
		    out.newLine();
		    out.write("lastNameInd "+lastNameInd);
		    //Close the output stream
		    out.close();
		}catch (Exception e){//Catch exception if any
		      System.err.println("Error: " + e.getMessage());
		}*/
		return lastNameInd;
		
	}
	
	public static void addElement(Person p) {
		data.add(p);
		
		Collections.sort(data, new Comparator<Person>() {
			@Override
	        public int compare(Person per1, Person per2)
	        {
	            int result;
	            if(per1.lastname.compareTo("NULL")==0)
	            	result = 1;
	            else if(per2.lastname.compareTo("NULL")==0)
	            	result = -1;
	            else {
		    		try {result = per1.lastname.compareToIgnoreCase(per2.lastname);}
		    		catch(Exception e){
		    			String QWE = "";
		    			result = -1;
		    			try{per1.lastname.compareTo(QWE);}
		    			catch(Exception ex){result = 1;}
		    		}
		    		if(result == 0) {
		    			try {result = per1.firstname.compareToIgnoreCase(per2.firstname);}
			    		catch(Exception e){
			    			String QWE = "";
			    			result = -1;
			    			try{per1.firstname.compareTo(QWE);}
			    			catch(Exception ex){result = 1;}
			    		}
		    		}
	            }
	    		return result;
	        }
	    });
		length = data.size();
		for(int i=0; i<length; i++) {
			data.get(i).setID(i);
		}
		System.out.println("The length of this arraylist is now "+length);
		
	}
	public static void deleteElement(int ID) {
		Person temp = null;
		for(int i=0; i<length; i++) {
			if(data.get(i).ID==ID) {
				temp = data.get(i);
				break;
			}
		}
		data.remove(temp);
		Collections.sort(data, new Comparator<Person>() {
			@Override
	        public int compare(Person per1, Person per2)
	        {
				int result;
	            if(per1.lastname.compareTo("NULL")==0)
	            	result = 1;
	            else if(per2.lastname.compareTo("NULL")==0)
	            	result = -1;
	            else {
		    		try {result = per1.lastname.compareToIgnoreCase(per2.lastname);}
		    		catch(Exception e){
		    			String QWE = "";
		    			result = -1;
		    			try{per1.lastname.compareTo(QWE);}
		    			catch(Exception ex){result = 1;}
		    		}
		    		if(result == 0) {
		    			try {result = per1.firstname.compareToIgnoreCase(per2.firstname);}
			    		catch(Exception e){
			    			String QWE = "";
			    			result = -1;
			    			try{per1.firstname.compareTo(QWE);}
			    			catch(Exception ex){result = 1;}
			    		}
		    		}
	            }
	    		return result;
	        }
	    });
		length = data.size();
		for(int i=0; i<length; i++) {
			data.get(i).setID(i);
		}
		System.out.println("The length of this arraylist is "+length);
	}
	public static ArrayList fnameSearch(String firstname, ArrayList<Person> thisList) {
		
		ArrayList<Person> fname= new ArrayList<Person>();
		
		for(int i=0; i<length; i++) {
			if(thisList.get(i).firstname.compareToIgnoreCase(firstname)==0) {
				fname.add(thisList.get(i));
			}
		}
		
		return fname;
	}
	public static ArrayList lnameSearch(String lastname, ArrayList<Person> thisList) {

		ArrayList<Person> lname= new ArrayList<Person>();
		
		for(int i=0; i<length; i++) {
			if(thisList.get(i).lastname.compareToIgnoreCase(lastname)==0) {
				lname.add(thisList.get(i));
			}
		}
		
		return lname;
	}
	public static ArrayList genderSearch(String gender, ArrayList<Person> thisList) {

		ArrayList<Person> gend= new ArrayList<Person>();
		
		for(int i=0; i<length; i++) {
			if(thisList.get(i).gender.compareToIgnoreCase(gender)==0) {
				gend.add(thisList.get(i));
			}
		}
		
		return gend;
	}
	public ArrayList ageSearch(int age, ArrayList<Person> thisList) {

		ArrayList<Person> lage= new ArrayList<Person>();
		
		for(int i=0; i<length; i++) {
			if(thisList.get(i).age==age) {
				lage.add(thisList.get(i));
			}
		}
		
		return lage;
	}
	public ArrayList ageLessThanOrEqual(int age, ArrayList<Person> thisList) {

		ArrayList<Person> lage= new ArrayList<Person>();
		
		for(int i=0; i<length; i++) {
			if(thisList.get(i).age<=age) {
				lage.add(thisList.get(i));
			}
		}
		
		return lage;
	}
	public ArrayList ageBiggerThanOrEqual(int age, ArrayList<Person> thisList) {

	ArrayList<Person> lage= new ArrayList<Person>();
	
	for(int i=0; i<length; i++) {
		if(thisList.get(i).age>=age) {
			lage.add(thisList.get(i));
		}
	}
	
	return lage;
	}
	public ArrayList citySearch(String city, ArrayList<Person> thisList) {

		ArrayList<Person> lcity= new ArrayList<Person>();
		
		for(int i=0; i<length; i++) {
			if(thisList.get(i).address.city.compareToIgnoreCase(city)==0) {
				lcity.add(thisList.get(i));
			}
		}
		
		return lcity;
	}
	public String printresult(ArrayList<Person> arr) {
		String s = "";
		
		for(int i=0; i<arr.size(); i++){
			s = s + arr.get(i).print() + "\n";
		}
		
		return s;
	}
	
	public static ArrayList fnameSearch(String firstname) {
		
		ArrayList<Person> fname= new ArrayList<Person>();
		
		for(int i=0; i<length; i++) {
			if(data.get(i).firstname.compareToIgnoreCase(firstname)==0) {
				fname.add(data.get(i));
			}
		}
		
		return fname;
	}
	public static ArrayList lnameSearch(String lastname) {

		ArrayList<Person> lname= new ArrayList<Person>();
		
		for(int i=0; i<length; i++) {
			if(data.get(i).lastname.compareToIgnoreCase(lastname)==0) {
				lname.add(data.get(i));
			}
		}
		
		return lname;
	}
	public static ArrayList genderSearch(String gender) {

		ArrayList<Person> gend= new ArrayList<Person>();
		
		for(int i=0; i<length; i++) {
			if(data.get(i).gender.compareToIgnoreCase(gender)==0) {
				gend.add(data.get(i));
			}
		}
		
		return gend;
	}
	public ArrayList ageSearch(int age) {

		ArrayList<Person> lage= new ArrayList<Person>();
		
		for(int i=0; i<length; i++) {
			if(data.get(i).age==age) {
				lage.add(data.get(i));
			}
		}
		
		return lage;
	}
	public ArrayList ageLessThanOrEqual(int age) {

		ArrayList<Person> lage= new ArrayList<Person>();
		
		for(int i=0; i<length; i++) {
			if(data.get(i).age<=age) {
				lage.add(data.get(i));
			}
		}
		
		return lage;
	}
	public ArrayList ageBiggerThanOrEqual(int age) {

		ArrayList<Person> lage= new ArrayList<Person>();
	
		for(int i=0; i<length; i++) {
			if(data.get(i).age>=age) {
				lage.add(data.get(i));
			}
		}
	
		return lage;
	}
	public ArrayList citySearch(String city) {

		ArrayList<Person> lcity= new ArrayList<Person>();
		
		for(int i=0; i<length; i++) {
			if(data.get(i).address.city.compareToIgnoreCase(city)==0) {
				lcity.add(data.get(i));
			}
		}
		
		return lcity;
	}
}
			
/*
while ((line = br.readLine()) != null) {
				
	// \\s+ means any number of whitespaces between tokens
	String [] tokens = line.split("\\s+");
	int indexAcc = 2;
	String lastname;
	String firstname;
	String eMail;
	int ID = Integer.valueOf(tokens[0]).intValue();
	if(tokens[indexAcc].compareTo("-")==0) {
		lastname = "";
		indexAcc += 1;
	} else {
		lastname = tokens[indexAcc];
		indexAcc += 2;
	}
	if(tokens[indexAcc].compareTo("-")==0) {
		firstname = "";
		indexAcc += 1;
	} else {
		firstname = tokens[indexAcc];
		indexAcc += 2;
	}
	String gender = tokens[indexAcc];
	indexAcc += 2;
	int age = Integer.valueOf(tokens[indexAcc]).intValue();
	indexAcc += 2;
	if(tokens[indexAcc].compareTo("-")==0) {
		eMail = "";
		indexAcc += 1;
	} else {
		eMail = tokens[indexAcc];
		indexAcc += 2;
		
	Address address = new 
		Address(tokens[12], tokens[13], tokens[14], tokens[15]);
	int year = Integer.valueOf(tokens[17]).intValue();
	int month = Integer.valueOf(tokens[29]).intValue();
	int day = Integer.valueOf(tokens[21]).intValue();
	MyDate dateofbirth = new MyDate(year, month, day);
	String result = tokens[23];
	Person temp = new Person(ID, lastname, firstname,
	gender, age, eMail, address, dateofbirth, result);
	System.out.println(line);
}*/
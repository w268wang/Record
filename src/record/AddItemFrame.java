package record;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.io.*;
import java.util.*;


public class AddItemFrame extends JDialog implements ActionListener{
	
	private static final int WIDTH = 920;
	private static final int HEIGHT = 280;
	boolean ok = false;
	Person tempPerson;
	Boolean interIsSeleted = false;
	JButton addButton = new JButton("Add");
	JButton saveButton = new JButton("Save");
	JButton cancelButton = new JButton("Cancel");
	JTextField lastname = new JTextField(20);
	JTextField firstname = new JTextField(20);
	JTextField eMail = new JTextField(20);
	JTextField homePhone = new JTextField(12);
	JTextField workPhone = new JTextField(12);
	JTextField mobilePhone = new JTextField(12);
	JTextField othercountry = new JTextField(10);
	JTextField city = new JTextField(20);
	JTextField address = new JTextField(20);
	JTextField year = new JTextField(4);
	JTextField result = new JTextField(81);
	JComboBox<String> month;
	JComboBox<String> day;
	JComboBox<String> province;
	
	JRadioButton ca = new JRadioButton("Canada");
	JRadioButton inter = new JRadioButton("International");
	JRadioButton male = new JRadioButton("Male");
	JRadioButton female = new JRadioButton("Female");
	
	String Lastname = "NULL";
	String Firstname = "NULL";
	String Gender = "male";
	int Age = -1;
	String EMail = "NULL";
	String HomePhone = "NULL";
	String WorkPhone = "NULL";
	String MobilePhone = "NULL";
	String Nation = "Canada";
	String Province = "NULL";
	String City = "NULL";
	String Address = "NULL";
	int Year = -1;
	int Month = -1;
	int Day = -1;
	String Result = "NULL";
	String preMonth;
	String preYear="";
	
	String[] month0 = new String[13];
	DefaultComboBoxModel<String> dayTemp = new DefaultComboBoxModel();
	String[] province0 = new String[15];
	
	Mouseclicked mouseclicked=new Mouseclicked();
	
	public AddItemFrame(Frame owner, String title) {
		super(owner,"add item",true);
        setSize(WIDTH,HEIGHT);
        setResizable(false);
        setLayout(new BorderLayout());
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2
                - WIDTH / 2, Toolkit.getDefaultToolkit()
                .getScreenSize().height
                / 2 - HEIGHT / 2); //set the frame in the middle of the screen
        initialize();
        

    	month = new JComboBox<String>(month0);
    	preMonth = (String)month.getSelectedItem();
    	dayTemp.addElement("");
    	day = new JComboBox();
    	day.setModel(dayTemp);
    	day.setEnabled(false);
    	province = new JComboBox<String>(province0);
        
		//JRadioButton ca = new JRadioButton("Canada");
		ca.setActionCommand("Canada");
		ca.setSelected(true);
		//JRadioButton inter = new JRadioButton("International");
		inter.setActionCommand("International");
		
		//JRadioButton male = new JRadioButton("Male");
		male.setActionCommand("male");
		male.setSelected(true);
		//JRadioButton female = new JRadioButton("Female");
		female.setActionCommand("female");
		
		ButtonGroup group1 = new ButtonGroup(); //group the radio buttons
	    group1.add(ca);
	    group1.add(inter);
	    ButtonGroup group2 = new ButtonGroup();
	    group2.add(male);
	    group2.add(female);
		
	    ca.addActionListener(this);
	    inter.addActionListener(this);
	    male.addActionListener(this);
	    female.addActionListener(this);
		month.addActionListener(this);
		day.addActionListener(this);
		
		JPanel panelcountry = new JPanel(new GridLayout(1, 3));
		panelcountry.add(ca);
		panelcountry.add(inter);
		JPanel panelgender = new JPanel(new GridLayout(1, 2));
		panelgender.add(male);
		panelgender.add(female);
		
		JPanel panel1 = new JPanel(new GridLayout(4, 4));
		//line1
		panel1.add(new JLabel(" Lastname:"));
		panel1.add(lastname);
		panel1.add(new JLabel(" Country:"));
		panel1.add(panelcountry);
		//line2
		panel1.add(new JLabel(" Firstname:"));
		panel1.add(firstname);
		panel1.add(new JLabel(" Province:"));
		panel1.add(province);
		//line3
		panel1.add(new JLabel(" Gender:"));
		panel1.add(panelgender);
		panel1.add(new JLabel(" City:"));
		panel1.add(city);
		//line4
		panel1.add(new JLabel(" e-mail:"));
		panel1.add(eMail);
		panel1.add(new JLabel(" Address:"));
		panel1.add(address);
		
		//a panel to joint TextFields together
		JPanel panelphone = new JPanel(new GridLayout(1, 6));
		panelphone.add(new JLabel(" Home Phone"));
		panelphone.add(homePhone);
		panelphone.add(new JLabel(" Work Phone"));
		panelphone.add(workPhone);
		panelphone.add(new JLabel(" Mobilephone"));
		panelphone.add(mobilePhone);
		
		
		JPanel paneldate = new JPanel(new GridLayout(1, 10));
		paneldate.add(new JLabel(" Date of Birth"));
		paneldate.add(new JLabel(""));
		paneldate.add(new JLabel("Year:"));
		paneldate.add(year);
		paneldate.add(new JLabel("Month:"));
		paneldate.add(month);
		paneldate.add(new JLabel("Day:"));
		paneldate.add(day);
		
		JPanel panelCountry = new JPanel(new GridLayout(1, 2));
		panelCountry.add(new JLabel("              		          "
				+ "Other Country:"));
		panelCountry.add(othercountry);
		
		//a panel for result
		JPanel panel2 = new JPanel(new GridLayout(2, 1));
		panel2.add(new JLabel(" Diagnose Result:"));
		panel2.add(result);
		
		JPanel cenPanel = new JPanel(new FlowLayout());
		cenPanel.add(panel1);
		cenPanel.add(panelphone);
		cenPanel.add(paneldate);
		cenPanel.add(panelCountry);
		cenPanel.add(panel2);
		
		add(cenPanel, BorderLayout.CENTER);
		
        addButton.setActionCommand("addItem");
        addButton.addActionListener(this);
        saveButton.setActionCommand("saveItem");
        saveButton.addActionListener(this);
        cancelButton.setActionCommand("cancel");
        cancelButton.addActionListener(this);
        JPanel panelbutton = new JPanel(new GridLayout(1, 3));
        panelbutton.add(addButton);
        panelbutton.add(saveButton);
        panelbutton.add(cancelButton);
        add(panelbutton, BorderLayout.SOUTH);
        
        othercountry.setEditable(false);
        province.setEditable(false);
        province.setEnabled(true);
        month.setEditable(false);
        //day.setEditable(false);
        
		setVisible(true);
	
	}
	
	void initialize() {
		
		for(int i=1; i<=12; i++)
			month0[i] = "" + i;
		
		String[] temp= {"", "Ontario", "BritishColumbia", "Manitoba",
				"Alberta", "Quebec", "NovaScotia", "NewBrunswick", 
				"PrinceEdwardIsland", "Saskatchewan",
				"NewfoundlandandLabrador", "NorthWest", "Yukon",
				"Nunavut", "Other"};
		
		for(int i=0; i<15; i++)
			province0[i] = temp[i];
		
		month0[0] = "";
	}
	
	/*void setDay(int max){
		day0 = new String[max+1];
		for(int i=1; i<=max; i++)
			day0[i] = "" + i;
		day0[0] = "";
	}*/
	
	class Mouseclicked extends MouseAdapter{}
	public void actionPerformed(ActionEvent e) {
		
		String strMonth = (String)month.getSelectedItem();
		if(preMonth.compareTo(strMonth)!=0||(strMonth.compareTo("2")==0
				&&(isLeap(preYear)&&!isLeap(year.getText()))||
				(!isLeap(preYear)&&isLeap(year.getText())))){
			preMonth = (String)month.getSelectedItem();
			preYear = year.getText();
			if(strMonth.compareTo("")==0) {
				day.setEnabled(false);
			} else {
				int tempMonth = Integer.valueOf(strMonth).intValue();
				int maxDay;
				if(tempMonth==2) {
					maxDay = 28;
					try{int yearTemp = Integer.valueOf(year.getText()).intValue();
					if((yearTemp%100!=0&&yearTemp%4==0)||(yearTemp%100==0&&yearTemp%400==0)){maxDay = 29;}}
					catch(Exception ex){maxDay = 28;}
				} else if(tempMonth==4||tempMonth==6||tempMonth==9||tempMonth==11){
					maxDay = 30;
				} else {
					maxDay = 31;
				}
				MutableComboBoxModel<String> tempDCBM = (MutableComboBoxModel) dayTemp;
				while(true) {
					if(tempDCBM.getSize()==1){
						tempDCBM.removeElementAt(0);
						break;
					} else {
						tempDCBM.removeElementAt(0);
					}
				}
				tempDCBM.addElement("");
				for(int i=1; i<=maxDay; i++){
					tempDCBM.addElement(""+i);
				}
				day.setModel(tempDCBM);
				day.setEnabled(true);
			}
		}
		
		String cmd = e.getActionCommand();
		if(cmd.equals("addItem")){ //instantiate new game
			try{Lastname = lastname.getText();
			if(Lastname.compareTo("")==0) Lastname = "NULL";}catch(Exception ex)
			{Lastname = "NULL";}
			try{Firstname = firstname.getText();
			if(Firstname.compareTo("")==0) Firstname = "NULL";}catch(Exception ex)
			{Firstname = "NULL";}
			try{Province = (String)province.getSelectedItem();
			if(Province.compareTo("")==0) Province = "NULL";}catch(Exception ex)
			{Province = "NULL";}
			try{City = city.getText();
			if(City.compareTo("")==0) City = "NULL";}catch(Exception ex)
			{City = "NULL";}
			try{EMail = eMail.getText();
			if(EMail.compareTo("")==0) EMail = "NULL";}catch(Exception ex)
			{EMail = "NULL";}
			try{HomePhone = homePhone.getText();
			if(HomePhone.compareTo("")==0) HomePhone = "NULL";}catch(Exception ex)
			{HomePhone = "NULL";}
			try{WorkPhone = workPhone.getText();
			if(WorkPhone.compareTo("")==0) WorkPhone = "NULL";}catch(Exception ex)
			{WorkPhone = "NULL";}
			try{MobilePhone = mobilePhone.getText();
			if(MobilePhone.compareTo("")==0) MobilePhone = "NULL";}catch(Exception ex)
			{MobilePhone = "NULL";}
			try{Address = address.getText();
			if(Address.compareTo("")==0) Address = "NULL";}catch(Exception ex)
			{Address = "NULL";}
			try{String strYear = year.getText();
			Year = Integer.valueOf(strYear).intValue();}catch(Exception ex)
			{Year = -1;}
			try{strMonth = (String)month.getSelectedItem();
			Month = Integer.valueOf(strMonth).intValue();}catch(Exception ex)
			{Month = -1;}
			try{String strDay = (String)day.getSelectedItem();
			Day = Integer.valueOf(strDay).intValue();}catch(Exception ex)
			{Day = -1;}
			try{Result = result.getText();
			if(Result.compareTo("")==0) Result = "NULL";}catch(Exception ex)
			{Result = "NULL";}
			if(interIsSeleted) {try{Nation=othercountry.getText();
			if(Nation.compareTo("")==0) Nation = "NULL";}catch(Exception ex)
			{Nation = "NULL";}}
			if(Year>1900||Month>0||Day>0)
				Age = calculateAge();
			else
				Age = -1;
			PhoneNumber tempPhoneNumber = new PhoneNumber(WorkPhone, HomePhone, MobilePhone);
			Address tempAddress = new Address(Nation, Province, City, Address);
			MyDate tempDate = new MyDate(Year, Month, Day);
			tempPerson = new Person(0, Lastname, Firstname, Gender, Age,
					EMail, tempPhoneNumber, tempAddress, tempDate, Result);
			ok = true;
			setVisible(false);
			System.out.println("addItem");
			System.out.println("#:Name=" + Lastname + " " + Firstname + " || Gender=" 
					+ Gender + " || Province=" + Province + " || City=" + City + " || EMail="
					+ EMail + " || Address=" + Address + " || DOB=" + Year + "-" + Month
					+ "-" + Day + " || Nation=" + Nation + ":#");
			return;
		}
		if(cmd.equals("saveItem")){ //instantiate new game
			try{Lastname = lastname.getText();
			if(Lastname.compareTo("")==0) Lastname = "NULL";}catch(Exception ex)
			{Lastname = "NULL";}
			try{Firstname = firstname.getText();
			if(Firstname.compareTo("")==0) Firstname = "NULL";}catch(Exception ex)
			{Firstname = "NULL";}
			try{Province = (String)province.getSelectedItem();
			if(Province.compareTo("")==0) Province = "NULL";}catch(Exception ex)
			{Province = "NULL";}
			try{City = city.getText();
			if(City.compareTo("")==0) City = "NULL";}catch(Exception ex)
			{City = "NULL";}
			try{EMail = eMail.getText();
			if(EMail.compareTo("")==0) EMail = "NULL";}catch(Exception ex)
			{EMail = "NULL";}
			try{HomePhone = homePhone.getText();
			if(HomePhone.compareTo("")==0) HomePhone = "NULL";}catch(Exception ex)
			{HomePhone = "NULL";}
			try{WorkPhone = workPhone.getText();
			if(WorkPhone.compareTo("")==0) WorkPhone = "NULL";}catch(Exception ex)
			{WorkPhone = "NULL";}
			try{MobilePhone = mobilePhone.getText();
			if(MobilePhone.compareTo("")==0) MobilePhone = "NULL";}catch(Exception ex)
			{MobilePhone = "NULL";}
			try{Address = address.getText();
			if(Address.compareTo("")==0) Address = "NULL";}catch(Exception ex)
			{Address = "NULL";}
			try{String strYear = year.getText();
			Year = Integer.valueOf(strYear).intValue();}catch(Exception ex)
			{Year = -1;}
			try{strMonth = (String)month.getSelectedItem();
			Month = Integer.valueOf(strMonth).intValue();}catch(Exception ex)
			{Month = -1;}
			try{String strDay = (String)day.getSelectedItem();
			Day = Integer.valueOf(strDay).intValue();}catch(Exception ex)
			{Day = -1;}
			try{Result = result.getText();
			if(Result.compareTo("")==0) Result = "NULL";}catch(Exception ex)
			{Result = "NULL";}
			if(interIsSeleted) {try{Nation=othercountry.getText();
			if(Nation.compareTo("")==0) Nation = "NULL";}catch(Exception ex)
			{Nation = "NULL";}}
			if(Year>1900||Month>0||Day>0)
				Age = calculateAge();
			else
				Age = -1;
			System.out.println("Save");
			System.out.println("#:Name=" + Lastname + " " + Firstname + " || Gender=" 
					+ Gender + " || Province=" + Province + " || City=" + City + " || EMail="
					+ EMail + " || Address=" + Address + " || DOB=" + Year + "-" + Month
					+ "-" + Day + " || Nation=" + Nation + ":#");
			return;
		}
		if(cmd.equals("cancel")){
			ok = false;
			setVisible(false);
			System.out.println("cancel");
			return;
		}
		if (cmd.equals("Canada")) {
            Nation="Canada";
            province.setEnabled(true);
            othercountry.setEditable(false);
            othercountry.setEnabled(false);
            interIsSeleted = false;
            return;
		}
        else if (cmd.equals("International")) {
        	province.setEnabled(false);;
        	othercountry.setEditable(true);
        	othercountry.setEnabled(true);
        	interIsSeleted = true;
        	return;
        }
		if (cmd.equals("male"))
			Gender="male";//true
		else if (cmd.equals("female"))
			Gender="female";//false
		
		
	}
	public Person getResult() {
		System.out.println("getresult");
		return tempPerson;
	}
	public boolean getState() {
		return ok;
	}
	
	private boolean isLeap(String year) {
		int temp = 1;
		try{
			temp = Integer.valueOf(year).intValue();
		} catch(Exception e) {return false;}
		return (temp%100!=0&&temp%4==0)||(temp%100==0&&temp%400==0);
	}
	
	private int calculateAge() {
		int extra = 0;
		int base = 0;
		int thisYear;
		int thisMonth;
		int thisDay;
		
		Calendar thisDate = Calendar.getInstance();
		thisYear = thisDate.get(Calendar.YEAR);
		thisMonth = thisDate.get(Calendar.MONTH);
		thisDay = thisDate.get(Calendar.DAY_OF_MONTH);
		base = thisYear - Year - 1;
		
		if(thisMonth>Month||(thisMonth==Month&&thisDay>Day)){
			extra = 1;
		}
		
		return base+extra;
	}
	
	public static void main(String[] args) {
		AddItemFrame therecord = new AddItemFrame(null, "add");
	}
}

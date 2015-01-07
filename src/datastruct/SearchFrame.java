package datastruct;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class SearchFrame extends JDialog implements ActionListener {

    private static final long serialVersionUID = -2586964420946498641L;
    private static final int WIDTH = 400;
	private static final int HEIGHT = 200;
	private boolean ok = false;
	
	JRadioButton male = new JRadioButton("Male");
	JRadioButton female = new JRadioButton("Female");
	JCheckBox nul = new JCheckBox(" Show All");
	JCheckBox fname = new JCheckBox(" Firstname:");
	JCheckBox lname = new JCheckBox(" Lastname:");
	JCheckBox gender = new JCheckBox(" Gender:");
	JCheckBox jage = new JCheckBox(" Age:");
	JCheckBox jcity = new JCheckBox(" City:");
	JButton okButton = new JButton("Search!");
	JButton cancelButton = new JButton("Cancel");
	JTextField lastname = new JTextField(20);
	JTextField firstname = new JTextField(20);
	JTextField city = new JTextField(20);
	JComboBox<String> age;
	String[] age0 = new String[100];
	
	String Fname = null;
	String Lname = null;
	String City = null;
	String Gender = null;
	
	String[][] Result = new String[5][2];
	
	int Age = -1;
	
	//public SearchFrame(Frame owner, String title){
	public SearchFrame(Frame owner){
		super(owner,"record search",true);
        setSize(WIDTH,HEIGHT);
        setResizable(false);
        setLayout(new BorderLayout());
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2
                - WIDTH / 2, Toolkit.getDefaultToolkit()
                .getScreenSize().height
                / 2 - HEIGHT / 2); //set the frame in the middle of the screen
        initiallize();
        
        age = new JComboBox<String>(age0);
        
        //JRadioButton male = new JRadioButton("Male");
		male.setActionCommand("male");
		male.setSelected(true);
		//JRadioButton female = new JRadioButton("Female");
		female.setActionCommand("female");
		
		ButtonGroup group1 = new ButtonGroup();
	    group1.add(male);
	    group1.add(female);
        
        //JCheckBox nul = new JCheckBox(" Null");
		nul.setActionCommand("null");
		//JCheckBox fname = new JCheckBox(" Firstname:");
		fname.setActionCommand("fname");
		//JCheckBox lname = new JCheckBox(" Lastname:");
		lname.setActionCommand("lname");
		//JCheckBox gender = new JCheckBox(" Gender:");
		gender.setActionCommand("gender");
		//JCheckBox jage = new JCheckBox(" Age:");
		jage.setActionCommand("jage");
		//JCheckBox jcity = new JCheckBox(" City:");
		jcity.setActionCommand("jcity");
		
		nul.addActionListener(this);
		fname.addActionListener(this);
		lname.addActionListener(this);
		firstname.addActionListener(this);
		lastname.addActionListener(this);
		gender.addActionListener(this);
		jage.addActionListener(this);
		jcity.addActionListener(this);
		age.addActionListener(this);
		city.addActionListener(this);
		male.addActionListener(this);
		female.addActionListener(this);
		
		JPanel panelgender = new JPanel(new GridLayout(1, 2));
		panelgender.add(male);
		panelgender.add(female);
		
		JPanel wesPanel = new JPanel(new GridLayout(7, 2));
		wesPanel.add(new JLabel("Items for searching:"));
		wesPanel.add(new JLabel("      | Values:"));
		wesPanel.add(new JLabel(" Defalult?"));
		wesPanel.add(nul);
		wesPanel.add(new JLabel(" Lastname?"));
		wesPanel.add(lname);
		wesPanel.add(new JLabel(" Firstname?"));
		wesPanel.add(fname);
		wesPanel.add(new JLabel(" Gender?"));
		wesPanel.add(gender);
		wesPanel.add(new JLabel(" Age?"));
		wesPanel.add(jage);
		wesPanel.add(new JLabel(" City?"));
		wesPanel.add(jcity);
		add(wesPanel, BorderLayout.WEST);
		
		JPanel cenPanel = new JPanel(new GridLayout(7, 1));
		cenPanel.add(new JLabel(""));
		cenPanel.add(new JLabel(""));
		cenPanel.add(lastname);
		cenPanel.add(firstname);
		cenPanel.add(panelgender);
		cenPanel.add(age);
		cenPanel.add(city);
		add(cenPanel, BorderLayout.CENTER);
		
		okButton.setActionCommand("ok");
        okButton.addActionListener(this);
        cancelButton.setActionCommand("cancel");
        cancelButton.addActionListener(this);
        JPanel panelbutton = new JPanel(new GridLayout(1, 2));
        panelbutton.add(okButton);
        panelbutton.add(cancelButton);
        add(panelbutton, BorderLayout.SOUTH);
        
        
        age.setEnabled(false);
		lastname.setEditable(false);
		firstname.setEditable(false);
		city.setEditable(false);
		male.setEnabled(false);
		female.setEnabled(false);
		
		setVisible(true);
        
	}
	
	private void initiallize(){
		for(int i=0; i<100; i++)
			age0[i] = ""+i;
		for(int i=0; i<5; i++){
			for(int j=0; j<2; j++)
				Result[i][j] = null;
		}
		
	}
	
	public boolean getState(){
		return ok;
	}
	
	//class Mouseclicked extends MouseAdapter{}
	
	public void actionPerformed(ActionEvent e) {
		
		if(jage.isSelected()){
			age.setEnabled(true);
		} else {age.setEnabled(false);}
		if(lname.isSelected()){
			lastname.setEditable(true); 
		} else {lastname.setEditable(false);}
		if(fname.isSelected()){
			firstname.setEditable(true);
		} else {firstname.setEditable(false);}
		if(jcity.isSelected()){
			city.setEditable(true);
		} else {city.setEditable(false);}
		if(gender.isSelected()){
			male.setEnabled(true);
			female.setEnabled(true);
		} else {
			male.setEnabled(false);
			female.setEnabled(false);
		}
		
		String cmd = e.getActionCommand();
		
		if(cmd.equals("null")){
			
			if(nul.isSelected()){
				fname.setEnabled(false);
				lname.setEnabled(false);
				gender.setEnabled(false);
				jage.setEnabled(false);
				jcity.setEnabled(false);
				age.setEnabled(false);
				lastname.setEditable(false);
				firstname.setEditable(false);
				city.setEditable(false);
				male.setEnabled(false);
				female.setEnabled(false);
			} else {
				fname.setEnabled(true);
				lname.setEnabled(true);
				gender.setEnabled(true);
				jage.setEnabled(true);
				jcity.setEnabled(true);
				if(jage.isSelected())
					age.setEnabled(true);
				if(lname.isSelected())
					lastname.setEnabled(true);
				if(fname.isSelected())
					firstname.setEnabled(true);
				if(jcity.isSelected())
					city.setEnabled(true);
				if(gender.isSelected()){
					male.setEnabled(true);
					female.setEnabled(true);
				}
			}
			
			return;
			
		}
		
		if(cmd.equals("ok")){
			setVisible(false);
			if(nul.isSelected()){
				Result[0][0] = "null";
			} else {
				if(lname.isSelected()){
					Result[0][0] = "lname";
					try{Result[0][1] = lastname.getText();}catch(Exception ex)
					{Result[0][1] = "NULL";}finally{}
				}
				if(fname.isSelected()){
					Result[1][0] = "fname";
					try{Result[1][1] = firstname.getText();}catch(Exception ex)
					{Result[1][1] = "NULL";}finally{}
				}
				if(jage.isSelected()){
					Result[2][0] = "age";
					try{Result[2][1] = (String)age.getSelectedItem();}catch(Exception ex)
					{Result[2][1] = "NULL";}finally{}
				}
				if(jcity.isSelected()){
					Result[3][0] = "city";
					try{Result[3][1] = city.getText();}catch(Exception ex)
					{Result[3][1] = "NULL";}finally{}
				}
				if(gender.isSelected()){
					Result[4][0] = "gender";
					if(male.isSelected())
						Result[4][1] = "male";
					else
						Result[4][1] = "female";
				}
			}
			ok = true;
			System.out.println("ok");
			return;
		}
		if(cmd.equals("cancel")){
			ok = false;
			setVisible(false);
			System.out.println("cancel");
			return;
		}
	}
	
	/*int nextEmpty(String[] str) {
		
		int len = str.length;
		for(int i=0; i<len; i++) {
			if(str[i]==null) {
				return i;
			}
		}
		return 0;
	}*/
	
	public String[][] getresult() {
		System.out.println("getresult");
		return Result;
	}
	
	public static void main(String[] args) {
	    // For test
		@SuppressWarnings("unused")
        SearchFrame therecord = new SearchFrame(null);
	}

}

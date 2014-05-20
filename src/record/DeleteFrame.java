package record;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.io.*;
import java.util.*;

public class DeleteFrame extends JDialog implements ActionListener {
	
	private static final int WIDTH = 190;
	private static final int HEIGHT = 120;
	
	//static final String TYPE = "w268wang";
	
	boolean ok = false;
	
	private JLabel information;
	String content = "";
	int itemInd = 0;
	JButton deleteButton = new JButton("Delete!");
	JButton cancelButton = new JButton("Cancel");
	
	String[] itemInd0;
	JComboBox <String> itemIndAcc;
	
	ArrayList<Person> data= new ArrayList<Person>();
	
	DeleteFrame(Frame owner, ArrayList<Person> thisData){
		
		super(owner,"Load",true);
		setSize(WIDTH,HEIGHT);
        setResizable(false);
        setLayout(new BorderLayout());
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2
                - WIDTH / 2, Toolkit.getDefaultToolkit()
                .getScreenSize().height
                / 2 - HEIGHT / 2); //set the frame in the middle of the screen
        int DataSize = thisData.size();
        initiallize(DataSize);
        data = thisData;
        
        itemIndAcc = new JComboBox<String>(itemInd0);
        
        information = new JLabel("");
        information.setBounds(60, 140, 300, 20);
        
        deleteButton.setActionCommand("delete");
        cancelButton.setActionCommand("cancel");
        
        JPanel fileNameInput = new JPanel(new FlowLayout());
        fileNameInput.add(new JLabel("Please choose a index "));
        fileNameInput.add(itemIndAcc);
        add(fileNameInput, BorderLayout.NORTH);
        
        information.setText("   ");
        add(information, BorderLayout.CENTER);
        
        JPanel button = new JPanel(new GridLayout(1, 2));
        button.add(deleteButton);
        button.add(cancelButton);
        add(button, BorderLayout.SOUTH);
        
        itemIndAcc.addActionListener(this);
        deleteButton.addActionListener(this);
        cancelButton.addActionListener(this);
        
        deleteButton.setEnabled(false);
	    setVisible(true);
		
	}
	
	public int getValue() {
		return itemInd;
	}
	public boolean getState() {
		return ok;
	}
	
	private void initiallize(int max) {
		
		itemInd0 = new String[max+1];
		for(int i=1; i<=max; i++){
			int value = i-1;
			itemInd0[i] = "" + value;
		}
		itemInd0[0] = "";
		
	}

	
	public void actionPerformed(ActionEvent e) {
		
		String temp = (String)itemIndAcc.getSelectedItem();
		if(temp.compareTo("")==0)
			deleteButton.setEnabled(false);
		else {
			deleteButton.setEnabled(true);
		
			itemInd = Integer.valueOf(temp).intValue();
			content = data.get(itemInd).ID+" "+data.get(itemInd).lastname
					+" "+data.get(itemInd).firstname+" "+data.get(itemInd).gender
					+" "+data.get(itemInd).result;
			
			information.setText(content);
		}
		String cmd = e.getActionCommand();
		if(cmd.equals("delete")) {
			ok = true;
			setVisible(false);
			System.out.println("delete");
			return;
		} 
		if(cmd.equals("cancel")) {
			ok = false;
			setVisible(false);
			System.out.println("cancel");
			return;
		}
		
	}
	
	public static void main(String[] args) {

		DeleteFrame deleteFrame = new DeleteFrame(null, new ArrayList<Person>());

	}

	

}

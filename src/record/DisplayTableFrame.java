package record;



import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class DisplayTableFrame extends JDialog implements ActionListener {
	
	private static final int WIDTH = 1400;
	private static final int HEIGHT = 400;
	
	private JTable textTable;
	JButton okButton = new JButton("OK!");
	
	public DisplayTableFrame(Frame owner, ArrayList<Person> thisData) {
		
		super(owner,"record search",true);
		setSize(WIDTH,HEIGHT);
        setResizable(true);
        setLayout(new BorderLayout());
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2
                - WIDTH / 2, Toolkit.getDefaultToolkit()
                .getScreenSize().height
                / 2 - HEIGHT / 2); //set the frame in the middle of the screen
        
        
        String[] columnNames = {"ID", 
        		"Last Name", "First Name",
        		"Gender", "e-mail", "Age",
        		"Year", "Month",
        		"Day", "Home Phone", "Work Phone", "Mobilephone", "Nation",
        		"Province", "City",
        		"Address", "Result"};

        Object[][] data = new Object[thisData.size()][17];
        for(int i=0; i<thisData.size(); i++) {
        	data[i][0] = thisData.get(i).ID;
        	data[i][1] = thisData.get(i).lastname;
        	data[i][2] = thisData.get(i).firstname;
        	data[i][3] = thisData.get(i).gender;
        	data[i][4] = thisData.get(i).eMail;
        	data[i][5] = thisData.get(i).age;
        	data[i][6] = thisData.get(i).dateofbirth.year;
        	data[i][7] = thisData.get(i).dateofbirth.month;
        	data[i][8] = thisData.get(i).dateofbirth.day;
        	data[i][9] = thisData.get(i).phoneNumber.homeNumber;
        	data[i][10] = thisData.get(i).phoneNumber.workNumber;
        	data[i][11] = thisData.get(i).phoneNumber.mobileNumber;
        	data[i][12] = thisData.get(i).address.nation;
        	data[i][13] = thisData.get(i).address.province;
        	data[i][14] = thisData.get(i).address.city;
        	data[i][15] = thisData.get(i).address.addr;
        	data[i][16] = thisData.get(i).result;
        }

        textTable = new JTable(data, columnNames);
        //textTable.setBounds(0, 0, WIDTH, HEIGHT);
        
        JScrollPane scrollPane = new JScrollPane(textTable);
		
        textTable.setEnabled(false);
        textTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        textTable.getColumnModel().getColumn(0).setPreferredWidth(2);
        textTable.getColumnModel().getColumn(3).setPreferredWidth(7);
        textTable.getColumnModel().getColumn(5).setPreferredWidth(3);
        textTable.getColumnModel().getColumn(6).setPreferredWidth(5);
        textTable.getColumnModel().getColumn(7).setPreferredWidth(3);
        textTable.getColumnModel().getColumn(8).setPreferredWidth(3);
        
        add(new JLabel(" The result is:"), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
        setVisible(true);
        
	}
	
	class Mouseclicked extends MouseAdapter{}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

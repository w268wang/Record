package guiframe;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import datastruct.Person;

public class DisplayTableFrame extends JDialog implements ActionListener {

    private static final long serialVersionUID = 6599111164840448485L;
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
        	data[i][0] = thisData.get(i).getID();
        	data[i][1] = thisData.get(i).getLastname();
        	data[i][2] = thisData.get(i).getFirstname();
        	data[i][3] = thisData.get(i).getGender();
        	data[i][4] = thisData.get(i).getEMail();
        	data[i][5] = thisData.get(i).getAge();
        	data[i][6] = thisData.get(i).getDateOfBirth().getYear();
        	data[i][7] = thisData.get(i).getDateOfBirth().getMonth();
        	data[i][8] = thisData.get(i).getDateOfBirth().getDay();
        	data[i][9] = thisData.get(i).getPhoneNumber().getHomeNumber();
        	data[i][10] = thisData.get(i).getPhoneNumber().getWorkNumber();
        	data[i][11] = thisData.get(i).getPhoneNumber().getMobileNumber();
        	data[i][12] = thisData.get(i).getAddress().getNation();
        	data[i][13] = thisData.get(i).getAddress().getProvince();
        	data[i][14] = thisData.get(i).getAddress().getCity();
        	data[i][15] = thisData.get(i).getAddress().getAddr();
        	data[i][16] = thisData.get(i).getResult();
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
	
	public void actionPerformed(ActionEvent e) {}
}

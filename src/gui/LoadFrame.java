package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.io.*;

public class LoadFrame extends JDialog implements ActionListener {

    private static final long serialVersionUID = 7157883588675034571L;
    private static final int WIDTH = 190;
	private static final int HEIGHT = 120;
	
	private static final String TYPE = "wwj";
	private final String HOMEPATH = "D:/";
	
	boolean ok = false;
	
	private JLabel fileName;
	String name = "";
	int nameInd = 0;
	JButton loadButton = new JButton("Load!");
	JButton cancelButton = new JButton("Cancel");
	
	String[] nameInd0;
	JComboBox <String> nameIndAcc;
	
	public LoadFrame(Frame owner, int lastNameInd) {
		super(owner,"Load",true);
		setSize(WIDTH,HEIGHT);
        setResizable(false);
        setLayout(new BorderLayout());
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2
                - WIDTH / 2, Toolkit.getDefaultToolkit()
                .getScreenSize().height
                / 2 - HEIGHT / 2); //set the frame in the middle of the screen
        initiallize(lastNameInd);
        
        nameIndAcc = new JComboBox<String>(nameInd0);
        
        fileName = new JLabel("");
        fileName.setBounds(60, 140, 300, 20);
        
        loadButton.setActionCommand("load");
        cancelButton.setActionCommand("cancel");
        
        JPanel fileNameInput = new JPanel(new FlowLayout());
        fileNameInput.add(new JLabel("Please choose a file "));
        fileNameInput.add(nameIndAcc);
        add(fileNameInput, BorderLayout.NORTH);
        
        JPanel display = new JPanel(new FlowLayout());
        display.add(new JLabel("The file you choose is "));
        fileName.setText("   ");
        display.add(fileName);
        add(display, BorderLayout.CENTER);
        
        JPanel button = new JPanel(new GridLayout(1, 2));
        button.add(loadButton);
        button.add(cancelButton);
        add(button, BorderLayout.SOUTH);
        
        nameIndAcc.addActionListener(this);
        loadButton.addActionListener(this);
        cancelButton.addActionListener(this);
        
        loadButton.setEnabled(false);
	    setVisible(true);
	}
	
	private void initiallize(int max) {
		
		nameInd0 = new String[max+2];
		for(int i=1; i<=max+1; i++){
			int value = i-1;
			nameInd0[i] = "" + value;
		}
		nameInd0[0] = "";
		
	}
	
	public int getValue() {
		return nameInd;
	}
	public boolean getState() {
		return ok;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		String temp = (String)nameIndAcc.getSelectedItem();
		if(temp.compareTo("")==0)
			loadButton.setEnabled(false);
		else {
			loadButton.setEnabled(true);
		
			nameInd = Integer.valueOf(temp).intValue();
			try{
				File file = new File(HOMEPATH+"david/out"+nameInd+"."+TYPE);
				System.out.println(nameInd);
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				String line = null;
				while((line = bufferedReader.readLine())!= null ){
				        // \\s+ means any number of whitespaces between tokens
				    String [] tokens = line.split("\\s+");
				    if(tokens[0].compareTo("Name")==0) {
				    	name = line.substring(5);
				    }
				}
				bufferedReader.close();
			} catch (Exception ex){}
			
			fileName.setText(name);
		}
		String cmd = e.getActionCommand();
		if(cmd.equals("load")) {
			ok = true;
			setVisible(false);
			System.out.println("load");
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
		@SuppressWarnings("unused")
        LoadFrame loadFrame = new LoadFrame(null, 8);
	}

}

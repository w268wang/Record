package record;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.io.*;
import java.util.*;


public class Record extends JFrame implements ActionListener{
	
	private boolean savable = false;
	private int nameInd;
	private String dataName = "";
	private final String TYPE = "wwj";
	private final String HOMEPATH = "D:/";
	
	private JMenuBar menubar;	//the menu bar at the top
	private JMenu[] mainMenu={new JMenu("File"),new JMenu("Help")};
	private JMenuItem[] subMenu1={new JMenuItem("New File"),new JMenuItem("Save"),new JMenuItem("Load"),
			new JMenuItem("Setting"),new JMenuItem("Exit")};
	private JMenuItem[] subMenu2={new JMenuItem("How to use"),new JMenuItem("About")};
	
	private JButton add = new JButton("add item");
	private JButton modify = new JButton("modify item");
	private JButton delete = new JButton("delete item");
	private JButton search = new JButton("search");
	private JButton info = new JButton("info");
	
	private static final int WIDTH = 170;	//set initial width
	private static final int HEIGHT = 225;	//set initial height
	//static Data thisData = new Data();
	static Data thisData;
	Mouseclicked mouseclicked=new Mouseclicked();
	
	public Record() {
		super("Records");
        setSize(WIDTH,HEIGHT);
        setResizable(false);
        setLayout(new FlowLayout());
        
        
    //to add the menu and add listener to all of them
        menubar=new JMenuBar();
        subMenu1[0].setActionCommand("new");
        subMenu1[1].setActionCommand("save");
        subMenu1[2].setActionCommand("load");
        subMenu1[3].setActionCommand("setting");
        subMenu1[4].setActionCommand("exit");
        subMenu2[0].setActionCommand("how");
        subMenu2[1].setActionCommand("about");
        for(int i=0;i<5;i++){ //add subMenu to mainMenu
            mainMenu[0].add(subMenu1[i]);
            subMenu1[i].addActionListener(this);
        }
        for(int i=0;i<2;i++){ //change to i < 3 to add backtrack function
            mainMenu[1].add(subMenu2[i]);
            subMenu2[i].addActionListener(this);
        }
        for(int i=0;i<2;i++)
            menubar.add(mainMenu[i]);
        setJMenuBar(menubar);
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2
                - WIDTH / 2, Toolkit.getDefaultToolkit()
                .getScreenSize().height
                / 2 - HEIGHT / 2); //set the frame in the middle of the screen
        add(add);
        add(modify);
        add(delete);
        add(search);
        add(info);
        add.addActionListener(this);
        modify.addActionListener(this);
        delete.addActionListener(this);
        search.addActionListener(this);
        info.addActionListener(this);
        add.setEnabled(false);
		modify.setEnabled(false);
		delete.setEnabled(false);
		search.setEnabled(false);
		info.setEnabled(false);
		
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
        	public void windowClosing(WindowEvent e) {
        		int tempConfirm = JOptionPane.showConfirmDialog(null,
        				"Are you sure to close the file?", "Warning",
        				JOptionPane.YES_NO_OPTION);
        		if (tempConfirm == JOptionPane.YES_OPTION) {  
        			System.exit(0);  //close the frame
        	    } else {}
        	} 
        });
        setVisible(true);
	}
	
	class Mouseclicked extends MouseAdapter{  //read information about mouse
        public void mouseClicked(MouseEvent evt){}
            
	}
	
    public void actionPerformed(ActionEvent e) { //where actions happen
		
    	if(!savable) {
    		add.setEnabled(false);
    		modify.setEnabled(false);
    		delete.setEnabled(false);
    		search.setEnabled(false);
    		info.setEnabled(false);
    	} else {
    		add.setEnabled(true);
    		modify.setEnabled(true);
    		delete.setEnabled(true);
    		search.setEnabled(true);
    		info.setEnabled(true);
    	}
    	
    	String cmdx = e.getActionCommand();
		if (cmdx.equals("add item")){
			AddItemFrame tempadd = new AddItemFrame(null, "add");
			if(tempadd.getState()){
				Person temp = tempadd.getResult();
				thisData.addElement(temp);
				System.out.println("add record");
			}
			return;
		}
		if (cmdx.equals("modify item")){
		    
			int ID = -1;
		    ModifyItemFrame modifyframe = new ModifyItemFrame(null, thisData.data);
		    if(modifyframe.getState()){
			    ID = modifyframe.getValue();
			    thisData.deleteElement(ID);
			    thisData.addElement(modifyframe.getResult());
				System.out.println("modify");
		    }
		    return;
		}
		if (cmdx.equals("delete item")){
		    
		    int ID = -1;
		    DeleteFrame deleteframe = new DeleteFrame(null, thisData.data);
		    if(deleteframe.getState()){
			    ID = deleteframe.getValue();
			    thisData.deleteElement(ID);
				System.out.println("delete");
		    }
		    return;
		}
		if (cmdx.equals("search")){
		    
			String finalResult = null;
			
			SearchFrame searchframe = new SearchFrame(null);
			if(searchframe.getState()) {
				String[][] Result = searchframe.getresult();
				if(Result[0][0]!=null&&Result[0][0].compareTo("null")==0){
					DisplayTableFrame displaytableframe = new DisplayTableFrame(null, thisData.data);
				} else {
					
					ArrayList<Person> searchResult=thisData.data;
					
					if(Result[0][0]!=null)
						searchResult = thisData.lnameSearch(Result[0][1], searchResult);
					if(Result[1][0]!=null)
						searchResult = thisData.fnameSearch(Result[1][1], searchResult);
					if(Result[2][0]!=null)
						searchResult = thisData.ageSearch(Integer.valueOf(Result[2][1]).intValue()
								, searchResult);
					if(Result[3][0]!=null)
						searchResult = thisData.citySearch(Result[3][1], searchResult);
					if(Result[4][0]!=null)
						searchResult = thisData.genderSearch(Result[4][1], searchResult);
					DisplayTableFrame displaytableframe = new DisplayTableFrame(null, searchResult);
					//try{finalResult = thisData.printresult(searchResult);}
					//catch(Exception ex){finalResult="Error!";}finally{}
					
				}
				
				System.out.println("search");
			    //DisplayFrame displayframe = new DisplayFrame(null, finalResult);
			    
			}
		    return;
		    
		}
		if (cmdx.equals("info")){
			
			int total = thisData.data.size();
			int maleNum = thisData.genderSearch("male").size();
			int femaleNum = thisData.genderSearch("female").size();
			int adultNum = thisData.ageBiggerThanOrEqual(17).size();
			JOptionPane.showMessageDialog(null,"This file has "+total
					+ " item(s).\n" + maleNum
					+ " male(s)    " + femaleNum + " female(s)\n"
					+ adultNum +" adult(s)");
		    System.out.println("show info");
		    return;
		}
		
		
		JMenuItem target = (JMenuItem)e.getSource(); //get targeted menu item
		String cmd = target.getActionCommand(); //store the commad in cmd
		if(cmd.equals("new")){ //instantiate new game
			
			if(savable) {
				int returnvalue = JOptionPane.showConfirmDialog(null, "Do you "
						+ "want to save the current file?", "Warning",
						JOptionPane.YES_NO_OPTION);
				if(returnvalue==JOptionPane.YES_OPTION) {
					thisData.save(nameInd, dataName);
					System.out.println("save");
				}
			}
			thisData= new Data(-1);
			savable = true;
			dataName = JOptionPane.showInputDialog("Please input a name for new database:");
			add.setEnabled(true);
    		modify.setEnabled(true);
    		delete.setEnabled(true);
    		search.setEnabled(true);
    		info.setEnabled(true);
    		nameInd = thisData.getCurrentNameInd();
    		System.out.println("The new file is confirmed to be out"+nameInd+"."+TYPE);
			System.out.println("new");
			return;
		}
		if (cmd.equals("save")){ //save file
		   
			if(savable) {
				int tempConform = JOptionPane.showConfirmDialog(null, "Do you "
						+ "want to save the current file?", "Warning",
						JOptionPane.YES_NO_OPTION);
				if(tempConform==JOptionPane.YES_OPTION) {
					thisData.save(nameInd, dataName);
					System.out.println("save");
				}
			} else {
				JOptionPane.showMessageDialog(null, "There is nothing to save!");
			}
			return;
			
	
		}
		if (cmd.equals("load")){ //load file
			
			File dir = new File(HOMEPATH+"david");
			File ind = new File(HOMEPATH+"david/Index."+TYPE);
			if(!dir.exists()||!dir.isDirectory()||!ind.exists()){
				JOptionPane.showMessageDialog(null,"No existing file!");
				System.out.println("load");
				return;
			}
			int lastNameInd = 0;
			try{
				File file = new File(HOMEPATH+"david/Index."+TYPE);
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				String line = null;
				while((line = br.readLine())!= null ){
				        // \\s+ means any number of whitespaces between tokens
				    String [] tokens = line.split("\\s+");
				    if(tokens[0].compareTo("lastNameInd")==0) {
				    	lastNameInd = Integer.valueOf(tokens[1]).intValue();
				    	//System.out.println("SET");
				    }
				    	
				}
			} catch (Exception ex){}
			
			/*int lastNameInd = 0;
			
			try{
				lastNameInd = thisData.getLastNameInd();
			} catch (Exception ex) {
				System.out.println("Error: "+ex.getMessage());
			}*/
			
			if(lastNameInd<0){
				JOptionPane.showMessageDialog(null,"No existing file!");
				System.out.println("load");
				return;
			} /*else if(lastNameInd==0){
				try{
					File file = new File("D:/david/out0."+TYPE);
					BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
					String line = null;
					while((line = br.readLine())!= null ){
					        // \\s+ means any number of whitespaces between tokens
					    String [] tokens = line.split("\\s+");
					    if(tokens[0].compareTo("Name")==0) 
					    	dataName = line.substring(5);
					    	
					}
				} catch (Exception ex){}
				JOptionPane.showMessageDialog(null,"Database 0: "+dataName+" is choosed!");
				thisData= new Data(0);
				System.out.println("load");
				return;
			}*/
			
			/*String x = JOptionPane.showInputDialog("Please choose"
					+ " a Data file (from 0 to"+lastNameInd+"):");
			boolean comp = true;
			do{
				try
				{
					comp = true;
					nameInd = Integer.valueOf(x).intValue();
					
					while(nameInd<0||nameInd>lastNameInd)
					{
						JOptionPane.showMessageDialog(null,"Error, please choose again!");
						String x2 = JOptionPane.showInputDialog("Please choose"
								+ " a Data file (from 0 to"+lastNameInd+"):");
						int x3 = Integer.valueOf(x2).intValue();
						nameInd=x3;
					}
				}
				catch(Exception ex)
				{
					comp = false;
				}
			}while(!comp);*/
			LoadFrame findNameInd = new LoadFrame(null, lastNameInd);
			if(findNameInd.getState()) {
				nameInd = findNameInd.getValue();
				try{
					File file = new File(HOMEPATH+"david/out"+nameInd+"."+TYPE);
					BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
					String line = null;
					while((line = br.readLine())!= null ){
					        // \\s+ means any number of whitespaces between tokens
					    String [] tokens = line.split("\\s+");
					    if(tokens[0].compareTo("Name")==0) 
					    	dataName = line.substring(5);
					    	
					}
				} catch (Exception ex){}
				JOptionPane.showMessageDialog(null,"Database "+nameInd+": "+dataName+ " is choosed!");
				thisData= new Data(nameInd);
				add.setEnabled(true);
	    		modify.setEnabled(true);
	    		delete.setEnabled(true);
	    		search.setEnabled(true);
	    		info.setEnabled(true);
	    		savable = true;
			    System.out.println("load");
			}
		    return;
		}
		if (cmd.equals("setting")){ //read the setting
			//String savePath = JOptionPane.showInputDialog("The file will be installed in ");
			
			System.out.println("setting");
		}
		if (cmd.equals("exit")){
			if(savable) {
				int returnvalue = JOptionPane.showConfirmDialog(null, "Do you "
						+ "want to save this file?", "Warning",
						JOptionPane.YES_NO_CANCEL_OPTION);
				if(returnvalue==JOptionPane.YES_OPTION) {
					thisData.save(nameInd, dataName);
					System.out.println("save");
				} else if(returnvalue==JOptionPane.CANCEL_OPTION) {
					return;
				}
			}
		    System.out.println("exit");
		    System.exit(1); //exit
		}
		if (cmd.equals("how")){ //how to play part
		    JOptionPane.showMessageDialog(null,"add: add a new person\n"
		    		+ "modify: modify a existing person\n"
		    		+ "delete: delete a existing person\n"
		    		+ "search: search the whole file\n"
		    		+ "info: information about this file\n");
		}
		if (cmd.equals("about")){ //about message
			JOptionPane.showMessageDialog(null,"Record V1.1 (demo version)\n"
					+ "Implmented by David Wang\n"
					+ "Contact me: w268wang@uwaterloo.ca\n"
					+ "Updated and debugged by Felix Tian\n"
					+ "Assistant Tester:\n"
					+ "Felix Tian the StudyBully\n"
					+ "Jayson Pan the conqueror\n"
					+ "Joyce Ji the troll\n");
		}

    }
	
	public static void main(String[] args) {
		Record therecord = new Record();
	}

}
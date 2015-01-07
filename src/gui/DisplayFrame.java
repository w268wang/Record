package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class DisplayFrame extends JDialog implements ActionListener {

    private static final long serialVersionUID = 6659563193616407129L;
    private static final int WIDTH = 800;
	private static final int HEIGHT = 500;
	
	private JTextArea textArea;
	//private JTable subtitle;
	JButton okButton = new JButton("OK!");
	
	//public DisplayFrame(Frame owner, String title, int height) {
	public DisplayFrame(Frame owner, String content) {
		
		super(owner,"record search",true);
		setSize(WIDTH,HEIGHT);
        setResizable(true);
        setLayout(new BorderLayout());
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2
                - WIDTH / 2, Toolkit.getDefaultToolkit()
                .getScreenSize().height
                / 2 - HEIGHT / 2); //set the frame in the middle of the screen
        
        textArea = new JTextArea("");
        textArea.setBounds(0, 0, WIDTH, HEIGHT);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        textArea.setText(content);
		
        add(new JLabel(" The result is:"), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
        setVisible(true);
        
	}
	
	class Mouseclicked extends MouseAdapter{}
	
	@Override
	public void actionPerformed(ActionEvent e) {}
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
        DisplayFrame therecord = new DisplayFrame(null, "as\nfaas\nasdasa\nasdas\nas\na\nas\nas\na");
	}

}

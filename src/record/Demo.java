package record;



public class Demo {

	public static void main(String[] args) {
		String a = "a";
		String b = "a";
		boolean sada = (a!=null);
		System.out.print(sada);
	}

}


/*public class Demo {

	public static void main(String[] args) {
		/*StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<100; i++){
			sb.append(i);
		}
		String s = sb.toString();
		String s = "";
		for(int i=0; i<100; i++){
			s+=i;
			s+="\n";
		}
		System.out.println(s);
	}

}*/

/*import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
public class Demo {
	private JLabel likeLabel;
	private JCheckBox bookCheckBox;
	private JCheckBox musiCheckBox;
	private JCheckBox footballCheckBox;
	private JLabel answerLabel;
	public void add(JFrame frame) {
		frame.setTitle("��ѡ���ʹ�÷���");
		frame.setLayout(null);
		likeLabel = new JLabel("���ã�");
		likeLabel.setBounds(60, 100, 40, 20);
		ActionListener likeCheckBoxActionListener = new ActionListener() {// ����һ�����õ�ActionEvent�¼�������
			public void actionPerformed(ActionEvent e) {
				answerLabel.setText("���İ����У�");
				if (bookCheckBox.isSelected())
				answerLabel.setText(answerLabel.getText() + "    "
				+ bookCheckBox.getText());
				if (musiCheckBox.isSelected())
				answerLabel.setText(answerLabel.getText() + "    "
				+ musiCheckBox.getText());
				if (footballCheckBox.isSelected())
				answerLabel.setText(answerLabel.getText() + "    "
				+ footballCheckBox.getText());
			}
		};
		bookCheckBox = new JCheckBox("����");// ����ָ���ı��ĸ�ѡ��
		bookCheckBox.setBounds(120, 100, 60, 20);
		bookCheckBox.addActionListener(likeCheckBoxActionListener);
		musiCheckBox = new JCheckBox("������");// ����ָ���ı��ĸ�ѡ��
		musiCheckBox.setBounds(190, 100, 70, 20);
		musiCheckBox.addActionListener(likeCheckBoxActionListener);
		footballCheckBox = new JCheckBox("������");// ����ָ���ı��ĸ�ѡ��
		footballCheckBox.setBounds(270, 100, 70, 20); 
		footballCheckBox.addActionListener(likeCheckBoxActionListener);
		answerLabel = new JLabel("");
		answerLabel.setBounds(60, 140, 300, 20);
		frame.add(likeLabel);
		frame.add(bookCheckBox);
		frame.add(musiCheckBox);
		frame.add(footballCheckBox);
		frame.add(answerLabel);
	}
	public static void main(String[] args) {
		JFrame frame = new JFrame("����JFrame��������");// ����ָ�������JFrame���ڶ���
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// �رհ�ť�Ķ���Ϊ�˳�����
		frame.setSize(400, 300);// ���ô��ڴ�С
		Dimension displaySize = Toolkit.getDefaultToolkit().getScreenSize();// �����ʾ����С����
		Dimension frameSize = frame.getSize();// ��ô��ڴ�С����
		if (frameSize.width > displaySize.width)
		frameSize.width = displaySize.width;// ���ڵĿ�Ȳ��ܴ�����ʾ���Ŀ��
		if (frameSize.height > displaySize.height)
		frameSize.height = displaySize.height;// ���ڵĸ߶Ȳ��ܴ�����ʾ���ĸ߶�
		frame.setLocation((displaySize.width - frameSize.width) / 2,
		(displaySize.height - frameSize.height) / 2);// ���ô��ھ�����ʾ����ʾ
		Demo index = new Demo();
		index.add(frame);// ��JFrame������ӱ�ǩ
		frame.setVisible(true);// ���ô���Ϊ�ɼ��ģ�Ĭ��Ϊ���ɼ�
	}
}*/
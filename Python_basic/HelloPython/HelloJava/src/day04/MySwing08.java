package day04;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MySwing08 extends JFrame {

	private JPanel contentPane;
	JTextArea ta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MySwing08 frame = new MySwing08();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MySwing08() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbl1 = new JLabel("첫별");
		lbl1.setBounds(41, 32, 57, 15);
		contentPane.add(lbl1);

		JLabel lbl2 = new JLabel("끝별");
		lbl2.setBounds(41, 67, 57, 15);
		contentPane.add(lbl2);

		JTextArea tft1 = new JTextArea();
		tft1.setBounds(151, 27, 112, 22);
		contentPane.add(tft1);

		JTextArea tft2 = new JTextArea();
		tft2.setBounds(151, 62, 112, 22);
		contentPane.add(tft2);

		JButton btn = new JButton("New button");
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				String result = "";
				String lastNum = tft2.getText();
				String firstNum = tft1.getText();
				for (int i = 0; i < Integer.parseInt(lastNum); i++) {
					for (int j = 0; j < i + Integer.parseInt(firstNum); j++) {
						result += "*";
					}
					result += "\n";
				}
				ta.setText(result);

			}
		});
		btn.setBounds(284, 44, 97, 23);
		contentPane.add(btn);

		ta = new JTextArea();
		ta.setBounds(41, 92, 293, 159);
		contentPane.add(ta);
	}

}

package day04;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class MySwing06 extends JFrame {

	private JPanel contentPane;
	private JTextField tft;
	JTextArea txt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MySwing06 frame = new MySwing06();
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
	public MySwing06() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbl = new JLabel("New label");
		lbl.setBounds(114, 13, 57, 15);
		contentPane.add(lbl);

		tft = new JTextField();
		tft.setBounds(212, 10, 44, 21);
		contentPane.add(tft);
		tft.setColumns(10);

		JButton btn = new JButton("New button");
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String text = "";
				for (int i = 1; i <= 9; i++) {
					System.out.println(tft.getText() + "*" + i + "=" + Integer.parseInt(tft.getText()) * i);
					text += tft.getText() + "*" + i + "=" + Integer.parseInt(tft.getText()) * i + " \n";

				}
				txt.setText(text);

			}
		});
		btn.setBounds(133, 50, 97, 23);
		contentPane.add(btn);

		txt = new JTextArea();
		txt.setBounds(95, 83, 202, 165);
		contentPane.add(txt);
	}
}

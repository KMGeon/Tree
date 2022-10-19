package day04;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class MySwing04 extends JFrame {

	private JPanel contentPane;
	private JTextField tf1;
	private JTextField tf2;
	private JTextField tf3;
	private JTextField tf4;
	private JTextField tf5;
	private JTextField tf6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MySwing04 frame = new MySwing04();
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
	public MySwing04() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		tf1 = new JTextField();
		tf1.setBounds(12, 21, 42, 21);
		contentPane.add(tf1);
		tf1.setColumns(10);

		tf2 = new JTextField();
		tf2.setColumns(10);
		tf2.setBounds(84, 21, 42, 21);
		contentPane.add(tf2);

		tf3 = new JTextField();
		tf3.setColumns(10);
		tf3.setBounds(150, 21, 42, 21);
		contentPane.add(tf3);

		tf4 = new JTextField();
		tf4.setColumns(10);
		tf4.setBounds(215, 21, 42, 21);
		contentPane.add(tf4);

		tf5 = new JTextField();
		tf5.setColumns(10);
		tf5.setBounds(281, 21, 42, 21);
		contentPane.add(tf5);

		tf6 = new JTextField();
		tf6.setColumns(10);
		tf6.setBounds(352, 21, 42, 21);
		contentPane.add(tf6);

		JButton btn = new JButton("로또 생성하기");
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Random rd = new Random();
				int ranNum = rd.nextInt(46) + 1;

				int[] temp = new int[6];
				int[] rara = new int[6];
				for (int i = 0; i < 6; i++) {
					temp[i] = ranNum;
				}

				tf1.setText(Integer.toString(temp[0]));
				tf2.setText(Integer.toString(temp[1]));
				tf3.setText(Integer.toString(temp[2]));
				tf4.setText(Integer.toString(temp[3]));
				tf5.setText(Integer.toString(temp[4]));
				tf6.setText(Integer.toString(temp[5]));

			}
		});
		btn.setBounds(84, 123, 247, 23);
		contentPane.add(btn);
	}

}

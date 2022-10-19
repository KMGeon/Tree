package day04;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class MySwing09 extends JFrame {

	private JPanel contentPane;
	private JTextField lbl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MySwing09 frame = new MySwing09();
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
	public MySwing09() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 233, 206);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		String flag = "";
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lbl = new JTextField();

		lbl.setBounds(29, 0, 144, 21);
		contentPane.add(lbl);
		lbl.setColumns(10);

		JButton btn1 = new JButton("1");
		btn1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				flag(1);
			}
		});
		btn1.setBounds(29, 31, 40, 23);
		contentPane.add(btn1);

		JButton btn2 = new JButton("2");
		btn2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				flag(2);
			}
		});
		btn2.setBounds(81, 31, 40, 23);
		contentPane.add(btn2);

		JButton btn3 = new JButton("3");
		btn3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				flag(3);
			}
		});
		btn3.setBounds(133, 31, 40, 23);
		contentPane.add(btn3);

		JButton btn4 = new JButton("4");
		btn4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				flag(4);
			}
		});
		btn4.setBounds(29, 64, 40, 23);
		contentPane.add(btn4);

		JButton btn5 = new JButton("5");
		btn5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				flag(5);
			}
		});
		btn5.setBounds(81, 64, 40, 23);
		contentPane.add(btn5);

		JButton btn6 = new JButton("6");
		btn6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				flag(6);
			}
		});
		btn6.setBounds(133, 64, 40, 23);
		contentPane.add(btn6);

		JButton btnCall = new JButton("CALL");
		btnCall.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, temp);

			}
		});
		btnCall.setBounds(81, 129, 92, 23);
		contentPane.add(btnCall);

		JButton btn7 = new JButton("7");
		btn7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				flag(7);
			}
		});
		btn7.setBounds(29, 97, 40, 23);
		contentPane.add(btn7);

		JButton btn8 = new JButton("8");
		btn8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				flag(8);
			}
		});
		btn8.setBounds(81, 97, 40, 23);
		contentPane.add(btn8);

		JButton btn9 = new JButton("9");
		btn9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				flag(9);
			}
		});
		btn9.setBounds(133, 97, 40, 23);
		contentPane.add(btn9);

		JButton btn0 = new JButton("0");
		btn0.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				flag(0);

			}
		});
		btn0.setBounds(29, 129, 40, 23);
		contentPane.add(btn0);
	}

	String temp = "";

	public void flag(int num) {
		temp += Integer.toString(num);
		lbl.setText(temp);
	}

}

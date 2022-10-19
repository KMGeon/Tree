package day04;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class calculation extends JFrame {

	private JPanel contentPane;
	private JTextField tf1;
	private JTextField tf2;
	private JTextField tf3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					calculation frame = new calculation();
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
	public calculation() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		tf1 = new JTextField();
		tf1.setBounds(28, 29, 59, 21);
		contentPane.add(tf1);
		tf1.setColumns(10);

		tf2 = new JTextField();
		tf2.setColumns(10);
		tf2.setBounds(142, 29, 73, 21);
		contentPane.add(tf2);

		tf3 = new JTextField();
		tf3.setColumns(10);
		tf3.setBounds(314, 29, 84, 21);
		contentPane.add(tf3);

		JButton btnNewButton = new JButton("=");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String a = tf1.getText();
				String b = tf2.getText();
				int aa = Integer.parseInt(a);

			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(234, 28, 47, 23);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("+");
		lblNewLabel.setBounds(112, 32, 18, 15);
		contentPane.add(lblNewLabel);
	}
}

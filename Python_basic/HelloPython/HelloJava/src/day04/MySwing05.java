package day04;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class MySwing05 extends JFrame {

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
					MySwing05 frame = new MySwing05();
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
	public MySwing05() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblMine = new JLabel("나");
		lblMine.setBounds(26, 10, 57, 15);
		contentPane.add(lblMine);

		JLabel lblCom = new JLabel("컴");
		lblCom.setBounds(26, 51, 57, 15);
		contentPane.add(lblCom);

		JLabel lblResult = new JLabel("결과");
		lblResult.setBounds(26, 90, 57, 15);
		contentPane.add(lblResult);

		tf1 = new JTextField();
		tf1.setBounds(115, 7, 116, 21);
		contentPane.add(tf1);
		tf1.setColumns(10);

		tf2 = new JTextField();
		tf2.setColumns(10);
		tf2.setBounds(115, 48, 116, 21);
		contentPane.add(tf2);

		tf3 = new JTextField();
		tf3.setColumns(10);
		tf3.setBounds(115, 87, 116, 21);
		contentPane.add(tf3);

		JButton btn = new JButton("New button");
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Random rd = new Random();
				int ranNum = rd.nextInt(5) + 1;
				if (ranNum % 2 == 0) {
					tf2.setText("짝");
				} else {
					tf2.setText("홀");
				}

				if (tf1.equals("홀") && tf2.equals("홀")) {
					tf3.setText("비김");
				} else if (tf1.equals("홀") && tf2.equals("짝")) {
					tf3.setText("이김");
				} else if (tf1.equals("짝") && tf2.equals("홀")) {
					tf3.setText("이김");
				} else if (tf1.equals("짝") && tf2.equals("짝")) {
					tf3.setText("비김");
				} else {
					tf3.setText("짐");
				}
			}
		});
		btn.setBounds(107, 158, 97, 23);
		contentPane.add(btn);
	}

}

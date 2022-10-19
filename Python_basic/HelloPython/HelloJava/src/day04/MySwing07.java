package day04;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class MySwing07 extends JFrame {

	private JPanel contentPane;
	private JTextField tft1;
	private JTextField tft2;
	private JTextField tft3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MySwing07 frame = new MySwing07();
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
	public MySwing07() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("나");
		lblNewLabel.setBounds(12, 10, 57, 15);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("컴");
		lblNewLabel_1.setBounds(12, 74, 57, 15);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("결과");
		lblNewLabel_2.setBounds(12, 152, 57, 15);
		contentPane.add(lblNewLabel_2);

		JButton btn = new JButton("New button");
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Random rd = new Random();
				int ranNum = rd.nextInt(10) + 1;
				if (ranNum % 2 == 0) {
					tft2.setText("가위");
				} else if (ranNum % 3 == 0) {
					tft3.setText("바위");
				} else {
					tft3.setText("보");
				}

				String mine = tft1.getText();
				String result = "";
				if (mine.equals("가위") && tft2.equals("보") || mine.equals("바위") && tft2.equals("가위")
						|| mine.equals("보") && tft2.equals("바위")) {
					result = "승리";
				} else if (mine.equals("가위") && tft2.equals("바위") || mine.equals("바위") && tft2.equals("보")
						|| mine.equals("보") && tft2.equals("가위")) {
					result = "패배";
				} else {
					result = "비김";
				}

				if (result.equals("승리")) {
					tft3.setText("승리");
				} else if (result.equals("패배")) {
					tft3.setText("패배");
				} else if (result.equals("패배")) {
					tft3.setText("패배");
				} else {
					tft3.setText("잘못입력");
				}
			}
		});
		btn.setBounds(145, 212, 97, 23);
		contentPane.add(btn);

		tft1 = new JTextField();
		tft1.setBounds(126, 7, 116, 21);
		contentPane.add(tft1);
		tft1.setColumns(10);

		tft2 = new JTextField();
		tft2.setColumns(10);
		tft2.setBounds(126, 71, 116, 21);
		contentPane.add(tft2);

		tft3 = new JTextField();
		tft3.setColumns(10);
		tft3.setBounds(126, 149, 116, 21);
		contentPane.add(tft3);
	}

}

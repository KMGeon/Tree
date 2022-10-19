package day04;



import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class changeData extends JFrame {
	public changeData() {
	}

	  public static void main(String args[]) {
	        JFrame Demo_Frame = new JFrame("kimMugeon");
	        Demo_Frame.getContentPane().setLayout(new BorderLayout());
	        Demo_Frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	        Demo_Frame.setSize(500,200);

	        final JLabel J_Label = new JLabel("Good Morning");
	        JButton J_Button = new JButton("change data");
	        J_Button.setSize(100,100);
	        J_Button.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent arg0) {
	                J_Label.setText("change data");
	            }
	        });

	        Demo_Frame.getContentPane().add(J_Label, BorderLayout.NORTH);
	        Demo_Frame.getContentPane().add(J_Button, BorderLayout.CENTER);
	        Demo_Frame.setVisible(true);
	    }
	}
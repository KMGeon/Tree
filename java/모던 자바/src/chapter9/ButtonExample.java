package chapter9;

public class ButtonExample {

	public static void main(String[] args) {
		Button btn = new Button();
		
		btn.setOnClickListener(new Callistener());
		btn.touch();
		
		btn.setOnClickListener(new MessageListener());
		btn.touch();
		

	}

}

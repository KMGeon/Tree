package HighJava.src.HighJavaHomeWork;

import javax.swing.JOptionPane;

public class T07_ThreadGame {
    public static boolean inputCheck = false;

    public static void main(String[] args) {
        GameTimer gt = new GameTimer();



        String[] data = {"가위", "바위", "보"};
        int index = (int)(Math.random()*3);
        번  String com = data[index];


        String man = null;


        gt.start();

        do{
            man = JOptionPane.showInputDialog("가위, 바위, 보를 입력하세요");
        }while(!man.equals("가위") && !man.equals("바위") && !man.equals("보"));

        inputCheck = true;


        String result = "";
        if( man.equals(com) ){
            result = "비겼습니다.";
        }else if( (man.equals("가위") && com.equals("보"))
                || (man.equals("바위") && com.equals("가위"))
                || (man.equals("보") && com.equals("바위")) ){
            result = "당신이 이겼습니다.";
        }else{
            result = "당신이 졌습니다.";
        }

        // 결과 출력
        System.out.println("=== 결 과 ===");
        System.out.println("컴퓨터 : " + com);
        System.out.println("당  신 : " + man);
        System.out.println("결  과 : " + result);
    }
}

class GameTimer extends Thread{
    @Override
    public void run() {
        for(int i=5; i>=1; i--){
            if(T07_ThreadGame.inputCheck==true){
                return;
            }
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("시간이 초과되어 당신이 졌습니다.");
        System.exit(0);
    }
}
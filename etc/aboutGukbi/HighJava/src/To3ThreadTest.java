package Thread;

public class To3ThreadTest {
    public static void main(String[] args) {
        //스레드의 수행시간 체크해 보기
        Thread thread = new Thread(new MyRunner());
        //Utc(universal time coodinated 협정 세계 표준시)를 사용하여
        //1970년 1월 1일 0시0분 0초로 기준으로 경과한 시간을 밀리세컨드 단위로
        //나타낸다.

        long startTime = System.currentTimeMillis();

        thread.start(); // 스레드 구동하기
        try {
            thread.join(); // 현재 실행중인 스레드에서 작업중인 스레드가 종료될 때까지 기다린다.
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("경과시간: "+(endTime-startTime));

    }
}

class MyRunner implements Runnable {
    //1~1000000까지의 합계를 수하는 스레드 클래스
    @Override
    public void run() {
        long sum = 0;
        for (int i = 0; i < 1000000; i++) {
            sum += i;
        }
        System.out.println("합계: " + sum);
    }
}
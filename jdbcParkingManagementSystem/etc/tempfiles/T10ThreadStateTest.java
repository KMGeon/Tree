package HighJava.src.Thread;

import javax.print.attribute.standard.PrinterState;

public class T10ThreadStateTest {
    /*
    <스레드의 상태>
    -new : 스레드가 생성되고 아직 start()가 호출되지 않은 상태
    -runnable : 실행 중 또는 실행 가능한 상태
    -blocked : 동기화 블럭에 의해서 일시정지 된 상태 (lock이 풀릴때까지 기다리는 상태)
    waiting , timed_waiting : 스레드의 작업이 종료되지는 않았지만 실행가능하지 않은
    일시정지 상태 .(timed_waiting은 일시정지 시간이 지정된 경우임.)
    -Terminated : 스레드의 작업이 종료된 상태.
     */
    public static void main(String[] args) {
        TargetThread targetTh = new TargetThread();
        StatePrintThred spTh = new StatePrintThred(targetTh);
        spTh.start();
    }
}

//Target용 스레드
class TargetThread extends Thread {
    //new -> 실행대기 -> sleep ->실행대기 -> 실행 -> 끝
    @Override
    public void run() {
        for (long i = 1; i <= 1000000000L; i++) {
            //시간 지연용
        }
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (long i = 1; i <= 1000000000L; i++) {
            //시간 지연용
        }
    }
}

//스레드의 상태를 출력하기 위한 스레드 클래스
class StatePrintThred extends Thread {
    private Thread targetThread; //상태변화를 관찰할 스레드 저장 변수

    public StatePrintThred(Thread targetThread) {
        this.targetThread = targetThread;
    }

    @Override
    public void run() {
        while (true) {
            //스레드 상태 구하기 getstate()사용
            Thread.State state = targetThread.getState();
            System.out.println("타겟 스레드의 상태값: " + state);

            //New 상태인지 검사하여 스레드 구동시키기
            if (state == State.NEW) {
                targetThread.start();
            }
            //종료 상태인지 검사하여 무한루프 빠져가가기
            if (state == State.TERMINATED) {
                targetThread.start();
//                break;
            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
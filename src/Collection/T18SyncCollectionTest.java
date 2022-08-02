package HighJava.src.Collection;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class T18SyncCollectionTest {
    /*
         vector , hashtable등의 예전부터 존재하던 Collection 클래스들은 내부에 동기화 처리가 되어 있다.
         그런데 새로 구성된 컬렉션 클래스들은 동기화 처리가 되어있지 않다.
         그래서 동기화가 필요한 프로그램에서 이런 컬렉션 클래스들을 사용하려면 동기화 처리를 한 후에 사용해야 한다.
     */
    //동기화 하지 않을 경우
    private static List<Integer> list1 = new Vector<>();

    //동기화 처리를 하는 경우
    //collections의 정적 메서드 중에서 synchronized로 시작하는 메서드 이용
  //  private static List<Integer> list2 = (List<Integer>) Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) {
        Runnable run = new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10000; i++) {
                    list1.add(i);
                }
            }
        };
        Thread[] threads = new Thread[]{
                new Thread(run), new Thread(run), new Thread(run), new Thread(run), new Thread(run)
        };

        for (Thread thread : threads
        ) {
            thread.start();

        }

        for (Thread th : threads) {
            try {
                th.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(list1);
        System.out.println(list1);
    }
}

/*
이거를 한 이유 : 여러값을 map에 널고 싶음 
그러면 클래스vo하나 만들어서 map에 담기 그리고 쓰고 싶을 때 iterator를 이용하여 뽑기
*/

package GeonStudy;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class mapTest {
    public static void main(String[] args) {
        HashMap<Integer, Test> map = new HashMap<>();

        map.put(1, new Test("111","222","333"));
        //System.out.println(map);

        Set<Integer> set = map.keySet();
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            Integer it = iterator.next();

            Test value1 = map.get(it);

            System.out.println(value1.getN1()+":"+value1.getN2()+":"+value1.getN3());
        }


    }
}

class Test {
    private String n1;
    private String n2;
    private String n3;

    public Test(String n1, String n2, String n3) {
        this.n1 = n1;
        this.n2 = n2;
        this.n3 = n3;
    }

    public String getN1() {
        return n1;
    }

    public void setN1(String n1) {
        this.n1 = n1;
    }

    public String getN2() {
        return n2;
    }

    public void setN2(String n2) {
        this.n2 = n2;
    }

    public String getN3() {
        return n3;
    }

    public void setN3(String n3) {
        this.n3 = n3;
    }
}



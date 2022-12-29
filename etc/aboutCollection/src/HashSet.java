package HighJava.src.Collection;

import java.util.Iterator;
import java.util.Set;
/*
HashSet은 중복과 순서x
 */
public class HashSet {
    public static void main(String[] args) {
       //추가의 방법 1
        Object[] s1 = {"1",new Integer(2),"3","3","3","4","5"};
        Set set = new java.util.HashSet();

        for(int i=0;i<s1.length;i++){
            set.add(s1[i]);
        }
        System.out.println(set);

      //추가의 방법 2
        Set s2 = new java.util.HashSet();
        s2.add("1");
        s2.add("2");
        s2.add("3");
        System.out.println(s2);

        //remove는 인덱스 접근을 못하고 오브젝트로 접근이 가능하다.
        s2.remove("1");
        System.out.println(s2);

        //set의 데이터에 접근하기 위해 iterator로 접근이 가능하다. -> 객체 가져오기
        Iterator it = s2.iterator();


        Iterator IT = s2.iterator();
        while(it.hasNext()){
            System.out.print(it.next());
        }

    }
}

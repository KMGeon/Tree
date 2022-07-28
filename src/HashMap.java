package HighJava.src.Collection;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/*

 */
public class HashMap {
    public static void main(String[] args) {
        Map map = new java.util.HashMap();

        //자료의 추가 및 수정은  put을 사용한다.
        map.put("유재석", 1);
        map.put("송지효", 2);
        map.put("하하", 3);
        map.put("김종국", 4);
        System.out.println(map);

        //자료삭제는 remove를 사용하고 키를 이용하여 접근한다.
        map.remove("김종국");
        System.out.println(map);

        //자료읽기 get(key값)
        System.out.println(map.get("유재석"));

        //키값들을 읽어와 자료를 출력하는 방법
        //1.keyset()메서드 이용하여 맵의 키값들만 읽어와 set형으로 반환한다.
        Set<String> keyset = map.keySet();
        System.out.println("iterator를 이용한 방법");
        Iterator<String> it = keyset.iterator();
        while (it.hasNext()) {
            String key = it.next();
            System.out.println(key + ":" + map.get(key));
        }

        //2.for-each문 이용하여 데이터를 처리ㄱ
        System.out.println("for-each문 사용");
        for (String key:keyset)
        {
            System.out.println(key+":"+map.get(key));
        }
        // 방법4 ->Map관련 클래스에는 map,entry타입의 내부 클래스가 만들어져 있다.
        // 이 내부 클ㄹ래스는 키값과 value라는 멤버변수로 구성되어 있다.
        // map에는 이 map.entry타입의 객체들 set형으로 저장하여 관리한다.
        // Map.Entry타입의 객체 모두 가져오기 ->entrySet()이용하기

        System.out.println("가장 올바른 추출법");
        Set<Map.Entry<String,String>>entrySet = map.entrySet();
        for (Map.Entry<String,String > es:entrySet) {
            System.out.println(es.getKey());
            //sout es.getvalue
        }
    }
}

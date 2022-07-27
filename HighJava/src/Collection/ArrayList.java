package Collection;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
List는 순서 ㅇ 중복 ㅇ
추가 : add / 인덱스 위치의 값 반환 : get(index)
삭제 remove   /객체가 포함되어 있는지 확인 contain
 */
public class ArrayList<I extends Number> {
    public static void main(String[] args) {
        List list = new java.util.ArrayList(10);
        list.add(new Integer(5));
        list.add(new Integer(4));
        list.add(new Integer(3));
        list.add(new Integer(2));
        list.add(new Integer(1));

        List list2 = new java.util.ArrayList(list.subList(1, 4));
        System.out.println(list);
//        for (int i = 0; i < list.size(); i++) {
//            System.out.print(list.get(i));
//        }
        System.out.println(list2);
        Collections.sort(list);
        Collections.sort(list, new DescA());
        System.out.println(list.contains(list.get(1)));

        list.set(1,new Integer(1));
        System.out.println(list);

        list.remove(0);
        System.out.println("삭제"+list);

        list.clear();
        System.out.println(list);

        System.out.println(list2.contains(new Integer(4)));

        //indexOf(비교객체); 리스트에서 비교객체를 찾아 비교객체가 있는 index값을 반환한다. 있으면 0 없으면 -1
        System.out.println(list2.indexOf(4));

        //toArray() : 리스트 안의 데이터틀을 배열로 변환하여 반환한다. 기본적인 object형 배열로 반환한다.
        Object[] strArr = list2.toArray();
        System.out.println(strArr.length);
    }


}

class DescA implements Comparator<Integer> {

    @Override
    public int compare(Integer str1, Integer str2) {


        return str1.compareTo(str2) * -1;
    }

}
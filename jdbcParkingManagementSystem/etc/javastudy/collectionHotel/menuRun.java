package Collection_Hotel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

class menuRun {

    HashMap<String, HotelVO> hotelVo = new HashMap<>();


    public  void fistDisplay(){
        System.out.println("어떤 업무를 하시겠습니까?");
        System.out.println("1.체크인 2.체크아웃 3.객실상태 4.업무종료");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.print("메뉴선택 =>");
    }

    //1번 입력

    public void Checkin() {

        System.out.print("어느방에 체크인 하시겠습니까?");
        String roomNum = util.sc.next();
        if(hotelVo.get(roomNum)!=null) {
            System.out.println("방이 차 있습니다.");
            return ;
        }
        System.out.print("이름 입력=>");
        String name = util.sc.next();

        hotelVo.put(roomNum, new HotelVO(roomNum, name));
        System.out.println("체크인이 되었습니다.");
        System.out.println();

    }

    public void CheckOut() {
        System.out.println("어느방을 체크아웃 하시겠습니까");
        String num=util.sc.next();

        if(hotelVo.get(num)!=null){
            hotelVo.remove(num);
            System.out.println("체크아웃 하였습니다.");
        }else{
            System.out.println("비어있습니다.");
        }
    }
    public void RoomState() {
        Set<String> keySet = hotelVo.keySet();
        System.out.println("=====================================");
        System.out.println(" 번호\t객실번호\t\t이름 ");
        System.out.println("=====================================");

        if(keySet.size() == 0) {
            System.out.println("등록된 룸이 없습니다");
        } else {
            Iterator<String> it = keySet.iterator();

            int count = 0;
            while(it.hasNext()) {
                count++;
                String rNum = it.next();
                HotelVO h = hotelVo.get(rNum);
                System.out.println(" " + count + "번"+ "\t" +"방번호 : "+ h.getrNum() +"\t" + "투숙객 : "+ h.getName());

            }
        }
        System.out.println("================================");
    }

    public void end() {
        System.out.println("================================");
        System.out.println("        호텔 문을 닫습니다.");
        System.out.println("================================");

    }
}

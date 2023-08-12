package Collection_Hotel;

class checkMunu {

    void open() {
        System.out.println("========================");
        System.out.println("호텔 문을 열었습니다.");
        System.out.println("========================");
    }

    public static void display() {

        util util = new util();
        menuRun menuRun = new menuRun();
        while (true){
            menuRun.fistDisplay();
            int inputNum = util.sc.nextInt();
            switch (inputNum) {
                case 1:
                    menuRun.Checkin();
                    break ;
                case 2:
                    menuRun.CheckOut();
                    break;
                case 3:
                    menuRun.RoomState();
                    break;
                case 4:
                    menuRun.end();
                    break;

            }
        }
    }
}


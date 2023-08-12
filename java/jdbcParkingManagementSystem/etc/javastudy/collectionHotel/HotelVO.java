package Collection_Hotel;

class HotelVO{
    private String rNum;
    private String name;

    public HotelVO(String rNum, String name) {
        super();
        this.name = name;
        this.rNum = rNum;
    }

    public HotelVO() {

    }

    public String getrNum() {
        return rNum;
    }


    public void setrNum(String rNum) {
        this.rNum = rNum;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Hotel [rNum=" + rNum + ", name=" + name + "]";
    }


}

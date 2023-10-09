package singleton;

public class Settings {
    private static Settings instance;
    //    다른부분에서 new로 접근 불가
    private  Settings (){}

//    멀티쓰레드 환경에서는 synchronized를 하면 하나씩 접근이 가능하다
//    단점으로는 동기화를 해서 성능저하가 있다.
    public static  Settings getInstance(){
        if(instance==null){
            instance = new Settings();
        }
        return instance;
    }



}

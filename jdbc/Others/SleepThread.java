package Others;

public class SleepThread {
    public static void Loading(){
        for(double i=1; i<=35; i++){
            System.out.print(Font.BACKGROUND_BLUE + "■" + Font.RESET);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(" 100%");
    }
}
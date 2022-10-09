package object;

public class RecursivaCall {
    public static void main(String[] args) {
        Test test = new Test();
        int result = test.factorial(4);
        System.out.println(result);
    }
}
//재귀적 호출이란 일정 조선을 만족할 경우 자신을 호출하는 것을 말한다.
class Test{
    int factorial(int n){
        int result = 0;
        if(n==1)
            result=1;
        else
            result=n+factorial(n-1); //다시 메소드 호출
        return  result;
    }
}
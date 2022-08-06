package TCP;

import com.google.gson.Gson;

public class TCP14 {
    public static void main(String[] args) {
        Student st = new Student("김무건","13","1234",1);


//        Gson gson = new Gson();
//        String student = gson.toJson(st);
//        System.out.println(student);

        Gson gson = new Gson();
        String gson1 = gson.toJson(st);
        System.out.println(gson1);


    }
}
/*
1. java에서 제공하는 class들 . 이걸 api라고 한다.
 */


class Student {

    private String name;
    private String address;
    private String password;
    private int num;

    public Student() {
    }

    public Student(String name, String address, String password, int num) {
        this.name = name;
        this.address = address;
        this.password = password;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

//    @Override
//    public String toString() {
//        return "Student{" +
//                "name='" + name + '\'' +
//                ", address='" + address + '\'' +
//                ", password='" + password + '\'' +
//                ", num=" + num +
//                '}';
//    }
}
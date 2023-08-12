package HighJava.src.JavaIO;

import javafx.scene.Parent;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;

public class T16NonSerializableParentTest {
    /*
    부모클래스가 Serializable 인터페이스를 구현하고 있지 않을 경우
    부모객체의 필드값 처리 방법
    1.부모클래스가 Serializable 인터페이스를 구현하도록 해야 한다.

    2.자식클래스에 writeObject()와 readObject()메서드를 이용하여 부모객체의 필드값을 처리할 수 있도록 직접 구현한다.
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        String tempFile = "E:\\D_Others/NonSerialTest.bin";
        FileOutputStream fos = new FileOutputStream(tempFile);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        Child child = new Child();
        child.setParentName("부모");
        child.setChildName("자식");

        //직렬화
        oos.writeObject(child);
        oos.flush(); //생략가능
        oos.close();
        fos.close();//생략가능
//        final ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tempFile));
        final ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(tempFile)));
        Child child2 = (Child) ois.readObject();

        System.out.println("parentName: " + child2.getChildName());
        System.out.println("parentName: " + child2.getParentName());
        ois.close();
    }
}

//Serializable을 구현하지 않은 부모클래스스
class Parent1 {
    private String parentName;

    public Parent1() {

    }

    public Parent1(String parentName) {
        this.parentName = parentName;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}

//Serializable을 구현한 자식클래스
class Child extends Parent1 implements Serializable {
    private String childName;

    public Child() {
        super();

    }

    public Child(String parentName, String childName) {
        super(parentName);
        this.childName = childName;
    }


    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    /*
    직렬화 될 때 자동으로 호출됨(접근 제한자 private이 아니면 자동 호출되지 않음)
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeUTF(getParentName());
        out.defaultWriteObject();
    }

    /*
    역직렬화 될 때 자동으로 호출됨.
     */
    private void readObject(ObjectOutputStream in)throws IOException , ClassNotFoundException{
//        setParentName(in.readUTF());
        in.defaultWriteObject();
    }
}
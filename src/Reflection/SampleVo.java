package HighJava.src.Reflection;

import java.io.Serializable;

public class SampleVo implements Serializable, Comparable<String> {

    public String id;
    protected String name;
    private int age;

    public SampleVo() {

    }

    public SampleVo(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Deprecated
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(String o) {
        return 0;
    }

    @Override
    public String toString() {
        return "SampleVo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

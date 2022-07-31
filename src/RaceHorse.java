package ddit;//package ddit;

import java.util.*;

public class RaceHorse {

    static int strRank = 1;

    public static void main(String[] args) {
        String[] str = {"1번마","2번마","3번마","4번마","5번마","6번마","7번마","8번마","9번마","10번마"};
        List<String> list = new ArrayList<>(Arrays.asList(str));

        for(String str1 : list){

            Horse horse = new Horse();
            horse.setName1(str1);
            horse.start();

        }
        for (String str1 : list) {

            try {
                Horse horse = new Horse(str1);
                horse.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Collections.sort(list);
        System.out.println("경기끝 ....");
        System.out.println("======================================================");
        System.out.println();
        System.out.println(" 경기 결과 ");

        for(String str2:list){

            Horse horse = new Horse(str2);
            System.out.println(horse.getName1() + " " + horse.getRank() + "등");
        }

    }
}

class Horse extends Thread implements Comparable<Horse>{
    private String name1;
    int rank;

    public Horse(){

    }
    public Horse(String name) {
        this.name1 = name;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name) {
        this.name1 = name;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }


    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            System.out.println("\n" + name1 + " : ");
            for (int j = 0; j < i; j++) {
                System.out.print("-");
            }
            System.out.print(">");

            for (int j = 49; j > i; j--) {
                System.out.print("-");
            }

            System.out.println();
            System.out.println();
            System.out.println();

            try {
                Thread.sleep((int) (Math.random() * 500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println(name1 + " 끝");

        setRank(RaceHorse.strRank);
        RaceHorse.strRank++;
    }

    @Override
    public int compareTo(Horse hor) {
        if (rank > hor.getRank()) {
            return 1;
        } else {
            return -1;
        }
    }
}


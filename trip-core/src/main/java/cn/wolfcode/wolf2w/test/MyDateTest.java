package cn.wolfcode.wolf2w.test;

public class MyDateTest {
    public static void main(String[] args) {
        MyDate date1 = MyDate.DATE1;

        date1.setPrefix("miumiu");
        System.out.println(date1.getPrefix());
        System.out.println(date1.getTime());
        System.out.println(date1.join("mumu"));


    }
}

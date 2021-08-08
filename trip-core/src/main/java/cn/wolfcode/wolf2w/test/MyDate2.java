package cn.wolfcode.wolf2w.test;


import lombok.Getter;
import lombok.Setter;

/*
 * 枚举核心特点：
 *   1:枚举类构造器是私有的
 *  2：枚举类定义完成后，他的实例个数是固定的
 * 3：其他操作跟普通的类差不多
 * 	但只能够给普通属性加setter方法，因为枚举类不能够去改动它，所以setter注解不能够贴在枚举类上面
 * @date 2021/8/9 5:53
 */
@Getter
public class MyDate2 {
    public static final MyDate2 DATE1 = new MyDate2("date1", 1L);
    public static final MyDate2 DATE2 = new MyDate2("date2", 2L);

    //就等价于刚才的枚举类
    //  DATE1("date1",1L),DATE2("date2",2L);

    @Setter
    private String prefix;
    @Setter
    private Long time;

    private MyDate2(String prefix, Long time) {
        this.prefix = prefix;
        this.time = time;
    }

    private MyDate2() {

    }

    public String join(String value) {
        return value;
    }
}

package week_05.homework4.bytebuddy;

public class Biz {

    @Tag
    public int foo(int value) {
        System.out.println("foo: " + value);
        return value;
    }

    public int bar(int value) {
        System.out.println("bar: " + value);
        return value;
    }
}

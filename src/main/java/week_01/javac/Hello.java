package week_01.javac;

/**
 * 涉及基本类型,四则运算,if和for 分析字节码
 * load 局部变量加载到操作栈
 * store 数值从操作栈存储到局部变量表
 * ldc2_w 将一个常量加载到操作数栈
 * const 简单数值类型送到栈顶 iconst dconst lconst 前缀代表类型
 *
 * javap -verbose /Users/luweihong/work/ahaschhool/geek_training_camp/src/week_01/javac/Hello.class
 * @Author: luweihong
 * @Date: 2021/3/16
 *
 */
public class Hello {

    public static void main(String[] args) {
        int a = 1;
        // ldc2_w 大于255的常量 在局部变量表里占用2位
        double b = 2d;
        float c = 3f;

        //ldc 在局部变量表里占用2位
        long d = 4l;
        // short 类型 iconst 字节码里当做int
        short e = 5;

        //  getstatic 调用静态方法,load int a 时候还要 i2d 与 double相加,向上转型,调用dadd
        // i2d dadd
        System.out.println(a + b);
        // f2d dmul
        System.out.println(b * c);
        // l2f fdiv
        System.out.println(c / d);
        // isub
        System.out.println(e - a);

        for (int i = 0; i < 5; i++) {
            // if_icmpge
            if(i > 3){
                System.out.println(b + e);
            }
            // if_icmpge
            // return
        }
    }
}

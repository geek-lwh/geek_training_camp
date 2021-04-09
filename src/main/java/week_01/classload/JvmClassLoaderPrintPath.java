package week_01.classload;

import sun.misc.Launcher;

import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

/**
 *
 * bootstrap 主要负责加载rt.jar等核心
 * ext 负责加载jre/ext下的jar
 * app 加载应用自身的classload以及相关依赖
 *
 *
 * @Author: luweihong
 * @Date: 2021/3/16
 */
public class JvmClassLoaderPrintPath {

    public static void main(String[] args) {
        // 获取bootstrap加载器 主要负责加载rt.jar
        URL[] urls = Launcher.getBootstrapClassPath().getURLs();
        System.out.println("启动类加载器");

        for (URL url : urls) {
            System.out.println("====> " + url.toExternalForm());
        }

        printClassLoader("扩展类加载器",JvmClassLoaderPrintPath.class.getClassLoader().getParent());

        printClassLoader("应用类加载器",JvmClassLoaderPrintPath.class.getClassLoader());

    }

    public static void printClassLoader(String name, ClassLoader CL) {
        if (CL != null) {
            System.out.println(name + " ClassLoader -> " + CL.toString());
            printURLForClassLoader(CL);
        }else{
            System.out.println(name + " ClassLoader -> null");
        }
    }

    /**
     * 通过反射获取classLoad里的字段ucp对象,再通过反射获取ucp对象里的字段path
     * 输出path集合
     * @param CL
     */
    public static void printURLForClassLoader(ClassLoader CL) {
        Object ucp = insightField(CL, "ucp");
        Object path = insightField(ucp, "path");
        ArrayList ps = (ArrayList) path;
        for (Object p : ps) {
            System.out.println("===> " + p.toString());
        }
    }

    public static Object insightField(Object obj, String fName) {
        try {
            Field f = null;
            if (obj instanceof URLClassLoader) {
                f = URLClassLoader.class.getDeclaredField(fName);
            } else {
                f = obj.getClass().getDeclaredField(fName);
            }
            f.setAccessible(true);
            return f.get(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

package week_01_jvm.classload;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

/**
 * 必做）自定义一个 Classloader，加载一个 Hello.xlass 文件，执行 hello 方法，此文件内容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件。文件群里提供。
 * @Author: luweihong
 * @Date: 2021/3/16
 */
public class CustomClassLoader extends ClassLoader{

    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        CustomClassLoader c = new CustomClassLoader();
        Class<?> hello = new CustomClassLoader().findClass("Hello");
        Object obj = hello.newInstance();
        Method method = hello.getMethod("hello");
        method.invoke(obj);
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // x= 255-x
        try {
            byte[] c = getContent("Hello.xlass");
            dataConvert(c);
            return defineClass(name, c, 0, c.length);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return super.findClass(name);
    }

    /**
     * 解密
     * x = 255-x
     * @param c
     */
    private void dataConvert(byte[] c) {
        for(int i =0;i<c.length;i++){
            c[i] = (byte) (255-c[i]);
        }
    }

    /**
     * 读Hello.xlass文件到byte数组
     * @param filePath
     * @return
     * @throws IOException
     */
    public byte[] getContent(String filePath) throws IOException {
        File file = new File(filePath);
        long fileSize = file.length();
        if (fileSize > Integer.MAX_VALUE) {
            System.out.println("file too big...");
            return null;
        }

        FileInputStream fi = new FileInputStream(file);
        byte[] buffer = new byte[(int) fileSize];
        int offset = 0;
        int numRead;
        while (offset < buffer.length
                && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
            offset += numRead;
        }
        // 确保所有数据均被读取
        if (offset != buffer.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }
        fi.close();
        return buffer;
    }
}

import java.io.*;
import java.lang.reflect.Method;

/**
 * course1(jvm) hw2 自定义一个 Classloader，加载一个 Hello.xlass 文件，执行 hello 方法，此文件内容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件
 */
public class XlassLoader extends ClassLoader {

    private static final String CLASS_NAME = "Hello";

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String path = name.replace(".", "/");
        String suffix = ".xlass";
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(path + suffix);//记得添加后缀
        try {
            int len = is.available();
            byte[] bs = new byte[len];
            is.read(bs);
            byte[] resByte = decode(bs);
            return defineClass(name, resByte, 0, resByte.length);
        } catch (IOException e) {
            throw new ClassNotFoundException();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public byte[] decode(byte[] bs) throws IOException {
//        FileInputStream is = new FileInputStream("src/main/resources/Hello.xlass");
//        byte[] bs = new byte[is.available()];
        for (int i = 0; i < bs.length; i++) {
            bs[i] = (byte) (255 - bs[i]);
        }
        return bs;
    }

    public static void main(String[] args) throws Exception {
        String methodName = "hello";
        ClassLoader classLoader = new XlassLoader();
        Class<?> clazz = classLoader.loadClass(CLASS_NAME);//load class
        Object ins = clazz.getDeclaredConstructor().newInstance();//get class instance
        Method method = clazz.getMethod(methodName);//get method from class
        method.invoke(ins);//execute method
//        Object ins = new XlassLoader().findClass(CLASS_NAME).newInstance();
    }
}

package com.study.hua.work;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyClassLoader extends ClassLoader {

    private final static String PATH = "C:\\geektime\\JAVA-000\\Week_01\\jvm\\src\\main\\java\\com\\study\\hua\\work\\Hello.xlass";
    private final static String CLASS_NAME = "Hello";
    private final static String METHOD_NAME = "hello";

    public static void main(String[] args) {

        try {
            Class<?> aClass = new MyClassLoader().findClass(CLASS_NAME);
            Method helloMethod = aClass.getDeclaredMethod(METHOD_NAME);
            helloMethod.invoke(aClass.newInstance());
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) {
        byte[] bytes = getByteArray(PATH);
        return defineClass(name, bytes, 0, bytes.length);
    }

    private byte[] getByteArray(String path) {
        FileInputStream fio = null;
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ;
        try {
            fio = new FileInputStream(path);
            int len;
            int realCode;
            while ((len = fio.read()) != -1) {
                realCode = 255 - len;
                bao.write(realCode);
            }
            bao.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fio) {
                    fio.close();
                }
                bao.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bao.toByteArray();
    }

}

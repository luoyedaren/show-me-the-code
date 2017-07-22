package com.mine.spring.tools;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Properties;

/**
 * Created by Administrator on 2017/7/22.
 * <p>
 * 文件资源操作
 * <p>
 * Spring 定义了一个 org.springframework.core.io.Resource 接口，
 * Resource 接口是为了统一各种类型不同的资源而定义的，
 * Spring 提供了若干 Resource 接口的实现类，
 * 这些实现类可以轻松地加载不同类型的底层资源，并提供了获取文件名、URL 地址以及资源内容的操作方法
 * <p>
 * <p>
 * Web 开发者最常面对需要转义的特殊字符类型 StringEscapeutils 编码解码
 * <p>
 * JavaScriptUtils.javaScriptEscape(String str);
 * HtmlUtils.htmlEscape(String str);①转换为HTML转义字符表示
 * HtmlUtils.htmlEscapeDecimal(String str); ②转换为数据转义表示
 * HtmlUtils.htmlEscapeHex(String str); ③转换为十六进制数据转义表示
 * HtmlUtils.htmlUnescape(String str);将经过转义内容还原
 * <p>
 * <p>
 * 4.EncodedResource(Resource对象,”UTF-8″) 编码资源(特殊的);
 */
public class ResourceTool {

    public void test1() {
        /**
         *
         *  访问文件资源
         通过 FileSystemResource 以文件系统绝对路径的方式进行访问；
         通过 ClassPathResource 以类路径的方式进行访问；
         通过 ServletContextResource 以相对于 Web 应用根目录的方式进行访问。
         *
         *在获取资源后，您就可以通过 Resource 接口定义的多个方法访问文件的数据和其它的信息
         getFileName() 获取文件名，
         getFile() 获取资源对应的 File 对象，
         getInputStream() 直接获取文件的输入流。
         createRelative(String relativePath) 在资源相对地址上创建新的资源。
         *
         */
        try {
            String filePath =
                    "D:/masterSpring/chapter23/webapp/WEB-INF/classes/conf/file1.txt";
            // ① 使用系统文件路径方式加载文件
            Resource res1 = new FileSystemResource(filePath);
            // ② 使用类路径方式加载文件
            Resource res2 = new ClassPathResource("conf/file1.txt");
            InputStream ins1 = res1.getInputStream();
            InputStream ins2 = res2.getInputStream();
            System.out.println("res1:" + res1.getFilename());
            System.out.println("res2:" + res2.getFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static final void test2() {

        /**
         *
         *
         *
         *
         */

        try {
            File clsFile = ResourceUtils.getFile("classpath:conf/file1.txt");
            System.out.println(clsFile.isFile());
            String httpFilePath = "file:D:/masterSpring/chapter23/src/conf/file1.txt";
            File httpFile = ResourceUtils.getFile(httpFilePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * static void copy(byte[] in, File out)     将 byte[] 拷贝到一个文件中
     * static void copy(byte[] in, OutputStream out)     将 byte[] 拷贝到一个输出流中
     * static int copy(File in, File out)     将文件拷贝到另一个文件中
     * static int copy(InputStream in, OutputStream out)     将输入流拷贝到输出流中
     * static int copy(Reader in, Writer out)     将 Reader 读取的内容拷贝到 Writer 指向目标输出中
     * static void copy(String in, Writer out)     将字符串拷贝到一个 Writer 指向的目标中属性文件操作
     * Spring 提供的 PropertiesLoaderUtils 允许您直接通过基于类路径的文件 地址加载属性资源
     */

    public static final void copyFileTest() {
        try {
            Resource res = new ClassPathResource("conf/file1.txt");
            // ① 将文件内容拷贝到一个 byte[] 中
            byte[] fileData = FileCopyUtils.copyToByteArray(res.getFile());
            // ② 将文件内容拷贝到一个 String 中
            String fileStr = FileCopyUtils.copyToString(new FileReader(res.getFile()));
            // ③ 将文件内容拷贝到另一个目标文件
            FileCopyUtils.copy(res.getFile(),
                    new File(res.getFile().getParent() + "/file2.txt"));
            // ④ 将文件内容拷贝到一个输出流中
            OutputStream os = new ByteArrayOutputStream();
            FileCopyUtils.copy(res.getInputStream(), os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final void properitesTest() {
        // ① jdbc.properties 是位于类路径下的文件
        try {
            Properties props = PropertiesLoaderUtils.loadAllProperties("jdbc.properties");
            System.out.println(props.getProperty("jdbc.driverClassName"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

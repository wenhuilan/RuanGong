package utils;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TxtOperation {
    @Test
    public void writeTxtTest() {
        String content = "Hello, this is the content to be written to the file.";
        String filePath = "example.txt";
        try {
         //将字符串内容转换为字节数组
            byte[] contentBytes = content.getBytes();
            // 创建 Path 对象
            Path path = Paths.get(filePath);
            // 使用 Files 类写入文件
            Files.write(path, contentBytes);
            System.out.println("Content has been written to the file using Files class.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
@Test
public String readTxtTest(String filePath) {
    StringBuilder content = new StringBuilder();
    try {
        FileInputStream fin = new FileInputStream(filePath); // 打开文件输入流
        InputStreamReader reader = new InputStreamReader(fin); // 创建输入流读取器
        BufferedReader buffReader = new BufferedReader(reader); // 创建缓冲字符输入流
        String strTmp = "";
        while ((strTmp = buffReader.readLine()) != null) { // 逐行读取文件内容
            content.append(strTmp).append("\n"); // 将每行内容添加到StringBuilder中
        }
        buffReader.close(); // 关闭缓冲字符输入流
    } catch (IOException e) {
        e.printStackTrace(); // 捕获并打印IO异常
    }
    return content.toString(); // 将读取到的文本内容以字符串形式返回
}



}

package demo1;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class InResult {
    String[] createAnswer(String filePath) {
        ArrayList<String> lines = new ArrayList<>();
        File ans = new File("InAnswers.txt"); // 创建一个文件对象，表示答案文件
        // 如果答案文件不存在，则创建新文件
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            if(!ans.exists()) {
                ans.createNewFile();
            }
            FileOutputStream fosans = new FileOutputStream(ans); // 创建一个文件输出流，用于写入答案文件
            String line;
            Expression ex = new Expression(); // 创建一个表达式求值对象
            int i=0;
            while ((line = br.readLine()) != null) {
                lines.add(line); // 将每行数据添加到 ArrayList 中
                String[] parts = line.split("\\."); // 使用双反斜杠转义点号
                if (parts.length >= 2) {
                    System.out.println(Arrays.toString(parts));
                    String endLine = parts[1];
                    Fenshu ansresult = ex.count(endLine); // 求解表达式结果
                    String ansres = null;
                    if(ansresult.getDenominator() == 1) {
                        ansres = i+1 + ". " + ansresult.getNumerator() + "\r\n"; // 格式化整数答案
                    } else {
                        if(ansresult.getNumerator() > ansresult.getDenominator()) {
                            int fz = ansresult.getNumerator();
                            int fm = ansresult.getDenominator();
                            int mut = fz / fm;
                            int newfz = fz % fm;
                            ansres = i+1 + ". " + mut + "'" + newfz + "/" + fm + "\r\n"; // 格式化带分数答案
                        } else if(ansresult.getNumerator() == ansresult.getDenominator()) {
                            ansres = i+1 + ". " + "1" + "\r\n"; // 格式化整数答案为1
                        } else {
                            ansres = i+1 + ". " + ansresult.getNumerator() + "/" + ansresult.getDenominator() + "\r\n"; // 格式化真分数答案
                        }
                    }
                    i++;
                    byte[] ansprint = ansres.getBytes();
                    fosans.write(ansprint); // 写入答案文件
                } else {
                    System.out.println("Line does not contain the expected format: " + line);
                    break;
                }

            }


            return lines.toArray(new String[0]); // 将 ArrayList 转换为 String 数组并返回

        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }

        return new String[0]; // 如果发生异常，则返回空数组或者其他默认值
    }
}
package demo1;

import java.io.*;
import java.text.NumberFormat;
import java.util.Scanner;

public class CorrectandWrong {
    private Scanner scanner1;
    private Scanner scanner2;

    // 创建 CorrectandWrong 类的构造方法，用于处理试题和答案文件
    public static String processFiles(String Quefile, String Ansfile2) {

        try{
            System.out.println("请输入文件名: ");

            // 提示用户输入答案文件2
            System.out.println("请输入答案文件名: ");

            File quefile = new File(Quefile);
            File ansfile1 = new File("InAnswers.txt");
            File ansfile2 = new File(Ansfile2);
            // 检查答案文件1和答案文件2是否存在
            if (quefile.exists() && ansfile2.exists()) {
                // 给定的题目文件,对其生成计算结果，保存在InAnswers.txt中
                InResult inResult = new InResult();
                StringBuilder result = new StringBuilder();
                inResult.createAnswer(Quefile); // 定义一个字符串数组变量

                InputStreamReader anread1 = new InputStreamReader(new FileInputStream("InAnswers.txt"), "GB2312");
                BufferedReader anbr1 = new BufferedReader(anread1);

                InputStreamReader anread2 = new InputStreamReader(new FileInputStream(Ansfile2), "GB2312");
                BufferedReader anbr2 = new BufferedReader(anread2);

                String an1;
                String an2;

                int correctnum = 0;
                int totalQuestions = 0;
                StringBuffer correct = new StringBuffer();
                StringBuffer wrong = new StringBuffer();

                // 比较两个答案文件内容，统计正确的数量
                while ((an1 = anbr1.readLine()) != null && (an2 = anbr2.readLine()) != null) {
                    totalQuestions++;
                    if (an1.equals(an2)) {
                        correct.append(totalQuestions + " ");
                        correctnum++;
                    } else {
                        wrong.append(totalQuestions + " ");
                    }
                }

                // 输出正确答案和错误答案的数量及具体题号
                System.out.println("Correct Answers: " + correctnum + " ( " + correct + ")");
                System.out.println("Wrong Answers: " + (totalQuestions - correctnum) + " ( " + wrong + ")");
                result.append("Correct Answers: " + correctnum + " ( " + correct + ")");
                result.append("Wrong Answers: " + (totalQuestions - correctnum) + " ( " + wrong + ")");
                NumberFormat nt = NumberFormat.getPercentInstance();
                nt.setMinimumFractionDigits(2);
                double correctPercent = (double) correctnum / (double) totalQuestions;
                double wrongPercent = 1 - correctPercent;

                // 输出正确率和错误率
                System.out.println("Correct Rate: " + nt.format(correctPercent));
                System.out.println("Wrong Rate: " + nt.format(wrongPercent));
                result.append("Correct Rate: " + nt.format(correctPercent));
                result.append("Wrong Rate: " + nt.format(wrongPercent));
                anread1.close();
                anread2.close();
                anbr1.close();
                anbr2.close();
                return result.toString();
            } else {
                System.out.println("找不到指定文件!");
                return "找不到指定文件!";
            }
        } catch (FileNotFoundException e) {
        System.out.println("文件不存在: " + e.getMessage());
            return "文件不存在！ ";
        } catch (IOException e) {
            System.out.println("处理文件时出现IO异常: " + e.getMessage());
            return "处理文件时出现IO异常";
        } catch (Exception e) {
            System.out.println("发生异常" + e.getMessage());
            return "发生异常";
        }

    }
}
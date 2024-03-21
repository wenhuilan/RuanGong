package demo1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class build {
    @SuppressWarnings("resource")
    public static String generateExpressions(String scannernum, String scannersize) throws IOException {
//        Scanner scannernum; // 用于读取用户输入的题目数量和数值最大值的 Scanner 对象
//        Scanner scannersize;
        int num=0; // 存储题目数量
        int size=0; // 存储数值最大值
        FormExpression fe = new FormExpression(); // 创建一个表达式生成器对象
        Expression ex = new Expression(); // 创建一个表达式求值对象
        Random r = new Random(); // 创建一个随机数生成器对象
        File que = new File("Exercises.txt"); // 创建一个文件对象，表示题目文件
        File ans = new File("Answers.txt"); // 创建一个文件对象，表示答案文件

        // 如果题目文件不存在，则创建新文件
        if(!que.exists()) {
            que.createNewFile();
        }
        // 如果答案文件不存在，则创建新文件
        if(!ans.exists()) {
            ans.createNewFile();
        }
        FileOutputStream fosque = new FileOutputStream(que); // 创建一个文件输出流，用于写入题目文件
        FileOutputStream fosans = new FileOutputStream(ans); // 创建一个文件输出流，用于写入答案文件

        System.out.println("请输入要生成的题目数(命令形式为：-n 自然数)：");
//        scannernum = new Scanner(System.in);
        String[] strnum = scannernum.split(" ");

        if(strnum[0].equals("-n") && strnum.length == 2) { // 检查用户输入的命令格式是否正确
            if(Integer.parseInt(strnum[1]) >= 1) { // 检查题目数量是否大于等于1
                num = Integer.parseInt(strnum[1]);

                System.out.println("请输入题目中数值的最大值(命令形式为:-r 自然数):");
//                scannersize = new Scanner(System.in);
                String[] strsize = scannersize.split(" ");

                if(strsize[0].equals("-r") && strsize.length == 2) { // 检查用户输入的最大值命令格式是否正确
                    if(Integer.parseInt(strsize[1]) >= 2) { // 检查数值最大值是否大于等于2
                        size = Integer.parseInt(strsize[1]);

                        for(int i = 0; i < num; i++) {
                            int opernum = r.nextInt(3) + 1;
                            String res = fe.FormExpression(size, opernum); // 生成表达式
                            System.out.println(res);
                            String result = i+1 + "." + res +"\r\n"; // 格式化题目内容
                            byte[] print = result.getBytes();
                            System.out.println(print);
                            fosque.write(print); // 写入题目文件
                            Fenshu ansresult = ex.count(res); // 求解表达式结果
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
                            byte[] ansprint = ansres.getBytes();
                            fosans.write(ansprint); // 写入答案文件
                            System.out.println(res); // 打印题目
                        }
                        fosque.flush();
                        fosans.flush();
                        fosque.close();
                        fosans.close();
                    } else {

                        System.out.println("-r参数设置错误，请重新输入，-n参数必须大于等于2!");
                        return "-r参数设置错误，请重新输入，-n参数必须大于等于2!";
                    }
                } else {
                    System.out.println("-r命令输入错误，请重新输入!");
                    return "-r命令输入错误，请重新输入!";
                }
            } else {
                System.out.println("-n参数设置错误，请重新输入，-n参数必须大于等于1!");
                return "-n参数设置错误，请重新输入，-n参数必须大于等于1!";
            }
        } else {
            System.out.println("-n命令输入错误，请重新输入!");
            return "-n命令输入错误，请重新输入!";
        }
        return "成功随机生成四则运算表达式！";
    }
}
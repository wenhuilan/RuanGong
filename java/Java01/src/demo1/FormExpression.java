package demo1;

import java.util.Random;
//生成表达式
public class FormExpression {
    // 生成一个包含随机数和分数计算的表达式
    String FormExpression(int size , int operatenum) {
        Random r = new Random();
        String[] operate = {"+","-","×","÷"}; // 定义四则运算符
        StringBuffer str = new StringBuffer(); // 用于存储最终生成的表达式
        StringBuffer strnew = new StringBuffer();
        int num;
        int numerator;
        int denominator;
        String oper;
        Fenshu fenshu;
        int divcount = 0;
        String[] numlist = new String[operatenum+1];
        String[] operlist = new String[operatenum];
        // 随机生成运算符和数字
        for(int i=0;i<operatenum;i++) {
            oper=operate[r.nextInt(4)];
            operlist[i]=oper;
            if(oper.equals("÷")) {
                divcount++;
            }
        }

        // 生成随机数和分数
        for(int i=0;i<operatenum+1;i++) {
            if(divcount==0) {
                num=r.nextInt(size);
                numerator = r.nextInt(size);
                denominator = r.nextInt(size);
            }else {
                num=r.nextInt(size)+1;
                numerator = r.nextInt(size)+1;
                denominator = r.nextInt(size)+1;
            }
            if(denominator!=0&&numerator<=denominator) {
                fenshu = new Fenshu(numerator,denominator);
            }else {
                fenshu = new Fenshu(denominator, numerator);
            }
            numlist[i]=randominput(num, fenshu);
        }

        // 构建表达式
        for(int i=0;i<2*operatenum+1;i++) {
            if(i%2==0) {
                str.append(numlist[i/2]+" ");
            }
            if(i%2!=0) {
                str.append(operlist[(i-1)/2]+" ");
            }
        }
        str.append("="+" ");

        String[] judge = str.toString().split(" ");
        // 处理减法运算
        for(int i=1;i<judge.length-1;i+=2) {
            if(judge[i].equals("-")) {
                Fenshu fs = new Fenshu();
                Expression ex = new Expression();
                StringBuffer font = new StringBuffer();
                StringBuffer back = new StringBuffer();
                StringBuffer newstr = new StringBuffer();
                for(int k=0;k<i;k++) {
                    font.append(judge[k]+" ");
                }
                for(int k=i+1;k<judge.length-1;k++) {
                    back.append(judge[k]+" ");
                }
                Fenshu fontfs = ex.count(font.toString());
                Fenshu backfs = ex.count(back.toString());
                String fontstr = fontfs.numerator+"/"+fontfs.denominator;
                String backstr = backfs.numerator+"/"+backfs.denominator;
                if(fs.compute(fontstr, backstr)) {
                    newstr.append(back.toString()+"- "+font.toString()+"= ");
                    String[] newjudge = newstr.toString().split(" ");
                    for(int k=0;k<newjudge.length;k++) {
                        judge[k]=newjudge[k];
                    }
                }
            }
        }
        // 将处理完减法运算的表达式转换为字符串并返回
        for(int i=0;i<judge.length;i++) {
            strnew.append(judge[i]+" ");
        }
        return strnew.toString();
    }

    // 随机选择输入数字或分数
    String randominput(int num,Fenshu fenshu) {
        String numstr = num+"";
        String fenshustr = fenshu.getNumerator() +"/"+fenshu.getDenominator();
        String[] strlist = {numstr , fenshustr};
        Random r = new Random();
        return strlist[r.nextInt(2)].toString();
    }
}
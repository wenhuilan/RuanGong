package demo1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Main extends JFrame {
    public Main() {
        super("四则运算题目生成程序");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建面板
        JPanel panel = new JPanel(){
            // 创建渐变背景色
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                // 设置渐变色
                GradientPaint gradient = new GradientPaint(0, 0, Color.decode("#84fab0"), getWidth(), getHeight(), Color.decode("#8fd3f4"));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setBackground(Color.BLUE);
        getContentPane().add(panel);
        placeComponents(panel);

        setLocationRelativeTo(null);
    }
    // 新建一个继承自 JPanel 的类，用于绘制渐变背景

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);
        JLabel titleLabel = new JLabel("欢迎使用该程序！");
        // 设置字体为粗体，大小为 32
        Font font = titleLabel.getFont().deriveFont(Font.BOLD, 38f);
        titleLabel.setFont(font);
        titleLabel.setBounds((getWidth()-500)/2, 20, 500, 50);
        panel.add(titleLabel);
        Font font1 = titleLabel.getFont().deriveFont(Font.BOLD, 22f);
        JButton generateButton = new JButton("生成四则运算题目");
        generateButton.setBounds((getWidth()-400)/2-220, 80, 400, 60);
        generateButton.setFont(font);
        panel.add(generateButton);
        JButton generateButton1 = new JButton("测试答案");
        generateButton1.setBounds((getWidth()-400)/2+220, 80, 400, 60);
        generateButton1.setFont(font);
        panel.add(generateButton1);

        JTextArea resultArea = new JTextArea();
        resultArea.setBounds((getWidth()-800)/2, 180, 800, 500);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBounds((getWidth()-800)/2, 180, 800, 500);
        panel.add(scrollPane);
        // 设置字体大小和颜色
        resultArea.setFont(new Font("Arial", Font.PLAIN, 32)); // 应用字体设置
        resultArea.setForeground(Color.BLACK); // 设置字体颜色为黑色
        panel.add(resultArea);

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String scannernum = InputDialog.showCustomInputDialog("请输入要生成的题目数(命令形式为：-n 自然数)：");
                String scannersize = InputDialog.showCustomInputDialog("请输入题目中数值的最大值(命令形式为:-r 自然数)");
                try {
                    String result = build.generateExpressions(scannernum, scannersize);
                    if (result == "成功随机生成四则运算表达式！"){
                        JOptionPane.showMessageDialog(null, result);
                        File ansfile1 = new File("Exercises.txt");
                        InputStreamReader anread2 = new InputStreamReader(new FileInputStream(ansfile1), "UTF-8");
                        BufferedReader anbr2 = new BufferedReader(anread2);

                        StringBuilder content = new StringBuilder();
                        String line;
                        while ((line = anbr2.readLine()) != null) {
                            content.append(line).append("\n"); // 逐行读取并拼接内容
                        }

                        // 将读取的内容设置到 JTextArea 中显示
                        resultArea.setText(content.toString());

                        // 关闭 BufferedReader
                        anbr2.close();

                    }else {
                        JOptionPane.showMessageDialog(null, result);
                    }

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        generateButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String QueTxt = InputDialog.showCustomInputDialog("请输入文件名：");
//                String QueTxt = JOptionPane.showInputDialog(null, "请输入文件名：");
                // 在这里可以将用户输入的答案与正确答案进行比较或其他操作
                System.out.println(QueTxt);
                if (QueTxt!=null){
                    String CeshiTxt = InputDialog.showCustomInputDialog( "请输入测试答案文件：");
                    System.out.println(CeshiTxt);
                    if (CeshiTxt == null){
                        JOptionPane.showMessageDialog(null, "输入的测试答案文件为空！");
                    }
                    CorrectandWrong correctandWrong = new CorrectandWrong(); // 创建 CorrectandWrong 类的实例
                    String result = correctandWrong.processFiles(QueTxt, CeshiTxt); // 调用 processFiles 方法并获取返回结果
                    // 判断result是否包含字符串"Correct"
                    if (result.contains("Correct")) {
                        System.out.println("结果中包含 'Correct' 字符串");
                        System.out.println(result);
                        resultArea.setText(result);
                    } else {
                        System.out.println("结果中不包含 'Correct' 字符串");
                        JOptionPane.showMessageDialog(null, result);
                    }

                }else{
                    JOptionPane.showMessageDialog(null, "输入的文件名为空！");
                }
            }
        });
    }



    public static void main(String[] args) {
        Main frame = new Main();
        frame.setVisible(true);
    }
}
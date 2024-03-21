package demo1;

import javax.swing.*;
import java.awt.*;

public class InputDialog {
    // 显示自定义输入对话框并返回用户输入的字符串
    public static String showCustomInputDialog(String title) {
        // 创建一个具有较大字体大小的文本框
        JTextField textField = new JTextField(30);
        Font font = new Font("宋体", Font.PLAIN, 26); // 设置字体为宋体，大小为26
        textField.setFont(font);

        // 创建一个面板并添加文本框
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1));
        panel.add(textField);

        // 创建一个 JOptionPane 实例并设置自定义面板
        JOptionPane optionPane = new JOptionPane(panel, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
        JDialog dialog = optionPane.createDialog(title);

        // 设置对话框大小
        dialog.setPreferredSize(new Dimension(650, 180));
        dialog.pack();

        // 获取屏幕尺寸
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((screenSize.getWidth() - dialog.getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - dialog.getHeight()) / 2);

        // 将对话框位置设置为屏幕中心
        dialog.setLocation(x, y);

        dialog.setVisible(true);

        // 处理用户输入
        Object value = optionPane.getValue();
        if (value instanceof Integer && ((Integer)value).intValue() == JOptionPane.OK_OPTION) {
            return textField.getText();
        } else {
            return null;
        }
    }
}
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
/*
 * Created by JFormDesigner on Thu May 30 10:48:42 CST 2019
 */



/**
 * @author unknown
 */
public class 聊天客户端 extends JFrame {
    public 聊天客户端() {
        initComponents();
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - marno
        idf = new JTextField();
        labelid = new JLabel();
        labelpass = new JLabel();
        login = new JButton();
        pasf = new JPasswordField();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("\u5728\u7ebf\u804a\u5929-\u767b\u5f55");
        setBackground(new Color(39, 50, 56));
        setResizable(false);
        Container contentPane = getContentPane();

        //---- idf ----
        idf.setHorizontalAlignment(SwingConstants.CENTER);

        //---- labelid ----
        labelid.setText("Account ID");
        labelid.setHorizontalAlignment(SwingConstants.CENTER);
        labelid.setLabelFor(idf);
        labelid.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));

        //---- labelpass ----
        labelpass.setText("Password");
        labelpass.setHorizontalAlignment(SwingConstants.CENTER);
        labelpass.setLabelFor(pasf);
        labelpass.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));

        //---- login ----
        login.setText("Login Now");
        login.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 36));
        login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loginMouseClicked(e);
            }
        });

        //---- pasf ----
        pasf.setHorizontalAlignment(SwingConstants.CENTER);

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                .addContainerGap(68, Short.MAX_VALUE)
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(labelid, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelpass, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(48, 48, 48)
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(pasf)
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addComponent(idf, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(64, 64, 64))
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                .addContainerGap(45, Short.MAX_VALUE)
                                .addComponent(login, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38))
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelid, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(idf, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelpass, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(pasf, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
                                .addGap(42, 42, 42)
                                .addComponent(login, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25))
        );
        pack();
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - marno
    private JTextField idf;
    private JLabel labelid;
    private JLabel labelpass;
    private JButton login;
    private JPasswordField pasf;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    public void login(String id,String pas){
        try {
            System.out.println("尝试与服务器建立连接...");
            Socket sck=new Socket("13.231.224.26",2000);
            System.out.println("新建登录报文...");
            InetAddress addr = InetAddress.getLocalHost();
            String content="1@login@"+id+"@"+pas+"@"+ addr.getHostAddress();
            System.out.println(content);
            PrintWriter cout = new PrintWriter(sck.getOutputStream(),true);
            cout.println(content);
            BufferedReader br = new BufferedReader(new InputStreamReader(sck.getInputStream()));
            String backmsg = br.readLine();
            System.out.println(backmsg);
            List<String> todoing = cutstring(backmsg);
            System.out.println(todoing);
            if(todoing.get(0).equals("success")){
                System.out.println("登陆成功，打开好友面板");
                listfrom ls = new listfrom(id);
                setVisible(false);
                ls.setVisible(true);
            }else {
                System.out.println("登陆失败-"+todoing.get(1));
                JOptionPane.showMessageDialog(null, "密码错误，请重新输入", "X错密码X", JOptionPane.ERROR_MESSAGE);
                pasf.setText("");
            }
            sck.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    static List<String> cutstring(String Stence) {
        List<String> stringlist = new ArrayList<String>();//用来存储解析出来的元素
        for (int i = 0; i < Stence.length(); i++) {
            if (Stence.charAt(i) == '@') {
                String temp = "";//存储单词
                int wordlength = i;
                while (wordlength < Stence.length() - 1 && Stence.charAt(++wordlength) != '@') {
                    temp += Stence.charAt(wordlength);
                    //System.out.println(temp);
                }
                stringlist.add(temp);
            }
        }
        return stringlist;
    }

    private void loginMouseClicked(MouseEvent e) {
        String id = idf.getText();
        String pas = String.valueOf(pasf.getPassword());
        System.out.println(id+pas);
        if(id.equals("")){
            JOptionPane.showMessageDialog(null, "请输入账号", "X空账号X", JOptionPane.ERROR_MESSAGE);
        }else if(pas.equals("")){
            JOptionPane.showMessageDialog(null, "请输入密码", "X空密码X", JOptionPane.ERROR_MESSAGE);
        }else login(id,pas);
    }

    public static void main(String args[]){
        聊天客户端 as = new 聊天客户端();
        as.setVisible(true);
    }
}

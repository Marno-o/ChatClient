import com.sun.org.apache.bcel.internal.generic.DCMPG;

import java.awt.*;
import java.awt.event.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.swing.*;
import javax.swing.GroupLayout;
/*
 * Created by JFormDesigner on Wed Jun 05 18:50:31 CST 2019
 */


/**
 * @author marno
 */
public class diaf extends JFrame {
    public diaf(String ip, String name, String hostnam) throws Exception {
        System.out.println("开始工作");
        initComponents(ip,name);
        hostname = hostnam;
        ip1 = ip;
        System.out.println("发出聊天请求");
        link(ip, name);

    }

    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here
        try {
            desip = InetAddress.getByName(ip1);
            byte[] buffer = (hostname + "说： " + textField1.getText()).getBytes();
            System.out.println("disip:"+desip);
            DatagramPacket pack = new DatagramPacket(buffer, buffer.length, desip, 3003);
            DatagramSocket dsock = new DatagramSocket();
            dsock.send(pack);
            textArea1.append(hostname + "说： " + textField1.getText() + "\n");
            textField1.setText("");
            System.out.println("ddfsafasda");
        } catch (Exception ew) {
            ew.printStackTrace();
        }
    }

    private void initComponents(String ip, String name) {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - marno
        textField1 = new JTextField();
        button1 = new JButton();
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextArea();

        //======== this ========
        setResizable(false);
        setTitle(name + "(" + ip + ")");
        addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                try {
                    desip = InetAddress.getByName(ip);
                    byte[] buffer = (hostname + "已离开\n").getBytes();
                    DatagramPacket pack = new DatagramPacket(buffer, buffer.length, desip, 3003);
                    DatagramSocket dsock = new DatagramSocket(3001);
                    dsock.send(pack);
                } catch (Exception ew) {
                    ew.printStackTrace();
                }
            }
        } );
        Container contentPane = getContentPane();

        //---- textField1 ----
        textField1.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));

        //---- button1 ----
        button1.setText("SEND");
        button1.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 26));
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button1MouseClicked(e);
            }
        });

        //======== scrollPane1 ========
        {

            //---- textArea1 ----
            textArea1.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
            scrollPane1.setViewportView(textArea1);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 315, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(button1, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)))
                                .addGap(12, 12, 12))
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button1, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - marno
    private JTextField textField1;
    private JButton button1;
    private JScrollPane scrollPane1;
    private JTextArea textArea1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    private String hostname;
    private InetAddress desip;
    private int destport;
    private String ip1;


    private void link(String ip, String name) {
        try {
            textArea1.append("正在与ip:" +ip+ name + "建立连接...\n");
            desip = InetAddress.getByName(ip);
            destport = 3003;//统一目标接收端3002，本机发送端3001
            byte[] buffer = ("你好" + hostname + "正在与你聊天").getBytes();
            DatagramPacket pack = new DatagramPacket(buffer, buffer.length, desip, destport);
            DatagramSocket dsock = new DatagramSocket();
            dsock.send(pack);
            textArea1.append("连接成功！可以开始聊天啦~\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void receive()throws  Exception {
        System.out.println("创建接受");
        byte[] data = new byte[512];
        DatagramPacket pack = new DatagramPacket(data, data.length);
        System.out.println("//监听3005端口，本地程序监听需更改");
        DatagramSocket dsock = new DatagramSocket(3002);//监听3005端口，本地程序监听需更改
        System.out.println("当接收到。。。");
        System.out.println("当接收到。。。"+dsock);

        while (dsock != null) {
            System.out.println("1");
            dsock.receive(pack);
            System.out.println("222:"+dsock.getInetAddress());
            textArea1.append(new String(pack.getData(), 0, pack.getLength()) + "\n");
            System.out.println("3");
        }
        System.out.println("完成");
    }

    public static void main(String args[])throws Exception {
        diaf di = new diaf("10.191.17.151", "name", "hostname");
        di.setVisible(true);
        di.receive();
    }


}

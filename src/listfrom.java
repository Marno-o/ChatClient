import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import javax.swing.GroupLayout;
/*
 * Created by JFormDesigner on Tue Jun 04 20:51:42 CST 2019
 */


/**
 * @author marno
 */
public class listfrom extends JFrame {
    public listfrom(String id) {
        sqlse1 = new sqlset();
        username = sqlse1.username(id);
        initComponents(id);
    }

    private void initComponents(String id) {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - marno
        label1 = new username(id);
        label2 = new ipid(id);
        label3 = new headpic();
        tabbedPane1 = new JTabbedPane();
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        list1 = new online(id);
        panel2 = new JPanel();
        scrollPane2 = new JScrollPane();
        list2 = new outline(id);

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("\u804a");
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                close(id);
            }
        });
        Container contentPane = getContentPane();

        //======== tabbedPane1 ========
        {

            //======== panel1 ========
            {

                // JFormDesigner evaluation mark
                panel1.setBorder(new javax.swing.border.CompoundBorder(
                        new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                                java.awt.Color.red), panel1.getBorder()));
                panel1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                    public void propertyChange(java.beans.PropertyChangeEvent e) {
                        if ("border".equals(e.getPropertyName())) throw new RuntimeException();
                    }
                });


                //======== scrollPane1 ========
                {
                    scrollPane1.setViewportView(list1);
                }

                GroupLayout panel1Layout = new GroupLayout(panel1);
                panel1.setLayout(panel1Layout);
                panel1Layout.setHorizontalGroup(
                        panel1Layout.createParallelGroup()
                                .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                );
                panel1Layout.setVerticalGroup(
                        panel1Layout.createParallelGroup()
                                .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                );
            }
            tabbedPane1.addTab("\u5728\u7ebf\u597d\u53cb", panel1);

            //======== panel2 ========
            {

                //======== scrollPane2 ========
                {
                    scrollPane2.setViewportView(list2);
                }

                GroupLayout panel2Layout = new GroupLayout(panel2);
                panel2.setLayout(panel2Layout);
                panel2Layout.setHorizontalGroup(
                        panel2Layout.createParallelGroup()
                                .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                );
                panel2Layout.setVerticalGroup(
                        panel2Layout.createParallelGroup()
                                .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                );
            }
            tabbedPane1.addTab("\u79bb\u7ebf\u597d\u53cb", panel2);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(label3, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(tabbedPane1)
                                .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addComponent(label1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(18, 18, 18)
                                                .addComponent(label2, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addComponent(label3, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tabbedPane1, GroupLayout.PREFERRED_SIZE, 377, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - marno
    private username label1;
    private ipid label2;
    private headpic label3;
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JScrollPane scrollPane1;
    private online list1;
    private JPanel panel2;
    private JScrollPane scrollPane2;
    private outline list2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    private sqlset sqlse1;
    private DefaultListModel onl;
    private DefaultListModel outl;
    private String username;

    private class outline extends JList {
        private outline(String id) {
            initComponents();
            Timer y = new Timer();
            y.schedule(new TimerTask() {
                @Override
                public void run() {

                    System.out.println("读取离线列表");
                    setModel(getOut(id));
                }
            }, 1000, 3000);
        }

        private void initComponents() {
            // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
            // Generated using JFormDesigner Evaluation license - marno
            // JFormDesigner - End of component initialization  //GEN-END:initComponents
        }

        // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
        // Generated using JFormDesigner Evaluation license - marno
        // JFormDesigner - End of variables declaration  //GEN-END:variables
    }

    private class online extends JList {
        private online(String id) {
            initComponents();

            Timer u = new Timer();
            u.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("读取在线列表");
                    setModel(getOnl(id));
                }
            }, 0, 3000);

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (getSelectedIndex() != -1) {
                        if (e.getClickCount() == 2) {
                            try {
                                twoClick(getip(getSelectedValue().toString()),getname(getSelectedValue().toString()),username);
                            }catch (Exception eq ){
                                eq.printStackTrace();
                            }
                        }
                    }
                }
            });
        }

        private String getip(String li) {
            String ipp = "";
            for (int i = 0; i < li.length(); i++) {
                if (li.charAt(i) == '(') {
                    int wordlength = i;
                    while (li.charAt(++wordlength) != ')') {
                        ipp += li.charAt(wordlength);
                        //System.out.println(temp);
                    }
                }
            }
            System.out.println("ipp的值" + ipp);
            return ipp;
        }

        private String getname(String li) {
            String name = "";
            for (int i = 0; i < li.length(); i++) {
                if (li.charAt(i) == ':') {
                    int wordlength = i;
                    while (li.charAt(++wordlength) != ' ') {
                        name += li.charAt(wordlength);
                        //System.out.println(temp);
                    }
                }
            }
            System.out.println("ipp的值" + name);
            return name;
        }


        private void initComponents() {
            // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
            // Generated using JFormDesigner Evaluation license - marno
            // JFormDesigner - End of component initialization  //GEN-END:initComponents
        }

        // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
        // Generated using JFormDesigner Evaluation license - marno
        // JFormDesigner - End of variables declaration  //GEN-END:variables
    }

    private class headpic extends JLabel {
        private headpic() {
            initComponents();
            Icon icon = new ImageIcon("src/pic/hp1.jpg");
            setIcon(icon);//添加图片
            setSize(110, 110);
        }

        private void initComponents() {
            // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
            // Generated using JFormDesigner Evaluation license - marno
            setText("text");
            // JFormDesigner - End of component initialization  //GEN-END:initComponents
        }

        // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
        // Generated using JFormDesigner Evaluation license - marno
        // JFormDesigner - End of variables declaration  //GEN-END:variables
    }

    private class username extends JLabel {
        private username(String id) {
            initComponents();
            setText(username);
            setFont(new Font("Microsoft YaHei UI", Font.BOLD, 26));
        }

        private void initComponents() {
            // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
            // Generated using JFormDesigner Evaluation license - marno
            setText("text");
            // JFormDesigner - End of component initialization  //GEN-END:initComponents
        }

        // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
        // Generated using JFormDesigner Evaluation license - marno
        // JFormDesigner - End of variables declaration  //GEN-END:variables
    }

    private class ipid extends JLabel {
        private ipid(String id) {
            initComponents();
            try {
                InetAddress addr = InetAddress.getLocalHost();
                setText("ID:" + id + "  IP:" + addr.getHostAddress());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void initComponents() {
            // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
            // Generated using JFormDesigner Evaluation license - marno
            setText("text");
            // JFormDesigner - End of component initialization  //GEN-END:initComponents
        }

        // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
        // Generated using JFormDesigner Evaluation license - marno
        // JFormDesigner - End of variables declaration  //GEN-END:variables
    }

    private DefaultListModel getOnl(String id) {
        onl = new DefaultListModel();
        String[][] de = sqlse1.dataq(id);
        for (int i = 0; i < de.length; i++) {
            if (de[i][2].equals("1")) {
                onl.addElement(i + 1 + " :" + de[i][0] + "   (" + de[i][1] + ")");
                System.out.println(de[i][0] + "   ip:" + de[i][1]);
                System.out.println(onl);
            }
        }
        return onl;
    }

    private DefaultListModel getOut(String id) {
        outl = new DefaultListModel();
        String[][] dee = sqlse1.dataq(id);
        for (int i = 0; i < dee.length; i++) {
            if (dee[i][2].equals("0")) {
                outl.addElement(dee[i][0] + "   ip:" + dee[i][1]);
                System.out.println(dee[i][0] + "   ip:" + dee[i][1]);
                System.out.println(outl);
            }
        }
        return outl;
    }

    private void twoClick(String ip, String name, String hostname)throws Exception {
        diaf nam = new diaf(ip, name, hostname);
        nam.setVisible(true);
        Timer u = new Timer();
        u.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    nam.receive();
                }catch (Exception s){
                    s.printStackTrace();
                }
            }
        }, 1000, 100000000);
    }

    private void close(String id) {
        try {
            System.out.println("尝试与服务器建立连接...");
            Socket sck = new Socket("13.231.224.26", 2000);
            System.out.println("新建离线报文...");
            String content = "@off@" + id;
            System.out.println(content);
            PrintWriter cout = new PrintWriter(sck.getOutputStream(), true);
            cout.println(content);
            sck.close();
            System.out.println("再见");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

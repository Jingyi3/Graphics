package lab3_2;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import javax.swing.*;

public class Casteljau extends Frame implements WindowListener, ComponentListener, MouseListener, ActionListener {//polygon change
    private double t = 0.0, ts = 0.0, pyX = 0.0, pyY = 0.0, sfX = 0.0, sfY = 0.0;
    private String pingyi, suofang;
    private int xz = 0;
    private double px[][] = new double[999][999];
    private double py[][] = new double[999][999];
    private int i = 0, j = 0, k, x, y, sum = 0;
    private Button button_Caste, button_Clear, button_OK, button_Py, button_Xz, button_Sf;
    private int flag1 = 0, flag2 = 0, flag3 = 0, flag_lab1 = 0, flag_fs = 0;
    private Dialog dialog;
    private Label label_dialog, label, label2, label3, label4;
    private TextField text, text2, text3, text4;
    private JPanel jPanel1, jPanel2;

    public Casteljau() {//initialize windows
        super("Bezier曲线(de Casteljau)算法");
        this.setSize(800, 600);
        this.setLocation(250, 100);
        this.setLayout(new FlowLayout());//windows mediacy
        this.setBackground(Color.lightGray);


        button_Caste = new Button("Casteljau");
        this.add(button_Caste);
        button_Caste.addActionListener(this);

        button_Clear = new Button("Clear");
        this.add(button_Clear);
        button_Clear.addActionListener(this);

        dialog = new Dialog(this, "错误", true);
        dialog.setSize(240, 80);
        label_dialog = new Label("没有给定位置矢量", Label.CENTER);
        dialog.add(label_dialog);
        dialog.addWindowListener(this);

        label = new Label("t比例");
        this.add(label);

        text = new TextField(10);
        text.setEditable(true);
        this.add(text);

        button_OK = new Button("OK");
        this.add(button_OK);
        button_OK.addActionListener(this);

        label = new Label("平移");
        this.add(label);
        text2 = new TextField(10);
        text2.setEditable(true);
        this.add(text2);
        button_Py = new Button("平移");
        this.add(button_Py);
        button_Py.addActionListener(this);


        label = new Label("旋转");
        this.add(label);
        text3 = new TextField(10);
        text3.setEditable(true);
        this.add(text3);
        button_Xz = new Button("旋转");
        this.add(button_Xz);
        button_Xz.addActionListener(this);

        label = new Label("缩放");
        this.add(label);
        text4 = new TextField(10);
        text4.setEditable(true);
        this.add(text4);
        button_Sf = new Button("缩放");
        this.add(button_Sf);
        button_Sf.addActionListener(this);

        this.addComponentListener(this);
        this.addWindowListener(this);
        this.addMouseListener(this);
        this.setVisible(true);

    }

    public void paint(Graphics g) {//draw Axes

        if (flag1 == 1) {
            g.setColor(Color.blue);
            g.fillOval(x - 5, y - 5, 10, 10);
        }

    }

    public void update(Graphics g) {//repaint the point
        AffineTransform at1 = new AffineTransform();
        Graphics2D g2 = (Graphics2D) g;

        if (flag1 == 1) {
            g2.setColor(Color.blue);
            g2.fillOval(x - 4, y - 4, 8, 8);
        }

        if (flag2 == 1) {
            g2.setColor(Color.red);
            for (int i = 0; i < sum - 1; i++) {
                g2.drawLine((int) px[i][0], (int) py[i][0], (int) px[i + 1][0], (int) py[i + 1][0]);
                //time delay
                for (int l = 0; l < 100000; l++)
                    for (int t = 0; t < 4000; t++) {
                    }
            }

            //画曲线
            t = 0.0;
            int count = 0;
            while (t <= 1.0) {
                for (k = 0; k < sum; k++)
                    for (i = 0; i < sum - k; i++) {
                        point(px, i, k, t);
                        point(py, i, k, t);
                    }
                g2.setColor(Color.black);
                g2.fillOval((int) px[0][sum - 1] - 1, (int) py[0][sum - 1] - 1, 2, 2);
                t = t + 0.001;
            }


            //如果点了旋转，就旋转
            if (flag_lab1 == 2) {
                xz = Integer.parseInt(text3.getText());
                at1.setToRotation(Math.PI / xz, 400, 0);
                g2.transform(at1);
                flag_fs = 2;
                flag_lab1 = 0;
            }

            //然后再画曲线，画完之后坐标系还是变了，记得要把坐标系调整回来
            t = 0.0;
            while (t <= 1.0) {
                for (k = 0; k < sum; k++)
                    for (i = 0; i < sum - k; i++) {
                        point(px, i, k, t);
                        point(py, i, k, t);
                    }
                g2.setColor(Color.black);
                g2.fillOval((int) px[0][sum - 1] - 1, (int) py[0][sum - 1] - 1, 2, 2);
                t = t + 0.001;
            }

            if (flag_fs == 2) {
                //将坐标系调整回来
                at1.setToRotation(-Math.PI / xz, 400, 0);
                g2.transform(at1);
            }


            //如果点了平移，就平移
            if (flag_lab1 == 1) {
                pingyi = text2.getText();
                String s[] = pingyi.split(",");
                pyX = Double.parseDouble(s[0]);
                pyY = Double.parseDouble(s[1]);
                at1.setToTranslation(pyX, pyY);
                g2.transform(at1);
                flag_fs = 1;
                flag_lab1 = 0;
            }

            t = 0.0;
            while (t <= 1.0) {
                for (k = 0; k < sum; k++)
                    for (i = 0; i < sum - k; i++) {
                        point(px, i, k, t);
                        point(py, i, k, t);
                    }
                g2.setColor(Color.black);
                g2.fillOval((int) px[0][sum - 1] - 1, (int) py[0][sum - 1] - 1, 2, 2);
                t = t + 0.001;
            }

            if (flag_fs == 1) {
                at1.setToTranslation(-pyX, -pyY);
                g2.transform(at1);
            }

            //如果点了缩放
            if (flag_lab1 == 3) {
                suofang = text4.getText();
                String s[] = suofang.split(",");
                sfX = Double.parseDouble(s[0]);
                sfY = Double.parseDouble(s[1]);
                System.out.println("x="+sfX+"  "+"y="+sfY);
                at1.setToScale(sfX, sfY);
                g2.transform(at1);
                flag_fs = 3;
                flag_lab1 = 0;
            }

            t = 0.0;
            while (t <= 1.0) {
                for (k = 0; k < sum; k++)
                    for (i = 0; i < sum - k; i++) {
                        point(px, i, k, t);
                        point(py, i, k, t);
                    }
                g2.setColor(Color.black);
                g2.fillOval((int) px[0][sum - 1] - 1, (int) py[0][sum - 1] - 1, 2, 2);
                t = t + 0.001;
            }

            if (flag_fs == 3) {
                at1.setToScale(1 / sfX, 1 / sfY);
                g2.transform(at1);
            }
        }

        if (flag1 == 0 && flag2 == 0) {
            g2.setColor(Color.lightGray);
            g2.fillRect(0, 0, 800, 800);
        }

        if (flag3 == 1) {

            ts = Double.parseDouble(text.getText());
            System.out.println(ts);
            for (k = 0; k < sum; k++)
                for (i = 0; i < sum - k; i++) {
                    point(px, i, k, ts);
                    point(py, i, k, ts);
                }

            for (k = 1; k < sum; k++) {
                for (i = 0; i < sum - k - 1; i++) {
                    g2.setColor(Color.blue);
                    g2.fillOval((int) px[i][k] - 3, (int) py[i][k] - 3, 6, 6);
                    //time delay
                    for (int l = 0; l < 100000; l++)
                        for (int t = 0; t < 4000; t++) {
                        }
                    g2.setColor(Color.green);
                    g2.drawLine((int) px[i][k], (int) py[i][k], (int) px[i + 1][k], (int) py[i + 1][k]);
                }
                g2.setColor(Color.blue);
                g2.fillOval((int) px[i][k] - 3, (int) py[i][k] - 3, 6, 6);
                //time delay
                for (int l = 0; l < 100000; l++)
                    for (int t = 0; t < 4000; t++) {
                    }
            }
        }

    }

    public double point(double p[][], int i, int k, double t) {//cacu the point
        if (k == 0) return p[i][0];
        else {
            p[i][k] = t * point(p, i + 1, k - 1, t) + (1.0 - t) * point(p, i, k - 1, t);
            return p[i][k];
        }

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button_Caste && flag1 == 0) {
            dialog.setLocation(this.getX() + 350, this.getY() + 250);
            dialog.setVisible(true);
        }

        if (e.getSource() == button_Caste && flag1 == 1) {
            flag2 = 1;
            this.repaint();
        }

        if (e.getSource() == button_Clear) {
            flag1 = 0;
            flag2 = 0;
            flag3 = 0;
            sum = 0;
            j = 0;
            this.repaint();
        }

        if (e.getSource() == button_OK) {
            flag3 = 1;
            this.repaint();
        }

        if (e.getSource() == button_Py) {
            flag_lab1 = 1;
            System.out.println("pingyi");
            this.repaint();
        }

        if (e.getSource() == button_Xz) {
            flag_lab1 = 2;
            this.repaint();
        }

        if (e.getSource() == button_Sf) {
            flag_lab1 = 3;
            System.out.println("suofang");
            this.repaint();
        }


    }

    public void componentResized(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentHidden(ComponentEvent e) {
    }

    public void componentShown(ComponentEvent e) {
    }

    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == this) {
            flag1 = 1;
            int count;
            x = e.getX(); //获取鼠标在窗口中的位置坐标
            y = e.getY();
            px[j][0] = (double) x;
            py[j][0] = (double) y;
            j++;
            sum = j;
            this.repaint();
        }


    }


    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void windowOpened(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }

    public void windowClosing(WindowEvent e) {//exit the window
        if (e.getSource() == this) {
            System.exit(0);
        }
        if (e.getSource() == dialog) {
            dialog.setVisible(false);
        }
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public static void main(String args[]) {//main
        Casteljau caste = new Casteljau();
    }
}

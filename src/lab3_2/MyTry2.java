package lab3_2;

/**
 * Created by ZJYYY on 2018/5/25.
 */

import java.awt.*;
import java.applet.*;
import java.awt.geom.*;



public class MyTry2 extends Applet {

    Image img;

    public void init(){
        img = getImage(getDocumentBase(),"myImg1.gif");  //图像文件与编译后的字节码文件在同一目录下
    }

    public void start (){
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // 定义绘制变换类at1
        AffineTransform at1 = new AffineTransform();

        // 设置并应用平移变换,注意这里平移可以理解为是屏幕坐标系统的平移: 平移以后,屏幕坐标的原点位于(150,150)
//        at1.setToTranslation(150.0, 150.0);
//        g2.transform(at1);

        //设置旋转变换的角度(90度)
        at1.setToRotation(Math.PI/2);
        g2.transform(at1);

//        g2.drawString("Python",70f, 70f);
//        g3.drawString("Java",70f, 70f);
        //g2.transform(at1); //每执行一次都将原来基础上再转90度(将原矩阵再乘以一个90度的旋转矩阵).



        // 以 90 度角分别绘制四个“Java”, 四个椭圆,四个图像(.gif)
        //注意观察三个不同类型图形的旋转情况,
        //参数中的坐标都应看作是坐标系平移后的新坐标. 它们都围绕新的坐标原点旋转
        for (int i = 0; i < 4; i++) {

            g2.drawString("Java",0.0f, 0.0f);

            g2.drawOval(30,0,50,10);

            g2.drawImage(img, 80,0,30,30,this);

            g2.transform(at1); //每执行一次都将原来基础上再转90度(将原矩阵再乘以一个90度的旋转矩阵).

        }





    }



}
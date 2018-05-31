package lab3_1;

import java.applet.Applet;
import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Created by ZJYYY on 2018/5/25.
 */
public class MyTry1 extends Applet{
    Image img;

    public void init(){
        img = getImage(getDocumentBase(),"myImg1.gif");  //图像文件与编译后的字节码文件在同一目录下
    }

    public void start (){
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        AffineTransform at1 = new AffineTransform();
        AffineTransform at2 = new AffineTransform();
        //平移
        at1.setToTranslation(150, 150);
        g2.transform(at1);
        //旋转
        at2.setToRotation(Math.PI / 2);



    }
}

package lab3_2;

/**
 * Created by ZJYYY on 2018/5/25.
 */
class  Point2D
{
    int x, y;
    Point2D(){  }
    Point2D(int i,int j)
    {
        x=i;
        y=j;
    }
    void offset(int a, int b)
    {
        x=x+a;
        y=y+b;
    }
    void distance(Point2D a,Point2D b)
    {
        float m;
        m=(float)Math.sqrt((a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y));
        System.out.print("二维空间两点之间的距离:");
        System.out.println("m="+m);
    }
}

public class Point3D extends Point2D
{
    int x,y,z;
    Point3D(int x,int y,int z)
    {
        this.x=x;
        this.y=y;
        this.z=z;
    }
    Point3D(Point2D p,int z)
    {
        x=p.x;
        y=p.y;
        this.z=z;
    }
    void offset(int a, int b,int c)
    {
        x=x+a;
        b=x+b;
        c=x+c;
    }
    void distance(Point3D a,Point3D b)
    {
        float n;
        n=(float)Math.sqrt((a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y)+(a.y-b.y)*(a.y-b.y));//计算两点之间的距离。
        System.out.print("三维空间两点之间的距离:");
        System.out.println("n="+n);
    }
    public static void main(String[] args)
    {
        Point2D p2d1=new Point2D(2,3);
        Point2D p2d2=new Point2D(3,6);

        Point3D p2d3=new Point3D(1,2,3);
        Point3D p2d4=new Point3D(p2d1,3);

        p2d1.distance(p2d1,p2d2);
        p2d3.distance(p2d3,p2d4);//平移一段距离。
    }
}
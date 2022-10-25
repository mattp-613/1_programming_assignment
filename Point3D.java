import java.lang.Math;

public class Point3D {
    
    double coordX;
    double coordY;
    double coordZ;
    int clusterLabel;

    double red;
    double green;
    double blue;

    Point3D(double coordX, double coordY, double coordZ){

        this.coordX = coordX;
        this.coordY = coordY;
        this.coordZ = coordZ;

    }

    public double getX(){return this.coordX;};
    public double getY(){return this.coordY;};
    public double getZ(){return this.coordZ;};

    public double red(){return this.red;};
    public double green(){return this.green;};
    public double blue(){return this.blue;};


    public void setclusterLabel(int clusterLabel){
        this.clusterLabel = clusterLabel;
    }

    public void setRGB(double red, double green, double blue){
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getcluterLabel(){return this.clusterLabel;};

    public double Distance(Point3D pt){
        double x = Math.pow(this.coordX - pt.getX(), 2);
        double y = Math.pow(this.coordY - pt.getY(), 2);
        double z = Math.pow(this.coordZ - pt.getZ(), 2);

        return Math.pow(x+y+z,0.5);
    }

    /* 
    public static void main(String[] args){
        Point3D point = new Point3D(-5,4,2);
        Point3D point2 = new Point3D(6,3,-2);
        System.out.println(point.Distance(point2));
    }*/
}
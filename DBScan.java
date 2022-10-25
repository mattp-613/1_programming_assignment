import java.io.*;
import java.util.*;

public class DBScan{
    
    List<Point3D> pnts;
    double eps; //distance
    double minPnts; //min # of points to be considered a cluster

    DBScan(List<Point3D> pnts){
        this.pnts = pnts;
    }

    public double setEps(double eps){
        //sets distance in between points to be considered in a cluster
        this.eps = eps;
        return eps;
    }

    public double setMinPnts(double minPnts){
        //sets minimum amount of points required to be considered in a cluster
        this.minPnts = minPnts;
        return minPnts;
    }

    public void findClusters(){
        //executes DBScan algorithm
        
    }

    public int getNumberOfClusters(){
        //goes through the list of points
        //finds and returns the number of clustered points
        //requires DBScan to label the points in clusters, otherwise will return dumb/inaccurate answer

        return 0;
    }


    public List<Point3D> getPoints(){return this.pnts;};


    public static List<Point3D> read(String filename){

        return null;
    }

    public void save(String filename){
        //saves all the points with their cluster label and associated RGB color


    }

    public static void main(String[] args){

        //FILENAME, EPS AND MINPOINTS
        String fileName = args[0];
        double eps = Double.parseDouble(args[1]);
        double minPnts = Double.parseDouble(args[2]);

        

    }


}
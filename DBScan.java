import java.io.*;
import java.util.*;

public class DBScan{
    
    List<Point3D> pnts;
    double eps; //distance
    double minPnts; //min # of points to be considered a cluster

    DBScan(List<Point3D> pnts){
        this.pnts = pnts;
    }

    DBScan(String fileName) throws FileNotFoundException{
        this.pnts = read(fileName);
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
        //this algorithm will attatch a cluster to each Point3D object in the pnts list
        
        for(int i = 0; i < this.pnts.size(); i++){
            Point3D pnt = this.pnts(i);

            if(pnt.getcluterLabel != null){
                
            }
        }
    }

    public int getNumberOfClusters(){
        //goes through the list of points
        //finds and returns the number of clustered points
        //requires DBScan to label the points in clusters, otherwise will return dumb/inaccurate answer

        return 0;
    }


    public List<Point3D> getPoints(){return this.pnts;};


    public static List<Point3D> read(String fileName) throws FileNotFoundException{
        //reads CSV file using java's Scanner util
        //returns the list of points from the CSV file in List format

        List<Point3D> pnts = new ArrayList<>();

        Scanner sc = new Scanner(new File(fileName));  
        sc.nextLine(); //skips the x,y,z
        sc.useDelimiter(",|\\n"); //uses , and new line as delimiters

        while (sc.hasNext()){

            Double x = Double.parseDouble(sc.next());
            Double y = Double.parseDouble(sc.next());
            Double z = Double.parseDouble(sc.next());
            Point3D pnt = new Point3D(x,y,z); //sc.next() moves to the next value and returns the current value
            pnts.add(pnt);

        }

        sc.close();
        return pnts;
    }

    public void save(String fileName){
        //saves CSV File
        //includes x,y,z,cluster #, and RGB
    }

    public static void main(String[] args)  throws FileNotFoundException{

        //FILENAME, EPS AND MINPOINTS
        String fileName = args[0];
        double eps = Double.parseDouble(args[1]);
        double minPnts = Double.parseDouble(args[2]);

        DBScan db = new DBScan(fileName);

    }

}
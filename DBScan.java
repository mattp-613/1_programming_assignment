import java.io.*;
import java.util.*;
import java.nio.file.*;

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

    //default constructor
    DBScan(String fileName, double eps, double minPnts) throws FileNotFoundException{
        this.pnts = read(fileName);
        this.eps = eps;
        this.minPnts = minPnts;
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
        
        int C = 0;
        Stack<Point3D> S = new Stack<>();

        for(int i = 0; i < this.pnts.size(); i++){
            Point3D pnt = this.pnts.get(i);

            if(pnt.getClusterLabel() == -1){ //okay so, an int in java cannot be null. so i decided any cluster label with -1 is considered unlabelled
                
                //Find and set noise points to cluster 0
                NearestNeighbors nb = new NearestNeighbors(this.pnts);
                List<Point3D> neighbors = new ArrayList<>();
                neighbors = nb.rangeQuery(this.eps, pnt);

                if (neighbors.size() < this.minPnts){
                    pnt.setClusterLabel(0); //set it as noise if it doesnt meet the neighbour requirement
                }

            }

            //points that are not labelled as noise (label != 0)

            //pnt.setClusterLabel(C); //????????????????????????????????????????????????? THEN WHY LABEL THEM ABOVE
            //pseudocode is stupid im not doing that
            S.push(pnt);
            C += 1;
            while(S.size() != 0){
                Point3D Q = S.pop();
                
                if (Q.getClusterLabel() == 0){
                    Q.setClusterLabel(C);
                }

                if (Q.getClusterLabel() == -1){
                    Q.setClusterLabel(C);
                    NearestNeighbors nb = new NearestNeighbors(this.pnts);
                    List<Point3D> neighbors = new ArrayList<>();
                    neighbors = nb.rangeQuery(this.eps, Q);

                    if(neighbors.size() >= this.minPnts){
                            for(int y = 0; y < neighbors.size(); y++){
                                Point3D N = neighbors.get(y);
                                S.push(N);
                            }
                    }
                }
            }
        }
    }

    public void findRGB(){
        //this finds the RGB per cluster
        //uses a random seed multiplier that multiplies the seed by the cluster # to obtain the RGB value

        Random rand = new Random();
        double redSeed = rand.nextDouble();
        double greenSeed = rand.nextDouble();
        double blueSeed = rand.nextDouble();

        for(int i = 0; i < this.pnts.size(); i++){

            int intRed = (int) (redSeed * this.pnts.get(i).getClusterLabel());
            double red = (redSeed * this.pnts.get(i).getClusterLabel()) - intRed;

            int intGreen = (int) (greenSeed * this.pnts.get(i).getClusterLabel());
            double green = (greenSeed * this.pnts.get(i).getClusterLabel()) - intGreen;

            int intBlue = (int) (blueSeed * this.pnts.get(i).getClusterLabel());
            double blue = (blueSeed * this.pnts.get(i).getClusterLabel()) - intBlue;

            this.pnts.get(i).setRGB(Math.round(red*100.0)/100.0,Math.round(green*100.0)/100.0,Math.round(blue*100.0)/100.0);

        }
    }

    public int getNumberOfClusters(){
        //goes through the list of points
        //finds and returns the number of clustered points
        //requires findClusters() to label the points in clusters, otherwise will return dumb/inaccurate answer
        //this method uses a Set object

        List<Integer> allClusters = new ArrayList<>();
        for(int i = 0; i < this.pnts.size(); i++){
            allClusters.add(i,this.pnts.get(i).getClusterLabel());

        }

        Set<Integer> sizeSet = new HashSet<>(allClusters);
        return sizeSet.size();

    }


    public List<Point3D> getPoints(){return this.pnts;};


    public static List<Point3D> read(String fileName) throws FileNotFoundException{
        //reads CSV file using java's Scanner util
        //returns the list of points from the CSV file in List format

        List<Point3D> pnts = new ArrayList<>();

        Scanner sc = new Scanner(new File(fileName));  
        sc.nextLine(); //skips the x,y,z string headers
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

    public void save(String fileName) throws IOException{
        //saves CSV File as filename_clusters_#eps_#minPts_#nClusters.csv
        //includes x,y,z,cluster #, and RGB
        //ensure that findClusters() and findRGB() have ran with newest data, otherwise this will output bad values

        int nClusters = getNumberOfClusters();
        String saveFileName = (fileName + "_clusters_" + eps + "_" + minPnts + "_" + nClusters + ".csv");
        
            // create a writer
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(saveFileName));
        
            // write header record
            writer.write("X,Y,Z,C,R,G,B");
            writer.newLine();
        
            // write all records
            for (int i = 0; i < this.pnts.size(); i++) {
                writer.write(this.pnts.get(i).getX() + ",");
                writer.write(this.pnts.get(i).getY() + ",");
                writer.write(this.pnts.get(i).getZ() + ",");
                writer.write(this.pnts.get(i).getClusterLabel() + ",");
                writer.write(this.pnts.get(i).red() + ",");
                writer.write(this.pnts.get(i).green() + ",");
                writer.write(this.pnts.get(i).blue() + "\n");
                //writer.newLine();
            }
        
            //close the writer
            writer.close();
        

        }

    public static void main(String[] args) throws FileNotFoundException, IOException{

        //javac DBScan.java && java DBScan Point_Cloud_2.csv 7 10

        //FILENAME, EPS AND MINPOINTS
        String fileName = args[0];
        double eps = Double.parseDouble(args[1]);
        double minPnts = Double.parseDouble(args[2]);


        //TESTING GROUNDS

         
        DBScan db = new DBScan(fileName, eps, minPnts);

        NearestNeighbors nb = new NearestNeighbors(db.getPoints());

        db.findClusters();

        List<Point3D> dbpoints = db.getPoints();

        db.findRGB();

        db.save("test1");

    }

}
import java.util.*;

public class NearestNeighbors {

    List<Point3D> pnts;

    NearestNeighbors(List<Point3D> pnts){
        this.pnts = pnts;
    }

    public List<Point3D> rangeQuery(double eps, Point3D p){
        //finds the closest points to point p, using the built in function in Point3D.
        //returns a list of points of all near neighbors, returns empty list if none!

        List<Point3D> pnts = new ArrayList<>();

        for(int i = 0; i < this.pnts.size(); i++){
            Point3D p2 = this.pnts.get(i);

            double distance = p.Distance(p2);
            if(distance<=eps){
                pnts.add(p2);
            }
        }

        return pnts;
    }
    
}

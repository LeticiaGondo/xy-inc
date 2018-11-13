package br.com.zup.recruitment.backend.springxyinc.pointofinterest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fellipe G on 11/11/2018.
 * Controller class for 'Points of Interest'
 * Contains business logic and calculations using points of interests
 */
public class PointOfInterestController {

    /**
     * Calculates the distance between two points of interest, (x1,y1) e (x2,y2)
     * Using the algebraic formula ''
     * @param pointOfInterest1 POI 1
     * @param pointOfInterest2 POI 2
     * @return double with the distance between POI 1 and POI 2
     */
    public double calculateDistanceBetweenPoints(PointOfInterest pointOfInterest1, PointOfInterest pointOfInterest2){

        double x1 = pointOfInterest1.getxCoordinate();
        double y1 = pointOfInterest1.getyCoordinate();
        double x2 = pointOfInterest2.getxCoordinate();
        double y2 = pointOfInterest2.getyCoordinate();

        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }

    /**
     * Checks if a point of interest is at least 'maxRadius' of distance from the other one
     * @param pointOfInterest1 reference POI
     * @param pointOfInterest2 POI to check
     * @param maxRadius maximum distance between them
     * @return boolean with either the POI is inside the 'maxRadius' radius or not
     */
    public boolean isInsideRadius(PointOfInterest pointOfInterest1,
                                  PointOfInterest pointOfInterest2,
                                  int maxRadius){

        double distanceBetweenPoints = calculateDistanceBetweenPoints(pointOfInterest1,pointOfInterest2);

        return distanceBetweenPoints <= maxRadius;
    }

    /**
     * Lists all the registered points of interest near a reference point, at a maximum distance of 'maxRadius'
     * @param poiList a list of the registered points of interest
     * @param referencePoint reference point
     * @param maxRadius the acceptable maximum distance
     * @return a list of POIs inside the reference POI radius
     */
    public List<PointOfInterest> listPointsInsideRadius(List<PointOfInterest> poiList,
                                                               PointOfInterest referencePoint,
                                                               int maxRadius){

        List<PointOfInterest> pointsInsideRadius = new ArrayList<>();

        for(PointOfInterest poi : poiList){
            if(isInsideRadius(referencePoint,poi,maxRadius)){
                pointsInsideRadius.add(poi);
            }
        }

        return pointsInsideRadius;
    }
}

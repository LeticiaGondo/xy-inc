package br.com.zup.recruitment.backend.springxyinc.pointofinterest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fellipe G on 11/11/2018.
 */
public class PointOfInterestController {

    public double calculateDistanceBetweenPoints(PointOfInterest pointOfInterest1, PointOfInterest pointOfInterest2){

        double x1 = pointOfInterest1.getxCoordinate();
        double y1 = pointOfInterest1.getyCoordinate();
        double x2 = pointOfInterest2.getxCoordinate();
        double y2 = pointOfInterest2.getyCoordinate();

        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }

    public boolean isInsideRadius(PointOfInterest pointOfInterest1,
                                  PointOfInterest pointOfInterest2,
                                  int maxRadius){

        double distanceBetweenPoints = calculateDistanceBetweenPoints(pointOfInterest1,pointOfInterest2);

        return distanceBetweenPoints <= maxRadius;
    }

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

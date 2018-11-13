package br.com.zup.recruitment.backend.springxyinc.pointofinterest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * Created by Fellipe G on 11/11/2018.
 * REST service class for Points of Interest for registering, listing and analysing points of interest
 * through REST methods
 */
@RestController
public class PointOfInterestService {

    @Autowired
    private PointOfInterestRepository pointOfInterestRepository;

    PointOfInterestController pointOfInterestController = new PointOfInterestController();

    /**
     * POST service on '/points-of-interest' to register a new point of interest in the database
     * @param pointOfInterest the new point of interest
     * @return PointOfInterest object containing the new registered POI
     */
    @PostMapping("/points-of-interest")
    public PointOfInterest createPointOfInterest(@Valid @RequestBody PointOfInterest pointOfInterest) {
        return  pointOfInterestRepository.save(pointOfInterest);
    }

    /**
     * GET service on '/points-of-interest' to list all registered points of interests in the database
     * @return list of PointOfInterest objects
     */
    @GetMapping("/points-of-interest")
    public List<PointOfInterest> retrieveAllPointsOfInterest() {
        return pointOfInterestRepository.findAll();
    }

    /**
     * GET service on '/points-of-interest/list-nearby' to list the points of interest inside the radius of a reference
     * point (X,Y), at the maximum distance of 'maxDistance'
     * @param x X coordinate of the reference point
     * @param y Y coordinate of the reference point
     * @param maxDistance radius size, checked maximum distance
     * @return list of PointOfInterest objects near the reference point
     */
    @GetMapping("/points-of-interest/list-nearby")
    public List<PointOfInterest> retrievePointsInsideRadius(@RequestParam("referenceX") int x,
                                                            @RequestParam("referenceY") int y,
                                                            @RequestParam("maxDistance") int maxDistance) {

        PointOfInterest referencePOI = new PointOfInterest("Reference poi",x,y);

        return pointOfInterestController.listPointsInsideRadius
                (pointOfInterestRepository.findAll(), referencePOI, maxDistance);
    }
}

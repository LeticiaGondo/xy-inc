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
 */
@RestController
public class PointOfInterestService {

    @Autowired
    private PointOfInterestRepository pointOfInterestRepository;

    PointOfInterestController pointOfInterestController = new PointOfInterestController();


    @PostMapping("/points-of-interest")
    public PointOfInterest createPointOfInterest(@Valid @RequestBody PointOfInterest pointOfInterest) {
        return  pointOfInterestRepository.save(pointOfInterest);
    }

    @GetMapping("/points-of-interest")
    public List<PointOfInterest> retrieveAllPointsOfInterest() {
        return pointOfInterestRepository.findAll();
    }

    @GetMapping("/points-of-interest/list-nearby")
    public List<PointOfInterest> retrievePointsInsideRadius(@RequestParam("referenceX") int x,
                                                            @RequestParam("referenceY") int y,
                                                            @RequestParam("maxDistance") int maxDistance) {

        PointOfInterest referencePOI = new PointOfInterest("Reference poi",x,y);

        return pointOfInterestController.listPointsInsideRadius
                (pointOfInterestRepository.findAll(), referencePOI, maxDistance);
    }
}

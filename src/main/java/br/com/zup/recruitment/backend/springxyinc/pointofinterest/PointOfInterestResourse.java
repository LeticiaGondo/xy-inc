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
public class PointOfInterestResourse {

    @Autowired
    private PointOfInterestRepository pointOfInterestRepository;

    @PostMapping("/points-of-interest")
    public ResponseEntity<Object> createPointOfInterest(@Valid @RequestBody PointOfInterest pointOfInterest) {
        PointOfInterest savedPointOfInterest = pointOfInterestRepository.save(pointOfInterest);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedPointOfInterest.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    @GetMapping("/points-of-interest")
    public List<PointOfInterest> retrieveAllPointsOfInterest() {
        return pointOfInterestRepository.findAll();
    }

    @GetMapping("/points-of-interest/list-nearby")
    public List<PointOfInterest> retrievePointsInsideRadius(@RequestParam("referenceX") int x,
                                                            @RequestParam("referenceY") int y,
                                                            @RequestParam("maxDistance") int maxDistance) {

        PointOfInterestController pointOfInterestController = new PointOfInterestController();
        PointOfInterest referencePOI = new PointOfInterest("Reference poi",x,y);

        return pointOfInterestController.listPointsInsideRadius
                (pointOfInterestRepository.findAll(), referencePOI,maxDistance);
    }


}

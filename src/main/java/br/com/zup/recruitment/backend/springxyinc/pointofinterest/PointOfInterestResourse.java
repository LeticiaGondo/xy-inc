package br.com.zup.recruitment.backend.springxyinc.pointofinterest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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

    @PostMapping("/pointsofinterest")
    public ResponseEntity<Object> createStudent(@Valid @RequestBody PointOfInterest pointOfInterest) {
        PointOfInterest savedPointOfInterest = pointOfInterestRepository.save(pointOfInterest);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedPointOfInterest.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    @GetMapping("/pointsofinterest")
    public List<PointOfInterest> retrieveAllStudents() {
        return pointOfInterestRepository.findAll();
    }
}

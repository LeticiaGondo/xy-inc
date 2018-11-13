package br.com.zup.recruitment.backend.springxyinc.pointofinterest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Fellipe G on 11/11/2018.
 * Repository interface to interact with the database objects
 */
@Repository
public interface PointOfInterestRepository extends JpaRepository<PointOfInterest, Long> {

}

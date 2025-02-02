package io.medsys.opteamer.repositories;

import io.medsys.opteamer.model.PreOperativeAssessment;
import org.springframework.data.repository.CrudRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface PreOperativeAssessmentRepository extends CrudRepository<PreOperativeAssessment,String> {
    Optional<PreOperativeAssessment> findByName(String name);
}

package io.medsys.opteamer.rest;

import io.medsys.opteamer.dto.PreOperativeAssessmentDTO;
import io.medsys.opteamer.services.PreOperativeAssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/preoperativeassessments")
public class PreOperativeAssessmentController {
    PreOperativeAssessmentService preOperativeAssessmentService;
    @Autowired
    public PreOperativeAssessmentController(PreOperativeAssessmentService preOperativeAssessmentService){
        this.preOperativeAssessmentService=preOperativeAssessmentService;
    }

    @GetMapping("/{name}")
    public ResponseEntity<PreOperativeAssessmentDTO> getPreOperativeAssessmentById(@PathVariable(name = "name") String name){
        Optional<PreOperativeAssessmentDTO> preOperativeAssessmentDTOOptional=preOperativeAssessmentService.getPreOperationAssessmentByName(name);
        return preOperativeAssessmentDTOOptional.map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<List<PreOperativeAssessmentDTO>> getAllPreOperationalAssessments(){
        List<PreOperativeAssessmentDTO> list = preOperativeAssessmentService.getAllPreOperativeAssessments();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping
    public ResponseEntity<PreOperativeAssessmentDTO> createPreOperationAssessment(@RequestBody PreOperativeAssessmentDTO preOperativeAssessmentDTO){
        PreOperativeAssessmentDTO createdPreOperativeAssessment = preOperativeAssessmentService.createPreOperativeAssessment(preOperativeAssessmentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPreOperativeAssessment);
    }

    @PutMapping("/{name}")
    public ResponseEntity<PreOperativeAssessmentDTO> updatePreOperativeAssessment(@PathVariable(name = "name") String name, @RequestBody PreOperativeAssessmentDTO preOperativeAssessmentDTO){
        Optional<PreOperativeAssessmentDTO> preOperativeAssessmentDTOOptional = preOperativeAssessmentService.updatePreOperativeAssessment(name, preOperativeAssessmentDTO);
        return preOperativeAssessmentDTOOptional.map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deletePreOperativeAssessment(@PathVariable(name = "name") String name){
        boolean deleted = preOperativeAssessmentService.deletePreOperativeAssessment(name);
        if(deleted){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}

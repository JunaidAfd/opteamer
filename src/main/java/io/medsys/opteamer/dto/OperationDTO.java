package io.medsys.opteamer.dto;

import io.medsys.opteamer.model.OperationRoom;
import io.medsys.opteamer.model.OperationType;
import io.medsys.opteamer.model.enums.OperationState;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationDTO {
    private Long id;
    private OperationType operationType;
    private OperationRoom operationRoom;
    private PatientDTO patient;
    private OperationState state;
    private LocalDateTime startDate;
    private Set<TeamMemberDTO> teamMembers;
}

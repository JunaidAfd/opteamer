package io.medsys.opteamer.dto;

import io.medsys.opteamer.repositories.PreOperativeAssessmentRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OperationReportDTO {
    private Long teamMemberId;
    private Long operationId;
    private TeamMemberDTO teamMember;
    private OperationDTO operation;
    private String report;
}

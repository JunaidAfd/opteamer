package io.medsys.opteamer.model;

import io.medsys.opteamer.model.embeddedids.OperationReportId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "OPERATION_REPORTS")
@NoArgsConstructor
@Getter
@Setter
public class OperationReport {
    @EmbeddedId
    private OperationReportId operationReportId;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "team_member_id")
    private TeamMember teamMember;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "operation_id")
    private Operation operation;

    @Column(name="report")
    private String report;
}

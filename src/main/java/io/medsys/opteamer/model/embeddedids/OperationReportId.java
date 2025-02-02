package io.medsys.opteamer.model.embeddedids;

import jakarta.persistence.Column;

import java.io.Serializable;

public class OperationReportId implements Serializable {
    @Column(name = "team_member_id")
    private Long teamMemberId;

    @Column(name = "operation_id")
    private Long operationId;
}

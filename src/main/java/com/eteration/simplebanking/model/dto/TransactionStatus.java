package com.eteration.simplebanking.model.dto;

public class TransactionStatus {

    private String status;
    private String approvalCode;

    public TransactionStatus(String status, String approvalCode) {
        this.status = status;
        this.approvalCode = approvalCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }
}

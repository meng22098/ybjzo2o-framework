package com.ybjzo2o.customer.model.dto;


public class AgencyCertificationAudit {

  private long id;
  private long serveProviderId;
  private String name;
  private String idNumber;
  private String legalPersonName;
  private String legalPersonIdCardNo;
  private String businessLicense;
  private long auditStatus;
  private long auditorId;
  private String auditorName;
  private java.sql.Timestamp auditTime;
  private long certificationStatus;
  private String rejectReason;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getServeProviderId() {
    return serveProviderId;
  }

  public void setServeProviderId(long serveProviderId) {
    this.serveProviderId = serveProviderId;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getIdNumber() {
    return idNumber;
  }

  public void setIdNumber(String idNumber) {
    this.idNumber = idNumber;
  }


  public String getLegalPersonName() {
    return legalPersonName;
  }

  public void setLegalPersonName(String legalPersonName) {
    this.legalPersonName = legalPersonName;
  }


  public String getLegalPersonIdCardNo() {
    return legalPersonIdCardNo;
  }

  public void setLegalPersonIdCardNo(String legalPersonIdCardNo) {
    this.legalPersonIdCardNo = legalPersonIdCardNo;
  }


  public String getBusinessLicense() {
    return businessLicense;
  }

  public void setBusinessLicense(String businessLicense) {
    this.businessLicense = businessLicense;
  }


  public long getAuditStatus() {
    return auditStatus;
  }

  public void setAuditStatus(long auditStatus) {
    this.auditStatus = auditStatus;
  }


  public long getAuditorId() {
    return auditorId;
  }

  public void setAuditorId(long auditorId) {
    this.auditorId = auditorId;
  }


  public String getAuditorName() {
    return auditorName;
  }

  public void setAuditorName(String auditorName) {
    this.auditorName = auditorName;
  }


  public java.sql.Timestamp getAuditTime() {
    return auditTime;
  }

  public void setAuditTime(java.sql.Timestamp auditTime) {
    this.auditTime = auditTime;
  }


  public long getCertificationStatus() {
    return certificationStatus;
  }

  public void setCertificationStatus(long certificationStatus) {
    this.certificationStatus = certificationStatus;
  }


  public String getRejectReason() {
    return rejectReason;
  }

  public void setRejectReason(String rejectReason) {
    this.rejectReason = rejectReason;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public java.sql.Timestamp getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(java.sql.Timestamp updateTime) {
    this.updateTime = updateTime;
  }

}

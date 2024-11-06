package com.example.english_learning_server.entity;//package com.example.english_learning_server.entity;
//
//import jakarta.persistence.*;
//import org.springframework.data.annotation.CreatedBy;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedBy;
//import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//@MappedSuperclass
//@EntityListeners(AuditingEntityListener.class)
//public abstract class BaseEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @Column(name = "created_by")
//    @CreatedBy
//    private String createdBy;
//    @Column(name = "create_date")
//    @CreatedDate
//    private String createDate;
//    @Column(name = "updated_by")
//    @LastModifiedBy
//    private String updatedBy;
//    @Column(name = "updated_date")
//    @LastModifiedDate
//    private String updatedDate;
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getCreatedBy() {
//        return createdBy;
//    }
//
//    public void setCreatedBy(String createdBy) {
//        this.createdBy = createdBy;
//    }
//
//    public String getCreateDate() {
//        return createDate;
//    }
//
//    public void setCreateDate(String createDate) {
//        this.createDate = createDate;
//    }
//
//    public String getUpdatedBy() {
//        return updatedBy;
//    }
//
//    public void setUpdatedBy(String updatedBy) {
//        this.updatedBy = updatedBy;
//    }
//
//    public String getUpdatedDate() {
//        return updatedDate;
//    }
//
//    public void setUpdatedDate(String updatedDate) {
//        this.updatedDate = updatedDate;
//    }
//}

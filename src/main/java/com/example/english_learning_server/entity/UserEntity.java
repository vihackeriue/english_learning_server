package com.example.english_learning_server.entity;//package com.example.english_learning_server.entity;
//
//import jakarta.persistence.*;
//
//import java.util.List;
//import java.util.Set;
//
//@Entity
//@Table(name = "user")
//public class UserEntity extends BaseEntity{
//    @Column(name = "email",length = 50, nullable = false, unique = true)
//    private String email;
//    @Column(name = "password", nullable = false)
//    private String password;
//    @Column(name = "full_name",columnDefinition = "NVARCHAR(255)")
//    private String fullName;
//    @Column(name = "phone", length = 15)
//    private String phone;
//    @Column(name = "avatar")
//    private String avatar;
//    @Column(name = "role", nullable = false)
//    private int role;
//    @Column (name = "status", nullable = false)
//    private int status = 1 ;
//
//    @OneToMany(mappedBy = "user")
//    private List<UserCourseEntity> users;
//
//
//
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getFullName() {
//        return fullName;
//    }
//
//    public void setFullName(String fullName) {
//        this.fullName = fullName;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public String getAvatar() {
//        return avatar;
//    }
//
//    public void setAvatar(String avatar) {
//        this.avatar = avatar;
//    }
//
//    public int getRole() {
//        return role;
//    }
//
//    public void setRole(int role) {
//        this.role = role;
//    }
//
//    public int getStatus() {
//        return status;
//    }
//
//    public void setStatus(int status) {
//        this.status = status;
//    }
//
//    public List<UserCourseEntity> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<UserCourseEntity> users) {
//        this.users = users;
//    }
//}

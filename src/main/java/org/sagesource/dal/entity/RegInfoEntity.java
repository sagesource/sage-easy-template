package org.sagesource.dal.entity;

public class RegInfoEntity {
    private Integer id;

    private String username;

    private String worknumber;

    private String sex;

    private String department;

    private String health;

    private String phone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getWorknumber() {
        return worknumber;
    }

    public void setWorknumber(String worknumber) {
        this.worknumber = worknumber == null ? null : worknumber.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health == null ? null : health.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }
}
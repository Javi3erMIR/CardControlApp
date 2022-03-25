package com.taasa.cardcontrol;

public class StudentModel {

    private String student_name;
    private String curp;
    private String student_group;
    private String study_time;
    private String school_id;
    private String student_id;
    private String validity;
    private int degree;

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getStudent_group() {
        return student_group;
    }

    public void setStudent_group(String student_group) {
        this.student_group = student_group;
    }

    public String getStudy_time() {
        return study_time;
    }

    public void setStudy_time(String study_time) {
        this.study_time = study_time;
    }

    public String getSchool_id() {
        return school_id;
    }

    public void setSchool_id(String school_id) {
        this.school_id = school_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    @Override
    public String toString() {
        return student_name;
    }
}

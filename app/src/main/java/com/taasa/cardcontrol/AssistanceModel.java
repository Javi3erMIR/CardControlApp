package com.taasa.cardcontrol;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class AssistanceModel {

    private String student_name;
    private String student_id;
    private String assistance_date;
    private boolean assistance;

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getAssistance_date() {
        return assistance_date;
    }

    public void setAssistance_date(String assistance_date) {
        this.assistance_date = assistance_date;
    }

    public boolean isAssistance() {
        return assistance;
    }

    public void setAssistance(boolean assistance) {
        this.assistance = assistance;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    @Override
    public String toString() {
        return isAssistance() ? student_name + " assisted" : student_name + " no assisted";
    }
}

package com.taasa.cardcontrol;

import com.google.firebase.database.FirebaseDatabase;

public class DBConnectionHelper {

    public static FirebaseDatabase fireDB(String instance){
        return FirebaseDatabase.getInstance(instance);
    }
}

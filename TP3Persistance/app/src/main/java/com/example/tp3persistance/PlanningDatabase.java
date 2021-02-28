package com.example.tp3persistance;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PlanningDatabase  {
    @PrimaryKey
    public int IdRdv;

    @ColumnInfo (name = "heure_rdv")
    public String heure_rdv;

    @ColumnInfo (name = "intitule_rdv")
    public String intitule_rdv;

    public PlanningDatabase(int IdRdv, String heure_rdv,String intitule_rdv) {
        this.IdRdv = IdRdv;
        this.heure_rdv = heure_rdv;
        this.intitule_rdv = intitule_rdv;
    }
}

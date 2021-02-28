package com.example.tp3persistance;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PlanningDAO {
    @Query("SELECT * FROM PlanningDatabase")
    List<PlanningDatabase> getAll();

    @Query("SELECT * FROM PlanningDatabase WHERE heure_rdv LIKE :heure_rdv LIMIT 1")
    PlanningDatabase getByHeure(String heure_rdv);

    @Insert
    void insert(PlanningDatabase planningDatabase);

    @Delete
    void delete(PlanningDatabase planningDatabase);
}

package com.example.tp3persistance;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PlanningView extends ViewModel {
    // Exercice 4
    public String Rdv_8h = "08h-10H : Rencontre client Dupont";
    public String Rdv_10h = "10h-12h : Travailler le dossier recrutement";
    public String Rdv_14h = "14h-16h : Réunion équipe";
    public String Rdv_16h = "16h-18h : Préparation dossier vente";
    //Fin Exercice 4


    //Exercice 5
    public MutableLiveData<String> rdv_8h;
    public MutableLiveData<String> rdv_10h;
    public MutableLiveData<String> rdv_14h;
    public MutableLiveData<String> rdv_16h;


    public MutableLiveData<String> getRdv_8h() {
        if (rdv_8h == null) {
            rdv_8h = new MutableLiveData<String>();
        }
        return rdv_8h;
    }

    public MutableLiveData<String> getRdv_10h() {
        if (rdv_10h == null) {
            rdv_10h = new MutableLiveData<String>();
        }
        return rdv_10h;
    }

    public MutableLiveData<String> getRdv_14h() {
        if (rdv_14h == null) {
            rdv_14h = new MutableLiveData<String>();
        }
        return rdv_14h;
    }

    public MutableLiveData<String> getRdv_16h() {
        if (rdv_16h == null) {
            rdv_16h = new MutableLiveData<String>();
        }
        return rdv_16h;
    }

    public void setRdv_8h(String str){
        rdv_8h.setValue(str);
    }



    public void setRdv_10h(String str){
        rdv_10h.setValue(str);
    }



    public void setRdv_14h(String str){
        rdv_14h.setValue(str);
    }



    public void setRdv_16h(String str){
        rdv_16h.setValue(str);
    }


}

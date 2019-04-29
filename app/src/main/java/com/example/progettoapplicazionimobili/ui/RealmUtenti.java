package com.example.progettoapplicazionimobili.ui;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;


public class RealmUtenti {

    private static RealmUtenti dbManager;

    private static Realm realm;

    private String realmName = "Utenti";

    private RealmUtenti(Context context) {

        if (realm == null) {

            //Realm Object is Created

            Realm.init(context);

            RealmConfiguration configuration = new RealmConfiguration.Builder()

                    .name(realmName)

                    .deleteRealmIfMigrationNeeded()

                    .build();

            realm = Realm.getInstance(configuration);

        }

    }

    public static RealmUtenti getRealmInstance(Context context) {

        if (dbManager == null) {

            //DBManager Object is Created

            dbManager = new RealmUtenti(context);

        }

        return dbManager;

    }

    //Prodotto Added in Realm
    public void addOrUpdateRealmList(UtentiApp utente) {

        realm.beginTransaction();

        realm.copyToRealmOrUpdate(utente);

        realm.commitTransaction();

    }
    //Get Realm Data
    public RealmResults getAllUtenti() {

        realm.beginTransaction();

        RealmResults realmNotes = realm.where(UtentiApp.class).findAll();

        realm.commitTransaction();

        return realmNotes;

    }

    //Realm Data Updated
    public void updateUtenti(UtentiApp utente) {

        realm.beginTransaction();

        realm.copyToRealmOrUpdate(utente);

        realm.commitTransaction();

    }

    //Realm Data Deleted

    public void deleteUtente(UtentiApp utente) {

        realm.beginTransaction();

        utente.deleteFromRealm();

        realm.commitTransaction();

    }

}

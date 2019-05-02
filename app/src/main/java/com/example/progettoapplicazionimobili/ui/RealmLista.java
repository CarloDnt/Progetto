package com.example.progettoapplicazionimobili.ui;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;


public class RealmLista {

    private static RealmLista dbManager;

    private static Realm realm;

    private String realmName = "Lista";

    private RealmLista(Context context) {

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

    public static RealmLista getRealmInstance(Context context) {

        if (dbManager == null) {

            //DBManager Object is Created

            dbManager = new RealmLista(context);

        }

        return dbManager;

    }

    //Prodotto Added in Realm
    public void addOrUpdateRealmList(NotaLista note) {

        realm.beginTransaction();

        realm.copyToRealmOrUpdate(note);

        realm.commitTransaction();

    }
    //Get Realm Data
    public RealmResults<ProdottoDisp> getAllNotes() {

        realm.beginTransaction();

        RealmResults realmNotes = realm.where(NotaLista.class).findAll();

        realm.commitTransaction();

        return realmNotes;

    }

    //Realm Data Updated
    public void updateNote(NotaLista note) {

        realm.beginTransaction();

        realm.copyToRealmOrUpdate(note);

        realm.commitTransaction();

    }

    //Realm Data Deleted

    public void deleteNote(NotaLista note) {

        realm.beginTransaction();

        note.deleteFromRealm();

        realm.commitTransaction();

    }

}

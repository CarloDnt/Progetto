package com.example.progettoapplicazionimobili.ui;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;


public class RealmManipulator {

    private static RealmManipulator dbManager;

    private static Realm realm;

    private String realmName = "RealmProdotti";

    private RealmManipulator(Context context) {

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

    public static RealmManipulator getRealmInstance(Context context) {

        if (dbManager == null) {

            //DBManager Object is Created

            dbManager = new RealmManipulator(context);

        }

        return dbManager;

    }

    //Prodotto Added in Realm
    public void addOrUpdateRealmList(ProdottoDisp prodotto) {

        realm.beginTransaction();

        realm.copyToRealmOrUpdate(prodotto);

        realm.commitTransaction();

    }
    //Get Realm Data
    public RealmResults getAllProdotti() {

        realm.beginTransaction();

        RealmResults realmNotes = realm.where(ProdottoDisp.class).findAll();

        realm.commitTransaction();

        return realmNotes;

    }

    //Realm Data Updated
    public void updateProdotti(ProdottoDisp prodotto) {

        realm.beginTransaction();

        realm.copyToRealmOrUpdate(prodotto);

        realm.commitTransaction();

    }

    //Realm Data Deleted

    public void deleteProdotto(ProdottoDisp prodotto) {

        realm.beginTransaction();

        prodotto.deleteFromRealm();

        realm.commitTransaction();

    }

}

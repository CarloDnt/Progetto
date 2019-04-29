package com.example.progettoapplicazionimobili.ui;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;


public class RealmDispensa {

    private static RealmDispensa dbManager;

    private static Realm realm;

    private String realmName = "Dispensa";

    private RealmDispensa(Context context) {

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

    public static RealmDispensa getRealmInstance(Context context) {

        if (dbManager == null) {

            //DBManager Object is Created

            dbManager = new RealmDispensa(context);

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
    public RealmResults<ProdottoDisp> getAllProdotti() {

        realm.beginTransaction();

        RealmResults realmNotes = realm.where(ProdottoDisp.class).findAll();

        realm.commitTransaction();

        return realmNotes;

    }
    public RealmResults<ProdottoDisp> getNomeProdotto(String nameP){
        realm.beginTransaction();

        RealmResults realmNotes = realm.where(ProdottoDisp.class).equalTo("nomeProdotto",nameP).findAll();

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

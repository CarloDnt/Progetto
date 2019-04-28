package com.example.progettoapplicazionimobili.ui;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.*;

public class ProdottoDisp extends RealmObject implements Parcelable {

    @PrimaryKey
    private int id ,quatita,prezzo;
    private String nomeProdotto;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeProdotto() {
        return nomeProdotto;
    }

    public void setNomeProdotto(String nomeProdotto) {
        this.nomeProdotto = nomeProdotto;
    }

    public Integer getQuatita() {
        return quatita;
    }

    public void setQuatita(Integer quatita) {
        this.quatita = quatita;
    }

    public int getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(int prezzo) {
        this.prezzo = prezzo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.nomeProdotto);
        dest.writeInt(this.quatita);
        dest.writeInt(this.prezzo);
    }

    public ProdottoDisp() {
    }

    public ProdottoDisp(int id, String nomeProdotto,Integer quatita,Integer prezzo) {
        this.id = id;
        this.nomeProdotto = nomeProdotto;
        this.quatita = quatita;
        this.prezzo = prezzo;

    }

    protected ProdottoDisp(Parcel in) {
        this.id = in.readInt();
        this.nomeProdotto = in.readString();
        this.quatita = in.readInt();
        this.prezzo=in.readInt();
    }

    public static final Parcelable.Creator<ProdottoDisp> CREATOR = new Parcelable.Creator<ProdottoDisp>() {
        @Override
        public ProdottoDisp createFromParcel(Parcel source) {
            return new ProdottoDisp(source);
        }

        @Override
        public ProdottoDisp[] newArray(int size) {
            return new ProdottoDisp[size];
        }
    };
}
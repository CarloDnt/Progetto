package com.example.progettoapplicazionimobili.ui;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.*;

public class NotaLista extends RealmObject implements Parcelable {

    @PrimaryKey
    private String nomeProdotto;

    private int quatita;

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nomeProdotto);
        dest.writeInt(this.quatita);
    }

    public NotaLista() {
    }

    public NotaLista(String nomeProdotto,Integer quatita) {
        this.nomeProdotto = nomeProdotto;
        this.quatita = quatita;
    }

    protected NotaLista(Parcel in) {
        this.nomeProdotto = in.readString();
        this.quatita = in.readInt();
    }

    public static final Parcelable.Creator<NotaLista> CREATOR = new Parcelable.Creator<NotaLista>() {
        @Override
        public NotaLista createFromParcel(Parcel source) {
            return new NotaLista(source);
        }

        @Override
        public NotaLista[] newArray(int size) {
            return new NotaLista[size];
        }
    };
}
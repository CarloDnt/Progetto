package com.example.progettoapplicazionimobili.ui;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.*;

public class ProdottoDisp extends RealmObject implements Parcelable {

    @PrimaryKey
    private String nomeProdotto;

    private int quatita,prezzo;
    private Date scadenza;

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

    public Date getScadenza() {
        return scadenza;
    }

    public void setScadenza(Date scadenza) {
        this.scadenza = scadenza;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nomeProdotto);
        dest.writeInt(this.quatita);
        dest.writeInt(this.prezzo);
        dest.writeSerializable(this.scadenza);
    }

    public ProdottoDisp() {
    }

    public ProdottoDisp(String nomeProdotto,Integer quatita,Integer prezzo,Date data) {
        this.nomeProdotto = nomeProdotto;
        this.quatita = quatita;
        this.prezzo = prezzo;
        this.scadenza=data;

    }

    protected ProdottoDisp(Parcel in) {
        this.nomeProdotto = in.readString();
        this.quatita = in.readInt();
        this.prezzo=in.readInt();
        this.scadenza=(Date) in.readSerializable();
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
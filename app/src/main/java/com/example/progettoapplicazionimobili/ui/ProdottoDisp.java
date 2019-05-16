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
    private byte[] img;

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

    public Integer getPrezzo() {
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

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
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
        dest.writeSerializable(this.img);
    }

    public ProdottoDisp() {
    }

    public ProdottoDisp(String nomeProdotto,Integer quatita,Integer prezzo,Date data,byte[] img) {
        this.nomeProdotto = nomeProdotto;
        this.quatita = quatita;
        this.prezzo = prezzo;
        this.scadenza=data;
        this.img=img;
    }

    protected ProdottoDisp(Parcel in) {
        this.nomeProdotto = in.readString();
        this.quatita = in.readInt();
        this.prezzo=in.readInt();
        this.scadenza=(Date) in.readSerializable();
        this.img= (byte[]) in.readSerializable();
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
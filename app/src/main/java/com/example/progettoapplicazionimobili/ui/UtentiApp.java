package com.example.progettoapplicazionimobili.ui;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class UtentiApp extends RealmObject implements Parcelable {

    @PrimaryKey
    private int id ;

    private String nomeUtente;
    private String passwordUtente;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNomeUtente(){return nomeUtente;}
    public void setNomeUtente(String nomeUtente) {
        this.nomeUtente = nomeUtente;
    }
    public String getPasswordUtente() {
        return passwordUtente;
    }
    public void setPasswordUtente(String passwordUtente) {
        this.passwordUtente = passwordUtente;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.nomeUtente);
        dest.writeString(this.passwordUtente);
    }

    public UtentiApp() {
    }

    public UtentiApp(int id, String nomeUtente, String passwordUtente) {
        this.id = id;
        this.nomeUtente = nomeUtente;
        this.passwordUtente = passwordUtente;
    }

    protected UtentiApp(Parcel in) {
        this.id = in.readInt();
        this.nomeUtente = in.readString();
        this.passwordUtente = in.readString();
    }

    public static final Creator<UtentiApp> CREATOR = new Creator<UtentiApp>() {
        @Override
        public UtentiApp createFromParcel(Parcel source) {
            return new UtentiApp(source);
        }

        @Override
        public UtentiApp[] newArray(int size) {
            return new UtentiApp[size];
        }
    };
}
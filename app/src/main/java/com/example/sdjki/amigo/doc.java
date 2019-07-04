package com.example.sdjki.amigo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.firestore.IgnoreExtraProperties;


@IgnoreExtraProperties
public class doc implements Parcelable{

    private String Date;
    private String Humidity;
    private String precip;
    private String pressure;
    private String temp;
    private String wind;
    private String flood;

    public doc(String Date, String Humidity, String precip, String pressure,String temp, String wind, String flood) {
        this.Date = Date;
        this.Humidity = Humidity;
        this.precip = precip;
        this.pressure = pressure;
        this.temp=temp;
        this.wind=wind;
        this.flood=flood;
    }

    public doc() {

    }

    protected doc(Parcel in) {
        Date = in.readString();
        Humidity = in.readString();
        precip = in.readString();
        pressure = in.readString();
        temp=in.readString();
        wind=in.readString();
        flood=in.readString();
    }

    public static final Parcelable.Creator<doc> CREATOR = new Creator<doc>() {
        @Override
        public doc createFromParcel(Parcel in) {
            return new doc(in);
        }

        @Override
        public doc[] newArray(int size) {
            return new doc[size];
        }
    };

    public String getpressure() {
        return pressure;
    }

    public void setpressure(String pressure) {
        this.pressure = pressure;
    }


    public String getwind() {
        return wind;
    }

    public void setwind(String wind) {
        this.wind = wind;
    }
    public String getflood() {
        return flood;
    }

    public void setflood(String flood) {
        this.flood = flood;
    }
    public String gettemp() {
        return temp;
    }

    public void settemp(String temp) {
        this.temp = temp;
    }
    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getHumidity() {
        return Humidity;
    }

    public void setHumidity(String Humidity) {
        this.Humidity = Humidity;
    }

    public String getprecip() {
        return precip;
    }

    public void setprecip(String precip) {
        this.precip = precip;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Date);
        parcel.writeString(Humidity);
        parcel.writeString(precip);
        parcel.writeString(pressure);
        parcel.writeString(temp);
        parcel.writeString(wind);
        parcel.writeString(flood);
    }
}

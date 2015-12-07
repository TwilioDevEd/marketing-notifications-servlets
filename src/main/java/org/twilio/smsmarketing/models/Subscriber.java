package org.twilio.smsmarketing.models;

import javax.persistence.*;

@SuppressWarnings("unused")
@Entity
@Table(name = "subscribers")
public class Subscriber {

    public Subscriber() {
    }

    public Subscriber(String phoneNumber){
        this.phoneNumber = phoneNumber;
        this.subscribed = false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "suscribed")
    private boolean subscribed;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }
}

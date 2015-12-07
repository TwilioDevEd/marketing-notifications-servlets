package org.twilio.smsmarketing.repositories;

import org.twilio.smsmarketing.models.Subscriber;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.twilio.smsmarketing.models.Subscriber;


public class SubscribersRepository extends Repository<Subscriber> {

    public SubscribersRepository() {
        super(Subscriber.class);
    }

    public Subscriber findByPhoneNumber(String phoneNumber){
        Subscriber subscriber = null;
        try {
            subscriber = (Subscriber) em.createQuery(
                    String.format("SELECT e FROM %s e WHERE e.phoneNumber = :phone_number", entityType.getSimpleName()))
                    .setParameter("phone_number", phoneNumber)
                    .getSingleResult();
        } catch (NoResultException ex) {
            System.out.println(ex.getMessage());
        }

        return subscriber;
    }

    public Iterable<Subscriber> findAllSubscribed(){
        Iterable<Subscriber> subscribers = null;
        try {
            subscribers = em.createQuery(
                    String.format("SELECT e FROM %s e WHERE e.subscribed = TRUE", entityType.getSimpleName()))
                    .getResultList();
        } catch (NoResultException ex) {
            System.out.println(ex.getMessage());
        }

        return subscribers;
    }
}



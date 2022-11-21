package com.example.social_bank.demo.services;

import com.example.social_bank.demo.util.JpaConn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

@Component("serviceDao")

public class ServiceDao {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    JpaConn conn;


    public List<ServicesTable> getServices() {

        try {
            List<ServicesTable> services =  conn.getEntityManager()
                    .createQuery("from Services").getResultList();
            return services;
        }catch (NoResultException noResultException){
            return null;
        }

    }

    public List<UserServices> getServices(int user_id) {

        try {
            List<UserServices> services =  conn.getEntityManager()
                    .createQuery("from Services").getResultList();

            List<UserServices> servicesTables = new ArrayList<>();

            for (int i =0; i<services.size(); i++){
                if (services.get(i).getUser_id() == user_id){
                    servicesTables.add(services.get(i));
                }
            }
            return servicesTables;
        }catch (NoResultException noResultException){
            return null;
        }

    }

    public String createUserService(UserServices e) {
        EntityTransaction txn = conn.getEntityManager().getTransaction();
        txn.begin();
        conn.getEntityManager().persist(e);
        txn.commit();
        return "done";
    }

    public void updateServiceStatus(int id, UserServices e) throws Exception {
        EntityTransaction txn = conn.getEntityManager().getTransaction();
        txn.begin();
        e.setUser_id(id);
        conn.getEntityManager().merge(e);
        conn.getEntityManager().flush();
        txn.commit();
    }
}

package com.example.social_bank.demo.complaints;

import com.example.social_bank.demo.comments.Comments;
import com.example.social_bank.demo.services.UserServices;
import com.example.social_bank.demo.util.JpaConn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.util.List;

public class ComplaintsDao {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    JpaConn conn;

    public String createComplaint(Complaints e) {
        EntityTransaction txn = conn.getEntityManager().getTransaction();
        txn.begin();
        conn.getEntityManager().persist(e);
        txn.commit();
        return "done";
    }

    public  List<Complaints> getComplaint(int user_id) {

        try {
           List<Complaints> comments = (List<Complaints>) conn.getEntityManager()
                    .createQuery("from Complaints where user_id=:user_id")
                    .setParameter("user_id", user_id).getResultList();
            return comments;
        }catch (NoResultException noResultException){
            return null;
        }

    }

}

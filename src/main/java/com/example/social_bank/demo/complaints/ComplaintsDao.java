package com.example.social_bank.demo.complaints;

import com.example.social_bank.demo.comments.Comments;
import com.example.social_bank.demo.services.UserServices;
import com.example.social_bank.demo.util.JpaConn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

@Component("complaintDao")
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
                    .createQuery("from Complaints").getResultList();

           List<Complaints> complaints = new ArrayList<>();

           for (int i =0;i<comments.size();i++){
               if (comments.get(i).getUser_id().contains(String.valueOf(user_id))){
                   complaints.add(comments.get(i));
               }
           }

            return complaints;
        }catch (NoResultException noResultException){
            return null;
        }

    }

}

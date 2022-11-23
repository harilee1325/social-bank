package com.example.social_bank.demo;

import com.example.social_bank.demo.account.Accounts;
import com.example.social_bank.demo.account.AccountsDao;
import com.example.social_bank.demo.account.Transaction;
import com.example.social_bank.demo.comments.Comments;
import com.example.social_bank.demo.comments.CommentsDao;
import com.example.social_bank.demo.complaints.Complaints;
import com.example.social_bank.demo.complaints.ComplaintsDao;
import com.example.social_bank.demo.services.ServiceDao;
import com.example.social_bank.demo.services.Services_Table;
import com.example.social_bank.demo.services.UserServices;
import com.example.social_bank.demo.user.UserDao;
import com.example.social_bank.demo.user.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Services {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier("bCryptEncoder")
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserDao dao;

    @Autowired
    AccountsDao accountsDao;

    @Autowired
    CommentsDao commentsDao;

    @Autowired
    ServiceDao serviceDao;

    @Autowired
    ComplaintsDao complaintsDao;

    public boolean createEmployee(Users e) {
        try {
            if (!validateEmployee(e)) {
                return false;
            }
            dao.createAdmin(e);
            return true;
        } catch (Exception ex) {
            logger.error(ex.getLocalizedMessage());
            ex.printStackTrace();
            return false;
        }
    }

    private boolean validateEmployee(Users e) {

        // Checks if password is good, if admin check they are L3 or L4
        if (e.getPassword() == null || e.getPassword().length() < 8) {
            return false;
        }
        return true;
    }


    public Users login(String email, String password) {
        Users user = dao.login(email, password);

        if (user != null) {
            System.out.println("login success");
            return user;
        }
        return null;
    }

    public boolean createComment(Comments e) {
        try {

            commentsDao.createComment(e);
            return true;
        } catch (Exception ex) {
            logger.error(ex.getLocalizedMessage());
            ex.printStackTrace();
            return false;
        }
    }

    public List<Comments> getAllComments() {
        return commentsDao.getAllComments();
    }

    public List<Comments> getComments(int id) {
        return commentsDao.getComments(id);
    }

    public Accounts getAccounts(int id) {
        return accountsDao.getAccount(id);
    }

    public List<Transaction> getTransaction() {
        return accountsDao.getTransaction();
    }
    public Users getUsers(int id) {
        return dao.getUser(id);
    }

    public boolean createAccount(Accounts accounts) {
        try {
            accountsDao.createAccount(accounts);
            return true;
        } catch (Exception ex) {
            logger.error(ex.getLocalizedMessage());
            ex.printStackTrace();
            return false;
        }
    }

    public boolean updateAccount(Long id, Accounts accounts) {
        try {
            accountsDao.updateAccount(id, accounts);
            return true;
        } catch (Exception ex) {
            logger.error(ex.getLocalizedMessage());
            ex.printStackTrace();
            return false;
        }
    }

    public List<Services_Table> getServices() {
        return serviceDao.getServices();
    }

    public List<UserServices> getServices(int user_id) {
        return serviceDao.getServices(user_id);
    }

    public boolean createService(UserServices userServices) {
        try {
            serviceDao.createUserService(userServices);
            return true;
        } catch (Exception ex) {
            logger.error(ex.getLocalizedMessage());
            ex.printStackTrace();
            return false;
        }
    }

    public boolean updateServiceStatus(int id, UserServices services) {
        try {
            serviceDao.updateServiceStatus(id, services);
            return true;
        } catch (Exception ex) {
            logger.error(ex.getLocalizedMessage());
            ex.printStackTrace();
            return false;
        }
    }

    public boolean createComplaint(Complaints complaints) {
        try {
            complaintsDao.createComplaint(complaints);
            return true;
        } catch (Exception ex) {
            logger.error(ex.getLocalizedMessage());
            ex.printStackTrace();
            return false;
        }
    }

    public List<Complaints> getComplaints(int userId) {
        return complaintsDao.getComplaint(userId);
    }

    public Accounts getAccountFromCard(String cardNo) {
        return accountsDao.getAccountWithDebitCard(cardNo);
    }

    public boolean createService(Services_Table userServices) {

        try {

            serviceDao.createService(userServices);
            return true;
        } catch (Exception ex) {
            logger.error(ex.getLocalizedMessage());
            ex.printStackTrace();
            return false;
        }
    }

    public Users getUser(String email) {
        Users user = dao.getUser(email);

        if (user != null) {
            System.out.println("login success");
            return user;
        }
        return null;
    }

    public void updateUserId(int userId, String creditCard) {
        try {
            accountsDao.updateUserId(userId, creditCard);
        } catch (Exception ex) {
            logger.error(ex.getLocalizedMessage());
            ex.printStackTrace();
        }
    }
}


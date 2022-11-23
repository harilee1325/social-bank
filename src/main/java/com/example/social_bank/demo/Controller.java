package com.example.social_bank.demo;


import com.example.social_bank.demo.account.AccountView;
import com.example.social_bank.demo.account.Accounts;
import com.example.social_bank.demo.account.Transaction;
import com.example.social_bank.demo.comments.CommentView;
import com.example.social_bank.demo.comments.Comments;
import com.example.social_bank.demo.complaints.ComplaintView;
import com.example.social_bank.demo.complaints.Complaints;
import com.example.social_bank.demo.services.ServiceView;
import com.example.social_bank.demo.services.Services_Table;
import com.example.social_bank.demo.services.UserServiceView;
import com.example.social_bank.demo.services.UserServices;
import com.example.social_bank.demo.user.LoginView;
import com.example.social_bank.demo.user.UserView;
import com.example.social_bank.demo.user.Users;
import com.example.social_bank.demo.util.ErrorView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@org.springframework.stereotype.Controller
@RestController
@RequestMapping(path = "/api")
public class Controller {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    Services services;

    @GetMapping("/")
    public ResponseEntity add(){

        Accounts accounts = new Accounts();
        accounts.setUser_id(-1);
        accounts.setCredit_card_number("1122334455");
        accounts.setBalance(1000.00);
        accounts.setCvv(123);
        accounts.setExp("12/25");

        Accounts accounts1 = new Accounts();
        accounts1.setUser_id(-1);
        accounts1.setCredit_card_number("1122334466");
        accounts1.setBalance(1000.00);
        accounts.setCvv(123);
        accounts.setExp("12/25");
        Services_Table servicesTable = new Services_Table();
        servicesTable.setDesc("desc");
        servicesTable.setService_name("Increase Limit");
        servicesTable.setId(1);

        Services_Table servicesTable1 = new Services_Table();
        servicesTable1.setDesc("desc");
        servicesTable1.setService_name("Deactivate");
        servicesTable1.setId(2);

        if (services.createService(servicesTable)){
            if (services.createService(servicesTable1)){
                if (services.createAccount(accounts) &&  services.createAccount(accounts1)){
                    return new ResponseEntity(new ErrorView("Success"), HttpStatus.UNAUTHORIZED);

                }

            }
        }

        return new ResponseEntity(new ErrorView("Error"), HttpStatus.UNAUTHORIZED);

    }


    @CrossOrigin(origins = "http://localhost:3001")


    @PostMapping("/create")
    public ResponseEntity createEmployeePost(@RequestBody UserView userView) {
        logger.info("Creating user {}", userView.getName());
        Users emp = new Users();
        emp.setEmail(userView.getEmail());
        emp.setName(userView.getName());
        emp.setPassword(userView.getPassword());// hashing in dao
        emp.setMobileNumber(userView.getMobileNumber());
        emp.setUsername(userView.getUsername());

        Accounts accounts = services.getAccountFromCard(userView.getCreditCard());

        if (accounts!=null){
            if (services.createEmployee(emp)) {
                Users users = services.getUser(userView.getEmail());
                services.updateUserId(users.getId(), userView.getCreditCard() );
                return new ResponseEntity(new ErrorView("Account created"), HttpStatus.CREATED);
            }
        }else{
            return new ResponseEntity(new ErrorView("User does not exist"), HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity(new ErrorView("Error"), HttpStatus.UNAUTHORIZED);
    }

    @CrossOrigin(origins = "http://localhost:3001")
    @PostMapping("/add_comment")
    public String createComment(@RequestBody CommentView commentView) {
        logger.info("Creating comment {}", commentView.getComment());
        Users acc = services.getUsers(Integer.parseInt(commentView.getUserId()));

        Comments comments = new Comments();
        comments.setComment(commentView.getComment());
        comments.setUser_id(commentView.getUserId());
        comments.setUsername(acc.getName());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        String date = dtf.format(now);
        comments.setDate(date);

        if (services.createComment(comments)) {
            return "redirect:create?success=true";
        }
        return "redirect:create?error=true";
    }

    @CrossOrigin(origins = "http://localhost:3001")
    @GetMapping("/all_comments")
    public ResponseEntity getAllComments() {
        try{
            List<Comments> comments = services.getAllComments();
            return new ResponseEntity(comments, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(new ErrorView("Error"), HttpStatus.FORBIDDEN);
        }
    }

    @CrossOrigin(origins = "http://localhost:3001")
    @GetMapping("/all_comments/{user_id}")
    public ResponseEntity getAllComments(@PathVariable("user_id") int userId) {
        try{
            List<Comments> comments = services.getComments(userId);
            return new ResponseEntity(comments, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(new ErrorView("Error"), HttpStatus.FORBIDDEN);
        }
    }

    @CrossOrigin(origins = "http://localhost:3001")
    @GetMapping("/all_transaction")
    public ResponseEntity getTransaction() {
        try{
            List<Transaction> transactions = services.getTransaction();
            return new ResponseEntity(transactions, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(new ErrorView("Error"), HttpStatus.FORBIDDEN);
        }
    }

    @CrossOrigin(origins = "http://localhost:3001")
    @PostMapping("/create_account")
    public String createAccount(@RequestBody AccountView accountView) {
        logger.info("Creating user {}", accountView.getUserId());


        Accounts acc = services.getAccounts(Integer.parseInt(accountView.getUserId()));
        Accounts accounts = new Accounts();
        accounts.setUser_id(Integer.parseInt((accountView.getUserId())));
        accounts.setCredit_card_number(((accountView.getCreditCard())));
        accounts.setExp(((accountView.getExp())));
        accounts.setCvv(Integer.parseInt((accountView.getCvv())));
        accounts.setName(((accountView.getName())));

        if (acc!=null){
            accounts.setBalance(acc.getBalance()+Double.parseDouble(accountView.getBalance()));
            if (services.updateAccount(acc.getId(), accounts)) {
                return "redirect:create?success=true";
            }
            return "redirect:create?error=true";
        }
        accounts.setBalance(Double.valueOf(accountView.getBalance()));
        if (services.createAccount(accounts)) {
            return "redirect:create?success=true";
        }
        return "redirect:create?error=true";
    }




    @CrossOrigin(origins = "http://localhost:3001")
    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody LoginView loginView) {
        Users user = services.login(loginView.getEmail(), loginView.getPassword());
        if (user!=null){
            return new ResponseEntity(new UserView(user.getEmail(), user.getName(), user.getId(), user.getUsername()), HttpStatus.OK);
        }
        return new ResponseEntity(new ErrorView("Cannot validate user"), HttpStatus.FORBIDDEN);

    }

    @CrossOrigin(origins = "http://localhost:3001")
    @GetMapping("/get_account/{user_id}")
    public ResponseEntity getSpecificAccount(@PathVariable("user_id") int id) {
        try{

            Accounts acc = services.getAccounts((id));

            return new ResponseEntity(new AccountView(String.valueOf(acc.getBalance()), String.valueOf(acc.getUser_id()), String.valueOf(acc.getCredit_card_number()), String.valueOf(acc.getExp()), String.valueOf(acc.getName()), String.valueOf(acc.getCvv())), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(new ErrorView("Error"), HttpStatus.FORBIDDEN);
        }
    }

    @CrossOrigin(origins = "http://localhost:3001")
    @GetMapping("/all_services")
    public ResponseEntity getServices() {
        try{
            List<Services_Table> servicesTables = services.getServices();
            return new ResponseEntity(servicesTables, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(new ErrorView("Error"), HttpStatus.FORBIDDEN);
        }
    }

    @CrossOrigin(origins = "http://localhost:3001")
    @GetMapping("/services/{user_id}")
    public ResponseEntity getServices(@PathVariable("user_id") int user_id) {
        try{
            List<UserServices> servicesTables = services.getServices(user_id);
            return new ResponseEntity(servicesTables, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(new ErrorView("Error"), HttpStatus.FORBIDDEN);
        }
    }


    @CrossOrigin(origins = "http://localhost:3001")
    @PostMapping("/make_service")
    public String makeService(@RequestBody ServiceView serviceView) {
        logger.info("Creating service {}", serviceView.getDesc());
        Services_Table userServices = new Services_Table();
        userServices.setService_name(serviceView.getName());
        userServices.setDesc(serviceView.getDesc());

        if (services.createService(userServices)) {
            return "redirect:create?success=true";
        }
        return "redirect:create?error=true";
    }

    @CrossOrigin(origins = "http://localhost:3001")
    @PostMapping("/create_service")
    public String createService(@RequestBody UserServiceView userView) {
        logger.info("Creating service {}", userView.getServiceId());
        UserServices userServices = new UserServices();
        userServices.setService_id(Integer.valueOf(userView.getServiceId()));
        userServices.setUser_id(Integer.parseInt(userView.getUserId()));
        userServices.setStatus(userView.getStatus());

        if (services.createService(userServices)) {
            return "redirect:create?success=true";
        }
        return "redirect:create?error=true";
    }


    @CrossOrigin(origins = "http://localhost:3001")

    @PostMapping("/add_complaint")
    public String createComplaint(@RequestBody ComplaintView complaintView) {
        logger.info("Creating complaint {}", complaintView.getComplaint());
        Complaints complaints = new Complaints();
        complaints.setComplaint(complaintView.getComplaint());
        complaints.setUser_id(complaintView.getUserId());
        complaints.setStatus(complaintView.getStatus());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        String date = dtf.format(now);
        complaints.setDate(date);

        if (services.createComplaint(complaints)) {
            return "redirect:create?success=true";
        }
        return "redirect:create?error=true";
    }

    @CrossOrigin(origins = "http://localhost:3001")
    @GetMapping("/get_complaint/{user_id}")
    public ResponseEntity getComplaint(@PathVariable("user_id") int id) {
        try{

            List<Complaints> complaints = services.getComplaints((id));

            return new ResponseEntity(complaints, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(new ErrorView("Error"), HttpStatus.FORBIDDEN);
        }
    }
}

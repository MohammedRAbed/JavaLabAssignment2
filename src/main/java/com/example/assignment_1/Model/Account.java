package com.example.assignment_1.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Account {
    private int id;
    private int user_id;
    private int account_number;
    private String username;
    private String currency;
    private double balance;
    private String creation_date;

    public Account(int userId, int accountNum, String username, String currency, double balance, String date) {
        this.user_id = userId;
        this.account_number = accountNum;
        this.username = username;
        this.currency = currency;
        this.balance = balance;
        this.creation_date = date;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUserId() {
        return user_id;
    }
    public int getAccountNumber() {
        return account_number;
    }
    public String getUsername() {
        return username;
    }
    public String getCurrency() {
        return currency;
    }
    public double getBalance() {
        return balance;
    }
    public String getCreationDate() {
        return creation_date;
    }

    //create a new account in accounts table
    public int save() throws SQLException, ClassNotFoundException{
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter =0;
        String sql = "INSERT INTO ACCOUNTS (ID,USER_ID,ACCOUNT_NUMBER,USERNAME,CURRENCY,BALANCE,CREATION_DATE) VALUES (?, ?, ?, ?,?,?,?)";
        ps = c.prepareStatement(sql);
        ps.setInt(1, this.getId());
        ps.setInt(2,this.getUserId());
        ps.setInt(3, this.getAccountNumber());
        ps.setString(4, this.getUsername());
        ps.setString(5, this.getCurrency());
        ps.setDouble(6, this.getBalance());
        ps.setString(7,this.getCreationDate());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println(this.getUsername()
                    +" User was added successfully!");
        }
        if (ps != null){
            ps.close();
        }
        c.close();
        return recordCounter;
    }


    //get all accounts from accounts table
    public static ArrayList<Account> getAllAccounts() throws SQLException, ClassNotFoundException{
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM ACCOUNTS";
        ps = c.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()){
            Account account = new Account(rs.getInt("user_id"),/*rs.getInt("account_number")*/111,rs.getString("username"),rs.getString("currency"),rs.getDouble("balance"),/*rs.getString("creation_date")*/"111");
            account.setId(rs.getInt("id"));
            accounts.add(account);

        }
        if (ps != null){
            ps.close();
        }
        c.close();
        return accounts;
    }


    //update an existing user in users table
    public int update() throws SQLException, ClassNotFoundException{
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter =0;
        String sql = "UPDATE ACCOUNTS SET USERNAME=?, CURRENCY=?, BALANCE=? , CREATION_DATE=? WHERE ID=?";
        ps = c.prepareStatement(sql);
        ps.setString(1,this.getUsername());
        ps.setString(2, this.getCurrency());
        ps.setDouble(3, this.getBalance());
        ps.setString(4, this.getCreationDate());
        ps.setInt(5, this.getId());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println("User with id : "+this.getId()+" was updated successfully!");
        }
        if (ps != null){
            ps.close();
        }
        c.close();
        return recordCounter;
    }


    //delete an user from users table
    public int delete() throws SQLException, ClassNotFoundException{
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter =0;
        String sql = "DELETE FROM ACCOUNTS WHERE ID=? ";
        ps = c.prepareStatement(sql);
        ps.setInt(1, this.getId());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println("The user with id : "+this.getId()+" was deleted successfully!");
        }
        if (ps != null){
            ps.close();
        }
        c.close();
        return recordCounter;
    }
}

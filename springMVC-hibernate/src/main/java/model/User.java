package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "USER")
public class User implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "userId")
    private int userId;

    @Column(name = "userName")
    private String userName;

    @Column(name="balance")
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public User(){
    }

    @ManyToOne
    @JoinColumn(name="branchCode")
    private Bank bank;

    public int getUserId() {

        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {

        return userName;
    }

    public void setUserName(String userName) {

        this.userName = userName;
    }
}


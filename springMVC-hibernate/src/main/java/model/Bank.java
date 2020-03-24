package model;

import javax.persistence.*;

@Entity
public class Bank {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="branchCode")
    private String branchCode;

    @Column(name="branchName")
    private String branchName;

    @OneToMany
     private User user;

    public String getBranchCode()
    {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {

        this.branchCode = branchCode;
    }

    public void setBranchName(String branchName) {
    }
}


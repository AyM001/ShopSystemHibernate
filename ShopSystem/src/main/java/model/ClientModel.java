package model;

import javax.persistence.*;


@Entity
public class ClientModel {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idClient;
    private String username;
    private int balance;


    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}

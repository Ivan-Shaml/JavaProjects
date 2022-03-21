package com.ivan.Bank;

public class Main {

    public static void main(String[] args) {

        Account vasil = new Account("Vasil Petrov", "0012512");

        Account petar = new Account("Petar Georgiev", "0018541");

        petar.showMenu();
    }

}
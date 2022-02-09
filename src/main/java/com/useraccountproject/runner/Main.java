package com.useraccountproject.runner;

import com.useraccountproject.connectionSetup.Configuration;
import com.useraccountproject.databaseQueries.AccountQueries;
import com.useraccountproject.databaseQueries.TransactionQueries;
import com.useraccountproject.databaseQueries.UsersQueries;
import com.useraccountproject.model.Transaction;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        printMenuForSelect();
        selectTheDesiredMenuItem();
    }

    public static void printMenuForSelect() {
        System.out.println(
                "Меню:" + "\n"
                        + "1.Зарегистрировать нового пользователя" + "\n"
                        + "2.Добавить аккаунт для нового пользователя" + "\n"
                        + "3.Пополнить существующий аккаунт" + "\n"
                        + "4.Снять средства с существующего аккаунта" + "\n"
        );
        System.out.print("Сделайте ваш выбор:");
    }

    //Method for select item menu
    public static void selectTheDesiredMenuItem() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        UsersQueries usersQueries = new UsersQueries();
        AccountQueries accountQueries = new AccountQueries();
        TransactionQueries transactionQueries = new TransactionQueries();
        int userId;
        int numMenu = scanner.nextInt();
        switch (numMenu) {
            case 1:
                System.out.println("Регистрация нового пользователя");
                usersQueries.registerNewUser();
                break;
            case 2:
                System.out.println("Введите свой номер телефона для проверки наличия вас в базе данных:");
                userId = scanner.nextInt();
                if (usersQueries.isUsersExists(userId)) {
                    accountQueries.addNewAccount();
                } else {
                    System.out.println("Ваших данных в базе нет.Создайте профиль");
                }
                break;
            case 3:
                System.out.println("Введите свой номер телефона для получения информации о балансе:");
                userId = scanner.nextInt();
                if (accountQueries.isAccountExists(userId)) {
                    accountQueries.getBalance(userId);
                    accountQueries.getCurrency(userId);
                    System.out.println("Введите сумму, которую хотите положить");
                    float topUpamount = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Введите в какой валюте:");
                    String currency = scanner.nextLine();
                    transactionQueries.addTransction(topUpamount);
                    accountQueries.replanishTheBalance(topUpamount, userId, currency);
                } else {
                    System.out.println("Ваших данных в базе нет.Создайте профиль");
                }
                break;
            case 4:
                System.out.println("Введите свой номер телефона для получения информации о балансе:");
                userId = scanner.nextInt();
                if (accountQueries.isAccountExists(userId)) {
                    accountQueries.getBalance(userId);
                    accountQueries.getCurrency(userId);
                    System.out.println("Введите сумму, которую хотите снять");
                    float topUpamount = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Введите в какой валюте:");
                    String currency = scanner.nextLine();
                    transactionQueries.addTransction(topUpamount);
                    accountQueries.windrawCash(topUpamount, userId, currency);
                    System.out.println("Наличные сняты");
                } else {
                    System.out.println("Ваших данных в базе нет.Создайте профиль");
                }
                break;
            default:
                System.out.println("Вы ввели неверную команду выбора");
        }
    }
}

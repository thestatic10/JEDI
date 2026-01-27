package com.flipkart.client;

import com.flipkart.bean.FlipFitRole;
import com.flipkart.exceptions.LoginFailedException;

import java.text.ParseException;
import java.util.Scanner;

public class FlipFitApplication {
    public static Scanner scanner = new Scanner(System.in);
    private static FlipFitAdminMenu adminClient = new FlipFitAdminMenu();
    private static FlipFitCustomerMenu customerClient = new FlipFitCustomerMenu();
    private static FlipFitGymOwnerMenu gymOwnerClient = new FlipFitGymOwnerMenu();

    private static void mainPage() {
        System.out.println("1. Login\n2. Registration\n3. Change Password\n4. Exit");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                registration();
                break;
            case 4:
                System.out.println("Thanks for visiting!");
                return;
            default:
                System.out.println("Invalid choice selected");
                break;
        }
        mainPage();
    }

    private static void login() {
        try {
            System.out.println("Choose your Role:");
            System.out.println("1. Admin");
            System.out.println("2. Gym Owner");
            System.out.println("3. Customer");
            int roleChoice = scanner.nextInt();

            FlipFitRole role = null;
            switch (roleChoice) {
                case 1:
                    role = FlipFitRole.ADMIN;
                    break;
                case 2:
                    role = FlipFitRole.GYM_OWNER;
                    break;
                case 3:
                    role = FlipFitRole.CUSTOMER;
                    break;
                default:
                    System.out.println("Invalid Option Selected");
                    return;
            }

            System.out.println("Enter your Username");
            String userName = scanner.next();

            System.out.println("Enter your Password");
            String password = scanner.next();

            switch (role) {
                case ADMIN:
                    adminClient.adminLogin(userName, password);
                    break;
                case GYM_OWNER:
                    gymOwnerClient.gymOwnerLogin(userName, password);
                    break;
                case CUSTOMER:
                    customerClient.customerLogin(userName, password);
                    break;
                default:
                    System.out.println("Invalid Option Selected");
                    break;
            }
        } catch (IllegalArgumentException | ParseException | LoginFailedException e) {
            System.out.println("Invalid Option Selected");
        }
    }

    private static void registration() {
        try {
            System.out.println("Choose your Role:");
            System.out.println("1. Admin");
            System.out.println("2. Gym Owner");
            System.out.println("3. Customer");
            int roleChoice = scanner.nextInt();

            FlipFitRole role = null;
            switch (roleChoice) {
                case 1:
                    role = FlipFitRole.ADMIN;
                    break;
                case 2:
                    role = FlipFitRole.GYM_OWNER;
                    break;
                case 3:
                    role = FlipFitRole.CUSTOMER;
                    break;
                default:
                    System.out.println("Invalid Option Selected");
                    return;
            }

            switch (role) {
                case ADMIN:
                    adminClient.register();
                    break;
                case CUSTOMER:
                    customerClient.register();
                    break;
                case GYM_OWNER:
                    gymOwnerClient.register();
                    break;
                default:
                    System.out.println("Invalid Option Selected");
                    break;
            }
        } catch (IllegalArgumentException | ParseException e) {
            System.out.println("Invalid Option Selected");
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to FlipFit Application!\n");
        mainPage();
    }

}

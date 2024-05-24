package com.cashwu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

/**
 * @author cash.wu
 * @since 2024/05/24
 */
@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    private MyGateway myGateway;

    public static void main(String[] args) {
        SpringApplication.run(Main.class,
                              args);

    }

    @Override
    public void run(String... args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("enter text : (type 'exit' to quit)");

        while (true) {
            String input = scanner.nextLine();

            if ("exit".equalsIgnoreCase(input)) {
                break;
            }

            myGateway.send(input);
        }

        scanner.close();

    }
}
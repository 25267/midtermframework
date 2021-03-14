package org.example;

import org.example.Configuration.Config;
import org.example.Dao.UserDao;
import org.example.controller.ManageController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NotesSystem {
    private static Boolean bool = true;
    private static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                Config.class
        );

        ManageController manageController = context.getBean("manageController", ManageController.class);

        System.out.println("1. To Login");
        System.out.println("2. TO register");
        System.out.println("0. Quit");

        String ch = read.readLine();

        if (ch.equals("1")) {


            if (manageController.login()) {


                while (bool) {
                    System.out.println("1. To Return all My Notes");
                    System.out.println("2. To update Note");
                    System.out.println("3. to Add new Note");
                    System.out.println("4. To change status of the Note");
                    System.out.println("5. To delete Note");
                    System.out.println("0. To Quit");
                    String choice = read.readLine();

                    switch (choice) {
                        case "1":
                            manageController.selectAll();
                            break;
                        case "2":
                            manageController.updateNote();
                            break;
                        case "3":
                            manageController.addNote();
                            break;
                        case "4":
                            manageController.changeStatus();
                            break;
                        case "5":
                            manageController.deleteNote();
                            break;
                        default:
                            bool = false;
                    }

                }


            } else {
                System.out.println("wrong data");

            }
        } else if (ch.equals("2")) {

            manageController.registration();

        } else {
            System.out.println("Bye");
        }

    }
}

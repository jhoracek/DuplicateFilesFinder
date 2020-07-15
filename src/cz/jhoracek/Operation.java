package cz.jhoracek;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

public class Operation {
    public static final String IGNORE = "0";
    public static final String DELETE = "1";
    public static final String CREATE_SOFTLINK = "2";
    public static final String CREATE_HARDLINK = "3";

    public static boolean ignore(ArrayList<File> duplicate) {
        return true;
    }

    public static void delete(ArrayList<File> duplicate) {

        for (File file : duplicate) {
            System.out.println("Do you want to delete " + file.getName() + "? (y/n)");
            Scanner sc = new Scanner(System.in);
            String action = sc.nextLine();
            switch (action) {
                case "y":
                    file.delete();
                    break;
                case "n":
                    continue;
                default:
                    System.out.println("Valid answers are : y/n");
            }
//            return true;
        }
    }

    public static void createSoftLink(ArrayList<File> duplicate) {
        System.out.println("File for which to create soft link: (file number)");
        Scanner sc = new Scanner(System.in);
        int fileNumber = Integer.parseInt(sc.nextLine());

        if (fileNumber < duplicate.size()) {

            for (int i = 0; i < duplicate.size(); i++) {

                if (i != fileNumber) {
                    try {
                        Files.delete(duplicate.get(i).toPath());
                        Files.createSymbolicLink(duplicate.get(i).toPath(), duplicate.get(fileNumber).toPath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            }

        } else {
            System.out.println("Wrong file number.");
        }
    }

    public static void createHardLink(ArrayList<File> duplicate) {
        System.out.println("File for which to create hard link: (file number)");
        Scanner sc = new Scanner(System.in);
        int fileNumber = Integer.parseInt(sc.nextLine());

        if (fileNumber < duplicate.size()) {

            for (int i = 0; i < duplicate.size(); i++) {

                if (i != fileNumber) {
                    try {
                        Files.delete(duplicate.get(i).toPath());
                        Files.createLink(duplicate.get(i).toPath(), duplicate.get(fileNumber).toPath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            }

        } else {
            System.out.println("Wrong file number.");
        }


//        public static boolean CrateSofLink (ArrayList < File > duplicate) {
//            return true;
//        }
//        public static boolean CreateHardLink (ArrayList < File > duplicate) {
//            return true;
//        }


    }
}

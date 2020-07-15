package cz.jhoracek;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        //System.out.println(args[0]);
        Directory directory = new Directory(args[0]);
//        System.out.println(directory.findDuplicates());

        List<ArrayList<File>> duplicates = directory.findDuplicates();

        for (ArrayList<File> duplicate : duplicates) {
            printMenu(duplicate);

            Scanner sc = new Scanner(System.in);
            String operation = sc.nextLine();
            runOperation(operation,duplicate);

        }


//        System.out.println("Choose operation: 0) Ignore 1) Delete 3) Create hardlink 4) Create softlink");
//        Scanner sc = new Scanner(System.in);
//        String option = sc.nextLine();
//        char znak = option.charAt(0);
//
//        System.out.print(new Menu((znak)));


    }

    private static void printMenu(ArrayList<File> files ) {
        System.out.println("Found duplicate files:");
        for (int i = 0; i < files.size(); i++) {
            System.out.println(i + ". " + files.get(i).getName());
        }


        System.out.println("Choose what to do with duplicate: 0) Ignore 1) Delete 2) Create softlink 3) Create hard link ");

    }

    private static void runOperation(String operation, ArrayList<File> duplicate){

        switch (operation) {
            case Operation.IGNORE:
                Operation.ignore(duplicate);
                break;
            case Operation.DELETE:
                Operation.delete(duplicate);
                break;
            case Operation.CREATE_SOFTLINK:
                Operation.createSoftLink(duplicate);
                break;
            case Operation.CREATE_HARDLINK:
                Operation.createHardLink(duplicate);
                break;
            default:

        }


        }



    }










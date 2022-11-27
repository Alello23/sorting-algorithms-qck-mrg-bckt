package src;

import java.lang.reflect.Field;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class Menu {

    public static void runSelectedOption(int option, LinkedList<Vessel> vessels) {

        long initialTime;
        long executionTime;

        switch (option) {

            case 1:
                initialTime = System.currentTimeMillis();
                SortingAlgorithms.quickSortAge(vessels);
                executionTime = System.currentTimeMillis() - initialTime;

                System.out.println("\nThe vessels have been sorted by age using QuickSort");
                printSortedObjectList(vessels, false);
                System.out.println("\nSorting algorithm execution time: " + executionTime + " ms");
                break;

            case 2:
                //Ordena la lista mediante la funcion de mergeSort
                initialTime = System.currentTimeMillis();
                SortingAlgorithms.mergeSortName(vessels);
                executionTime = System.currentTimeMillis() - initialTime;

                System.out.println("\nThe vessels have been sorted by name using MergeSort");
                printSortedObjectList(vessels, true);
                System.out.println("\nSorting algorithm execution time: " + executionTime + " ms");
                break;

            case 3:
                SortingAlgorithms.bucketSortCapabilities(vessels);
                break;

            case 4:
                System.out.print("\n───────────────────────────────────────────────────────────\n" +
                        "─██████████████───████████──████████─██████████████─██████─\n" +
                        "─██░░░░░░░░░░██───██░░░░██──██░░░░██─██░░░░░░░░░░██─██░░██─\n" +
                        "─██░░██████░░██───████░░██──██░░████─██░░██████████─██░░██─\n" +
                        "─██░░██──██░░██─────██░░░░██░░░░██───██░░██─────────██░░██─\n" +
                        "─██░░██████░░████───████░░░░░░████───██░░██████████─██░░██─\n" +
                        "─██░░░░░░░░░░░░██─────████░░████─────██░░░░░░░░░░██─██░░██─\n" +
                        "─██░░████████░░██───────██░░██───────██░░██████████─██████─\n" +
                        "─██░░██────██░░██───────██░░██───────██░░██────────────────\n" +
                        "─██░░████████░░██───────██░░██───────██░░██████████─██████─\n" +
                        "─██░░░░░░░░░░░░██───────██░░██───────██░░░░░░░░░░██─██░░██─\n" +
                        "─████████████████───────██████───────██████████████─██████─\n" +
                        "───────────────────────────────────────────────────────────\n");
                break;

        }
    }


    static int askForInteger(String message, int min, int max) {

        Scanner s = new Scanner(System.in);

        int option = min - 1;

        do {
            System.out.print(message);
            try {
                option = s.nextInt();
                if (option < min || option > max) {
                    System.out.printf("\nPlease enter a number between %d and %d.%n\n", min, max);
                }
            } catch (InputMismatchException e) {
                System.out.println("\nPlease enter a number.\n");
            } finally {
                s.nextLine();
            }
        } while (option < min || option > max);

        return option;
    }

    public static LinkedList<Vessel> DatasetSelection() {

        LinkedList<Vessel> vessels;
        int option;

        System.out.println("\nWelcome to CatTheHobie!");

        System.out.println("\nDataset selection");

        System.out.print("\n\t1) fleetXS.txt");
        System.out.print("\n\t2) fleetS.txt");
        System.out.print("\n\t3) fleetM.txt");
        System.out.println("\n\t4) fleetL.txt\n");

        option = Menu.askForInteger("Please choose an option: ", 1, 4);

        switch (option) {
            case 1:
                vessels = DatasetLoader.loadVessels("fleetXS.txt");
                break;

            case 2:
                vessels = DatasetLoader.loadVessels("fleetS.txt");
                break;

            case 3:
                vessels = DatasetLoader.loadVessels("fleetM.txt");
                break;

            case 4:
                vessels = DatasetLoader.loadVessels("fleetL.txt");
                break;

            default:
                vessels = DatasetLoader.loadVessels("fleetXS.txt");
                break;
        }


        return vessels;
    }

    public static void show() {
        System.out.println(
                        "\n--------------------------------------------------------------------\n" +
                        "\nWhat do you want to do?\n\n" +
                        "   1. Sort vessels by age\n" +
                        "   2. Sort vessels by name\n" +
                        "   3. Sort vessels by capabilities\n" +
                        "   4. Exit\n\n--------------------------------------------------------------------\n");
    }

    private static void printSortedObjectList(LinkedList<Vessel> vessels, Boolean ascendingOrder) {
        int i;

        if (ascendingOrder) {
            for (i = 0; i < vessels.size(); i++) {
                printObjectFields(vessels, i);
            }
        } else {
            for (i = vessels.size() - 1; i >= 0; i--) {
                printObjectFields(vessels, i);
            }
        }
    }

    private static void printObjectFields(LinkedList<Vessel> vessels, int i) {
        System.out.print("\n\n");
        for (Field field : vessels.get(i).getClass().getFields()) {
            String name = field.getName();
            Object value;
            try {
                value = field.get(vessels.get(i));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            System.out.printf("%s: %s%n", name, value);
        }
    }

}

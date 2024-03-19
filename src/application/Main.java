package application;

import exceptions.ActionsExceptions;
import services.OptionMenu;

import java.util.Locale;

public class Main {

    public static void main(String[] args) throws ActionsExceptions {

        Locale.setDefault(Locale.US);

        Integer actionChoice = OptionMenu.runMenu();

        if (actionChoice == -1) {
            OptionMenu.endRun();
        }

        OptionMenu.runAction(actionChoice);


//        System.out.println("Hello and welcome!");
//        System.out.println();
//
//
//        Branch branch1 = new Branch("BEBEDOURO", "SP", "000100");
//        System.out.println(branch1);
//
//        Customer camila = new Customer("Camila Fukuda", "123489489411");
//
//        AbstractAccount accP = new PersonAccount("001549489", branch1, camila, 12900.50, 200.90);
//        System.out.println(accP);
//
//        System.out.printf("getBalance: %.2f", accP.getBalance());
//        System.out.println();
//        System.out.printf("getLimit: %.2f", accP.getLimit());
//        System.out.println();
//        System.out.printf("getBranch: %s", accP.getBranch());
//        System.out.println();
//        System.out.printf("getType: %s", accP.getType());
//        System.out.println();
//        System.out.printf("getCustomer: %s", accP.getCustomer());
//        System.out.println();
//        System.out.printf("getClass: %s", accP.getClass());
//        System.out.println();
//
//        accP.setBalance(0);
//        System.out.printf("getBalance: %.2f", accP.getBalance());
//        System.out.println();
//
//        accP.setLimit(10000);
//        System.out.printf("getLimit: %.2f", accP.getLimit());
//        System.out.println();
//
//        accP.setBranch(new Branch("TAIUVA", "SP", "000200"));
//        System.out.printf("getBranch: %s", accP.getBranch());
//        System.out.println();

    }
}
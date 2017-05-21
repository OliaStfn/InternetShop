package com.stef.MagazineProject.domain;


import org.apache.log4j.Logger;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Register implements Serializable {

    private static ArrayList<Sale> sales = new ArrayList<Sale>();
    private static final Logger log = Logger.getLogger(Register.class);


    public static Sale creatSale() {
        Sale temp = new Sale();
        sales.add(temp);
        return temp;
    }


    public static void addItemsToSale(Sale sale) {

        Scanner in = new Scanner(System.in);
        int repeat = -1;
        do {

            try {

                Product pr1 = Stock.findProduct();
                System.out.print("Enter quantity  of product: ");
                int quantity =  Integer.parseInt(in.next());
                if (quantity <= 0) {
                    throw new Exception();
                }

               // sale.addLine(quantity,pr1.getName(), pr1.getDescription(), pr1.getPrice(),
               //         pr1.getVendor(),pr1.getWarrantyMonth());
                System.out.println("Add other product?");
                System.out.println("1-YES / 0-NO :");
                repeat =  Integer.parseInt(in.next());
            } catch (Exception e) {
                System.out.println("Quantity less one");
                log.error("Quantity less one" + e.getMessage());
            }

        } while (repeat != 0);
        log.info(sale.toString());

    }
}

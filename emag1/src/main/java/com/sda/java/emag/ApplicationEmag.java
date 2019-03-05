package com.sda.java.emag;

import com.sda.java.emag.businesslogic.CartController;
import com.sda.java.emag.businesslogic.User;
import com.sda.java.emag.item.Colours;
import com.sda.java.emag.item.Phone;
import com.sda.java.emag.item.Shoes;
import com.sda.java.emag.businesslogic.Cart;
import com.sda.java.emag.businesslogic.Stock;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationEmag {
    private static final String PHONE_NAME = "X";
    private static final float PRICE = 1000f;
    private static final String BRAND = "Iphone";
    private static final String CPU = "A10";
    private static final float DISPLAY_SIZE = 5.5f;
    private static final float COMPARE_DOUBLE_DELTA = 0.01f;
    private static final String SHOES_MODEL = "Nike air";
    private static final int SHOES_PRICE = 200;
    private static final String SHOES_BRAND = "Nike";
    private static final int SHOES_SIZE = 24;
    private static final int AVAILABLE_QUANTITY = 100000;
    private static final int REQUESTED_QUANTITY = AVAILABLE_QUANTITY;


    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        final Object stockMutex = new Object();
        final Stock baneasaMall = new Stock(new ConcurrentHashMap<>(), stockMutex);

        baneasaMall.loadState();
       // System.out.println("Loaded items: " + baneasaMall.showItems());
        final Phone iphoneX = new Phone(PHONE_NAME, PRICE, BRAND, CPU, DISPLAY_SIZE);
       // final Shoes nikeAir = new Shoes(SHOES_MODEL, SHOES_PRICE, SHOES_BRAND, SHOES_SIZE, Colours.GREEN);
        baneasaMall.addItem(iphoneX, REQUESTED_QUANTITY);

        final User anca = new User(new CartController(baneasaMall), iphoneX, REQUESTED_QUANTITY);
        final User george = new User(new CartController(baneasaMall), iphoneX, REQUESTED_QUANTITY);
        final Thread ancaThread = new Thread(anca);
        final Thread georgeThread = new Thread(george);

        ancaThread.start();
        georgeThread.start();

        ancaThread.join();
        georgeThread.join();
        int ancaRetrievedItemsQuantity = anca.getRetrievedItemsQuantity();
        System.out.println("Anca's retrieved items: " + ancaRetrievedItemsQuantity);
        int georgeRetrievedItemsQuantity = george.getRetrievedItemsQuantity();
        System.out.println("George's retrieved items: " + georgeRetrievedItemsQuantity);
        System.out.println("Total items: " + (ancaRetrievedItemsQuantity + georgeRetrievedItemsQuantity));
        System.out.println("Stock Items: \n" + baneasaMall.showItems());
        //UseStock
        //UseCart
        baneasaMall.saveState();
    }

    public static void useStock(Stock baneasaMall, Phone iphoneX) {
        final int supply_quantity = 10;
        final int consume_quantity = 3;
        final int currentIphoneStock = baneasaMall.addItem(iphoneX, supply_quantity);
        System.out.println("Initial Iphone X businesslogic quantity: " + currentIphoneStock);

        int retrievedIphoneStock = baneasaMall.retrieveItem(iphoneX, consume_quantity);
        System.out.println("Received Iphone X quantity1: " + retrievedIphoneStock);

        retrievedIphoneStock = baneasaMall.retrieveItem(iphoneX, consume_quantity);
        System.out.println("Received Iphone X quantity2: " + retrievedIphoneStock);

        System.out.println(baneasaMall.showItems());
    }

    private static void useCart(Phone iphoneX, Shoes nikeAir) {
        final Cart cart = new Cart(new HashMap<>());
        cart.addItem(iphoneX, 20);
        cart.addItem(nikeAir, 100);
        try {
            cart.print();
        } catch (IOException e) {
            System.out.println("Cannot access file during print. " + e.getMessage());
        }
    }

}


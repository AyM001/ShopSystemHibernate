package userInterface;

import model.StockModel;
import service.StockService;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class SellUI {

    private StockService stockService = new StockService();
    private StockModel stockModel = new StockModel();
    private StockUI stockUI = new StockUI();
    private ClientUI clientUI = new ClientUI();
    Scanner scanner = new Scanner(System.in);

    public void startSelling() {
        int option = -1;
        while (option != 0) {
            System.out.println("1.Add to cart.");
            System.out.println("0.Back");

            option = scanner.nextInt();
            scanner.nextLine();
            if (option == 1) {
                addToCart();
            }
        }
    }


    public void addToCart() {

        System.out.println("Name of product:");
        String name = scanner.nextLine();

        System.out.println("Quantity:");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Name of client:");
        String username = scanner.nextLine();
        stockService.addToCart(name , quantity, username);



    }
}

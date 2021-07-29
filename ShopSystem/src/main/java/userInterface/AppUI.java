package userInterface;

import service.ClientService;

import java.util.Scanner;


/* pachet de API-uri pentru fiecare Obiect


 */
public class AppUI {
    private StockUI stockUI = new StockUI();
    private CategoryUI categoryUI = new CategoryUI();
    private ClientUI clientUI = new ClientUI();
    private SellUI sellUI = new SellUI();
    Scanner scanner = new Scanner(System.in);

    public void startApp() {
        System.out.println("Type HELP for options!");

        Scanner scanner = new Scanner(System.in);

        String option = "";

        while (!option.equalsIgnoreCase("exit")) {

            option = scanner.nextLine();

            if (option.equalsIgnoreCase("help")) {
                System.out.println("------OPTIONS------");
                System.out.println("-------------------");
                System.out.println("1. PRINT PRODUCTS CATEGORY");
                System.out.println("2. PRINT PRODUCTS ALL");
                System.out.println("3. PRINT PRODUCT NAME");
                System.out.println("4. PRINT CATEGORIES");
                System.out.println("5. BUY");
                System.out.println("6. REPLENISH");
                System.out.println("7. ADD NEW CATEGORY");
                System.out.println("8. ADD NEW PRODUCT");
                System.out.println("9. REMOVE PRODUCT");
                System.out.println("10. REGISTER");

            } else if (option.equalsIgnoreCase("PRINT PRODUCTS CATEGORY")) {
                stockUI.viewStocksByCategory();
            } else if (option.equalsIgnoreCase("PRINT PRODUCTS ALL")) {
                stockUI.viewAvailableStocks();
            } else if (option.equalsIgnoreCase("PRINT PRODUCT NAME")) {
                stockUI.findProductByName();
            } else if (option.equalsIgnoreCase("PRINT CATEGORIES")) {
                categoryUI.viewCategories();
            } else if (option.equalsIgnoreCase("BUY")) {
                sellUI.addToCart();
            } else if (option.equalsIgnoreCase("REPLENISH")) {
                stockUI.replenish();
            } else if (option.equalsIgnoreCase("ADD NEW CATEGORY")) {
                categoryUI.addCategory();
            } else if (option.equalsIgnoreCase("ADD NEW PRODUCT")) {
                stockUI.addStock();
            } else if (option.equalsIgnoreCase("REMOVE PRODUCT")) {
                stockUI.removeStock();
            } else if (option.equalsIgnoreCase("REGISTER")){
                clientUI.addClient();
            }
        }
    }
}

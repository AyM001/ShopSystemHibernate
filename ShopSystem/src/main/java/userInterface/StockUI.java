package userInterface;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import model.CategoryModel;
import model.StockModel;
import service.CategoryService;
import service.StockService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class StockUI {
    private StockModel stockModel = new StockModel();
    private StockService stockService = new StockService();
    private CategoryService categoryService = new CategoryService();
    private CategoryUI categoryUI = new CategoryUI();
    private CategoryModel categoryModel = new CategoryModel();
    Scanner scanner = new Scanner(System.in);

    public void startStockApp() {
        int option = -1;
        while (option != 0) {
            System.out.println("This is the stocks management");
            System.out.println("-----------------------------");
            System.out.println("Options:");
            System.out.println("1.Add a new stock.");
            System.out.println("2.View available stocks.");
            System.out.println("3.Replenish.");
            System.out.println("4.Remove stocks.");
            System.out.println("5.PRINT PRODUCTS by CATEGORY.");
            System.out.println("0.Back.");

            option = scanner.nextInt();
            scanner.nextLine();
            if (option == 1) {
                addStock();
            } else if (option == 2) {
                viewAvailableStocks();
            } else if (option == 3) {
                replenish();
            } else if (option == 4) {
                removeStock();
            } else if (option == 5) {
                viewStocksByCategory();
            }
        }
    }


    public void addStock() {
        System.out.println("Name:");
        String name = scanner.nextLine();
        System.out.println("Quantity:");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Price:");
        int price = scanner.nextInt();
        scanner.nextLine();
        System.out.println("MaxQuantity:");
        int maxQty = scanner.nextInt();
        scanner.nextLine();
        List<CategoryModel> list = categoryService.printAllCategories(categoryModel);
        list.forEach(categoryModel1 -> System.out.println(categoryModel1.getNameCategory()));
        System.out.println("Category name:");
        String categName = scanner.nextLine();
        CategoryModel categoryModel = new CategoryModel();
        Optional<CategoryModel> optional = categoryService.findCategoryByName(categoryModel, categName);
        if (!optional.isPresent()) {
            System.out.println("-------------------");
            System.out.println("Category not found!");
            System.out.println("-------------------");
        } else {
            CategoryModel foundCategory = optional.get();
            stockModel.setCategory(foundCategory);
            stockModel.setName(name);
            stockModel.setQuantity(quantity);
            stockModel.setPrice(price);
            stockModel.setMaxQuantity(maxQty);

            stockService.addStock(stockModel);
            System.out.println("Added!");

        }
    }

    public void viewAvailableStocks() {
        List<StockModel> myStockList = stockService.printAllStocks(stockModel);
        if (myStockList.isEmpty() || myStockList.size() == 0) {
            System.out.println("-----------------------------");
            System.out.println("There are no stocks in store!");
            System.out.println("-----------------------------");
        } else
            myStockList.forEach(stockModel1 -> {
                System.out.println("-------------------------");
                System.out.println(stockModel1.getName() + " " + stockModel1.getQuantity()
                        + " " + stockModel1.getCategory().getNameCategory()
                        + " " + stockModel1.getPrice() + " "
                        + " " + stockModel1.getMaxQuantity() + " ");
                System.out.println("-------------------------");
            });
    }


    public void viewStocksByCategory() {


            System.out.println("Category name:");
            List<CategoryModel> categoryModels = categoryService.printAllCategories(categoryModel);
            categoryModels.forEach(categoryModel1 -> System.out.println(categoryModel1.getNameCategory()));
            String categoryName = scanner.nextLine();
            Optional<CategoryModel> optional = categoryService.findCategoryByName(categoryModel , categoryName);
            if (!optional.isPresent()) {
                System.out.println("Category not found!");
            } else {
                List<StockModel> stockModelList;

            stockModelList = stockService.findStocksByCategory(stockModel, categoryName);
            System.out.println("--------------------------------------");
            System.out.println("Here are the products from" + " " + categoryName + " category ");
            stockModelList.forEach(stockModel1 -> {
                System.out.println(stockModel1.getName() + " "
                        + stockModel1.getQuantity() + " " + stockModel1.getPrice());
            });
            System.out.println("--------------------------------------");

        }
    }


    public void replenish() {

        List<StockModel> list = stockService.printAllStocks(stockModel);
        list.forEach(stockModel1 -> System.out.println(stockModel1.getIdStock() + "." + stockModel1.getName() + " "
                + stockModel1.getQuantity() + " " + stockModel1.getPrice() + "$" +
                stockModel1.getMaxQuantity() + "max"));
        System.out.println("Enter stock Id for replenish:");
        int id = scanner.nextInt();
        scanner.nextLine();
        Optional<StockModel> optional = stockService.findStockById(stockModel, id);
        if (!optional.isPresent()) {
            System.out.println("-----------------------");
            System.out.println("Wrong or missing product!");
            System.out.println("-----------------------");
        } else {
            StockModel updatedStock = optional.get();


            System.out.println("Enter quantity for replenish:");
            int newQuantity = scanner.nextInt();
            scanner.nextLine();

            int totalQuantity = updatedStock.getQuantity() + newQuantity;
            if (updatedStock.getMaxQuantity() < totalQuantity) {
                System.out.println("Restricted!" + "\n" + "Maxim quantity of this product is" + updatedStock.getMaxQuantity());
            } else {
                updatedStock.setQuantity(totalQuantity);
                stockService.update(updatedStock);
                System.out.println("---------------------------------");
                System.out.println("Quantity was succesfully replenished!");
            }

        }
    }

    public void removeStock() {
        List<StockModel> stockModels = stockService.printAllStocks(stockModel);
        stockModels.forEach(stockModel1 -> System.out.println(stockModel1.getIdStock() + "." + stockModel1.getName()));
        System.out.println("Select Id of stock to be removed:");
        int id = scanner.nextInt();
        scanner.nextLine();
        Optional<StockModel> optional = stockService.findStockById(stockModel, id);
        if (!optional.isPresent()) {
            System.out.println("----------------");
            System.out.println("Stock not found!");
        } else {
            StockModel stockForDelete = optional.get();
            stockForDelete.setIdStock(id);
            if (stockForDelete.getQuantity() == 0) {
                stockService.deleteStock(stockForDelete);
                System.out.println("--------------");
                System.out.println("Stock removed!");
            } else {
                System.out.println("Cannot remove product!");
            }
        }
    }

    public void findProductByName() {

        System.out.println("Type name of the product:");
        String productName = scanner.nextLine();
        Optional<StockModel> optional = stockService.findStockByName(stockModel, productName);
        if (!optional.isPresent()) {
            System.out.println("Product not found!");
        } else {
            StockModel found = optional.get();
            System.out.println(found.getName() + " " + found.getQuantity() + " " + found.getPrice());
        }
    }
}



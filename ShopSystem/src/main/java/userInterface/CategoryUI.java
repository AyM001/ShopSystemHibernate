package userInterface;


import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import model.CategoryModel;
import service.CategoryService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CategoryUI {
    private CategoryModel categoryModel = new CategoryModel();
    private CategoryService categoryService = new CategoryService();
    Scanner scanner = new Scanner(System.in);

    public void startCategoryApp() {
        int option = -1;
        while (option != 0) {
            System.out.println("This is the category management.");
            System.out.println("-----------------------------");
            System.out.println("Options:");
            System.out.println("1.Add a new category.");
            System.out.println("2.View available categories.");
            System.out.println("3.Remove category.");
            System.out.println("0.Back.");

            option = scanner.nextInt();
            scanner.nextLine();
            if (option == 1) {
                addCategory();
            } else if (option == 2) {
                viewCategories();
            } else if (option == 3) {
                removeCategory();
            }
        }
    }

    public void addCategory() {
        System.out.println("Name:");
        String categoryName = scanner.nextLine();

        Optional<CategoryModel> optional = categoryService.findCategoryByName(categoryModel , categoryName);
        if (!optional.isPresent()) {
            categoryModel.setNameCategory(categoryName);
            categoryService.addCategory(categoryModel);
        } else
            System.out.println("Category already exist!");

    }


    public void viewCategories() {
        List<CategoryModel> categoryModelList =  categoryService.printAllCategories(categoryModel);
        if (categoryModelList.size() == 0) {
            System.out.println("-----------------------");
            System.out.println("No categories available");
            System.out.println("-----------------------");
        } else {

                System.out.println("Categories available: ");
                categoryModelList.forEach(categoryModel1 -> System.out.print(categoryModel1.getNameCategory() + ","));
            System.out.println();
        }
    }


    private void removeCategory() {
        viewCategories();
        System.out.println("Type category name to remove:");
            String categoryNAme = scanner.nextLine();
        Optional<CategoryModel> optional = categoryService.findCategoryByName(categoryModel, categoryNAme);
        if (!optional.isPresent()) {
            System.out.println("-----------------------");
            System.out.println("Category doesn't exist!");
            System.out.println("-----------------------");
        } else {
            CategoryModel removedCategory = optional.get();
            categoryService.deleteCategory(removedCategory);
            System.out.println("---------------------------------");

        }
    }
}

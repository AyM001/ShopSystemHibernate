package service;

import model.CategoryModel;
import persistence.GenericDao;
import java.util.List;
import java.util.Optional;

public class CategoryService {
    public GenericDao<CategoryModel> genericDao = new GenericDao<>();

    public void addCategory(CategoryModel categoryModel) {
        genericDao.add(categoryModel);
        System.out.println("Category Added!");
    }

    public List<CategoryModel> printAllCategories(CategoryModel categoryModel) {
        List<CategoryModel> myCategories = genericDao.getFromDb(categoryModel);
        return myCategories;
    }

   public Optional<CategoryModel> findCategoryByName(CategoryModel categoryModel, String name ){
        List<CategoryModel> myCategories = genericDao.getFromDb(categoryModel);
        return myCategories.stream().filter(categoryModel1 -> categoryModel1.getNameCategory().equalsIgnoreCase(name)).findFirst();
    }


    public void deleteCategory(CategoryModel categoryModel) {
        genericDao.deleteFromDb(categoryModel);

    }
}

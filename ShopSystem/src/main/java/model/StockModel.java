package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class StockModel {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idStock;
    private String name;
    private int quantity;
    private int price;
    private int maxQuantity;

    public StockModel() {
    }


    @ManyToOne
    @JoinColumn(name = "category" , referencedColumnName = "idCategory")
    private CategoryModel category;



    public int getIdStock() {
        return idStock;
    }

    public void setIdStock(int idStock) {
        this.idStock = idStock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }
}


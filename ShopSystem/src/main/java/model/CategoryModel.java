package model;

import javax.persistence.*;


// @Entity pentru a crea  tabelul corespunzator obiectului in Sql


@Entity
public class CategoryModel{

    // strategy IDENTITY pentru a se auto incrementa id-ul.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCategory;

    private String nameCategory;


    public CategoryModel() {
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }


}

package service;

import model.ClientModel;
import model.StockModel;
import persistence.GenericDao;
import java.util.*;
import java.util.stream.Collectors;

public class StockService {


    private GenericDao<StockModel> genericDao = new GenericDao<>();
    private ClientService clientService = new ClientService();
    private List<StockModel> cos = new ArrayList<>();

    public void addStock(StockModel stockModel) {

        genericDao.add(stockModel);

    }

    public List<StockModel> printAllStocks(StockModel stockModel) {
        List<StockModel> list = genericDao.getFromDb(stockModel);
        return list;
    }

    public Optional<StockModel> findStockById(StockModel stockModel, int id) {
        List<StockModel> stockModels = genericDao.getFromDb(stockModel);
        return stockModels.stream().filter(stockModel1 -> stockModel1.getIdStock() == id).findFirst();
    }

    public Optional<StockModel> findStockByName(StockModel stockModel, String name) {
        List<StockModel> stockModels = genericDao.getFromDb(stockModel);
        return stockModels.stream().filter(stockModel1 -> stockModel1.getName().equalsIgnoreCase(name)).findFirst();
    }


    public List<StockModel> findStocksByCategory(StockModel stockModel, String categoryName) {
        List<StockModel> stockModels = genericDao.getFromDb(stockModel);
        return stockModels.stream().filter(stockModel1 ->
                stockModel1.getCategory().getNameCategory().equalsIgnoreCase(categoryName)).collect(Collectors.toList());
    }

    public void update(StockModel stockModel) {
        genericDao.updateDB(stockModel);
    }

    public void deleteStock(StockModel stockModel) {
        genericDao.deleteFromDb(stockModel);
    }

    public void addToCart(String productName, int quantity, String username) {
        StockModel stockModel = new StockModel();
        Optional<StockModel> optional = findStockByName(stockModel, productName);
        if (!optional.isPresent()) {
            System.out.println("Product not found!");
        } else {
            StockModel produsDinStock = optional.get();
            StockModel produsInCos = new StockModel();
            produsInCos.setName(productName);
            produsInCos.setQuantity(quantity);
            ClientModel clientModel = new ClientModel();
            Optional<ClientModel> userOptional = clientService.findClientByName(clientModel ,username);
            if (!userOptional.isPresent()) {
                System.out.println("User not found!");
            } else {
                ClientModel userModel = userOptional.get();
                int total = produsDinStock.getPrice() * quantity;
                if (produsDinStock.getQuantity() < quantity || userModel.getBalance() < total) {
                    System.out.println("In stock are only "
                            + produsDinStock.getQuantity() + " " + produsDinStock.getName() + " left and balance is: " + userModel.getBalance());
                } else {
                    cos.add(produsInCos);
                    int balanceAfterPurchase = userModel.getBalance() - total;
                    System.out.println(username + " has bought " + quantity + " of " + productName);
                    System.out.println("Balance: " + " " +  balanceAfterPurchase);
                    StockModel produsRamas = new StockModel();
                    int cantitateVerificata = produsDinStock.getQuantity() - quantity;
                    if (cantitateVerificata >= 0)
                        produsRamas.setQuantity(cantitateVerificata);
                    produsRamas.setName(produsDinStock.getName());
                    produsRamas.setPrice(produsDinStock.getPrice());
                    produsRamas.setMaxQuantity(produsDinStock.getMaxQuantity());
                    produsRamas.setCategory(produsDinStock.getCategory());
                    genericDao.deleteFromDb(produsDinStock);
                    genericDao.add(produsRamas);
                }
            }
        }
    }
}

package service;


import model.ClientModel;
import persistence.GenericDao;
import java.util.List;
import java.util.Optional;


public class ClientService {
    private GenericDao<ClientModel> genericDao = new GenericDao<>();


    public void addClient(ClientModel clientModel){
        genericDao.add(clientModel);
    }

    public List<ClientModel> viewClients(ClientModel clientModel){
        return genericDao.getFromDb(clientModel);
    }

    public Optional<ClientModel> findClientByName(ClientModel clientModel, String name ){
        List<ClientModel> myClients = genericDao.getFromDb(clientModel);
        return myClients.stream().filter(categoryModel1 -> categoryModel1.getUsername().equalsIgnoreCase(name)).findFirst();
    }


}

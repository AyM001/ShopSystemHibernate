package userInterface;

import model.ClientModel;
import service.ClientService;

import java.util.List;
import java.util.Scanner;

public class ClientUI {
    private ClientService clientService = new ClientService();
    private Scanner scanner = new Scanner(System.in);
    private ClientModel clientModel = new ClientModel();

    public void startClientApp(){
        int option = -1  ;
        while (option != 0){
            System.out.println("1.Add client.");
            System.out.println("2.View clients.");
            System.out.println("0.Back.");
            option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1){
                addClient();
            } else if (option == 2){
                viewClients();
            }
        }
    }

    public void viewClients() {
       List<ClientModel> clients =  clientService.viewClients(clientModel);
       if (clients.isEmpty()){
           System.out.println("No clients registered!");
       }
       clients.forEach(clientModel1 -> {
           System.out.println("------------------------");
           System.out.println(clientModel1.getIdClient() + "." + clientModel1.getUsername() +
                   " Balance " + clientModel1.getBalance());
           System.out.println("------------------------");
       });
    }

    public void addClient() {
        System.out.println("Enter name:");
        String userName = scanner.nextLine();
        System.out.println("Enter balance:");
        int balance = scanner.nextInt();
        scanner.nextLine();
        ClientModel clientModel = new ClientModel();
        clientModel.setUsername(userName);
        clientModel.setBalance(balance);
        clientService.addClient(clientModel);
        System.out.println("-----------------------");
        System.out.println("Registered succesfully!");
        System.out.println("-----------------------");
    }
}

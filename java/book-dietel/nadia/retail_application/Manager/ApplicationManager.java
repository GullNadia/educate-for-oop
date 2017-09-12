package Manager;

import Entities.Item;
import Entities.User;

import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

public class ApplicationManager {

    Scanner in = new Scanner(System.in);
    IOManager ioManager = new IOManager();
    UserManager userManager = new UserManager();
    ItemManager itemManager = new ItemManager();
    OrderManager orderManager = new OrderManager();
    User user = new User();
    private String email = "";

    public void manageApplication() throws Exception {
        ioManager.printAppMenu();
        int choice = in.nextInt();
        switch (choice) {
            case 1:
                List<String> userDetail = ioManager.getUserDetailFromCLI();
                userManager.addUser(userDetail);
                break;
            case 2:
                ioManager.printItemHeadingMsg();
                List<Item> list = itemManager.getAllItems();
                ioManager.printItems(list);
                break;
            case 3:
                 email = ioManager.getUserEmail();
                 user = userManager.getUserByEmail(email);
                if (user != null) {
                    ioManager.printWelComeMsg();
                    ioManager.printItemHeadingMsg();
                    List<Item> listOfAllItems = itemManager.getAllItems();
                    ioManager.printItems(listOfAllItems);
                    orderManager.createOrder(user.getId());
                    List<List> listOfOrderedItem = ioManager.getOrderByUser();
                    int orderId = orderManager.createPurchase(listOfOrderedItem);
                    ResultSet rs = orderManager.getOrderDetail(orderId);
                    ioManager.printOrderedItem(rs);
                } else {
                    ioManager.printRegisterFirstMsg();
                    ioManager.getUserDetailFromCLI();
                }
                break;
            case 4:
                email = ioManager.getUserEmail();
                user = userManager.getUserByEmail(email);
                if (user != null) {
                    int orderId = orderManager.getOrderIdByUserId(user.getId());
                    ResultSet rs = orderManager.getOrderDetail(orderId);
                    ioManager.printOrderedItem(rs);
                } else {
                    ioManager.printRegisterFirstMsg();
                }
        }
    }
    public static void main(String[] args) throws Exception {

        ApplicationManager appManager = new ApplicationManager();
        appManager.manageApplication();
    }

}

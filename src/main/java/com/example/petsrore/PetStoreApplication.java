package com.example.petsrore;

import com.example.petsrore.model.Activity;
import com.example.petsrore.model.Order;
import com.example.petsrore.model.Pet;
import com.example.petsrore.service.IActService;
import com.example.petsrore.service.IOrderService;
import com.example.petsrore.service.IPetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class PetStoreApplication implements CommandLineRunner {
    private Scanner scanner = new Scanner(System.in);
    @Autowired
    private IPetService petService;
    @Autowired
    private IActService actService;
    @Autowired
    private IOrderService orderService;
    Logger logger = LoggerFactory.getLogger(PetStoreApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(PetStoreApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        while (true) {
            printHelp();
            String str = scanner.next();
            if ("1".equals(str)) {
                for (Pet pet : petService.getPetList()) {
                    logger.info(pet.getId() + "\t" + pet.getName() + "\t" + pet.getType()
                            + "\t" + pet.getStatus() + "\t" + pet.getPrice());
                    System.out.println(pet.getId() + "\t" + pet.getName() + "\t" + pet.getType()
                            + "\t" + pet.getStatus() + "\t" + pet.getPrice());
                }
            } else if ("2".equals(str)) {
                for (Activity activity : actService.getActivityList()) {
                    if (activity.getActStatus() == 0) {
                        System.out.println(activity.getActId() + "\t" + activity.getActName() + "\t"
                                + activity.getActPetType() + "\t" + activity.getActRebate());
                    }
                }
            }else if("3".equals(str)){
                System.out.println("?????????????????????");
                String name = scanner.next();
                System.out.println("??????????????????id");
                int petId = scanner.nextInt();
                for (Pet pet : petService.getPetList()) {
                    if (pet.getId() == petId && pet.getStatus() == 0) {
                        for (Activity activity : actService.getActivityList()) {
                            if (activity.getActPetType() == pet.getType() && activity.getActStatus() == 0) {
                                System.out.println("????????????" + orderService.amount(petId));
                                orderService.newOrder(name, petId);
                                petService.changeStatus(petId, 1);
                            } else if (activity.getActPetType() != pet.getType() || activity.getActStatus() != 0) {
                                System.out.println("????????????" + orderService.amount(petId));
                                orderService.newOrder(name, petId);
                                petService.changeStatus(petId, 1);
                            }
                        }
                    }else if(pet.getId() == petId && pet.getStatus() != 0){
                        System.out.println("??????????????????????????????");
                    }
                }
            }else if("s".equals(str)){
                admin();
            }else if("4".equals(str)) {
                System.out.println("??????????????????id");
                int petId = scanner.nextInt();
                petService.changeStatus(petId, 0);
            }
        }
    }


    public void admin(){
        while (true){
            adminHelp();
            String str = scanner.next();
            if("1".equals(str)){
                System.out.println("?????????????????????");
                String name = scanner.next();
                System.out.println("?????????????????????");
                int type = scanner.nextInt();
                System.out.println("?????????????????????");
                double price = scanner.nextDouble();
                petService.newPet(name,type,price);
            }else if("2".equals(str)){
                System.out.println("?????????????????????");
                String name = scanner.next();
                System.out.println("?????????????????????");
                double rebate = scanner.nextDouble();
                System.out.println("??????????????????????????????");
                int type = scanner.nextInt();
                actService.newAct(name,rebate,type);
            }else if("3".equals(str)){
                for (Order order : orderService.getOrderList()) {
                    System.out.println(order.getOrderId() + "\t" + order.getOrderName() + "\t" + order.getPetId()
                            + "\t" + order.getAmount());
                }
            }else if("4".equals(str)){
                double[] ave =petService.ave();
                System.out.println("????????????????????????" + ave[0]);
                System.out.println("????????????????????????" + ave[1]);
            }else if("5".equals(str)){
                Pet[] pets =petService.max();
                System.out.println("?????????????????????" + pets[0].getPrice());
                System.out.println("?????????????????????" + pets[1].getPrice());
            }else if("6".equals(str)){
                Pet[] pets =petService.min();
                System.out.println("?????????????????????" + pets[0].getPrice());
                System.out.println("?????????????????????" + pets[1].getPrice());
            }else if("q".equals(str)){
                break;
            }
        }
    }

    public void adminHelp(){
        System.out.println("????????????????????????");
        System.out.println("??????1 ????????????");
        System.out.println("??????2 ????????????");
        System.out.println("??????3 ??????????????????");
        System.out.println("??????4 ???????????????????????????");
        System.out.println("??????5 ????????????????????????");
        System.out.println("??????6 ????????????????????????");
        System.out.println("??????q ??????");
    }

    public void printHelp(){
        System.out.println("????????????????????????");
        System.out.println("??????1 ????????????????????????");
        System.out.println("??????2 ????????????????????????");
        System.out.println("??????3 ????????????");
    }


}

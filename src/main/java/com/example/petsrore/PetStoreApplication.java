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
                System.out.println("请输入你的姓名");
                String name = scanner.next();
                System.out.println("请输入宠物的id");
                int petId = scanner.nextInt();
                for (Pet pet : petService.getPetList()) {
                    if (pet.getId() == petId && pet.getStatus() == 0) {
                        for (Activity activity : actService.getActivityList()) {
                            if (activity.getActPetType() == pet.getType() && activity.getActStatus() == 0) {
                                System.out.println("请支付：" + orderService.amount(petId));
                                orderService.newOrder(name, petId);
                                petService.changeStatus(petId, 1);
                            } else if (activity.getActPetType() != pet.getType() || activity.getActStatus() != 0) {
                                System.out.println("请支付：" + orderService.amount(petId));
                                orderService.newOrder(name, petId);
                                petService.changeStatus(petId, 1);
                            }
                        }
                    }else if(pet.getId() == petId && pet.getStatus() != 0){
                        System.out.println("对不起，该宠物已下架");
                    }
                }
            }else if("s".equals(str)){
                admin();
            }else if("4".equals(str)) {
                System.out.println("请输入宠物的id");
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
                System.out.println("请输入宠物姓名");
                String name = scanner.next();
                System.out.println("请输入宠物类型");
                int type = scanner.nextInt();
                System.out.println("请输入宠物价格");
                double price = scanner.nextDouble();
                petService.newPet(name,type,price);
            }else if("2".equals(str)){
                System.out.println("请输入活动名字");
                String name = scanner.next();
                System.out.println("请输入活动折扣");
                double rebate = scanner.nextDouble();
                System.out.println("请输入活动宠物的种类");
                int type = scanner.nextInt();
                actService.newAct(name,rebate,type);
            }else if("3".equals(str)){
                for (Order order : orderService.getOrderList()) {
                    System.out.println(order.getOrderId() + "\t" + order.getOrderName() + "\t" + order.getPetId()
                            + "\t" + order.getAmount());
                }
            }else if("4".equals(str)){
                double[] ave =petService.ave();
                System.out.println("猫的平均价格为：" + ave[0]);
                System.out.println("狗的平均价格为：" + ave[1]);
            }else if("5".equals(str)){
                Pet[] pets =petService.max();
                System.out.println("猫的最高价是：" + pets[0].getPrice());
                System.out.println("狗的最高价是：" + pets[1].getPrice());
            }else if("6".equals(str)){
                Pet[] pets =petService.min();
                System.out.println("猫的最低价是：" + pets[0].getPrice());
                System.out.println("狗的最低价是：" + pets[1].getPrice());
            }else if("q".equals(str)){
                break;
            }
        }
    }

    public void adminHelp(){
        System.out.println("欢迎来到宠物系统");
        System.out.println("输入1 上架宠物");
        System.out.println("输入2 上架活动");
        System.out.println("输入3 查看顾客信息");
        System.out.println("输入4 查看宠物的平均价格");
        System.out.println("输入5 查看宠物的最高价");
        System.out.println("输入6 查看宠物的最低价");
        System.out.println("输入q 退出");
    }

    public void printHelp(){
        System.out.println("欢迎来到宠物商店");
        System.out.println("输入1 查看当前宠物列表");
        System.out.println("输入2 查看当前活动信息");
        System.out.println("输入3 购买宠物");
    }


}

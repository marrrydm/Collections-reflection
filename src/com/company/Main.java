package com.company;
import Auto.*;
import Exceptions.*;
import Interface.*;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;
import java.lang.reflect.*;

public class Main {
    public static void main(String[] args) throws NoSuchModelNameException, DuplicateModelNameException {
        try {
            System.out.println("------------------------------");
            System.out.println("Задание 1");
            Class vehicle = Class.forName(args[0]);
            Constructor constructor = vehicle.getConstructor(String.class, int.class);
            Method setPriceModelByName = vehicle.getMethod(args[1], String.class, double.class);
            Object car = constructor.newInstance(args[2],Integer.parseInt(args[3]));
            String name = args[4];
            double price = Double.parseDouble(args[5]);

            System.out.println("До измений:");
            System.out.println(car);
            System.out.println("После изменений:");
            setPriceModelByName.invoke(car,name,price);
            System.out.println(car);
            System.out.println("------------------------------");

            System.out.println("Задание 2");
            Transport vehicleCar = new Car("Mercedes");
            Transport createCar = Vehicle.createTransport("Nissan",3, vehicleCar);
            System.out.println(createCar.getClass().getSimpleName());
            System.out.println(createCar);
            System.out.println("------------------------------");

            System.out.println("Задание 3");
            Transport vehicleScooter = new Scooter("Yamaha",3);
            vehicleScooter.addNewModel("Honda",111);
            vehicleScooter.setNewModelName("Скутер2","Скутер56");
            vehicleScooter.deleteModelByName("Скутер1");
            System.out.println(vehicleScooter);
            System.out.println("------------------------------");

            System.out.println("Задание 4");
            Transport vehicleATV = new ATV("Suzuki",5);
            vehicleATV.addNewModel("Stels",141);
            vehicleATV.setNewModelName("Квадроцикл3","Квадроцикл56");
            vehicleATV.deleteModelByName("Квадроцикл1");
            System.out.println(vehicleATV);
            System.out.println("------------------------------");

            System.out.println("Задание 5");
            Transport vehicleMoped = new Moped("Honda",5);
            vehicleMoped.addNewModel("Stels",99);
            vehicleMoped.setNewModelName("Мопед3","Мопед56");
            vehicleMoped.deleteModelByName("Мопед1");
            System.out.println(vehicleMoped);
            System.out.println("------------------------------");

            System.out.println("Задание 6");
            Transport veh1 = new Car("Toyota",1);
            Transport veh2 = new Motorbike("Lada",4);
            Transport veh3 = new Moped("Honda",2);
            Transport veh4 = new Scooter("Yamaha",1);
            Transport veh5 = new ATV("Stels",2);
            System.out.println(veh1);
            System.out.println(veh2);
            System.out.println(veh3);
            System.out.println(veh4);
            System.out.println(veh5);
            System.out.println(Vehicle.getMeanPrice(veh1,veh2,veh3,veh4,veh5));
            System.out.println("------------------------------");

            System.out.println("Задание 7");
            Transport carr = new Car("KIA", 1);
            Transport carr1 = Vehicle.readTransport();
            Vehicle.writeTransport(carr);
            System.out.println(carr1);


        } catch (ModelPriceOutOfBoundsException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException | IOException e) {
            e.printStackTrace();
        }
    }
}


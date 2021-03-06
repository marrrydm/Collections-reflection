package Auto;

import java.io.Serializable;
import java.util.Arrays;
import Exceptions.*;
import Interface.Transport;

public class Car implements Transport,Serializable,Cloneable {
    private static final long serialVersionUID = 1;
    private String brand;
    private Model[] models;
    private String vehicle = "Car";

    private class Model implements Serializable, Cloneable {

        private String name;
        private double price;

        public Model(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getNameModel() {
            return name;
        }

        public void setNameModel(String name) {
            this.name = name;
        }

        public double getPriceModel() {
            return price;
        }

        public void setPriceModel(double price) {
            if (price < 0)
                throw new ModelPriceOutOfBoundsException("Цена модели должна быть положительным числом");
            this.price = price;
        }
        public double getPrice() {
            return price;
        }
        @Override
        public String toString()
        {
            return  getNameModel() + " , "  + getPriceModel();
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (!(obj instanceof Model)) return false;
            if (obj instanceof Model) {
                Model model = (Model) obj;
                return model.name.equals(name) && model.price == price;
            }
            return false;
        }
        @Override
        public int hashCode() {
            int hash = (int) price;
            hash = 10 * hash + (name != null ? name.hashCode() : 0);
            return hash;
        }
        @Override
        public Object clone(){
            Model model =null;
            try{
                model = (Model)super.clone();
            } catch(CloneNotSupportedException exception){
                return model;
            }
            return model;
        }
    }

    public Car(String brand, int length) throws DuplicateModelNameException {
        this.brand = brand;
        models = new Model[length];

        for (int i = 0; i < length; i++) {
            models[i] = new Model("Машина" + i, 100 * (i + 1));
        }
    }

    public String getVehicle() {
        return vehicle;
    }

    public String getMotoBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setNewModelName(String oldName, String newName)
            throws NoSuchModelNameException, DuplicateModelNameException {
        int index = 0;
        while (index < getLenModels()) {
            if (models[index].getNameModel().equals(newName))
                throw new DuplicateModelNameException("Такая модель уже есть");
            index++;
        }
        index = 0;
        while (index < getLenModels() && !models[index].getNameModel().equals(oldName)) {
            index++;
        }
        if (index == getLenModels()) {
            throw new NoSuchModelNameException("Модель не найдена");
        }
        models[index].setNameModel(newName);
    }

    public String[] getArrayNamesOfModels() {
        String[] names = new String[models.length];
        for (int i = 0; i < names.length; i++) {
            names[i] = models[i].getNameModel();
        }
        return names;
    }

    public double getPriceModelByName(String nameModel) throws NoSuchModelNameException {
        int index = 0;
        while (index < getLenModels() && !models[index].getNameModel().equals(nameModel)) {
            index++;
        }
        if (index == getLenModels()) {
            throw new NoSuchModelNameException("Модель не найдена");
        }
        return models[index].getPriceModel();
    }

    public void setPriceModelByName(String nameModel, double priceModel) throws NoSuchModelNameException {
        if (priceModel < 0) {
            throw new ModelPriceOutOfBoundsException("Цена модели не может быть отрицательной");
        }
        int index = 0;
        while (index < getLenModels() && !models[index].getNameModel().equals(nameModel)) {
            index++;
        }
        if (index == getLenModels()) throw new NoSuchModelNameException("Модели с таким именем нет");
        models[index].setPriceModel(priceModel);
    }

    public double[] getArrayPricesOfModels() {
        double[] price = new double[models.length];
        for (int i = 0; i < price.length; i++) {
            price[i] = models[i].getPriceModel();
        }
        return price;
    }

    public void addNewModel(String modelName, double modelPrice) throws DuplicateModelNameException {
        if (modelPrice < 0) {
            throw new ModelPriceOutOfBoundsException("Цена модели не может быть отрицательной");
        } else {
            int i = 0;
            while (i < models.length && models[i] != null) {
                if (models[i].getNameModel().equals(modelName)) {
                    throw new DuplicateModelNameException("Такая модель уже есть");
                }
                i++;
            }
            if (i == models.length) {
                Model[] addModel = Arrays.copyOf(models, models.length + 1);
                addModel[models.length] = new Model(modelName, modelPrice);
                models = addModel;
            } else {
                models[i] = new Model(modelName, modelPrice);
            }
        }
    }

    public void deleteModelByName(String modelName) throws NoSuchModelNameException {

        int index = findModel(modelName);
        if (findModel(modelName) != -1) {
            System.arraycopy(models, index + 1, models, index, getLenModels() - index - 1);
            models = Arrays.copyOf(models, getLenModels() - 1);
        } else {
            throw new NoSuchModelNameException("Модель не найдена");
        }
    }

    public int findModel(String modelName) {
        int length = getLenModels();
        int index = 0;
        while (index < length) {
            if (models[index].getNameModel().equals(modelName)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public int getLenModels() {
        return models.length;
    }

    public Car(String brand) {
        this.brand = brand;
        models = new Model[0];
    }
    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        String brand = getMotoBrand();
        stringBuffer.append("Марка: ").append(brand).append("\n");
        int size = getLenModels();
        stringBuffer.append("Количество: ").append(size).append("\n");
        stringBuffer.append("Модели: " + "\n");

        for (Model model : models) {
            stringBuffer.append(model).append("\n");
        }
        return stringBuffer.toString();
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (!(obj instanceof Transport)) return false;
        if (obj instanceof Transport) {
            Transport transport = (Transport) obj;
             return this.getMotoBrand().equals(transport.getMotoBrand()) &&
                    Arrays.equals(this.getArrayNamesOfModels(), transport.getArrayNamesOfModels()) &&
                    Arrays.equals(this.getArrayPricesOfModels(), transport.getArrayPricesOfModels());
        } else return false;
    }
    @Override
    public int hashCode() {
        int hash = brand != null ? brand.hashCode() : 0;
        hash = 10 * hash + (models != null ? Arrays.hashCode(models) : 0);
        return hash;
    }
    @Override
    public Object clone() {
        Car car = null;
        try {
            car = (Car) super.clone();
            car.models = models.clone();
            for (int i = 0; i < getLenModels(); i++) {
                car.models[i] = (Model) models[i].clone();
            }
            return car;
        } catch (CloneNotSupportedException exception) {
            return car;
        }
    }
}

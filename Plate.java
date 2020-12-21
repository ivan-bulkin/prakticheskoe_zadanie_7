package prakticheskoe_zadanie_7;

import static prakticheskoe_zadanie_7.Main.RAZMER_TARELKI;

public class Plate {

    public int food;

    //Метод добавления еды в тарелку, случайным образом добавляет еду в тарелку
    //6. Добавить в тарелку метод, с помощью которого можно было бы добавлять еду в тарелку
    public void addFood() {
        System.out.print("\nВ тарелке было еды : " + food);
        this.food += (int) (Math.random() * RAZMER_TARELKI);
        if (food > RAZMER_TARELKI) {
            food = RAZMER_TARELKI;//Если хозяин пытается положить в тарелку еды больше, чем размер тарелки, то не даём ему это сделать. Ограничение - это размер тарелки RAZMER_TARELKI
        }
        System.out.println(", после ежедневного добавления еды стало : " + food);
    }

    public void printInfo() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "\nСейчас в тарелке еды: " + food;
    }

    public void decreaseFood(int food) {
        this.food -= food;
    }

}

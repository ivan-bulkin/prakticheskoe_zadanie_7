package prakticheskoe_zadanie_7;

import java.time.Year;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    static Scanner in = new Scanner(System.in);

    public static final int RAZMER_TARELKI = 50;//переменная, которая отвечает за то, сколько максимально корма может быть в тарелке
    public static final int NASKOLKO_GOLODEN_KOT = 10;//переменная, которая отвечает за то, на сколько максимально может быть голоден кот. Кот может быть голоден по-разному в разный день. Набегался сильно, не доел в прошлый раз и т.д.

    //Создать массив из 5-ти котов
    public static Cat[] catArray = new Cat[5];//Создаём массив котов
    public static String[] catName = {"Мартин", "Матроскин", "Василий", "Фёдор", "Проглот"};//Создаём массив имён для котов new String[5]

    public static void main(String[] args) {

        privetstvieProgrammi();

        setKotovGolodnimi();//делаем котов голодными

        printNaSkolkoGolodniKoti();//Выводим на экран на сколько голодны коты

        Plate plate = new Plate();//Создаём тарелку с едой

        plate.addFood();//добавляем в тарелку еду

        kormimKotov(plate);//кормим котов из тарелки

        printNaSkolkoGolodniKotiPolseKormlenya();//Выводим на экран на сколько голодны коты после кормления

        plate.printInfo();//Выводим на экран сколько еды в тарелке
    }

    private static void privetstvieProgrammi() {
        System.out.println("\n---=== Практическое задание к уроку № 7 Коты и тарелка с едой ===---");
        System.out.println("                         Сделал Иван Булкин");
    }

    private static void kormimKotov(Plate plate) {
        int skolko_siel_kot = 0;
        for (int i = 0; i < catName.length; i++) {
            if (plate.food > 0) {//Выполняем только в том случае, если в тарелке есть еда
                skolko_siel_kot = plate.food;
                plate.decreaseFood(catArray[i].goloden);
                if (plate.food < 0) {//Если кот съел еды больше, чем осталось в тарелке, то вычитаем из голода только то, что он реально смог съесть
                    catArray[i].goloden -= skolko_siel_kot;
                    plate.food = 0;//Делаем в тарелке ноль еды, т.к. отрицательное количество еды быть не может
                    break;//выходим, если в тарелке больше нет еды
                } else {
                    catArray[i].goloden = 0;
                }
            }
        }
    }

    //Метод, который выводит на экран на сколько голодны коты после кормления
    private static void printNaSkolkoGolodniKotiPolseKormlenya() {
        System.out.println("После кормления: ");
        for (int i = 0; i < catName.length; i++) {
            if (catArray[i].goloden == 0) {
                System.out.print(catArray[i].name + ": сыт; ");
            } else {
                System.out.print(catArray[i].name + " остался голоден и хочет ещё съесть: " + catArray[i].goloden + "; ");
            }
        }
    }

    //Метод, который выводит на экран на сколько голодны коты
    private static void printNaSkolkoGolodniKoti() {
        System.out.println("Сегодня коты хотят съесть еды: ");
        for (int i = 0; i < catName.length; i++) {
            System.out.print(catArray[i].name + ": " + catArray[i].goloden + "; ");
        }
    }

    //Метод, который делает котов голодными
    private static void setKotovGolodnimi() {
        //Заполняем массив котов. Подставляем имя кота из массива имён и на случайное число в пределах NASKOLKO_GOLODEN_KOT делаем его голодным.
        //Затем случайным образом меняем котов в массиве местами, чтобы потом коты ели именно в такой очерёдности. Кто раньше встал, того и тапки
        //Сперва хотел реализовать расстановку котов на кормёжку случайным образом самостоятельно, но потом интернет выдал решение: Collections.shuffle
        for (int i = 0; i < catName.length; i++) {
            catArray[i] = new Cat(catName[i], (int) (Math.random() * NASKOLKO_GOLODEN_KOT));
        }
        Collections.shuffle(Arrays.asList(catArray));//случайным образом меняем котов в массиве местами
    }

    private static void viborDeistvya() {
        int vibor_deistviya;
        do {
            vibor_deistviya = vvodTselogoChisla(1, 2, "Покормить ещё котиков, 1 - Да, 2 - Нет.");
            if (vibor_deistviya != -100) {
            } else {
                System.out.print("Вы ошиблись. ");
            }
            if (vibor_deistviya == 1) {

            }
            if (vibor_deistviya == 2) {
                System.out.println("\nДо новых встреч, ждём Вас снова.");
                in.close();//Необходимо закрыть объект in
                System.exit(0);
            }
        }
        while (vibor_deistviya == -100);
    }

    //Универсальная процедура ввода целого числа, буду её использовать в других программах
    //На вход процедуры передаём от какого до какого числа пользовательо должен ввести число
    //min_znachenie - меньше этого числа вводить нельзя
    //max_znachenie - больше этого числа вводить нельзя
    //возвращает -100, если число введено не верно и возвращает целое число, если число введено верно и в нужном интервале
    //message - сообщение пользователю о том, что именно надо вводить
    private static int vvodTselogoChisla(int min_znachenie, int max_znachenie, String message) {
        System.out.print(message + ", введите число от " + min_znachenie + " до " + max_znachenie + ": ");
        int vvedennoe_chislo = -100;
        if (in.hasNextInt()) {
            vvedennoe_chislo = in.nextInt();
            if (vvedennoe_chislo < min_znachenie || vvedennoe_chislo > max_znachenie) {
                vvedennoe_chislo = -100;
            }
        } else {
            in.next();
        }
        return vvedennoe_chislo;
    }

/*    //Универсальная процедура выхода из программы, буду её использовать в других программах
    //Выходит из программы, если пользователь выбирает "y", "yes", "д", "да", "+", "торжественно подтверждаю"
    //Возвращает false, если пользователь выбирает "n", "н", "-", "играть, так играть, продолжаем"
    //продолжает спрашивать о выходе, если пользователь ввёл любое другое значение
    private static boolean vixodIzProgrammi() {
        System.out.println("\nВыходим из программы, Вы уверены? y/n (д/н)");
        String n = in.next();
        switch (n) {
            case "y", "yes", "д", "да", "+", "торжественно подтверждаю" -> {
                System.out.println("\nДо новых встреч, ждём Вас снова.");
                in.close();//Необходимо закрыть объект in
                System.exit(0);
            }
            case "n", "н", "-", "играть, так играть, продолжаем" -> {
                System.out.println();
                return false;
            }
            default -> {
                System.out.printf("Вы ввели: " + n + ", такого значения нет в списке%n" + "Выберите y/n или д/н и попробуйте ещё разик.");
                vixodIzProgrammi();
            }
        }
        return false;
    }*/
}
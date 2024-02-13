package nerdschool.bar;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Pub {
    public static final String ONE_BEER = "hansa";
    public static final String ONE_CIDER = "grans";
    public static final String A_PROPER_CIDER = "strongbow";
    public static final String GT = "gt";
    public static final String BACARDI_SPECIAL = "bacardi_special";
    private int PRICE;
    private int STUDENT_DISCOUNT = 10;
    private Map<String, Integer> drinksMap = new HashMap<>();

    public Pub() {
        drinksMap.put(ONE_BEER, 74);
        drinksMap.put(ONE_CIDER, 103);
        drinksMap.put(A_PROPER_CIDER, 110);
        drinksMap.put(GT, priceGT());
        drinksMap.put(BACARDI_SPECIAL, priceBacardi());
    }

    public int computeCost(String drink, boolean student, int amountBought) {
        if (!isValidDrink(drink)) {
            throw new RuntimeException("No such drink exists");
        }
        if (isInvalidOrder(drink, amountBought)) {
            throw new RuntimeException("Too many drinks, max 2.");
        }

        PRICE = getPrice(drink);
        if (isStudent(drink, student)) {
            PRICE -= PRICE / STUDENT_DISCOUNT;
        }
        return PRICE * amountBought;
    }

    private boolean isInvalidOrder(String drink, int amountBought) {
        int maxDrinks = 2;
        return (amountBought > maxDrinks && (Objects.equals(drink, GT) || Objects.equals(drink, BACARDI_SPECIAL)));
    }

    private boolean isValidDrink(String drink) {
        return drinksMap.containsKey(drink);
    }

    private int getPrice(String drink) {
        return (drinksMap.get(drink));
    }

    private boolean isStudent(String drink, boolean student) {
        return (student && (drink.equals(ONE_BEER) || drink.equals(ONE_CIDER) || drink.equals(A_PROPER_CIDER)));
    }

    private int priceRum() {
        return 65;
    }

    private int priceGrenadine() {
        return 10;
    }

    private int priceLimeJuice() {
        return 10;
    }

    private int priceGreenStuff() {
        return 10;
    }

    private int priceTonicWater() {
        return 20;
    }

    private int priceGin() {
        return 85;
    }

    private int priceGT() {
        return priceGin() + priceTonicWater() + priceGreenStuff();
    }

    private int priceBacardi() {
        return  priceGin()/2 + priceRum() + priceGrenadine() + priceLimeJuice();
    }

}

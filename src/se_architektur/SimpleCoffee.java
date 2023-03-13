package se_architektur;

public class SimpleCoffee implements Coffee{
    @Override
    public int getCost() {
        return 1;
    }

    @Override
    public String getIngredients() {
        return "Coffee";
    }
}

package se_architektur;

public abstract class CoffeeDecorator implements Coffee{
    private final Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee c){
        this.decoratedCoffee = c;
    }

    @Override
    public int getCost(){
        return decoratedCoffee.getCost();
    }

    @Override
    public String getIngredients(){
        return decoratedCoffee.getIngredients();
    }
}

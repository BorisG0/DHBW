package se_architektur;

public class WithSprinkles extends CoffeeDecorator{
    public WithSprinkles(Coffee c){
        super(c);
    }

    @Override
    public int getCost(){
        return super.getCost() + 2;
    }

    @Override
    public String getIngredients(){
        return super.getIngredients() + ", Sprinkles";
    }
}

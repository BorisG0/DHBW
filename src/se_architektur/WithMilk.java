package se_architektur;

public class WithMilk extends CoffeeDecorator{
    public WithMilk(Coffee c){
        super(c);
    }

    @Override
    public int getCost(){
        return super.getCost() + 1;
    }

    @Override
    public String getIngredients(){
        return super.getIngredients() + ", Milk";
    }
}

package christmasPastryShop.core.interfaces;

public interface Controller {
   String addDelicacy(String type, String name, double price);

   String addCocktail(String type, String name, int portion, String brand);

   String addBooth(String type, int tableNumber, int capacity);

   String reserveBooth(int numberOfPeople);

   String orderDelicacy(int tableNumber, String foodName);

   String orderCocktail(int tableNumber, String drinkName, String drinkBrand);

   String leaveBooth(int tableNumber);

   String getIncome();
}

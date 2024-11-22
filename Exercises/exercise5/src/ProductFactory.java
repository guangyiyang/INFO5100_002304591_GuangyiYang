public class ProductFactory {

    public static Product createProduct(String type){
        if (type.equals("A")) {
            return new ProductA();
        } else if (type.equals("B")){
            return new ProductB();
        }
        return null;
    }
}

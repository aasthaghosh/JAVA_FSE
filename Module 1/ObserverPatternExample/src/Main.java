public class Main {
    public static void main(String[] args) {
        StockMarket stockMarket = new StockMarket("Google", 175.50);
        
        Observer mobileApp = new MobileApp("AndroidClient");
        Observer webApp = new WebApp("ChromeClient");

        stockMarket.registerObserver(mobileApp);
        stockMarket.registerObserver(webApp);

        stockMarket.setPrice(180.20);
        
        System.out.println();
        stockMarket.deregisterObserver(webApp);
        stockMarket.setPrice(185.00);
    }
}

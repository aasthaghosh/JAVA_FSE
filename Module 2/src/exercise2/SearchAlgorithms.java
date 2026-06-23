package exercise2;

public class SearchAlgorithms {
    public static Product linearSearch(Product[] products, String targetName) {
        for (Product product : products) {
            if (product.getProductName().equalsIgnoreCase(targetName)) {
                return product;
            }
        }
        return null;
    }

    public static Product binarySearch(Product[] sortedProducts, String targetName) {
        int low = 0;
        int high = sortedProducts.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparison = sortedProducts[mid].getProductName().compareToIgnoreCase(targetName);
            if (comparison == 0) {
                return sortedProducts[mid];
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }
}

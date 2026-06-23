package exercise6;

public class LibrarySearch {
    public static Book linearSearchByTitle(Book[] books, String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public static Book binarySearchByTitle(Book[] sortedBooks, String title) {
        int low = 0;
        int high = sortedBooks.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comp = sortedBooks[mid].getTitle().compareToIgnoreCase(title);
            if (comp == 0) {
                return sortedBooks[mid];
            } else if (comp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }
}

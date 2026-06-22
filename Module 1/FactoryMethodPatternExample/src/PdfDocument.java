public class PdfDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening PDF document (.pdf) in read-only mode...");
    }

    @Override
    public void close() {
        System.out.println("Closing PDF document...");
    }
}

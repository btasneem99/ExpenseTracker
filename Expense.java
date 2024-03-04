import java.util.Date;

public class Expense {
    private String description;
    private double amount;
    private String category;
    private Date date;

    public Expense(String description, double amount, String category, Date date) {
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    // Getters and setters (not shown for brevity)

    @Override
    public String toString() {
        return "Expense{" +
                "description='" + description + '\'' +
                ", amount=" + amount +
                ", category='" + category + '\'' +
                ", date=" + date +
                '}';
    }
}

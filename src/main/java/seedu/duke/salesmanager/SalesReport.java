package seedu.duke.salesmanager;

public class SalesReport {
    private final SoldItem[] selectedSoldItems;
    private final int month;

    public SalesReport(SoldItem[] selectedSoldItems, int month) {
        this.selectedSoldItems = selectedSoldItems;
        this.month = month;
    }

    public String reportStats() {
        String reportStats = "";
        return reportStats;
    }

    public String reportDetails() {
        String reportDetails = "";
        return reportDetails;
    }
}

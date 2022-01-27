public class MonthlyReportRow {

    private String itemName;
    private boolean isExpense;
    private int expenseQty;
    private int amountOfOneExpense;

    MonthlyReportRow(String itemName, String isExpense, String expenseQty, String amountOfOneExpense){

        this.itemName = itemName;
        this.isExpense = Boolean.parseBoolean(isExpense);
        this.expenseQty = Integer.parseInt(expenseQty.trim());
        this.amountOfOneExpense = Integer.parseInt(amountOfOneExpense.trim());
    }

    public String getItemName() {
        return itemName;
    }

    public boolean isExpense() {
        return isExpense;
    }

    public int getExpenseQty() {
        return expenseQty;
    }

    public int getAmountOfOneExpense() {
        return amountOfOneExpense;
    }


}

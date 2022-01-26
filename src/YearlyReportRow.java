public class YearlyReportRow {

    private byte monthOfExpense;
    private int expenseAmount;
    private boolean isExpense;

        public YearlyReportRow(String monthOfExpense, String expenseAmount, String isExpense){
        this.isExpense = Boolean.parseBoolean(isExpense);
        this.monthOfExpense = (byte)Integer.parseInt(monthOfExpense.trim());
        this.expenseAmount = Integer.parseInt(expenseAmount.trim());

    }

    public byte getMonthOfExpense() {
        return monthOfExpense;
    }

    public int getExpenseAmount() {
        return expenseAmount;
    }


    public boolean isExpense() {
        return isExpense;
    }

}

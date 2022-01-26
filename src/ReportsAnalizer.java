import java.util.ArrayList;
import java.util.HashMap;

public class ReportsAnalizer {

    //returns all month numbers in which errors between monthly and yearly reports occurs
    public ArrayList<String> checkExpenses (ReportsReader reader){

        ArrayList<String> errorList = new ArrayList<>();
        for (String monthNumber: reader.getYearlyReportMap().keySet()){

            for (String isExpense: reader.getYearlyReportMap().get(monthNumber).keySet()) {
                Integer monthTotal = getMonthlyTotalAmount(reader.getMonthlyReportsMap().get(monthNumber), Boolean.parseBoolean(isExpense.trim()) );
                if (monthTotal!=reader.getYearlyReportMap().get(monthNumber).get(isExpense).getExpenseAmount()){
                    errorList.add(monthNumber);
                }
            }
        }

    return errorList;
    }

    //calculates average amount of income or expenses depending on isExpense argument value
    public Integer getAverageNumbersFromYearlyReport(HashMap<String, HashMap<String, YearlyReportRow>> yearlyReportMap, boolean isExpense){
        int totalAmount =0;
        int numberOfMonth =0;
        for (String nextMonth: yearlyReportMap.keySet()) {
            HashMap <String, YearlyReportRow> monthlyRecords = yearlyReportMap.get(nextMonth);
            numberOfMonth++;
            for (String nextKey: monthlyRecords.keySet()){
                if (Boolean.parseBoolean(nextKey.trim()) == isExpense){
                    totalAmount+=monthlyRecords.get(nextKey).getExpenseAmount();
                }

            }
        }

       return totalAmount/numberOfMonth;
    }
    //calculate difference between Incomes and Expenses for all months in the year
    public ArrayList<Integer> getMonthlyProfits (HashMap<String, HashMap<String, YearlyReportRow>> yearlyReportMap){
            ArrayList<Integer> monthlyMargines = new ArrayList<>();
            for (String nextMonth: yearlyReportMap.keySet()) {
                HashMap <String, YearlyReportRow> monthlyRecords = yearlyReportMap.get(nextMonth);
                int margin =monthlyRecords.get("false").getExpenseAmount()-monthlyRecords.get("true").getExpenseAmount();
                monthlyMargines.add(margin);

            }

            return monthlyMargines;
    }

    //calculate most profitable item or max expense depending on isExpense argument
    public MonthlyReportRow getMaxItemInMonth(ReportsReader reader, String monthNumber, boolean isExpense){
        int maxProfit = 0;
        int maxIndex =0;

            ArrayList<MonthlyReportRow> nextMonth = reader.getMonthlyReportsMap().get(monthNumber);

            for (int i=0; i<nextMonth.size(); i++) {
                MonthlyReportRow reportRow = nextMonth.get(i);
                if (reportRow.isExpense()==isExpense) {

                    int rowTotal = reportRow.getExpenseQty() * reportRow.getAmountOfOneExpense();

                    if (rowTotal>maxProfit) {
                        maxProfit = rowTotal;
                        maxIndex = i;
                    }
                }
            }


        return reader.getMonthlyReportsMap().get(monthNumber).get(maxIndex);
    }

    //calculate total monthly expense or income depending on passed argument isExpense
    private Integer getMonthlyTotalAmount(ArrayList<MonthlyReportRow> monthlyReport, Boolean isExpense) {
      int total = 0;

      for (MonthlyReportRow reportRow: monthlyReport) {
          if (reportRow.isExpense() == isExpense) {
              total += reportRow.getExpenseQty() * reportRow.getAmountOfOneExpense();
          }
      }
        return total;
    }

}

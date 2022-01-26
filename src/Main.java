import java.util.*;
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ReportsReader reader = new ReportsReader();

        while (true) {
            printMenu();
            int command = scanner.nextInt();

            if (command == 1) {
                //read all monthly expenses
                reader.createAllMonthsReportsMap(2021);

            } else if (command == 2) {
                //read yearly expenses
                reader.createYearlyReportMap(2021);
                for (String month: reader.getYearlyReportMap().keySet()){
                    HashMap<String, YearlyReportRow> expensesReport = reader.getYearlyReportMap().get(month);
                }
            } else if (command == 3) {

                if (reader.getYearlyReportMap()!=null && reader.getMonthlyReportsMap()!=null) {
                    ReportsAnalizer analyzer = new ReportsAnalizer();
                    ArrayList<String> errorMonths = analyzer.checkExpenses(reader);
                    if (errorMonths.size()>0){
                        System.out.println("Обанаружены несоответствия в суммах расходом месячного и годового баланса за следующие месяцы:");
                        for (String month: errorMonths) {
                            System.out.println(month);
                        }
                    } else {
                        System.out.println("При сверке годового и месячных отчетов ошибок не обнаружено");
                    }
                } else {
                    System.out.println("Месячные или годовые отчеты еще не считаны, пожалуйста считайте их перед сверкой");

                }
            } else if (command == 4) {
                //print monthly expenses info

                if (reader.getMonthlyReportsMap() != null) {
                    ReportsAnalizer analyzer = new ReportsAnalizer();
                    for (String monthNumber : reader.getMonthlyReportsMap().keySet()) {
                        MonthlyReportRow maxProfitableItem = analyzer.getMaxItemInMonth(reader, monthNumber, false);
                        System.out.println("В месяце номер " + monthNumber);
                        System.out.print("  Cамый прибыльный товар: " + maxProfitableItem.getItemName());
                        System.out.println("  " + maxProfitableItem.getAmountOfOneExpense() * maxProfitableItem.getExpenseQty());
                        MonthlyReportRow maxMonthlyExpense = analyzer.getMaxItemInMonth(reader, monthNumber, true);
                        System.out.print("  Самая большая трата: ");
                        System.out.print("  " + maxMonthlyExpense.getItemName());
                        System.out.println("  " + maxMonthlyExpense.getAmountOfOneExpense() * maxMonthlyExpense.getExpenseQty());

                    }



            }else {
                        System.out.println("Месячный отчет еще не считан, пожалуйста считайте его перед сверкой");
                    }
                } else if (command == 5) {
                //print yearly expenses info
                if (reader.getYearlyReportMap() != null){
                    ReportsAnalizer analyzer = new ReportsAnalizer();
                    System.out.println("2021");
                    for (int i=0;i< analyzer.getMonthlyProfits(reader.getYearlyReportMap()).size();i++){
                        System.out.println("Прибыль по месяцу "+(i+1)+" равна "+analyzer.getMonthlyProfits(reader.getYearlyReportMap()).get(i));
                    }
                    System.out.println("Средний расход за все месяцы в году: "+analyzer.getAverageNumbersFromYearlyReport(reader.getYearlyReportMap(), true));
                    System.out.println("Cредний доход за все месяцы в году: "+analyzer.getAverageNumbersFromYearlyReport(reader.getYearlyReportMap(), false));



                } else {
                    System.out.println("Годовой отчет еще не считан, пожалуйста считайте его перед сверкой");

                }


            } else if (command == 0) {
                break;
            } else {
                System.out.println("Такого пункта меню не существует, пожалуйста попробуйте еще раз");
            }


        }
    }
        //print menu for user input
        private static void printMenu(){
            System.out.println("Пожалуйста введите цифру пункта меню");
            System.out.println("1 - Считать все месячные отчёты");
            System.out.println("2 - Считать годовой отчёт");
            System.out.println("3 - Сверить отчёты");
            System.out.println("4 - Вывести информацию о всех месячных отчётах");
            System.out.println("5 - Вывести информацию о годовом отчёте");
            System.out.println("0 - Выход из программы");

        }

}


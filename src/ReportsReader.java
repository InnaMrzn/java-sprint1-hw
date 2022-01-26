import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class ReportsReader {

   public static final byte NUMBER_OF_MONTHS=3;

   //declare Maps to hold monthly and yearly reports objects
    private HashMap <String, ArrayList<MonthlyReportRow>> monthlyReportsMap;
    private HashMap <String, HashMap<String, YearlyReportRow>> yearlyReportMap;

    //read all files with monthly reports and creates HashMap (keys=months)
    public void createAllMonthsReportsMap (int yearNumber){

        this.monthlyReportsMap = new HashMap<>();
        //iterate through months
        for (int i=1; i<=NUMBER_OF_MONTHS; i++) {
            //for each month create filePath for report file
            String filePath = "m."+yearNumber+"0" + i + ".csv";
            //retrieve String with all file content
            String fileContents = readFileContentsOrNull(filePath);
            //call method to parse content for each month file
            ArrayList<MonthlyReportRow> monthlyReport = getMonthlyReport(fileContents);
            //put monthly report to Map
            String mapKey = (Integer.valueOf(i).toString().length()==1) ? "0"+i : Integer.valueOf(i).toString();
            getMonthlyReportsMap().put(mapKey,monthlyReport);

        }
    }

    //read file with yearly report and creates HashMap (keys=months, then keys=isExpense)
    public void createYearlyReportMap (int yearNumber) {
        yearlyReportMap = new HashMap<>();
        // split into lines
        String filePath = "y."+yearNumber + ".csv";
        //retrieve String with all file content
        String fileContents = readFileContentsOrNull(filePath);
        //call method to parse content for each month file
        String[] lines = fileContents.split("\\n");

        for (int i = 1; i<lines.length;i++){
            //split into columns
            String[] columns = lines[i].split(",");

            YearlyReportRow report = new YearlyReportRow(columns[0],columns[1],columns[2].trim());
            if (yearlyReportMap.containsKey(columns[0].trim())) {
                HashMap <String, YearlyReportRow> expensesMap = yearlyReportMap.get(columns[0]);
                expensesMap.put(columns[2].trim(),report);
                yearlyReportMap.put(columns[0].trim(), expensesMap);

            } else {
                HashMap<String, YearlyReportRow> expensesMap = new HashMap<>();
                expensesMap.put(columns[2].trim(), report);
                yearlyReportMap.put(columns[0].trim(), expensesMap);
            }


        }

    }

    public HashMap<String, ArrayList<MonthlyReportRow>> getMonthlyReportsMap() {
        return monthlyReportsMap;
    }

    public HashMap<String, HashMap<String, YearlyReportRow>> getYearlyReportMap() {
        return yearlyReportMap;
    }

    //reads file and returns content as a String
    private String readFileContentsOrNull(String filePath) {

        try {
            String directoryPath = "C:\\\\Users\\Imurzina\\IdeaProjects\\java-sprint1-hw\\src\\";
            String fullPath = directoryPath+filePath;
            return Files.readString(Path.of(fullPath));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }

    //returns array with rows and columns of single monthly report file
    private ArrayList<MonthlyReportRow> getMonthlyReport (String fileContents) {

        ArrayList<MonthlyReportRow> monthlyReport = new ArrayList<>();

        //split into rows
        String[] rows = fileContents.split("\\n");

        for (int i = 1; i<rows.length;i++){
            // split each row into columns
            String[] columns = rows[i].split(",");
            //instantiate MonthlyReportRow object and populate with values
            MonthlyReportRow reportRow = new MonthlyReportRow(columns[0],columns[1],columns[2],columns[3]);
            //add new object to array of rows
            monthlyReport.add(reportRow);
        }
        return monthlyReport;

    }



}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SysAnatomyUI;

/**
 *
 * @author Saranya
 */
public class GenerateReport {
  public static void Main(String[] args)
  {
    // First, load JasperDesign from XML and compile it into JasperReport
JasperDesign jasperDesign = JasperManager.loadXmlDesign("../ReportDesign/Design1.xml");
JasperReport jasperReport = JasperManager.compileReport(jasperDesign);
// Second, create a map of parameters to pass to the report.
Map parameters = new HashMap();
parameters.put("ReportTitle", "Basic JasperReport");
parameters.put("MaxSalary", new Double(25000.00));
// Third, get a database connection
Connection conn = Database.getConnection(); 
// Fourth, create JasperPrint using fillReport() method
JasperPrint jasperPrint = JasperManager.fillReport(jasperReport, 
   parameters, conn);
// You can use JasperPrint to create PDF
JasperManager.printReportToPdfFile(jasperPrint, "BasicReport.pdf");
// Or to view report in the JasperViewer
JasperViewer.viewReport(jasperPrint);
  }
}



package MainPages;

import controller.ReportController;
import java.sql.SQLException;

public class ReportMain {
    public static void main(String[] args) throws SQLException{
        ReportController sc = new ReportController() ;
        sc.start(); 
    }

}
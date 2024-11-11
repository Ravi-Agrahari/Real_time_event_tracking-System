
package MainPages ;

import controller.MetricController;
import java.sql.SQLException;




public class MetricMain {
    public static void main(String[] args) {
        try {
            MetricController metricController = new MetricController();
            metricController.start();
        } catch (SQLException e) {
            System.out.println("Failed to initialize the MetricController: " + e.getMessage());
        }
    }
}

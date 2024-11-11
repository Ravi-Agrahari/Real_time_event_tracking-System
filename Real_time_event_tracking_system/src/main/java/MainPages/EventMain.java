
package MainPages;

import controller.EventController;
import java.sql.SQLException;

public class EventMain {
    public static void main(String[] args) throws SQLException{
        EventController ed = new EventController() ; 
        ed.displayDashboard(); 
        
    }
}

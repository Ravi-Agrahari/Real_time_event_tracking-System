

package MainPages;

import controller.SessionController;
import java.sql.SQLException;

public class SessionMain {
    public static void main(String[] args) throws SQLException{
        SessionController sc = new SessionController() ;
        sc.start(); 
    }

}

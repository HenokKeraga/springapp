import com.jcraft.jsch.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main1 {

    static Connection conn = null;
    static String userid="root", password = "letsgomarco";
    static String url = "jdbc:mysql://DbServerIpAddress:/3306/tkdeneme";
    public static Channel channel;
    /** Creates a new instance of DbConnect */

    public static Connection initMySQLJDBCConnection() throws InstantiationException, IllegalAccessException{

        try {
            Class.forName ("com.mysql.jdbc.Driver").newInstance ();

        } catch(java.lang.ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
        }

        try {
            conn= DriverManager.getConnection(url,userid, password);
        } catch(SQLException ex) {
            System.err.println("SQLException: " + ex.toString());
        }

        return conn;
    }
    public static void closeMySQLJDBCConnection(){
        if(conn!=null){
            try{
                conn.close();
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws JSchException {
        // TODO code application logic here
        JSch jsch=new JSch();
        String host="0.0.0.0";
        String user="mike";
        int port=22;

        Session session=jsch.getSession(user, host, port);
        try{


            // username and password will be given via UserInfo interface.
            UserInfo ui=new MyUserInfo("password123");
            session.setUserInfo(ui);
            session.connect();
            System.out.println("Session opened.");
            Channel channel=session.openChannel("exec");
            // channel.
            initMySQLJDBCConnection();
            channel.disconnect();

        }
        catch(Exception ex){
            System.err.println(ex.toString());
            System.err.println("Error occured!");
            System.exit(0);
        }
        finally
        {
            System.out.println("Connection established");
            closeMySQLJDBCConnection();
            session.disconnect();
        }
    }

}


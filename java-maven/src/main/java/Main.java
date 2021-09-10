import com.jcraft.jsch.*;

import java.io.ByteArrayOutputStream;
import java.sql.*;
import java.util.Properties;

public class Main {
  //  public static void main(String[] args) throws JSchException, SQLException {
  //
  //    //    JSch jSch = new JSch();
  //    //    Session session=jSch.getSession("root","0.0.0.0");
  //    //    session.setPort(3306);
  //    //    session.setPassword("letsgomarco");
  //    //    session.setConfig("StrictHostKeyChecking","no");//disable the server key //no
  // authenticate
  //    //    session.connect();
  //    //    ChannelSftp channel= (ChannelSftp) session.openChannel("sftp");
  //    //
  //    //    channel.connect();
  //    //
  //    //    channel.disconnect();
  //    //
  //    //    session.disconnect();
  //
  //    int lport = 5656;
  //
  //    int rport = 3306;
  //
  //    String rhost = "0.0.0.0";
  //
  //    String host = "0.0.0.0";
  //
  //    String user = "root";
  //
  //    String password = "letsgomarco";
  //
  //    String dbUser = "";
  //
  //    String dbPass = "";
  //
  //    String schema = "somedatabase";
  //    Connection conn = null;
  //    Session jschSession = null;
  //
  //    try {
  //
  //      Properties config = new Properties();
  //
  //      config.put("StrictHostKeyChecking", "no");
  //
  //      JSch jsch = new JSch();
  //
  //      jschSession = jsch.getSession(user, host, 22);
  //
  //      jschSession.setPassword(password);
  //
  //      jschSession.setConfig(config);
  //
  //      jschSession.connect();
  //
  //      System.out.println("Connected");
  //
  //      int assigned_port = jschSession.setPortForwardingL(lport, rhost, rport);
  //
  //      System.out.println("localhost:" + assigned_port + " -> " + rhost + ":" + rport);
  //
  //      System.out.println("Port Forwarded");
  //
  //      Class.forName("com.mysql.jdbc.Driver").newInstance();
  //
  //      String url = "jdbc:mysql://localhost:" + rport + "/" + schema;
  //
  //      conn = DriverManager.getConnection(url, dbUser, dbPass);
  //
  //      System.out.println("Database connection established");
  //
  //      Statement stmt = conn.createStatement();
  //
  //      String sql = "SELECT * FROM CUSTOMER";
  //
  //      ResultSet rs = stmt.executeQuery(sql);
  //
  //      while (rs.next()) {
  //
  //        System.out.println(rs.getInt(1) + " " + rs.getString(2));
  //      }
  //
  //      rs.close();
  //
  //      stmt.close();
  //
  //      System.out.println("DONE");
  //
  //    } catch (Exception e) {
  //
  //      e.printStackTrace();
  //
  //      System.out.println(e.getMessage());
  //
  //      if (jschSession != null && jschSession.isConnected()) {
  //
  //        System.out.println("Closing SSH Connection during error");
  //
  //        System.out.println("Closing SSH Connection during error");
  //
  //        jschSession.disconnect();
  //      }
  //
  //    } finally {
  //
  //      if (conn != null && !conn.isClosed()) {
  //
  //        System.out.println("Closing Database Connection");
  //
  //        System.out.println("Closing Database Connection");
  //
  //        conn.close();
  //      }
  //
  //      if (jschSession != null && jschSession.isConnected()) {
  //
  //        System.out.println("Closing SSH Connection");
  //
  //        System.out.println("Closing SSH Connection");
  //
  //        // jschSession.disconnect();
  //
  //      }
  //    }
  //  }

  public static void main(String args[]) throws Exception {
    String username = "mike";
    String password = "password123";
    String host = "0.0.0.0";
    int port = 22;
    String command = "get filename.zip";
    String response=listFolderStructure(username, password, host, port, command);
    System.out.println(response);
  }

  public static String listFolderStructure(
      String username, String password, String host, int port, String command) throws Exception {
    Session session = null;
    ChannelExec channel = null;
    String response = null;
    try {
      session = new JSch().getSession(username, host, port);
      session.setPassword(password);
      session.setConfig("StrictHostKeyChecking", "no");
      session.connect();
      channel = (ChannelExec) session.openChannel("exec");
      channel.setCommand(command);
      ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
      ByteArrayOutputStream errorResponseStream = new ByteArrayOutputStream();
      channel.setOutputStream(responseStream);
      channel.setErrStream(errorResponseStream);
      channel.connect();
      while (channel.isConnected()) {
        Thread.sleep(100);
      }
      String errorResponse = new String(errorResponseStream.toByteArray());
      response = new String(responseStream.toByteArray());
      if (!errorResponse.isEmpty()) {
        throw new Exception(errorResponse);
      }
    } finally {
      if (session != null) {
        session.disconnect();
      }
      if (channel != null) {
        channel.disconnect();
      }
    }
    return response;
  }
}

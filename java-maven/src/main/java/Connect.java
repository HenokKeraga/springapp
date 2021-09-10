import com.jcraft.jsch.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Connect {

  public static void main(String[] args) {
    //
    String host = "0.0.0.0";
    int port = 22;
    String username = "mike";
    String password = "password123";
    JSch jSch = new JSch();
    Session session = null;
    Channel channel = null;
    ChannelSftp channelSftp = null;

    try {

      session = jSch.getSession(username, host, port);

      Properties properties = new Properties();
      properties.put("StrictHostKeyChecking", "no");

      session.setConfig(properties);
      session.setPassword(password);
      session.connect();

      channel=session.openChannel("sftp");
      channel.connect();
      System.out.println("connected");

      channelSftp=(ChannelSftp)channel;
      String fullLocation="upload";
      channelSftp.cd(fullLocation);
      String home = System.getProperty("user.home");
// upload
//      File file   = new File(home + "/Desktop/test.txt");
//      FileInputStream fileInputStream = new FileInputStream(file);
//      channelSftp.put(fileInputStream,file.getName());
//      System.out.println(" upload success ");
//      fileInputStream.close();
   //dowload
      String remoteLoc= "/upload/test.txt";
      channelSftp.get(remoteLoc,home + "/Desktop/test.txt");
      System.out.println("downloaded");
//      channel = session.openChannel("exec");
//      ((ChannelExec)channel).setCommand("cd /upload");
//
//      InputStream inputStream =channel.getInputStream();
//      channel.connect();
//      StringBuilder output = new StringBuilder();
//      int bytesRead;
//      byte[] buffer= new byte[1024];
//      while (!channel.isClosed()){
//        while ((bytesRead=inputStream.read(buffer,0,buffer.length))!=-1){
//          output.append(new String(buffer,0,bytesRead));
//        }
//      }
//
//      System.out.println(output);

    } catch (Exception e) {
      System.out.println("exception "+e);
    }finally{
      //channelSftp.disconnect();
      channel.disconnect();
      session.disconnect();

    }
  }
}

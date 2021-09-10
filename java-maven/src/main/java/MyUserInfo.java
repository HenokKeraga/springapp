import com.jcraft.jsch.UserInfo;

public class MyUserInfo implements UserInfo {

    public MyUserInfo(String password) {
    }

    public boolean promptYesNo(String str) {
    return false;
  }

  public boolean promptPassword(String str) {
    return false;
  }

  public boolean promptPassphrase(String str) {
    return false;
  }

  public void showMessage(String str) {
    return;
  }

  public String getPassword() {
    return null;
  }

  public String getPassphrase() {
    return null;
  }
}
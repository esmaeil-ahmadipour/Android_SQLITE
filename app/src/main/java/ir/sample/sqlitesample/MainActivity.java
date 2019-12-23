package ir.sample.sqlitesample;

import android.os.Bundle;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import ir.sample.sqlitesample.model.data_access.User_DataAccess;
import ir.sample.sqlitesample.model.table_object.User;

public class MainActivity extends AppCompatActivity {

    private User_DataAccess userDataAccess;
    private List<User> userList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userDataAccess = new User_DataAccess(this);
        userDataAccess.open();

        insertUser("Admin", "Admin@gmail.com");
        insertUser("User", "User@gmail.com");
        insertUser("User2", "User2@gmail.com");
        updateUserById(2,"John","John@gmail.com");
        updateUserByName("User2","Fredrick","Fredrick@yahoo.com");
        userList = userDataAccess.getAllUser();
        selectQuery(userList);

    }

    @Override
    protected void onPause() {
        userDataAccess.close();
        super.onPause();
    }

    @Override
    protected void onResume() {
        userDataAccess.open();
        super.onResume();
    }


    private void insertUser(String userName, String userEmail) {
        User user = new User();
        user.setName(userName);
        user.setEmail(userEmail);
        userDataAccess.addUser(user);
    }


    private void selectQuery(List<User> allUserList) {
        if (allUserList.size() != 0) {
            for (User user : allUserList) {
                System.out.println("Id:" + user.getId());
                System.out.println("Name:" + user.getName());
                System.out.println("Email:" + user.getEmail());
            }
        }
    }

    private void updateUserById(int userId ,String userName, String userEmail) {
        User user = new User();
        user.setName(userName);
        user.setEmail(userEmail);
        userDataAccess.updateUserById(user , userId);
    }
    private void updateUserByName(String oldUserName ,String newUserName, String newUserEmail) {
        User user = new User();
        user.setName(newUserName);
        user.setEmail(newUserEmail);
        userDataAccess.updateUserByName(user , oldUserName);
    }
}

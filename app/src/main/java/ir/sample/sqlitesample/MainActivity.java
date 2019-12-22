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
        userList = userDataAccess.getAllUser();

        insertUser("Admin", "Admin@gmail.com");
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
}

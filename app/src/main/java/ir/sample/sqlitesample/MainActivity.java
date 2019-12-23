package ir.sample.sqlitesample;

import android.os.Bundle;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import ir.sample.sqlitesample.model.data_access.User_DataAccess;
import ir.sample.sqlitesample.model.table_object.User;

public class MainActivity extends AppCompatActivity {

    private User_DataAccess userDataAccess;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userDataAccess = new User_DataAccess(this);
        userDataAccess.open();

        //Insert 5 Records ;
        prepareInsertData();


        //Update Records by Two Different Methods;
        updateUserById(4, "John", "John@gmail.com");
        updateUserByName("User4", "Fredrick", "Fredrick@yahoo.com");

        //Delete Records by Two Different Methods;
        deleteUserById(2);
        deleteUserByName("User2");

        //Select Records by Two Different Methods;
        selectAllUsers();
        selectUserById(1);
        selectUserByName("Fredrick");


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

    private void prepareInsertData() {
        if (userDataAccess.emptyTable()) {
            System.out.println("Prepare Insert Data.");

            insertUser("Admin", "Admin@gmail.com");
            insertUser("User1", "User1@gmail.com");
            insertUser("User2", "User2@gmail.com");
            insertUser("User3", "User3@gmail.com");
            insertUser("User4", "User4@gmail.com");
        } else
            System.out.println("Available Data.");
    }

    private void selectAllUsers() {
        List<User> selectAllUsers_List;
        selectAllUsers_List = userDataAccess.getAllUsers();

        if (selectAllUsers_List.size() != 0) {
            for (User user : selectAllUsers_List) {
                System.out.println("Id:" + user.getId());
                System.out.println("Name:" + user.getName());
                System.out.println("Email:" + user.getEmail());
            }
        }
    }

    private void selectUserById(int userId) {
        User user = new User();
        user = userDataAccess.getUserById(userId);
        System.out.println("Id:" + user.getId());
        System.out.println("Name:" + user.getName());
        System.out.println("Email:" + user.getEmail());
    }

    private void selectUserByName(String userName) {
        List<User> selectUsersByName_List;
        selectUsersByName_List = userDataAccess.getUsersByName(userName);

        if (selectUsersByName_List.size() != 0) {
            for (User user : selectUsersByName_List) {
                System.out.println("Id:" + user.getId());
                System.out.println("Name:" + user.getName());
                System.out.println("Email:" + user.getEmail());
            }
        }
    }

    private void insertUser(String userName, String userEmail) {
        User user = new User();
        user.setName(userName);
        user.setEmail(userEmail);
        userDataAccess.addUser(user);
    }

    private void updateUserById(int userId, String userName, String userEmail) {
        User user = new User();
        user.setName(userName);
        user.setEmail(userEmail);
        userDataAccess.updateUserById(user, userId);
    }

    private void updateUserByName(String oldUserName, String newUserName, String newUserEmail) {
        User user = new User();
        user.setName(newUserName);
        user.setEmail(newUserEmail);
        userDataAccess.updateUserByName(user, oldUserName);
    }

    private void deleteUserById(int userId) {
        userDataAccess.deleteUserById(userId);
    }

    private void deleteUserByName(String userName) {
        userDataAccess.deleteUserByName(userName);
    }

}

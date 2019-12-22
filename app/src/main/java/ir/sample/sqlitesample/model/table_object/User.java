package ir.sample.sqlitesample.model.table_object;
//This  is  An Encapsulation Class For Columns Of Table Users
public class User {
    private  int id;
    private  String name ;
    private  String email;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}

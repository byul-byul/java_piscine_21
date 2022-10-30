public interface UserList {

    public void     addUser(User newUser);
    public User     getUserByID(Integer id);
    public User     getUserByIndex(int index);
    public int      getUserCount();
    public void     showDetails();
}

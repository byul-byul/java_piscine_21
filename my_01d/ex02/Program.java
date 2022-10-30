public class Program {
    public static void  main(String[] args) {

        User            sender      = new User();
        User            recipient   = new User("Bob", 500);
        User            neo         = new User("Neo", 7777);

        sender.setName("Alex");
        sender.setBalance(1000);

        sender.showDetails();
        recipient.showDetails();
        neo.showDetails();

        UsersArrayList   userList = new UsersArrayList();
        userList.showDetails();

        userList.addUser(sender);
        userList.addUser(recipient);
        userList.addUser(neo);
        userList.showDetails();

        System.out.println("\n>>>>>>>> SEARCH TEST <<<<<<<<");
        try {
            userList.getUserByIndex(1).showDetails();
            userList.getUserByID(neo.getIdentifier()).showDetails();
            userList.getUserByIndex(4).showDetails();
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println(exception.toString());
        } catch (UserNotFoundException exception) {
            System.out.println(exception.toString());
        }
    }
}

public class Program {

    public static void  showUserFields(User u) {

        System.out.println("***__U_DATA__***");
        System.out.println("user_name: " + u.getName());
        System.out.println("user_idtf: " + String.format("%08d", u.getIdentifer()));
        System.out.println("user_blnc: " + u.getBalance());
        System.out.println("***__________***");
        System.out.println();
    }

    public static void  main(String[] args) {

        User        sender = new User();
        User        recipient = new User("Bob", 500);
        User        qwerty = new User();
        User        asdfgh = new User();
        User        zxcvbn = new User();


        sender.setName("Alex");
        sender.setBalance(1000);

        showUserFields(sender);
        showUserFields(recipient);
        showUserFields(qwerty);
        showUserFields(asdfgh);
        showUserFields(zxcvbn);

    }
}
//String.format("%03d", lastGeneratedID)
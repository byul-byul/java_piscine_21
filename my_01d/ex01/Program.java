public class Program {

    public static void  main(String[] args) {

        User        sender = new User();
        User        recipient = new User("Bob", 500);
        User        qwerty = new User();
        User        asdfgh = new User();
        User        zxcvbn = new User();

        sender.setName("Alex");
        sender.setBalance(1000);

        sender.showDetails();
        recipient.showDetails();
        qwerty.showDetails();
        asdfgh.showDetails();
        zxcvbn.showDetails();
    }
}

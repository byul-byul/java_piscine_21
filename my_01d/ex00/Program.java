public class Program {

    public static void  main(String[] args) {

        User        sender = new User();
        User        recipient = new User(0002, "Bob", 500);

        sender.setIdentifier(0001);
        sender.setName("Alex");
        sender.setBalance(1000);

        sender.showDetails();
        recipient.showDetails();

        Transaction credit = new Transaction(recipient, sender, false, -30);
        credit.showDetails();
        sender.showDetails();
        recipient.showDetails();

        Transaction debit = new Transaction(recipient, sender, true, 50);
        debit.showDetails();
        sender.showDetails();
        recipient.showDetails();

        Transaction wrong = new Transaction(recipient, sender, false, 80);
        wrong.showDetails();
        sender.showDetails();
        recipient.showDetails();;
    }
}

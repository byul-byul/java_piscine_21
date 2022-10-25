public class Program {

    public static void  showUserFields(User u) {

        System.out.println("***__U_DATA__***");
        System.out.println("user_name: " + u.getName());
        System.out.println("user_idtf: " + u.getIdentifer());
        System.out.println("user_blnc: " + u.getBalance());
        System.out.println("***__________***");
        System.out.println();
    }

    public static void  showTransactionFields(Transaction t) {

        System.out.println("***__T_DATA__***");
        System.out.println("t_id: " + t.getIdentifier());
        System.out.println("t_recipient: " + t.getRecipient().getName());
        System.out.println("t_sender: " + t.getSender().getName());
        System.out.println("t_category: " + t.getCategory());
        System.out.println("t_amount: " + t.getAmount());
        System.out.println("***__________***");
        System.out.println();
    }
    public static void  main(String[] args) {

        User        sender = new User();
        User        recipient = new User(0002, "Bob", 500);

        sender.setIdentifier(0001);
        sender.setName("Alex");
        sender.setBalance(1000);

        showUserFields(sender);
        showUserFields(recipient);

        Transaction credit = new Transaction(recipient, sender, false, -30);
        showTransactionFields(credit);
        showUserFields(sender);
        showUserFields(recipient);
        System.out.println("###################");
        System.out.println("###################");

        Transaction debit = new Transaction(recipient, sender, true, 50);
        showTransactionFields(debit);
        showUserFields(sender);
        showUserFields(recipient);
        System.out.println("###################");
        System.out.println("###################");

        Transaction wrong = new Transaction(recipient, sender, false, 80);
        showTransactionFields(wrong);
        showUserFields(sender);
        showUserFields(recipient);
        System.out.println("###################");
        System.out.println("###################");
    }
}

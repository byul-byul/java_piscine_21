import java.util.UUID;

public class Program {
    public static void main(String[] args) {

        User        alex = new User("Alex", 1000);
        User        boba = new User("Boba", 500);
        User        mike = new User("Mike", 2000);
        User        gala = new User("Gala", 800);
        User        sara = new User("Sara", 1500);

        alex.showDetails();
        boba.showDetails();
        mike.showDetails();
        gala.showDetails();
        sara.showDetails();

        Transaction transaction_1 = new Transaction(alex, boba, false, -30);
        Transaction transaction_2 = new Transaction(boba, mike, true, 130);
        Transaction transaction_3 = new Transaction(gala, sara, false, -300);
        Transaction transaction_4 = new Transaction(mike, alex, false, -400);
        Transaction transaction_5 = new Transaction(boba, mike, true, 700);

        alex.showDetails();
        boba.showDetails();
        mike.showDetails();
        gala.showDetails();
        sara.showDetails();

        transaction_1.showDetails();
        transaction_2.showDetails();
        transaction_3.showDetails();
        transaction_4.showDetails();
        transaction_5.showDetails();

        alex.getTList().showDetails();
        boba.getTList().showDetails();

        Transaction[]	transactionArray;
        transactionArray = alex.getTList().toArray();
        transactionArray[1].showDetails();
        System.out.println("transactionArray.length = " + transactionArray.length);

        alex.getTList().delete(transactionArray[1].getIdentifier());

        transactionArray = alex.getTList().toArray();
        System.out.println("transactionArray.length = " + transactionArray.length);

        try {
            alex.getTList().delete(UUID.randomUUID());
        } catch (TransactionNotFoundException e) {
            System.out.println(e.toString());
        }
    }
}

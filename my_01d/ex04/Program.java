public class Program {
    public static void main(String[] args) {

        TransactionsService     newTS = new TransactionsService();
        User                    boba = new User("Boba", 5000);
        User                    alex = new User("Alex", 3000);
        User                    soma = new User("Soma", 1500);

        newTS.addUser(boba);
        newTS.addUser(alex);
        newTS.addUser(soma);

        boba.showDetails();
        alex.showDetails();
        soma.showDetails();

        newTS.makeTransaction(alex.getIdentifier(), boba.getIdentifier(), 500);
        boba.showDetails();
        alex.showDetails();
        soma.showDetails();
        newTS.getUserTransactionsList(alex.getIdentifier())[0].showDetails();
        newTS.getUserTransactionsList(boba.getIdentifier())[0].showDetails();

        newTS.makeTransaction(soma.getIdentifier(), boba.getIdentifier(), 800);
        boba.showDetails();
        alex.showDetails();
        soma.showDetails();
        newTS.getUserTransactionsList(soma.getIdentifier())[0].showDetails();
        newTS.getUserTransactionsList(boba.getIdentifier())[0].showDetails();

        newTS.makeTransaction(soma.getIdentifier(), boba.getIdentifier(), 0);
        boba.showDetails();
        alex.showDetails();
        soma.showDetails();
        newTS.getUserTransactionsList(soma.getIdentifier())[0].showDetails();
        newTS.getUserTransactionsList(boba.getIdentifier())[0].showDetails();
        newTS.getInvalidTransactionsList()[0].showDetails();
    }
}
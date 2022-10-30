public class TransactionsService {

	private UserList            _userList;
    private TransactionsList    _invalidTransactionsList;

    public                      TransactionsService() {
        _userList = new UserArrayList();
        _invalidTransactionsList = new TransactionsLinkedList();
    }

    public Transaction[]        getInvalidTransactionsList() {
        return(_invalidTransactionsList.toArray());
    }

    public Transaction[]        getUserTransactionsList(Integer id) {
        return(_userList.getUserByID(id).getTList());
    }

    public void                 addToInvalidTransactionsList(Transaction t) {
        _invalidTransactionsList.add(t);
    }

    public void                 addUser(User u) {
        _userList.addUser(u);
    }

    public Integer              getUserBalance(User u) {
        return (u.getBalance());
    }
    public void                 makeTransaction(Integer recID, Integer senID, Integer amt) {

        if (amt == 0) {
            System.err.println("Incorrect arguments for Transaction() !");
            System.exit(-1);
        }
        Transaction     temp;

        temp._identifier = UUID.randomUUID();
        try {
            temp._recipient = UserList.getUserByID(recID);
            temp._sender = UserList.getUserByID(senID);
        } catch (UserNotFoundException e) {
            System.out.println(e.toString());
        }
        temp._amount = amt;
        if (amt > 0) {
            temp._isDebit = true;
            temp._recipient.getTList().add(temp);
            temp._amount = -amt;
            temp._isDebit = false;
            temp._sender.getTList().add(temp);
        }
        else {
            temp._isDebit = false;
            temp._sender.getTList().add(temp);
            temp._amount = -amt;
            temp._isDebit = true;
            temp._recipient.getTList().add(temp);
        }
    }
    public Transaction[]        getUserTransactionsList(User u) {}
}
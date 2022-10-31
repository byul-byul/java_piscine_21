import java.util.UUID;

public class TransactionsService {

	private UserList            _userList;
    private TransactionsList    _invalidTransactionsList;

    public                      TransactionsService() {
        _userList = new UsersArrayList();
        _invalidTransactionsList = new TransactionsLinkedList();
    }

    public Transaction[]        getInvalidTransactionsList() {
        return(_invalidTransactionsList.toArray());
    }

    public Transaction[]        getUserTransactionsList(Integer id) {
        return(_userList.getUserByID(id).getTList().toArray());
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

    public Transaction[]        getUserTransactionsList(User u) {
        return (u.getTList().toArray());
    }

    public void                 makeTransaction(Integer recID, Integer senID, Integer amt) {
        if (getUserBalance(_userList.getUserByID(senID)) < amt) {
            throw (new IllegalTransactionException());
        }
        User tempR = new User();
        User tempS = new User();
        try {
            tempR = _userList.getUserByID(recID);
            tempS = _userList.getUserByID(senID);

        } catch (UserNotFoundException exception) {
            System.out.println(exception.toString());
            return ;
        }
        UUID tempID = UUID.randomUUID();

        if (amt == 0) {
            _invalidTransactionsList.add(new Transaction(tempID, tempR, tempS, amt > 0 ? true : false, amt));
            return ;
        }
        tempR.getTList().add(new Transaction(tempID, tempR, tempS, amt > 0 ? true : false, amt));
        tempS.getTList().add(new Transaction(tempID, tempR, tempS, amt > 0 ? false : true, -amt));
    }
}

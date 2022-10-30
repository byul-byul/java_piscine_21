public class User {

    private Integer             _identifier;
    private String              _name;
    private int                 _balance;
    private TransactionsList    _transactionList;

    public          User(String name, int balance) {
        _identifier = UserIdsGenerator.getInstance().generateId();
        _transactionList = new TransactionsLinkedList();
        _name = name;
        _balance = balance;
        if (_balance < 0) {
            _balance = 0;
        }
    }

    public          User() {
        _identifier = UserIdsGenerator.getInstance().generateId();
        _transactionList = new TransactionsLinkedList();
        _name = null;
        _balance = 0;
    }

    public Integer  getIdentifier() {
        return (_identifier);
    }

    public String   getName() {
        return (_name);
    }

    public int      getBalance() {
        return (_balance);
    }

    public TransactionsList getTList() {
        return (_transactionList);
    }

    public void     setName(String name) {
        _name = name;
    }

    public void     setBalance(int balance) {
        _balance = balance;
    }

    public void     showDetails() {
        System.out.println( "\n***** USER_DETAILS *****" + 
                            "\nName:\t\t" + _name +
                            "\nID:\t\t" + String.format("%08d", _identifier) +
                            "\nBalance:\t" + _balance +
                            "\n************************");
    }
}

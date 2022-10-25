public class User {

    private Integer     _identider;
    private String      _name;
    private int         _balance;

    public          User(String name, int balance) {
        _identider = UserIdsGenerator.getInstance().generateId();
        _name = name;
        _balance = balance;
        if (_balance < 0) {
            _balance = 0;
        }
    }

    public          User() {
        _identider = UserIdsGenerator.getInstance().generateId();
        _name = null;
        _balance = 0;
    }

    public Integer  getIdentifer() {
        return (_identider);
    }

    public String   getName() {
        return (_name);
    }

    public int      getBalance() {
        return (_balance);
    }

    public void     setName(String name) {
        _name = name;
    }

    public void     setBalance(int balance) {
        _balance = balance;
    }
}

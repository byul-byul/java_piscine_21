import java.util.UUID;

public class Transaction {

    private UUID    _identifier;
    private User    _recipient;
    private User    _sender;
    private boolean _isDebit;
    private Integer _amount;

    private         Transaction() {}

/*    public          Transaction(Integer recID, Integer senID, Integer amt) {
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
        if (amt > 0) {
            temp._amount = amt;
            temp._isDebit = true;
            temp._recipient.getTList().add(temp);
            temp._amount = -amt;
            temp._isDebit = false;
            temp._sender.getTList().add(temp);
        }
        else {
            temp._amount = amt;
            temp._isDebit = false;
            temp._sender.getTList().add(temp);
            temp._amount = -amt;
            temp._isDebit = true;
            temp._recipient.getTList().add(temp);
        }
    }*/

    public          Transaction(User rcp, User snd, boolean ctg, Integer amt) {
        if (rcp == null || snd == null || amt == null) {
            System.err.println("Incorrect arguments for Transaction() !");
            System.exit(-1);
        }
        _identifier = UUID.randomUUID();
        _recipient = rcp;
        _sender = snd;
        _isDebit = ctg;
        _amount = amt;

        if (_isDebit && _amount > 0) {
            _recipient.setBalance(_recipient.getBalance() + _amount);
            _sender.setBalance(_sender.getBalance() - _amount);
        }
        else if (!_isDebit && _amount < 0) {
            _recipient.setBalance(_recipient.getBalance() - _amount);
            _sender.setBalance(_sender.getBalance() + _amount);
        }
        else {
            _amount = 0;
        }
        _recipient.getTList().add(this);
        _sender.getTList().add(this);
    }

    public UUID     getIdentifier() {
        return (_identifier);
    }

    public User     getRecipient() {
        return (_recipient);
    }

    public User     getSender() {
        return (_sender);
    }

    public boolean  getCategory() {
        return (_isDebit);
    }

    public Integer  getAmount() {
        return (_amount);
    }

    public void     showDetails() {
        System.out.println( "\n***** TRANSACTION_DETAILS *****" + 
                            "\nUUID:\t\t" + _identifier +
                            "\nRecipient:\t" + _recipient.getName() +
                            "\nSender:\t\t" + _sender.getName() +
                            "\nAmount:\t\t" + _amount +
                            "\nIsDebit\t\t" + _isDebit +
                            "\n*******************************");
    }
}

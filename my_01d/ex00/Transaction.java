import java.util.UUID;

public class Transaction {

    private UUID    _identifier;
    private User    _recipient;
    private User    _sender;
    private boolean _isDebit;
    private Integer _amount;

    public          Transaction(User rcp, User snd, boolean ctg, Integer amt) {
        _identifier = UUID.randomUUID();
        _recipient = rcp;
        _sender = snd;
        _isDebit = ctg;
        _amount = amt;

        if (_isDebit && _amount > 0) {
            rcp.setBalance(rcp.getBalance() + amt);
            snd.setBalance(snd.getBalance() - amt);
        }
        else if (!_isDebit && _amount < 0) {
            rcp.setBalance(rcp.getBalance() - amt);
            snd.setBalance(snd.getBalance() + amt);
        }
        else {
            _amount = 0;
        }
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

    public void     setRecipient(User rcp) {
        _recipient = rcp;
    }

    public void     setSender(User snd) {
        _sender = snd;
    }

    public void     setCategory(boolean ctg) {
        _isDebit = ctg;
    }

    public void     setAmount(Integer amt) {
        _amount = amt;
    }
}

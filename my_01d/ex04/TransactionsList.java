import java.util.UUID;

public interface TransactionsList {
    public int              size();
    public void             add(Transaction transaction);
    public void             delete(UUID id);
    public Transaction[]    toArray();
    public void             showDetails();
}
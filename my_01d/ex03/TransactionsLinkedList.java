import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {

    private int             _size;
    private TransactionNode _head;
    private TransactionNode _tail;

    public                  TransactionsLinkedList() {
        _size = 0;
        _head = new TransactionNode(null);
        _tail = new TransactionNode(null);
        _head.next = _tail;
        _tail.prev = _head;
    }

    @Override
    public int              size() {
        return (_size);
    }

    @Override
    public void             add(Transaction transaction) {
        TransactionNode newNode = new TransactionNode(transaction, _tail, _tail.prev);
        _tail.prev.next = newNode;
        _tail.prev = newNode;
        _size++;
    }

    @Override
    public void             delete(UUID id) {
        TransactionNode     temp = _head.next;
        while (temp != _tail) {
            if (temp.transaction.getIdentifier().equals(id)) {
                temp.prev.next = temp.next;
                temp.next.prev = temp.prev;
                _size--;
                return ;
            }
            temp = temp.next;
        }
        throw (new TransactionNotFoundException());
    }

    @Override
    public Transaction[]    toArray() {
        Transaction[]       array = new Transaction[_size];
        TransactionNode     temp = _head.next;
        for (int i = 0; i < _size; i++) {
            array[i] = temp.transaction;
            temp = temp.next;
        }
        return (array);
    }

    @Override
    public void             showDetails() {
        System.out.println( "\n>>>>>> TRANSACTION_LIST_DETAILS <<<<<<" + 
                            "\nList size:\t\t" + _size);
        TransactionNode     temp = _head.next;
        while (temp != _tail) {
            temp.transaction.showDetails();
            temp = temp.next;
        }
        System.out.println("\n>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<");
    }
}

class TransactionNode {

    public Transaction      transaction;
    public TransactionNode  next;
    public TransactionNode  prev;

    public          TransactionNode(Transaction t) {
        transaction = t;
        next = null;
        prev = null;
    }

    public          TransactionNode(Transaction t, TransactionNode n, TransactionNode p) {
        transaction = t;
        next = n;
        prev = p;
    }
}

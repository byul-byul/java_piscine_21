public class UsersArrayList implements UserList {

    private static final int    SIZE = 10;

    private int                 _userCount = 0;
    private User[]              _list = new User[SIZE];

    public int          getSize() {
        return (_list.length);
    }

    private boolean     _isUserExist(Integer id) {
        for (int i = 0; i < _userCount; i++) {
            if (id == _list[i].getIdentifier()) {
                return (true);
            }
        }
        return (false);
    }

    @Override
    public void         addUser(User newUser) {
        if (_isUserExist(newUser.getIdentifier())) {
            return ;
        }
        if (_userCount == _list.length) {
            User[]  temp = new User[_list.length * 2];
            for (int i = 0; i < _list.length; i++) {
                temp[i] = _list[i];
            }
            _list = temp;
        }
        _list[_userCount] = newUser;
        _userCount++;
    }

    @Override
    public User     getUserByID(Integer id) {
        for (int i = 0; i < _userCount; i++) {
            if (id == _list[i].getIdentifier()) {
                return (_list[i]);
            }
        }
        throw (new UserNotFoundException());
    }

    @Override
    public User     getUserByIndex(int index) {
        if (index >= _userCount) {
            throw (new UserNotFoundException());
        }
        if (_isUserExist(_list[index].getIdentifier())) {
                return (_list[index]);
        }
        throw (new UserNotFoundException());
    }

    @Override
    public int      getUserCount() {
        return (_userCount);
    }

    @Override
    public void     showDetails() {
        System.out.println( "\n***** USER_LIST_DETAILS *****" + 
                            "\nUser count:\t\t" + _userCount +
                            "\nList size:\t\t" + _list.length +
                            "\n*****************************");
    }
}

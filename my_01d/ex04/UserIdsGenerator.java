public class UserIdsGenerator {

    private static UserIdsGenerator _instance;
    private Integer                 _lastGeneratedID;

    private                         UserIdsGenerator() {
        _lastGeneratedID = 0;
    }

    public static UserIdsGenerator  getInstance() {
        if(_instance == null) {
            _instance = new UserIdsGenerator();
        }
        return (_instance);	
    }

    public Integer                  generateId() {
        _lastGeneratedID += 1;
        return (_lastGeneratedID);
    }
}

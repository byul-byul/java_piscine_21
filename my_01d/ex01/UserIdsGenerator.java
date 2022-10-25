public class UserIdsGenerator {

    private static UserIdsGenerator instance;
    private Integer                 lastGeneratedID;

    private                         UserIdsGenerator() {
        lastGeneratedID = 0;
    }

    public static UserIdsGenerator  getInstance() {
        if(instance == null) {
            instance = new UserIdsGenerator();
        }
        return (instance);	
    }

    public Integer                  generateId() {
        lastGeneratedID += 1;
        return (lastGeneratedID);
    }
}

package challengedeck.exceptions

class CantLeaveBattlefieldException extends RuntimeException {

    @Override
    String getMessage() {
        return "Can't leave if exist a Horror creature on the battlefield"
    }
    static constraints = {
    }
}

package challengedeck.exceptions

class PlayerLostGameException extends RuntimeException {

    @Override
    String getMessage() {
        return "Player lost game"
    }
    static constraints = {
    }
}

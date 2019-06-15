package challengedeck.exceptions

class CthuluLostGameException extends RuntimeException {

    @Override
    String getMessage() {
        return "Cthulhu lost game"
    }
    static constraints = {
    }
}

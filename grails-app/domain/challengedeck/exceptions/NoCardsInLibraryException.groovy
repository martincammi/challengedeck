package challengedeck.exceptions

class NoCardsInLibraryException extends RuntimeException {

    @Override
    String getMessage() {
        return "No cards In Library"
    }
    static constraints = {
    }
}

package challengedeck

import junit.framework.TestCase

abstract class SingleGame extends TestCase {

    CthulhuGame getSingleGame(){
        return CthulhuGame.getSingleGameInstance()
    }
}

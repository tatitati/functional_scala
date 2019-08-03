package test.fpModules

import java.awt.Color

// we need to extends AnimalWithTail because the rest of services are forcing to us (they are self-types)
object IrishSetter extends AnimalWithTail(Color.red) with DogTailServices with DogMouthServices {

}

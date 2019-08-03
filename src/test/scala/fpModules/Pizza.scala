package fpModules

sealed trait Topping
case object Cheese extends Topping
case object Pepperoni extends Topping
case object Sausage extends Topping
case object Mushrooms extends Topping
case object Onions extends Topping


sealed trait CrustSize
case object SmallCrustSize extends CrustSize
case object MediumCrustSize extends CrustSize
case object LargeCrustSize extends CrustSize


sealed trait CrustType
case object RegularCrustType extends CrustType
case object ThinCrustType extends CrustType
case object ThickCrustType extends CrustType


case class Pizza (
                crustSize: CrustSize,
                crustType: CrustType,
                toppings: Seq[Topping]
)

case class Order(
                pizzas: Seq[Pizza],
                customer: Customer

                )

case class Customer(
                   name: String,
                   phone: String,
                   address: Address
                   )

case class Address(
                  street1: String,
                  street2: String,
                  city: String,
                  state: String,
                  zipCode: String
                  )


// time to design our behaviours
// any no-abstract class extending this trait has to implement this functions
  trait PizzaServiceInterface {
    def addToping(p: Pizza, t: Topping): Pizza

    def removeTopping(p: Pizza, t: Topping): Pizza

    def removeAllTopping(p: Pizza): Pizza

    def updateCrustSize(p: Pizza, cs: CrustSize): Pizza

    def updateCrustType(p: Pizza, ct: CrustType): Pizza

    def calculatePizzaPrice(
                             p: Pizza,
                             toppingsPrices: Map[Topping, Money],
                             crustSizePrices: Map[CrustSize, Money],
                             crustTypePrices: Map[CrustType, Money]
                           ): Money
  }

  // Create an specific pizza
//  val calzone = Pizza(
//    MediumCrustSize,
//    RegularCrustType,
//    Seq(Cheese)
//  )

  // define concret implementations
  trait PizzaService extends PizzaServiceInterface {
    def addTopping(p: Pizza, t: Topping): Pizza = {
      val newToppings = p.toppings :+ t
      p.copy(toppings = newToppings)
    }

    def removeTopping(p: Pizza, t: Topping): Pizza = {
      val newToppings = ListUtils.dropFirstMatch(p.toppings, t)
      p.copy(toppings = newToppings)
    }

    def removeAllToppings(p: Pizza): Pizza = {
      val newToppings = Seq[Topping]()
      p.copy(toppings = newToppings)
    }

    def updateCrustSize(p: Pizza, cs: CrustSize): Pizza = {
      p.copy(crustSize = cs)
    }

    def updateCrustType(p: Pizza, ct: CrustType): Pizza = {
      p.copy(crustType = ct)
    }

    def calculatePizzaPrice(
                           p: Pizza,
                           toppingsPrices: Map[Topping, Money],
                           crustSizePrices: Map[CrustSize, Money],
                           crustTypePrices: Map[CrustType, Money]
                           ): Money = {
      val base = BigDecimal(10)
      val numToppings = p.toppings.size
      val price = base + 1.00 * numToppings
      price
  }
}



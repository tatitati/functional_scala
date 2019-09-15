package domain.order

sealed trait OrderMedium
case object DEBITCARD extends OrderMedium
case object CREDITCARD extends OrderMedium
case object PAYPAL extends OrderMedium

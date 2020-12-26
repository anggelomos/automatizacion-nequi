
#Proyecto: Pagina web de nopCommerce
#Cliente: nopCommerce
#Elaborado por: Sofka Technologies
#Email: angelo.mosquera@sofka.com.co
#Sitio web: www.sofka.com.co

Feature: Compra de producto

  Scenario Outline: 001 - Cantidad minima del producto Apple MacBook
    Given que entre a la pagina del producto Apple MacBook Pro 13-inch
    When compro "<cantidad>" computadores
    Then aparecera el mensaje "<mensaje>"

    Examples:
      | cantidad       | mensaje                                           |
      | negative       | Quantity should be positive                       |
      | 0              | Quantity should be positive                       |
      | 1              | The minimum quantity allowed for purchase is 2    |
      | 2              | The product has been added to your shopping cart  |
      | greaterThanTwo | The product has been added to your shopping cart  |
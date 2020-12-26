
#Proyecto: Pagina web de nopCommerce
#Cliente: nopCommerce
#Elaborado por: Sofka Technologies
#Email: angelo.mosquera@sofka.com.co
#Sitio web: www.sofka.com.co

Feature: Menu de login

  Scenario: 001 - Verificacion del boton de login
    When ingreso a la pagina principal
    Then podre visualizar un boton que me lleve al menu de login

  Scenario: 002 - Ingresar datos invalidos
    Given que entre al menu de login
    When ingreso datos invalidos en cada campo
    Then aparecera un mensaje de error
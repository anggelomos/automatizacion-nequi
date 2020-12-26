
#Proyecto: Pagina web de nopCommerce
#Cliente: nopCommerce
#Elaborado por: Sofka Technologies
#Email: angelo.mosquera@sofka.com.co
#Sitio web: www.sofka.com.co

Feature: Menu de registro

  Scenario: 001 - Verificacion del boton de registro
    When entro a la pagina principal
    Then podre visualizar un boton que me lleve al menu de registro

  Scenario: 002 - Ingresar datos validos
    Given que entre al menu de registro
    When ingreso datos validos en cada campo
    And hago click en el boton registrar
    Then aparecera un mensaje de exito
package com.aluracursos.convertidorApp.modelos;

import com.aluracursos.convertidorApp.calculos.ConsultarMoneda;

import javax.swing.*;

public class Menu {

    public static int menuPpal(){
        Object[] possibleValues = { "Conversor de Moneda", "Conversor de Temperatura"};
        Object selectedOption = JOptionPane.showInputDialog(null,
                "Seleccione una opción de conversión", "Menú",
                JOptionPane.PLAIN_MESSAGE,null,
                possibleValues, possibleValues[0]);
        if (selectedOption == null) {
            return 0; // Usuario canceló
        } else {
            return selectedOption.equals("Conversor de Moneda") ? 1 : 2;
        }
    }

    public static int menu2() {

        Object[] options = {"Pesos Chilenos a Dólar", "Pesos Chilenos a Euro", "Pesos Chilenos a Pesos Colombianos",
                "Pesos Chilenos a Pesos Argentinos", "Pesos Chilenos a Bolivares"};
        Object selectedOption = JOptionPane.showInputDialog(null,
                "Elije la moneda a la que desees convertir tu dinero:",
                "Monedas",
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);

        if (selectedOption == null) {
            //return 0; // Usuario canceló
            JOptionPane.showMessageDialog(null, "Programa Finalizado");
            return 0;
        }

        String fromCurrency = "CLP";
        String toCurrency = "";

        switch (selectedOption.toString()) {
            case "Pesos Chilenos a Dólar":
                toCurrency = "USD";
                break;
            case "Pesos Chilenos a Euro":
                toCurrency = "EUR";
                break;
            case "Pesos Chilenos a Pesos Colombianos":
                toCurrency = "COP";
                break;
            case "Pesos Chilenos a Pesos Argentinos":
                toCurrency = "ARS";
                break;
            case "Pesos Chilenos a Bolivares":
                toCurrency = "VES";
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción no válida");
                return 0;
        }

        return convertidorMonedas(fromCurrency, toCurrency);
        }

    public static int convertidorMonedas(String fromCurrency, String toCurrency) {
        double amount = 0;
        while (true) {
            String amountStr = JOptionPane.showInputDialog("Ingrese la cantidad a convertir:");
            if (amountStr == null) {
                JOptionPane.showMessageDialog(null, "Programa Finalizado");
                System.exit(0);
            }

            // Validar si la entrada es un número
            try {
                amount = Double.parseDouble(amountStr);
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido.");
            }
        }

        ConsultarMoneda converter = new ConsultarMoneda();
        try {
            double result = converter.tipoCambio(fromCurrency, toCurrency, amount);
            JOptionPane.showMessageDialog(null, amount + " " + fromCurrency + " equivale a " + result + " " + toCurrency);

            // Preguntar al usuario si desea continuar
            int eleccion = JOptionPane.showConfirmDialog(null, "¿Desea realizar otra conversión?", "Continuar",
                    JOptionPane.YES_NO_CANCEL_OPTION);
            if (eleccion == JOptionPane.YES_OPTION){
                menu2();
            } else {
                JOptionPane.showMessageDialog(null, "Programa Terminado");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al convertir la moneda: " + e.getMessage());
        }
        return 0;
    }

    public static void menuTemperatura(){
        String mensaje = "Sea Bienvenid@ al convertidor de temperatura:\n\n" +
                "1. - De Celsius a Fahrenheit\n" +
                "2. - De Fahrenheit a Celsius\n" +
                "3. - De Fahrenheit a Kelvin\n" +
                "4. - De Kelvin a Fahrenheit\n" +
                "5. - De Celsius a Kelvin\n" +
                "6. - De Kelvin a Celsius\n" +
                "7. - Salir.";

        String opcionStr = JOptionPane.showInputDialog(null, mensaje, "Grados de Temperatura", JOptionPane.PLAIN_MESSAGE);
        if (opcionStr == null || opcionStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Gracias por usar el convertidor de temperatura.", "Salir", JOptionPane.INFORMATION_MESSAGE);
            return ;
        }

        int opcion = Integer.parseInt(opcionStr);
        if (opcion < 1 || opcion > 7) {
            JOptionPane.showMessageDialog(null, "Opción no válida. Por favor, elija una opción válida.", "Error", JOptionPane.ERROR_MESSAGE);
            menuTemperatura(); // Vuelve a mostrar el menú
            return ;
        }

        if (opcion != 7) {
            convertirTemperatura(opcion);
        } else {
            JOptionPane.showMessageDialog(null, "Gracias por usar el convertidor de temperatura.", "Salir", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void convertirTemperatura(int opcion){
        double valor = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el valor a convertir:"));

        switch (opcion) {
            case 1:
                convertirCelsiusAFahrenheit(valor, "Celsius", "Fahrenheit", 0.55556, 32);
                break;
            case 2:
                convertirFahrenheitACelsius(valor, "Fahrenheit", "Celsius", 0.55556, 32);
                break;
            case 3:
                convertirFahrenheitAKelvin(valor, "Fahrenheit", "Kelvin", 0.55556, 273.15 , 32);
                break;
            case 4:
                convertirKelvinAFahrenheit(valor, "Kelvin", "Fahrenheit", 1.8, 273.15, 32);
                break;
            case 5:
                convertirCelsiusAKelvin(valor, "Celsius", "Kelvin", 273.15);
                break;
            case 6:
                convertirKelvinACelsius(valor, "Kelvin", "Celsius", 273.15);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción no válida. Por favor, elija una opción válida.", "Error", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }

    public static void convertirCelsiusAFahrenheit(double valor, String temp1, String temp2, double factor, double valor2) {
        double resultado = (valor * factor) + valor2;
        JOptionPane.showMessageDialog(null, valor + " " + temp1 + " son " + resultado + " " + temp2, "Resultado", JOptionPane.INFORMATION_MESSAGE);
        preguntarSiContinua();
    }

    public static void convertirFahrenheitACelsius(double valor, String temp1, String temp2, double factor, double valor2) {
        double resultado = (valor - valor2) * factor;
        JOptionPane.showMessageDialog(null, valor + " " + temp1 + " son " + resultado + " " + temp2, "Resultado", JOptionPane.INFORMATION_MESSAGE);
        preguntarSiContinua();
    }

    public static void convertirFahrenheitAKelvin(double valor, String temp1, String temp2, double factor, double valor2, double valor3) {
        double resultado = Math.round(((valor - valor3) * factor + valor2) * 1000.0) / 1000.0;
        JOptionPane.showMessageDialog(null, valor + " " + temp1 + " son " + resultado + " " + temp2, "Resultado", JOptionPane.INFORMATION_MESSAGE);
        preguntarSiContinua();
    }

    public static void convertirKelvinAFahrenheit(double valor, String temp1, String temp2, double factor, double valor2, double valor3) {
        double resultado = Math.round(((valor - valor2) * factor + valor3) * 1000.0) / 1000.0;
        JOptionPane.showMessageDialog(null, valor + " " + temp1 + " son " + resultado + " " + temp2, "Resultado", JOptionPane.INFORMATION_MESSAGE);
        preguntarSiContinua();
    }

    public static void convertirCelsiusAKelvin(double valor, String temp1, String temp2, double factor) {
        double resultado = Math.round((valor + factor) * 100.0) / 100.0;
        JOptionPane.showMessageDialog(null, valor + " " + temp1 + " son " + resultado + " " + temp2, "Resultado", JOptionPane.INFORMATION_MESSAGE);
        preguntarSiContinua();
    }

    public static void convertirKelvinACelsius(double valor, String temp1, String temp2, double factor) {
        double resultado = Math.round((valor - factor) * 100.0) / 100.0;
        JOptionPane.showMessageDialog(null, valor + " " + temp1 + " son " + resultado + " " + temp2, "Resultado", JOptionPane.INFORMATION_MESSAGE);
        preguntarSiContinua();
    }

    public static void preguntarSiContinua(){
        int eleccion = JOptionPane.showConfirmDialog(null, "¿Desea realizar otra conversión?", "Continuar",
                JOptionPane.YES_NO_CANCEL_OPTION);
        if (eleccion == JOptionPane.YES_OPTION){
            menuTemperatura();
        } else {
            JOptionPane.showMessageDialog(null, "Programa Terminado");
        }
    }
}

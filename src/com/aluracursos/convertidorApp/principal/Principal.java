package com.aluracursos.convertidorApp.principal;

import javax.swing.*;

import static com.aluracursos.convertidorApp.modelos.Menu.*;

public class Principal {
    public static void main(String[] args) {

        int option = menuPpal();
        if (option == 0) {
            JOptionPane.showMessageDialog(null, "Programa Finalizado");
        } else {
            switch (option) {
                case 1:
                    menu2();
                    break;
                case 2:
                    menuTemperatura();
                    break;
            }
        }
    }
}

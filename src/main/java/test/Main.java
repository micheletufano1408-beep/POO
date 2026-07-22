package test;

import controller.Controller;
import gui.Login;

public class Main {
    public static void main(String[] args) {

        Controller controllerDiAvvio = new Controller();

        new Login(controllerDiAvvio);

    }
}
package Main;

import Controller.Controller;
import Model.Model;
import View.View;

import javax.swing.*;

public class Main {
    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Model model = new Model();
                Controller controller = new Controller();
                controller.setModel(model);
                View view = new View();
                view.setController(controller);
                view.show();
            }
        });
    }
}
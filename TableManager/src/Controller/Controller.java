package Controller;

import Controller.DialogAction.AddDialogAction;
import Controller.DialogAction.EraseDialogAction;
import Controller.DialogAction.SearchDialogAction;
import Model.*;

import java.util.ArrayList;

public class Controller {
    private Model model;

    public void setModel(Model model) {
        this.model = model;
    }

    public ArrayList runAddAction(ArrayList<String> arrayList) {
        AddDialogAction addDialogAction = new AddDialogAction();
        addDialogAction.setModel(model);
        ArrayList newList = addDialogAction.run(arrayList);

        return newList;
    }

    public ArrayList runSearchAction(ArrayList<String> arrayList, int num) {
        SearchDialogAction searchDialogAction = new SearchDialogAction();
        searchDialogAction.setModel(model);
        ArrayList newList = searchDialogAction.run(arrayList, num);

        return newList;
    }

    public ArrayList runEraseAction(ArrayList<String> arrayList, int num) {
        EraseDialogAction eraseDialogAction = new EraseDialogAction();
        eraseDialogAction.setModel(model);
        ArrayList newList = eraseDialogAction.run(arrayList, num);

        if (!((newList.get(0).equals("1") || newList.get(0).equals("2") || newList.get(0).equals("3")) && newList.size() == 1)) {
            if (model.getPersons().size() == 0) {
                ArrayList result = new ArrayList();
                result.add("-1");
                result.add(newList.size());

                return result;
            }

            else {
                ArrayList result = model.getPersons();
                result.add(newList.size());

                return result;
            }
        }

        return newList;
    }

    public void clearModel() {
        model.clearModel();
    }

    public ArrayList<Person> getPersons() {
        return model.getPersons();
    }
}
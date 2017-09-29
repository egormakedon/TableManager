package Model;

import java.util.ArrayList;

public class Model {
    private ArrayList<Person> persons = new ArrayList<>();

    public void addData(ArrayList<String> arrayList) {
        arrayList = refactorData(arrayList);

        Person person = new Person();
        person.setData(arrayList);

        persons.add(person);
    }

    private ArrayList<String> refactorData(ArrayList<String> arrayList) {
        ArrayList<String> newList = new ArrayList<>();

        for (String obj : arrayList) {
            if (!checkString(obj)) {
                String string = obj;

                string = string.toUpperCase();
                string = string.charAt(0) + string.substring(1).toLowerCase();

                newList.add(string);
            }

            else {
                newList.add(obj);
            }
        }

        return newList;
    }

    private boolean checkString(String string) {
        try {
            Integer.parseInt(string);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void eraseData(Object obj) {
        persons.remove(obj);
    }

    public void clearModel() {
        persons.clear();
    }

    public ArrayList<Person> getPersons() { return persons;}
}
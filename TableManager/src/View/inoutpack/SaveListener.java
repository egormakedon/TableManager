package View.inoutpack;

import Controller.Controller;
import Model.Person;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class SaveListener implements ActionListener {
    private Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser("D:");

        FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter("xml", "xml");
        fileChooser.setFileFilter(fileNameExtensionFilter);

        int ret = fileChooser.showDialog(null, "save file");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = factory.newDocumentBuilder();

                Document doc = documentBuilder.newDocument();
                Element rootElement = doc.createElement("students");
                doc.appendChild(rootElement);

                for (Person person : controller.getPersons()) {
                    Element student = doc.createElement("student");
                    rootElement.appendChild(student);

                    Element surname = doc.createElement("surname");
                    surname.appendChild(doc.createTextNode(person.getSurname()));
                    student.appendChild(surname);

                    Element name = doc.createElement("name");
                    name.appendChild(doc.createTextNode(person.getName()));
                    student.appendChild(name);

                    Element fathername = doc.createElement("fathername");
                    fathername.appendChild(doc.createTextNode(person.getFathername()));
                    student.appendChild(fathername);

                    Element country = doc.createElement("country");
                    country.appendChild(doc.createTextNode(person.getCountry()));
                    student.appendChild(country);

                    Element region = doc.createElement("region");
                    region.appendChild(doc.createTextNode(person.getRegion()));
                    student.appendChild(region);

                    Element city = doc.createElement("city");
                    city.appendChild(doc.createTextNode(person.getCity()));
                    student.appendChild(city);

                    Element street = doc.createElement("street");
                    street.appendChild(doc.createTextNode(person.getStreet()));
                    student.appendChild(street);

                    Element house = doc.createElement("house");
                    house.appendChild(doc.createTextNode(Integer.toString(person.getHouse())));
                    student.appendChild(house);

                    Element housing = doc.createElement("housing");
                    housing.appendChild(doc.createTextNode(Integer.toString(person.getHousing())));
                    student.appendChild(housing);

                    Element apartment = doc.createElement("apartment");
                    apartment.appendChild(doc.createTextNode(Integer.toString(person.getApartment())));
                    student.appendChild(apartment);

                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    DOMSource domSource = new DOMSource(doc);
                    StreamResult streamResult = new StreamResult(new File(file.getPath() + ".xml"));

                    transformer.transform(domSource, streamResult);
                }

            } catch (ParserConfigurationException e1) {
                e1.printStackTrace();
            } catch (TransformerConfigurationException e1) {
                e1.printStackTrace();
            } catch (TransformerException e1) {
                e1.printStackTrace();
            }
        }
    }
}
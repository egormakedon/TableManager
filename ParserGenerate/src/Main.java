import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

class Main {
    public static void main(String[] args) {
        ArrayList surname = new ArrayList();
        ArrayList name = new ArrayList();
        ArrayList fathername = new ArrayList();
        ArrayList country = new ArrayList();
        ArrayList region = new ArrayList();
        ArrayList city = new ArrayList();
        ArrayList street = new ArrayList();
        ArrayList house = new ArrayList();
        ArrayList housing = new ArrayList();
        ArrayList apartment = new ArrayList();

        surname.add("Македон");
        surname.add("Трушков");
        surname.add("Якимцов");
        surname.add("Гаврилюк");
        surname.add("Белявский");
        surname.add("Дубовский");
        surname.add("Дудков");

        name.add("Егор");
        name.add("Артем");
        name.add("Иван");
        name.add("Макс");
        name.add("Влад");
        name.add("Влад");
        name.add("Антон");

        fathername.add("Андреевич");
        fathername.add("Александрович");
        fathername.add("Владимирович");
        fathername.add("Павлович");
        fathername.add("Иванович");
        fathername.add("Сергеевич");
        fathername.add("Робертович");

        country.add("Беларусь");
        country.add("Россия");
        country.add("Сша");
        country.add("Канада");
        country.add("Испания");
        country.add("Франция");
        country.add("Германия");

        region.add("Минская");
        region.add("Витебская");
        region.add("Гродненская");
        region.add("Брестская");
        region.add("Могилевская");
        region.add("Гомельская");
        region.add("Минская");

        city.add("Лида");
        city.add("Минск");
        city.add("Москва");
        city.add("Париж");
        city.add("Барселона");
        city.add("Мадрид");
        city.add("Брест");

        street.add("Космонавтов");
        street.add("Комсомольская");
        street.add("Славинского");
        street.add("Ленинская");
        street.add("Космонавтов");
        street.add("Гастелло");
        street.add("Пушкинская");

        house.add("121");
        house.add("228");
        house.add("1337");
        house.add("44");
        house.add("903");
        house.add("52");
        house.add("555");

        housing.add("12");
        housing.add("32");
        housing.add("37");
        housing.add("40");
        housing.add("52");
        housing.add("6");
        housing.add("71");

        apartment.add("36");
        apartment.add("770");
        apartment.add("223");
        apartment.add("1");
        apartment.add("95");
        apartment.add("108");
        apartment.add("1230");

        JFrame frame = new JFrame("ParserGenerate");

        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        JTextField textField = new JTextField(7);
        textField.setText("200");

        JButton generate = new JButton("generate");
        JButton save = new JButton("save");

        frame.add(textField);
        frame.add(generate);

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        frame.add(save, c);

        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);

        Model model = new Model();

        generate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.clearModel();

                for (int i = 0; i < Integer.parseInt(textField.getText()); i++) {
                    ArrayList arrayList = new ArrayList();

                    for (int j = 0; j < 10; j++) {
                        int rand = (int) Math.round(0 - 0.5 + Math.random() * 7);

                        if (j == 0) arrayList.add(surname.get(rand));
                        if (j == 1) arrayList.add(name.get(rand));
                        if (j == 2) arrayList.add(fathername.get(rand));
                        if (j == 3) arrayList.add(country.get(rand));
                        if (j == 4) arrayList.add(region.get(rand));
                        if (j == 5) arrayList.add(city.get(rand));
                        if (j == 6) arrayList.add(street.get(rand));
                        if (j == 7) arrayList.add(house.get(rand));
                        if (j == 8) arrayList.add(housing.get(rand));
                        if (j == 9) arrayList.add(apartment.get(rand));
                    }

                    model.addData(arrayList.get(0).toString(), arrayList.get(1).toString(),
                            arrayList.get(2).toString(), arrayList.get(3).toString(), arrayList.get(4).toString(),
                            arrayList.get(5).toString(), arrayList.get(6).toString(), arrayList.get(7).toString(),
                            arrayList.get(8).toString(), arrayList.get(9).toString());
                }
            }
        });

        save.addActionListener(new ActionListener() {
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

                        for (int i = 0; i < model.getListNumber(); i++) {
                            Element student = doc.createElement("student");
                            rootElement.appendChild(student);

                            Element surname = doc.createElement("surname");
                            surname.appendChild(doc.createTextNode(model.getSurnameList().get(i).toString()));
                            student.appendChild(surname);

                            Element name = doc.createElement("name");
                            name.appendChild(doc.createTextNode(model.getNameList().get(i).toString()));
                            student.appendChild(name);

                            Element fathername = doc.createElement("fathername");
                            fathername.appendChild(doc.createTextNode(model.getFathernameList().get(i).toString()));
                            student.appendChild(fathername);

                            Element country = doc.createElement("country");
                            country.appendChild(doc.createTextNode(model.getCountryList().get(i).toString()));
                            student.appendChild(country);

                            Element region = doc.createElement("region");
                            region.appendChild(doc.createTextNode(model.getRegionList().get(i).toString()));
                            student.appendChild(region);

                            Element city = doc.createElement("city");
                            city.appendChild(doc.createTextNode(model.getCityList().get(i).toString()));
                            student.appendChild(city);

                            Element street = doc.createElement("street");
                            street.appendChild(doc.createTextNode(model.getStreetList().get(i).toString()));
                            student.appendChild(street);

                            Element house = doc.createElement("house");
                            house.appendChild(doc.createTextNode(model.getHouseList().get(i).toString()));
                            student.appendChild(house);

                            Element housing = doc.createElement("housing");
                            housing.appendChild(doc.createTextNode(model.getHousingList().get(i).toString()));
                            student.appendChild(housing);

                            Element apartment = doc.createElement("apartment");
                            apartment.appendChild(doc.createTextNode(model.getApartmentList().get(i).toString()));
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
        });
    }
}
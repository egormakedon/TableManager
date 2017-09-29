package View.inoutpack;

import Controller.Controller;
import View.*;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class OpenListener implements ActionListener {
    private Controller controller;
    private JFrame frame;
    private Table table;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser("D:");

        FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter("xml", "xml");
        fileChooser.setFileFilter(fileNameExtensionFilter);

        int ret = fileChooser.showDialog(null, "open file");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try {
                SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
                SAXParser saxParser = saxParserFactory.newSAXParser();

                controller.clearModel();

                DefaultHandler handler = new DefaultHandler() {
                    String string;
                    ArrayList<String> arrayList = new ArrayList();

                    @Override
                    public void endDocument() throws SAXException {
                        table.setTablePanel(controller.getPersons());
                    }

                    @Override
                    public void startElement(String namespace, String localName, String qName, Attributes attribute) throws SAXException {
                        string = qName;
                    }

                    @Override
                    public void endElement(String namespace, String localName, String qName) throws SAXException {
                        if (qName.equals("student")) {
                            controller.runAddAction(arrayList);
                            arrayList.clear();
                        }
                    }

                    @Override
                    public void characters(char[] ch, int start, int length) throws SAXException {
                        if (!string.equals("student") && !string.equals("students")) {
                            arrayList.add(new String(ch, start, length));
                            string = "students";
                        }
                    }
                };

                saxParser.parse(new File(file.getPath()), handler);

            } catch (ParserConfigurationException e1) {
                e1.printStackTrace();
            } catch (org.xml.sax.SAXException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();

            }
        }
    }
}
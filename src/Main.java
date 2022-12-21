import model.Employee;
import model.Root;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.print.Doc;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Root root = new Root();
        Document doc;
        try {
            doc = buildDoc();
        } catch (Exception e){
            System.out.println("Error " + e.toString());
            return;
        }



        Node rootNode = doc.getFirstChild();
        //System.out.println("AAA " + rootNode.getNodeName());
        String mainName = null;
        Node officeNode = null;
        NodeList rootChilds = rootNode.getChildNodes();
        for (int i = 0; i < rootChilds.getLength(); i++) {
            if (rootChilds.item(i).getNodeType() != Node.ELEMENT_NODE)
                continue;

            //System.out.println("BBB " + rootChilds.item(i).getNodeName());
            switch (rootChilds.item(i).getNodeName()) {
                case "name": {
                    mainName = rootChilds.item(i).getTextContent();
                    System.out.println("mainName " + mainName);
                }
                case "offices": {
                    officeNode = rootChilds.item(i);
                }
            }

        }

        root.setName_company(mainName);
        if(officeNode == null){
            return;
        }
        NodeList offices = officeNode.getChildNodes();
        for(int i = 0;i<offices.getLength();i++)
            System.out.println("offices: " + offices.item(i).getNodeName());
        NodeList office = null;
        NodeList employees = null;
        NodeList employeesMas = null;
        NodeList element = null;
        List<Employee> employeeList = new ArrayList<>();
        for(int i = 0; i<offices.getLength(); i++){
            if(offices.item(i).getNodeType() != Node.ELEMENT_NODE){
                continue;
            }
            if(!offices.item(i).getNodeName().equals("office")) {
                continue;
            }
            office = offices.item(i).getChildNodes();
            String name = null;
            String job = null;
            for(int k=0;k<office.getLength();k++){
                System.out.println("office: " + office.item(k).getNodeName());
                if(office.item(k).getNodeName()=="employees") {
                    employees = office.item(k).getChildNodes();
                    for (int y = 0; y < employees.getLength(); y++) {
                        System.out.println("employees: " + employees.item(y).getNodeName());
                        if(employees.item(y).getNodeName()=="employee"){
                            element = employees.item(y).getChildNodes();
                            for(int j=0; j < element.getLength(); j++){
                                if(element.item(j).getNodeType() != Node.ELEMENT_NODE){
                                    continue;
                                }
                                switch (element.item(j).getNodeName()) {
                                    case "name": {
                                        name = element.item(j).getTextContent();
                                    } case "job": {
                                        job = element.item(j).getTextContent();
                                    }
                                }
                                Employee employee = new Employee(name, job);
                                employeeList.add(employee);
                            }
                        }
                    }
                }
            }
         }
            root.setFlor(employeeList);
            System.out.println("Root " + root.toString());

    }


    private static Document buildDoc() throws Exception{
        File file = new File("office.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        return dbf.newDocumentBuilder().parse(file);
    }
}



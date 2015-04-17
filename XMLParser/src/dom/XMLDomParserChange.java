package dom;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Hugh on 2015/3/2 0002.
 */
public class XMLDomParserChange {
    private DocumentBuilderFactory factory;
    private DocumentBuilder builder;
    private Document document;
    private StringBuilder xmlContent;
    private ArrayList<Integer> nodeNum;
    private Person nowPerson;
    private ArrayList<Person> persons;

    public XMLDomParserChange(String filePath) {
        init();
        readFile(filePath);
        ergodicXML(getRootElement(),1);
        for (int i = 0; i < persons.size()-1; i++) {
            xmlContent.append(persons.get(i).getInfo());
            xmlContent.append("\n");
        }
    }
    private void init(){
        persons = new ArrayList<Person>();
        factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        xmlContent = new StringBuilder();
        xmlContent.append("XML内容：\n");
        xmlContent.append("======================\n");
        nodeNum = new ArrayList<Integer>();
    }
    private void readFile(String filePath){
        try {
            document = builder.parse(filePath);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("文件不存在！");
        }
    }
    private Element getRootElement(){
        return document.getDocumentElement();
    }
    /*
    root为根节点，level表示该节点所处层数，比如最外层为第一层
     */
    private void ergodicXML(Element root,int level){
        String tagName = root.getTagName();
        if (level==2&&(!tagName.equals("people"))){ /*忽略其他两张表的内容*/
            return;
        }

        if (tagName.equals("age")){
            nowPerson.setAge(Integer.parseInt(root.getTextContent()));
        }
        /*标签名*/
        if (level>=3)
            nowPerson.addInfo(root.getTagName().toUpperCase());
        /*属性*/
        NamedNodeMap attributes = root.getAttributes();
        if (attributes.getLength() != 0){
            for (int i = 0; i < attributes.getLength(); i++) {
                Node attribute = attributes.item(i);
                String attrName = attribute.getNodeName();      /*属性名*/
                String attrValue = attribute.getNodeValue();    /*属性值*/


                if (attrName.equals("income")){                 /*收入属性的设置*/
                    nowPerson.setIncome(Double.parseDouble(attrValue));
                }
                if (level>=3){
                    if (i==0){
                        nowPerson.addInfo("\tAttr( ");
                    }
                    nowPerson.addInfo(attrName + ":" + attrValue);
                    if (i==attributes.getLength()-1){
                        nowPerson.addInfo(" )");
                    }else {
                        nowPerson.addInfo(" , ");
                    }
                }
            }
        }

          /*子节点、文本*/
        NodeList children = root.getChildNodes();
        int length = children.getLength();

        if (length==0 || (length==1&&root.getTextContent().trim().equals(""))){
            /*空节点*/
            if (level>=3)
                nowPerson.addInfo("\t:\t\"节点值为空\"");
        } else if (children.getLength()==1){
            /*节点值*/
            if (level>=3){
                nowPerson.addInfo("\t:\t");
                nowPerson.addInfo(root.getTextContent().trim());
            }
        } else {
            /*子节点*/
            for (int i = 0; i < length; i++) {
                if (children.item(i) instanceof Element){
                    Element child = (Element)children.item(i);
                    if (tagName.equals("people")){
                        if (nowPerson!=null){
                            if (nowPerson.getIncome()>35000&&nowPerson.getAge()>20){
                                persons.add(nowPerson);
                            }
                        }
                        nowPerson = new Person();
                    }
                    ergodicXML(child,level+1);

                }
            }
        }
    }
    public void infoToConsole(){
        System.out.println(xmlContent);
    }
    public void infoToFile(String filePath){

        File file = new File(filePath);
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(getCount());
            writer.write(xmlContent.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getCount(){
        StringBuilder count = new StringBuilder();
        count.append("节点计数：\n");
        count.append("======================\n");
        for (int i = 0; i < nodeNum.size()-1; i++) {
            count.append("\t"+(i+1)+"级节点数： \t" + nodeNum.get(i+1)+"\n");
        }
        count.append("======================\n\n\n\n");
        return count.toString();
    }

    public static void main(String[] args){
        XMLDomParserChange xmlDomParser = new XMLDomParserChange("./xmark0.01.xml");
        xmlDomParser.infoToConsole();
        System.out.println(xmlDomParser.getCount());
        xmlDomParser.infoToFile("./resultFromDom.txt");
    }
}

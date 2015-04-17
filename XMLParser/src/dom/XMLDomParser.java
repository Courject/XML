package dom;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by Hugh on 2015/3/2 0002.
 */
public class XMLDomParser {
    private DocumentBuilderFactory factory;
    private DocumentBuilder builder;
    private Document document;
    private StringBuilder xmlContent;
    private ArrayList<Integer> nodeNum;

    public XMLDomParser(String filePath) {
        init();
        readFile(filePath);
        ergodicXML(getRootElement(),1);
    }
    private void init(){
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
        /*层级计数*/
        if (nodeNum.size()<level){
            nodeNum.add(1);
        } else {
            nodeNum.set(level-1,nodeNum.get(level-1)+1);
        }
        /*标签名*/
        addIndents(level);
        xmlContent.append(root.getTagName().toUpperCase());
        /*属性*/
        NamedNodeMap attributes = root.getAttributes();
        if (attributes.getLength() != 0){
            for (int i = 0; i < attributes.getLength(); i++) {
                Node attribute = attributes.item(i);
                String attrName = attribute.getNodeName();      /*属性名*/
                String attrValue = attribute.getNodeValue();    /*属性值*/

                if (i==0){
                    xmlContent.append("\tAttr( ");
                }
                xmlContent.append(attrName + ":" + attrValue);
                if (i==attributes.getLength()-1){
                    xmlContent.append(" )");
                }else {
                    xmlContent.append(" , ");
                }
            }
        }

        /*子节点、文本*/
        NodeList children = root.getChildNodes();
        int length = children.getLength();

        if (length==0 || (length==1&&root.getTextContent().trim().equals(""))){
            /*空节点*/
            xmlContent.append("\t:\t\"节点值为空\"");
        } else if (children.getLength()==1){
            /*节点值*/
            xmlContent.append("\t:\t");
            xmlContent.append(root.getTextContent().trim());
        } else {
            /*子节点*/
            for (int i = 0; i < length; i++) {
                if (children.item(i) instanceof Element){
                    Element child = (Element)children.item(i);
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
    private void addIndents(int num){
        if (num != 1){
            xmlContent.append("\n");
            for (int i = 1; i < num; i++) {
                xmlContent.append("\t");
            }
        }
    }

    public static void main(String[] args){
        XMLDomParser xmlDomParser = new XMLDomParser("./xmark0.01.xml");
        xmlDomParser.infoToConsole();
        System.out.println(xmlDomParser.getCount());
        xmlDomParser.infoToFile("./resultFromDom.txt");
    }
}

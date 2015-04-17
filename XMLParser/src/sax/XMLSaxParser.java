package sax;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Hugh on 2015/3/19 0019.
 */
public class XMLSaxParser {
    private SAXParserFactory factory;
    private SAXParser parser;
    private XMLHandler handler;
    private StringBuilder xmlContent;

    public XMLSaxParser(String filePath){
        init();
        readFile(filePath);
    }
    private void init(){
        factory = SAXParserFactory.newInstance();

        xmlContent = new StringBuilder();
        xmlContent.append("XML内容：\n");
        xmlContent.append("======================\n");

        handler = new XMLHandler(xmlContent);
        try {
            parser = factory.newSAXParser();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
    private void readFile(String filePath){
        try {
            parser.parse(filePath,handler);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void infoToConsole(){
        System.out.println(xmlContent);
    }
    public String getCount(){
        ArrayList<String> tagList = handler.getTags();
        StringBuilder count = new StringBuilder();
        count.append("XML文件中的所有标签：\n");
        count.append("==================================================\n");
        count.append("共计 " + tagList.size()+"个\n");
        count.append("==================================================\n");

        for (int i = 0; i < tagList.size(); i++) {
            count.append(String.format("%15s",tagList.get(i)));
            if (i!=tagList.size()-1){
                count.append(",");
            }
            if (i%5==4){
                count.append("\n");
            }
        }

        count.append("\n==================================================\n\n\n");
        return count.toString();
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

    public static void main(String[] args) {
        XMLSaxParser saxParser = new XMLSaxParser("./xmark0.01.xml");
        saxParser.infoToConsole();
        System.out.println(saxParser.getCount());
        saxParser.infoToFile("./resultFromSax.txt");
    }
}

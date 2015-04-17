package sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Created by Hugh on 2015/3/19 0019.
 */
public class XMLHandler extends DefaultHandler{
    private StringBuilder xmlContent;
    private int level;                      //当前节点级数
    private ArrayList<Integer> nodeNum;
    private ArrayList<String> tags;

    public XMLHandler(StringBuilder xmlContent) {
        this.xmlContent = xmlContent;
        init();
    }

    private void init(){
        level = 0;
        nodeNum = new ArrayList<Integer>();
        tags = new ArrayList<String>();
    }

    /*开始解析文档*/
    public void startDocument () throws SAXException {

    }

    /*文档解析结束*/
    public void endDocument () throws SAXException {

    }

    /*开始解析节点*/
    public void startElement (String uri, String localName, String qName, Attributes attributes) throws SAXException {
        /*层级计数*/
        level++;
        if (nodeNum.size()<level){
            nodeNum.add(1);
        } else {
            nodeNum.set(level-1,nodeNum.get(level-1)+1);
        }
        /*标签记录*/
        if (tags.indexOf(qName)==-1){
            tags.add(qName);
        }
        /*标签名*/
        addIndents();
        xmlContent.append(qName.toUpperCase());
        /*属性*/
        for (int i = 0; i < attributes.getLength(); i++) {
            String attrName = attributes.getQName(i);       /*属性名*/
            String attrValue = attributes.getValue(i);      /*属性值*/

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
    /*保存节点内容*/
    public void characters (char[] ch, int start, int length) throws SAXException {
        String content = new String(ch,start,length);
        if (!content.trim().equals("")) {
            xmlContent.append("\t:\t");
            xmlContent.append(content.trim());
        }
    }
    /*结束解析节点*/
    public void endElement (String uri, String localName, String qName) throws SAXException {
        level--;
    }
    private void addIndents(){
        int num = level;
        if (num != 1){
            xmlContent.append("\n");
            for (int i = 1; i < num; i++) {
                xmlContent.append("\t");
            }
        }
    }
    public ArrayList<String> getTags(){
        return tags;
    }
}

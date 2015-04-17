import javax.xml.xquery.XQException;

/**
 * Created by Hugh on 2015/4/16 0016.
 */
public class Main {
    public static void main (String[] args) throws XQException {
        String xQuery =
                "for $person in doc('./xml/data.xml')/site/people/person " +
                "let $name := concat(string($person/name),'--爱好丰富') " +
                "where count($person/profile/interest) > 5 " +
                "return <person id='{$person/@id}'>{" +
                        "<name>{$name}</name>," +
                        "$person/emailaddress," +
                        "$person/phone," +
                        "$person/address," +
                        "$person/homepage," +
                        "$person/creditcard," +
                        "$person/profile," +
                        "$person/watches" +
                        "}</person>";

        XQueryDemo demo = new XQueryDemo();
        demo.execute(xQuery);
        demo.resultToFile("./xml/data(changed).xml");
    }
}

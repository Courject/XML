import net.sf.saxon.xqj.SaxonXQDataSource;

import javax.xml.xquery.*;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Hugh on 2015/4/16 0016.
 */
public class XQueryDemo {
    private XQDataSource dataSource;
    private XQConnection connection;
    private XQExpression expression;
    private XQResultSequence result;
    private StringBuilder resultStr;

    public XQueryDemo() {
        init();
    }

    public void init() {
        dataSource = new SaxonXQDataSource();
        try {
            connection = dataSource.getConnection();
        } catch (XQException e) {
            e.printStackTrace();
        }
    }

    public void execute(String query) {
        try {
            expression = connection.createExpression();
            result = expression.executeQuery(query);
        } catch (XQException e) {
            e.printStackTrace();
        }
    }

    public void resultToFile(String filePath) throws XQException {
        try {
            FileWriter fw = new FileWriter(filePath);
            resultStr = new StringBuilder();

            resultStr.append("<site>\n<people>\n");
            while (result.next()){
                resultStr.append(result.getItemAsString(null)+"\n");
            }
            resultStr.append("</people>\n</site>");

            fw.write(resultStr.toString());
            fw.flush();
            fw.close();
        } catch (IOException e) {
            System.err.println("文件路径错误");
        }

    }
}

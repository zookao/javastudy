package ecnu;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.List;

public class XmlUtil {
    /**
     * 定义解析器和文档对象
     */
    public SAXReader saxReader;
    public Document document;

    public XmlUtil(String path) {
        saxReader = new SAXReader();
        try {
            document = saxReader.read(path);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public List<Element> getNodes(String name) {
        Element root = document.getRootElement();
        return root.elements(name);
    }
}
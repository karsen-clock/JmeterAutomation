import com.ctrip.automation.common.XmlToHtml;

public class TestXmlToHtml {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String xmlFileName = "E:\\GitHubLocal\\Maven-Jmeter\\target\\jmeter\\results\\test.xml";
        String xslFileName = "E:\\GitHubLocal\\Maven-Jmeter\\src\\test\\resources\\Detail Test Report.xsl";
        String htmlFileName = "E:\\GitHubLocal\\Maven-Jmeter\\target\\jmeter\\results\\test.html";
        
        XmlToHtml xmlToHtml=new XmlToHtml();
        
        xmlToHtml.Transform(xmlFileName, xslFileName, htmlFileName);

	}

}

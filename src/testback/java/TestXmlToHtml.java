import com.ctrip.automation.common.XmlToHtml;

public class TestXmlToHtml {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String xmlFileName = "E:\\GitHubLocal\\Maven-Jmeter\\target\\jmeter\\results\\Testing.xml";
        String xslFileNameSummary = "E:\\GitHubLocal\\Maven-Jmeter\\src\\test\\resources\\SummaryReport.xsl";
        String xslFileNameDetails = "E:\\GitHubLocal\\Maven-Jmeter\\src\\test\\resources\\DetailsReport.xsl";
        String htmlFileNameSummary = "E:\\GitHubLocal\\Maven-Jmeter\\target\\jmeter\\results\\SummaryReport.html";
        String htmlFileNameDetails = "E:\\GitHubLocal\\Maven-Jmeter\\target\\jmeter\\results\\DetailsReport.html";
        
        XmlToHtml xmlToHtml=new XmlToHtml();
        
        xmlToHtml.Transform(xmlFileName, xslFileNameSummary, htmlFileNameSummary);
        xmlToHtml.Transform(xmlFileName, xslFileNameDetails, htmlFileNameDetails);

	}

}

import org.testng.Reporter;
import org.testng.ReporterConfig;
import org.testng.annotations.Test;

public class Test1 {

@Test
		public void first()
		{
		assert "edddc".endsWith("c");
		Reporter.log("just a test");
		
		Reporter.log("werer", false);
		}
	}


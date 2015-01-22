package extension;

public class DefaultPageErrorOutPut implements PageErrorOutPut {  
    
	public void returnErrorPage(RawPage rawPage) {		
		rawPage.printLogInfo();
	}

}

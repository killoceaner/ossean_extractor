package extension;

public class DefaultPageErrorOutPut implements PageErrorOutPut {  
    
	public void returnErrorPage(RawPage rawPage,String message) {		
		rawPage.printLogInfo(message);
	}

}

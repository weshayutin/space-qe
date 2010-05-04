package com.redhat.rhn.harness.Space01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.redhat.qe.auto.testng.TestNGUtils;
import com.redhat.qe.auto.testopia.Assert;
import com.redhat.rhn.harness.Space01.pages.WebList;
import com.redhat.rhn.harness.Space01.pages.DocumentationSearchPage;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;

@Test(groups="tests")
public class DocumentationSearch extends SeleniumSetup {

	RhnHelper rh = new RhnHelper();
	
	@BeforeMethod(groups = { "testplan-SearchDocumentation" })
	public void test00Prep_ClearSELinux(){
		log.finer("Clearing SELinux logs");
		page_RhnCommon.JustOpen();
		task_TestPrep.command_clear_SELinux_AuditLog(IRhnBase.SERVER_HOSTNAME);
	}


	@AfterMethod(groups = { "teardown" })
	public void test_999_TestSELinux(){
		log.finer("Checking SELinux logs");
		assertTrue(task_TestPrep.command_check_SELinux_AuditLog(IRhnBase.SERVER_HOSTNAME));
	}
	
	DocumentationSearchPage page = new DocumentationSearchPage();
   
    
	@Test(groups = { "testplan-SearchDocumentation" })
	public void test_DocumentationSearch_DisplayPageHelpSearch()  {
		task_RhnBase.OpenAndLogIn();
		task_Search.DisplayDocumentationSearchPage();
    }
	
    
	@Test(groups = { "testplan-SearchDocumentation" })
	public void test_DocumentationSearch_byContentAndTitle() {
		advancedDocumentationSearch(IRhnBase.SEARCH_Documentation_ContentAndTitle, "SSL", true);
		verifyNotEmpty();
		assertTrue(task_Search.searchForResult("SSL", totalInList()));
	}
	
	
	@Test(groups = { "testplan-SearchDocumentation" })
	public void test_DocumentationSearch_byContent() {
		advancedDocumentationSearch(IRhnBase.SEARCH_Documentation_Content, "register", true);
		verifyNotEmpty();
		assertTrue(task_Search.searchForResult("register", totalInList()));
	}
	
	@Test(groups = { "testplan-SearchDocumentation" })
	public void test_DocumentationSearch_byTitle() {
		advancedDocumentationSearch(IRhnBase.SEARCH_Documentation_Title, "RHN Proxy Server", true);
		verifyNotEmpty();
		assertTrue(task_Search.searchForResult("RHN Proxy Server", totalInList()));
	}
	
	@Test(groups = { "testplan-Search Documentation" })
	public void test_DocumentationSearch_byFreeForm() {
		advancedDocumentationSearch(IRhnBase.SEARCH_Documentation_FreeForm, "url:'channel-mgmt'", true);
		verifyNotEmpty();
		assertTrue(task_Search.searchForResult("Channel Management Guide", totalInList()));
	}
	
	@Test(groups = { "testplan-SearchDocumentation" })
	public void test_DocumentationSearch_byTitle_LanguageGerman() {
		task_RhnBase.OpenAndLogIn();
		try {
			task_RhnBase.changeLanguageSettingFromEnglish(false, "de");
			assertTrue(rh.isTextPresent("Präferenzen modifiziert"));
			advancedDocumentationSearch(IRhnBase.SEARCH_Documentation_Title_German, "Berechtigungen verwalten", false);
			verifyNotEmpty();
			assertTrue(task_Search.searchForResult("Berechtigungen verwalten", totalInList()));
		}
		finally {
			task_RhnBase.changeLanguageSettingFromGerman(false, "en_US");
			assertTrue(rh.isTextPresent("Preferences modified"));
		}
	}
	
	@Test(dataProvider="getReferenceGuideByLanguageDataAs2dArray", groups = { "testplan-SearchDocumentation" })
	public void test_DocumentationSearch_OpenReferenceGuideByLanguage(String language, String languageGuide, String translatedReferenceGuide, String translatedReferenceGuideTopic) {
		task_RhnBase.OpenAndLogIn();
		page_DocSearch.click_HelpLink();
		page_DocSearch.click_RefernceGuide();
		log.info("Attemptiing to open the Red Hat Network Reference Guide in '"+language+"'.");
		page_DocSearch.click_LanguageGuides(languageGuide);
		Assert.assertTrue(rh.isTextPresent(translatedReferenceGuide), "The '"+language+"' translated 'Reference Guide' is deplayed on this page.");
		Assert.assertTrue(rh.isTextPresent(translatedReferenceGuideTopic),  "A '"+language+"' translated Reference Guide topic is deplayed on this page.");
	}

//  This test was replaced by its equivalent dataProvided test_DocumentationSearch_OpenReferenceGuideByLanguage(...)
//	@Test(groups = { "testplan-SearchDocumentation" })
//	public void test_DocumentationSearch_IndexPages() {
//		task_RhnBase.OpenAndLogIn();
//		page_DocSearch.click_HelpLink();
//				
//		//German
//		page_DocSearch.click_RefernceGuide();
//		page_DocSearch.click_LanguageGuides("de-DE");
//		assertTrue(rh.isTextPresent("Referenzhandbuch"));
//		assertTrue(rh.isTextPresent("Virtualisierung"));
//		
//		//Spanish
//		page_DocSearch.click_RefernceGuide();
//		page_DocSearch.click_LanguageGuides("es-ES");
//		assertTrue(rh.isTextPresent("Manual de referencia"));
//		assertTrue(rh.isTextPresent("Virtualización"));
//		
//		//French
//		page_DocSearch.click_RefernceGuide();
//		page_DocSearch.click_LanguageGuides("fr-FR");
//		assertTrue(rh.isTextPresent("Guide de référence"));
//		assertTrue(rh.isTextPresent("Virtualisation"));
//		
//		//Italian
//		page_DocSearch.click_RefernceGuide();
//		page_DocSearch.click_LanguageGuides("it-IT");
//		assertTrue(rh.isTextPresent("Reference Guide"));
//		assertTrue(rh.isTextPresent("Virtualizzazione"));
//		
//		//Brazilian
//		page_DocSearch.click_RefernceGuide();
//		page_DocSearch.click_LanguageGuides("pt-BR");
//		assertTrue(rh.isTextPresent("Guia de Referência"));
//		assertTrue(rh.isTextPresent("Virtualização"));
//		
//		//Japanese
//		page_DocSearch.click_RefernceGuide();
//		page_DocSearch.click_LanguageGuides("ja-JP");
//		assertTrue(rh.isTextPresent("リファレンスガイド"));
//		assertTrue(rh.isTextPresent("複数組織"));
//		
//		//Korean
//		page_DocSearch.click_RefernceGuide();
//		page_DocSearch.click_LanguageGuides("ko-KR");
//		assertTrue(rh.isTextPresent("참조 가이드 "));
//		assertTrue(rh.isTextPresent("여러 조직 관리"));
//		
//		//Simplified Chinese
//		page_DocSearch.click_RefernceGuide();
//		page_DocSearch.click_LanguageGuides("zh-CN");
//		assertTrue(rh.isTextPresent("参​考​指​南​"));
//		assertTrue(rh.isTextPresent("Red Hat Network 总​览​"));
//		
//		//Traditional Chinese
//		page_DocSearch.click_RefernceGuide();
//		page_DocSearch.click_LanguageGuides("zh-TW");
//		assertTrue(rh.isTextPresent("參​考​指​南​"));
//		assertTrue(rh.isTextPresent("多​重​組​織​"));
//		
//		//Russian
//		page_DocSearch.click_RefernceGuide();
//		page_DocSearch.click_LanguageGuides("ru-RU");
//		assertTrue(rh.isTextPresent("Reference Guide"));
//		assertTrue(rh.isTextPresent("Подготовка систем клиентов"));
//		
//		//Bengali
//		page_DocSearch.click_RefernceGuide();
//		page_DocSearch.click_LanguageGuides("bn-IN");
//		assertTrue(rh.isTextPresent("রেফারেন্স গাইড ৫.২.০"));
//		assertTrue(rh.isTextPresent("ভার্চুয়ালাইজেশন"));
//		
//		//Gujarati
//		page_DocSearch.click_RefernceGuide();
//		page_DocSearch.click_LanguageGuides("gu-IN");
//		assertTrue(rh.isTextPresent("સંદર્ભ માર્ગદર્શિકા"));
//		assertTrue(rh.isTextPresent("ઘણીબધી સંસ્થાઓ"));
//		
//		//Hindi
//		page_DocSearch.click_RefernceGuide();
//		page_DocSearch.click_LanguageGuides("hi-IN");
//		assertTrue(rh.isTextPresent("संदर्भ गाइड 5.2.0"));
//		assertTrue(rh.isTextPresent("वर्चुअलाइजेशन"));
//		
//		//Punjabi
//		page_DocSearch.click_RefernceGuide();
//		page_DocSearch.click_LanguageGuides("pa-IN");
//		assertTrue(rh.isTextPresent("Red Hat ਨੈੱਟਵਰਕ ਸੈਟੇਲਾਈਟ"));
//		assertTrue(rh.isTextPresent("ਵਰਚੁਅਲਾਈਜੇਸ਼ਨ"));
//		
//		//Tamil
//		page_DocSearch.click_RefernceGuide();
//		page_DocSearch.click_LanguageGuides("ta-IN");
//		assertTrue(rh.isTextPresent("Red Hat Network சேட்டிலைட்"));
//		assertTrue(rh.isTextPresent("மெய்நிகராக்கம்"));
//	}
	
	
	@Test(groups = { "testplan-SearchDocumentation" })
	public void test_DocumentationSearch_ExternalDocuments() {
		task_RhnBase.OpenAndLogIn();
		page_RhnCommon.openExternalLink("http://www.redhat.com/docs/manuals/satellite/");
		assertTrue(rh.isTextPresent("Red Hat Network Satellite 5.3.0"));
	}
	
	
	public int totalInList() {
		WebList list = WebList.getList();
		return list.getTotalCount();
	}
	
	private void verifyNotEmpty() {
		WebList list = WebList.getList();
		assertTrue(list.getTotalCount() > 0);
	}
	
	
	public void advancedDocumentationSearch( int fieldToSearch, String searchValue, 
			boolean openAndLogin) {
		if(openAndLogin){
			task_RhnBase.OpenAndLogIn();
		}
		page_DocSearch.click_HelpLink();
		page_DocSearch.click_DocSearchLink();
		
		page_DocSearch.setTxt_SearchText(searchValue);
		page_DocSearch.select_SearchFor(fieldToSearch);
		page_DocSearch.clickButton_Search();			
	}
	
	@DataProvider(name="getReferenceGuideByLanguageDataAs2dArray")
	public Object[][] getReferenceGuideByLanguageDataAs2dArray() {
		return TestNGUtils.convertListOfListsTo2dArray(getReferenceGuideByLanguageDataAsListOfLists());
	}
	protected List<List<Object>> getReferenceGuideByLanguageDataAsListOfLists() {
		List<List<Object>> ll = new ArrayList<List<Object>>();
		ll.add(Arrays.asList(new Object[]{"English",				"en-US",	"Reference Guide",				"Virtualization"} ));
		ll.add(Arrays.asList(new Object[]{"German",					"de-DE",	"Referenzhandbuch",				"Virtualisierung"} ));
		ll.add(Arrays.asList(new Object[]{"Spanish",				"es-ES",	"Manual de referencia",			"Virtualización"} ));
		ll.add(Arrays.asList(new Object[]{"French",					"fr-FR",	"Guide de référence",			"Virtualisation"} ));
		ll.add(Arrays.asList(new Object[]{"Italian",				"it-IT",	"Reference Guide",				"Virtualizzazione"} ));
		ll.add(Arrays.asList(new Object[]{"Brazilian Portuguese",	"pt-BR",	"Guia de Referência",			"Virtualização"} ));
		ll.add(Arrays.asList(new Object[]{"Japanese",				"ja-JP",	"リファレンスガイド",				"複数組織"} ));
		ll.add(Arrays.asList(new Object[]{"Korean",					"ko-KR",	"참조 가이드",						"여러 조직 관리"} ));
		ll.add(Arrays.asList(new Object[]{"Simplified Chinese",		"zh-CN",	"参​考​指​南",						"虚​拟​化​"} ));
//		ll.add(Arrays.asList(new Object[]{"Traditional Chinese",	"zh-TW",	"參​考​指​南	",						"虛​擬化​"} ));
		ll.add(Arrays.asList(new Object[]{"Traditional Chinese",	"zh-TW",	"參​考​指​南​",						"\u865b\u200b\u64ec\u200b\u5316\u200b"} ));
		ll.add(Arrays.asList(new Object[]{"Russian",				"ru-RU",	"Reference Guide",				"Подготовка систем клиентов"} ));
		ll.add(Arrays.asList(new Object[]{"Bengali",				"bn-IN",	"রেফারেন্স গাইড ৫.২.০",				"ভার্চুয়ালাইজেশন"} ));
		ll.add(Arrays.asList(new Object[]{"Gujarati",				"gu-IN",	"સંદર્ભ માર્ગદર્શિકા",					"ઘણીબધી સંસ્થાઓ"} ));
		ll.add(Arrays.asList(new Object[]{"Hindi",					"hi-IN",	"संदर्भ गाइड 5.2.0",					"वर्चुअलाइजेशन"} ));
		ll.add(Arrays.asList(new Object[]{"Punjabi",				"pa-IN",	"Red Hat ਨੈੱਟਵਰਕ ਸੈਟੇਲਾਈਟ",			"ਵਰਚੁਅਲਾਈਜੇਸ਼ਨ"} ));
		ll.add(Arrays.asList(new Object[]{"Tamil",					"ta-IN",	"Red Hat Network சேட்டிலைட்",		"மெய்நிகராக்கம்"} ));
		return ll;
	}
}

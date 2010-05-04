package com.redhat.rhn.harness.hosted;

import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.Hosted.tasks.ErrataManagement;

public class T05ErrataManagementHosted extends ErrataManagement{
	RhnHelper rh = new RhnHelper();

public void testDisplayErrataOverviewPage(){
	
	DisplayErrataOverviewPage();
}

public void testDisplayAllErrataPage(){
	
	DisplayAllErrataPage();

}
public void testDisplayReleventErrataPage(){
	
	DisplayReleventErrataPage();

}
public void testManageErrataPage(){
	
	DisplayManageErrataPage();

}
public void testPublishedErrataPage(){
	
	DisplayPublishedErrataPage();

}
public void testUnpublishedErrataPage(){
	
	DisplayUnpublishedErrataPage();

}
public void testDeleteErrata(){
	
	DeleteAllErrata(true);

}


public void testCreateNewErratum(){
	
	CreateNewErrata("NewErrataAut05");


}

public void testPublishNewErratum(){
	
	PublishNewErrata("NewErrataAut04");


}
public void testCreateNewErratumErrorCheck1(){
	
    CreateNewErrataErrorCheck1();


}
public void testCreateNewErratumErrorCheck2(){
	
    CreateNewErrataErrorCheck2();


}
public void testCreateNewErratumErrorCheck3(){
	
    CreateNewErrataErrorCheck3();


}
public void testCreateNewErratumErrorCheck4(){
	
    CreateNewErrataErrorCheck4();


}
public void testCreateNewErratumErrorCheck5(){
	
    CreateNewErrataErrorCheck5();


}
public void testCreateNewErratumErrorCheck6(){
	
    CreateNewErrataErrorCheck6();


}
public void testCreateNewErratumErrorCheck7(){
	
    CreateNewErrataErrorCheck7();




}

}

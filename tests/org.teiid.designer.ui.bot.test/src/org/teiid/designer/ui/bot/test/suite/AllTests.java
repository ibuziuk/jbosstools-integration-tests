package org.teiid.designer.ui.bot.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;
import org.teiid.designer.ui.bot.test.ImportWizardTest;
import org.teiid.designer.ui.bot.test.ModelWizardTest;
import org.teiid.designer.ui.bot.test.TopDownWsdlTest;
import org.teiid.designer.ui.bot.test.VirtualGroupTutorialTest;

/**
 * Test suite for all teiid bot tests
 * 
 * @author apodhrad
 * 
 */
@SuiteClasses({
	ImportWizardTest.class,
	ModelWizardTest.class,
	TopDownWsdlTest.class,
	VirtualGroupTutorialTest.class
})
@RunWith(TeiidSuite.class)
public class AllTests {

}
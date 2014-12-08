/*******************************************************************************
 * Copyright (c) 2010-2012 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Red Hat, Inc. - initial API and implementation
 ******************************************************************************/
package org.jboss.tools.cdi.seam3.bot.test.tests;

import static org.junit.Assert.*;

import org.jboss.ide.eclipse.as.reddeer.server.requirement.ServerReqType;
import org.jboss.ide.eclipse.as.reddeer.server.requirement.ServerRequirement;
import org.jboss.ide.eclipse.as.reddeer.server.requirement.ServerRequirement.JBossServer;
import org.jboss.reddeer.eclipse.jdt.ui.packageexplorer.PackageExplorer;
import org.jboss.reddeer.jface.text.contentassist.ContentAssistant;
import org.jboss.reddeer.junit.requirement.inject.InjectRequirement;
import org.jboss.reddeer.eclipse.ui.perspectives.JavaEEPerspective;
import org.jboss.reddeer.requirements.cleanworkspace.CleanWorkspaceRequirement.CleanWorkspace;
import org.jboss.reddeer.requirements.openperspective.OpenPerspectiveRequirement.OpenPerspective;
import org.jboss.reddeer.requirements.server.ServerReqState;
import org.jboss.reddeer.swt.impl.styledtext.DefaultStyledText;
import org.jboss.reddeer.workbench.impl.editor.DefaultEditor;
import org.jboss.reddeer.workbench.impl.editor.TextEditor;
import org.jboss.tools.cdi.reddeer.CDIConstants;
import org.jboss.tools.cdi.seam3.bot.test.base.Seam3TestBase;
import org.jboss.tools.cdi.seam3.bot.test.util.SeamLibrary;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * @author jjankovi
 * 
 */
@CleanWorkspace
@OpenPerspective(JavaEEPerspective.class)
@JBossServer(state=ServerReqState.PRESENT, type=ServerReqType.AS7_1)
public class SeamConfigInjectOpenOnTest extends Seam3TestBase {

	private static String projectName = "seamConfigInjectOpenOn";
	private static final String SEAM_CONFIG = "seam-beans.xml";
	
	@InjectRequirement
    private static ServerRequirement sr;

	@BeforeClass
	public static void setup() {
		importSeam3ProjectWithLibrary(projectName, SeamLibrary.SOLDER_3_1, sr.getRuntimeNameLabelText(sr.getConfig()));
	}

	@Before
	public void openSeamConfig() {
		PackageExplorer pe = new PackageExplorer();
		pe.open();
		pe.getProject(projectName).getProjectItem(CDIConstants.SRC, "test",
				"Report.java").open();
		new TextEditor("Report.java");
	}

	@Test
	public void testBasicInjectOpenOn() {
		
		TextEditor t = new TextEditor("Report.java");
		t.selectText("path1");
		ContentAssistant as = t.openOpenOnAssistant();
		for(String s: as.getProposals()){
			if(s.contains("Open Resource in seam-beans.xml")){
				as.chooseProposal(s);
				break;
			}
		}
		new DefaultEditor(SEAM_CONFIG);
		assertExpectedSelection("<r:Resource path=\"value\">");

	}

	@Test
	public void testQualifierInjectOpenOn() {

		TextEditor t = new TextEditor("Report.java");
		t.selectText("path2");
		ContentAssistant as = t.openOpenOnAssistant();
		for(String s: as.getProposals()){
			if(s.contains("Open Resource in seam-beans.xml")){
				as.chooseProposal(s);
				break;
			}
		}
		new DefaultEditor(SEAM_CONFIG);
		assertExpectedSelection("<r:Resource>");

	}

	private void assertExpectedSelection(String selectedString) {
		assertEquals(selectedString, new DefaultStyledText().getSelectionText());
	}

}

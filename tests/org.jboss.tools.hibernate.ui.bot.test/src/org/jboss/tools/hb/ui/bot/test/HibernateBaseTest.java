package org.jboss.tools.hb.ui.bot.test;

import java.io.File;
import java.io.IOException;

import org.jboss.tools.hibernate.ui.bot.testcase.Activator;
import org.jboss.tools.ui.bot.ext.SWTTestExt;
import org.jboss.tools.ui.bot.ext.helper.FileHelper;
import org.jboss.tools.ui.bot.ext.helper.ImportHelper;
import org.jboss.tools.ui.bot.ext.helper.ResourceHelper;
import org.jboss.tools.ui.bot.ext.view.ErrorLogView;

public class HibernateBaseTest extends SWTTestExt {
	
	public void emptyErrorLog() {
		ErrorLogView el = new ErrorLogView();
		el.delete();
		util.waitForNonIgnoredJobs();
	}
	
	public void checkErrorLog() {
		ErrorLogView el = new ErrorLogView();
		int count = el.getRecordCount();
		if (count > 0) {
			el.logMessages();
			// Ignored for now
			// fail("Unexpected messages in Error log, see test log");
		}
	}
	
	public void importTestProject(String dir) {
		String rpath = ResourceHelper.getResourceAbsolutePath(
				Activator.PLUGIN_ID, dir);
		String wpath = ResourceHelper.getWorkspaceAbsolutePath() + dir;
		File rfile = new File(rpath);
		File wfile = new File(wpath);
		
		wfile.mkdirs();
		try {
			FileHelper.copyFilesBinaryRecursively(rfile, wfile, null);
		} catch (IOException e) {
			fail("Unable to copy test project");
		}
		ImportHelper.importAllProjects(wpath);
		util.waitForNonIgnoredJobs();		
	}
		
		 
}
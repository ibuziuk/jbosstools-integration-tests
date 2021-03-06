/*******************************************************************************
 * Copyright (c) 2007-2010 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributor:
 *     Red Hat, Inc. - initial API and implementation
 ******************************************************************************/
package org.jboss.tools.vpe.ui.bot.test.wizard;

import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.jboss.tools.ui.bot.ext.gen.ActionItem;
import org.jboss.tools.ui.bot.ext.types.IDELabel;
import org.jboss.tools.ui.bot.test.WidgetVariables;
import org.jboss.tools.vpe.ui.bot.test.VPEAutoTestCase;

/**
 * This test class open vpe preference page Window->Preferences->JBoss
 * Tools->Web->Editors->Visual Page Editor->Templates
 * 
 * @author mareshkau
 * 
 */
public class VPESourceCodeTemplatesPreferencePageTest extends VPEAutoTestCase {

  // just open a VPE Source Code templates preference test page
  public void testSourceCodeTemplatesPreferencePage() {
    open.preferenceOpen(ActionItem.Preference.JBossTools.LABEL);
    SWTBotTree preferenceTree = this.bot.tree();
    preferenceTree.expandNode(IDELabel.PreferencesDialog.JBOSS_TOOLS)
        //$NON-NLS-1$
        .expandNode(IDELabel.PreferencesDialog.JBOSS_TOOLS_WEB)
        //$NON-NLS-1$
        .expandNode(IDELabel.PreferencesDialog.JBOSS_TOOLS_WEB_EDITORS)
        //$NON-NLS-1$
        .expandNode(IDELabel.PreferencesDialog.JBOSS_TOOLS_WEB_EDITORS_VPE)
        .select(); //$NON-NLS-1$
    bot.tabItem(
        IDELabel.PreferencesDialog.JBOSS_TOOLS_WEB_EDITORS_VPE_VISUAL_TEMPLATES)
        .activate(); //$NON-NLS-1$
    SWTBotShell preferencesShell = bot.activeShell();
    try {
      this.bot.button(IDELabel.Button.ADD).click(); //$NON-NLS-1$
      this.bot.button(IDELabel.Button.CANCEL).click(); //$NON-NLS-1$
    } catch (WidgetNotFoundException ex) {
      fail("Preference Page has not been created" + ex);//$NON-NLS-1$
    } finally {
      preferencesShell.activate();
      this.bot.button(WidgetVariables.OK_BUTTON).click();
    }
  }

  @Override
  protected void closeUnuseDialogs() {

  }

  @Override
  protected boolean isUnuseDialogOpened() {
    return false;
  }
}

package org.jboss.tools.cdi.reddeer.cdi.ui;

import org.jboss.reddeer.common.wait.WaitUntil;
import org.jboss.reddeer.core.matcher.WithTextMatcher;
import org.jboss.reddeer.jface.wizard.NewWizardDialog;
import org.jboss.reddeer.swt.condition.TableHasRows;
import org.jboss.reddeer.swt.impl.button.CheckBox;
import org.jboss.reddeer.swt.impl.button.PushButton;
import org.jboss.reddeer.swt.impl.combo.LabeledCombo;
import org.jboss.reddeer.swt.impl.shell.DefaultShell;
import org.jboss.reddeer.swt.impl.table.DefaultTable;
import org.jboss.reddeer.swt.impl.text.DefaultText;
import org.jboss.reddeer.swt.impl.text.LabeledText;
import org.jboss.tools.cdi.reddeer.CDIConstants;

public class NewStereotypeCreationWizard extends NewWizardDialog{
	
	public static final String NAME="Stereotype Annotation";
	
	public NewStereotypeCreationWizard(){
		super(CDIConstants.CDI_GROUP,NAME);
	}
	
	public void setPackage(String packageName){
		new LabeledText("Package:").setText(packageName);
	}
	
	public void setName(String name){
		new LabeledText("Name:").setText(name);
	}
	
	public String getName(){
		return new LabeledText("Name:").getText();
	}
	
	public String getPackage(){
		return new LabeledText("Package:").getText();
	}
	
	public void setInherited(boolean inherited){
		new CheckBox("Add @Inherited").toggle(inherited);
	}
	
	public void setAlternative(boolean alternative){
		new CheckBox("Add @Alternative").toggle(alternative);
	}
	
	public boolean isAlternative(){
		return new CheckBox("Add @Alternative").isChecked();
	}
	
	public void setNamed(boolean named){
		new CheckBox("Add @Named").toggle(named);
	}
	
	public void setRegisterInBeans(boolean register){
		new CheckBox("Register in beans.xml").toggle(register);
	}
	
	public void setGenerateComments(boolean generate){
		new CheckBox("Generate comments").toggle(generate);
	}
	
	public void setTarget(String target){
		new LabeledCombo("Target:").setSelection(target);
	}
	
	public void setScope(String scope){
		new LabeledCombo("Scope:").setSelection(scope);
	}
	
	public void addInterceptorBindings(String bindings){
		new PushButton("Add").click();
		new DefaultShell("Select Interceptor Binding Annotation Type");
		new DefaultText(0).setText(bindings);
		new WaitUntil(new TableHasRows(new DefaultTable()));
		new PushButton("OK").click();
		new DefaultShell("New Stereotype");
	}
	
	public void addStereoptypes(String stereoptypes){
		new PushButton(1,new WithTextMatcher("Add")).click();
		new DefaultShell("Select Stereotype Annotation Type");
		new DefaultText(0).setText(stereoptypes);
		new WaitUntil(new TableHasRows(new DefaultTable()));
		new PushButton("OK").click();
		new DefaultShell("New Stereotype");
	}



}

package org.sikuli.ide;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import org.sikuli.basics.Debug;
import org.sikuli.ide.SikuliIDE.FileAction;

public class SikuliIDEPopUpMenu extends JPopupMenu {

  private static String me = "SikuliIDEPopUpMenu";
  private static int lvl = 3;
  private String popType;
  private boolean validMenu = true;

  public static final String POP_TAB = "POP_TAB";
  private CloseableTabbedPane refTab;
  private EditorRegionLabel refRegionLabel = null;
  private EditorPatternLabel refPatternLabel = null;
  public static final String POP_IMAGE = "POP_IMAGE";

  /**
   * Get the value of isValidMenu
   *
   * @return the value of isValidMenu
   */
  public boolean isValidMenu() {
    return validMenu;
  }

  private static void log(int level, String message, Object... args) {
    Debug.logx(level, "", me + ": " + message, args);
  }

  public SikuliIDEPopUpMenu(String pType, Object ref) {
    popType = pType;
    init(ref);
  }

  private void init(Object ref) {
    if (popType.equals(POP_TAB)) {
      refTab = (CloseableTabbedPane) ref;
      popTabMenu();
    } else if (popType.equals(POP_IMAGE)) {
      if (((EditorRegionLabel) ref).isRegionLabel()) {
        refRegionLabel = (EditorRegionLabel) ref;
      } else {
        refPatternLabel = (EditorPatternLabel) ref;
      }
      PopImageMenu();
    } else {
      validMenu = false;
    }
    if (!validMenu) {
      return;
    }
  }

  private void fireIDEFileMenu(String name) throws NoSuchMethodException {
    fireIDEMenu(SikuliIDE.getInstance().getFileMenu(), name);
  }

  private void fireIDERunMenu(String name) throws NoSuchMethodException {
    fireIDEMenu(SikuliIDE.getInstance().getRunMenu(), name);
  }

  private void fireIDEMenu(JMenu menu, String name) throws NoSuchMethodException {
    JMenuItem jmi;
    String jmiName = null;
    for (int i = 0; i < menu.getItemCount(); i++) {
      jmi = menu.getItem(i);
      if (jmi == null || jmi.getName() == null) {
        continue;
      }
      jmiName = jmi.getName();
      if (jmiName.equals(name)) {
        jmi.doClick();
      }
    }
    if (jmiName == null) {
      log(-1, "IDEFileMenu not found: " + name);
    }
  }

  private JMenuItem createMenuItem(JMenuItem item, ActionListener listener) {
    item.addActionListener(listener);
    return item;
  }

  private JMenuItem createMenuItem(String name, ActionListener listener) {
    return createMenuItem(new JMenuItem(name), listener);
  }

  class MenuAction implements ActionListener {

    protected Method actMethod = null;
    protected String action;

    public MenuAction() {
    }

    public MenuAction(String item) throws NoSuchMethodException {
      Class[] paramsWithEvent = new Class[1];
      try {
        paramsWithEvent[0] = Class.forName("java.awt.event.ActionEvent");
        actMethod = this.getClass().getMethod(item, paramsWithEvent);
        action = item;
      } catch (ClassNotFoundException cnfe) {
        log(-1, "Can't find menu action: %s\n" + cnfe, item);
      }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      if (actMethod != null) {
        try {
          log(lvl, "MenuAction." + action);
          Object[] params = new Object[1];
          params[0] = e;
          actMethod.invoke(this, params);
        } catch (Exception ex) {
          log(-1, "Problem when trying to invoke menu action %s\nError: %s",
                  action, ex.getMessage());
        }
      }
    }
  }

  private void popTabMenu() {
    try {
      add(createMenuItem("Move Tab", new PopTabAction(PopTabAction.MOVE_TAB)));
      add(createMenuItem("Duplicate", new PopTabAction(PopTabAction.DUPLICATE)));
      addSeparator();
      add(createMenuItem("Save", new PopTabAction(PopTabAction.SAVE)));
      add(createMenuItem("SaveAs", new PopTabAction(PopTabAction.SAVE_AS)));
      addSeparator();
      add(createMenuItem("Run", new PopTabAction(PopTabAction.RUN)));
      add(createMenuItem("Run Slowly", new PopTabAction(PopTabAction.RUN_SLOW)));

    } catch (NoSuchMethodException ex) {
      validMenu = false;
    }
  }

  class PopTabAction extends MenuAction {

    static final String MOVE_TAB = "doMoveTab";
    static final String DUPLICATE = "doDuplicate";
    static final String SAVE = "doSave";
    static final String SAVE_AS = "doSaveAs";
    static final String RUN = "doRun";
    static final String RUN_SLOW = "doRunSlow";

    public PopTabAction() {
      super();
    }

    public PopTabAction(String item) throws NoSuchMethodException {
      super(item);
    }

    public void doMoveTab(ActionEvent ae) throws NoSuchMethodException {
      log(lvl, "doMoveTab: entered");
      fireIDEFileMenu("SAVE");
      fireIDEFileMenu("CLOSE_TAB");
    }

    public void doDuplicate(ActionEvent ae) throws NoSuchMethodException {
      log(lvl, "doDuplicate: entered");
      fireIDEFileMenu("SAVE");
      fireIDEFileMenu("SAVE_AS");
    }

    public void doSave(ActionEvent ae) throws NoSuchMethodException {
      log(lvl, "doSave: entered");
      fireIDEFileMenu("SAVE");
    }

    public void doSaveAs(ActionEvent ae) throws NoSuchMethodException {
      log(lvl, "doSaveAs: entered");
      fireIDEFileMenu("SAVE_AS");
    }

    public void doRun(ActionEvent ae) throws NoSuchMethodException {
      log(lvl, "doRun: entered");
      fireIDERunMenu("RUN");
    }

    public void doRunSlow(ActionEvent ae) throws NoSuchMethodException {
      log(lvl, "doRunSlow: entered");
      fireIDERunMenu("RUN_SLOWLY");
    }
  }

  private void PopImageMenu() {
    try {
      add(createMenuItem("Preview", new PopImageAction(PopImageAction.PREVIEW)));
    } catch (NoSuchMethodException ex) {
      validMenu = false;
    }
  }

  class PopImageAction extends MenuAction {

    static final String PREVIEW = "doPreview";

    public PopImageAction() {
      super();
    }

    public PopImageAction(String item) throws NoSuchMethodException {
      super(item);
    }

    public void doPreview(ActionEvent ae) {
      log(lvl, "doPreview:");
    }
  }
}

package org.sikuli.ide;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import org.sikuli.basics.Debug;

public class SikuliIDEPopUpMenu extends JPopupMenu {

  private static String me = "SikuliIDEPopUpMenu";
  private static int lvl = 3;
  
  private int entryCount;
  private String popType; 
  
  private boolean isValidMenu = true;
  /**
   * Get the value of isValidMenu
   *
   * @return the value of isValidMenu
   */
  public boolean isIsValidMenu() {
    return isValidMenu;
  }

  private static void log(int level, String message, Object... args) {
    Debug.logx(level, "", me + ": " + message, args);
  }
  
  public SikuliIDEPopUpMenu(String pType, int count) {
    entryCount = count;
    popType = pType;
    init(); 
  }
  
  private void init() {
    if (popType.equals("POP_TAB")) {
      makeMenuPopTab();
    } else if (popType.equals("POP_IMAGE")) {
      makeMenuPopImage();
    } else {
      isValidMenu = false;
    }
  }

  private JMenuItem createMenuItem(JMenuItem item, KeyStroke shortcut, ActionListener listener) {
    if (shortcut != null) {
      item.setAccelerator(shortcut);
    }
    item.addActionListener(listener);
    return item;
  }

  private JMenuItem createMenuItem(String name, KeyStroke shortcut, ActionListener listener) {
    JMenuItem item = new JMenuItem(name);
    return createMenuItem(item, shortcut, listener);
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
        log(-1,"Can't find menu action: %s\n" + cnfe, item);
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
    			log(-1,"Problem when trying to invoke menu action %s\nError: %s", 
                  action, ex.getMessage());
        }
      }
    }
  }

  private void makeMenuPopTab() {
    
  }

  private void makeMenuPopImage() {
    
  }
}

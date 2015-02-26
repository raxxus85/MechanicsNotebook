import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToolBar;

public class ToolbarDemo extends JFrame {

  public static final String FontNames[] = { "Serif", "SansSerif", "Courier" };

  protected Font fonts[];

  protected JMenuItem[] fontMenus;

  protected JCheckBoxMenuItem boldMenu = new JCheckBoxMenuItem("Bold");

  protected JCheckBoxMenuItem italicMenu = new JCheckBoxMenuItem("Italic");

  protected JToolBar toolBar;

  public ToolbarDemo() {
    super("Toolbars & actions");
    setSize(450, 350);

    fonts = new Font[FontNames.length];
    for (int i = 0; i < FontNames.length; i++)
      fonts[i] = new Font(FontNames[i], Font.PLAIN, 12);

    JMenuBar menuBar = createMenuBar();
    setJMenuBar(menuBar);


    WindowListener wndCloser = new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    };
    addWindowListener(wndCloser);

    updateMonitor();
    setVisible(true);
  }

  protected JMenuBar createMenuBar() {
    final JMenuBar menuBar = new JMenuBar();

    JMenu mFile = new JMenu("File");
    mFile.setMnemonic('f');

    //ImageIcon iconNew = new ImageIcon("file_new.gif");
    ImageIcon iconNew = new ImageIcon(getClass().getResource("/customer32x32EDIT.png"));
    Action actionNew = new AbstractAction("New", iconNew) {
      public void actionPerformed(ActionEvent e) {
        System.out.println("new action");
      }
    };
    JMenuItem item = mFile.add(actionNew);
    mFile.add(item);

    //ImageIcon iconOpen = new ImageIcon("file_open.gif");
    //jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mechanicShrunk - Copy.png")));
    ImageIcon iconOpen = new ImageIcon(getClass().getResource("/customer32x32ADD.png"));
    Action actionOpen = new AbstractAction("Open...", iconOpen) {
      public void actionPerformed(ActionEvent e) {
        System.out.println("open action");
      }
    };
    item = mFile.add(actionOpen);
    mFile.add(item);

    //ImageIcon iconSave = new ImageIcon("file_save.gif");
    ImageIcon iconSave = new ImageIcon(getClass().getResource("/customer32x32REMOVE.png"));
    Action actionSave = new AbstractAction("Save...", iconSave) {
      public void actionPerformed(ActionEvent e) {
        System.out.println("save action");
      }
    };
    item = mFile.add(actionSave);
    mFile.add(item);

    mFile.addSeparator();

    Action actionExit = new AbstractAction("Exit") {
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    };
    item = mFile.add(actionExit);
    item.setMnemonic('x');
    menuBar.add(mFile);

    toolBar = new JToolBar();
    JButton btn1 = toolBar.add(actionNew);
    btn1.setToolTipText("New text");
    JButton btn2 = toolBar.add(actionOpen);
    btn2.setToolTipText("Open text file");
    JButton btn3 = toolBar.add(actionSave);
    btn3.setToolTipText("Save text file");

    ActionListener fontListener = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        updateMonitor();
      }
    };

    JMenu mFont = new JMenu("Font");
    mFont.setMnemonic('o');

    ButtonGroup group = new ButtonGroup();
    fontMenus = new JMenuItem[FontNames.length];
    for (int k = 0; k < FontNames.length; k++) {
      int m = k + 1;
      fontMenus[k] = new JRadioButtonMenuItem(m + " " + FontNames[k]);
      boolean selected = (k == 0);
      fontMenus[k].setSelected(selected);
      fontMenus[k].setMnemonic('1' + k);
      fontMenus[k].setFont(fonts[k]);
      fontMenus[k].addActionListener(fontListener);
      group.add(fontMenus[k]);
      mFont.add(fontMenus[k]);
    }

    mFont.addSeparator();

    boldMenu.setMnemonic('b');
    Font fn = fonts[1].deriveFont(Font.BOLD);
    boldMenu.setFont(fn);
    boldMenu.setSelected(false);
    boldMenu.addActionListener(fontListener);
    mFont.add(boldMenu);

    italicMenu.setMnemonic('i');
    fn = fonts[1].deriveFont(Font.ITALIC);
    italicMenu.setFont(fn);
    italicMenu.setSelected(false);
    italicMenu.addActionListener(fontListener);
    mFont.add(italicMenu);

    menuBar.add(mFont);

    getContentPane().add(toolBar, BorderLayout.NORTH);

    return menuBar;
  }

  protected void updateMonitor() {
    int index = -1;
    for (int k = 0; k < fontMenus.length; k++) {
      if (fontMenus[k].isSelected()) {
        index = k;
        break;
      }
    }
    if (index == -1)
      return;

    if (index == 2) // Courier
    {
      boldMenu.setSelected(false);
      boldMenu.setEnabled(false);
      italicMenu.setSelected(false);
      italicMenu.setEnabled(false);
    } else {
      boldMenu.setEnabled(true);
      italicMenu.setEnabled(true);
    }

    int style = Font.PLAIN;
    if (boldMenu.isSelected())
      style |= Font.BOLD;
    if (italicMenu.isSelected())
      style |= Font.ITALIC;
    Font fn = fonts[index].deriveFont(style);
  }

  public static void main(String argv[]) {
    new ToolbarDemo();
  }
}
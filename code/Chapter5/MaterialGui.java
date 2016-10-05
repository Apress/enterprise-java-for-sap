import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MaterialGui implements ActionListener {
  JFrame sapFrame;
  JPanel sapPanel;
  JTextField matSearch;
  JButton retrieveList;
  JTextArea resultText;
  JPanel selectPane;
  JPanel displayPane;

  public MaterialGui() {
    sapFrame = new JFrame("Material Search for SAP R/3");
    sapPanel = new JPanel();
    sapPanel.setLayout(new BorderLayout());
    selectPane = new JPanel();
    displayPane = new JPanel();

    matSearch = new JTextField(20);
    resultText = new JTextArea(10,30);
    resultText.setEditable(false);
    JScrollPane resultPane = new JScrollPane(resultText,
                                       JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                                       JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

    retrieveList = new JButton("Get materials...");
    retrieveList.setMnemonic(KeyEvent.VK_ENTER);
    retrieveList.addActionListener(this);

    selectPane.add(matSearch);
    selectPane.add(retrieveList);
    displayPane.add(resultPane);

    sapPanel.add(selectPane, BorderLayout.NORTH);
    sapPanel.add(displayPane, BorderLayout.SOUTH);
    sapFrame.getContentPane().add(sapPanel);
    sapFrame.getRootPane().setDefaultButton(retrieveList);
    sapFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    sapFrame.pack();
    sapFrame.setVisible(true);
  }

  public void actionPerformed(ActionEvent event) {
    InterfaceCaller iCaller = new InterfaceCaller();
    Hashtable returnHash = iCaller.getMaterialList(matSearch.getText());
    Hashtable rowHash;
    for (Enumeration e = returnHash.elements(); e.hasMoreElements();) {
      rowHash = (Hashtable)e.nextElement();
      resultText.append("Material: " +
     (String)rowHash.get("MATERIAL") + "\n");
      resultText.append("Material description: " +
     (String)rowHash.get("MATL_DESC") + "\n\n");
    }
  }
  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(
      UIManager.getCrossPlatformLookAndFeelClassName());
    }
    catch (Exception e) {}
    MaterialGui matGui = new MaterialGui();
  }
}

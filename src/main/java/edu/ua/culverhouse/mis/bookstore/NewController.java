package edu.ua.culverhouse.mis.bookstore;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class NewController {

    // Separate JPanels for edit and new functions
    private JPanel newJPanel, editJPanel;
    private JTextField newIsbnFld, newTitleFld, newAuthorFld, newGenreFld, newCopiesFld, newLengthFld, newCoverFld;
    private JTextField editIsbnFld, editTitleFld, editAuthorFld, editGenreFld, editCopiesFld, editLengthFld,
            editCoverFld;

    public NewController() {
    }

    // Used if NEW button was clicked from homepage
    public Book createAndDisplayNewGUI() {
        Book tempBook = new Book();

        // Set layout for newPane, store button clicked in selection
        int selection = JOptionPane.showConfirmDialog(null, getNewPanel(), "Add Book", JOptionPane.OK_CANCEL_OPTION);

        // If user clicks OK, check text fields for errors
        if (selection == JOptionPane.OK_OPTION) {
            if (newErrorCheck()) {
                // If text fields are filled out correctly, create new Dog object and
                // return to MainController
                tempBook = new Book(null, newIsbnFld.getText(), newTitleFld.getText(), newAuthorFld.getText(),
                        newCoverFld.getText(), newGenreFld.getText(), Integer.parseInt(newLengthFld.getText()),
                        Integer.parseInt(newCopiesFld.getText()), null);
                return tempBook;
                // If text fields were not displayed properly, errorCheck function will display
                // error
                // message, then redisplay newJPanel
            } else {
                createAndDisplayNewGUI();
            }
        }
        return null;
    }

    // Used if EDIT button was clicked from homepage, pass in text field values from
    // selected dog
    // in dogLst
    /* public Dog createAndDisplayEditGUI(String id, String name, String breed, int sexIndex, String age, String weight,
            String imgPath) {
        Dog tempDog = new Dog();
        // Create editJPanel, set layout
        int selection = JOptionPane.showConfirmDialog(null,
                getEditPanel(id, name, breed, sexIndex, age, weight, imgPath), "Edit Dog",
                JOptionPane.OK_CANCEL_OPTION);
        // If okay button is pressed, check text fields for errors
        if (selection == JOptionPane.OK_OPTION) {
            // If no errors, create and initialize tempDog object and send it back to
            // MainController
            if (editErrorCheck()) {
                tempDog = new Dog(Integer.parseInt(editIdFld.getText()), editNameFld.getText(), editBreedFld.getText(),
                        editSexComboBox.getItemAt(editSexComboBox.getSelectedIndex()),
                        Integer.parseInt(editAgeFld.getText()), Float.parseFloat(editWeightFld.getText()),
                        editImgFld.getText());
                return tempDog;
            } else {
                createAndDisplayEditGUI(id, name, breed, sexIndex, age, weight, imgPath);
            }
        }
        return tempDog;
    } */

    // Create and set layout for newJOptionPane
    private JPanel getNewPanel() {
        newJPanel = new JPanel();
        newJPanel.setLayout(new GridLayout(0, 2, 2, 2));

        newIsbnFld = new JTextField("");
        newTitleFld = new JTextField("");
        newAuthorFld = new JTextField("");
        newCoverFld = new JTextField("");
        newGenreFld = new JTextField("");
        newCopiesFld = new JTextField("");
        newLengthFld = new JTextField("");

        newJPanel.add(new JLabel("ISBN"));
        newJPanel.add(newIsbnFld);

        newJPanel.add(new JLabel("Title"));
        newJPanel.add(newTitleFld);

        newJPanel.add(new JLabel("Author"));
        newJPanel.add(newAuthorFld);

        newJPanel.add(new JLabel("Cover"));
        newJPanel.add(newCoverFld);

        newJPanel.add(new JLabel("Genre"));
        newJPanel.add(newGenreFld);

        newJPanel.add(new JLabel("Copies"));
        newJPanel.add(newCopiesFld);

        newJPanel.add(new JLabel("Length"));
        newJPanel.add(newLengthFld);

        newJPanel.setSize(new Dimension(350, 10));
        newJPanel.setPreferredSize(new Dimension(350, newJPanel.getPreferredSize().height));

        return newJPanel;
    }

    // Create and set layout for editJOptionPane
    /* private JPanel getEditPanel(String id, String name, String breed, int sexIndex, String age, String weight,
            String imgPath) {
        editJPanel = new JPanel();
        editJPanel.setLayout(new GridLayout(0, 2, 2, 2));

        editIdFld = new JTextField(id);
        editNameFld = new JTextField(name);
        editBreedFld = new JTextField(breed);
        editAgeFld = new JTextField(age);
        editWeightFld = new JTextField(weight);
        String[] sexOptions = { " ", "M", "F" };
        editSexComboBox = new JComboBox<>(sexOptions);
        editSexComboBox.setSelectedIndex(sexIndex);
        editImgFld = new JTextField(imgPath);

        editJPanel.add(new JLabel("ID"));
        editJPanel.add(editIdFld);

        editJPanel.add(new JLabel("Name"));
        editJPanel.add(editNameFld);

        editJPanel.add(new JLabel("Breed"));
        editJPanel.add(editBreedFld);

        editJPanel.add(new JLabel("Sex"));
        editJPanel.add(editSexComboBox);

        editJPanel.add(new JLabel("Age"));
        editJPanel.add(editAgeFld);

        editJPanel.add(new JLabel("Weight"));
        editJPanel.add(editWeightFld);

        editJPanel.add(new JLabel("Image URL"));
        editJPanel.add(editImgFld);

        editJPanel.setSize(new Dimension(350, 10));
        editJPanel.setPreferredSize(new Dimension(350, editJPanel.getPreferredSize().height));

        return editJPanel;
    } */

    // Check text fields for errors in newJPanel, display error messages if errors
  private boolean newErrorCheck() {
    if (newIsbnFld.getText().equals("")) {
      JOptionPane.showMessageDialog(new JFrame(),
          "Please enter an ISBN!",
          "Invalid ISBN",
          JOptionPane.ERROR_MESSAGE);
      return false;
    }
    if (newTitleFld.getText().equals("")) {
      JOptionPane.showMessageDialog(new JFrame(),
          "Please enter a title!",
          "Invalid title",
          JOptionPane.ERROR_MESSAGE);
      return false;
    }
    if (newAuthorFld.getText().equals("")) {
      JOptionPane.showMessageDialog(new JFrame(),
          "Please enter an author!",
          "Invalid author",
          JOptionPane.ERROR_MESSAGE);
      return false;
    }
    if (newCoverFld.getText().equals("")) {
      JOptionPane.showMessageDialog(new JFrame(),
          "Please enter a valid cover url!",
          "Invalid url",
          JOptionPane.ERROR_MESSAGE);
      return false;
    }
    try {
      Integer.parseInt(newCopiesFld.getText());
    } catch (Exception a) {
      JOptionPane.showMessageDialog(new JFrame(),
          "Please enter a valid number of copies!",
          "Invalid weight",
          JOptionPane.ERROR_MESSAGE);
      return false;
    }
    try {
         Integer.parseInt(newLengthFld.getText());
    } catch (Exception a) {
      JOptionPane.showMessageDialog(new JFrame(),
          "Please enter a valid length!",
          "Invalid url",
          JOptionPane.ERROR_MESSAGE);
      return false;
    }
    return true;
  }

    /* // Check text fields for errors in editJPanel, display error messages if errors
    private boolean editErrorCheck() {
        try {
            Integer.parseInt(editIdFld.getText());
        } catch (Exception a) {
            JOptionPane.showMessageDialog(new JFrame(), "Please enter a valid id!", "Invalid age",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (editNameFld.getText().equals("")) {
            JOptionPane.showMessageDialog(new JFrame(), "Please enter a name!", "Invalid name",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (editBreedFld.getText().equals("")) {
            JOptionPane.showMessageDialog(new JFrame(), "Please enter a breed!", "Invalid breed",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (editSexComboBox.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(new JFrame(), "Please choose a sex!", "No sex selected",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            Integer.parseInt(editAgeFld.getText());
        } catch (Exception a) {
            JOptionPane.showMessageDialog(new JFrame(), "Please enter a valid age!", "Invalid age",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            Float.parseFloat(editWeightFld.getText());
        } catch (Exception a) {
            JOptionPane.showMessageDialog(new JFrame(), "Please enter a valid weight!", "Invalid weight",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (editImgFld.getText().equals("")) {
            JOptionPane.showMessageDialog(new JFrame(), "Please enter a valid Image URL!", "Invalid url",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    } */
}

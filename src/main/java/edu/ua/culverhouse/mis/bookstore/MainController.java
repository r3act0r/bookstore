package edu.ua.culverhouse.mis.bookstore;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MainController {

  // Private arrays and list for keeping track of dogs and customers
  private List<Book> myBooks;
  DefaultListModel<Book> tempBooks;
  private String cwid;

  // Private GUI components
  private JFrame mainJFrame;
  private JButton closeBtn, delBtn, editBtn, newBtn, rntBtn, returnBtn;
  private JList<Book> bookLst;

  private JTextField isbnFld, titleFld, authorFld, genreFld, copiesFld, lengthFld;

  private JLabel isbnLbl, titleLbl, authorLbl, genreLbl, copiesLbl, lengthLbl, imgLbl;

  // Constructor for home page, calls prepareGUI() which sets layout and
  // functionality of
  // the home page
  private MainController() throws Exception {
    prepareGUI();
  }

  // At program start, create object of type MainController (Home page)
  public static void main(String[] args) throws Exception {
    MainController MainController = new MainController();

  }

  // Main method where layout and functionality of the home page is defined
  private void prepareGUI() throws Exception {
    // Initialize main JFrame
    mainJFrame = new JFrame();

    // Initialize myDogs, myCustomers, and DefaultListModel tempDogs (used to live
    // update dogLst)
    tempBooks = new DefaultListModel<>();
    cwid = "11662623";
    List<Book> books = BookFile.GetAllBooks(cwid);
    for (Book book : books) {
        tempBooks.addElement(book);
    }

    imgLbl = new JLabel();
    imgLbl.setBounds(600, 69, 250, 330);
    mainJFrame.add(imgLbl);

    // Initialize list of dogs on the right side of home page
    bookLst = new JList<>(tempBooks);
    bookLst.setBounds(13, 69, 341, 412);

    // Fill text fields with info if a dog is selected, else clear all text fields
    bookLst.addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {

          // Clear text fields if no dog is selected
          if (bookLst.getSelectedIndex() == -1) {
            clearTextFields();

            // Fill text fields with respective data from dogLst
          } else {
            isbnFld.setText(tempBooks.get(bookLst.getSelectedIndex()).getIsbn());
            titleFld.setText(tempBooks.get(bookLst.getSelectedIndex()).getTitle());
            authorFld.setText(tempBooks.get(bookLst.getSelectedIndex()).getAuthor());
            genreFld.setText(tempBooks.get(bookLst.getSelectedIndex()).getGenre()); 
            copiesFld.setText(Integer.toString(tempBooks.get(bookLst.getSelectedIndex()).getCopies()));
            lengthFld.setText(Integer.toString(tempBooks.get(bookLst.getSelectedIndex()).getLength()));
            
            String path = tempBooks.get(bookLst.getSelectedIndex()).getCover();
            BufferedImage image = null;
            try {
            URL url = new URL(path);
            image = ImageIO.read(url);
            } catch (IOException a) {
              a.printStackTrace();
            }
            Image dimg = image.getScaledInstance(imgLbl.getWidth(), imgLbl.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(dimg);
            imgLbl.setIcon(imageIcon);
          }
        }
      }
    });
    mainJFrame.add(bookLst);

    // Initialize close button
    closeBtn = new JButton("Close");
    closeBtn.setBounds(778, 500, 73, 20);

    // If clicked, exit program
    closeBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });
    mainJFrame.add(closeBtn);

    // Initialize delete button
    delBtn = new JButton("Delete");
    delBtn.setBounds(675, 500, 73, 20);

    // ActionListener for delete button click
    delBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

        // Prevents any rented dogs from being deleted, would probably break program if
        // allowed
        if (!bookLst.isSelectionEmpty()) {
          
            String bookName = titleFld.getText();

            // Make user confirm they want to delete selected dog, save button choice in
            // selection
            int selection = JOptionPane.showConfirmDialog(mainJFrame,
                ("Are you sure you want you want to delete " + titleFld.getText() + "?"), "Confirm Deletion",
                JOptionPane.OK_CANCEL_OPTION);

            // If the OK button was clicked
            if (selection == JOptionPane.OK_OPTION) {
              try {
                BookFile.DeleteBook(tempBooks.elementAt(bookLst.getSelectedIndex()), cwid);
                tempBooks.removeElementAt(bookLst.getSelectedIndex());
                bookLst.clearSelection();
                JOptionPane.showMessageDialog(mainJFrame, (bookName + " was successfully deleted!"), "Success!",
                    JOptionPane.INFORMATION_MESSAGE);
              } catch (Exception a) {
                JOptionPane.showMessageDialog(mainJFrame, "Somethings broken", "IOException",
                    JOptionPane.ERROR_MESSAGE);
              }
            }
          
        } else {
          // If no dog is selected from dogLst, show error message
          JOptionPane.showMessageDialog(mainJFrame, "Please select a dog from the list to delete!", "Error",
              JOptionPane.ERROR_MESSAGE);
        }
      }
    });
    mainJFrame.add(delBtn);

    // Initialize edit button and set layout on MainFrame
    editBtn = new JButton("Edit");
    editBtn.setBounds(572, 500, 73, 20);

    // ActionListener for edit button
    /* editBtn.addActionListener(new ActionListener() {
      Book tempBook = new Book();

      @Override
      public void actionPerformed(ActionEvent e) {
        // First make sure that a dog is selected from the list
        if (!bookLst.isSelectionEmpty()) {
          
            NewController NewController = new NewController();

            // Call createAndDisplayEditGUI, sets layout and functionality of edit
            // JOptionPane
            // Once done editing, create new Dog object with updated attributes and store
            // it/
            // in tempDog
            tempBook = NewController.createAndDisplayEditGUI(isbnFld.getText(), titleFld.getText(), authorFld.getText(),
                genreFld.getSelectedIndex(), copiesFld.getText(), lengthFld.getText(), tempDogs.get(dogLst.getSelectedIndex()).getCover());

            try {
              // Set dog to be edited in tempDogs list equal to edited dog from NewController
              // and clear list selection so dogList is updated
              tempBooks.set(dogLst.getSelectedIndex(), Dog.editDog(myDogs, tempDog.getId(), tempDog.getName(),
                  tempDog.getBreed(), tempDog.getSex(), tempDog.getAge(), tempDog.getWeight(), tempDog.getImgPath()));
              dogLst.clearSelection();
              JOptionPane.showMessageDialog(mainJFrame, (tempDog.getName() + " was successfully edited!"), "Success!",
                  JOptionPane.INFORMATION_MESSAGE);

            } catch (IOException a) {
              JOptionPane.showMessageDialog(mainJFrame, "Somethings Broken", "Error", JOptionPane.ERROR_MESSAGE);
            }
          }

        } else {
          // If no dog is selected from dogLst, show error message
          JOptionPane.showMessageDialog(mainJFrame, "Please select a dog from the list to edit!", "Error",
              JOptionPane.ERROR_MESSAGE);
        }
      }

    });
 */
    mainJFrame.add(editBtn);

    // Initialize newBtn and set layout
    newBtn = new JButton("New");
    newBtn.setBounds(469, 500, 73, 20);
    newBtn.addActionListener(new ActionListener() {
      Book tempBook = null;

      @Override
      public void actionPerformed(ActionEvent e) {
        // Create NewController object
        NewController NewController = new NewController();
        // Set layout and functionality of New Dog JOptionPane and display Pane
        tempBook = NewController.createAndDisplayNewGUI();

        try {
          // If new dog was successfully added in createAndDisplayNewGui(), tempDog will
          // not equal
          // null and can now be added to tempDogs list
          if (tempBook != null) {
            // Add newly created Dog to tempDogs, dogLst automatically updated
            BookFile.SaveBook(tempBook, cwid, "");
            tempBooks.addElement(tempBook);
            bookLst.clearSelection();
            JOptionPane.showMessageDialog(mainJFrame, (tempBook.getTitle() + " was successfully added!"), "Success!",
                JOptionPane.INFORMATION_MESSAGE);
          }
        } catch (Exception a) {
          JOptionPane.showMessageDialog(mainJFrame, "Somethings Broken", "Error", JOptionPane.ERROR_MESSAGE);
        }
      }
    });
 
    mainJFrame.add(newBtn);

    // Initialize rent button and set layout
    rntBtn = new JButton("Rent");
    rntBtn.setBounds(778, 420, 73, 52);
    //rntBtn.addActionListener(new ActionListener() {
      //Dog tempDog = null;

      /* @Override
      public void actionPerformed(ActionEvent e) {
        // Make sure that a dog is selected from dogLst
        if (!dogLst.isSelectionEmpty()) {
          // Make sure that selected dog is not currently rented
          if (!tempDogs.elementAt(dogLst.getSelectedIndex()).isRented()) {
            // Prompt user for email address with JOptionPane (customer "primary key")
            // and store in string
            String email = JOptionPane.showInputDialog(mainJFrame, "Please enter your email address", "Rent a dog",
                JOptionPane.INFORMATION_MESSAGE);
            try {
              // Call rentDog function, which adds rented dog to Customer object and writes to
              // dog and customer text files
              tempDog = Customer.rentDog(myCustomers, myDogs, email, tempDogs.elementAt(dogLst.getSelectedIndex()));
              // If rentDog was successful, will return not null dog
              if (tempDog != null) {
                // Update arrays and tempDogs list and clear dogLst selection
                update();
                dogLst.clearSelection();
                JOptionPane.showMessageDialog(mainJFrame, (tempDog.getName() + " was successfully rented!"), "Success!",
                    JOptionPane.INFORMATION_MESSAGE);
                // Show error message if email entered already has a dog rented
              } else {
                JOptionPane.showMessageDialog(mainJFrame, ("You already have a dog rented!"), "Error",
                    JOptionPane.ERROR_MESSAGE);
              }
            } catch (IOException a) {
              JOptionPane.showMessageDialog(mainJFrame, "Somethings broken", "Error", JOptionPane.ERROR_MESSAGE);
            }
            // Show error message if selected dog is already rented
          } else {
            JOptionPane.showMessageDialog(mainJFrame, (nameFld.getText() + " is already rented!"), "Error",
                JOptionPane.ERROR_MESSAGE);
          }
          // Show error message if no dog was selected from dogLst
        } else {
          JOptionPane.showMessageDialog(mainJFrame, "Please select a dog from the list to rent!", "Error",
              JOptionPane.ERROR_MESSAGE);
        }
      }
    }); */
    mainJFrame.add(rntBtn);

    // Initialize and set layout for rent button
    returnBtn = new JButton("Return");
    returnBtn.setBounds(675, 420, 73, 52);
    /* returnBtn.addActionListener(new ActionListener() {
      boolean flag = false;

      @Override
      public void actionPerformed(ActionEvent e) {
        // Prompt user to enter email address and store in string
        String email = JOptionPane.showInputDialog(mainJFrame, "Please enter your email address", "Return a dog",
            JOptionPane.INFORMATION_MESSAGE);

        try {
          // if customer currently has a dog rented, flag will be set to true, else flag
          // is false
          flag = Customer.returnDog(myCustomers, myDogs, email);
          if (flag) {
            // If returnDog was successful, update arrays and tempDogs list and
            // clear dogLst selection
            update();
            dogLst.clearSelection();
            JOptionPane.showMessageDialog(mainJFrame, ("Return successful!"), "Success!",
                JOptionPane.INFORMATION_MESSAGE);
            // Show error if user tries to return dog when they don't have one currently
            // rented
          } else {
            JOptionPane.showMessageDialog(mainJFrame, "You don't have any dogs rented!", "You fucked up",
                JOptionPane.ERROR_MESSAGE);
          }
        } catch (IOException a) {
          JOptionPane.showMessageDialog(mainJFrame, "Somethings broken", "Error", JOptionPane.ERROR_MESSAGE);
        }
      }
    }); */
    mainJFrame.add(returnBtn);

    // Initialize text boxes and set layouts
    isbnFld = new JTextField();
    isbnFld.setBounds(380, 69, 200, 20);
    isbnFld.setEditable(false);
    mainJFrame.add(isbnFld);

    titleFld = new JTextField();
    titleFld.setBounds(380, 134, 200, 20);
    titleFld.setEditable(false);
    mainJFrame.add(titleFld);

    authorFld = new JTextField();
    authorFld.setBounds(380, 199, 200, 20);
    authorFld.setEditable(false);
    mainJFrame.add(authorFld);

    genreFld = new JTextField();
    genreFld.setBounds(380, 264, 200, 20);
    genreFld.setEnabled(false);
    mainJFrame.add(genreFld);

    copiesFld = new JTextField();
    copiesFld.setBounds(380, 329, 200, 20);
    copiesFld.setEditable(false);
    mainJFrame.add(copiesFld);

    lengthFld = new JTextField();
    lengthFld.setBounds(380, 394, 200, 20);
    lengthFld.setEditable(false);
    mainJFrame.add(lengthFld);

    // Initialize text box labels and set layout
    isbnLbl = new JLabel("ISBN");
    isbnLbl.setBounds(388, 49, 60, 20);
    mainJFrame.add(isbnLbl);

    authorLbl = new JLabel("Author");
    authorLbl.setBounds(388, 244, 120, 20);
    mainJFrame.add(authorLbl);

    genreLbl = new JLabel("Genre");
    genreLbl.setBounds(388, 179, 60, 20);
    mainJFrame.add(genreLbl);

    titleLbl = new JLabel("Title");
    titleLbl.setBounds(388, 114, 60, 20);
    mainJFrame.add(titleLbl);

    copiesLbl = new JLabel("Copies");
    copiesLbl.setBounds(388, 309, 60, 20);
    mainJFrame.add(copiesLbl);

    lengthLbl = new JLabel("Length");
    lengthLbl.setBounds(388, 374, 60, 20);
    mainJFrame.add(lengthLbl);

    // Finally set MainJFrame size, default close (when x button is clicked), set
    // layout to null
    // and make it visible
    mainJFrame.setSize(860, 550);
    mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainJFrame.setLayout(null);
    mainJFrame.setVisible(true);
  }

  // Helper function to clear all text fields when no Dog is selected from dogLst
  private void clearTextFields() {
    isbnFld.setText("");
    titleFld.setText("");
    authorFld.setText("");
    genreFld.setText("");
    copiesFld.setText("");
    lengthFld.setText("");
    
  }

  // Helper function to update all arrays/lists, pulls info from text files, then
  // add non deleted
  // dogs to tempDogs list from myDogs array
  /* private void update() throws IOException {
    myDogs = DogFile.getAllDogs();
    myCustomers = CustomerFile.getAllCustomers();
    tempDogs = new DefaultListModel<>();

    for (int i = 0; i < Dog.getCount(); ++i) {
      if (!myDogs[i].isDeleted()) {
        tempDogs.addElement(myDogs[i]);
      }
    }
  } */
}

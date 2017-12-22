package lt.Sinomoro.Indexer.GUI;

import lt.Sinomoro.Indexer.Utility.Data;
import lt.Sinomoro.Indexer.Utility.DataHolder;
import lt.Sinomoro.Indexer.Utility.PostitParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class Gui {

    private static JList<Data>list1 = new JList<>(new Data[]{new Data(true ),new Data(true ),new Data(true ),new Data(true ),new Data(true ),new Data(true ),new Data(true ),new Data(true ),new Data(true ), new Data(true)});
    private static JList<Data>list2 = new JList<>(new Data[]{new Data(true ),new Data(true ),new Data(true ),new Data(true ),new Data(true ),new Data(true ),new Data(true ),new Data(true), new Data(true)});

    private static AddressField field1 = new AddressField();
    private static AddressField field2 = new AddressField();


    public static void getGUI()
    {
        JFrame frame = new JFrame("Indexer, Now with Swing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container pane = frame.getContentPane();
        GroupLayout layout = new GroupLayout(pane);

        pane.setLayout(layout);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(field1.getPanel())
                                .addComponent(list1))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(field2.getPanel())
                                .addComponent(list2))
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(field1.getPanel())
                                .addComponent(field2.getPanel()))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(list1)
                                .addComponent(list2))
        );

        frame.pack();
        frame.setVisible(true);
    }

    static class AddressField{
        private JTextField cityField;
        private JTextField streetField;
        private JTextField numberField;

        private JPanel contentPane;

        AddressField()
        {
            contentPane = new  JPanel();
            GroupLayout layout = new GroupLayout(contentPane);
            layout.setAutoCreateGaps(true);
            layout.setAutoCreateContainerGaps(true);


            JLabel cityLabel = new JLabel("Miestas");
            JLabel streetLabel = new JLabel("Gatve");
            JLabel numberLabel = new JLabel("Numeris");

            cityField = new JTextField(15);
            streetField= new JTextField(15);
            numberField= new JTextField(15);

            cityField.addKeyListener(myKeyListener());
            streetField.addKeyListener(myKeyListener());
            numberField.addKeyListener(myKeyListener());

            cityField.addFocusListener(myFocusListener());
            streetField.addFocusListener(myFocusListener());
            numberField.addFocusListener(myFocusListener());

            layout.setHorizontalGroup(
                    layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(cityLabel)
                                    .addComponent(streetLabel)
                                    .addComponent(numberLabel))
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(cityField)
                                    .addComponent(streetField)
                                    .addComponent(numberField))
            );
            layout.setVerticalGroup(
                    layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(cityLabel)
                                    .addComponent(cityField))
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(streetLabel)
                                    .addComponent(streetField))
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(numberLabel)
                                    .addComponent(numberField))
            );
            contentPane.setLayout(layout);
        }

        JPanel getPanel() {
            return contentPane;
        }

        private String getAddress()
        {
            return streetField.getText() +" "+ numberField.getText() + " " + cityField.getText();
        }

        public boolean hasEmptyFields()
        {
            return (cityField.getText().equals("")||numberField.getText().equals("")||streetField.getText().equals(""));
        }
    }

    static void iNeedToDoThis()
    {
        SwingWorker worker = new SwingWorker() {

            DataHolder data;

            @Override
            protected Object doInBackground() {
                Data[] temp;
                if(!field1.hasEmptyFields())
                {
                    try {
                        data = PostitParser.getData(field1.getAddress());
                    } catch (URISyntaxException | MalformedURLException e) {
                        e.printStackTrace();
                    }
                    list1.removeAll();
                    temp = new Data[data.getData().size()];
                    temp = data.getData().toArray(temp);
                    list1.setListData(temp);
                }
                if(!field2.hasEmptyFields())
                {
                    try {
                        data = PostitParser.getData(field2.getAddress());
                    } catch (URISyntaxException | MalformedURLException e) {
                        e.printStackTrace();
                    }
                    list2.removeAll();
                    temp = new Data[data.getData().size()];
                    temp = data.getData().toArray(temp);
                    list2.setListData(temp);
                }
                return null;
            }

            @Override
            public void done() {

            }
        };
        worker.execute();
    }

    static KeyListener myKeyListener()
    {
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                   iNeedToDoThis();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
    }

    static FocusListener myFocusListener()
    {
        return new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                iNeedToDoThis();
            }
        };
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;

public class MilSymGuiActions
{
   StringBuilder MilSymString =  new StringBuilder("S---------------" );



    public MilSymGuiActions()
    {




        /* set up the GUI items */
        // frame.getContentPane().setLayout(new FlowLayout());
        /* set up default UI fonts */
        setUIFont (new javax.swing.plaf.FontUIResource("Serif", Font.PLAIN,30));

        JFrame frame = new JFrame("World Wind");
        frame.setDefaultLookAndFeelDecorated(true);


       /*
        https://docs.oracle.com/javase/tutorial/uiswing/components/menu.html
        */
        JMenuBar menuBar;
        JMenu  menu,submenu,aboutMenu,nodeMenu;
        JMenuItem menuItem;
        JRadioButtonMenuItem rbMenuItem, dDSNodeMenuItem, pubMenuItem, logMenuItem;
        JRadioButtonMenuItem  dDSMetMenuItem, stopPubMenuItem, stopLogMenuItem;

        menuBar = new JMenuBar();
        menu = new JMenu("Options");
        menuBar.add(menu);

        menuItem = new JMenuItem("");
        menu.add(menuItem);


        //a group of radio button menu items
        menu.addSeparator();

        rbMenuItem = new JRadioButtonMenuItem("Enable Logging");
        rbMenuItem.setSelected(false);

        menu.add(rbMenuItem);
        //a group of JMenuItems
        //a group of radio box menu items
        menu.addSeparator();
        ButtonGroup dDSGroup = new ButtonGroup();
        dDSNodeMenuItem = new JRadioButtonMenuItem("Send Node information/Receive Metrics");
        dDSGroup.add(dDSNodeMenuItem);
        dDSNodeMenuItem.setSelected(true);
        menu.add(dDSNodeMenuItem);

        dDSMetMenuItem = new JRadioButtonMenuItem("Receive Node Information/Send Metrics");
        dDSGroup.add(dDSMetMenuItem);
        menu.add(dDSMetMenuItem);

        /*start and stop publishing */
        menu.addSeparator();
        ButtonGroup pubGroup = new ButtonGroup();
        pubMenuItem = new JRadioButtonMenuItem("Start Publishing DDS Messages");

        pubGroup.add(pubMenuItem);
        pubMenuItem.setSelected(false);
        menu.add(pubMenuItem);

        stopPubMenuItem = new JRadioButtonMenuItem("Stop Publishing DDS Messages");
        pubGroup.add(stopPubMenuItem);
        stopPubMenuItem.setSelected(true);
        menu.add(stopPubMenuItem);
        /*start and stop logging */
        menu.addSeparator();
        ButtonGroup logGroup = new ButtonGroup();
        logMenuItem = new JRadioButtonMenuItem("Start Logging Positions");
        logGroup.add(logMenuItem);
        logMenuItem.setSelected(false);
        menu.add(logMenuItem);

        stopLogMenuItem = new JRadioButtonMenuItem("Stop Logging Positions");
        logGroup.add(stopLogMenuItem);
        stopLogMenuItem.setSelected(true);
        menu.add(stopLogMenuItem);

        //Build second menu in the menu bar.
        aboutMenu = new JMenu("About");
        menuItem = new JMenuItem("DST - LD - SITN");
        aboutMenu.add(menuItem);
        menuBar.add(aboutMenu);

        nodeMenu = new JMenu("NODES");
        menuItem = new JMenuItem("Change Node symbols");
        nodeMenu.add(menuItem);

        menuBar.add(nodeMenu);



        frame.setJMenuBar (menuBar);
/* sert up the drop down lists*/
        String[] iFFStrings = { "Friend", "Hostile", "Neutral", "Null"};
        char[] iFFChars = { 'F', 'H', 'N','-'};

        JLabel affiliationLabel = new JLabel("Affiliation");
        affiliationLabel.setBounds(400, 100, 140, 40);
        frame.add(affiliationLabel);
        //Create the combo box, select item at index 4.
        //Indices start at 0, so 4 specifies the pig.
        JComboBox petList = new JComboBox(iFFStrings);
        petList.setSelectedIndex(3);
       petList.setBounds(400, 120, 140, 40);
        frame.add(petList);




        JTextField JT = new JTextField("");


        JT.setText(MilSymString.toString());
        frame.add(JT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* add the worldwind canvas to the JFrame */


        frame.setSize(2000, 2000);
        frame.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension currentDim = frame.getSize();
                JT.setBounds(100,300,(int)currentDim.width/3, (int) currentDim.height/4);

            }

            @Override
            public void componentMoved(ComponentEvent e) {

            }

            @Override
            public void componentShown(ComponentEvent e) {

            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });
        frame.setVisible(true);

/*
Set up the Gui Listeners
 */
        petList.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                MilSymString.setCharAt(1,iFFChars[petList.getSelectedIndex()]);
                JT.setText(MilSymString.toString());

            }
        });

        logMenuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {


            }
        });


        JT.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {

                System.out.println(MilSymString);

            }

        });
        ActionListener timerListener = new ActionListener()
        {

            public void actionPerformed(ActionEvent actionEvent)
            {


            }
        };
        Timer timer = new Timer(1000, timerListener);
        timer.start();
        ActionListener secondTimerListener = new ActionListener()
        {

            public void actionPerformed(ActionEvent actionEvent)
            {



            }
        };
        Timer secondTimer = new Timer(3000, secondTimerListener);
        secondTimer.start();


    }

    public static void setUIFont (javax.swing.plaf.FontUIResource f){
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get (key);
            if (value instanceof javax.swing.plaf.FontUIResource)
                UIManager.put (key, f);
        }
    }
}


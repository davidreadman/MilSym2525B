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
   String MilSymString =  "S---------------" ;
   Sting MilArray =


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
        JMenu  menu,submenu,aboutMenu;
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

        frame.setJMenuBar (menuBar);
/* sert up the drop down lists*/
        String[] iFFStrings = { "Friend", "Hostile", "Neutral", "Null"};
       char[] iFFChars = { 'F', 'H', 'N','-'};

        //Create the combo box, select item at index 4.
        //Indices start at 0, so 4 specifies the pig.
        JComboBox petList = new JComboBox(iFFStrings);
        petList.setSelectedIndex(2);
       petList.setBounds(400, 120, 140, 40);
        frame.add(petList);



        JButton debugButton = new JButton("debug", new ImageIcon("debug.png"));
        debugButton.setBounds(400, 120, 140, 40);
        // frame.add(cloButton);



        JTextField JT = new JTextField("");

        JT.setBounds(100, 300, 500, 1000);
       // JT.setBackground(new Color(0,0,0,200));
       // JT.setOpaque(false);

        JT.setText(Arrays.toString(MilSymString));
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
                MilSymString[1]=iFFChars[petList.getSelectedIndex()];
                JT.setText(Arrays.toString(MilSymString));

            }
        });

        logMenuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {


            }
        });

        debugButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {

                System.out.println("canvas node 1");

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


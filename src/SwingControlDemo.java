import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class SwingControlDemo implements ActionListener {
    private JFrame mainFrame;
    private JLabel statusLabel;
    private JPanel controlPanel;
    private JMenuBar mb;
    private JMenu file, edit, help;
    private JMenuItem cut, copy, paste, selectAll;
    private JTextArea ta; //typing area
    private JTextArea te; //typing area

    private JTextArea ti; //typing area
    private int WIDTH=800;
    private int HEIGHT=700;


    public SwingControlDemo() {
        prepareGUI();
    }

    public static void main(String[] args) {
        SwingControlDemo swingControlDemo = new SwingControlDemo();
        swingControlDemo.showEventDemo();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Java SWING Examples");
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setLayout(new GridLayout(2, 1));

        //menu at top
        cut = new JMenuItem("cut");
        copy = new JMenuItem("copy");
        paste = new JMenuItem("paste");
        selectAll = new JMenuItem("selectAll");
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);

        mb = new JMenuBar();
        file = new JMenu("File");
        edit = new JMenu("Edit");
        help = new JMenu("Help");
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        mb.add(file);
        mb.add(edit);
        mb.add(help);
        //end menu at top

        ta = new JTextArea("url");
        ti = new JTextArea("Resutlts");
        ta.setBounds(50, 5, WIDTH-100, HEIGHT-50);
        ti.setBounds(50, 5, WIDTH-100, HEIGHT-50);
        te = new JTextArea("keyword");
        te.setBounds(50, 5, WIDTH-100, HEIGHT-50);
        mainFrame.add(mb);  //add menu bar
        mainFrame.add(ta);//add typing area
        mainFrame.add(ti);//add typing area
        mainFrame.add(te);//add typing area
        mainFrame.setJMenuBar(mb); //set menu bar

        statusLabel = new JLabel("", JLabel.CENTER);
        statusLabel.setSize(350, 100);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout()); //set the layout of the pannel

        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }

    private void showEventDemo() {

        JButton Date = new JButton("Date");
        JButton URL = new JButton("URL");
        JButton keyword = new JButton("keyword");
        JButton Go = new JButton("Go");
        JButton Results = new JButton("Results");



        Date.setActionCommand("Date");
        URL.setActionCommand("URL");
        keyword.setActionCommand("keyword");
        Go.setActionCommand("Go");
        Results.setActionCommand("Results");


        Date.addActionListener(new ButtonClickListener());
        URL.addActionListener(new ButtonClickListener());
        keyword.addActionListener(new ButtonClickListener());
        Go.addActionListener(new ButtonClickListener());
        Results.addActionListener(new ButtonClickListener());

        controlPanel.add(Date);
        controlPanel.add(URL);
        controlPanel.add(keyword);
        controlPanel.add(Go);
        controlPanel.add(Results);




        mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cut)
            ta.cut();
        if (e.getSource() == paste)
            ta.paste();
        if (e.getSource() == copy)
            ta.copy();
        if (e.getSource() == selectAll)
            ta.selectAll();
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
if(e.getActionCommand().equals("Go")) {
    try {

        String link = ta.getText();
        String keyword = te.getText();
        System.out.println();
        System.out.print("hello \n");
        URL url = new URL(link);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(url.openStream())
        );
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.contains("href=")) {
                int indexSpot = line.indexOf("href=");
//                System.out.println("og"+line.substring(indexSpot+6));
                String html = line.substring(indexSpot + 6);
                int htmlspot = html.indexOf("\"");
                int htmlspot2 = html.indexOf("'");

//                 System.out.println(htmlspot);
//                 System.out.println(htmlspot2);
                if (htmlspot < 0) {
                    if (html.substring(0, htmlspot2).contains(keyword)) {
                        System.out.println(html.substring(0, htmlspot2));
                        ti.append(html.substring(0, htmlspot2)+"\n");
                    }


                } else if (htmlspot2 < 0) {
                    if (html.substring(0, htmlspot).contains(keyword)) {
                        System.out.println(html.substring(0, htmlspot));
                        ti.append(html.substring(0, htmlspot)+"\n");

                    }

                } else if (htmlspot > htmlspot2) {
                    if (html.substring(0, htmlspot2).contains(keyword)) {
                        System.out.println(html.substring(0, htmlspot2));
                        ti.append(html.substring(0, htmlspot2)+"\n");
                    }

                } else {
                    if (html.substring(0, htmlspot).contains(keyword)) {
                        System.out.println(html.substring(0, htmlspot));
                        ti.append(html.substring(0, htmlspot)+"\n");


                    }


                }


            }


        }
        reader.close();

    } catch (Exception ex) {
        System.out.println(ex);
    }
}
//            String command = e.getActionCommand();
//
//            if (command.equals("Date")) {
//                statusLabel.setText("URL");
//            } else if (command.equals("keyword")) {
//                statusLabel.setText("GO.");
//            } else if (command.equals("Cancel")) {
//                statusLabel.setText("Cancel Button clicked.");
//            } else if (command.equals("startbutton")){
//                statusLabel.setText("Start Button clicked.");
//
//            }
//            else{
//                statusLabel.setText("No Button CLicked");
//            }

        }
    }
}

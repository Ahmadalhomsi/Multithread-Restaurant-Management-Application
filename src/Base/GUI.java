/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Base;

import OBJ.Asci;
import OBJ.Masa;
import OBJ.Musteri;
import java.awt.Color;
import java.util.List;
import javax.swing.JLabel;

/**
 *
 * @author xandros
 */
public class GUI extends javax.swing.JFrame {

    /**
     * Creates new form GUI
     */
    public GUI() {
        initComponents();
    }

    public void StepSetter(int step) {
        LabelX.setText("Step " + (step+1));
    }

    public void tablesStatusUpdater(List<Musteri> musteriList) {
        // Print the table grid
        String status = "Null";
        int masaIndex = 1;
        for (int i = 1; i <= 6; i++) {

            Color c = Color.black;
            if ((i - 1) < musteriList.size()) {
                status = "Full";
                c = Color.red;
            } else {
                status = "Full";
            }

            switch (masaIndex) {
                case 1 -> {
                    Masa1StatusLabel.setText(status);
                    Masa1StatusLabel.setForeground(c);
                }
                case 2 -> {
                    Masa2StatusLabel.setText(status);
                    Masa2StatusLabel.setForeground(c);
                }
                case 3 -> {
                    Masa3StatusLabel.setText(status);
                    Masa3StatusLabel.setForeground(c);
                }
                case 4 -> {
                    Masa4StatusLabel.setText(status);
                    Masa4StatusLabel.setForeground(c);
                }
                case 5 -> {
                    Masa5StatusLabel.setText(status);
                    Masa5StatusLabel.setForeground(c);
                }
                case 6 -> {
                    Masa6StatusLabel.setText(status);
                    Masa6StatusLabel.setForeground(c);
                }
                default -> {
                }
            }
            masaIndex++;

        }
        this.repaint();
    }

    public void tablesStatusRemover(List<Musteri> musteriList) {
        // Print the table grid
        String status = "Null";
        int masaIndex = 1;
        for (int i = 1; i <= 6; i++) {

            Color c = Color.black;
            status = "Null";

            switch (masaIndex) {
                case 1 -> {
                    Masa1StatusLabel.setText(status);
                    Masa1StatusLabel.setForeground(c);
                }
                case 2 -> {
                    Masa2StatusLabel.setText(status);
                    Masa2StatusLabel.setForeground(c);
                }
                case 3 -> {
                    Masa3StatusLabel.setText(status);
                    Masa3StatusLabel.setForeground(c);
                }
                case 4 -> {
                    Masa4StatusLabel.setText(status);
                    Masa4StatusLabel.setForeground(c);
                }
                case 5 -> {
                    Masa5StatusLabel.setText(status);
                    Masa5StatusLabel.setForeground(c);
                }
                case 6 -> {
                    Masa6StatusLabel.setText(status);
                    Masa6StatusLabel.setForeground(c);
                }
                default -> {
                }
            }
            masaIndex++;

        }
        this.repaint();
    }

    public void SpecificTableActUpdater(Musteri musteri, String status) {
        int masaIndex = musteri.getIndex();

        Color c = Color.BLACK;

        if (status.equals("Eating")) {
            c = Color.GREEN;
        } else if (status.equals("Waiting the garson")) {
            c = Color.ORANGE;
        } else if (status.equals("Waiting the order")) {
            c = Color.MAGENTA;
        } else if (status.equals("Left")) {
            c = Color.RED;
        }

        switch (masaIndex) {
            case 1 -> {
                Masa1ActLabel.setText(status);
                Masa1ActLabel.setForeground(c);
            }
            case 2 -> {
                Masa2ActLabel.setText(status);
                Masa2ActLabel.setForeground(c);
            }
            case 3 -> {
                Masa3ActLabel.setText(status);
                Masa3ActLabel.setForeground(c);
            }
            case 4 -> {
                Masa4ActLabel.setText(status);
                Masa4ActLabel.setForeground(c);
            }
            case 5 -> {
                Masa5ActLabel.setText(status);
                Masa5ActLabel.setForeground(c);
            }
            case 6 -> {
                Masa6ActLabel.setText(status);
                Masa6ActLabel.setForeground(c);
            }
            default -> {
            }
        }
        this.repaint();
    }

    public void tablesMusteriNameUpdater(Musteri musteri) {
        int masaIndex = Integer.parseInt(musteri.getNameX());
        String musteriName = "Customer " + musteri.getNameX();

        switch (masaIndex) {
            case 1 -> {
                Masa1MusteriNameLabel.setText(musteriName);
            }
            case 2 -> {
                Masa2MusteriNameLabel.setText(musteriName);
            }
            case 3 -> {
                Masa3MusteriNameLabel.setText(musteriName);
            }
            case 4 -> {
                Masa4MusteriNameLabel.setText(musteriName);
            }
            case 5 -> {
                Masa5MusteriNameLabel.setText(musteriName);
            }
            case 6 -> {
                Masa6MusteriNameLabel.setText(musteriName);
            }
            default -> {
            }
        }
        this.repaint();
    }

    public void kazancNumberUpdater(int k) {
        KazancNumberLabel.setText(k + "");
        this.repaint();
    }

    public synchronized void AscilarUpdate(Asci asci, String durum) {
        if ((!durum.equals("Null")) || (asci.getReady()==2) ) {
            asci.setReady(asci.getReady() - 1);
        }

        int asciIndex = asci.getIndex();
        int yemek = asci.getReady();
        
        System.out.println("Yemek: " + yemek + " " + durum + " Asci: " + asciIndex);
        if (asciIndex == 1) {
            if ( (yemek == 1) || (yemek == 3) ) { // 1 yemek
                Asci1Yemek1Label.setText(durum);
            } else if ( (yemek == 0) || (yemek == 2) ) { // 2 yemek
                Asci1Yemek2Label.setText(durum);
            }

        } else if (asciIndex == 2) {
            if ((yemek == 1) || (yemek == 3)) { // 1 yemek
                Asci2Yemek1Label.setText(durum);
            } else if ( (yemek == 0) || (yemek == 2) ) { // 2 yemek
                Asci2Yemek2Label.setText(durum);
            }
        }

        if (durum.equals("Null")) {
            asci.setReady(asci.getReady() + 1);
        }

        this.repaint();
    }

    public void GarsonlarUpdate(int garson, String durum) {

        if (garson == 1) {
            Garson1StatusLabel.setText(durum);
        } else if (garson == 2) {
            Garson2StatusLabel.setText(durum);
        } else if (garson == 3) {
            Garson3StatusLabel.setText(durum);
        }
        this.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        GarsonsLabel = new javax.swing.JLabel();
        Masa1MusteriNameLabel1 = new javax.swing.JLabel();
        Masa1MusteriNameLabel8 = new javax.swing.JLabel();
        Masa1MusteriNameLabel9 = new javax.swing.JLabel();
        Masa1MusteriNameLabel10 = new javax.swing.JLabel();
        Garson2StatusLabel = new javax.swing.JLabel();
        Garson1StatusLabel = new javax.swing.JLabel();
        Masa1MusteriNameLabel11 = new javax.swing.JLabel();
        Garson3StatusLabel = new javax.swing.JLabel();
        Masa1MusteriNameLabel12 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        ChefsLabel = new javax.swing.JLabel();
        Masa1MusteriNameLabel2 = new javax.swing.JLabel();
        Masa1MusteriNameLabel3 = new javax.swing.JLabel();
        Masa1MusteriNameLabel4 = new javax.swing.JLabel();
        Masa1MusteriNameLabel5 = new javax.swing.JLabel();
        Masa1MusteriNameLabel6 = new javax.swing.JLabel();
        Asci2Yemek2Label = new javax.swing.JLabel();
        Asci1Yemek1Label = new javax.swing.JLabel();
        Asci1Yemek2Label = new javax.swing.JLabel();
        Asci2Yemek1Label = new javax.swing.JLabel();
        Masa1MusteriNameLabel7 = new javax.swing.JLabel();
        MasalarPanel = new javax.swing.JPanel();
        GarsonsLabel1 = new javax.swing.JLabel();
        Masa1Panel = new javax.swing.JPanel();
        Masa1NameLabel = new javax.swing.JLabel();
        Masa1StatusLabel = new javax.swing.JLabel();
        Masa1ActLabel = new javax.swing.JLabel();
        Masa1MusteriNameLabel = new javax.swing.JLabel();
        Masa2Panel = new javax.swing.JPanel();
        Masa2NameLabel = new javax.swing.JLabel();
        Masa2StatusLabel = new javax.swing.JLabel();
        Masa2ActLabel = new javax.swing.JLabel();
        Masa2MusteriNameLabel = new javax.swing.JLabel();
        Masa3Panel = new javax.swing.JPanel();
        Masa3NameLabel = new javax.swing.JLabel();
        Masa3StatusLabel = new javax.swing.JLabel();
        Masa3ActLabel = new javax.swing.JLabel();
        Masa3MusteriNameLabel = new javax.swing.JLabel();
        Masa4Panel = new javax.swing.JPanel();
        Masa4NameLabel = new javax.swing.JLabel();
        Masa4StatusLabel = new javax.swing.JLabel();
        Masa4ActLabel = new javax.swing.JLabel();
        Masa4MusteriNameLabel = new javax.swing.JLabel();
        Masa5Panel = new javax.swing.JPanel();
        Masa5NameLabel = new javax.swing.JLabel();
        Masa5StatusLabel = new javax.swing.JLabel();
        Masa5ActLabel = new javax.swing.JLabel();
        Masa5MusteriNameLabel = new javax.swing.JLabel();
        Masa6Panel = new javax.swing.JPanel();
        Masa6NameLabel = new javax.swing.JLabel();
        Masa6StatusLabel = new javax.swing.JLabel();
        Masa6ActLabel = new javax.swing.JLabel();
        Masa6MusteriNameLabel = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        ChefsLabel1 = new javax.swing.JLabel();
        KazancLabel = new javax.swing.JLabel();
        KazancNumberLabel = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        NextButton = new javax.swing.JButton();
        LabelX = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(725, 565));

        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(51, 0, 0));
        jPanel2.setLayout(null);

        GarsonsLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        GarsonsLabel.setForeground(new java.awt.Color(255, 255, 255));
        GarsonsLabel.setText("Waiters:");
        jPanel2.add(GarsonsLabel);
        GarsonsLabel.setBounds(32, 16, 95, 25);

        Masa1MusteriNameLabel1.setForeground(new java.awt.Color(255, 255, 255));
        Masa1MusteriNameLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Masa1MusteriNameLabel1.setText("Status:");
        jPanel2.add(Masa1MusteriNameLabel1);
        Masa1MusteriNameLabel1.setBounds(0, 80, 80, 16);

        Masa1MusteriNameLabel8.setForeground(new java.awt.Color(255, 255, 255));
        Masa1MusteriNameLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Masa1MusteriNameLabel8.setText("Status:");
        jPanel2.add(Masa1MusteriNameLabel8);
        Masa1MusteriNameLabel8.setBounds(0, 140, 80, 16);

        Masa1MusteriNameLabel9.setForeground(new java.awt.Color(255, 255, 255));
        Masa1MusteriNameLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Masa1MusteriNameLabel9.setText("Waiter 2:");
        jPanel2.add(Masa1MusteriNameLabel9);
        Masa1MusteriNameLabel9.setBounds(40, 120, 80, 16);

        Masa1MusteriNameLabel10.setForeground(new java.awt.Color(255, 255, 255));
        Masa1MusteriNameLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Masa1MusteriNameLabel10.setText("Waiter 1:");
        jPanel2.add(Masa1MusteriNameLabel10);
        Masa1MusteriNameLabel10.setBounds(40, 60, 80, 16);

        Garson2StatusLabel.setForeground(new java.awt.Color(255, 255, 255));
        Garson2StatusLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Garson2StatusLabel.setText("Null");
        jPanel2.add(Garson2StatusLabel);
        Garson2StatusLabel.setBounds(70, 140, 80, 16);

        Garson1StatusLabel.setForeground(new java.awt.Color(255, 255, 255));
        Garson1StatusLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Garson1StatusLabel.setText("Null");
        jPanel2.add(Garson1StatusLabel);
        Garson1StatusLabel.setBounds(70, 80, 80, 16);

        Masa1MusteriNameLabel11.setForeground(new java.awt.Color(255, 255, 255));
        Masa1MusteriNameLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Masa1MusteriNameLabel11.setText("Status:");
        jPanel2.add(Masa1MusteriNameLabel11);
        Masa1MusteriNameLabel11.setBounds(0, 210, 80, 16);

        Garson3StatusLabel.setForeground(new java.awt.Color(255, 255, 255));
        Garson3StatusLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Garson3StatusLabel.setText("Null");
        jPanel2.add(Garson3StatusLabel);
        Garson3StatusLabel.setBounds(70, 210, 80, 16);

        Masa1MusteriNameLabel12.setForeground(new java.awt.Color(255, 255, 255));
        Masa1MusteriNameLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Masa1MusteriNameLabel12.setText("Waiter 3:");
        jPanel2.add(Masa1MusteriNameLabel12);
        Masa1MusteriNameLabel12.setBounds(40, 190, 80, 16);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 160, 290);

        jPanel3.setBackground(new java.awt.Color(0, 51, 51));
        jPanel3.setLayout(null);

        ChefsLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ChefsLabel.setForeground(new java.awt.Color(255, 255, 255));
        ChefsLabel.setText("Chefs:");
        jPanel3.add(ChefsLabel);
        ChefsLabel.setBounds(34, 14, 83, 25);

        Masa1MusteriNameLabel2.setForeground(new java.awt.Color(255, 255, 255));
        Masa1MusteriNameLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Masa1MusteriNameLabel2.setText("Chef 2:");
        jPanel3.add(Masa1MusteriNameLabel2);
        Masa1MusteriNameLabel2.setBounds(40, 110, 80, 16);

        Masa1MusteriNameLabel3.setForeground(new java.awt.Color(255, 255, 255));
        Masa1MusteriNameLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Masa1MusteriNameLabel3.setText("Meal 1:");
        jPanel3.add(Masa1MusteriNameLabel3);
        Masa1MusteriNameLabel3.setBounds(0, 70, 80, 16);

        Masa1MusteriNameLabel4.setForeground(new java.awt.Color(255, 255, 255));
        Masa1MusteriNameLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Masa1MusteriNameLabel4.setText("Meal 1:");
        jPanel3.add(Masa1MusteriNameLabel4);
        Masa1MusteriNameLabel4.setBounds(0, 130, 80, 16);

        Masa1MusteriNameLabel5.setForeground(new java.awt.Color(255, 255, 255));
        Masa1MusteriNameLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Masa1MusteriNameLabel5.setText("Meal 2:");
        jPanel3.add(Masa1MusteriNameLabel5);
        Masa1MusteriNameLabel5.setBounds(0, 90, 80, 16);

        Masa1MusteriNameLabel6.setForeground(new java.awt.Color(255, 255, 255));
        Masa1MusteriNameLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Masa1MusteriNameLabel6.setText("Meal 2:");
        jPanel3.add(Masa1MusteriNameLabel6);
        Masa1MusteriNameLabel6.setBounds(0, 150, 80, 16);

        Asci2Yemek2Label.setForeground(new java.awt.Color(255, 255, 255));
        Asci2Yemek2Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Asci2Yemek2Label.setText("Null");
        jPanel3.add(Asci2Yemek2Label);
        Asci2Yemek2Label.setBounds(70, 150, 80, 16);

        Asci1Yemek1Label.setForeground(new java.awt.Color(255, 255, 255));
        Asci1Yemek1Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Asci1Yemek1Label.setText("Null");
        jPanel3.add(Asci1Yemek1Label);
        Asci1Yemek1Label.setBounds(70, 70, 80, 16);

        Asci1Yemek2Label.setForeground(new java.awt.Color(255, 255, 255));
        Asci1Yemek2Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Asci1Yemek2Label.setText("Null");
        jPanel3.add(Asci1Yemek2Label);
        Asci1Yemek2Label.setBounds(70, 90, 80, 16);

        Asci2Yemek1Label.setForeground(new java.awt.Color(255, 255, 255));
        Asci2Yemek1Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Asci2Yemek1Label.setText("Null");
        jPanel3.add(Asci2Yemek1Label);
        Asci2Yemek1Label.setBounds(70, 130, 80, 16);

        Masa1MusteriNameLabel7.setForeground(new java.awt.Color(255, 255, 255));
        Masa1MusteriNameLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Masa1MusteriNameLabel7.setText("Chef 1:");
        jPanel3.add(Masa1MusteriNameLabel7);
        Masa1MusteriNameLabel7.setBounds(40, 50, 80, 16);

        jPanel1.add(jPanel3);
        jPanel3.setBounds(0, 290, 160, 180);

        MasalarPanel.setBackground(new java.awt.Color(102, 51, 0));
        MasalarPanel.setLayout(null);

        GarsonsLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        GarsonsLabel1.setForeground(new java.awt.Color(255, 255, 255));
        GarsonsLabel1.setText("Tables:");
        MasalarPanel.add(GarsonsLabel1);
        GarsonsLabel1.setBounds(190, 20, 160, 30);

        Masa1Panel.setLayout(null);

        Masa1NameLabel.setText("Table 1");
        Masa1Panel.add(Masa1NameLabel);
        Masa1NameLabel.setBounds(70, 10, 50, 16);

        Masa1StatusLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Masa1StatusLabel.setText("Null");
        Masa1Panel.add(Masa1StatusLabel);
        Masa1StatusLabel.setBounds(70, 50, 37, 16);

        Masa1ActLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Masa1ActLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Masa1ActLabel.setText("Status");
        Masa1Panel.add(Masa1ActLabel);
        Masa1ActLabel.setBounds(0, 70, 170, 16);

        Masa1MusteriNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Masa1MusteriNameLabel.setText("Customer name");
        Masa1Panel.add(Masa1MusteriNameLabel);
        Masa1MusteriNameLabel.setBounds(47, 30, 110, 16);

        MasalarPanel.add(Masa1Panel);
        Masa1Panel.setBounds(0, 70, 170, 120);

        Masa2Panel.setLayout(null);

        Masa2NameLabel.setText("Table 2");
        Masa2Panel.add(Masa2NameLabel);
        Masa2NameLabel.setBounds(70, 10, 50, 16);

        Masa2StatusLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Masa2StatusLabel.setText("Null");
        Masa2Panel.add(Masa2StatusLabel);
        Masa2StatusLabel.setBounds(70, 50, 37, 16);

        Masa2ActLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Masa2ActLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Masa2ActLabel.setText("Status");
        Masa2Panel.add(Masa2ActLabel);
        Masa2ActLabel.setBounds(10, 70, 158, 16);

        Masa2MusteriNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Masa2MusteriNameLabel.setText("Customer name");
        Masa2Panel.add(Masa2MusteriNameLabel);
        Masa2MusteriNameLabel.setBounds(47, 30, 110, 16);

        MasalarPanel.add(Masa2Panel);
        Masa2Panel.setBounds(180, 70, 170, 120);

        Masa3Panel.setLayout(null);

        Masa3NameLabel.setText("Table 3");
        Masa3Panel.add(Masa3NameLabel);
        Masa3NameLabel.setBounds(67, 6, 50, 16);

        Masa3StatusLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Masa3StatusLabel.setText("Null");
        Masa3Panel.add(Masa3StatusLabel);
        Masa3StatusLabel.setBounds(70, 50, 37, 16);

        Masa3ActLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Masa3ActLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Masa3ActLabel.setText("Status");
        Masa3Panel.add(Masa3ActLabel);
        Masa3ActLabel.setBounds(10, 70, 158, 16);

        Masa3MusteriNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Masa3MusteriNameLabel.setText("Customer name");
        Masa3Panel.add(Masa3MusteriNameLabel);
        Masa3MusteriNameLabel.setBounds(47, 30, 110, 16);

        MasalarPanel.add(Masa3Panel);
        Masa3Panel.setBounds(360, 70, 170, 120);

        Masa4Panel.setLayout(null);

        Masa4NameLabel.setText("Table 4");
        Masa4Panel.add(Masa4NameLabel);
        Masa4NameLabel.setBounds(67, 6, 50, 16);

        Masa4StatusLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Masa4StatusLabel.setText("Null");
        Masa4Panel.add(Masa4StatusLabel);
        Masa4StatusLabel.setBounds(70, 50, 37, 16);

        Masa4ActLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Masa4ActLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Masa4ActLabel.setText("Status");
        Masa4Panel.add(Masa4ActLabel);
        Masa4ActLabel.setBounds(8, 70, 160, 16);

        Masa4MusteriNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Masa4MusteriNameLabel.setText("Customer name");
        Masa4Panel.add(Masa4MusteriNameLabel);
        Masa4MusteriNameLabel.setBounds(50, 30, 110, 16);

        MasalarPanel.add(Masa4Panel);
        Masa4Panel.setBounds(0, 200, 170, 120);

        Masa5Panel.setLayout(null);

        Masa5NameLabel.setText("Table 5");
        Masa5Panel.add(Masa5NameLabel);
        Masa5NameLabel.setBounds(67, 6, 50, 16);

        Masa5StatusLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Masa5StatusLabel.setText("Null");
        Masa5Panel.add(Masa5StatusLabel);
        Masa5StatusLabel.setBounds(70, 50, 37, 16);

        Masa5ActLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Masa5ActLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Masa5ActLabel.setText("Status");
        Masa5Panel.add(Masa5ActLabel);
        Masa5ActLabel.setBounds(8, 70, 160, 16);

        Masa5MusteriNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Masa5MusteriNameLabel.setText("Customer name");
        Masa5Panel.add(Masa5MusteriNameLabel);
        Masa5MusteriNameLabel.setBounds(47, 30, 110, 16);

        MasalarPanel.add(Masa5Panel);
        Masa5Panel.setBounds(180, 200, 170, 120);

        Masa6Panel.setLayout(null);

        Masa6NameLabel.setText("Table 6");
        Masa6Panel.add(Masa6NameLabel);
        Masa6NameLabel.setBounds(68, 6, 50, 16);

        Masa6StatusLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Masa6StatusLabel.setText("Null");
        Masa6Panel.add(Masa6StatusLabel);
        Masa6StatusLabel.setBounds(70, 50, 37, 16);

        Masa6ActLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Masa6ActLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Masa6ActLabel.setText("Status");
        Masa6Panel.add(Masa6ActLabel);
        Masa6ActLabel.setBounds(10, 70, 158, 16);

        Masa6MusteriNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Masa6MusteriNameLabel.setText("Customer name");
        Masa6Panel.add(Masa6MusteriNameLabel);
        Masa6MusteriNameLabel.setBounds(47, 30, 110, 16);

        MasalarPanel.add(Masa6Panel);
        Masa6Panel.setBounds(360, 200, 170, 120);

        jPanel1.add(MasalarPanel);
        MasalarPanel.setBounds(160, 0, 530, 330);

        jPanel5.setBackground(new java.awt.Color(0, 51, 0));

        ChefsLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ChefsLabel1.setForeground(new java.awt.Color(255, 255, 255));
        ChefsLabel1.setText("Chashier:");

        KazancLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        KazancLabel.setForeground(new java.awt.Color(255, 255, 255));
        KazancLabel.setText("Profit:");

        KazancNumberLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        KazancNumberLabel.setForeground(new java.awt.Color(255, 255, 255));
        KazancNumberLabel.setText("0");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ChefsLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(KazancLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(KazancNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(159, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ChefsLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(KazancLabel)
                    .addComponent(KazancNumberLabel))
                .addContainerGap(83, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel5);
        jPanel5.setBounds(160, 330, 350, 140);

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));

        NextButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        NextButton.setText("Next");
        NextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextButtonActionPerformed(evt);
            }
        });

        LabelX.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        LabelX.setForeground(new java.awt.Color(51, 51, 51));
        LabelX.setText("Step 1");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelX, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(LabelX, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(NextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        jPanel1.add(jPanel4);
        jPanel4.setBounds(510, 330, 180, 140);

        jTabbedPane1.addTab("Problem1", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    boolean next1 = false;

    private void NextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextButtonActionPerformed
        // TODO add your handling code here:
        System.out.println("Next Pressed");
        next1 = true;
    }//GEN-LAST:event_NextButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Asci1Yemek1Label;
    private javax.swing.JLabel Asci1Yemek2Label;
    private javax.swing.JLabel Asci2Yemek1Label;
    private javax.swing.JLabel Asci2Yemek2Label;
    private javax.swing.JLabel ChefsLabel;
    private javax.swing.JLabel ChefsLabel1;
    private javax.swing.JLabel Garson1StatusLabel;
    private javax.swing.JLabel Garson2StatusLabel;
    private javax.swing.JLabel Garson3StatusLabel;
    private javax.swing.JLabel GarsonsLabel;
    private javax.swing.JLabel GarsonsLabel1;
    private javax.swing.JLabel KazancLabel;
    private javax.swing.JLabel KazancNumberLabel;
    private javax.swing.JLabel LabelX;
    private javax.swing.JLabel Masa1ActLabel;
    private javax.swing.JLabel Masa1MusteriNameLabel;
    private javax.swing.JLabel Masa1MusteriNameLabel1;
    private javax.swing.JLabel Masa1MusteriNameLabel10;
    private javax.swing.JLabel Masa1MusteriNameLabel11;
    private javax.swing.JLabel Masa1MusteriNameLabel12;
    private javax.swing.JLabel Masa1MusteriNameLabel2;
    private javax.swing.JLabel Masa1MusteriNameLabel3;
    private javax.swing.JLabel Masa1MusteriNameLabel4;
    private javax.swing.JLabel Masa1MusteriNameLabel5;
    private javax.swing.JLabel Masa1MusteriNameLabel6;
    private javax.swing.JLabel Masa1MusteriNameLabel7;
    private javax.swing.JLabel Masa1MusteriNameLabel8;
    private javax.swing.JLabel Masa1MusteriNameLabel9;
    private javax.swing.JLabel Masa1NameLabel;
    private javax.swing.JPanel Masa1Panel;
    private javax.swing.JLabel Masa1StatusLabel;
    private javax.swing.JLabel Masa2ActLabel;
    private javax.swing.JLabel Masa2MusteriNameLabel;
    private javax.swing.JLabel Masa2NameLabel;
    private javax.swing.JPanel Masa2Panel;
    private javax.swing.JLabel Masa2StatusLabel;
    private javax.swing.JLabel Masa3ActLabel;
    private javax.swing.JLabel Masa3MusteriNameLabel;
    private javax.swing.JLabel Masa3NameLabel;
    private javax.swing.JPanel Masa3Panel;
    private javax.swing.JLabel Masa3StatusLabel;
    private javax.swing.JLabel Masa4ActLabel;
    private javax.swing.JLabel Masa4MusteriNameLabel;
    private javax.swing.JLabel Masa4NameLabel;
    private javax.swing.JPanel Masa4Panel;
    private javax.swing.JLabel Masa4StatusLabel;
    private javax.swing.JLabel Masa5ActLabel;
    private javax.swing.JLabel Masa5MusteriNameLabel;
    private javax.swing.JLabel Masa5NameLabel;
    private javax.swing.JPanel Masa5Panel;
    private javax.swing.JLabel Masa5StatusLabel;
    private javax.swing.JLabel Masa6ActLabel;
    private javax.swing.JLabel Masa6MusteriNameLabel;
    private javax.swing.JLabel Masa6NameLabel;
    private javax.swing.JPanel Masa6Panel;
    private javax.swing.JLabel Masa6StatusLabel;
    private javax.swing.JPanel MasalarPanel;
    private javax.swing.JButton NextButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}

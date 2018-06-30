package imageconverter.ui;

import imageconverter.ImageConverter;
import imageconverter.conv.Conv;
import imageconverter.conv.ConvConstants;
import imageconverter.conv.UnsupportedImageFormatException;
import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

/**
 * @author Sonic
 */
public class MainWindow extends javax.swing.JFrame {

    public static int counter = 0;
    
    private static boolean gtkShouldBeEnabled = false, windowsShouldBeEnabled = false;
    
    public static volatile boolean threadsFinished = false;
    
    private static ConvConstants format = ConvConstants.FORMAT_BMP;
    
    private File baseBuffer = new File("");
    
    private List<File> openQueue = new ArrayList();
    
    private Map<String, Conv> loadedBuffer = new ConcurrentHashMap<>();
    
    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
	
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jTextField1 = new javax.swing.JTextField();
        selectFileButton = new javax.swing.JButton();
        isGroupCheckbox = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        fileMaskField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        fileFormatBox = new javax.swing.JComboBox<>();
        openFilesButon = new javax.swing.JButton();
        maskCheckbox = new javax.swing.JCheckBox();
        jFileChooser1 = new javax.swing.JFileChooser();
        jMenuItem3 = new javax.swing.JMenuItem();
        jDialog2 = new javax.swing.JDialog();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jFileChooser2 = new javax.swing.JFileChooser();
        jToolBar1 = new javax.swing.JToolBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        layerTable = new javax.swing.JTable();
        showLayersButton = new javax.swing.JButton();
        consoleLabel = new javax.swing.JLabel();
        jDesktopPane2 = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openButton = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        bmpResave = new javax.swing.JMenuItem();
        gifResave = new javax.swing.JMenuItem();
        jpegResave = new javax.swing.JMenuItem();
        pngResave = new javax.swing.JMenuItem();
        wbmpResave = new javax.swing.JMenuItem();
        exportMenu = new javax.swing.JMenu();
        bmpExport = new javax.swing.JMenuItem();
        gifExport = new javax.swing.JMenuItem();
        jpegExport = new javax.swing.JMenuItem();
        pngExport = new javax.swing.JMenuItem();
        wbmpExport = new javax.swing.JMenuItem();
        exportallButton = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        appearanceMenu = new javax.swing.JMenu();
        motifStyle = new javax.swing.JMenuItem();
        gtkStyle = new javax.swing.JMenuItem();
        nimbusStyle = new javax.swing.JMenuItem();
        metalStyle = new javax.swing.JMenuItem();
        winStyle = new javax.swing.JMenuItem();

        jDialog1.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jDialog1.setTitle("Open file(s)");
        jDialog1.setBounds(new java.awt.Rectangle(0, 0, 730, 430));
        jDialog1.setMinimumSize(new java.awt.Dimension(730, 430));
        jDialog1.setResizable(false);

        jTextField1.setText("Base file");

        selectFileButton.setText("Select");
        selectFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectFileButtonActionPerformed(evt);
            }
        });

        isGroupCheckbox.setText("Open group");
        isGroupCheckbox.setEnabled(false);
        isGroupCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isGroupCheckboxActionPerformed(evt);
            }
        });

        jLabel1.setText("Name mask");
        jLabel1.setEnabled(false);

        fileMaskField.setEnabled(false);

        jLabel2.setText("Mask is a sequence to define other members of the file group. \\d means any digit (0-9), \\D means alphabetical symbol (a-z ignore case).");
        jLabel2.setEnabled(false);

        jLabel3.setText("For example, if mask is \"File$$\" it will open File00.png, File93.jpg, etc. Specifying the extension will prevent another extensions from being opened");
        jLabel3.setEnabled(false);

        jLabel4.setText("Format");
        jLabel4.setEnabled(false);

        fileFormatBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Any", "BMP", "GIF", "JPEG", "PNG", "WBMP" }));
        fileFormatBox.setEnabled(false);

        openFilesButon.setText("Open");
        openFilesButon.setEnabled(false);
        openFilesButon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFilesButonActionPerformed(evt);
            }
        });

        maskCheckbox.setText("Use custom mask");
        maskCheckbox.setEnabled(false);
        maskCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maskCheckboxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(selectFileButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fileMaskField, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fileFormatBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(openFilesButon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(maskCheckbox)
                            .addComponent(isGroupCheckbox))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectFileButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(isGroupCheckbox)
                .addGap(6, 6, 6)
                .addComponent(maskCheckbox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(fileMaskField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(fileFormatBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 208, Short.MAX_VALUE)
                .addComponent(openFilesButon)
                .addContainerGap())
        );

        jFileChooser1.setAcceptAllFileFilterUsed(false);
        jFileChooser1.setApproveButtonText("Open");
        jFileChooser1.setDialogTitle("Open...");

        jMenuItem3.setText("jMenuItem3");

        jDialog2.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jDialog2.setTitle("Export all options");
        jDialog2.setMaximumSize(new java.awt.Dimension(680, 340));
        jDialog2.setMinimumSize(new java.awt.Dimension(680, 340));

        jLabel5.setText("Save to: ");

        jButton1.setText("...");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel6.setText("Format: ");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BMP", "GIF", "JPEG", "PNG", "WBMP" }));

        jButton2.setText("Export all");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jDialog2Layout.createSequentialGroup()
                        .addGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDialog2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jDialog2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 241, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
        );

        jFileChooser2.setAcceptAllFileFilterUsed(false);
        jFileChooser2.setCurrentDirectory(new java.io.File("C:\\"));
            jFileChooser2.setDialogTitle("Choose location");
            jFileChooser2.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            setTitle("Sonic's Image Converter");
            addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowOpened(java.awt.event.WindowEvent evt) {
                    formWindowOpened(evt);
                }
            });

            jToolBar1.setRollover(true);

            layerTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    {null, null}
                },
                new String [] {
                    "Image", "Format"
                }
            ) {
                Class[] types = new Class [] {
                    java.lang.String.class, java.lang.String.class
                };
                boolean[] canEdit = new boolean [] {
                    false, false
                };

                public Class getColumnClass(int columnIndex) {
                    return types [columnIndex];
                }

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            });
            jScrollPane1.setViewportView(layerTable);

            showLayersButton.setText("Show selected image");
            showLayersButton.setEnabled(false);
            showLayersButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    showLayersButtonActionPerformed(evt);
                }
            });

            consoleLabel.setText("<none>");

            javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
            jDesktopPane2.setLayout(jDesktopPane2Layout);
            jDesktopPane2Layout.setHorizontalGroup(
                jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 709, Short.MAX_VALUE)
            );
            jDesktopPane2Layout.setVerticalGroup(
                jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE)
            );

            fileMenu.setText("File");

            openButton.setText("Open");
            openButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    openButtonActionPerformed(evt);
                }
            });
            fileMenu.add(openButton);

            jMenu1.setText("Save");

            bmpResave.setText("BMP");
            jMenu1.add(bmpResave);

            gifResave.setText("GIF");
            jMenu1.add(gifResave);

            jpegResave.setText("JPEG");
            jMenu1.add(jpegResave);

            pngResave.setText("PNG");
            jMenu1.add(pngResave);

            wbmpResave.setText("WBMP");
            jMenu1.add(wbmpResave);

            fileMenu.add(jMenu1);

            exportMenu.setText("Export");

            bmpExport.setText("BMP");
            bmpExport.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    bmpExportActionPerformed(evt);
                }
            });
            exportMenu.add(bmpExport);

            gifExport.setText("GIF");
            gifExport.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    gifExportActionPerformed(evt);
                }
            });
            exportMenu.add(gifExport);

            jpegExport.setText("JPEG");
            jpegExport.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jpegExportActionPerformed(evt);
                }
            });
            exportMenu.add(jpegExport);

            pngExport.setText("PNG");
            pngExport.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    pngExportActionPerformed(evt);
                }
            });
            exportMenu.add(pngExport);

            wbmpExport.setText("WBMP");
            wbmpExport.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    wbmpExportActionPerformed(evt);
                }
            });
            exportMenu.add(wbmpExport);

            fileMenu.add(exportMenu);

            exportallButton.setText("Export all");
            exportallButton.setEnabled(false);
            exportallButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    exportallButtonActionPerformed(evt);
                }
            });
            fileMenu.add(exportallButton);

            jMenuBar1.add(fileMenu);

            editMenu.setText("Edit");

            appearanceMenu.setText("Appearance");

            motifStyle.setText("Cde/Motif");
            motifStyle.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    motifStyleActionPerformed(evt);
                }
            });
            appearanceMenu.add(motifStyle);

            gtkStyle.setText("GTK+");
            gtkStyle.setEnabled(false);
            gtkStyle.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    gtkStyleActionPerformed(evt);
                }
            });
            appearanceMenu.add(gtkStyle);

            nimbusStyle.setText("Nimbus");
            nimbusStyle.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    nimbusStyleActionPerformed(evt);
                }
            });
            appearanceMenu.add(nimbusStyle);

            metalStyle.setText("Metal");
            metalStyle.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    metalStyleActionPerformed(evt);
                }
            });
            appearanceMenu.add(metalStyle);

            winStyle.setText("Windows");
            winStyle.setEnabled(false);
            winStyle.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    winStyleActionPerformed(evt);
                }
            });
            appearanceMenu.add(winStyle);

            editMenu.add(appearanceMenu);

            jMenuBar1.add(editMenu);

            setJMenuBar(jMenuBar1);

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                    .addGap(6, 6, 6)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(consoleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jDesktopPane2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(showLayersButton, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE))))
                    .addContainerGap())
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(showLayersButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 348, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jDesktopPane2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                    .addComponent(consoleLabel)
                    .addGap(6, 6, 6))
            );

            pack();
        }

    private void formWindowOpened(java.awt.event.WindowEvent evt) {
        String osname = System.getProperty("os.name");
        if(osname.contains("Windows")) {
            winStyle.setEnabled(true);
            windowsShouldBeEnabled = true;
        }
        else if(osname.contains("Linux")) {
            gtkStyle.setEnabled(true);
            gtkShouldBeEnabled = true;
        }
        
        jFileChooser1.addChoosableFileFilter(new FileFilter(){
            
            @Override
            public boolean accept(File f){
                boolean isGood = false;
                if(f.isDirectory()) isGood = true;
                else {
                    String name = f.getName();
                    String extension = name.substring(name.lastIndexOf("."));
                    switch(extension.toLowerCase()){
                        case ".bmp": isGood = true; break;
                        case ".gif": isGood = true; break;
                        case ".jpg":
                        case ".jpeg": isGood = true; break;
                        case ".png": isGood = true; break;
                        case ".wbmp": isGood = true; break;                        
                    }
                }
                return isGood;
            }

            @Override
            public String getDescription() {
                return "Supported image formats (BMP, GIF, JPEG, PMG, WBMP)";
            }
        });
    }

    private void motifStyleActionPerformed(java.awt.event.ActionEvent evt) {
        motifStyle.setEnabled(false);
        gtkStyle.setEnabled(gtkShouldBeEnabled);
        nimbusStyle.setEnabled(true);
        metalStyle.setEnabled(true);
        winStyle.setEnabled(windowsShouldBeEnabled);
        changeStyle("Motif");
    }

    private void gtkStyleActionPerformed(java.awt.event.ActionEvent evt) {
        motifStyle.setEnabled(true);
        gtkStyle.setEnabled(false);
        nimbusStyle.setEnabled(true);
        metalStyle.setEnabled(true);
        winStyle.setEnabled(windowsShouldBeEnabled);
        changeStyle("GTK+");
    }

    private void nimbusStyleActionPerformed(java.awt.event.ActionEvent evt) {
        motifStyle.setEnabled(true);
        gtkStyle.setEnabled(gtkShouldBeEnabled);
        nimbusStyle.setEnabled(false);
        metalStyle.setEnabled(true);
        winStyle.setEnabled(windowsShouldBeEnabled);
        changeStyle("Nimbus");
    }

    private void metalStyleActionPerformed(java.awt.event.ActionEvent evt) {
        motifStyle.setEnabled(true);
        gtkStyle.setEnabled(gtkShouldBeEnabled);
        nimbusStyle.setEnabled(true);
        metalStyle.setEnabled(false);
        winStyle.setEnabled(windowsShouldBeEnabled);
        changeStyle("Metal");
    }

    private void winStyleActionPerformed(java.awt.event.ActionEvent evt) {
        motifStyle.setEnabled(true);
        gtkStyle.setEnabled(gtkShouldBeEnabled);
        nimbusStyle.setEnabled(true);
        metalStyle.setEnabled(true);
        winStyle.setEnabled(false);
        changeStyle("Windows");
    }

    private void selectFileButtonActionPerformed(java.awt.event.ActionEvent evt) {
        jFileChooser1.setMultiSelectionEnabled(false);
        jFileChooser1.showOpenDialog(jDialog1);
        baseBuffer = jFileChooser1.getSelectedFile();
        jTextField1.setText(baseBuffer.getAbsolutePath());
        isGroupCheckbox.setEnabled(true);
        openFilesButon.setEnabled(true);
    }

    private void isGroupCheckboxActionPerformed(java.awt.event.ActionEvent evt) {
        if(baseBuffer.equals(new File(""))) {
            JOptionPane.showMessageDialog(rootPane, "Select a file first!", "Error!", JOptionPane.ERROR_MESSAGE);
            isGroupCheckbox.setSelected(false);
        } else {
            maskCheckbox.setEnabled(true);
        }
    }

    private void openFilesButonActionPerformed(java.awt.event.ActionEvent evt) {
        jDialog1.setVisible(false);
        if(isGroupCheckbox.isSelected()) {
            if(maskCheckbox.isSelected()) openGroup(fileMaskField.getText(), fileFormatBox.getSelectedItem().toString());
            else openGroupLex();
        }
        else openSingle();
    }

    private void openButtonActionPerformed(java.awt.event.ActionEvent evt) {
        jDialog1.setVisible(true);
    }

    private void bmpExportActionPerformed(java.awt.event.ActionEvent evt) {
        exportSingle(ConvConstants.FORMAT_BMP);
    }

    private void gifExportActionPerformed(java.awt.event.ActionEvent evt) {
        exportSingle(ConvConstants.FORMAT_GIF);
    }

    private void showLayersButtonActionPerformed(java.awt.event.ActionEvent evt) {
        //Pretty self-explanatory
        String imageName = layerTable.getValueAt(layerTable.getSelectedRow(), 0).toString();
        Conv c = loadedBuffer.get(imageName);
        
        //Create and setup an internal frame container to view the image
        JInternalFrame iframe = new JInternalFrame();
            iframe.setClosable(true);
            iframe.setEnabled(true);
            iframe.setIconifiable(true);
            iframe.setResizable(false);
            iframe.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            iframe.setTitle(imageName);
        
        //Get the image and setup a rectangle with x=0, y=0 and width and height of the image 
        Image img = c.getImage();
        int w = img.getWidth(new BlankObserver());
        int h = img.getHeight(new BlankObserver());
        Rectangle rekt = new Rectangle();
        rekt.setSize(w, h);
        
        //Create a label that will display the image and paint in on the label
        JLabel infLabel = new JLabel();
        infLabel.setBounds(rekt);     
        ImageIcon imageIcon = new ImageIcon();
        imageIcon.setImage(img);
        infLabel.setIcon(imageIcon);
        
        //Add the label as the child component to JInternalFrame
        iframe.add(infLabel);  
        
        //Resize the internal frame to fit its components' (basically the image) size
        iframe.pack();          
        
        //Add the frame to JDesktopPane container and display it
        jDesktopPane2.add(iframe);
        iframe.setVisible(true);
    }

    private void jpegExportActionPerformed(java.awt.event.ActionEvent evt) {
        exportSingle(ConvConstants.FORMAT_JPEG);
    }

    private void pngExportActionPerformed(java.awt.event.ActionEvent evt) {
        exportSingle(ConvConstants.FORMAT_PNG);
    }

    private void wbmpExportActionPerformed(java.awt.event.ActionEvent evt) {
        exportSingle(ConvConstants.FORMAT_WBMP);
    }

    private void maskCheckboxActionPerformed(java.awt.event.ActionEvent evt) {
        if(maskCheckbox.isSelected()) {
            JOptionPane.showMessageDialog(jDialog1, "This feature is not currently available!", "Warning!", JOptionPane.WARNING_MESSAGE);
            maskCheckbox.setSelected(false);
        }
    }

    private void exportallButtonActionPerformed(java.awt.event.ActionEvent evt) {
        jDialog2.setVisible(true);
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        jFileChooser2.showOpenDialog(jDialog2);
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        File dir = jFileChooser2.getSelectedFile();
        String ext = "." + jComboBox1.getSelectedItem().toString().toLowerCase();
        Conv.FORMATS.forEach((c, s) -> {
            if(s.equals(ext))
                format = c;
        });
        jDialog2.setVisible(false);
        exportGroup(format, dir,  loadedBuffer);
    }

    /**
     * @param args the command line arguments
     */
    public static void wmain(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (ImageConverter.WSTYLE_CURRENT.equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(() -> {
            ImageConverter.mw = new MainWindow();
            ImageConverter.mw.setVisible(true);
            conlog("Loaded");
        });
    }

    
    private javax.swing.JMenu appearanceMenu;
    private javax.swing.JMenuItem bmpExport;
    private javax.swing.JMenuItem bmpResave;
    private javax.swing.JLabel consoleLabel;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenu exportMenu;
    private javax.swing.JMenuItem exportallButton;
    private javax.swing.JComboBox<String> fileFormatBox;
    private javax.swing.JTextField fileMaskField;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem gifExport;
    private javax.swing.JMenuItem gifResave;
    private javax.swing.JMenuItem gtkStyle;
    private javax.swing.JCheckBox isGroupCheckbox;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JFileChooser jFileChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenuItem jpegExport;
    private javax.swing.JMenuItem jpegResave;
    private javax.swing.JTable layerTable;
    private javax.swing.JCheckBox maskCheckbox;
    private javax.swing.JMenuItem metalStyle;
    private javax.swing.JMenuItem motifStyle;
    private javax.swing.JMenuItem nimbusStyle;
    private javax.swing.JMenuItem openButton;
    private javax.swing.JButton openFilesButon;
    private javax.swing.JMenuItem pngExport;
    private javax.swing.JMenuItem pngResave;
    private javax.swing.JButton selectFileButton;
    private javax.swing.JButton showLayersButton;
    private javax.swing.JMenuItem wbmpExport;
    private javax.swing.JMenuItem wbmpResave;
    public javax.swing.JMenuItem winStyle;
	

    //Change window style
    private void changeStyle(String laf) {
        try {
            //Save the style that user chose
            BufferedWriter bw = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "/style"));
            bw.write(laf);
            bw.close();
            
            //Iterate through all L&Fs that are currently available and if any of them matches the preferred one, change the style to it
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (laf.equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            
            //Nice and easy way to change the style in runtime
            SwingUtilities.updateComponentTreeUI(this);
            //Resize the form to fit all its components' size
            this.pack();
        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public static void conlog(String text){
        ImageConverter.mw.consoleLabel.setForeground(Color.black);
        ImageConverter.mw.consoleLabel.setText(text);
        System.out.println(text);
    }
    
    
    public static void conlog(String text, Color col){
        ImageConverter.mw.consoleLabel.setForeground(col);
        ImageConverter.mw.consoleLabel.setText(text);
        System.out.println(text);
    }
    
    
    private void openSingle(){
        //Clear the map to prevent overloading, name collisions and so on. This deletes EVERYTHING that was loaded before
        loadedBuffer.clear();
        exportallButton.setEnabled(false);
        conlog("Opening file: " + baseBuffer.getAbsolutePath(), Color.blue);
        try {
            Conv c = new Conv(baseBuffer);
            loadedBuffer.put(baseBuffer.getName(), c);
            conlog("Opened all files", new Color(60, 220, 60, 255));
        } catch (FileNotFoundException | UnsupportedImageFormatException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        exportallButton.setEnabled(false);
        afterload();
    }
    
    
    //Opens group of files. Each next file name must lexicographically follow the previous one
    private void openGroupLex(){
        openQueue.add(baseBuffer); //Because it will not pass the lexicographical check
        
        //Getting the extension ang initial file name
        String filenameBuffer = baseBuffer.getName();
        String extension = filenameBuffer.substring(filenameBuffer.lastIndexOf("."));
        
        //Getting the directory with our files
        String baseFile = baseBuffer.getAbsolutePath();  
        if(baseFile.contains("/")) baseFile = baseFile.replaceAll("/", "\\");
        File baseFolder = new File(baseFile.substring(0, baseFile.lastIndexOf("\\")+1));
                
        //Filling up the open queue
        for(File f : baseFolder.listFiles()){
            String s = f.getName();
            if(s.compareToIgnoreCase(filenameBuffer) > 0 && s.endsWith(extension)) openQueue.add(f);
        }
        
        //Opening
        
        ImageLoader imgLoader = new ImageLoader(openQueue);
        Thread thr = new Thread(imgLoader);
        thr.start();   
        
        exportallButton.setEnabled(true);
    }    
    
    
    //NOT WORKING YET!
    //Opens group of files using a user-defined name mask, that is being converted to a valid regular expression before opening files
    private void openGroup(String mask, String extension){
        //Clear the map to prevent overloading, name collisions and so on. This deletes EVERYTHING that was loaded before
        loadedBuffer.clear();
        exportallButton.setEnabled(true);
        conlog("Opening group of files");
        openQueue.add(baseBuffer);
        
        //First we get the folder to look files into
        String baseFile = baseBuffer.getAbsolutePath();  
        if(baseFile.contains("/")) baseFile = baseFile.replaceAll("/", "\\");
        String baseFolder = baseFile.substring(0, baseFile.lastIndexOf("\\")+1);
        
        //Setting up a regex
        String regex = "";
        if(mask.contains("№")) {
            String regexBuffer = mask.replaceAll("№", "\\&d");
            regex = regexBuffer.replaceAll("&", "");
        }
        if(mask.contains("@")) regex = mask.replaceAll("@", "\\D");
        else regex = mask;
        if(extension.equals("Any")) regex += " *";
        else regex += ("."+extension.toLowerCase());
        conlog("Group regex mask is "+regex);
        for(File f : new File(baseFolder).listFiles()){
            String filename = f.getName().toLowerCase();
            if(filename.matches(regex)) openQueue.add(f);
        }
        
        ImageLoader imgLoader = new ImageLoader(openQueue);
        Thread thr = new Thread(imgLoader);
        thr.start();
        exportallButton.setEnabled(true);
    }
    
    
    //Does some work after loading images
    private void afterload(){
        //Cleans up the load queue
        openQueue.clear();
        showLayersButton.setEnabled(true);
        setupLayerTable();
    }
    
    
    //Setups image list somewhy called a layer table
    private void setupLayerTable(){
        //Calculates amount of rows needed to fit all the images
        int rows = loadedBuffer.size(), neededRows = 0, actualRows = layerTable.getRowCount();        
        neededRows = rows - actualRows;
        DefaultTableModel tmodel = (DefaultTableModel) layerTable.getModel();
        Object[] defaults = {"", ""};
        
        if(neededRows != 0) cleanupTable(tmodel);
        
        if(neededRows < 0) {
            for(int i = actualRows; i > actualRows + neededRows; i--) 
                tmodel.removeRow(i);            
        }
        else if(neededRows > 0) {
            for(int i = actualRows; i < actualRows + neededRows; i++) 
                tmodel.addRow(defaults);            
        }
        
        //Fills the table
        MainWindow.counter = 0;
        loadedBuffer.forEach((String s, Conv c) -> {
            tmodel.setValueAt(s, MainWindow.counter, 0);
            tmodel.setValueAt(Conv.FORMATS.get(c.getImageFormat()).substring(1).toUpperCase(), MainWindow.counter, 1);
            MainWindow.counter++;
        });
    }
    
    
    private void cleanupTable(DefaultTableModel tmodel){
        //Not used yet
    }
    
    
    public void addToBuffer(String s, Conv c){
        loadedBuffer.put(s, c);
    }
    
    
    public void processThreading(boolean isFinished){
        MainWindow.threadsFinished = isFinished;
        fileMenu.setEnabled(isFinished);
        if(isFinished) afterload();
    }
    
    
    private void exportSingle(ConvConstants format){
        String imageName = layerTable.getValueAt(layerTable.getSelectedRow(), 0).toString();
        Conv c = loadedBuffer.get(imageName);
        c.convert(format);
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileFilter(){
            @Override
            public boolean accept(File f) {
                return f.getName().endsWith(Conv.FORMATS.get(format));
            }

            @Override
            public String getDescription() {
                return Conv.FORMATS.get(format).substring(1).toUpperCase() + " image";
            }
            
        });
        fileChooser.setFileSelectionMode(0);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.showSaveDialog(rootPane);       
        
        File f = new File(jFileChooser1.getSelectedFile().getAbsolutePath() + Conv.FORMATS.get(format));
        
        c.save(f);
        conlog("Saved image "+imageName+" as "+Conv.FORMATS.get(format).substring(1).toUpperCase()+" at "+f.getAbsoluteFile());
    }
    
    
    private void exportGroup(ConvConstants format, File dir, Map<String, Conv> convs){
        new Thread(new ImageSaver(format, dir, convs)).start();
    }
  


class ImageLoader implements Runnable {
        
    private final List<File> loadingQueue;
    
    public ImageLoader(List<File> queue){
        loadingQueue = queue;
    }
    
    @Override
    public void run(){        
        ImageConverter.mw.processThreading(false);
        Executors.newFixedThreadPool(1)
            .submit(() -> {
            startLoading();
        });
    }
    
    
    private void startLoading(){
        loadingQueue.stream().filter((f) -> (!f.isDirectory())).forEach((f) -> {
            loadImage(f);
        });        
        //After all iterations are over
        MainWindow.conlog("Opened all files", new Color(60, 220, 60, 255));
        ImageConverter.mw.processThreading(true);
    }
    
        
    public void loadImage(File image){
        try {
            MainWindow.conlog("Opening file: "+image.getAbsolutePath(), Color.blue);
            Conv c = new Conv(image);
            ImageConverter.mw.addToBuffer(image.getName(), c);
        } catch (FileNotFoundException | UnsupportedImageFormatException ex) {
            Logger.getLogger(ImageLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}



class ImageSaver implements Runnable {

    private final ConvConstants format;
    private final File dir;
    private final Map<String, Conv> convs;
    
    public ImageSaver(ConvConstants cc, File f, Map<String, Conv> lc){
        format = cc;
        dir = f;
        convs = lc;
    }
    
    @Override
    public void run() {
        convs.forEach((s, c) -> {
            c.convert(format);
            c.save(new File(dir.getAbsolutePath() + "\\" + s));
        });
    }
    
}




class BlankObserver implements ImageObserver {

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {		
        return false;
    }
    
}
  
}

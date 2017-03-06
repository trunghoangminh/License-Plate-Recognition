package org.it.tdt.edu.vn.main;

import javax.swing.JFrame;

/**
 * 
 * @author TrungHoang
 *
 */
public class MainForm extends JFrame {

	/**
//	 * 
//	 */
	private static final long serialVersionUID = 1L;
//	private JPanel contentPanel;
//	private JFileChooser fileChooser;
//	private ImagePanel imagePanel;
//	private static final String IMG_URl = "/home/hmtrung/Pictures/biensoxe.jpg";
//
//	static {
//		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//	}
//
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//
//					// Set look and feel for Swing
//					UIManager.setLookAndFeel(UIManager
//							.getSystemLookAndFeelClassName());
//
//					MainForm frame = new MainForm();
//					frame.setVisible(true);
//
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * This is the main frame of application license plate recognition.
//	 */
//	public MainForm() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 1000, 700);
//		contentPanel = new JPanel();
//		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPanel);
//		contentPanel.setLayout(null);
//
//		// add Image panel
//
////		LiscencePlateBufferedImage liscencePlateBufferedImage = new LiscencePlateBufferedImage(
////				IMG_URl);
////
////		ImageProcessor grayImge = new GrayImage(liscencePlateBufferedImage);
////
////		ImageProcessor pyrDownImage = new PyrDownImage(
////		 liscencePlateBufferedImage);
////
////		ImageProcessor thresholdImage = new ThresHoldImage(
////				liscencePlateBufferedImage, grayImge.createImageResult());
////		BufferedImage bufferedGrayImage = thresholdImage.createImageResult();
////		imagePanel = new ImagePanel(bufferedGrayImage);
////		imagePanel.setLocation(5, 5);
////		imagePanel.setSize(800, 600);
////		contentPanel.add(imagePanel);
//
//		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
//		tabbedPane.setBounds(826, 5, 148, 600);
//		contentPanel.add(tabbedPane);
//
//		JButton btnRecogination = new JButton("Recogination");
//		btnRecogination.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
//		btnRecogination.setBounds(507, 620, 110, 30);
//		contentPanel.add(btnRecogination);
//
//		JButton btnBrowse = new JButton("Browse");
//		btnBrowse.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				fileChooser = new JFileChooser();
//				fileChooser
//						.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
//				fileChooser.setMultiSelectionEnabled(true);
//				fileChooser.showOpenDialog(contentPanel);
//			}
//		});
//		btnBrowse.setBounds(694, 620, 110, 30);
//		contentPanel.add(btnBrowse);
//
//	}
}

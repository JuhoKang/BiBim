package kr.re.ec.bibim.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import kr.re.ec.bibim.controller.MainController;
import kr.re.ec.bibim.util.LogUtil;
import kr.re.ec.bibim.vo.FolderData;
import kr.re.ec.bibim.vo.NoteData;

public abstract class MainFrame extends JFrame{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1446530117300808753L;
	private boolean topview = true;
	protected JButton noteaddbt = new JButton("노트추가");
	protected JButton notermbt = new JButton("노트제거");
	protected JButton folderaddbt= new JButton("폴더추가");
	protected JButton folderrmbt = new JButton("폴더제거");
	
	protected FolderData selectedfolder;
	protected NoteData selectednote;
	
	
	public JList<FolderData> folderlist = null;
	public JList<NoteData> notelist = null;
	private Container con;
	public DefaultListModel<FolderData> foldermodel = new DefaultListModel<FolderData>();
	public DefaultListModel<NoteData> notemodel = new DefaultListModel<NoteData>();
//	DefaultListModel<FolderData> model1 = new DefaultListModel<FolderData>();
//	DefaultListModel<FolderData> model2= new DefaultListModel<FolderData>();
	public void init() {
		setTitle("Note Manager");

	//	String items2[] = { "자바", "jsp", "html5", "안드로이드" };
	//	String items3[] = { "dd바", "jddsp", "htmdl5", "안드로이드" };

	//	list2 = new JList<String>(items2);
		
		setLayout(new BorderLayout());
		/*
		final FolderData folder = new FolderData();
		for(int i=0 ; i< 30 ; i++){
			folder.setFolderid(i);
			folder.getFolderid();
			model1.addElement(folder);
		}
		*/
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		folderlist = new JList(foldermodel);
		folderlist.setCellRenderer(new FolderListCellRenderer());
		folderlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane pane = new JScrollPane(folderlist);
		
		notelist = new JList(notemodel);
		notelist.setCellRenderer(new NoteListCellRenderer());
		notelist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane pane2 = new JScrollPane(notelist);


		con = getContentPane();
		JPanel pn1 = new JPanel(new BorderLayout());
		JPanel pn12 = new JPanel(new GridLayout(1, 2));
		pn1.add(pane, BorderLayout.CENTER);
		pn1.add(pn12, BorderLayout.SOUTH);
		pn12.add(folderaddbt);
		pn12.add(folderrmbt);
		JPanel pn2 = new JPanel(new BorderLayout());
		JPanel pn22 = new JPanel(new GridLayout(1, 2));
		pn2.add(pane2, BorderLayout.CENTER);
		pn2.add(pn22, BorderLayout.SOUTH);
		pn22.add(noteaddbt);
		pn22.add(notermbt);
		con.setLayout(new GridLayout(1, 2));
		con.add(pn1);
		con.add(pn2);

		
		
	//	folderlist.getSelectionModel().addListSelectionListener(new FolderListSelectionHandler());
		//notelist.getSelectionModel().addListSelectionListener(new No);


		// 전체 창 사이즈 가져오는거네
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		// 컴퓨터 화면 크기가 다르지만 모든 컴퓨터의 화면에 -- 창을 중앙에 위치시킬 수 있다.
		int xPos = screenSize.width / 3;
		int yPos = screenSize.height / 3;

		setLocation(xPos - 100, yPos - 50);

		setSize(700, 400);
		setVisible(true);
		setAlwaysOnTop(topview);
	}
	protected class FolderListSelectionHandler implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) { 
            ListSelectionModel lsm = (ListSelectionModel)e.getSource();
            
            int firstIndex = e.getFirstIndex();
            int lastIndex = e.getLastIndex();
            boolean isAdjusting = e.getValueIsAdjusting(); 
            String listlog;
            listlog = 
            		("Event for indexes "
                          + firstIndex + " - " + lastIndex
                          + "; isAdjusting is " + isAdjusting
                          + "; selected indexes:");
 
            if (lsm.isSelectionEmpty()) {
                listlog.concat(" <none>");
            } else {
                // Find out which indexes are selected.
                int minIndex = lsm.getMinSelectionIndex();
                int maxIndex = lsm.getMaxSelectionIndex();
                for (int i = minIndex; i <= maxIndex; i++) {
                    if (lsm.isSelectedIndex(i) && isAdjusting == true) {
                    	selectedfolder = (FolderData) folderlist.getModel()
								.getElementAt(i);
						LogUtil.d("clicked on: "
								+ selectedfolder.toString());
						
                        listlog.concat(" " + i);
                    }
                }
            }
            MainController.getInstance().getNoteList();
            listlog.concat("\n");
            LogUtil.d("listlog :" + listlog);
        }
    }

	
	protected class FolderListCellRenderer extends JLabel implements ListCellRenderer<FolderData>{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public FolderListCellRenderer() {
			setOpaque(true);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public Component getListCellRendererComponent(
				JList<? extends FolderData> list, FolderData value, int index,
				boolean isSelected, boolean cellHasFocus) {
			
			setText(value.getName());
			// TODO Auto-generated method stub
			if(isSelected){
				setBackground(Color.LIGHT_GRAY);
				setForeground(Color.RED);
			}
			else{
				setBackground(Color.white);
				setForeground(Color.black);
			}
			
			return this;
		}
		
	}
	
	protected class NoteListCellRenderer extends JLabel implements ListCellRenderer<NoteData>{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public NoteListCellRenderer() {
			setOpaque(true);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public Component getListCellRendererComponent(
				JList<? extends NoteData> list, NoteData value, int index,
				boolean isSelected, boolean cellHasFocus) {
			setText("<html><b> Title : "+value.getTitle()+"</b><br>"+"<i><font color = gray>Date : "
				+ value.getDate()+"</font></i></html>");
			// TODO Auto-generated method stub
			if(isSelected){
				setBackground(Color.lightGray);
				setForeground(Color.blue);
			}
			else{
				setBackground(Color.white);
				setForeground(Color.black);
			}
			
			return this;
		}
		
	}
}

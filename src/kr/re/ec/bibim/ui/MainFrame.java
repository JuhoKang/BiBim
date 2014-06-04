package kr.re.ec.bibim.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import kr.re.ec.bibim.vo.FolderData;

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1446530117300808753L;
	private boolean topview = true;
	protected JButton noteaddbt = new JButton("노트추가");
	protected JButton notervbt = new JButton("노트제거");
	protected JButton addButton = new JButton("추가");
	protected JButton removeButton = new JButton("제거");
	
	@SuppressWarnings("rawtypes")
	JList list = null;
	JList list2 = null;
	private Container con;
	DefaultListModel<FolderData> model1 = new DefaultListModel<FolderData>();
	DefaultListModel<FolderData> model2= new DefaultListModel<FolderData>();
	ArrayList<FolderData> ar1 = new ArrayList<FolderData>();
	ArrayList<FolderData> ar2 = new ArrayList<FolderData>(); 
	public MainFrame() {
		setTitle("Note Manager");

	//	String items2[] = { "자바", "jsp", "html5", "안드로이드" };
	//	String items3[] = { "dd바", "jddsp", "htmdl5", "안드로이드" };

	//	list2 = new JList<String>(items2);
		
		setLayout(new BorderLayout());
		final FolderData folder = new FolderData();
		for(int i=0 ; i< 30 ; i++){
			folder.setFolderid(i);
			folder.getFolderid();
			model1.addElement(folder);
		}
		
		
		list = new JList(model1);
		JScrollPane pane = new JScrollPane(list);
		
		list2 = new JList(model2);
		JScrollPane pane2 = new JScrollPane(list2);


		con = getContentPane();
		JPanel pn1 = new JPanel(new BorderLayout());
		JPanel pn12 = new JPanel(new GridLayout(1, 2));
		pn1.add(pane, BorderLayout.CENTER);
		pn1.add(pn12, BorderLayout.SOUTH);
		pn12.add(addButton);
		pn12.add(removeButton);
		JPanel pn2 = new JPanel(new BorderLayout());
		JPanel pn22 = new JPanel(new GridLayout(1, 2));
		pn2.add(pane2, BorderLayout.CENTER);
		pn2.add(pn22, BorderLayout.SOUTH);
		pn22.add(noteaddbt);
		pn22.add(notervbt);
		con.setLayout(new GridLayout(1, 2));
		con.add(pn1);
		con.add(pn2);

		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model1.addElement(folder);
			}
		});
		
		
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = list.getSelectedIndex();
				model1.remove(index);
			}
		});
		
		noteaddbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model2.addElement(folder);
			}
		});
		
		
		notervbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = list2.getSelectedIndex();
			    model2.remove(index);
			}
		});
		
		 ListSelectionListener listSelectionListener = new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent listSelectionEvent) {
		        System.out.print("First index: " + listSelectionEvent.getFirstIndex());
		        System.out.print(", Last index: " + listSelectionEvent.getLastIndex());
		        boolean adjust = listSelectionEvent.getValueIsAdjusting();
		        System.out.println(", Adjusting? " + adjust);
		        if (!adjust) {
		          JList list = (JList) listSelectionEvent.getSource();
		          int selections[] = list.getSelectedIndices();
		          Object selectionValues[] = list.getSelectedValues();
		          for (int i = 0, n = selections.length; i < n; i++) {
		            if (i == 0) {
		              System.out.print("  Selections: ");
		            }
		            System.out.print(selections[i] + "/" + selectionValues[i] + " ");
		          }
		          System.out.println();
		        }
		      }
		    };
		list.addListSelectionListener(listSelectionListener);

	    MouseListener mouseListener = new MouseAdapter() {
	      public void mouseClicked(MouseEvent mouseEvent) {
	        JList theList = (JList) mouseEvent.getSource();
	        if (mouseEvent.getClickCount() == 2) {
	          int index = theList.locationToIndex(mouseEvent.getPoint());
	          if (index >= 0) {
	            Object o = theList.getModel().getElementAt(index);
	            System.out.println("Double-clicked on: " + o.toString());
	          }
	        }
	      }
	    };
	    list.addMouseListener(mouseListener);

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
}

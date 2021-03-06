/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.tableviewer;

import edu.wpi.first.tableviewer.dialog.AddArrayDialog;
import edu.wpi.first.tableviewer.dialog.AddBooleanDialog;
import edu.wpi.first.tableviewer.dialog.AddNumberDialog;
import edu.wpi.first.tableviewer.dialog.AddStringDialog;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.event.TableModelEvent;
import javax.swing.tree.TreePath;

/**
 * Responsible for displaying all the data within a NetworkTable, either as
 * LeaefNodes or as BranchNodes.
 *
 * @author Sam
 */
public class BranchNode extends AbstractTreeNode {

    private final String name;

    public BranchNode(String key, String name) {
        super(new TableEntryData(name, null));
        this.name = name;
        if (key.startsWith("/"))
            key = key.substring(1);
        table = NetworkTable.getTable(key);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    /**
     * Changes the type of the data displayed within this branch. This is only
     * a graphical update.
     *
     * @param newType The new type for this branch to show.
     */
    public void updateType(String newType) {
        data.setType(newType);
        outline.tableChanged(new TableModelEvent(outline.getModel()));
    }

    /**
     * Creates the {@code JPopupMenu} to display when this node is
     * right-clicked.
     *
     * @param path The path from the root to this branch.
     * @return The menu for this node.
     */
    public JPopupMenu getMenu(final TreePath path) {
        JPopupMenu popupMenu = new JPopupMenu("Add Items");

        JMenuItem addArrayItem = new JMenuItem("Add array");
        JMenuItem addBooleanItem = new JMenuItem("Add boolean");
        JMenuItem addNumberItem = new JMenuItem("Add number");
        JMenuItem addStringItem = new JMenuItem("Add string");

        addArrayItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddArrayDialog(path, table).setVisible(true);
            }
        });

        addBooleanItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddBooleanDialog(path, table).setVisible(true);
            }
        });

        addNumberItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddNumberDialog(path, table).setVisible(true);
            }
        });

        addStringItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddStringDialog(path, table).setVisible(true);
            }
        });

        popupMenu.add(addArrayItem);
        popupMenu.add(addBooleanItem);
        popupMenu.add(addNumberItem);
        popupMenu.add(addStringItem);

        return popupMenu;
    }
}

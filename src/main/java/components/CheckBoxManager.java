package components;

import main.Main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lukasz on 01.04.2017.
 */
public class CheckBoxManager {

    private Main container;

    public CheckBoxManager(Main pane) {
        this.container = pane;
    }

    public List<String> getAllCheckedBoxes() {
        List<Component> components = getAllComponents(this.container);
        List<JCheckBox> checkBoxList = getAllCheckBoxes(components);
        return getOnlyCheckedBoxes(checkBoxList);
    }

    private static List<Component> getAllComponents(Container c) {
        Component[] comps = c.getComponents();
        List<Component> compList = new ArrayList<Component>();
        for (Component comp : comps) {
            compList.add(comp);
            if (comp instanceof Container)
                compList.addAll(getAllComponents((Container) comp));
        }
        return compList;
    }

    private static List<JCheckBox> getAllCheckBoxes(List<Component> components) {
        List<JCheckBox> lista = new ArrayList<JCheckBox>();
        for (Component comp : components) {
            if (comp instanceof JCheckBox) {
                lista.add((JCheckBox) comp);
            }
        }
        return lista;
    }

    private static List<String> getOnlyCheckedBoxes(List<JCheckBox> checkBoxList) {
        List<String> checkedValues = new ArrayList<String>();
        for (JCheckBox checkBox : checkBoxList) {
            if (checkBox.isSelected()) {
                checkedValues.add(checkBox.getText());
            }
        }

        return checkedValues;
    }
}

package com.progr.amador.TNText.Viewer;

import com.progr.amador.TNText.Model.Elements.Element;
import com.progr.amador.TNText.TerminalGUI;

public interface ElementViewer<T extends Element> {

    void draw(T element, TerminalGUI gui);
}

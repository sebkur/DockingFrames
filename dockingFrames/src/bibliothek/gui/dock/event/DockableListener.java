/**
 * Bibliothek - DockingFrames
 * Library built on Java/Swing, allows the user to "drag and drop"
 * panels containing any Swing-Component the developer likes to add.
 * 
 * Copyright (C) 2007 Benjamin Sigg
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * Benjamin Sigg
 * benjamin_sigg@gmx.ch
 * 
 * Wunderklingerstr. 59
 * 8215 Hallau
 * CH - Switzerland
 */


package bibliothek.gui.dock.event;

import javax.swing.Icon;

import bibliothek.gui.dock.Dockable;
import bibliothek.gui.dock.title.DockTitle;

/**
 * A listener which is added to a {@link Dockable}. These listeners are manly
 * used to update the titles of a {@link Dockable} whenever necessary.
 * @author Benjamin Sigg
 */
public interface DockableListener {
    /**
     * Will be invoked when a {@link DockTitle} was {@link Dockable#bind(DockTitle) binded}
     * to a {@link Dockable}.
     * @param dockable the <code>Dockable</code> whose title is set
     * @param title the new title
     */
    public void titleBinded( Dockable dockable, DockTitle title );
    
    /**
     * Will be invoked when a {@link DockTitle} was {@link Dockable#unbind(DockTitle) unbinded}
     * from a {@link Dockable}.
     * @param dockable the <code>Dockable</code> whose title was removed
     * @param title the remove title
     */
    public void titleUnbinded( Dockable dockable, DockTitle title );
    
    /**
     * Invoked when the title of a {@link Dockable} has changed.
     * @param dockable the <code>Dockable</code> whose title is changed
     * @param oldTitle the title before the change
     * @param newTitle the title after the change
     */
    public void titleTextChanged( Dockable dockable, String oldTitle, String newTitle );
    
    /**
     * Invoked when the title-icon of a {@link Dockable} has changed
     * @param dockable the <code>Dockable</code> whose title is changed
     * @param oldIcon the old icon, may be <code>null</code>
     * @param newIcon the new icon, may be <code>null</code>
     */
    public void titleIconChanged( Dockable dockable, Icon oldIcon, Icon newIcon );
}
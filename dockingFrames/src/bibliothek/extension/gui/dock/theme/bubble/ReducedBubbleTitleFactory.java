/*
 * Bibliothek - DockingFrames
 * Library built on Java/Swing, allows the user to "drag and drop"
 * panels containing any Swing-Component the developer likes to add.
 * 
 * Copyright (C) 2007 Benjamin Sigg
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 * 
 * Benjamin Sigg
 * benjamin_sigg@gmx.ch
 * CH - Switzerland
 */
package bibliothek.extension.gui.dock.theme.bubble;

import java.awt.Point;

import bibliothek.extension.gui.dock.theme.BubbleTheme;
import bibliothek.gui.DockStation;
import bibliothek.gui.Dockable;
import bibliothek.gui.dock.title.DockTitle;
import bibliothek.gui.dock.title.DockTitleFactory;
import bibliothek.gui.dock.title.DockTitleVersion;

/**
 * A factory creating instances of {@link BubbleDockTitle}. The titles are
 * modified such that the always have round edges.
 * @author Benjamin Sigg
 *
 */
public class ReducedBubbleTitleFactory implements DockTitleFactory {
	/** the theme from which colors can be read */
    private BubbleTheme theme;
    
    /**
     * Creates a new factory.
     * @param theme a theme from which colors will be read
     */
    public ReducedBubbleTitleFactory( BubbleTheme theme ){
        this.theme = theme;
    }

    public DockTitle createDockableTitle( Dockable dockable, DockTitleVersion version ) {
        return new Title( theme, dockable, version );
    }

    public <D extends Dockable & DockStation> DockTitle createStationTitle( D dockable, DockTitleVersion version ) {
        return new Title( theme, dockable, version );
    }

    /**
     * A {@link BubbleDockTitle} whose edges are always round.
     * @author Benjamin Sigg
     */
    private static class Title extends BubbleDockTitle{
    	/**
    	 * Creates a new title.
    	 * @param theme the theme from which to read colors
    	 * @param dockable the dockable for which this title will be shown
    	 * @param origin the {@link DockTitleVersion} which was used to create this title
    	 */
        public Title( BubbleTheme theme, Dockable dockable, DockTitleVersion origin ) {
            super( theme, dockable, origin, false );
        }
        
        @Override
        public Point getPopupLocation( Point click ){
        	return null;
        }
        
        @Override
        public void setOrientation( Orientation orientation ) {
            switch( orientation ){
                case SOUTH_SIDED:
                case NORTH_SIDED:
                case FREE_HORIZONTAL:
                    orientation = Orientation.FREE_HORIZONTAL;
                    break;
                case EAST_SIDED:
                case WEST_SIDED:
                case FREE_VERTICAL:
                    orientation = Orientation.FREE_VERTICAL;
                    break;
            }
            
            super.setOrientation( orientation );
        }
    }
}
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
package bibliothek.gui.dock.common;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import bibliothek.gui.DockFrontend;
import bibliothek.gui.Dockable;
import bibliothek.gui.dock.FlapDockStation;
import bibliothek.gui.dock.SplitDockStation;
import bibliothek.gui.dock.FlapDockStation.Direction;
import bibliothek.gui.dock.common.intern.CControlAccess;
import bibliothek.gui.dock.common.intern.CDockable;
import bibliothek.gui.dock.common.intern.CStateManager;

/**
 * A component that is normally set into the center of the
 * main- {@link JFrame}. This component can display
 * and manage some {@link CDockable}s.<br>
 * This component contains in the center a {@link SplitDockStation} allowing
 * to show several {@link CDockable}s at the same time. At each border a
 * {@link FlapDockStation} allows to show "minimized" {@link CDockable}s.<br>
 * Note: clients should not create {@link CContentArea}s directly, they should 
 * use {@link CControl#getContentArea()} to get the default content area, or
 * {@link CControl#createContentArea(String)} to create a new content area. 
 * @author Benjamin Sigg
 */
public class CContentArea extends JPanel{
    
    /** the child in the center */
    private SplitDockStation center;
    
    /** the child at the north border */
    private FlapDockStation north;
    /** the child at the south border */
    private FlapDockStation south;
    /** the child at the east border */
    private FlapDockStation east;
    /** the child at the west border */
    private FlapDockStation west;
    
    /** an identifier for this center */
    private String uniqueId;
    
    /**
     * Creates a new center.
     * @param access connection to a {@link CControl}
     * @param uniqueId a unique identifier of this center
     */
    public CContentArea( final CControlAccess access, String uniqueId ){
    	this.uniqueId = uniqueId;
        center = access.getOwner().getFactory().createSplitDockStation();
        center.setExpandOnDoubleclick( false );
        
        north = access.getOwner().getFactory().createFlapDockStation();
        south = access.getOwner().getFactory().createFlapDockStation();
        east = access.getOwner().getFactory().createFlapDockStation();
        west = access.getOwner().getFactory().createFlapDockStation();
        
        north.setAutoDirection( false );
        north.setDirection( Direction.SOUTH );
        
        south.setAutoDirection( false );
        south.setDirection( Direction.NORTH );
        
        east.setAutoDirection( false );
        east.setDirection( Direction.WEST );
        
        west.setAutoDirection( false );
        west.setDirection( Direction.EAST );
        
        setLayout( new BorderLayout() );
        add( center, BorderLayout.CENTER );
        add( north.getComponent(), BorderLayout.NORTH );
        add( south.getComponent(), BorderLayout.SOUTH );
        add( east.getComponent(), BorderLayout.EAST );
        add( west.getComponent(), BorderLayout.WEST );
        
        CStateManager state = access.getStateManager();
        state.add( getCenterIdentifier(), center );
        state.add( getSouthIdentifier(), south );
        state.add( getNorthIdentifier(), north );
        state.add( getEastIdentifier(), east );
        state.add( getWestIdentifier(), west );
        
        DockFrontend frontend = access.getOwner().intern();
        frontend.addRoot( center, getCenterIdentifier() );
        frontend.addRoot( north, getNorthIdentifier() );
        frontend.addRoot( south, getSouthIdentifier() );
        frontend.addRoot( east, getEastIdentifier() );
        frontend.addRoot( west, getWestIdentifier() );
    }
    
    /**
     * Gets the unique id of this center.
     * @return the unique id
     */
    public String getUniqueId(){
		return uniqueId;
	}
    
    /**
     * Exchanges all the {@link CDockable}s on the center panel by
     * the elements of <code>grid</code>.
     * @param grid a grid containing some new {@link Dockable}s
     */
    public void deploy( CGrid grid ){
        getCenter().dropTree( grid.toTree() );
    }
    
    /**
     * Gets the station in the center of this {@link CContentArea}.
     * @return the central station
     */
    public SplitDockStation getCenter(){
		return center;
	}
    
    /**
     * Gets the station in the north of this {@link CContentArea}
     * @return the station in the north
     */
    public FlapDockStation getNorth(){
		return north;
	}

    /**
     * Gets the station in the south of this {@link CContentArea}
     * @return the station in the south
     */
    public FlapDockStation getSouth(){
		return south;
	}
    
    /**
     * Gets the station in the east of this {@link CContentArea}
     * @return the station in the east
     */
    public FlapDockStation getEast(){
		return east;
	}
    
    /**
     * Gets the station in the west of this {@link CContentArea}
     * @return the station in the west
     */
    public FlapDockStation getWest(){
		return west;
	}
    
    /**
     * Gets the global identifier for the panel in the center.
     * @return the identifier
     */
    public String getCenterIdentifier(){
    	return getCenterIdentifier( uniqueId );
    }
    
    /**
     * Creates the global identifier of a panel in the center.
     * @param uniqueCenterId the unique if of the owning {@link CContentArea}.
     * @return the global identifier
     */
    public static String getCenterIdentifier( String uniqueCenterId ){
    	return uniqueCenterId + " center";
    }

    /**
     * Gets the global identifier for the panel in the north.
     * @return the identifier
     */
    public String getNorthIdentifier(){
    	return getNorthIdentifier( uniqueId );
    }
    
    
    /**
     * Creates the global identifier of a panel in the north.
     * @param uniqueCenterId the unique if of the owning {@link CContentArea}.
     * @return the global identifier
     */
    public static String getNorthIdentifier( String uniqueCenterId ){
    	return uniqueCenterId + " north";
    }


    /**
     * Gets the global identifier for the panel in the south.
     * @return the identifier
     */
    public String getSouthIdentifier(){
    	return getSouthIdentifier( uniqueId );
    }
    
    
    /**
     * Creates the global identifier of a panel in the south.
     * @param uniqueCenterId the unique if of the owning {@link CContentArea}.
     * @return the global identifier
     */
    public static String getSouthIdentifier( String uniqueCenterId ){
    	return uniqueCenterId + " south";
    }

    /**
     * Gets the global identifier for the panel in the east.
     * @return the identifier
     */
    public String getEastIdentifier(){
    	return getEastIdentifier( uniqueId );
    }
    
    /**
     * Creates the global identifier of a panel in the east.
     * @param uniqueCenterId the unique if of the owning {@link CContentArea}.
     * @return the global identifier
     */
    public static String getEastIdentifier( String uniqueCenterId ){
    	return uniqueCenterId + " east";
    }

    /**
     * Gets the global identifier for the panel in the west.
     * @return the identifier
     */
    public String getWestIdentifier(){
    	return getWestIdentifier( uniqueId );
    }
    
    /**
     * Creates the global identifier of a panel in the west.
     * @param uniqueCenterId the unique if of the owning {@link CContentArea}.
     * @return the global identifier
     */
    public static String getWestIdentifier( String uniqueCenterId ){
    	return uniqueCenterId + " west";
    }
}
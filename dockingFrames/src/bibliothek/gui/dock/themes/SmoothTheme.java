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


package bibliothek.gui.dock.themes;

import bibliothek.gui.dock.themes.smooth.SmoothDefaultTitleFactory;

/**
 * This theme uses the {@link SmoothDefaultTitleFactory} to create some
 * titles which smoothly changes their color.
 * @author Benjamin Sigg
 *
 */
public class SmoothTheme extends DefaultTheme {
    /**
     * Constructor, sets the special title-factory of this theme
     */
    public SmoothTheme(){
        setTitleFactory( new SmoothDefaultTitleFactory() );
    }
}
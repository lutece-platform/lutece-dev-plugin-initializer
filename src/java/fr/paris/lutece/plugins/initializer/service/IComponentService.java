/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.paris.lutece.plugins.initializer.service;

import fr.paris.lutece.plugins.lutecetools.business.Component;
import java.util.Collection;

public interface IComponentService
{
    Collection<Component> getComponentList( boolean bLoadFull);
    
    Component getFullComponent( String strArtifactId );
    
    String getComponentAsJsonString( Component component );
}

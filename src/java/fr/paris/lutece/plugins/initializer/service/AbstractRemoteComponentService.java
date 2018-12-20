/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.paris.lutece.plugins.initializer.service;

import fr.paris.lutece.plugins.lutecetools.business.Component;
import fr.paris.lutece.portal.service.util.AppLogService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;


public abstract class AbstractRemoteComponentService implements IComponentService
{
    private static final ObjectMapper _mapper = new ObjectMapper( );
    
    protected abstract String getJSONArtifactIdList( );
    protected abstract String getJSONComponent( String strArtifactId );
    
    
    @Override
    public Collection<Component> getComponentList( boolean bLoadFull )
    {
        Collection<Component> colComponent = new ArrayList<Component>();
        
        String strJSONArtifactIdList = getJSONArtifactIdList();
        try
        {
            ComponentsHolder componentsHolder = _mapper.readValue( strJSONArtifactIdList, ComponentsHolder.class);
            
            if ( bLoadFull )
            {
                // All the comp are totally fetched. This process can take a long time when using remote Components WS
                for ( Component component : componentsHolder.getComponents( ) )
                {
                      String strJSONComponent = getJSONComponent( component.getArtifactId( ) );
                      ComponentHolder componentHolder = _mapper.readValue( strJSONComponent, ComponentHolder.class);
                      colComponent.add( componentHolder.getComponent( ) );
                }
            }
            else
            {
                colComponent = componentsHolder.getComponents();
            }
            
        }
        catch ( IOException e )
        {
            AppLogService.error( " Unable to deserealize json ", e);
        }
        return colComponent;
        
    }
    
    @Override
    public Component getFullComponent( String strArtifactId )
    {
        String strJSONComponent = getJSONComponent( strArtifactId );
        try
        {
            ComponentHolder componentHolder = _mapper.readValue( strJSONComponent, ComponentHolder.class);
            return componentHolder.getComponent( );
        }
        catch ( IOException e )
        {
            AppLogService.error( " Unable to deserealize json ", e);
        }
        return null;
    }
    
    @Override
    public String getComponentAsJsonString( Component component )
    {
        try
        {
            return  _mapper.writeValueAsString( component );
        }
        catch ( IOException e )
        {
            AppLogService.error( "Unable to serealize component", e);
            return null;
        }
    }
    
    @JsonIgnoreProperties( ignoreUnknown = true )
    public static class ComponentsHolder {
        
        @JsonProperty("components")
        private List<Component> _listComponents;

        public List<Component> getComponents()
        {
            return _listComponents;
        }

        @JsonProperty("components")
        public void setComponents( List<Component> _listComponents)
        {
            this._listComponents = _listComponents;
        }
    }
    
    @JsonIgnoreProperties( ignoreUnknown = true )
    public static class ComponentHolder {
        
        @JsonProperty("component")
        private Component _component;

        public Component getComponent()
        {
            return _component;
        }

        @JsonProperty("component")
        public void setComponent( Component component)
        {
            _component = component;
        }
    }
}
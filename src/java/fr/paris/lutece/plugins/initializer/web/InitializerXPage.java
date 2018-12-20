/*
 * Copyright (c) 2002-2018, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
 	
package fr.paris.lutece.plugins.initializer.web;

import fr.paris.lutece.plugins.initializer.service.IComponentService;
import fr.paris.lutece.plugins.lutecetools.business.Component;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.portal.util.mvc.commons.annotations.Action;
import fr.paris.lutece.portal.web.xpages.XPage;
import fr.paris.lutece.portal.util.mvc.xpage.MVCApplication;
import fr.paris.lutece.portal.util.mvc.commons.annotations.View;
import fr.paris.lutece.portal.util.mvc.xpage.annotations.Controller;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * This class provides a simple implementation of an XPage
 */
@Controller( xpageName = "initializer" , pageTitleI18nKey = "initializer.xpage.initializer.pageTitle" , pagePathI18nKey = "initializer.xpage.initializer.pagePathLabel" )
public class InitializerXPage extends MVCApplication
{
    private static final String TEMPLATE_XPAGE = "/skin/plugins/initializer/initialize.html";
    
    // VIEW INITIALIZE
    private static final String VIEW_INITIALIZE = "initialize";
    
    // BEANS
    private static final String BEAN_COMPONENT_SERVICE = "initializer.componentService";
    
    // MARKERS
    private static final String MARK_COMPONENT_LIST = "components_list";
    
    //ACTIONS
    private static final String ACTION_GET_FULL_COMPONENT = "getFullComponent";
    
    //PARAMETERS
    private static final String PARAMETER_ID = "id";
    
    IComponentService _componentService = SpringContextService.getBean( BEAN_COMPONENT_SERVICE );
    /**
     * Returns the content of the page initializer. 
     * @param request The HTTP request
     * @return The view
     */
    @View( value = VIEW_INITIALIZE , defaultView = true )
    public XPage viewHome( HttpServletRequest request )
    {
        Map<String,Object> model = getModel();
        model.put( MARK_COMPONENT_LIST, _componentService.getComponentList( false ) );
        
        return getXPage( TEMPLATE_XPAGE, request.getLocale(  ), model );
    }
    
    @Action( value = ACTION_GET_FULL_COMPONENT )
    public XPage getFullComponent( HttpServletRequest request )
    {
        String strId = request.getParameter( PARAMETER_ID );
        Component fullComponent = _componentService.getFullComponent( strId );
        String jsonComponent = _componentService.getComponentAsJsonString( fullComponent );
        
        return responseJSON( jsonComponent );
    }
}

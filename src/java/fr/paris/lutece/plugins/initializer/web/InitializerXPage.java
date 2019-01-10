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

import fr.paris.lutece.plugins.initializer.business.CategoryComponentHome;
import fr.paris.lutece.plugins.initializer.business.StarterComponentHome;
import fr.paris.lutece.plugins.lutecetools.business.Component;
import fr.paris.lutece.plugins.lutecetools.business.dto.SiteBuilderConfDto;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.portal.util.mvc.commons.annotations.Action;
import fr.paris.lutece.portal.web.xpages.XPage;
import fr.paris.lutece.portal.util.mvc.xpage.MVCApplication;
import fr.paris.lutece.portal.util.mvc.commons.annotations.View;
import fr.paris.lutece.portal.util.mvc.xpage.annotations.Controller;
import fr.paris.lutece.plugins.lutecetools.service.ILuteceToolsService;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;

/**
 * This class provides a simple implementation of an XPage
 */
@Controller( xpageName = "initializer", pageTitleI18nKey = "initializer.xpage.initializer.pageTitle", pagePathI18nKey = "initializer.xpage.initializer.pagePathLabel" )
public class InitializerXPage extends MVCApplication
{
    private static final String TEMPLATE_XPAGE = "/skin/plugins/initializer/initialize.html";

    // VIEW INITIALIZE
    private static final String VIEW_INITIALIZE = "initialize";

    // BEANS
    private static final String BEAN_LUTECE_TOOLS_SERVICE = "initializer.luteceToolsService";

    // MARKERS
    private static final String MARK_COMPONENT_LIST = "components_list";
    private static final String MARK_CATEGORIES_LIST = "categories_list";

    // ACTIONS
    private static final String ACTION_GET_FULL_COMPONENT = "getFullComponent";
    private static final String ACTION_DOWNLOAD_POM_SITE = "doDownloadPomSite";
    private static final String ACTION_GET_ALL_ARTIFACT_ID = "getAllArtifactId";

    // PARAMETERS
    private static final String PARAMETER_ID = "id";
    private static final String PARAMATER_COMPONENTS = "comp";

    ILuteceToolsService _luteceToolsService = SpringContextService.getBean( BEAN_LUTECE_TOOLS_SERVICE );

    /**
     * Returns the content of the page initializer.
     * 
     * @param request
     *            The HTTP request
     * @return The view
     */
    @View( value = VIEW_INITIALIZE, defaultView = true )
    public XPage viewHome( HttpServletRequest request )
    {
        Map<String, Object> model = getModel( );

        model.put( MARK_COMPONENT_LIST, StarterComponentHome.getStarterComponentsList( ) );
        model.put( MARK_CATEGORIES_LIST, CategoryComponentHome.getCategoryComponentsList( ) );

        return getXPage( TEMPLATE_XPAGE, request.getLocale( ), model );
    }

    /**
     * Get a JSON representing the full component
     * 
     * @param request
     *            The HttpServletRequest
     * @return a JSON representing a full component.
     */
    @Action( value = ACTION_GET_FULL_COMPONENT )
    public XPage getFullComponent( HttpServletRequest request )
    {
        String strId = request.getParameter( PARAMETER_ID );
        Component fullComponent = _luteceToolsService.getFullComponent( strId, true );
        String jsonComponent = _luteceToolsService.getComponentAsJsonString( fullComponent );

        return responseJSON( jsonComponent );
    }
    
    
    /**
     * Get a JSON representing the full component
     * 
     * @param request
     *            The HttpServletRequest
     * @return a JSON representing a full component.
     */
    @Action( value = ACTION_GET_ALL_ARTIFACT_ID )
    public XPage getAllLuteceArtifactId( HttpServletRequest request )
    {
        String strJSONArtifactId = _luteceToolsService.getJSONArtifactIdList();

        return responseJSON( strJSONArtifactId );
    }

    /**
     * Action for downloading the site pom
     * 
     * @param request
     *            The HttpServletRequest
     * @return the XML pom file
     */
    @Action( value = ACTION_DOWNLOAD_POM_SITE )
    public XPage doDownloadSitePom( HttpServletRequest request )
    {
        if ( request.getParameterValues( PARAMATER_COMPONENTS ) != null )
        {
            Set<String> setArtifactId = new HashSet<>( Arrays.asList( request.getParameterValues( PARAMATER_COMPONENTS ) ) );
            List<Component> listComponents = _luteceToolsService.getFullComponentList( setArtifactId.toArray( new String [ setArtifactId.size( )] ), true );

            SiteBuilderConfDto siteBuilderConf = new SiteBuilderConfDto( );

            populate( siteBuilderConf, request );
            siteBuilderConf.setListComponents( listComponents );

            String strSitePom = _luteceToolsService.getSitePom( siteBuilderConf );
            return download( strSitePom, "pom.xml", MediaType.APPLICATION_XML_VALUE );
        }
        return redirectView( request, VIEW_INITIALIZE );
    }
}

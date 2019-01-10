/*
 * Copyright (c) 2002-2019, Mairie de Paris
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

package fr.paris.lutece.plugins.initializer.business;

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.util.ReferenceList;
import fr.paris.lutece.util.sql.DAOUtil;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides Data Access methods for StarterComponent objects
 */
public final class StarterComponentDAO implements IStarterComponentDAO
{
    // Constants
    private static final String SQL_QUERY_SELECT = "SELECT id_starter_component, artifact_id, code_category, label_key, label_default, description_key, description_default FROM initializer_starter_component WHERE id_starter_component = ?";
    private static final String SQL_QUERY_INSERT = "INSERT INTO initializer_starter_component ( artifact_id, code_category, label_key, label_default, description_key, description_default ) VALUES ( ?, ?, ?, ?, ?, ? ) ";
    private static final String SQL_QUERY_DELETE = "DELETE FROM initializer_starter_component WHERE id_starter_component = ? ";
    private static final String SQL_QUERY_UPDATE = "UPDATE initializer_starter_component SET id_starter_component = ?, artifact_id = ?, code_category = ?, label_key = ?, label_default = ?, description_key = ?, description_default = ? WHERE id_starter_component = ?";
    private static final String SQL_QUERY_SELECTALL = "SELECT id_starter_component, artifact_id, code_category, label_key, label_default, description_key, description_default FROM initializer_starter_component";
    private static final String SQL_QUERY_SELECTALL_ID = "SELECT id_starter_component FROM initializer_starter_component";

    /**
     * {@inheritDoc }
     */
    @Override
    public void insert( StarterComponent starterComponent, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, Statement.RETURN_GENERATED_KEYS, plugin );
        try
        {
            int nIndex = 1;
            daoUtil.setString( nIndex++, starterComponent.getArtifactId( ) );
            daoUtil.setString( nIndex++, starterComponent.getCodeCategory( ) );
            daoUtil.setString( nIndex++, starterComponent.getLabelKey( ) );
            daoUtil.setString( nIndex++, starterComponent.getLabelDefault( ) );
            daoUtil.setString( nIndex++, starterComponent.getDescriptionKey( ) );
            daoUtil.setString( nIndex++, starterComponent.getDescriptionDefault( ) );

            daoUtil.executeUpdate( );
            if ( daoUtil.nextGeneratedKey( ) )
            {
                starterComponent.setId( daoUtil.getGeneratedKeyInt( 1 ) );
            }
        }
        finally
        {
            daoUtil.free( );
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public StarterComponent load( int nKey, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT, plugin );
        daoUtil.setInt( 1, nKey );
        daoUtil.executeQuery( );
        StarterComponent starterComponent = null;

        if ( daoUtil.next( ) )
        {
            starterComponent = new StarterComponent( );
            int nIndex = 1;

            starterComponent.setId( daoUtil.getInt( nIndex++ ) );
            starterComponent.setArtifactId( daoUtil.getString( nIndex++ ) );
            starterComponent.setCodeCategory( daoUtil.getString( nIndex++ ) );
            starterComponent.setLabelKey( daoUtil.getString( nIndex++ ) );
            starterComponent.setLabelDefault( daoUtil.getString( nIndex++ ) );
            starterComponent.setDescriptionKey( daoUtil.getString( nIndex++ ) );
            starterComponent.setDescriptionDefault( daoUtil.getString( nIndex++ ) );
        }

        daoUtil.free( );
        return starterComponent;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void delete( int nKey, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE, plugin );
        daoUtil.setInt( 1, nKey );
        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void store( StarterComponent starterComponent, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, plugin );
        int nIndex = 1;

        daoUtil.setInt( nIndex++, starterComponent.getId( ) );
        daoUtil.setString( nIndex++, starterComponent.getArtifactId( ) );
        daoUtil.setString( nIndex++, starterComponent.getCodeCategory( ) );
        daoUtil.setString( nIndex++, starterComponent.getLabelKey( ) );
        daoUtil.setString( nIndex++, starterComponent.getLabelDefault( ) );
        daoUtil.setString( nIndex++, starterComponent.getDescriptionKey( ) );
        daoUtil.setString( nIndex++, starterComponent.getDescriptionDefault( ) );
        daoUtil.setInt( nIndex, starterComponent.getId( ) );

        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<StarterComponent> selectStarterComponentsList( Plugin plugin )
    {
        List<StarterComponent> starterComponentList = new ArrayList<StarterComponent>( );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin );
        daoUtil.executeQuery( );

        while ( daoUtil.next( ) )
        {
            StarterComponent starterComponent = new StarterComponent( );
            int nIndex = 1;

            starterComponent.setId( daoUtil.getInt( nIndex++ ) );
            starterComponent.setArtifactId( daoUtil.getString( nIndex++ ) );
            starterComponent.setCodeCategory( daoUtil.getString( nIndex++ ) );
            starterComponent.setLabelKey( daoUtil.getString( nIndex++ ) );
            starterComponent.setLabelDefault( daoUtil.getString( nIndex++ ) );
            starterComponent.setDescriptionKey( daoUtil.getString( nIndex++ ) );
            starterComponent.setDescriptionDefault( daoUtil.getString( nIndex++ ) );

            starterComponentList.add( starterComponent );
        }

        daoUtil.free( );
        return starterComponentList;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<Integer> selectIdStarterComponentsList( Plugin plugin )
    {
        List<Integer> starterComponentList = new ArrayList<Integer>( );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL_ID, plugin );
        daoUtil.executeQuery( );

        while ( daoUtil.next( ) )
        {
            starterComponentList.add( daoUtil.getInt( 1 ) );
        }

        daoUtil.free( );
        return starterComponentList;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public ReferenceList selectStarterComponentsReferenceList( Plugin plugin )
    {
        ReferenceList starterComponentList = new ReferenceList( );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin );
        daoUtil.executeQuery( );

        while ( daoUtil.next( ) )
        {
            starterComponentList.addItem( daoUtil.getInt( 1 ), daoUtil.getString( 2 ) );
        }

        daoUtil.free( );
        return starterComponentList;
    }
}

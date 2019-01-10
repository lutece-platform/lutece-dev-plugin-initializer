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
 * This class provides Data Access methods for CategoryComponent objects
 */
public final class CategoryComponentDAO implements ICategoryComponentDAO
{
    // Constants
    private static final String SQL_QUERY_SELECT = "SELECT id_category_component, code, label_key, label_default FROM initializer_category_component WHERE id_category_component = ?";
    private static final String SQL_QUERY_INSERT = "INSERT INTO initializer_category_component ( code, label_key, label_default ) VALUES ( ?, ?, ? ) ";
    private static final String SQL_QUERY_DELETE = "DELETE FROM initializer_category_component WHERE id_category_component = ? ";
    private static final String SQL_QUERY_UPDATE = "UPDATE initializer_category_component SET id_category_component = ?, code = ?, label_key = ?, label_default = ? WHERE id_category_component = ?";
    private static final String SQL_QUERY_SELECTALL = "SELECT id_category_component, code, label_key, label_default FROM initializer_category_component";
    private static final String SQL_QUERY_SELECTALL_ID = "SELECT id_category_component FROM initializer_category_component";

    /**
     * {@inheritDoc }
     */
    @Override
    public void insert( CategoryComponent categoryComponent, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, Statement.RETURN_GENERATED_KEYS, plugin );
        try
        {
            int nIndex = 1;
            daoUtil.setString( nIndex++ , categoryComponent.getCode( ) );
            daoUtil.setString( nIndex++ , categoryComponent.getLabelKey( ) );
            daoUtil.setString( nIndex++ , categoryComponent.getLabelDefault( ) );
            
            daoUtil.executeUpdate( );
            if ( daoUtil.nextGeneratedKey( ) ) 
            {
                categoryComponent.setId( daoUtil.getGeneratedKeyInt( 1 ) );
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
    public CategoryComponent load( int nKey, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT, plugin );
        daoUtil.setInt( 1 , nKey );
        daoUtil.executeQuery( );
        CategoryComponent categoryComponent = null;

        if ( daoUtil.next( ) )
        {
            categoryComponent = new CategoryComponent();
            int nIndex = 1;
            
            categoryComponent.setId( daoUtil.getInt( nIndex++ ) );
            categoryComponent.setCode( daoUtil.getString( nIndex++ ) );
            categoryComponent.setLabelKey( daoUtil.getString( nIndex++ ) );
            categoryComponent.setLabelDefault( daoUtil.getString( nIndex++ ) );
        }

        daoUtil.free( );
        return categoryComponent;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void delete( int nKey, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE, plugin );
        daoUtil.setInt( 1 , nKey );
        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void store( CategoryComponent categoryComponent, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, plugin );
        int nIndex = 1;
        
        daoUtil.setInt( nIndex++ , categoryComponent.getId( ) );
        daoUtil.setString( nIndex++ , categoryComponent.getCode( ) );
        daoUtil.setString( nIndex++ , categoryComponent.getLabelKey( ) );
        daoUtil.setString( nIndex++ , categoryComponent.getLabelDefault( ) );
        daoUtil.setInt( nIndex , categoryComponent.getId( ) );

        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<CategoryComponent> selectCategoryComponentsList( Plugin plugin )
    {
        List<CategoryComponent> categoryComponentList = new ArrayList<CategoryComponent>(  );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            CategoryComponent categoryComponent = new CategoryComponent(  );
            int nIndex = 1;
            
            categoryComponent.setId( daoUtil.getInt( nIndex++ ) );
            categoryComponent.setCode( daoUtil.getString( nIndex++ ) );
            categoryComponent.setLabelKey( daoUtil.getString( nIndex++ ) );
            categoryComponent.setLabelDefault( daoUtil.getString( nIndex++ ) );

            categoryComponentList.add( categoryComponent );
        }

        daoUtil.free( );
        return categoryComponentList;
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public List<Integer> selectIdCategoryComponentsList( Plugin plugin )
    {
        List<Integer> categoryComponentList = new ArrayList<Integer>( );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL_ID, plugin );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            categoryComponentList.add( daoUtil.getInt( 1 ) );
        }

        daoUtil.free( );
        return categoryComponentList;
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public ReferenceList selectCategoryComponentsReferenceList( Plugin plugin )
    {
        ReferenceList categoryComponentList = new ReferenceList();
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            categoryComponentList.addItem( daoUtil.getInt( 1 ) , daoUtil.getString( 2 ) );
        }

        daoUtil.free( );
        return categoryComponentList;
    }
}

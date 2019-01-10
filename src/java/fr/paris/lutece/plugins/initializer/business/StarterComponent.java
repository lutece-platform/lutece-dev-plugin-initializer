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

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
import java.io.Serializable;

/**
 * This is the business class for the object StarterComponent
 */ 
public class StarterComponent implements Serializable
{
    private static final long serialVersionUID = 1L;

    // Variables declarations 
    private int _nId;
    
    @Size( max = 255 , message = "#i18n{initializer.validation.startercomponent.ArtifactId.size}" ) 
    private String _strArtifactId;
    
    private String _strCodeCategory;
    
    @Size( max = 255 , message = "#i18n{initializer.validation.startercomponent.LabelKey.size}" ) 
    private String _strLabelKey;
    
    private String _strLabelDefault;
    
    @Size( max = 255 , message = "#i18n{initializer.validation.startercomponent.DescriptionKey.size}" ) 
    private String _strDescriptionKey;
    
    private String _strDescriptionDefault;

    /**
     * Returns the Id
     * @return The Id
     */
    public int getId( )
    {
        return _nId;
    }

    /**
     * Sets the Id
     * @param nId The Id
     */ 
    public void setId( int nId )
    {
        _nId = nId;
    }
    
    /**
     * Returns the ArtifactId
     * @return The ArtifactId
     */
    public String getArtifactId( )
    {
        return _strArtifactId;
    }

    /**
     * Sets the ArtifactId
     * @param strArtifactId The ArtifactId
     */ 
    public void setArtifactId( String strArtifactId )
    {
        _strArtifactId = strArtifactId;
    }
    
    /**
     * Returns the CodeCategory
     * @return The CodeCategory
     */
    public String getCodeCategory( )
    {
        return _strCodeCategory;
    }

    /**
     * Sets the CodeCategory
     * @param strCodeCategory The CodeCategory
     */ 
    public void setCodeCategory( String strCodeCategory )
    {
        _strCodeCategory = strCodeCategory;
    }
    
    /**
     * Returns the LabelKey
     * @return The LabelKey
     */
    public String getLabelKey( )
    {
        return _strLabelKey;
    }

    /**
     * Sets the LabelKey
     * @param strLabelKey The LabelKey
     */ 
    public void setLabelKey( String strLabelKey )
    {
        _strLabelKey = strLabelKey;
    }
    
    /**
     * Returns the LabelDefault
     * @return The LabelDefault
     */
    public String getLabelDefault( )
    {
        return _strLabelDefault;
    }

    /**
     * Sets the LabelDefault
     * @param strLabelDefault The LabelDefault
     */ 
    public void setLabelDefault( String strLabelDefault )
    {
        _strLabelDefault = strLabelDefault;
    }
    
    /**
     * Returns the DescriptionKey
     * @return The DescriptionKey
     */
    public String getDescriptionKey( )
    {
        return _strDescriptionKey;
    }

    /**
     * Sets the DescriptionKey
     * @param strDescriptionKey The DescriptionKey
     */ 
    public void setDescriptionKey( String strDescriptionKey )
    {
        _strDescriptionKey = strDescriptionKey;
    }
    
    /**
     * Returns the DescriptionDefault
     * @return The DescriptionDefault
     */
    public String getDescriptionDefault( )
    {
        return _strDescriptionDefault;
    }

    /**
     * Sets the DescriptionDefault
     * @param strDescriptionDefault The DescriptionDefault
     */ 
    public void setDescriptionDefault( String strDescriptionDefault )
    {
        _strDescriptionDefault = strDescriptionDefault;
    }
   
}

/*
 * Copyright 2023 maber01.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.ac.leedsbeckett.lti.services.nrps;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maber01
 */
public class NrpsMembershipContainer
{
  static final Logger logger = Logger.getLogger( NrpsMembershipContainer.class.getName() );
  
  boolean valid=false;
  String id = null;
  NrpsContext context = new NrpsContext();
  ArrayList<NrpsMember> members = new ArrayList<>();
  
  public boolean isValid()
  {
    return valid;
  }

  public String getId()
  {
    return id;
  }
  
  public List<NrpsMember> getMembers()
  {
    return members;
  }
  
  public void load( String json )
  {
    try
    {
      ObjectMapper mapper = new ObjectMapper();
      JsonFactory factory = mapper.getFactory();
      JsonParser parser = factory.createParser( json );
      JsonNode node = mapper.readTree( parser );
      JsonNode child;
      if ( node.isObject() )
      {
        logger.fine( "Loading base JSON object." );
        
        if ( !node.has( "id" ) || 
             !node.has( "context" ) || 
             !node.has( "members" ) )
        {
          logger.warning( "Missing fields." );
          return;
        }
        
        child = node.get( "id" );
        if ( !child.isTextual() )
        {
          logger.warning( "Non-text ID." );
          return;
        }
        id = child.textValue();
        
        child = node.get( "context" );
        if ( !child.isContainerNode() )
        {
          logger.warning( "Non container context." );
          return;
        }
        context.load( child );
        if ( !context.isValid() )
          return;
        
        child = node.get( "members" );
        if ( !child.isArray() )
        {
          logger.warning( "members node is not array" );
          return;          
        }
        
        JsonNode element;
        Iterator<JsonNode> i = child.elements();
        while ( i.hasNext() )
        {
          element = i.next();
          NrpsMember member = new NrpsMember();
          member.load( element );
          if ( !member.isValid() )
            return;
          members.add( member );
        }
      }
      
      valid = true;
    }
    catch ( Exception e )
    {
      logger.log( Level.SEVERE, "Exception while trying to parse JSON.", e );
    }
  }
  
  public void dumpToLog()
  {
    logger.log( Level.INFO, id );
    context.dumpToLog();
    for ( NrpsMember m : members )
      m.dumpToLog();
  }
}

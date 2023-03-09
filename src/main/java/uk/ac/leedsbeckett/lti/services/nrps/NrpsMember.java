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

import com.fasterxml.jackson.databind.JsonNode;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maber01
 */
public class NrpsMember
{
  static final Logger logger = Logger.getLogger( NrpsMember.class.getName() );
  
  boolean valid;

  String name;
  String userId;
  
  public boolean isValid()
  {
    return valid;
  }

  public String getName()
  {
    return name;
  }

  public String getUserId()
  {
    return userId;
  }
  
  public void load( JsonNode node )
  {
    if ( 
         !node.has( "name" ) || 
         !node.get( "name" ).isTextual() || 
         !node.has( "user_id" ) ||
         !node.get( "user_id" ).isTextual()
       )
    {
      logger.warning( "Invalid fields." );
      return;
    }
    name    = node.get( "name" ).textValue();
    userId  = node.get( "user_id" ).textValue();
    
    valid = true;
  }
  
  public void dumpToLog()
  {
    logger.log( Level.INFO, name );
    logger.log( Level.INFO, userId );
  }
}

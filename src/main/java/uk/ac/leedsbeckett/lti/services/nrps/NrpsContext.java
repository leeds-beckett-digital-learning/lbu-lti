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
public class NrpsContext
{
  static final Logger logger = Logger.getLogger( NrpsContext.class.getName() );


  boolean valid=false;
  
  String id;
  String label;
  String title;

  public boolean isValid()
  {
    return valid;
  }

  public String getId()
  {
    return id;
  }

  public String getLabel()
  {
    return label;
  }

  public String getTitle()
  {
    return title;
  }
 
  public void load( JsonNode node )
  {
    if ( 
         !node.has( "id" ) || 
         !node.get( "id" ).isTextual() || 
         !node.has( "label" ) || 
         !node.get( "label" ).isTextual() || 
         !node.has( "title" ) ||
         !node.get( "title" ).isTextual()
       )
    {
      logger.warning( "Invalid fields." );
      return;
    }
    id    = node.get( "id" ).textValue();
    label = node.get( "label" ).textValue();
    title = node.get( "title" ).textValue();
    valid = true;
  }

  public void dumpToLog()
  {
    logger.log( Level.INFO, id );
    logger.log( Level.INFO, label );
    logger.log( Level.INFO, title );
  }
}

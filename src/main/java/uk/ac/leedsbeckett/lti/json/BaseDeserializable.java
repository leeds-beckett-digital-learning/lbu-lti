/*
 * Copyright 2026 maber01.
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
package uk.ac.leedsbeckett.lti.json;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This base class can provide other classes with a standard behaviour for
 * handling unknown fields when deserializing JSON.
 * 
 * @author maber01
 */
public class BaseDeserializable
{
  static final Logger logger = Logger.getLogger( BaseDeserializable.class.getName() );
  
  /**
   * When an unknown field comes along handle it in a standard way.
   * At present a severe event will be logged but no exception will be
   * thrown and the field/value pair will be discarded.
   * 
   * @param name The name of the field.
   * @param value The value of the field.
   */
  @JsonAnySetter 
  public void setExtraField( String name, Object value )
  {
    Exception justforstacktrace = new Exception( "Dummy exception." );
    StringBuilder sb = new StringBuilder();
    sb.append( "Unknown field found in JSON. Class " );
    sb.append( this.getClass().toString() );
    sb.append( " Field " );
    sb.append( name );
    logger.log( Level.SEVERE, name, justforstacktrace );
  }
}

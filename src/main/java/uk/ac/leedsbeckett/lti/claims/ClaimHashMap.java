/*
 * Copyright 2022 Leeds Beckett University.
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

package uk.ac.leedsbeckett.lti.claims;

import io.jsonwebtoken.Claims;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * Provides a super class for any Claim object that works like a dictionary.
 * 
 * @author jon
 */
public abstract class ClaimHashMap implements Serializable
{
  public HashMap map;
  
  /**
   * Construct from named field within generic claims.
   * @param claims The generic jsonwebtoken claims object.
   * @param name The name of the field in the claims to use.
   */
  public ClaimHashMap( Claims claims, String name )
  {
    map = claims.get( name, HashMap.class );
  }

  /**
   * Get a named field from within this object.
   * 
   * @param name The name of the field required.
   * @return The native representation of the requested field.
   */
  public Object getAsObject( String name )
  {
    if ( map == null ) return null;
    return map.get( name );
  }

  /**
   * Get the string value of a named field within this object.
   * @param name The name of the field required.
   * @return The return value of the toString method of the requested field.
   */
  public String getAsString( String name )
  {
    Object value = getAsObject( name );
    if ( value == null ) return null;
    return value.toString();
  }
  
  /**
   * Get a field which is a List of values
   * 
   * @param name The name of the field required.
   * @return A List if the field is a type of List otherwise null;
   */
  public List getAsList( String name )
  {
    Object value = getAsObject( name );
    if ( value == null ) return null;
    if ( value instanceof List )
      return (List)value;
    return null;
  }
}

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
import java.util.List;

/**
 * Provides a super class for any Claim object that works like an indexed list.
 *
 * @author jon
 */
public abstract class ClaimList
{
  public List list;
  
  /**
   * Construct from named field within generic claims.
   * @param claims The generic jsonwebtoken claims object.
   * @param name The name of the field in the claims to use.
   */
  public ClaimList( Claims claims, String name )
  {
    list = (List)claims.get( name );
  }
 
  /**
   * The size of the list, i.e. the number of entries.
   * @return The size.
   */
  public int getSize()
  {
    if ( list == null ) return 0;
    return list.size();
  }
  
  
  /**
   * Get a field from within this object at given position.
   * 
   * @param n The index of the field required.
   * @return The native representation of the requested field.
   */
  public Object getAsObject( int n )
  {
    if ( list == null || n<0 || n>=list.size() ) return null;
    return list.get( n );
  }

  /**
   * Get a field in string form from within this object at given position.
   * 
   * @param n The index of the field required.
   * @return The return value of the toString method of the requested field.
   */
  public String getAsString( int n )
  {
    Object value = getAsObject( n );
    if ( value == null ) return null;
    return value.toString();
  }
}

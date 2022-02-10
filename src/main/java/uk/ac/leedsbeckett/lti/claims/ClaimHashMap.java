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
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author jon
 */
public abstract class ClaimHashMap
{
  public HashMap map;
  
  public ClaimHashMap( Claims claims, String name )
  {
    map = claims.get( name, HashMap.class );
  }

  public Object getAsObject( String name )
  {
    if ( map == null ) return null;
    return map.get( name );
  }

  public String getAsString( String name )
  {
    Object value = getAsObject( name );
    if ( value == null ) return null;
    return value.toString();
  }
  
  public List getAsList( String name )
  {
    Object value = getAsObject( name );
    if ( value == null ) return null;
    if ( value instanceof List )
      return (List)value;
    return null;
  }
}

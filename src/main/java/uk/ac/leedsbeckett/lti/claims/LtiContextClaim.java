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
 *
 * @author jon
 */
public class LtiContextClaim extends ClaimHashMap
{
  public static final String NAME = "https://purl.imsglobal.org/spec/lti/claim/context";
  
  List types;
  
  LtiContextClaim( Claims claims )
  {
    super( claims, NAME );
    types = super.getAsList( "type" );
  }

  public String getId()
  {
    return getAsString( "id" );
  }

  public String getLabel()
  {
    return getAsString( "label" );
  }

  public String getTitle()
  {
    return getAsString( "title" );
  }

  public int getTypeCount()
  {
    if ( types == null ) return 0;
    return types.size();
  }
  
  public String getType( int n )
  {
    if ( types == null ) return null;
    if ( n<0 || n>= types.size() )
      return null;
    return types.get( n ).toString();
  }
}

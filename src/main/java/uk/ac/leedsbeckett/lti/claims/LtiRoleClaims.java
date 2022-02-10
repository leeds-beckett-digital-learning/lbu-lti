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

/**
 * Represents the LTI role claim.
 * 
 * @author jon
 */
public class LtiRoleClaims extends ClaimList
{
  public static final String NAME = "https://purl.imsglobal.org/spec/lti/claim/roles";
  
  Object o;
  boolean standardinstructor=false;
  
  /**
   * Construct from generic jsonwebtoken claims
   * 
   * @param claims The jsonwebtoken claims object.
   */
  LtiRoleClaims( Claims claims )
  {
    super( claims, NAME );
    for ( Object item : list )
      if ( "http://purl.imsglobal.org/vocab/lis/v2/membership#Instructor".equals( item.toString() ) )
        standardinstructor=true;
  }

  public boolean isInStandardInstructorRole()
  {
    return standardinstructor;
  }
}

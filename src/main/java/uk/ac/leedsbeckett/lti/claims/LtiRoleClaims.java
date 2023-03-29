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
import java.util.HashSet;
import java.util.logging.Logger;

/**
 * Represents the LTI role claim.
 * See https://imsglobal.org/spec/lti/v1p3/#role-vocabularies
 * A.2 Role vocabularies
 * 
 * Only a few roles have been implemented here so far.
 * 
 * @author jon
 */
public class LtiRoleClaims extends ClaimList implements Serializable
{
  static final Logger logger = Logger.getLogger( LtiRoleClaims.class.getName() );
  
  public static final String NAME = "https://purl.imsglobal.org/spec/lti/claim/roles";
  
  public static final String MEMBERSHIP_ADMINISTRATOR_ROLE = "http://purl.imsglobal.org/vocab/lis/v2/membership#Administrator";
  public static final String MEMBERSHIP_INSTRUCTOR_ROLE    = "http://purl.imsglobal.org/vocab/lis/v2/membership#Instructor";
  public static final String MEMBERSHIP_LEARNER_ROLE       = "http://purl.imsglobal.org/vocab/lis/v2/membership#Learner";
  public static final String INSTITUTION_STUDENT_ROLE      = "http://purl.imsglobal.org/vocab/lis/v2/institution/person#Student";
  public static final String INSTITUTION_STAFF_ROLE        = "http://purl.imsglobal.org/vocab/lis/v2/institution/person#Staff";
  public static final String INSTITUTION_FACULTY_ROLE      = "http://purl.imsglobal.org/vocab/lis/v2/institution/person#Faculty";
  public static final String SYSTEM_ADMINISTRATOR_ROLE     = "http://purl.imsglobal.org/vocab/lis/v2/system/person#Administrator";


  final HashSet roleSet = new HashSet<>();
  
  
  /**
   * Construct from generic jsonwebtoken claims
   * 
   * @param claims The jsonwebtoken claims object.
   */
  LtiRoleClaims( Claims claims )
  {
    super( claims, NAME );
    for ( Object item : list )
    {
      logger.fine( item.toString() );
      roleSet.add( item );
    }
  }

  public boolean isInRole( String role )
  {
    return roleSet.contains( role );
  }  
}

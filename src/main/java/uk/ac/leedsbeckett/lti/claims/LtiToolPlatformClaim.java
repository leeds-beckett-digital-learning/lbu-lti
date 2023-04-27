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

/**
 * Represents the LTI tool platform claim.
 * 
 * @author jon
 */
public class LtiToolPlatformClaim extends ClaimHashMap implements Serializable
{
  public static final String NAME = "https://purl.imsglobal.org/spec/lti/claim/tool_platform";

  /**
   * Construct from generic jsonwebtoken claims
   * 
   * @param claims The jsonwebtoken claims object.
   */
  public LtiToolPlatformClaim( Claims claims )
  {
    super( claims, NAME );
  }
  
  public String getGuid()
  {
    return getAsString( "guid" );
  }

  public String getUrl()
  {
    return getAsString( "url" );
  }
  
  public String getContactEmail()
  {
    return getAsString( "contact_email" );
  }

  public String getDescription()
  {
    return getAsString( "description" );
  }

  public String getName()
  {
    return getAsString( "name" );
  }

  public String getProductFamilyCode()
  {
    return getAsString( "product_family_code" );
  }

  public String getVersion()
  {
    return getAsString( "version" );
  }

}

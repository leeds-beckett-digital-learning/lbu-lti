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

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.jsonwebtoken.Claims;
import java.io.Serializable;

/**
 * Represents the LTI tool platform claim.
 * 
 * @author jon
 */
public class LtiLaunchPresentationClaim extends ClaimHashMap implements Serializable
{
  public static final String NAME = "https://purl.imsglobal.org/spec/lti/claim/launch_presentation";

  /**
   * Construct from generic jsonwebtoken claims
   * 
   * @param claims The jsonwebtoken claims object.
   */
  public LtiLaunchPresentationClaim( Claims claims )
  {
    super( claims, NAME );
  }
  
  public String getDocumentTarget()
  {
    return getAsString( "document_target" );
  }

  public String getHeight()
  {
    return getAsString( "height" );
  }

  public String getWidth()
  {
    return getAsString( "width" );
  }

  /**
   * If the return URL is not null it can be modified by adding specific query
   * parameters either for logging/error reporting.
   * 
   * Query parameters:
   * lti_errormsg error message for user.
   * lti_msg non-error message for user.
   * lti_errorlog message for platform error log
   * lti_log message for platform non-error log
   * 
   * Platforms must allow these parameters but may choose not not to do 
   * anything with them.
   * 
   * @return The return URL for this LTI launch.
   */
  public String getReturnUrl()
  {
    return getAsString( "return_url" );
  }
  
  public String getLocale()
  {
    return getAsString( "locale" );
  }
}

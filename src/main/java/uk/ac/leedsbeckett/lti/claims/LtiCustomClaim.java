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
 * Represents the LTI custom claim.
 * 
 * @author jon
 */
public class LtiCustomClaim extends ClaimHashMap
{
  public static final String NAME = "https://purl.imsglobal.org/spec/lti/claim/custom";
  
  String toolName, toolType;
  
  /**
   * Construct from generic jsonwebtoken claims
   * 
   * @param claims The jsonwebtoken claims object.
   */
  public LtiCustomClaim( Claims claims )
  {
    super( claims, NAME );
    toolName = super.getAsString( "lti.jonmaber.co.uk#tool_name" );
    toolType = super.getAsString( "lti.jonmaber.co.uk#tool_type" );
  }
  
  public String getToolName()
  {
    return toolName;
  }  
  
  public String getToolType()
  {
    return toolType;
  }  
}

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
 * Represents the LTI resource claim.
 * 
 * @author jon
 */
public class LtiResourceClaim extends ClaimHashMap implements Serializable
{
  public static final String NAME = "https://purl.imsglobal.org/spec/lti/claim/resource_link";

  String id;
  String description;
  String title;
  
  /**
   * Construct from generic jsonwebtoken claims
   * 
   * @param claims The jsonwebtoken claims object.
   */
  public LtiResourceClaim( Claims claims )
  {
    super( claims, NAME );
    id          = super.getAsString( "id"         );
    description = super.getAsString( "decription" );
    title       = super.getAsString( "title"      );
  }

  public String getId()
  {
    return id;
  }

  public String getDescription()
  {
    return description;
  }

  public String getTitle()
  {
    return title;
  }
  
  
}

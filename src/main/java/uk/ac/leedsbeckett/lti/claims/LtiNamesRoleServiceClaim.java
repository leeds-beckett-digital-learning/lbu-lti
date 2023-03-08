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
import java.util.List;

/**
 * Represents the LTI custom claim.
 * 
 * @author jon
 */
public class LtiNamesRoleServiceClaim extends ClaimHashMap implements Serializable
{
  public static final String NAME = "https://purl.imsglobal.org/spec/lti-nrps/claim/namesroleservice";
  
  String contextMembershipsUrl;
  List<String> scope;
  List<String> serviceVersions;
  
  
  /**
   * Construct from generic jsonwebtoken claims
   * 
   * @param claims The jsonwebtoken claims object.
   */
  public LtiNamesRoleServiceClaim( Claims claims )
  {
    super( claims, NAME );
    contextMembershipsUrl = super.getAsString( "context_memberships_url" );
    scope = super.getAsList( "scope" );
    serviceVersions = super.getAsList( "service_versions" );
  }

  public String getContextMembershipsUrl()
  {
    return contextMembershipsUrl;
  }

  public List<String> getScope()
  {
    return scope;
  }

  public List<String> getServiceVersions()
  {
    return serviceVersions;
  }
}

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
import uk.ac.leedsbeckett.lti.services.LtiServiceScope;

/**
 * Represents the LTI custom claim.
 * 
 * @author jon
 */
public class LtiNamesRoleServiceClaim extends ClaimHashMap implements Serializable
{
  public static final String NAME                = "https://purl.imsglobal.org/spec/lti-nrps/claim/namesroleservice";
  public static final LtiServiceScope SCOPE = 
          new LtiServiceScope( "https://purl.imsglobal.org/spec/lti-nrps/scope/contextmembership.readonly" );
  
  String contextMembershipsUrl;
  List<String> serviceVersions;
  
  
  /**
   * Construct from generic jsonwebtoken claims
   * 
   * https://www.imsglobal.org/spec/lti-nrps/v2p0#lti-1-3-integration
   * Section 3.6.1.1
   * 
   * @param claims The jsonwebtoken claims object.
   */
  public LtiNamesRoleServiceClaim( Claims claims )
  {
    super( claims, NAME );
    contextMembershipsUrl = super.getAsString( "context_memberships_url" );
    serviceVersions = super.getAsList( "service_versions" );
  }

  public String getContextMembershipsUrl()
  {
    return contextMembershipsUrl;
  }

  public List<String> getServiceVersions()
  {
    return serviceVersions;
  }
}

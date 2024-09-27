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

package uk.ac.leedsbeckett.lti.services.ags;

import io.jsonwebtoken.Claims;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import uk.ac.leedsbeckett.lti.claims.ClaimHashMap;
import uk.ac.leedsbeckett.lti.services.LtiServiceScope;
import uk.ac.leedsbeckett.lti.services.LtiServiceScopeSet;

/**
 * Represents the LTI custom claim.
 * 
 * @author jon
 */
public class LtiAssessmentAndGradesServiceClaim extends ClaimHashMap implements Serializable
{
  public static final String NAME                    = "https://purl.imsglobal.org/spec/lti-ags/claim/endpoint";          
  
	public static final LtiServiceScope SCOPE_LINEITEM          = new LtiServiceScope( "https://purl.imsglobal.org/spec/lti-ags/scope/lineitem" );
	public static final LtiServiceScope SCOPE_RESULT            = new LtiServiceScope( "https://purl.imsglobal.org/spec/lti-ags/scope/result.readonly" );
	public static final LtiServiceScope SCOPE_SCORE             = new LtiServiceScope( "https://purl.imsglobal.org/spec/lti-ags/scope/score" );
	public static final LtiServiceScope SCOPE_LINEITEM_READONLY = new LtiServiceScope( "https://purl.imsglobal.org/spec/lti-ags/scope/lineitem.readonly" );
  
  String lineItem = null;
  String lineItems = null;
  LtiServiceScopeSet scopeSet = new LtiServiceScopeSet();
  
  
  /**
   * Construct from generic jsonwebtoken claims
   * 
   * https://www.imsglobal.org/spec/lti-nrps/v2p0#lti-1-3-integration
   * Section 3.6.1.1
   * 
   * @param claims The jsonwebtoken claims object.
   */
  public LtiAssessmentAndGradesServiceClaim( Claims claims )
  {
    super( claims, NAME );
    lineItem = super.getAsString( "lineitem" );
    lineItems = super.getAsString( "lineitems" );
    List list = super.getAsList( "scope" );
    if ( list != null )
      for ( Object o : list )
        scopeSet.addScope( new LtiServiceScope( o.toString() ) );
  }

  public String getLineItemUrl()
  {
    return lineItem;
  }

  public String getLineItemsUrl()
  {
    return lineItems;
  }

  public boolean isLineItemInScope()
  {
    return scopeSet.containsScope( SCOPE_LINEITEM );
  }

  public boolean isLineItemReadOnlyInScope()
  {
    return scopeSet.containsScope( SCOPE_LINEITEM_READONLY );
  }

  public boolean isResultInScope()
  {
    return scopeSet.containsScope( SCOPE_RESULT );
  }

  public boolean isScoreInScope()
  {
    return scopeSet.containsScope( SCOPE_SCORE );
  }
}

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
import java.util.List;

/**
 * Represents the LTI tool platform claim.
 * 
 * @author jon
 */
public class LtiDeepLinkingSettings extends ClaimHashMap implements Serializable
{
  public static final String NAME = "https://purl.imsglobal.org/spec/lti-dl/claim/deep_linking_settings";
  List accepttypes;
  List acceptpresentationdocumenttargets;

  /**
   * Construct from generic jsonwebtoken claims
   * 
   * @param claims The jsonwebtoken claims object.
   */
  public LtiDeepLinkingSettings( Claims claims )
  {
    super( claims, NAME );
    accepttypes = getAsList( "accept_types" );
    acceptpresentationdocumenttargets = getAsList( "accept_presentation_document_targets" );
  }
  
  public String getDeepLinkReturnUrl()
  {
    return getAsString( "deep_link_return_url" );
  }
  
  public String[] getAcceptTypes()
  {
    if ( accepttypes == null )
      return new String[0];
    String[] v = new String[accepttypes.size()];
    for ( int i=0; i<v.length; i++ )
      v[i] = accepttypes.get( i ).toString();
    return v;
  }

  public String[] getAcceptPresentationDocumentTargets()
  {
    if ( acceptpresentationdocumenttargets == null )
      return new String[0];
    String[] v = new String[acceptpresentationdocumenttargets.size()];
    for ( int i=0; i<v.length; i++ )
      v[i] = acceptpresentationdocumenttargets.get( i ).toString();
    return v;
  }

  public String[] getAcceptMediaTypes()
  {
    String raw = getAsString( "accept_media_types" );
    if ( raw == null )
      return new String[0];
    return raw.split( ";" );
  }

}

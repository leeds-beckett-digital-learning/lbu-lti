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

package uk.ac.leedsbeckett.lti;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import java.security.PublicKey;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import uk.ac.leedsbeckett.lti.LtiConfiguration.Client;
import uk.ac.leedsbeckett.lti.state.LtiState;

/**
 *
 * @author jon
 */
public class LtiMessageLaunch
{
  HttpServletRequest request;
  LtiState state;
  Claims claims;
  
  public LtiMessageLaunch( HttpServletRequest request, LtiState state )
  {
    this.request = request;
    this.state = state;
  }
  
  public void validate() throws LtiException
  {
    if ( state == null )
      throw new LtiException( "No state" );
    Client client = state.getClient();
    if ( client == null )
      throw new LtiException( "No client" );
    PublicKey pk = client.getPublicKey();
    if ( pk == null )
      throw new LtiException( "No client public key" );
    String id_token = request.getParameter( "id_token" );
    if ( StringUtils.isEmpty( id_token ) )
      throw new LtiException( "No id_token" );
    
    Jws<Claims> jws = Jwts.parserBuilder().setSigningKey( pk ).build().parseClaimsJws( id_token );
    
    String nonce = state.getNonce();
    if ( nonce == null )
      throw new LtiException( "Attempt to reuse old nonce." );
    
    if ( !nonce.equals( jws.getBody().get( "nonce" ) ) )
      throw new LtiException( "Nonce doesn't match the one created by Login servlet." );
    
    claims = jws.getBody();
    state.clearNonce();  // To be sure it cannot be used again
  }

  public Claims getClaims()
  {
    return claims;
  }

  public LtiState getState()
  {
    return state;
  }
  
  
}

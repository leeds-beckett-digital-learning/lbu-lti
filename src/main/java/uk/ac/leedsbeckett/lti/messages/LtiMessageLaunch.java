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

package uk.ac.leedsbeckett.lti.messages;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import uk.ac.leedsbeckett.lti.LtiException;
import uk.ac.leedsbeckett.lti.config.ClientLtiConfiguration;
import uk.ac.leedsbeckett.lti.config.LtiConfiguration;
import uk.ac.leedsbeckett.lti.state.LtiState;

/**
 * Represents an LTI Message of the type that requests a tool launch.
 * 
 * @author jon
 */
public class LtiMessageLaunch
{
  static final Logger logger = Logger.getLogger( LtiMessageLaunch.class.getName() );

  HttpServletRequest request;
  LtiState state;
  Claims claims;

  /**
   * Constructor uses an HttpServletRequest to find out the user id_token
   * and a reference to an LtiState object.
   * 
   * @param request The HTTP request from the servlet that handles the launch request.
   * @param state The LtiState that was created by the login servlet
   */
  public LtiMessageLaunch( HttpServletRequest request, LtiState state )
  {
    this.request = request;
    this.state = state;
  }
  
  /**
   * Validates the claims carried by this message.
   * 
   * @param config The configuration to use for validation.
   * @throws LtiException Thrown if information is missing but mainly if the digital signature is invalid.
   */
  public void validate( LtiConfiguration config ) throws LtiException
  {
    if ( state == null )
      throw new LtiException( "No state" );
    ClientLtiConfiguration clientconfig = config.getClientLtiConfiguration( state.getClientKey() );
    if ( clientconfig == null )
      throw new LtiException( "No client" );
    String id_token = request.getParameter( "id_token" );
    if ( StringUtils.isEmpty( id_token ) )
      throw new LtiException( "No id_token" );

    logger.info( "Parsing id_token." );
    Jws<Claims> jws = Jwts.parserBuilder().setSigningKeyResolver( clientconfig ).build().parseClaimsJws( id_token );
    
    //  Nonce check is moved into the LtiStateStore getState method
    claims = jws.getBody();
  }

  /** 
   * Retrieve the secure claims.
   * 
   * @return The claims.
   */
  public Claims getClaims()
  {
    return claims;
  }

  /** 
   * Retrieve the LTI state object
   * 
   * @return The state.
   */
  public LtiState getState()
  {
    return state;
  }  
}

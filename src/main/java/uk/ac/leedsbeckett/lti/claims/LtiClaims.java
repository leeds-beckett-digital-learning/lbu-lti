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
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * A wrapper class for a standard jsonwebtoken Claims object. This provides
 * access to parsed out LTI specific claims.
 * 
 * @author jon
 */
public class LtiClaims implements Claims
{
  Claims               wrapped;
  
  LtiContextClaim      lticontext      = null;
  LtiCustomClaim       lticustom       = null;
  LtiResourceClaim     ltiresource     = null;
  LtiRoleClaims        ltiroles        = null;
  LtiToolPlatformClaim ltitoolplatform = null;

  /**
   * Constructs an object by wrapping a given claims object.
   * 
   * @param wrapped The claims to parse and wrap.
   */
  public LtiClaims( Claims wrapped )
  {
    assert wrapped != null;
    this.wrapped = wrapped;
    if ( wrapped.containsKey( LtiContextClaim.NAME ) )
      lticontext = new LtiContextClaim( wrapped );
    if ( wrapped.containsKey( LtiCustomClaim.NAME ) )
      lticustom = new LtiCustomClaim( wrapped );
    if ( wrapped.containsKey( LtiResourceClaim.NAME ) )
      ltiresource = new LtiResourceClaim( wrapped );
    if ( wrapped.containsKey( LtiRoleClaims.NAME ) )
      ltiroles = new LtiRoleClaims( wrapped );
    if ( wrapped.containsKey( LtiToolPlatformClaim.NAME ) )
      ltitoolplatform = new LtiToolPlatformClaim( wrapped );
  }

  /**
   * Gets the parsed LtiContext claim.
   * 
   * @return The parsed object.
   */
  public LtiContextClaim getLtiContext()
  {
    return lticontext;
  }

  /**
   * Gets the parsed LtiCustom claim.
   * 
   * @return The parsed object.
   */
  public LtiCustomClaim getLtiCustom()
  {
    return lticustom;
  }

  /**
   * Gets the parsed LtiResource claim.
   * 
   * @return The parsed object.
   */
  public LtiResourceClaim getLtiResource()
  {
    return ltiresource;
  }

  /**
   * Gets the parsed LtiRoles claim.
   * 
   * @return The parsed object.
   */
  public LtiRoleClaims getLtiRoles()
  {
    return ltiroles;
  }

  /**
   * Gets the parsed LtiToolPlatform claim.
   * 
   * @return The parsed object.
   */
  public LtiToolPlatformClaim getLtiToolPlatform()
  {
    return ltitoolplatform;
  }


  
  
  
  
  @Override
  public String getIssuer()
  {
    return wrapped.getIssuer();
  }

  @Override
  public Claims setIssuer( String arg0 )
  {
    assert false;
    return this;
  }

  @Override
  public String getSubject()
  {
    return wrapped.getSubject();
  }

  @Override
  public Claims setSubject( String arg0 )
  {
    assert false;
    return this;
  }

  @Override
  public String getAudience()
  {
    return wrapped.getAudience();
  }

  @Override
  public Claims setAudience( String arg0 )
  {
    assert false;
    return this;
  }

  @Override
  public Date getExpiration()
  {
    return wrapped.getExpiration();
  }

  @Override
  public Claims setExpiration( Date arg0 )
  {
    assert false;
    return this;
  }

  @Override
  public Date getNotBefore()
  {
    return wrapped.getNotBefore();
  }

  @Override
  public Claims setNotBefore( Date arg0 )
  {
    assert false;
    return this;
  }

  @Override
  public Date getIssuedAt()
  {
    return wrapped.getIssuedAt();
  }

  @Override
  public Claims setIssuedAt( Date arg0 )
  {
    assert false;
    return this;
  }

  @Override
  public String getId()
  {
    return wrapped.getId();
  }

  @Override
  public Claims setId( String arg0 )
  {
    assert false;
    return this;
  }

  @Override
  public <T> T get( String arg0, Class<T> arg1 )
  {
    return wrapped.get( arg0, arg1 );
  }

  @Override
  public Object get( Object arg0 )
  {
    return wrapped.get( arg0 );
  }

  @Override
  public int size()
  {
    return wrapped.size();
  }

  @Override
  public boolean isEmpty()
  {
    return wrapped.isEmpty();
  }

  @Override
  public boolean containsKey( Object arg0 )
  {
    return wrapped.containsKey( arg0 );
  }

  @Override
  public boolean containsValue( Object arg0 )
  {
    return wrapped.containsValue( arg0 );
  }

  @Override
  public Object put( String arg0, Object arg1 )
  {
    assert false;
    return arg1;
  }

  @Override
  public Object remove( Object arg0 )
  {
    assert false;
    return arg0;
  }

  @Override
  public void putAll( Map<? extends String, ? extends Object> arg0 )
  {
    assert false;
  }

  @Override
  public void clear()
  {
    assert false;
  }

  @Override
  public Set<String> keySet()
  {
    return wrapped.keySet();
  }

  @Override
  public Collection<Object> values()
  {
    return wrapped.values();
  }

  @Override
  public Set<Entry<String, Object>> entrySet()
  {
    return wrapped.entrySet();
  }
  
  
}

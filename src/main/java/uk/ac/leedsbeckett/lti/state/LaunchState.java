/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.ac.leedsbeckett.lti.state;

import uk.ac.leedsbeckett.lti.claims.LtiRoleClaims;

/**
 *
 * @author jon
 */
public class LaunchState
{
  private String personName;
  private String platformName;
  private LtiRoleClaims roles;

  public String getPersonName()
  {
    return personName;
  }

  public void setPersonName( String personName )
  {
    this.personName = personName;
  }

  public String getPlatformName()
  {
    return platformName;
  }

  public void setPlatformName( String platformName )
  {
    this.platformName = platformName;
  }

  public LtiRoleClaims getRoles()
  {
    return roles;
  }

  public void setRoles( LtiRoleClaims roles )
  {
    this.roles = roles;
  }
  
  
}

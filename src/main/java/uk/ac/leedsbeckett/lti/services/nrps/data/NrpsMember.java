/*
 * Copyright 2023 maber01.
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
package uk.ac.leedsbeckett.lti.services.nrps.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maber01
 */
public class NrpsMember implements Serializable
{
  static final Logger logger = Logger.getLogger( NrpsMember.class.getName() );
  
  private final String status;
  private final String givenName;
  private final String familyName;
  private final String middleName;
  private final String name;
  private final String userId;
  private final String email;
  private final String lisPersonSourcedid;
  private final String[] roles;

  public NrpsMember( 
          @JsonProperty("status")      String status, 
          @JsonProperty("given_name")  String givenName, 
          @JsonProperty("family_name") String familyName, 
          @JsonProperty("middle_name") String middleName, 
          @JsonProperty("name")        String name, 
          @JsonProperty("user_id")     String userId, 
          @JsonProperty("email")       String email, 
          @JsonProperty("lis_person_sourcedid")     String lisPersonSourcedid, 
          @JsonProperty("roles")       String[] roles )
  {
    this.status = status;
    this.givenName = givenName;
    this.familyName = familyName;
    this.middleName = middleName;
    this.name = name;
    this.userId = userId;
    this.email = email;
    this.lisPersonSourcedid = lisPersonSourcedid;
    this.roles = roles;
  }

  public String getStatus()
  {
    return status;
  }

  public String getGivenName()
  {
    return givenName;
  }

  public String getFamilyName()
  {
    return familyName;
  }

  public String getMiddleName()
  {
    return middleName;
  }
    
  public String getName()
  {
    return name;
  }

  public String getUserId()
  {
    return userId;
  }

  public String getEmail()
  {
    return email;
  }

  public String getLisPersonSourcedid()
  {
    return lisPersonSourcedid;
  }

  public String[] getRoles()
  {
    return roles;
  }
  
  public void dumpToLog()
  {
    logger.log( Level.INFO, name );
    logger.log( Level.INFO, userId );
  }
}
